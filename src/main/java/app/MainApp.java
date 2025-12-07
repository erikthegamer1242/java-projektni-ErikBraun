package app;

import entity.Route;
import entity.Stop;
import entity.Vehicle;
import entity.subclasses.Driver;
import entity.subclasses.User;
import entity.superclasses.Person;
import utilty.DataSearch;
import utilty.JSONHelper;

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
                System.out.println("1) Print statistics");
                System.out.println("2) Find a driver in route by name");
                System.out.println("3) Find a person by name");
                System.out.println("4) Print all routes");
                System.out.println("5) Calculate all drivers pay");
                System.out.println("6) Quit");
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
                        scanner.nextLine();
                        String response = scanner.nextLine();
                        if (response.equalsIgnoreCase("N")) {
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
}
