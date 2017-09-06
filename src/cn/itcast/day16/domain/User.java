package cn.itcast.day16.domain;

/*
 * 用于封装用户对象的类
 */
public class User {
	private String username;
	private String password;
	private String checkCode;

	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
