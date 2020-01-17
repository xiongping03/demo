package com.wechat.model.menu;

import java.util.ArrayList;

public class Menu {
	private ArrayList<BaseButton> button = new ArrayList<BaseButton>();

	public ArrayList<BaseButton> getButton() {
		return button;
	}

	public void setButton(ArrayList<BaseButton> button) {
		this.button = button;
	}
	
}
