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
		ArrayList<BaseButton> button = menu.getButton();//一级菜单
		//添加一个一级点击菜单
		button.add(new ClickButton("一级菜单","1"));
		//添加一级跳转菜单
		button.add(new ViewButton("一级跳转", "http://www.baidu.com"));
		//二级菜单对象
		SubButton sbButton = new SubButton("有二级菜单");
		//二级菜单的菜单列表
		List<BaseButton> sub_button = sbButton.getSub_button();
		sub_button.add(new PhotoOrAlbumButton("传图", "31"));
		sub_button.add(new ClickButton("点击","32"));
		sub_button.add(new MiniprogramButton("小程序", "http://www.ilovemdl.cn/OfficialAccount/userManager/getUserInfo", "wx315aa7350d3bad89", "pages/index/index"));
		button.add(sbButton);
		
		//形成JSONString
		String menuString = JSONObject.toJSONString(menu);
		
		String createMenuUrl ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+WechatServiceImpl.getAccessToken();
		String doPost = HttpClientUtil.doPost(createMenuUrl, menuString);
		System.out.println(doPost);
	}
}
