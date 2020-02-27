package com.duiyi.domain;

import java.io.Serializable;

public class User implements Serializable {
	private String username;
	
	private String password;
	
	private String passwordAgain;
	
	private String nickname;
	
	private String email;
	
	public User() {
		this(null, null, null, null, null);
	}

	public User(String username, String password, String passwordAgain, String nickname, String email) {
		this.username = username;
		this.password = password;
		this.passwordAgain = passwordAgain;
		this.nickname = nickname;
		this.email = email;
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

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "username:" + username
			+ "\npassword:" + password
			+ "\npasswordAgain:" + passwordAgain
			+ "\nnickname:" + nickname
			+ "\nemail:" + email;
	}
}
