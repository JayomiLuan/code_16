package cn.itcast.day16.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//通过request对象的getSession()方法可以得到Session对象
		HttpSession session = req.getSession();
		
		//在当前Session中保存一个数据，在其它页面中取得这个数据并显示
		session.setAttribute("msg", "大家好，这是保存在Session中的一个字符串。");
		
		//用重定向的方式转到另一个页面
		resp.sendRedirect("/day16/ts1");
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
