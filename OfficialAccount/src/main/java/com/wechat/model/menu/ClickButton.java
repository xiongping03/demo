package com.wechat.model.menu;

/**
 * @Description: 点击菜单 
 * @Author 熊平
 * @Date 2019年12月24日
 */
public class ClickButton extends BaseButton{
	private String type = "click";//click 菜单
	private String key ;//click 菜单的key
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ClickButton(String name,String key) {
		super(name);
		// TODO Auto-generated constructor stub
		this.key = key; 
	}
	
}
