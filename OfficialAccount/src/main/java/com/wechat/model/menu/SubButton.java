package com.wechat.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:�����˵� 
 * @Author ��ƽ
 * @Date 2019��12��24��
 */
public class SubButton extends BaseButton{
	//�˵���������ת��
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
