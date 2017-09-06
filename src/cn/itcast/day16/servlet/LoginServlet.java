package cn.itcast.day16.servlet;

import cn.itcast.day16.domain.User;
import cn.itcast.day16.service.LoginService;
import cn.itcast.day16.service.impl.LoginServiceImpl;
import cn.itcast.day16.utils.ServletUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cn.itcast.day16.utils.ServletUtils.CHECK_CODE;

//静态导入指定类中的所有静态常量


/**
 * 进行登录验证的Servlet
 * @author Administrator
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 7166389392041760052L;
	private static final String COUNT = "count";

	//实例化一个业务逻辑的对象
	private LoginService loginService = new LoginServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("进入doGet方法");
		
		//一、得到表单中提交的用户名和密码。
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//取得用户提交的验证码
		String checkCode = req.getParameter("checkCode");

		//二、封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setCheckCode(checkCode);//用户输入的验证码
		
		//得到向浏览器输出内容用的输出字符流。
		resp.setContentType("text/html;charset=UTF-8");//设置响应对象的字符集，解决中文乱码问题
		PrintWriter out = resp.getWriter();

		//三、调用业务逻辑
		boolean flag = false;
		try {
			//从Session中取得之前生成的验证码
			String cc = (String)req.getSession().getAttribute(CHECK_CODE);
			flag = loginService.checkLogin(user , cc );
		} catch (Exception e) {
			e.printStackTrace();
			writePage( "server error" , out );
		}
		
		//四、根据业务逻辑的返回结果，给用户响应信息
		if( flag ){
			//登录成功
			//进行访问量的累加,得到ServletContext对象
			ServletContext context = getServletContext();
			//取得其中保存的访问量的值,取得Map中名字为count的值
			Long count = (Long)context.getAttribute( COUNT );
			
			if( count == null ){
				//如果值不存在，则说明是第一次访问，则设置值为1
				count = 1L;
				context.setAttribute( COUNT , count );
			}else{
				//如果已存在，则count值自增1，然后存回ServletContext中
				count++;
				context.setAttribute( COUNT , count );
			}
			
			//读取Cookie，显示上次登录的时间。
			Cookie[] cookies = req.getCookies();
			String lastLoginTime = ServletUtils.getCookieValue(cookies, "lastLoginTime");
			//在页面输出指定的内容
			writePage("login success. 您是第" + count + "位访客。" + lastLoginTime , out);
			
			//写入Cookie，记录本次登录的日期
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Cookie cookie = new Cookie( "lastLoginTime" , sdf.format( d ) );
			//设置有效期,否则浏览关闭Cookie销毁 
			cookie.setMaxAge( 30*24*60*60 );
			resp.addCookie(cookie);//写入Cookie
			
		}else{
			//登录失败
			writePage("login failed", out);
		}
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("进入doPost方法");
		//把方法处理转到doGet中进行执行，实现的是POST和GET方式提交的表单都用同一段代码来处理
		doGet(req,resp);
	}

	/**
	 * 负责在浏览器中显示一段信息
	 * @param msg 信息内容
	 * @param out 输出流对象
	 */
	private void writePage( String msg , PrintWriter out ){
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\" />");
		out.println("		<title>login infomation</title>");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<font color=\"blue\">" + msg + "</font>");
		out.println("	</body>");
		out.println("</html>");
	}
}
