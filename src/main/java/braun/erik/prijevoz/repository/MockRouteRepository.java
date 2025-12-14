package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Route;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MockRouteRepository implements RouteRepository {

    private final List<Route> routes = new ArrayList<>();

    public MockRouteRepository() {
        DriverRepository driverRepository = new MockDriverRepository();
        VehicleRepository vehicleRepository = new MockVehicleRepository();
        StopRepository stopRepository = new MockStopRepository();
        routes.add(new Route(123, "Makraks", vehicleRepository.get().getFirst(), driverRepository.get().getFirst(), stopRepository.get(), BigDecimal.valueOf(100)));
    }

    @Override
    public List<Route> get() {
        return routes;
    }

    @Override
    public void set(List<Route> list) {
        routes.addAll(list);
    }
}
