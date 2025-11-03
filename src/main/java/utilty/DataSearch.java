package utilty;

import entity.Route;
import entity.superclasses.Person;

import java.math.BigDecimal;

/**
 * Static interface implementing methods that search and do statistical analysis of the data
 * @author erik
 * @version 1.0
 */

public interface DataSearch {

    /**
     * Finds a route that has the least amount of stops
     * @param routes an array of routes
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithLeastStops(Route[] routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        Integer leastStops = routes[0].getStopLength();
        Route route = routes[0];
        for (Route r : routes) {
            if (r.getStopLength() < leastStops) {
                leastStops = r.getStopLength();
                route = r;
            }
        }
        return route;
    }

    /**
     * Finds a route that has the most amount of stops
     * @param routes an array of routes
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithMostStops(Route[] routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        Integer mostStops = routes[0].getStopLength();
        Route route = routes[0];
        for (Route r : routes) {
            if (r.getStopLength() > mostStops) {
                mostStops = r.getStopLength();
                route = r;
            }
        }
        return route;
    }

    /**
     * Finds a route that has the driver's name in it
     * @param routes an array of routes
     * @param driverName String name of the driver
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findRouteWithDriverName(Route[] routes, String driverName) {
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
     * @param persons an array of people
     * @param name String name of the driver
     * @return If found returns the route, else null
     * @throws NullPointerException when one or more parameters are null
     */
    static Person findPersonByName(Person[] persons, String name) {
        if (persons == null) {
            throw new NullPointerException("persons must not be null");
        }
        if (name == null) {
            throw new NullPointerException("name must not be null");
        }
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds the most expensive route based on the stop cost and number of stops
     * @param routes an array of people
     * @return The most expensive route
     * @throws NullPointerException when one or more parameters are null
     */
    static Route findMostExpensiveRoute(Route[] routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        BigDecimal mostExpensiveRoute = routes[0].getStopCost();
        Route route = routes[0];
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
     * @param routes an array of routes
     * @throws NullPointerException when one or more parameters are null
     */
    static void showRouteStatistics(Route[] routes) {
        if (routes == null) {
            throw new NullPointerException("routes must not be null");
        }
        String format = "Total routes: " + routes.length
                + "\nRoute with least stops: " + findRouteWithLeastStops(routes).toString()
                + "\nRoute with most stops: " + findRouteWithMostStops(routes).toString()
                + "\nMost expensive route: " + findMostExpensiveRoute(routes).toString();
        System.out.println(format); //NOSONAR: No logger
    }
}
