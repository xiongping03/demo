package com.wechat.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.XmlUtil;
import com.wechat.model.message.BaseMessage;
import com.wechat.service.IWechatService;
import com.wechat.service.impl.WechatServiceImpl;
import com.wechathandle.MessageHandler;
import com.wechathandle.impl.EventMessageHandle;
import com.wechathandle.impl.ImageMessageHandle;
import com.wechathandle.impl.NewsMessageHandle;
import com.wechathandle.impl.TextMessageHandle;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	private final static String token = "wechat";
	@Autowired
	private IWechatService wechatService;
  
    /**У��ǩ��
     * @param model
     * @return
     * @throws Exception 
     */
	@RequestMapping(value="/connect",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String connect(HttpServletRequest request) throws Exception{
		//�ж���ʲô����
		boolean isGet = request.getMethod().toLowerCase().equals("get"); 
		System.out.println("�ұ�����");
		if (isGet) {
			//΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
	    	String signature = request.getParameter("signature");
	    	//ʱ���
	    	String timestamp = request.getParameter("timestamp");
	    	//�����
	    	String nonce = request.getParameter("nonce");
	    	//����ַ���
	    	String echostr = request.getParameter("echostr");
	    	if (check(timestamp,nonce,token,signature)) {
				 return echostr;
			}else{
				 return "����ʧ��"; 
			}
		}else{
			Map<String, String> mapForRequest = XmlUtil.getMapForRequest(request);
			String response = getResponse(mapForRequest);
			System.out.println(response);
			System.out.println(WechatServiceImpl.getAccessToken());
			return response;
		}
    	
    	
	}
    //��֤ǩ��
    private boolean check(String timestamp,String nonce,String token,String signature){
    	/* 1����token��timestamp��nonce�������������ֵ������� 
    	 * 2�������������ַ���ƴ�ӳ�һ���ַ�������sha1���� 
    	 * 3�������߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
    	 * 	*/
    	String [] strs = new String[]{token,timestamp,nonce};
    	Arrays.sort(strs);
    	String str = strs[0]+strs[1]+strs[2];
    	//����
    	String sha1Hex = DigestUtils.sha1Hex(str);
    	return signature.equals(sha1Hex);
    	
    }
    
    private String getResponse(Map<String, String> requestMap){
    	System.out.println(requestMap.toString());
    	String msgType = requestMap.get("MsgType");
    	MessageHandler messageHandler = null ;
    	switch (msgType) {
		case "text":
			if ("ͼ��".equals(requestMap.get("Content"))) {
				messageHandler = new NewsMessageHandle();
			}
			else {
				messageHandler = new TextMessageHandle();
			}
			
			break;
		case "event"://�û����click�˵�
			messageHandler = new EventMessageHandle();
			break;
		case "image"://�û�����imageͼƬ
			messageHandler = new ImageMessageHandle();
			break;
		default:
			break;
		}
    	BaseMessage baseMessage = messageHandler.handleMessage(requestMap);
    	return XmlUtil.getXmlForBean(baseMessage);
    			 
    }
}
