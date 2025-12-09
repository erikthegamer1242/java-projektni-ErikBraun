package app;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;
import entity.superclasses.Person;
import jakarta.xml.bind.JAXBException;
import utilty.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// NOSONAR (S106,S1192)
@SuppressWarnings({"squid:S106", "squid:S1192", "squid:S2133"})
public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        logger.info("Started main app!");
        System.out.println("Welcome to the best Driving Management System.\n");
        Scanner scanner = new Scanner(System.in);


        List<User> users = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        List<Stop> stops = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        try {
            users = JSONHelper.readListFromJSON("src/main/resources/data/users.json", new ArrayList<User>() {
            }.getClass());
            drivers = JSONHelper.readListFromJSON("src/main/resources/data/drivers.json", new ArrayList<Driver>() {
            }.getClass());
            vehicles = JSONHelper.readListFromJSON("src/main/resources/data/vehicles.json", new ArrayList<Vehicle>() {
            }.getClass());
            stops = JSONHelper.readListFromJSON("src/main/resources/data/stops.json", new ArrayList<Stop>() {
            }.getClass());
            routes = JSONHelper.readListFromJSON("src/main/resources/data/routes.json", new ArrayList<Route>() {
            }.getClass());
        } catch (Exception e) {
            logger.error("Error reading from JSON", e);
        }


        System.out.println("These are all the routes: ");
        for (int i = 0; i < routes.size(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.println(routes.get(i));
        }

        boolean running = true;
        while (running) {
            boolean correct = false;
            int action = 0;
            do {
                System.out.println("Here are available actions, please select one: ");
                for(int i = 1; i < 9; i++) {
                    System.out.println(i + ") " + listActions(i));
                }

                try {
                    action = scanner.nextInt();
                    XMLHelper.writeOneAction("User selected: " + listActions(action), "src/main/resources/actions/actions.xml");
                    correct = true;

                } catch (InputMismatchException | NullPointerException e) {
                    logger.error("Invalid input for action", e);
                    if (e instanceof InputMismatchException) {
                        scanner.nextLine();
                    }
                } catch (JAXBException e) {
                    logger.error("Error while writing to XML", e);
                    scanner.nextLine();
                }
            } while (!correct);
            scanner.nextLine();

            switch (action) {
                case 1:
                    routes.sort(Comparator.comparingInt(r -> r.getStops().size()));
                    DataSearch.showRouteStatistics(routes);
                    break;
                case 2:
                    System.out.print("Enter driver's name: ");
                    String driverName = scanner.nextLine();
                    Optional<Route> foundRoute = DataSearch.findRouteWithDriverName(routes, driverName);
                    if (foundRoute.isPresent()) {
                        System.out.println("Found route that has driver: " + driverName);
                        System.out.println(foundRoute.get());
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
                    Optional<Person> found = DataSearch.findPersonByName(persons, personName);
                    if (found.isPresent()) {
                        if (found.get() instanceof Driver) {
                            System.out.println("Found " + personName + ". He is a driver!");
                        } else if (found.get() instanceof User) {
                            System.out.println("Found " + personName + ". He is a user!");
                        }
                        System.out.println(found.get());
                    } else {
                        System.out.println("Person not found");
                    }
                    break;
                case 4:
                    Map<Integer, List<Route>> allRoutes = routes.stream()
                            .collect(Collectors.groupingBy(route -> route.getStops().size()));
                    allRoutes.forEach((n, r) -> {
                        System.out.println("++++++ Routes with " + n + " stop" + ((n != 1) ? "s" : "") + ": ++++++\n");
                        r.forEach(System.out::println);
                        System.out.println();
                    });
                    break;
                case 5:
                    DataSearch.calculatePayForAllEmployees(drivers);
                    break;
                case 6:
                    System.out.println("Warning! Taking a backup will override the previous backup. Are you sure? (Y/N)");
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        try {
                            BinaryHelper.writeAllDataToFile(users, drivers, vehicles, stops, routes, "src/main/resources/backup/backup.dat");
                        } catch (IOException e) {
                            logger.error("Error writing to backup file", e);
                        }
                    } else {
                        System.out.println("Canceling the backup...");
                    }
                    break;
                case 7:
                    System.out.println("Warning! Restoring a backup will override all current in-memory objects. Are you sure? (Y/N)");
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        try {
                            BackupDTO restoredData = BinaryHelper.readAllDataFromFile("src/main/resources/backup/backup.dat");
                            users = restoredData.users();
                            drivers = restoredData.drivers();
                            vehicles = restoredData.vehicles();
                            routes = restoredData.routes();
                            stops = restoredData.stops();
                        } catch (IOException | ClassNotFoundException e) {
                            logger.error("Error reading from backup", e);
                        }
                    }
                    else {
                        System.out.println("Canceling restore...");
                    }
                    System.out.println();
                    break;
                case 8:
                    running = false;
                    try {
                        JSONHelper.writeListToJSON(users, "src/main/resources/data/users.json");
                        JSONHelper.writeListToJSON(drivers, "src/main/resources/data/drivers.json");
                        JSONHelper.writeListToJSON(vehicles, "src/main/resources/data/vehicles.json");
                        JSONHelper.writeListToJSON(stops, "src/main/resources/data/stops.json");
                        JSONHelper.writeListToJSON(routes, "src/main/resources/data/routes.json");
                    } catch (Exception e) {
                        logger.error("Error while writing to JSON", e);
                        System.out.println("Error while writing to JSON\nDo you still want to quit the application? (Y/N)");
                        if (scanner.nextLine().equalsIgnoreCase("N")) {
                            running = true;
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid input! Try again!");
                    break;
            }
        }
    }

    public static String listActions(Integer idx) {
        return switch (idx) {
            case 1 -> "Print statistics";
            case 2 -> "Find a driver in route by name";
            case 3 -> "Find a person by name";
            case 4 -> "Print all routes";
            case 5 -> "Calculate all drivers pay";
            case 6 -> "Run backup";
            case 7 -> "Restore backup";
            case 8 -> "Quit";
            default -> "";
        };
    }
}
