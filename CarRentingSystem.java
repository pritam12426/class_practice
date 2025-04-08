import java.util.ArrayList;
import java.util.Scanner;

class Car {
	private int id;
	private String brand;
	private String model;
	private boolean isRented;
	private double rentalPricePerDay;
	private int rentalDays;

	public Car(int id, String brand, String model, double rentalPricePerDay) {
		this.id                = id;
		this.brand             = brand;
		this.model             = model;
		this.isRented          = false;
		this.rentalPricePerDay = rentalPricePerDay;
		this.rentalDays        = 0;
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

	public void rent(int days) {
		isRented   = true;
		rentalDays = days;
	}

	public void returnCar() {
		isRented   = false;
		rentalDays = 0;
	}

	@Override
	public String toString() {
		return "Car ID: " + id + ", " + brand + " " + model + ", Price per day: ₹" + rentalPricePerDay
		    + (isRented ? " [RENTED for " + rentalDays + " days]" : " [AVAILABLE]");
	}
}

public class CarRentingSystem {
	private static ArrayList<Car> carList = new ArrayList<>();
	private static int nextId             = 1;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;

		System.out.println("Welcome to the Car Renting System!");

		while (true) {
			System.out.println("\n1. Add Car");
			System.out.println("2. View All Cars");
			System.out.println("3. Rent a Car");
			System.out.println("4. Return a Car");
			System.out.println("5. Calculate Rental Cost");
			System.out.println("6. View Rental History");
			System.out.println("7. Exit");
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();

			switch (choice) {
				case 1:
					addCar(scanner);
					break;
				case 2:
					viewCars();
					break;
				case 3:
					rentCar(scanner);
					break;
				case 4:
					returnCar(scanner);
					break;
				case 5:
					calculateRentalCost(scanner);
					break;
				case 6:
					viewRentalHistory(scanner);
					break;
				case 7:
					System.out.println("Thank you for using the system!");
					return;
				default:
					System.out.println("Invalid option.");
			}
		}
	}

	private static void addCar(Scanner scanner) {
		System.out.print("Enter brand: ");
		String brand = scanner.next();
		System.out.print("Enter model: ");
		String model = scanner.next();
		System.out.print("Enter rental price per day: ");
		double rentalPricePerDay = scanner.nextDouble();
		carList.add(new Car(nextId++, brand, model, rentalPricePerDay));
		System.out.println("Car added successfully!");
	}

	private static void viewCars() {
		if (carList.isEmpty()) {
			System.out.println("No cars available.");
			return;
		}
		for (Car car : carList) {
			System.out.println(car);
		}
	}

	private static void rentCar(Scanner scanner) {
		System.out.print("Enter Car ID to rent: ");
		int id = scanner.nextInt();
		System.out.print("Enter number of rental days: ");
		int days = scanner.nextInt();
		for (Car car : carList) {
			if (car.getId() == id) {
				if (!car.isRented()) {
					car.rent(days);
					System.out.println("Car rented successfully for " + days + " days!");
					return;
				} else {
					System.out.println("Car is already rented.");
					return;
				}
			}
		}
		System.out.println("Car ID not found.");
	}

	private static void returnCar(Scanner scanner) {
		System.out.print("Enter Car ID to return: ");
		int id = scanner.nextInt();
		for (Car car : carList) {
			if (car.getId() == id) {
				if (car.isRented()) {
					car.returnCar();
					System.out.println("Car returned successfully!");
					return;
				} else {
					System.out.println("Car is not currently rented.");
					return;
				}
			}
		}
		System.out.println("Car ID not found.");
	}

	private static void calculateRentalCost(Scanner scanner) {
		System.out.print("Enter Car ID to calculate rental cost: ");
		int id = scanner.nextInt();
		System.out.print("Enter number of rental days: ");
		int days = scanner.nextInt();
		for (Car car : carList) {
			if (car.getId() == id) {
				double cost = car.getRentalPricePerDay() * days;
				System.out.println("Total rental cost for " + days + " days: ₹" + cost);
				return;
			}
		}
		System.out.println("Car ID not found.");
	}

	private static void viewRentalHistory(Scanner scanner) {
		System.out.print("Enter Car ID to view rental history: ");
		int id = scanner.nextInt();
		for (Car car : carList) {
			if (car.getId() == id) {
				System.out.println("Rental history for Car ID " + id + ": "
				    + (car.isRented() ? "Currently rented for " + car.getRentalDays() + " days"
				                      : "Not currently rented"));
				return;
			}
		}
		System.out.println("Car ID not found.");
	}
}
