package com.wechathandle;

import java.util.Map;

import com.wechat.model.message.BaseMessage;

public interface MessageHandler {
	BaseMessage handleMessage(Map<String, String> requestMap);
}
