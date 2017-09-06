package cn.itcast.day16.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC工具类
 * @author Administrator
 *
 */
public class JdbcUtils {
	
	static{
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//取得连接对象
	public static Connection getConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql:///day13","itcast","itcast");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//释放资源
	public static void releaseResource( Statement stmt , Connection conn ){
		releaseResource( null , stmt , conn );
	}
	
	public static void releaseResource( ResultSet rs , Statement stmt , Connection conn ){
		if( rs != null ){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = null;
		
		if( stmt != null ){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stmt = null;
		
		if( conn != null ){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}

}
