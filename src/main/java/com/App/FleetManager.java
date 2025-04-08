package com.App;

import java.sql.*;

public class FleetManager {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String role;

	public FleetManager(int employeeId, String firstName, String lastName, String role) {
		this.employeeId = employeeId;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.role       = role;
	}

	public void addCarToFleet(Connection conn, Car car) throws SQLException {
		Car.addCarToDatabase(conn, car);
	}

	public void removeCarFromFleet(Connection conn, int carId) throws SQLException {
		String query = "DELETE FROM cars WHERE car_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, carId);
			stmt.executeUpdate();
		}
	}
}
