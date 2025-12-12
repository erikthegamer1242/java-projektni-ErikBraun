package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.subclasses.Driver;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockDriverRepository implements DriverRepository {

    private final List<Driver> drivers = new ArrayList<>();

    public MockDriverRepository() {
        drivers.add(new Driver.DriverBuilder("0249156481", "Pero", "Peric", "81638", BigDecimal.valueOf(24), BigDecimal.valueOf(160)).email("pero@perotrans.hr").phoneNumber("08426126").dateOfBirth(LocalDate.of(2000, 12, 13)).build());

    }

    @Override
    public List<Driver> getDrivers() {
        return drivers;
    }
}
