package com.duiyi.dao;

import com.duiyi.domain.User;

public interface UserDao {
	/**
	 * ͨ���û��������û�
	 *
	 * @param username �û���
	 * @return User �û�
	 */
	User findUserByUsername(String username);

	/**
	 * ����û�
	 *
	 * @param user �û�
	 */
	void addUser(User user);

	/**
	 * ͨ���û�������������û�
	 *
	 * @param username �û���
	 * @param password ����
	 * @return User �û�
	 */
	User findUserByUsernameAndPassword(String username, String password);
}
