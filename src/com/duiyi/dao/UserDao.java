package com.duiyi.dao;

import com.duiyi.domain.User;

public interface UserDao {
	/**
	 * 通过用户名查找用户
	 *
	 * @param username 用户名
	 * @return User 用户
	 */
	User findUserByUsername(String username);

	/**
	 * 添加用户
	 *
	 * @param user 用户
	 */
	void addUser(User user);

	/**
	 * 通过用户名和密码查找用户
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return User 用户
	 */
	User findUserByUsernameAndPassword(String username, String password);
}
