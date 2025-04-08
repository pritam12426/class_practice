package com.App;

import java.sql.*;

public class Payment {
	private int paymentId;
	private int reservationId;
	private Date paymentDate;
	private double amount;
	private String paymentMethod;
	private String paymentStatus;

	public Payment(int paymentId,
	    int reservationId,
	    Date paymentDate,
	    double amount,
	    String paymentMethod,
	    String paymentStatus) {
		this.paymentId     = paymentId;
		this.reservationId = reservationId;
		this.paymentDate   = paymentDate;
		this.amount        = amount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
	}

	public void processPayment(Connection conn) throws SQLException {
		String query = "UPDATE payments SET payment_status = 'Completed' WHERE payment_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, paymentId);
			stmt.executeUpdate();
		}
	}

	public void refundPayment(Connection conn) throws SQLException {
		String query = "UPDATE payments SET payment_status = 'Refunded' WHERE payment_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, paymentId);
			stmt.executeUpdate();
		}
	}
}
