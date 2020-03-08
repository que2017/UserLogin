package com.duiyi.dao;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.duiyi.domain.User;
import com.duiyi.util.XmlDaoUtil;

public class XmlUserDao implements UserDao {
	
	public User findUserByUsername(String username) {
		Document dom = XmlDaoUtil.getDom();
		if (dom == null) {
			return null;
		}
		Element root = dom.getRootElement();
		// 利用XPATH表达式查找具有username属性值等于传入的用户名的元素
		List<Element> list = root.selectNodes("//user[@username='" + username + "']");
		if (list.size() > 0) {
			return buildUserByElement(list.get(0));
		} else {
			return null;
		}
	}
	
	public void addUser(User user) {
		Document dom = XmlDaoUtil.getDom();
		if (dom == null) {
			return;
		}
		Element root = dom.getRootElement();
		// 创建出一个<user>元素，将传入的user信息设置到这个标签中
		Element userElement = DocumentHelper.createElement("user");
		userElement.setAttributeValue("username", user.getUsername());
		userElement.setAttributeValue("password", user.getPassword());
		userElement.setAttributeValue("nickname", user.getNickname());
		userElement.setAttributeValue("email", user.getEmail());
		// 把设置好的<user>元素挂在到<users>中
		root.add(userElement);
		// 回写到xml文件中
		XmlDaoUtil.refreshXml();
	}

	public User findUserByUsernameAndPassword(String username, String password) {
		Document dom = XmlDaoUtil.getDom();
		if (dom == null) {
			return null;
		}
		Element root = dom.getRootElement();
		// 利用XPATH表达式查找具有username和password属性值等于传入的用户名的元素
		List<Element> list = root.selectNodes("//user[@username='" + username + "' and @password='" + password + "']");
		if (list.size() > 0) {
			return buildUserByElement(list.get(0));
		} else {
			return null;
		}
	}
	
	private User buildUserByElement(Element userElement) {
		User user = new User();
		user.setUsername(userElement.attributeValue("username"));
		user.setPassword(userElement.attributeValue("password"));
		user.setNickname(userElement.attributeValue("nickname"));
		user.setEmail(userElement.attributeValue("email"));
		return user;
	}
}
