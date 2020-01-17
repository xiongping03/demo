package com.wechat.model.menu;

public class MiniprogramButton extends BaseButton{
	private String type = "miniprogram";//按钮类型为小程序 
	private String url ;//不支持小程序时跳转的地址
	private String appid;//小程序的appid
	private String pagepath;//小程序业务路径
	
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
