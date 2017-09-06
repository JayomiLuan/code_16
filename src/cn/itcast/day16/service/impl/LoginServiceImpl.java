package cn.itcast.day16.service.impl;

import cn.itcast.day16.dao.LoginDao;
import cn.itcast.day16.dao.impl.LoginDaoImpl;
import cn.itcast.day16.domain.User;
import cn.itcast.day16.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao = new LoginDaoImpl();
	
	/**
	 * 用传入的用户对象的用户名查找数据库，
	 * 用传入的密码和数据库中保存的密码进行比对，如果相同，则登录成功返回true
	 * 否则返回false
	 */
	@Override
	public boolean checkLogin(User user , String checkCode ) throws Exception {
		boolean flag = false;

		//先对验证码进行比对，如果不同，则不需要做数据库操作，直接返回false，登录失败
		if( !checkCode.equals( user.getCheckCode() ) ){
			return false;
		}
		
		//使用用户名（username）去数据库取得信息
		User u = loginDao.get( user.getUsername() );
		
		//用传入的密码和数据库中的密码进行比较
		if( u != null ){
			if( u.getPassword().equals( user.getPassword() ) ){
				flag = true;//用户存在且密码样同，赋值为true
			}
		}
		
		return flag;
	}

}
