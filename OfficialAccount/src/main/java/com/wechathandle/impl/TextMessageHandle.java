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
		if ("登录".equals(msg)) {
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe213e29b3573b3e0&redirect_uri=http://www.ilovemdl.cn/OfficialAccount/userManager/getUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
			response = "点击<a href = \""+url+"\">这里</a>登录";
		}else{
			response = machineChat(msg);
		}
		
		TextMessage textMessage = new TextMessage(requestMap, response);
		return textMessage;
	}
	//机器人聊天
	private String machineChat(String msg){
		String appKey =  "7cd94e4abeffab76500d5ab687eab12b";
		String chatURL =  "http://op.juhe.cn/iRobot/index?";//请求地址
		Map<String, String> parameterMap = new HashMap<String, String>();//请求参数
		parameterMap.put("key", appKey);
		try {
			parameterMap.put("info", URLEncoder.encode(msg,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameterMap.put("dtype", "");//数据类型 JSON XML
		parameterMap.put("loc", "");//地点
		parameterMap.put("lon", "");//经度
		parameterMap.put("lat", "");//维度
		parameterMap.put("userid", "");//用户id
		String httpurl =  chatURL+MapUtil.asUrlParams(parameterMap);
	
	    String rspData = HttpClientUtil.doGet(httpurl);  
	    //System.out.println(rspData);
	    //解析json
	    JSONObject jsonObject = JSONObject.parseObject(rspData);
	    int result = (int)jsonObject.get("error_code");   
	    Object resultObject = result==0?jsonObject.getJSONObject("result").get("text"):jsonObject.get("reason");
		return (String)resultObject;
	}

}
