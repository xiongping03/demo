package com.wechat.model.menu;

/**
 * @Description: ����˵� 
 * @Author ��ƽ
 * @Date 2019��12��24��
 */
public class ClickButton extends BaseButton{
	private String type = "click";//click �˵�
	private String key ;//click �˵���key
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
