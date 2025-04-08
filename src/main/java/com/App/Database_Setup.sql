CREATE TABLE IF NOT EXISTS cars (
	car_id INTEGER PRIMARY KEY AUTOINCREMENT,
	make TEXT,
	model TEXT,
	year INTEGER,
	color TEXT,
	license_plate TEXT,
	price_per_day REAL,
	availability_status BOOLEAN,
	mileage REAL
);

CREATE TABLE IF NOT EXISTS customers (
	customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
	first_name TEXT,
	last_name TEXT,
	email TEXT,
	phone_number TEXT,
	address TEXT,
	driver_license_number TEXT
);

CREATE TABLE IF NOT EXISTS reservations (
	reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,
	car_id INTEGER,
	customer_id INTEGER,
	start_date DATE,
	end_date DATE,
	total_price REAL,
	reservation_status TEXT,
	FOREIGN KEY (car_id) REFERENCES cars(car_id),
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS payments (
	payment_id INTEGER PRIMARY KEY AUTOINCREMENT,
	reservation_id INTEGER,
	payment_date DATE,
	amount REAL,
	payment_method TEXT,
	payment_status TEXT,
	FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

INSERT INTO cars (car_id, make, model, year, color, license_plate, price_per_day, availability_status, mileage) VALUES
	(1, 'Toyota', 'Camry', 2020, 'White', 'MH12AB1234', 50.0, TRUE, 15000.0),
	(2, 'Honda', 'Civic', 2019, 'Black', 'DL8CAF5678', 45.0, TRUE, 20000.0),
	(3, 'Maruti', 'Swift', 2021, 'Red', 'KA03CD9101', 30.0, TRUE, 10000.0),
	(4, 'Hyundai', 'Creta', 2018, 'Blue', 'TN10EF2345', 55.0, TRUE, 25000.0),
	(5, 'Ford', 'EcoSport', 2022, 'Silver', 'GJ01GH6789', 60.0, TRUE, 5000.0);


INSERT INTO customers (customer_id, first_name, last_name, email, phone_number, address, driver_license_number) VALUES
	(1, 'Amit', 'Sharma', 'amit.sharma@gmail.com', '9876543210', '123 MG Road, Mumbai, Maharashtra', 'DL1234567890'),
	(2, 'Priya', 'Rao', 'priya.rao@gmail.com', '8765432109', '456 Brigade Road, Bangalore, Karnataka', 'KA0987654321'),
	(3, 'Rahul', 'Patel', 'rahul.patel@gmail.com', '7654321098', '789 Anna Salai, Chennai, Tamil Nadu', 'TN1122334455'),
	(4, 'Sneha', 'Gupta', 'sneha.gupta@gmail.com', '6543210987', '1011 Park Street, Kolkata, West Bengal', 'WB2233445566'),
	(5, 'Vikram', 'Singh', 'vikram.singh@gmail.com', '5432109876', '1213 Connaught Place, New Delhi, Delhi', 'DL3344556677');

INSERT INTO reservations (reservation_id, car_id, customer_id, start_date, end_date, total_price, reservation_status) VALUES
	(1, 1, 1, '2023-10-01', '2023-10-05', 200.0, 'Confirmed'),
	(2, 2, 2, '2023-10-10', '2023-10-15', 225.0, 'Confirmed'),
	(3, 3, 3, '2023-10-20', '2023-10-25', 150.0, 'Pending'),
	(4, 4, 4, '2023-11-01', '2023-11-05', 275.0, 'Confirmed'),
	(5, 5, 5, '2023-11-10', '2023-11-15', 300.0, 'Cancelled');

INSERT INTO payments (payment_id, reservation_id, payment_date, amount, payment_method, payment_status) VALUES
	(1, 1, '2023-10-01', 200.0, 'Credit Card', 'Completed'),
	(2, 2, '2023-10-10', 225.0, 'Debit Card', 'Completed'),
	(3, 3, '2023-10-20', 150.0, 'Net Banking', 'Pending'),
	(4, 4, '2023-11-01', 275.0, 'UPI', 'Completed'),
	(5, 5, '2023-11-10', 300.0, 'Cash', 'Refunded');
