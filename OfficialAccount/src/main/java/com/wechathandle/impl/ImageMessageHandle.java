package com.wechathandle.impl;

import java.util.HashMap;
import java.util.Map;







import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.wechat.model.message.BaseMessage;
import com.wechat.model.message.TextMessage;
import com.wechathandle.MessageHandler;

public class ImageMessageHandle implements MessageHandler{

	@Override
	public BaseMessage handleMessage(Map<String, String> requestMap) {
		//return new TextMessage(requestMap, "(5)");
		AipOcr client = new AipOcr("18101156", "NYKGj2DYLWfGtR0XxP00tuEW", "cg98wYUc4tvDxGrZCfnUPMn8Td1qLAsQ");
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("language_type", "CHN_ENG");
	    options.put("detect_direction", "true");
	    options.put("detect_language", "true");
	    options.put("probability", "true");
	    
	    
	    // 参数为本地图片路径
	    //String image = "E:/PS素材/testAI.png";
	    String url = requestMap.get("PicUrl");
	    System.out.println(url);
	    org.json.JSONObject  res = client.basicGeneralUrl(url, options);
	    JSONObject json = JSONObject.parseObject(res.toString());
	    JSONArray jsonArray = json.getJSONArray("words_result");
	    StringBuilder sbBuilder = new StringBuilder();
	    for (int i = 0; i < jsonArray.size(); i++) {
	    	JSONObject jsonObject = jsonArray.getJSONObject(i);
	    	sbBuilder.append(jsonObject.getString("words"));
		}	
	    String string = sbBuilder.toString();
	    System.out.println("AI返回的字符串为"+string);


	    
		return new TextMessage(requestMap, string);
	}

}
