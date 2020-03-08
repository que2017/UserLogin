package com.duiyi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.duiyi.domain.User;
import com.duiyi.util.JDBCUtil;

/**
 * PreparedStatement����Ԥ����Ļ��ƽ�sql�������ɺͲ����ֱ�������ݿ������,
 * �Ӷ�ʹ���ݿ�ֱ�ĳ���Щ��sql����������Щ�ǲ���,����һ����ʹ�����д���sql�Ĺؼ���,
 * ���ݿ������Ҳ����������������ֵʹ��,�ؼ��ֲ���������,�Ӷ���ԭ���Ϸ�ֹ��sqlע�������
 * 
 * PreparedStatement��Ҫ�����µ������ŵ�:
 * 		1.���Է�ֹsqlע��
 *		2.����ʹ����Ԥ�������,ִ�е�Ч��Ҫ����Statement
 *		3.sql���ʹ��?��ʽ�������,Ȼ�����÷�������?��ֵ,����ƴ���ַ���,�����������
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
