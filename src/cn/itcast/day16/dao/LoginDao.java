package cn.itcast.day16.dao;

import cn.itcast.day16.domain.User;

/**
 * LoginDao接口，规定此类要实现的方法
 * @author Administrator
 *
 */
public interface LoginDao {
	//根据用户名取得用户信息
	User get(String username) throws Exception;
}
