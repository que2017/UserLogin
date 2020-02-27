package com.duiyi.service;

import com.duiyi.dao.XmlUserDao;
import com.duiyi.domain.User;
import com.duiyi.exception.MsgException;

public class UserService {
	private XmlUserDao dao = new XmlUserDao();
	
	/**
	 * 注册用户
	 *
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException {
		// 1.检查用户名是否已经存在，如果已经存在则抛出异常
		if (dao.findUserByUsername(user.getUsername()) != null) {
			throw new MsgException("用户名已经存在");
		}
		// 2.如果用户名不存在，则调用dao中的方法添加用户名
		dao.addUser(user);
	}
}
