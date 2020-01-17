package com.wechat.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:二级菜单 
 * @Author 熊平
 * @Date 2019年12月24日
 */
public class SubButton extends BaseButton{
	//菜单类型向上转型
	private List<BaseButton> sub_button = new ArrayList<BaseButton>();

	public List<BaseButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<BaseButton> sub_button) {
		this.sub_button = sub_button;
	}
	public SubButton(String name) {
		super(name);
	}

}
