package com.wechat.model.menu;
//≤Àµ•œÓ∏∏¿‡
public abstract class BaseButton {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseButton(String name) {
		super();
		this.name = name;
	}
	
}
