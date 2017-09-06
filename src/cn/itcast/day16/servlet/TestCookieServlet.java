package cn.itcast.day16.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//取得Cookie
		Cookie[] cookies = req.getCookies();
		if( cookies != null ){
			for( Cookie cookie : cookies ){
				System.out.println( cookie.getName() + " -> " + cookie.getValue() );
			}
		}
		
		//写入Cookie
		//cookie.setMaxAge(2*7*24*60*60);//Cookie的有效期，如果不设置有效期，则有效期为本次会话（关闭浏览器或，更换浏览器，则消失）
		//cookie.setPath("/aaa/bbb/ccc");//设置Cookie的有效范围
		resp.addCookie( new Cookie( "aaa" , "value1" ) );
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
