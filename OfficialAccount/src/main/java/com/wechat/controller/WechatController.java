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
  
    /**校验签名
     * @param model
     * @return
     * @throws Exception 
     */
	@RequestMapping(value="/connect",method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String connect(HttpServletRequest request) throws Exception{
		//判断是什么方法
		boolean isGet = request.getMethod().toLowerCase().equals("get"); 
		System.out.println("我被请求");
		if (isGet) {
			//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	    	String signature = request.getParameter("signature");
	    	//时间戳
	    	String timestamp = request.getParameter("timestamp");
	    	//随机数
	    	String nonce = request.getParameter("nonce");
	    	//随机字符串
	    	String echostr = request.getParameter("echostr");
	    	if (check(timestamp,nonce,token,signature)) {
				 return echostr;
			}else{
				 return "接入失败"; 
			}
		}else{
			Map<String, String> mapForRequest = XmlUtil.getMapForRequest(request);
			String response = getResponse(mapForRequest);
			System.out.println(response);
			System.out.println(WechatServiceImpl.getAccessToken());
			return response;
		}
    	
    	
	}
    //验证签名
    private boolean check(String timestamp,String nonce,String token,String signature){
    	/* 1）将token、timestamp、nonce三个参数进行字典序排序 
    	 * 2）将三个参数字符串拼接成一个字符串进行sha1加密 
    	 * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
    	 * 	*/
    	String [] strs = new String[]{token,timestamp,nonce};
    	Arrays.sort(strs);
    	String str = strs[0]+strs[1]+strs[2];
    	//加密
    	String sha1Hex = DigestUtils.sha1Hex(str);
    	return signature.equals(sha1Hex);
    	
    }
    
    private String getResponse(Map<String, String> requestMap){
    	System.out.println(requestMap.toString());
    	String msgType = requestMap.get("MsgType");
    	MessageHandler messageHandler = null ;
    	switch (msgType) {
		case "text":
			if ("图文".equals(requestMap.get("Content"))) {
				messageHandler = new NewsMessageHandle();
			}
			else {
				messageHandler = new TextMessageHandle();
			}
			
			break;
		case "event"://用户点击click菜单
			messageHandler = new EventMessageHandle();
			break;
		case "image"://用户发送image图片
			messageHandler = new ImageMessageHandle();
			break;
		default:
			break;
		}
    	BaseMessage baseMessage = messageHandler.handleMessage(requestMap);
    	return XmlUtil.getXmlForBean(baseMessage);
    			 
    }
}
