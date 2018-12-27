package com.source.components;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ReadCookies {
	public static Map<String,Cookie> ReadCookie(HttpServletRequest request){
	//私有静态方法
		Map<String,Cookie> cookieMap =new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie : cookies){
				cookieMap.put(cookie.getName(),cookie);
		    }
		}
		return cookieMap;
		}
	
	
}
