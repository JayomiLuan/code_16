package cn.itcast.day16.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于显示所有商品列表的页面，显示浏览历史记录（显示Cookie中记录的浏览历史）
 * @author Administrator
 *
 */
public class ProductListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//显示商品列表
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\" />");
		out.println("		<title>商品列表</title>");
		out.println("		<style>");
		out.println("			td>font { color:red; }");
		out.println("			td img { width:200px; height:200px;}");
		out.println("		</style>");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<table>");
		out.println("			<tr>");
		
		for( int i = 1 ; i <= 10 ; i++ ){
			out.println("				<td>");
			out.println("					<a href=\"/day16/pServlet?pid="+i+"\"><img src=\"/day16/images/cs100"+(i<10?"0"+i:i)+".jpg\"></a><br/>");
			out.println("					名称"+i+"<br/>");
			out.println("					<font>￥299</font>");
			out.println("				</td>");
			if( i == 5 ){
				out.println("</tr><tr>");
			}
		}
		
		out.println("			</tr>");
		out.println("		</table>");

		
		//显示Cookie中的历史记录
		//从request中取得所有Cookie
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
		
		out.println("<div>");
		out.println("<h2>浏览历史记录：</h2>");
		out.println(  );//在DIV中显示浏览过的历史记录

		//当Cookie中的内容不为空字符串时可以进行解析
		if( !"".equals( history ) ){
			String[] s = history.split(",");//以逗号分隔得到浏览过的商品的ID的数组
			System.out.println( "====" + history );
			for( String id : s ){
				int t = Integer.parseInt(id);
				out.println("<div style=\"float:left;width:100px; height:110px; font-size:10px;\">");
				out.println("<img src=\"/day16/images/cs100"+(t<10?"0"+t:t)+".jpg\" width=\"100\" height=\"100\" ><br/>"+t);
				out.println("</div>");
			}
			out.println("</div>");
		}
		
		out.println("	</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
