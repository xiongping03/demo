package com.util;

import java.util.Iterator;
import java.util.Map;

import org.springframework.util.StringUtils;

public class MapUtil {
	 public static String asUrlParams(Map<String, String> source){
	        Iterator<String> it = source.keySet().iterator();
	        StringBuilder paramStr = new StringBuilder();
	        while (it.hasNext()){
	            String key = it.next();
	            if (StringUtils.isEmpty(source.get(key))){
	                continue;
	            }
	            paramStr.append("&").append(key).append("=").append(source.get(key));
	        }
	        // ȥ����һ��&
	        return paramStr.substring(1);
	    }
}
