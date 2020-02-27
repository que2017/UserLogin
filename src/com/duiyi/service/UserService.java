package com.duiyi.service;

import com.duiyi.dao.XmlUserDao;
import com.duiyi.domain.User;
import com.duiyi.exception.MsgException;

public class UserService {
	private XmlUserDao dao = new XmlUserDao();
	
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
}
