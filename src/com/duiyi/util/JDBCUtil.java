package com.duiyi.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	private static Properties prop = null;
	
	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(JDBCUtil.class.getClassLoader().getResource("config.properties").getPath()));
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡ���ݿ�����
	 *
	 * @return ���ݿ����Ӷ���
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// ע�����ݿ�����
		Class.forName(prop.getProperty("driver"));
		// ��ȡ����
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
	}
	
	/**
	 * �ر���Դ
	 *
	 * @param resultSet
	 * @param statement
	 * @param conn
	 */
	public static void close(ResultSet resultSet, Statement statement, Connection conn) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resultSet = null;
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	private JDBCUtil() {
	}
}
