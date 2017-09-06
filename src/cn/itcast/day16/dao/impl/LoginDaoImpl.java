package cn.itcast.day16.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.itcast.day16.dao.LoginDao;
import cn.itcast.day16.domain.User;
import cn.itcast.day16.utils.JdbcUtils;

/**
 * Dao层的代码只操作数据库，对数据进行处理，但不参与业务（***业务必须在service层处理）
 * @author Administrator
 *
 */
public class LoginDaoImpl implements LoginDao {

	/**
	 * 取得指定用户的信息
	 */
	@Override
	public User get(String username) throws Exception {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			//SQL语句中的字段列表尽量写全，即使是所有，也不要用*。
			stmt = conn.prepareStatement("select username,password from tb_user where username=?");
			//设置参数
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			
			//封装结果集为对象
			if( rs.next() ){
				user = new User();
				user.setUsername( rs.getString("username") );
				user.setPassword( rs.getString("password") );
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseResource(rs, stmt, conn);
		}
		
		return user;
	}

}
