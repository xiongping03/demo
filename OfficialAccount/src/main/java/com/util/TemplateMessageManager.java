package com.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.wechat.service.impl.WechatServiceImpl;

public class TemplateMessageManager {
	/**
	 * @Description: ������ҵ��Ϣ
	 * @Author ��ƽ
	 * @Date 2019��12��25�� ����7:24:22
	 */
	@Test
	public void setIndustryMessage(){
		String accessToken = WechatServiceImpl.getAccessToken();
		String  url= "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+accessToken;
		Map<String, String> map = new HashMap<String, String>();
		map.put("industry_id1", "1");
		map.put("industry_id2", "4");
		//�γ�JSONString
		String param = JSONObject.toJSONString(map);
		System.out.println(param);
		String doPost = HttpClientUtil.doPost(url, param);
		System.out.println(doPost);
	}
	
	/**
	 * @Description: ��ȡ��ҵ��Ϣ
	 * @Author ��ƽ
	 * @Date 2019��12��25�� ����7:21:39
	 */
	@Test
	public  void getIndustryMessage(){
		
		String url ="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+WechatServiceImpl.getAccessToken();
		String doGet = HttpClientUtil.doGet(url);
		System.out.println(doGet);
	}
	/**����ģ����Ϣ
	 * @Description: 
	 * @Author ��ƽ
	 * @Date 2019��12��25�� ����7:51:39
	 */
	@Test
	public  void sendTemplateMessage(){
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+WechatServiceImpl.getAccessToken();
		String param = "{\r\n" + 
				"           \"touser\":\"oIy6zwXd2UFE5sT6SEvfRWab8_KU\",\r\n" + 
				"           \"template_id\":\"f_Qq6rufa2tNLFMvOqOTrizcyFpmsruoEib84F1C5_Y\",          \r\n" + 
				"           \"data\":{\r\n" + 
				"                   \"first\": {\r\n" + 
				"                       \"value\":\"�˼������ˣ�\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"company\":{\r\n" + 
				"                       \"value\":\"���������˼���\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"time\": {\r\n" + 
				"                       \"value\":\"2019��12��25��\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"result\": {\r\n" + 
				"                       \"value\":\"2019��12��25��\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   },\r\n" + 
				"                   \"remark\":{\r\n" + 
				"                       \"value\":\"������������죡\",\r\n" + 
				"                       \"color\":\"#173177\"\r\n" + 
				"                   }\r\n" + 
				"           }\r\n" + 
				"       }";
		String doPost = HttpClientUtil.doPost(url, param);
		System.out.println(doPost);
	}
}
