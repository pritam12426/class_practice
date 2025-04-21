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
		return "Car ID: " + id + " - " + brand + " " + model + " | ₹" + rentalPricePerDay + "/day"
		    + (isRented ? " [RENTED for " + rentalDays + " days]" : " [AVAILABLE]")
		    + ", Total Rentals: " + totalRentalCount;
	}
}

public class CarRentingSystemGUI extends JFrame {
	private final ArrayList<Car> carList                = new ArrayList<>();
	private int nextId                                  = 1;
	private final DefaultListModel<String> carListModel = new DefaultListModel<>();
	private final JList<String> carDisplayList          = new JList<>(carListModel);

	public CarRentingSystemGUI() {
		super("🚗 Car Renting System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setMinimumSize(new Dimension(700, 400));
		setLocationRelativeTo(null);

		initUI();
		setVisible(true);
	}

	private void initUI() {
		setLayout(new BorderLayout(10, 10));

		carDisplayList.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(carDisplayList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Available Cars"));
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets             = new Insets(5, 5, 5, 5);
		gbc.fill               = GridBagConstraints.HORIZONTAL;
		gbc.gridx              = 0;

		String[] buttons = { "Add Car",
			"Rent Car",
			"Return Car",
			"Calculate Cost",
			"View History",
			"Most Rented Car",
			"Exit" };

		for (int i = 0; i < buttons.length; i++) {
			JButton btn = new JButton(buttons[i]);
			btn.setFont(new Font("SansSerif", Font.BOLD, 13));
			gbc.gridy = i;
			buttonPanel.add(btn, gbc);

			switch (buttons[i]) {
				case "Add Car" -> btn.addActionListener(e -> addCar());
				case "Rent Car" -> btn.addActionListener(e -> rentCar());
				case "Return Car" -> btn.addActionListener(e -> returnCar());
				case "Calculate Cost" -> btn.addActionListener(e -> calculateCost());
				case "View History" -> btn.addActionListener(e -> viewHistory());
				case "Most Rented Car" -> btn.addActionListener(e -> showMostRented());
				case "Exit" -> btn.addActionListener(e -> System.exit(0));
			}
		}

		add(buttonPanel, BorderLayout.EAST);
	}

	private void addCar() {
		try {
			String brand    = JOptionPane.showInputDialog(this, "Enter brand:");
			String model    = JOptionPane.showInputDialog(this, "Enter model:");
			String priceStr = JOptionPane.showInputDialog(this, "Enter price per day:");
			if (brand == null || model == null || priceStr == null) return;

			double price = Double.parseDouble(priceStr);
			Car car      = new Car(nextId++, brand, model, price);
			carList.add(car);
			carListModel.addElement(car.toString());
		} catch (Exception e) {
			showError("Invalid input.");
		}
	}

	private void rentCar() {
		int index = carDisplayList.getSelectedIndex();
		if (index == -1) {
			showError("Select a car to rent.");
			return;
		}
		Car car = carList.get(index);
		if (car.isRented()) {
			showError("Car is already rented.");
			return;
		}
		String daysStr = JOptionPane.showInputDialog(this, "Enter rental days:");
		if (daysStr == null) return;
		try {
			int days = Integer.parseInt(daysStr);
			car.rent(days);
			updateCarList();
			showMessage("Car rented successfully for " + days + " days.");
		} catch (Exception e) {
			showError("Invalid number.");
		}
	}

	private void returnCar() {
		int index = carDisplayList.getSelectedIndex();
		if (index == -1) {
			showError("Select a car to return.");
			return;
		}
		Car car = carList.get(index);
		if (!car.isRented()) {
			showError("Car is not currently rented.");
			return;
		}
		car.returnCar();
		updateCarList();
		showMessage("Car returned successfully.");
	}

	private void calculateCost() {
		int index = carDisplayList.getSelectedIndex();
		if (index == -1) {
			showError("Select a car.");
			return;
		}
		String daysStr = JOptionPane.showInputDialog(this, "Enter number of rental days:");
		if (daysStr == null) return;
		try {
			int days    = Integer.parseInt(daysStr);
			Car car     = carList.get(index);
			double cost = car.getRentalPricePerDay() * days;
			showMessage("Total cost: ₹" + cost);
		} catch (Exception e) {
			showError("Invalid number.");
		}
	}

	private void viewHistory() {
		int index = carDisplayList.getSelectedIndex();
		if (index == -1) {
			showError("Select a car.");
			return;
		}
		Car car = carList.get(index);
		showMessage("Car ID " + car.getId() + "\nCurrently: "
		    + (car.isRented() ? "Rented for " + car.getRentalDays() + " days" : "Available")
		    + "\nTotal times rented: " + car.getTotalRentalCount());
	}

	private void showMostRented() {
		if (carList.isEmpty()) {
			showError("No cars available.");
			return;
		}
		Car mostRented = carList.get(0);
		for (Car c : carList) {
			if (c.getTotalRentalCount() > mostRented.getTotalRentalCount()) {
				mostRented = c;
			}
		}
		showMessage("Most rented car:\n" + mostRented);
	}

	private void updateCarList() {
		carListModel.clear();
		for (Car c : carList) {
			carListModel.addElement(c.toString());
		}
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(CarRentingSystemGUI::new);
	}
}
