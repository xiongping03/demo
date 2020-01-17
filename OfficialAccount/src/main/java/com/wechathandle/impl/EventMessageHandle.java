package com.wechathandle.impl;

import java.util.Map;

import com.wechat.model.message.BaseMessage;
import com.wechathandle.MessageHandler;

public class EventMessageHandle implements MessageHandler{

	@Override
	public BaseMessage handleMessage(Map<String, String> requestMap) {
		String eventType = requestMap.get("Event");//事件类型
		MessageHandler messageHandler = null ;
    	switch (eventType) {
		case "CLICK":		
			messageHandler = new ClickMessageHandle();
			break;
		case "VIEW":		
			
			break;
		
		default:
			break;
		}
		
		
    	BaseMessage baseMessage = messageHandler.handleMessage(requestMap);
    	return baseMessage;
	}

}
