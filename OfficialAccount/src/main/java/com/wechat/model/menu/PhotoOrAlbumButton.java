package com.wechat.model.menu;

import java.util.ArrayList;
import java.util.List;

public class PhotoOrAlbumButton extends BaseButton{
	public PhotoOrAlbumButton(String name,String key) {
		super(name);
		this.key = key;
		// TODO Auto-generated constructor stub
	}
	private String type = "pic_photo_or_album";//click 菜单
	private String key ;//click 菜单的key
	//菜单类型向上转型
	private List<BaseButton> sub_button = new ArrayList<BaseButton>();
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
	public List<BaseButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<BaseButton> sub_button) {
		this.sub_button = sub_button;
	}
	
} 
