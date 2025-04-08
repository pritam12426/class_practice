package com.App;

import java.sql.*;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String driverLicenseNumber;

	public Customer(int customerId,
	    String firstName,
	    String lastName,
	    String email,
	    String phoneNumber,
	    String address,
	    String driverLicenseNumber) {
		this.customerId          = customerId;
		this.firstName           = firstName;
		this.lastName            = lastName;
		this.email               = email;
		this.phoneNumber         = phoneNumber;
		this.address             = address;
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public void reserveCar(Connection conn, int carId) throws SQLException {
		String query = "INSERT INTO reservations (car_id, customer_id, start_date, end_date, "
		               + "total_price, reservation_status) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, carId);
			stmt.setInt(2, this.customerId);
			stmt.setDate(3, Date.valueOf("2025-02-10"));
			stmt.setDate(4, Date.valueOf("2025-02-12"));
			stmt.setDouble(5, 100.0);
			stmt.setString(6, "Confirmed");
			stmt.executeUpdate();
		}
	}

	public void cancelReservation(Connection conn, int reservationId) throws SQLException {
		String query = "DELETE FROM reservations WHERE reservation_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, reservationId);
			stmt.executeUpdate();
		}
	}
}
