package com.duiyi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.duiyi.domain.User;
import com.duiyi.util.JDBCUtil;

public class MysqlUserDao implements UserDao {

	public void addUser(User user) {
		String sql = "insert into users values (null, '"
			+ user.getUsername() + "', '"
			+ user.getPassword() + "', '"
			+ user.getNickname() + "', '"
			+ user.getEmail() +"')";
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			// �������ݿ�
			conn = JDBCUtil.getConnection();
			// ���������ݿⷢ��sql��statement����
			statement = conn.createStatement();
			// ���ô�����ִ��sql����ȡ�����
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(resultSet, statement, conn);
		}
	}

	public User findUserByUsername(String username) {
		String sql = "select * from users where username='" + username +"'";
		return findUserBySql(sql);
	}

	public User findUserByUsernameAndPassword(String username, String password) {
		String sql = "select * from users where username='" + username + "'and password='" + password + "'";
		return findUserBySql(sql);
	}

	private User findUserBySql(String sql) {
		User user = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtil.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"),
								resultSet.getString("username"),
								resultSet.getString("password"),
								resultSet.getString("nickname"),
								resultSet.getString("email"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(resultSet, statement, conn);
		}
		return user;
	}
}
