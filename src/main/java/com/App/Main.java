package com.App;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// Step 1: Connect to the database
			connection = Database.connect();
			System.out.println("Connection established successfully.");

			// Step 2: Perform database operations
			// For example, you could create a statement and execute a query here
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cars");
			while (rs.next()) {
				System.out.println(rs.getString("model"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
					System.out.println("Connection closed successfully.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
