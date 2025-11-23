package utilty;

import entity.Route;
import entity.subclasses.Employed;
import entity.subclasses.User;
import entity.superclasses.Person;

import java.math.BigDecimal;
import java.util.*;

/**
 * Static interface implementing methods that search and do statistical analysis of the data
 *
 * @author erik
 * @version 1.0
 */

public interface DataSearch {
    final String ROUTES_MESSAGE = "routes must not be null";

    /**
     * Finds a route that has the least amount of stops
     *
     * @param inRoutes a list of routes
     * @return If found returns the route, else first route
     * @throws NullPointerException   when one or more parameters are null
     * @throws NoSuchElementException when the list is empty
     */
    static Route findRouteWithLeastStops(List<Route> inRoutes) {
        List<Route> routes = Objects.requireNonNull(inRoutes, ROUTES_MESSAGE);
        Integer leastStops = routes.getFirst().getStops().size();
        Route route = routes.getFirst();
        for (Route r : routes) {
            if (r.getStops().size() < leastStops) {
                leastStops = r.getStops().size();
                route = r;
            }
        }
        return route;
    }

    /**
     * Finds a route that has the most amount of stops
     *
     * @param inRoutes a list of routes
     * @return If found returns the route, else first route
     * @throws NullPointerException   when one or more parameters are null
     * @throws NoSuchElementException when the list is empty
     */
    static Route findRouteWithMostStops(List<Route> inRoutes) {
        List<Route> routes = Objects.requireNonNull(inRoutes, ROUTES_MESSAGE);
        Integer mostStops = routes.getFirst().getStops().size();
        Route route = routes.getFirst();
        for (Route r : routes) {
            if (r.getStops().size() > mostStops) {
                mostStops = r.getStops().size();
                route = r;
            }
        }
        return route;
    }

    /**
     * Finds a route that has the driver's name in it
     *
     * @param routes     a list of routes
     * @param driverName String name of the driver
     * @return If found returns the route, else an empty optional
     * @throws NullPointerException when one or more parameters are null
     */
    static Optional<Route> findRouteWithDriverName(List<Route> routes, String driverName) {
        if (routes == null) {
            throw new NullPointerException(ROUTES_MESSAGE);
        }
        if (driverName == null) {
            throw new NullPointerException("driverName must not be null");
        }
        for (Route r : routes) {
            if (r.getDriver().getName().equals(driverName)) {
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a person that has any person's name
     *
     * @param persons a list of people
     * @param name    String name of the person
     * @return If found returns the person, else null. Wrapped with optional
     * @throws NullPointerException when one or more parameters are null
     */
    static Optional<Person> findPersonByName(List<? extends Person> persons, String name) {
        if (persons == null) {
            throw new NullPointerException("persons must not be null");
        }
        if (name == null) {
            throw new NullPointerException("name must not be null");
        }

        Integer idx = Collections.binarySearch(persons, new User.UserBuilder("1", name, "1").build(), (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        if (idx < 0) {
            return Optional.empty();
        }
        return Optional.of(persons.get(idx));
    }

    /**
     * Finds the most expensive route based on the stop cost and number of stops
     *
     * @param inRoutes a list of people
     * @return The most expensive route
     * @throws NullPointerException   when one or more parameters are null
     * @throws NoSuchElementException when the list is empty
     */
    static Route findMostExpensiveRoute(List<Route> inRoutes) {
        List<Route> routes = Objects.requireNonNull(inRoutes, ROUTES_MESSAGE);
        BigDecimal mostExpensiveRoute = routes.getFirst().getStopCost();
        Route route = routes.getFirst();
        for (Route r : routes) {
            if (r.getStopCost().compareTo(mostExpensiveRoute) > 0) {
                mostExpensiveRoute = r.getStopCost();
                route = r;
            }
        }
        return route;
    }

    /**
     * Prints out the route statistics
     *
     * @param routes a list of routes
     * @throws NullPointerException   when one or more parameters are null
     * @throws NoSuchElementException when the list is empty
     */
    static void showRouteStatistics(List<Route> routes) {
        if (routes == null) {
            throw new NullPointerException(ROUTES_MESSAGE);
        }
        String format = "Total routes: " + routes.size() + "\nRoute with least stops: " + routes.getFirst().toString() + "\nRoute with most stops: " + routes.getLast().toString() + "\nMost expensive route: " + findMostExpensiveRoute(routes).toString();
        System.out.println(format); //NOSONAR: No logger
    }

    static <T extends Person & Employed> void calculatePayForAllEmployees(List<T> people) {
        people.forEach(p -> System.out.println(p.calculatePay()));
    }
}
