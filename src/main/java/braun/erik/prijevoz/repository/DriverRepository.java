package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.subclasses.Driver;

import java.util.List;

public interface DriverRepository extends Repository<Driver> {

    @Override
    List<Driver> get();

    @Override
    void set(List<Driver> list);
}
