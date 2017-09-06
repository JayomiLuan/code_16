package cn.itcast.day16.service;

import cn.itcast.day16.domain.User;

public interface LoginService {
	//判断此用户是否登录成功
	boolean checkLogin( User user ,String checkCode ) throws Exception;
}
