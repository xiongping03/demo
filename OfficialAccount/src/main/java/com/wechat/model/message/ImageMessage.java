package com.wechat.model.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
//ͼƬ
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{
	@XStreamAlias("MediaId")
	private String mediaId;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public ImageMessage(Map<String, String> requestMap,String mediaId) {
		super(requestMap);
		// TODO Auto-generated constructor stub
		this.setMsgType("image");
		this.mediaId = mediaId;
	}
	

}
