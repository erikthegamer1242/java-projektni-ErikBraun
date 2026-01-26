package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of vehicle repository using JSON as backend
 *
 * @author erik
 * @version 1.0
 */
public class JSONVehicleRepository implements VehicleRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/vehicles.json";

    private static class DataHolder {
        private static final List<Vehicle> VEHICLES = loadVehicles();

        @java.lang.SuppressWarnings({"squid:S2133"})
        private static List<Vehicle> loadVehicles() {
            try {
                return JSONHelper.readListFromJSON(PATH, new ArrayList<Vehicle>() {
                }.getClass());
            } catch (Exception e) {
                DialogUtil.showReadErrorDialog("vehicles");
                MainApp.logger.error("Cannot read JSON vehicles", e);
                return new ArrayList<>();
            }
        }
    }

    /**
     * Default constructor
     */
    public JSONVehicleRepository() { /* Backwards compatibility before singleton */ }

    @Override
    public List<Vehicle> get() {
        return DataHolder.VEHICLES;
    }

    @Override
    public void set(List<Vehicle> list) {
        DataHolder.VEHICLES.addAll(list);
        try {
            JSONHelper.writeListToJSON(DataHolder.VEHICLES, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("vehicles");
            MainApp.logger.error("Cannot write JSON vehicles", e);
        }
    }

    @Override
    public Vehicle getLastInserted() {
        return DataHolder.VEHICLES.getLast();
    }
}
