package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wechat.model.message.BaseMessage;
import com.wechat.model.message.ImageMessage;
import com.wechat.model.message.MusicMessage;
import com.wechat.model.message.NewsMessage;
import com.wechat.model.message.TextMessage;
import com.wechat.model.message.VideoMessage;
import com.wechat.model.message.VoiceMessage;

public class XmlUtil {
	//从request的输入流获取到字符串
	public static String getStringForRequest(HttpServletRequest request) throws IOException{
		
		ServletInputStream inputStream = request.getInputStream();
		byte[] b = new byte[1024];
		int length;
		StringBuilder sb = new StringBuilder();
		while(( length = inputStream.read(b))!=-1){
			sb.append(new String(b,0,length));
		}
		inputStream.close();
		return sb.toString();
	
	}	
	//从request的输入流获取Map
	@SuppressWarnings("unchecked")
	public static Map<String, String> getMapForRequest(HttpServletRequest request) throws Exception{
		// 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
         // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
       // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
         }
         // 释放资源
        inputStream.close();
        inputStream = null;
        return map; 
	}
	//从对象转化成xml
	public static String getXmlForBean(BaseMessage msg){
		XStream stream = new XStream();
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(MusicMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		String xml = stream.toXML(msg);
		return xml;
	}
	
}
