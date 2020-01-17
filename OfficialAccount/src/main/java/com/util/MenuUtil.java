package com.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.wechat.model.menu.BaseButton;
import com.wechat.model.menu.ClickButton;
import com.wechat.model.menu.Menu;
import com.wechat.model.menu.MiniprogramButton;
import com.wechat.model.menu.PhotoOrAlbumButton;
import com.wechat.model.menu.SubButton;
import com.wechat.model.menu.ViewButton;
import com.wechat.service.impl.WechatServiceImpl;

public class MenuUtil {
	public static void main(String[] args) {
		createMenu();
		
	}
	public static void createMenu(){
		Menu menu = new Menu();
		ArrayList<BaseButton> button = menu.getButton();//һ���˵�
		//���һ��һ������˵�
		button.add(new ClickButton("һ���˵�","1"));
		//���һ����ת�˵�
		button.add(new ViewButton("һ����ת", "http://www.baidu.com"));
		//�����˵�����
		SubButton sbButton = new SubButton("�ж����˵�");
		//�����˵��Ĳ˵��б�
		List<BaseButton> sub_button = sbButton.getSub_button();
		sub_button.add(new PhotoOrAlbumButton("��ͼ", "31"));
		sub_button.add(new ClickButton("���","32"));
		sub_button.add(new MiniprogramButton("С����", "http://www.ilovemdl.cn/OfficialAccount/userManager/getUserInfo", "wx315aa7350d3bad89", "pages/index/index"));
		button.add(sbButton);
		
		//�γ�JSONString
		String menuString = JSONObject.toJSONString(menu);
		
		String createMenuUrl ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+WechatServiceImpl.getAccessToken();
		String doPost = HttpClientUtil.doPost(createMenuUrl, menuString);
		System.out.println(doPost);
	}
}
