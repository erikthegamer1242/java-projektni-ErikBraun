package app;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.exceptions.RouteCostNegativeException;
import entity.exceptions.YearNegativeException;
import entity.subclasses.Driver;
import entity.subclasses.User;
import entity.superclasses.Person;
import utilty.DataSearch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// NOSONAR (S106,S1192)
@java.lang.SuppressWarnings({"squid:S106", "squid:S1192"})
public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        logger.info("Started main app!");
        System.out.println("Welcome to the best Driving Management System.\nPlease enter new data below to proceed");
        Scanner scanner = new Scanner(System.in);

        boolean correct = false;
        Integer stopsQuantity = 0;
        List<Stop> stops = new ArrayList<>();
        do {
            try {
                System.out.println("How many stops do you want to add?");
                stopsQuantity = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < stopsQuantity; i++) {
                    System.out.println("Please enter stop ID for " + (i + 1) + ". stop : ");
                    Integer stopID = scanner.nextInt();
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

        correct = false;
        Integer driverQuantity = 0;
        List<Driver> drivers = new ArrayList<>();
        do {
            try {
                System.out.println("How many drivers do you want to add?");
                driverQuantity = scanner.nextInt();
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
                    drivers.add(new Driver.DriverBuilder(driverOIB, driverName, driverLastName, driverLicenseNumber, driverHourlyPay).email(driverEmail).phoneNumber(driverPhoneNumber).dateOfBirth(LocalDate.parse(driverDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).build());
                }
                correct = true;
            } catch (InputMismatchException | NullPointerException e) {
                logger.error("Invalid input for drivers", e);
                if (e instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            }
        } while (!correct);

        correct = false;
        Integer vehicleQuantity = 0;
        List<Vehicle> vehicles = new ArrayList<>();
        do {
            try {
                System.out.println("How many vehicles do you want to add?");
                vehicleQuantity = scanner.nextInt();
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
                    Integer vehicleProductionYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Choose motor type for " + (i + 1) + ". vehicle : ");
                    Integer idx = 1;
                    for (Vehicle.MotorType motorType : Vehicle.MotorType.values()) {
                        System.out.print(idx++ + ") ");
                        System.out.println(motorType.toString());
                    }
                    Integer enumChoice = scanner.nextInt() - 1;
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

        correct = false;
        Integer userQuantity = 0;
        List<User> users = new ArrayList<>();
        do {
            try {
                System.out.println("How many users do you want to add?");
                userQuantity = scanner.nextInt();
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

        correct = false;
        Integer routesQuantity = 0;

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
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < routesQuantity; i++) {
            System.out.println("Please enter route's ID for " + (i + 1) + ". route: ");
            Integer routeID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter route's name for " + (i + 1) + ". route: ");
            String routeName = scanner.nextLine();
            Integer vehicleIndex = 0;
            while (true) {
                try {
                    System.out.println("Please enter route's vehicle from the list below for " + (i + 1) + ". route: ");
                    for (int j = 0; j < vehicleQuantity; j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(vehicles.get(j).toString());
                    }
                    vehicleIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (vehicleIndex >= vehicleQuantity || vehicleIndex < 0) {
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
            Integer driverIndex = 0;
            while (true) {
                try {
                    System.out.println("Please enter route's driver from the list below for " + (i + 1) + ". route: ");
                    for (int j = 0; j < driverQuantity; j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(drivers.get(j).toString());
                    }
                    driverIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (driverIndex >= driverQuantity || driverIndex < 0) {
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
            Integer stopLenght = 0;
            while (true) {
                try {
                    System.out.println("Enter how many stops to be added to the " + (i + 1) + ". route");
                    stopLenght = scanner.nextInt();
                    scanner.nextLine();
                    if (stopLenght > stopsQuantity || stopLenght < 0) {
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
            Integer stopCounter = 0;
            List<Stop> stopsForRoute = new ArrayList<>();
            System.out.println("Please enter stops to be added to the " + (i + 1) + ". route");
            while (stopCounter <= stopLenght - 1) {
                try {
                    for (int j = 0; j < stopsQuantity; j++) {
                        System.out.print((j + 1) + ") ");
                        System.out.println(stops.get(j).toString());
                    }
                    Integer stopIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (stopIndex >= stopsQuantity || stopIndex < 0) {
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

        System.out.println("These are all the routes: ");
        for (int i = 0; i < routes.size(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(routes.get(i).toString());
        }

        boolean running = true;
        while (running) {
            correct = false;
            Integer action = 0;
            do {
                System.out.println("Here are available actions, please select one: ");
                System.out.println("1) Print statistics");
                System.out.println("2) Find a driver in route by name");
                System.out.println("3) Find a person by name");
                System.out.println("4) Quit");
                try {
                    action = scanner.nextInt();
                    correct = true;
                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for action", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                }
            } while (!correct);
            scanner.nextLine();

            switch (action) {
                case 1:
                    DataSearch.showRouteStatistics(routes);
                    break;
                case 2:
                    System.out.print("Enter driver's name: ");
                    String driverName = scanner.nextLine();
                    Route foundRoute = DataSearch.findRouteWithDriverName(routes, driverName);
                    if (foundRoute != null) {
                        System.out.println("Found route that has driver: " + driverName);
                        System.out.println(foundRoute);
                    } else {
                        System.out.println("Driver not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter person's name: ");
                    String personName = scanner.nextLine();
                    List<Person> persons = new ArrayList<>();
                    persons.addAll(users);
                    persons.addAll(drivers);
                    persons.sort(Comparator.comparing(Person::getName).thenComparing(Person::getSurname));
                    Person found = DataSearch.findPersonByName(persons, personName);
                    if (found != null) {
                        if (found instanceof Driver) {
                            System.out.println("Found " + personName + ". He is a driver!");
                        } else if (found instanceof User) {
                            System.out.println("Found " + personName + ". He is a user!");
                        }
                        System.out.println(found);
                    } else {
                        System.out.println("Person not found");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }
        }
    }
}
