package com.duiyi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.duiyi.domain.User;
import com.duiyi.util.JDBCUtil;

/**
 * PreparedStatement利用预编译的机制将sql语句的主干和参数分别传输给数据库服务器,
 * 从而使数据库分辨的出哪些是sql语句的主干哪些是参数,这样一来即使参数中带了sql的关键字,
 * 数据库服务器也仅仅将他当作参数值使用,关键字不会起作用,从而从原理上防止了sql注入的问题
 * 
 * PreparedStatement主要有如下的三个优点:
 * 		1.可以防止sql注入
 *		2.由于使用了预编译机制,执行的效率要高于Statement
 *		3.sql语句使用?形式替代参数,然后再用方法设置?的值,比起拼接字符串,代码更加优雅
 */
public class SafeMySqlUserDao implements UserDao {
	public void addUser(User user) {
		String sql = "insert into users values (null, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtil.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getNickname());
			statement.setString(4, user.getEmail());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(resultSet, statement, conn);
		}
	}

	public User findUserByUsername(String username) {
		String sql = "select * from users where username=?";
		User user = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtil.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getUserByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	public User findUserByUsernameAndPassword(String username, String password) {
		String sql = "select * from users where username=? and password=?";
		User user = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtil.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getUserByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}

	private User getUserByResultSet(ResultSet resultSet) throws SQLException {
		return new User(resultSet.getInt("id"),
				resultSet.getString("username"),
				resultSet.getString("password"),
				resultSet.getString("nickname"),
				resultSet.getString("email"));
	}
}
