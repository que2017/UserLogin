package com.duiyi.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import com.duiyi.exception.MsgException;
import com.duiyi.util.StringUtil;

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
	
	public User(Map<String, String[]> map) {
		this.username = map.get("username")[0];
		this.password = map.get("password")[0];
		this.passwordAgain = map.get("passwordAgain")[0];
		this.nickname = map.get("nickname")[0];
		this.email = map.get("email")[0];
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
	
	/**
	 * ��������Ƿ�Ϸ�
	 */
	public void checkValue() throws MsgException {
		if (StringUtil.isEmpty(username)) {
			throw new MsgException("�û�������Ϊ�գ�");
		}
		if (StringUtil.isEmpty(password)) {
			throw new MsgException("���벻��Ϊ�գ�");
		}
		if (StringUtil.isEmpty(passwordAgain)) {
			throw new MsgException("ȷ�����벻��Ϊ�գ�");
		}
		if (!password.equals(passwordAgain)) {
			throw new MsgException("�������벻һ�£�");
		}
		if (StringUtil.isEmpty(nickname)) {
			throw new MsgException("�ǳƲ���Ϊ�գ�");
		}
		if (StringUtil.isEmpty(email)) {
			throw new MsgException("���䲻��Ϊ�գ�");
		}
		if (!email.matches("^\\w+@\\w+(\\.\\w+)+$")) {
			throw new MsgException("�����ʽ����ȷ��");
		}
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
