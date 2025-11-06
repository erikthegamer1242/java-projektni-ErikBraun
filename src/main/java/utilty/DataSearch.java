package utilty;

import entity.Route;
import entity.subclasses.User;
import entity.superclasses.Person;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Static interface implementing methods that search and do statistical analysis of the data
 * @author erik
 * @version 1.0
 */

public interface DataSearch {

    /**
     * Finds a route that has the least amount of stops
     * @param routes a list of routes
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithLeastStops(List<Route> routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
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
     * @param routes a list of routes
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithMostStops(List<Route> routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
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
     * @param routes a list of routes
     * @param driverName String name of the driver
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithDriverName(List<Route> routes, String driverName) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        if (driverName == null) {
            throw new NullPointerException("driverName must not be null");
        }
        for (Route r : routes) {
            if (r.getDriver().getName().equals(driverName)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Finds a route that has any person's name
     * @param persons a list of people
     * @param name String name of the driver
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Person findPersonByName(List<Person> persons, String name) {
        if (persons == null) {
            throw new NullPointerException("persons must not be null");
        }
        if (name == null) {
            throw new NullPointerException("name must not be null");
        }
        Comparator<Person> comparator = Comparator.comparing(Person::getName);

        Integer idx = Collections.binarySearch(persons, new User.UserBuilder("1", name, "1").build(), comparator);
        if (idx < 0) {
            return null;
        }
        return persons.get(idx);
    }

    /**
     * Finds the most expensive route based on the stop cost and number of stops
     * @param routes a list of people
     * @return The most expensive route
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findMostExpensiveRoute(List<Route> routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
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
     * @param routes a list of routes
     * @throws NullPointerException when one or more parameters are null
     */
    static void showRouteStatistics(List<Route> routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        String format = "Total routes: " + routes.size()
                + "\nRoute with least stops: " + routes.getFirst().toString()
                + "\nRoute with most stops: " + routes.getLast().toString()
                + "\nMost expensive route: " + findMostExpensiveRoute(routes).toString();
        System.out.println(format); //NOSONAR: No logger
    }
}
