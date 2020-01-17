package com.wechathandle.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.util.HttpClientUtil;
import com.util.MapUtil;
import com.wechat.model.message.BaseMessage;
import com.wechat.model.message.TextMessage;
import com.wechathandle.MessageHandler;

public class TextMessageHandle implements MessageHandler{

	@Override
	public BaseMessage handleMessage(Map<String, String> requestMap) {
		String msg = requestMap.get("Content");
		String response = "";
		if ("��¼".equals(msg)) {
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe213e29b3573b3e0&redirect_uri=http://www.ilovemdl.cn/OfficialAccount/userManager/getUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
			response = "���<a href = \""+url+"\">����</a>��¼";
		}else{
			response = machineChat(msg);
		}
		
		TextMessage textMessage = new TextMessage(requestMap, response);
		return textMessage;
	}
	//����������
	private String machineChat(String msg){
		String appKey =  "7cd94e4abeffab76500d5ab687eab12b";
		String chatURL =  "http://op.juhe.cn/iRobot/index?";//�����ַ
		Map<String, String> parameterMap = new HashMap<String, String>();//�������
		parameterMap.put("key", appKey);
		try {
			parameterMap.put("info", URLEncoder.encode(msg,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameterMap.put("dtype", "");//�������� JSON XML
		parameterMap.put("loc", "");//�ص�
		parameterMap.put("lon", "");//����
		parameterMap.put("lat", "");//ά��
		parameterMap.put("userid", "");//�û�id
		String httpurl =  chatURL+MapUtil.asUrlParams(parameterMap);
	
	    String rspData = HttpClientUtil.doGet(httpurl);  
	    //System.out.println(rspData);
	    //����json
	    JSONObject jsonObject = JSONObject.parseObject(rspData);
	    int result = (int)jsonObject.get("error_code");   
	    Object resultObject = result==0?jsonObject.getJSONObject("result").get("text"):jsonObject.get("reason");
		return (String)resultObject;
	}

}
