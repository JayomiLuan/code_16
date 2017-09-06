package cn.itcast.day16.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到当前商品的ID
		String pid = req.getParameter("pid");
		int id = Integer.parseInt(pid);
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		//显示当前商品信息
		out.println("<img src=\"/day16/images/cs100"+(id<10?"0"+id:id)+".jpg\"><br/>");
		out.println("<a href=\"/day16/plServlet\">返回商品列表</a>");

		//把当前商品的ID写入Cookie
		//1、取得原有保存浏览记录的Cookie
		Cookie[] cookies = req.getCookies();
		String history = "";
		if( cookies != null ){
			//遍历所有的Cookie
			for( Cookie cookie : cookies ){
				if( "history".equals( cookie.getName() ) ){
					//找到保存浏览历史记录的Cookie
					history = cookie.getValue();
				}
			}
		}

		//2、查看当前访问的商品是否存在于历史记录中（1,2,3,6,）
		if( !history.contains( id+"," ) ){
			//3、如果不存在，则添加到历史记录中
			history += id+",";//把当前商品的ID追加到历史记录字符串的尾部
			resp.addCookie( new Cookie( "history" , history ) );//写入Cookie
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
