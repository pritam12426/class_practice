package com.App;

import java.sql.*;

public class Car {
	private int carId;
	private String make;
	private String model;
	private int year;
	private String color;
	private String licensePlate;
	private double pricePerDay;
	private boolean availabilityStatus;
	private double mileage;

	public Car(int carId,
	    String make,
	    String model,
	    int year,
	    String color,
	    String licensePlate,
	    double pricePerDay,
	    boolean availabilityStatus,
	    double mileage) {
		this.carId              = carId;
		this.make               = make;
		this.model              = model;
		this.year               = year;
		this.color              = color;
		this.licensePlate       = licensePlate;
		this.pricePerDay        = pricePerDay;
		this.availabilityStatus = availabilityStatus;
		this.mileage            = mileage;
	}

	public boolean isAvailable() {
		// TODO: Write the function to fetch the isAvailable date the the database
		return availabilityStatus;
	}

	public void updateAvailability(boolean status) {
		// TODO: Write the function which alter the the data in database for Availability of the car
		this.availabilityStatus = status;
	}

	public double getPrice() {
		// TODO: Write the function to fetch the price of the car from database
		return pricePerDay;
	}

	public static void addCarToDatabase(Connection conn, Car car) throws SQLException {
		String query =
		    "INSERT INTO cars (car_id, make, model, year, color, license_plate, price_per_day, "
		    + "availability_status, mileage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, car.carId);
			stmt.setString(2, car.make);
			stmt.setString(3, car.model);
			stmt.setInt(4, car.year);
			stmt.setString(5, car.color);
			stmt.setString(6, car.licensePlate);
			stmt.setDouble(7, car.pricePerDay);
			stmt.setBoolean(8, car.availabilityStatus);
			stmt.setDouble(9, car.mileage);
			stmt.executeUpdate();
		}
	}
}
