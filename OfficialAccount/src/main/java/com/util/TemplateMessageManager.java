package com.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.wechat.service.impl.WechatServiceImpl;

public class TemplateMessageManager {
	/**
	 * @Description: 设置行业信息
	 * @Author 熊平
	 * @Date 2019年12月25日 下午7:24:22
	 */
	@Test
	public void setIndustryMessage(){
		String accessToken = WechatServiceImpl.getAccessToken();
		String  url= "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+accessToken;
		Map<String, String> map = new HashMap<String, String>();
		map.put("industry_id1", "1");
		map.put("industry_id2", "4");
		//形成JSONString
		String param = JSONObject.toJSONString(map);
		System.out.println(param);
		String doPost = HttpClientUtil.doPost(url, param);
		System.out.println(doPost);
	}
	
	/**
	 * @Description: 获取行业信息
	 * @Author 熊平
	 * @Date 2019年12月25日 下午7:21:39
	 */
	@Test
	public  void getIndustryMessage(){
		
		String url ="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+WechatServiceImpl.getAccessToken();
		String doGet = HttpClientUtil.doGet(url);
		System.out.println(doGet);
	}
	/**发送模板消息
	 * @Description: 
	 * @Author 熊平
	 * @Date 2019年12月25日 下午7:51:39
	 */
	@Test
	public  void sendTemplateMessage(){
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WechatServiceImpl.getAccessToken();
		String param = "{\r\n" + 
				"           \"touser\":\"oIy6zwXd2UFE5sT6SEvfRWab8_KU\",\r\n" + 
				"           \"template_id\":\"f_Qq6rufa2tNLFMvOqOTrizcyFpmsruoEib84F1C5_Y\",          \r\n" + 
				"           \"data\":{\r\n" + 
				"                   \"first\": {\r\n" + 
				"                       \"value\":\"人家想你了！\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"company\":{\r\n" + 
				"                       \"value\":\"都不来找人家玩\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"time\": {\r\n" + 
				"                       \"value\":\"2019年12月25日\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"result\": {\r\n" + 
				"                       \"value\":\"2019年12月25日\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"remark\":{\r\n" + 
				"                       \"value\":\"快点来找我聊天！\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   }\r\n" + 
				"           }\r\n" + 
				"       }";
		String doPost = HttpClientUtil.doPost(url, param);
		System.out.println(doPost);
	}
}
