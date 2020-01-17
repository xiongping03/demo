package com.wechat.model.menu;

public class MiniprogramButton extends BaseButton{
	private String type = "miniprogram";//��ť����ΪС���� 
	private String url ;//��֧��С����ʱ��ת�ĵ�ַ
	private String appid;//С�����appid
	private String pagepath;//С����ҵ��·��
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

	public MiniprogramButton(String name,  String url,
			String appid, String pagepath) {
		super(name);
		this.url = url;
		this.appid = appid;
		this.pagepath = pagepath;
	}

	

}
