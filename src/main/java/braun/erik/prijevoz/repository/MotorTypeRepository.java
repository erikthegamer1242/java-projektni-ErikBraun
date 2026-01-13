package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Vehicle;

import java.util.List;

/**
 * Typed interface for Vehicle.MotorType repository
 *
 * @author erik
 * @version 1.0
 */
public interface MotorTypeRepository extends Repository<Vehicle.MotorType> {
    @Override
    default List<Vehicle.MotorType> get() {
        return List.of(Vehicle.MotorType.values());
    }

    @Override
    default void set(List<Vehicle.MotorType> list) {
        throw new UnsupportedOperationException("This should never be called");
    }
}
