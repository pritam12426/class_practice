package com.App;

import java.sql.*;

public class Database {
	private static final String URL = "jdbc:sqlite:rentalsystem.db";
	private static Connection conn;

	public static Connection connect() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(URL);
		}
		return conn;
	}

	public static void close() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}
