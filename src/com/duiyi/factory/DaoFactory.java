package com.duiyi.factory;

import java.io.FileReader;
import java.util.Properties;

import com.duiyi.dao.UserDao;

public class DaoFactory {
	private static DaoFactory factory = new DaoFactory();
	
	private static Properties prop = null;
	
	static {
		prop = new Properties();
		try {
			prop.load(new FileReader(DaoFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static DaoFactory getFactory() {
		return factory;
	}
	
	public UserDao getUserDao() {
		String classname = prop.getProperty("userdao");
		try {
			return (UserDao) Class.forName(classname).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private DaoFactory() {
	}
}
