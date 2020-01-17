package com.wechat.model.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//文本消息接收对象
@XStreamAlias("xml")
public class TextMessage extends BaseMessage{
	@XStreamAlias("Content")
	private String content;

	

	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public TextMessage(Map<String, String> requestMap,String content) {
		super(requestMap);
		// TODO Auto-generated constructor stub
		this.setMsgType("text");
		this.content =content ;
	}
	

}
