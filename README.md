# Class Project

## Overview

DriveEasy: Your Gateway
to Smart Car Rentals


A car rental system where users can book cars based on availability, rental period, and pricing.
It implements inheritance for different vehicle types and interfaces for payment processing.


## Diagrams

### Class Diagram
![](./resources/diagram/class_diagram.svg)

### SQL Table Schema Diagram
![](resources/diagram/table_schema.svg)

## How to Build and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Gradle 6.8.3 or higher for the build system


### Steps
1. Clone the repository:
```sh
git clone https://github.com/pritam12426/class_practice
cd class_practice
```

2. Build the project using Gradle:
```sh
./gradlew run
```

3. Run the application:
```sh
java -jar build/libs/class_practice.jar
```

## Database Setup
The database schema can be set up using the `Database_Setup.sql` script located in the `src/main/java/com/App` directory. This script creates the necessary tables and relationships for the car rental system.

``` sh
sqlite3 rentalsystem.db < src/main/java/com/App/Database_Setup.sql
```

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
