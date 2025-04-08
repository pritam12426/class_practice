package com.App;

import java.sql.*;

public class Reservation {
	private int reservationId;
	private int carId;
	private int customerId;
	private Date startDate;
	private Date endDate;
	private double totalPrice;
	private String reservationStatus;

	public Reservation(int reservationId,
	    int carId,
	    int customerId,
	    Date startDate,
	    Date endDate,
	    double totalPrice,
	    String reservationStatus) {
		this.reservationId     = reservationId;
		this.carId             = carId;
		this.customerId        = customerId;
		this.startDate         = startDate;
		this.endDate           = endDate;
		this.totalPrice        = totalPrice;
		this.reservationStatus = reservationStatus;
	}

	public void confirmReservation() {
		this.reservationStatus = "Confirmed";
	}

	public void cancelReservation() {
		this.reservationStatus = "Cancelled";
	}

	public double calculateTotalPrice(int days, double pricePerDay) {
		return days * pricePerDay;
	}
}
