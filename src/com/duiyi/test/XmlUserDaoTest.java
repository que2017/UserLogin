package com.duiyi.test;

import org.junit.Test;

import com.duiyi.dao.XmlUserDao;
import com.duiyi.domain.User;

public class XmlUserDaoTest {
	@Test
	public void testFindUserByUsername() {
		XmlUserDao dao = new XmlUserDao();
		User user = dao.findUserByUsername("admin");
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByUserNameAndPassword() {
		XmlUserDao dao = new XmlUserDao();
		User user = dao.findUserByUsernameAndPassword("admin", "admin");
		System.out.println(user);
	}
	
	@Test
	public void testAddUser() {
		XmlUserDao dao = new XmlUserDao();
		User user = new User("duiyi", "123", "", "duiyizi", "duiyi@qq.com");
		dao.addUser(user);
	}
}
