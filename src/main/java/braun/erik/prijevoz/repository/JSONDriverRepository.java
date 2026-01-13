package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of driver repository using JSON as backend
 *
 * @author erik
 * @version 1.0
 */
public class JSONDriverRepository implements DriverRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/drivers.json";

    private static class DataHolder {
        private static final List<Driver> DRIVERS = loadDrivers();

        @java.lang.SuppressWarnings({"squid:S2133"})
        private static List<Driver> loadDrivers() {
            try {
                return JSONHelper.readListFromJSON(PATH, new ArrayList<Driver>() {
                }.getClass());
            } catch (Exception e) {
                DialogUtil.showReadErrorDialog("drivers");
                MainApp.logger.error("Cannot read JSON drivers", e);
                return new ArrayList<>();
            }
        }
    }

    /**
     * Default constructor.
     */
    public JSONDriverRepository() { /* Backwards compatibility before singleton */ }

    @Override
    public List<Driver> get() {
        return DataHolder.DRIVERS;
    }

    @Override
    public void set(List<Driver> list) {
        DataHolder.DRIVERS.addAll(list);
        try {
            JSONHelper.writeListToJSON(DataHolder.DRIVERS, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("drivers");
            MainApp.logger.error("Cannot write JSON drivers", e);
        }
    }
}
