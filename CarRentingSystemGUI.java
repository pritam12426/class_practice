import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Car {
	private int id;
	private String brand;
	private String model;
	private boolean isRented;
	private double rentalPricePerDay;
	private int rentalDays;
	private int totalRentalCount;

	public Car(int id, String brand, String model, double rentalPricePerDay) {
		this.id                = id;
		this.brand             = brand;
		this.model             = model;
		this.rentalPricePerDay = rentalPricePerDay;
		this.isRented          = false;
		this.rentalDays        = 0;
		this.totalRentalCount  = 0;
	}

	public int getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public boolean isRented() {
		return isRented;
	}
	public double getRentalPricePerDay() {
		return rentalPricePerDay;
	}
	public int getRentalDays() {
		return rentalDays;
	}
	public int getTotalRentalCount() {
		return totalRentalCount;
	}

	public void rent(int days) {
		isRented   = true;
		rentalDays = days;
		totalRentalCount++;
	}

	public void returnCar() {
		isRented   = false;
		rentalDays = 0;
	}

	@Override
	public String toString() {
		return "Car ID: " + id + ", " + brand + " " + model + ", ₹" + rentalPricePerDay
		    + (isRented ? " [RENTED for " + rentalDays + " days]" : " [AVAILABLE]")
		    + ", Rentals: " + totalRentalCount;
	}
}

public class CarRentingSystemGUI {
	private ArrayList<Car> carList = new ArrayList<>();
	private int nextId             = 1;

	private JFrame frame;
	private JTextArea displayArea;

	public CarRentingSystemGUI() {
		frame = new JFrame("Car Renting System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);

		JPanel panel = new JPanel(new GridLayout(9, 1));
		displayArea  = new JTextArea(10, 40);
		displayArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(displayArea);

		JButton addCarBtn     = new JButton("Add Car");
		JButton viewCarsBtn   = new JButton("View All Cars");
		JButton rentCarBtn    = new JButton("Rent a Car");
		JButton returnCarBtn  = new JButton("Return a Car");
		JButton costBtn       = new JButton("Calculate Rental Cost");
		JButton historyBtn    = new JButton("View Rental History");
		JButton mostRentedBtn = new JButton("Most Rented Car");
		JButton clearBtn      = new JButton("Clear Output");

		addCarBtn.addActionListener(e -> addCar());
		viewCarsBtn.addActionListener(e -> viewCars());
		rentCarBtn.addActionListener(e -> rentCar());
		returnCarBtn.addActionListener(e -> returnCar());
		costBtn.addActionListener(e -> calculateRentalCost());
		historyBtn.addActionListener(e -> viewRentalHistory());
		mostRentedBtn.addActionListener(e -> viewMostRentedCar());
		clearBtn.addActionListener(e -> displayArea.setText(""));

		panel.add(addCarBtn);
		panel.add(viewCarsBtn);
		panel.add(rentCarBtn);
		panel.add(returnCarBtn);
		panel.add(costBtn);
		panel.add(historyBtn);
		panel.add(mostRentedBtn);
		panel.add(clearBtn);

		frame.getContentPane().add(BorderLayout.WEST, panel);
		frame.getContentPane().add(BorderLayout.CENTER, scroll);
		frame.setVisible(true);
	}

	private void addCar() {
		String brand    = JOptionPane.showInputDialog("Enter brand:");
		String model    = JOptionPane.showInputDialog("Enter model:");
		String priceStr = JOptionPane.showInputDialog("Enter rental price per day:");
		if (brand == null || model == null || priceStr == null) return;

		try {
			double price = Double.parseDouble(priceStr);
			Car car      = new Car(nextId++, brand, model, price);
			carList.add(car);
			display("Car added: " + car);
		} catch (NumberFormatException e) {
			display("Invalid price!");
		}
	}

	private void viewCars() {
		if (carList.isEmpty()) {
			display("No cars available.");
			return;
		}
		for (Car car : carList) {
			display(car.toString());
		}
	}

	private void rentCar() {
		try {
			int id   = Integer.parseInt(JOptionPane.showInputDialog("Enter Car ID:"));
			int days = Integer.parseInt(JOptionPane.showInputDialog("Enter rental days:"));
			for (Car car : carList) {
				if (car.getId() == id) {
					if (!car.isRented()) {
						car.rent(days);
						display("Car rented: " + car);
						return;
					} else {
						display("Car is already rented.");
						return;
					}
				}
			}
			display("Car ID not found.");
		} catch (Exception e) {
			display("Invalid input.");
		}
	}

	private void returnCar() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Car ID:"));
			for (Car car : carList) {
				if (car.getId() == id) {
					if (car.isRented()) {
						car.returnCar();
						display("Car returned: " + car);
						return;
					} else {
						display("Car is not currently rented.");
						return;
					}
				}
			}
			display("Car ID not found.");
		} catch (Exception e) {
			display("Invalid input.");
		}
	}

	private void calculateRentalCost() {
		try {
			int id   = Integer.parseInt(JOptionPane.showInputDialog("Enter Car ID:"));
			int days = Integer.parseInt(JOptionPane.showInputDialog("Enter number of days:"));
			for (Car car : carList) {
				if (car.getId() == id) {
					double cost = car.getRentalPricePerDay() * days;
					display("Rental cost: ₹" + cost);
					return;
				}
			}
			display("Car ID not found.");
		} catch (Exception e) {
			display("Invalid input.");
		}
	}

	private void viewRentalHistory() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Car ID:"));
			for (Car car : carList) {
				if (car.getId() == id) {
					String info = "Car ID " + id + ": "
					    + (car.isRented() ? "Currently rented for " + car.getRentalDays() + " days"
					                      : "Not currently rented")
					    + ", Total Rentals: " + car.getTotalRentalCount();
					display(info);
					return;
				}
			}
			display("Car ID not found.");
		} catch (Exception e) {
			display("Invalid input.");
		}
	}

	private void viewMostRentedCar() {
		if (carList.isEmpty()) {
			display("No cars available.");
			return;
		}
		Car mostRented = null;
		for (Car car : carList) {
			if (mostRented == null || car.getTotalRentalCount() > mostRented.getTotalRentalCount()) {
				mostRented = car;
			}
		}
		display("Most rented car: " + mostRented);
	}

	private void display(String msg) {
		displayArea.append(msg + "\n");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(CarRentingSystemGUI::new);
	}
}
