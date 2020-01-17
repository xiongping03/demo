package com.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.util.HttpClientUtil;
import com.util.MapUtil;
import com.wechat.model.AccessToken;
import com.wechat.service.IWechatService;
@Service("com.wechat.service.impl.WechatServiceImpl")
public class WechatServiceImpl implements IWechatService{
	private static final String APPID = "wxe213e29b3573b3e0";
	private static final String APPSECRET = "353dec15f1eb9ffab38a1c6ab2bc6df4";
	private static final String GRANT_TYPE = "client_credential";
	private static final String  URL = "https://api.weixin.qq.com/cgi-bin/token?";
	private static AccessToken accessToken;
	private static void getToken(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("grant_type", GRANT_TYPE);
		map.put("appid", APPID);
		map.put("secret", APPSECRET);
		String httpurl =  URL+MapUtil.asUrlParams(map);
		String rspData = HttpClientUtil.doGet(httpurl);  
		JSONObject jsonObject = JSONObject.parseObject(rspData);
		String access_token = jsonObject.getString("access_token");
		String expires_in = jsonObject.getString("expires_in");
		 accessToken = new AccessToken(access_token, expires_in);
		
		
	}
	/**
	 * @Description: ��ȡtoken
	 * @Author ��ƽ
	 * @Date 2019��12��26�� ����7:36:56
	 * @return
	 */
	public static String getAccessToken(){
		if(accessToken==null||accessToken.isExpired()){
			getToken();
		}
		return accessToken.getAccessToken();
	}
	/**
	 * @Description:������ά��ticket 
	 * @Author ��ƽ
	 * @Date 2019��12��26�� ����7:44:51
	 * @return String
	 */
	public String getQrCodeTicket(){
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		url = url.replace("TOKEN", getAccessToken());
		String param = "{\"expire_seconds\": 600, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}";
		String responseData = HttpClientUtil.doPost(url, param);
		String ticket = JSONObject.parseObject(responseData).getString("ticket");	
		return ticket;
		
	}
	/**
	 * @Description: ��ȡ�ѹ�ע�û���Ϣ
	 * @Author ��ƽ
	 * @Date 2019��12��26�� ����7:58:14
	 * @param openid
	 * @return
	 */
	public String getWechatUserInfo(String openid){
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		url= url.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid);
		String doGet = HttpClientUtil.doGet(url);
		return doGet;
	}
	
}
