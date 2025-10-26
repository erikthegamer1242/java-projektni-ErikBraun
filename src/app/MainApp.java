package app;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;
import entity.superclasses.Person;
import utilty.DataSearch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the best Driving Management System.\nPlease enter new data below to proceed");
        System.out.println("How many stops do you want to add?");
        Scanner scanner = new Scanner(System.in);
        Integer stopsQuantity = scanner.nextInt();
        scanner.nextLine();
        Stop[] stops = new Stop[stopsQuantity];
        for (int i = 0; i < stopsQuantity; i++) {
            System.out.println("Please enter stop ID for " + (i + 1) + ". stop : ");
            Integer stopID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter stop name for " + (i + 1) + ". stop: ");
            String stopName = scanner.nextLine();
            stops[i] = new Stop(stopID, stopName);
        }
        System.out.println("How many drivers do you want to add?");
        Integer driverQuantity = scanner.nextInt();
        scanner.nextLine();
        Driver[] drivers = new Driver[driverQuantity];
        for (int i = 0; i < driverQuantity; i++) {
            System.out.println("Please enter driver's OIB for " + (i + 1) + ". driver : ");
            String OIB = scanner.nextLine();
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
            drivers[i] = new Driver.DriverBuilder(OIB, driverName, driverLastName, driverLicenseNumber, driverHourlyPay).
                    email(driverEmail).
                    phoneNumber(driverPhoneNumber).
                    dateOfBirth(LocalDate.parse(driverDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).
                    build();
        }
        System.out.println("How many vehicles do you want to add?");
        Integer vehicleQuantity = scanner.nextInt();
        scanner.nextLine();
        Vehicle[] vehicles = new Vehicle[vehicleQuantity];
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
            vehicles[i] = new Vehicle(vehicleName, vehicleModel, vehicleLicensePlate, vehicleVinNumber, vehicleProductionYear);
        }
        System.out.println("How many users do you want to add?");
        Integer userQuantity = scanner.nextInt();
        scanner.nextLine();
        User[] users = new User[userQuantity];
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
            users[i] = new User.UserBuilder(userOIB, userName, userSurname)
                    .email(userEmail)
                    .dateOfBirth(LocalDate.parse(userDateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .build();
        }
        System.out.println("How many routes do you want to add?");
        Integer routesQuantity = scanner.nextInt();
        scanner.nextLine();
        Route[] routes = new Route[routesQuantity];
        for (int i = 0; i < routesQuantity; i++) {
            System.out.println("Please enter route's ID for " + (i + 1) + ". route: ");
            Integer routeID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter route's name for " + (i + 1) + ". route: ");
            String routeName = scanner.nextLine();
            Integer vehicleIndex = 0;
            while (true) {
                System.out.println("Please enter route's vehicle from the list below for " + (i + 1) + ". route: ");
                for (int j = 0; j < vehicleQuantity; j++) {
                    System.out.print((j + 1) + ") ");
                    vehicles[j].printVehicle();
                }
                vehicleIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (vehicleIndex >= vehicleQuantity || vehicleIndex < 0) {
                    System.out.println("Invalid index!");
                } else {
                    break;
                }
            }
            Integer driverIndex = 0;
            while (true) {
                System.out.println("Please enter route's driver from the list below for " + (i + 1) + ". route: ");
                for (int j = 0; j < driverQuantity; j++) {
                    System.out.print((j + 1) + ") ");
                    drivers[j].toString();
                }
                driverIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (driverIndex >= driverQuantity || driverIndex < 0) {
                    System.out.println("Invalid index!");
                } else {
                    break;
                }
            }
            Integer stopLenght = 0;
            while (true) {
                System.out.println("Enter how many stops to be added to the " + (i + 1) + ". route");
                stopLenght = scanner.nextInt();
                scanner.nextLine();
                if (stopLenght > stopsQuantity || stopLenght < 0) {
                    System.out.println("Not enough stops available!");
                } else {
                    break;
                }
            }
            Integer stopCounter = 0;
            Stop[] stopsForRoute = new Stop[stopLenght];
            System.out.println("Please enter stops to be added to the " + (i + 1) + ". route");
            while (stopCounter <= stopLenght - 1) {
                for (int j = 0; j < stopsQuantity; j++) {
                    System.out.print((j + 1) + ") ");
                    stops[j].printStop();
                }
                Integer stopIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (stopIndex >= stopsQuantity || stopIndex < 0) {
                    System.out.println("Invalid index!");
                } else {
                    stopsForRoute[stopCounter++] = stops[stopIndex];
                }
            }
            System.out.println("Please enter how much a stop costs for the " + (i + 1) + ". route");
            BigDecimal stopCost = scanner.nextBigDecimal();
            scanner.nextLine();
            routes[i] = new Route(routeID, routeName, vehicles[vehicleIndex], drivers[driverIndex], stopsForRoute, stopLenght, stopCost);
        }
        System.out.println("These are all the routes: ");
        for (int i = 0; i < routes.length; i++) {
            System.out.print((i + 1) + ") ");
            routes[i].printRoute();
        }
        while (true) {
            System.out.println("Here are available actions, please select one: ");
            System.out.println("1) Print statistics");
            System.out.println("2) Find a driver in route by name");
            System.out.println("3) Find a person by name");
            System.out.println("3) Quit");

            Integer action = scanner.nextInt();
            scanner.nextLine();

            if (action == 1) {
                DataSearch.showRouteStatistics(routes);
            }
            else if (action == 2) {
                System.out.print("Enter driver's name: ");
                String driverName = scanner.nextLine();
                Route foundRoute = DataSearch.findRouteWithDriverName(routes, driverName);
                if (foundRoute != null) {
                    System.out.println("Found route that has driver: " + driverName);
                    foundRoute.printRoute();
                } else {
                    System.out.println("Driver not found");
                }
            }
            else if (action == 3) {
                System.out.println("Enter person's name: ");
                String personName = scanner.nextLine();
                Integer size = driverQuantity + userQuantity;
                Person[] persons = new Person[size];
                for (int i = 0; i < driverQuantity; i++) {
                    persons[i] = drivers[i];
                }
                for (int i = 0; i < userQuantity; i++) {
                    persons[i+driverQuantity] = users[i];
                }
                Person found = DataSearch.findPersonByName(persons, personName);
                if (found != null) {
                    System.out.println("Found person: " + personName);
                    System.out.println(found.toString());
                }
                else  {
                    System.out.println("Person not found");
                }

            }
            else if (action == 4) {
                break;
            }
            else {
                System.out.println("Invalid input! Try again!");
            }
        }
    }

}
