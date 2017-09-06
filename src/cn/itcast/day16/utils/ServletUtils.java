package cn.itcast.day16.utils;

import javax.servlet.http.Cookie;

public class ServletUtils {
	//在Session中保存验证码时使用的名字
	public static final String CHECK_CODE = "checkCode";
	
	//用于取得指定名称的Cookie的值的工具方法
	public static String getCookieValue( Cookie[] cookies , String name ){
		String value = "";
		
		if( cookies != null ){
			for( Cookie cookie : cookies ){
				if( name.equals( cookie.getName() ) ){
					value = cookie.getValue();
				}
			}
		}
		
		return value;
	}
}
