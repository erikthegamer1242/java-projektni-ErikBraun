package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.model.subclasses.User;

import java.util.Map;

/**
 * Simple class used to return a repository based on entity class
 */
public final class RepositoryLookup {

    /**
     * Default constructor.
     */
    private RepositoryLookup() {
        // intentionally empty to remove Javadoc warning
    }

    /**
     * Map of repositories where the key is the entity class type
     */
    private static final Map<Class<?>, Repository<?>> repositories = Map.of(
            Driver.class, new DBDriverRepository(),
            Route.class, new JSONRouteRepository(),
            Stop.class, new JSONStopRepository(),
            User.class, new DBUserRepository(),
            Vehicle.class, new DBVehicleRepository(),
            Vehicle.MotorType.class, new MotorTypeRepository() {
            }
    );

    /**
     * Get a repository based on the class type
     *
     * @param entityClass entity class type
     * @param <T>         entity type
     * @return repository of entity type
     */
    @SuppressWarnings("unchecked")
    public static <T extends DisplayOption> Repository<T> getRepository(Class<?> entityClass) {
        return (Repository<T>) repositories.get(entityClass);
    }
}