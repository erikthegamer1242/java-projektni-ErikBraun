package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.subclasses.Driver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockRouteRepository implements RouteRepository {

    private final List<Route> routes = new ArrayList<>();

    public MockRouteRepository() {
        DriverRepository driverRepository = new MockDriverRepository();
        VehicleRepository vehicleRepository = new MockVehicleRepository();
        StopRepository stopRepository = new MockStopRepository();
        routes.add(new Route(123, "Makraks", vehicleRepository.getVehicles().getFirst(), driverRepository.getDrivers().getFirst(), stopRepository.getStops(), BigDecimal.valueOf(100)));
    }

    @Override
    public List<Route> getRoutes() {
        return routes;
    }
}
