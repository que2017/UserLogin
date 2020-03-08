package com.duiyi.service;

import com.duiyi.dao.UserDao;
import com.duiyi.domain.User;
import com.duiyi.exception.MsgException;
import com.duiyi.factory.DaoFactory;

public class UserService {
	private UserDao dao = DaoFactory.getFactory().getUserDao();
	
	/**
	 * ע���û�
	 *
	 * @param user
	 * @throws MsgException 
	 */
	public void registUser(User user) throws MsgException {
		// 1.����û����Ƿ��Ѿ����ڣ�����Ѿ��������׳��쳣
		if (dao.findUserByUsername(user.getUsername()) != null) {
			throw new MsgException("�û����Ѿ�����");
		}
		// 2.����û��������ڣ������dao�еķ�������û���
		dao.addUser(user);
	}
	
	/**
	 * ����û��������Ƿ���ȷ
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public User isUserCorrect(String username, String password) {
		return dao.findUserByUsernameAndPassword(username, password);
	}
}
