package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.model.subclasses.User;

import java.util.Map;

public final class RepositoryLookup {

    private RepositoryLookup() {
    }

    private static final Map<Class<?>, Repository<?>> repositories = Map.of(
            Driver.class, new JSONDriverRepository(),
            Route.class, new JSONRouteRepository(),
            Stop.class, new JSONStopRepository(),
            User.class, new JSONUserRepository(),
            Vehicle.class, new JSONVehicleRepository(),
            Vehicle.MotorType.class, new MotorTypeRepository() {
            }
    );

    @SuppressWarnings("unchecked")
    public static <T extends DisplayOption> Repository<T> getRepository(Class<?> entityClass) {
        return (Repository<T>) repositories.get(entityClass);
    }
}