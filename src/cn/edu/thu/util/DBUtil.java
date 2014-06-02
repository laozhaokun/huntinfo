package cn.edu.thu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhf E-mail:zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午6:54:07
 */
public class DBUtil {
	public Connection connection;

	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/hunt_info?useUnicode=true&characterEncoding=utf-8", "root", "root");
			connection = DriverManager.getConnection(
					"jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_huntinfo?useUnicode=true&characterEncoding=utf-8", "accesskey", "accesspassword");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int update(String update) {
		int rows = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(update);
			rows = stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public ResultSet query(String query) {
		ResultSet ret = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ret = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
