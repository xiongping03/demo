package com.wechathandle.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wechat.model.message.Article;
import com.wechat.model.message.BaseMessage;
import com.wechat.model.message.NewsMessage;
import com.wechathandle.MessageHandler;

public class ClickMessageHandle implements MessageHandler{

	@Override
	public BaseMessage handleMessage(Map<String, String> requestMap) {
		String eventKey = requestMap.get("EventKey");
		BaseMessage baseMessage = null;
		switch (eventKey) {
		case "1": 
			List<Article> articles = new ArrayList<Article>();
			Article article = new Article("张佳琪逗逼照片", "这是一个图文消息", "http://mmbiz.qpic.cn/mmbiz_jpg/bYagDfZKwrF3I0bJcUjf5JVbGBeqeqXEfsFATCKvyXmyib9TOOhCUTQlY7BKXrjK3L0P9Tmj7qsqHCA5ZQOwOEg/0", "www.baidu.com");
			articles.add(article);
			baseMessage = new NewsMessage(requestMap, articles);
			break;

		default:
			break;
		}
		return baseMessage;
	}

}
