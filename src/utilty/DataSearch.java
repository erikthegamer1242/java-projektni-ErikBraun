package utilty;

import entity.Route;

import java.math.BigDecimal;

public class DataSearch {

    private DataSearch() {}

    public static Route findRouteWithLeastStops(Route[] routes) {
        Integer leastStops = routes[0].getStopLength();
        Route route = routes[0];
        for (Route r: routes ) {
            if (r.getStopLength() < leastStops) {
                leastStops = r.getStopLength();
                route = r;
            }
        }
        return route;
    }

    public static Route findRouteWithMostStops(Route[] routes) {
        Integer mostStops = routes[0].getStopLength();
        Route route = routes[0];
        for (Route r: routes ) {
            if (r.getStopLength() > mostStops) {
                mostStops = r.getStopLength();
                route = r;
            }
        }
        return route;
    }

    public static Route findRouteWithDriverName(Route[] routes, String driverName) {
        for (Route r: routes ) {
            if (r.getDriver().getName().equals(driverName)) {
                return r;
            }
        }
        return null;
    }

    public static Route findMostExpensiveRoute(Route[] routes) {
        BigDecimal mostExpensiveRoute = routes[0].getStopCost();
        Route route = routes[0];
        for (Route r: routes ) {
            if (r.getStopCost().compareTo(mostExpensiveRoute) > 0) {
                mostExpensiveRoute = r.getStopCost();
                route = r;
            }
        }
        return route;
    }

    public static void showRouteStatistics(Route[] routes) {
        System.out.println("Total routes: " + routes.length);
        System.out.println("Route with least stops: ");
        findRouteWithLeastStops(routes).printRoute();
        System.out.println("Route with most stops: ");
        findRouteWithMostStops(routes).printRoute();
        System.out.println("Most expensive route: ");
        findMostExpensiveRoute(routes).printRoute();
    }
}
