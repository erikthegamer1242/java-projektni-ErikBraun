package utilty;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.exceptions.RouteCostNegativeException;
import entity.exceptions.YearNegativeException;
import entity.subclasses.Driver;
import entity.subclasses.User;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Static interface implementing methods for reading data from the console using Scanner
 *  *
 *  * @author erik
 *  * @version 1.0
 */
public interface InputData {
    /**
     * Reads a list of stops from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @return List<Stop> the list of stops that were created from input
     */
    public static List<Stop> inputStops(Scanner scanner, Logger logger) {
        boolean correct = false;
        List<Stop> stops = new ArrayList<>();
        do {
            try {
                System.out.println("How many stops do you want to add?");
                int stopsQuantity = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < stopsQuantity; i++) {
                    System.out.println("Please enter stop ID for " + (i + 1) + ". stop : ");
                    int stopID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter stop name for " + (i + 1) + ". stop: ");
                    String stopName = scanner.nextLine();
                    stops.add(new Stop(stopID, stopName));
                }
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for stops", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            }
        } while (!correct);
        return stops;
    }

    /**
     * Reads a list of drivers from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @return List<Driver> the list of drivers created from input
     */
    public static List<Driver> inputDrivers(Scanner scanner, Logger logger) {
        boolean correct = false;
        List<Driver> drivers = new ArrayList<>();
        do {
            try {
                System.out.println("How many drivers do you want to add?");
                int driverQuantity = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < driverQuantity; i++) {
                    System.out.println("Please enter driver's OIB for " + (i + 1) + ". driver : ");
                    String driverOIB = scanner.nextLine();
                    System.out.println("Please enter driver's first name for " + (i + 1) + ". driver : ");
                    String driverName = scanner.nextLine();
                    System.out.println("Please enter driver's last name for " + (i + 1) + ". driver : ");
                    String driverLastName = scanner.nextLine();
                    System.out.println("Please enter driver's license number for " + (i + 1) + ". driver : ");
                    String driverLicenseNumber = scanner.nextLine();
                    System.out.println("Please enter driver's email for " + (i + 1) + ". driver : ");
                    String driverEmail = scanner.nextLine();
                    System.out.println("Please enter driver's phone number for " + (i + 1) + ". driver : ");
                    String driverPhoneNumber = scanner.nextLine();
                    System.out.println("Please enter driver's date of birth (DD-MM-YYYY) for " + (i + 1) + ". driver : ");
                    String driverDateOfBirth = scanner.nextLine();
                    System.out.println("Please enter driver's hourly pay for " + (i + 1) + ". driver : ");
                    BigDecimal driverHourlyPay = new BigDecimal(scanner.nextLine());
                    System.out.println("Please enter driver's working hours for " + (i + 1) + ". driver : ");
                    BigDecimal driverWorkingHours = new BigDecimal(scanner.nextLine());
                    drivers.add(new Driver.DriverBuilder(driverOIB, driverName, driverLastName, driverLicenseNumber, driverHourlyPay, driverWorkingHours).email(driverEmail).phoneNumber(driverPhoneNumber).dateOfBirth(LocalDate.parse(driverDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build());
                }
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for drivers", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            }
        } while (!correct);
        return drivers;
    }

    /**
     * Reads a list of vehicles from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @return List<Vehicle> the list of vehicles created from input
     */
    public static List<Vehicle> inputVehicles(Scanner scanner, Logger logger) {
        boolean correct = false;
        List<Vehicle> vehicles = new ArrayList<>();
        do {
            try {
                System.out.println("How many vehicles do you want to add?");
                int vehicleQuantity = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < vehicleQuantity; i++) {
                    System.out.println("Please enter vehicle's name for " + (i + 1) + ". vehicle : ");
                    String vehicleName = scanner.nextLine();
                    System.out.println("Please enter vehicle's model for " + (i + 1) + ". vehicle : ");
                    String vehicleModel = scanner.nextLine();
                    System.out.println("Please enter vehicle's license plate of registration for " + (i + 1) + ". vehicle : ");
                    String vehicleLicensePlate = scanner.nextLine();
                    System.out.println("Please enter vehicle's vin number for " + (i + 1) + ". vehicle : ");
                    String vehicleVinNumber = scanner.nextLine();
                    System.out.println("Please enter vehicle's production year for " + (i + 1) + ". vehicle : ");
                    int vehicleProductionYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Choose motor type for " + (i + 1) + ". vehicle : ");
                    int idx = 1;
                    for (Vehicle.MotorType motorType : Vehicle.MotorType.values()) {
                        System.out.print(idx++ + ") ");
                        System.out.println(motorType.toString());
                    }
                    int enumChoice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (enumChoice < 0 || enumChoice > Vehicle.MotorType.values().length) {
                        throw new InputMismatchException("Invalid choice for motor type enum!");
                    }
                    vehicles.add(new Vehicle(vehicleName, vehicleModel, vehicleLicensePlate, vehicleVinNumber, vehicleProductionYear, Vehicle.MotorType.values()[enumChoice]));
                }
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for vehicles", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            } catch (YearNegativeException e) {
                logger.error(e.getMessage());
                scanner.nextLine();
            }
        } while (!correct);
        return vehicles;
    }

    /**
     * Reads a list of users from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @return List<User> the list of users created from input
     */
    public static List<User> inputUsers(Scanner scanner, Logger logger) {
        boolean correct = false;
        List<User> users = new ArrayList<>();
        do {
            try {
                System.out.println("How many users do you want to add?");
                int userQuantity = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < userQuantity; i++) {
                    System.out.println("Please enter user's first name for " + (i + 1) + ". user : ");
                    String userName = scanner.nextLine();
                    System.out.println("Please enter user's surname for " + (i + 1) + ". user : ");
                    String userSurname = scanner.nextLine();
                    System.out.println("Please enter user's OIB for  " + (i + 1) + ". user : ");
                    String userOIB = scanner.nextLine();
                    System.out.println("Please enter user's email for " + (i + 1) + ". user : ");
                    String userEmail = scanner.nextLine();
                    System.out.println("Please enter user's date of birth (DD-MM-YYYY) for " + (i + 1) + ". user : ");
                    String userDateOfBirth = scanner.nextLine();
                    users.add(new User.UserBuilder(userOIB, userName, userSurname).email(userEmail).dateOfBirth(LocalDate.parse(userDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build());
                }
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for users", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            } catch (DateTimeParseException e) {
                logger.error("Invalid DOB entered for user", e);
            }
        } while (!correct);
        return users;
    }

    /**
     * Reads the number of routes from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @return Integer the number of routes entered
     */
    public static Integer inputRoutesQuantity(Scanner scanner, Logger logger) {
        boolean correct = false;
        int routesQuantity = 0;
        do {
            try {
                System.out.println("How many routes do you want to add?");
                routesQuantity = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for routes", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            }
        } while (!correct);
        scanner.nextLine();
        return routesQuantity;
    }

    /**
     * Reads a list of routes from input.
     *
     * @param scanner Scanner the scanner used to read input
     * @param logger Logger the logger used to log errors
     * @param routesQuantity Integer the number of routes to read
     * @param vehicles list of available vehicles to choose from
     * @param drivers list of available drivers to choose from
     * @param stops list of available stops to build routes from
     * @return list of routes created from input
     */
    public static List<Route> inputRoutes(Scanner scanner, Logger logger, Integer routesQuantity, List<Vehicle> vehicles, List<Driver> drivers, List<Stop> stops) {
        boolean correct = false;
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < routesQuantity; i++) {
            System.out.println("Please enter route's ID for " + (i + 1) + ". route: ");
            Integer routeID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter route's name for " + (i + 1) + ". route: ");
            String routeName = scanner.nextLine();
            int vehicleIndex;
            while (true) {
                try {
                    System.out.println("Please enter route's vehicle from the list below for " + (i + 1) + ". route: ");
                    for (int j = 0; j < vehicles.size(); j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(vehicles.get(j).toString());
                    }
                    vehicleIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (vehicleIndex >= vehicles.size() || vehicleIndex < 0) {
                        System.out.println("Invalid index!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for route's vehicle", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                }
            }

            int driverIndex;
            while (true) {
                try {
                    System.out.println("Please enter route's driver from the list below for " + (i + 1) + ". route: ");
                    for (int j = 0; j < drivers.size(); j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(drivers.get(j).toString());
                    }
                    driverIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (driverIndex >= drivers.size() || driverIndex < 0) {
                        System.out.println("Invalid index!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for route's driver", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                }
            }

            int stopLength;
            while (true) {
                try {
                    System.out.println("Enter how many stops to be added to the " + (i + 1) + ". route");
                    stopLength = scanner.nextInt();
                    scanner.nextLine();
                    if (stopLength > stops.size() || stopLength < 0) {
                        System.out.println("Not enough stops available!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for route's stops", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                }
            }
            int stopCounter = 0;
            List<Stop> stopsForRoute = new ArrayList<>();
            System.out.println("Please enter stops to be added to the " + (i + 1) + ". route");
            while (stopCounter <= stopLength - 1) {
                try {
                    for (int j = 0; j < stops.size(); j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(stops.get(j).toString());
                    }
                    int stopIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (stopIndex >= stops.size() || stopIndex < 0) {
                        System.out.println("Invalid index!");
                    } else {
                        stopsForRoute.add(stops.get(stopIndex));
                        stopCounter++;
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for stops", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                }
            }
            correct = false;
            do {
                try {
                    System.out.println("Please enter how much a stop costs for the " + (i + 1) + ". route");
                    BigDecimal stopCost = scanner.nextBigDecimal();
                    scanner.nextLine();
                    routes.add(new Route(routeID, routeName, vehicles.get(vehicleIndex), drivers.get(driverIndex), stopsForRoute, stopCost));
                    correct = true;
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for route's cost", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                } catch (RouteCostNegativeException e) {
                    logger.error(e.getMessage());
                }
            } while (!correct);
        }
        return routes;
    }
}
