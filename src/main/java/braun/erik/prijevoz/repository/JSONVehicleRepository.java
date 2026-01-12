package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Vehicle;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class JSONVehicleRepository implements VehicleRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/vehicles.json";
    private List<Vehicle> vehicles;

    @java.lang.SuppressWarnings({"squid:S2133"})
    public JSONVehicleRepository() {
        try {
            vehicles = JSONHelper.readListFromJSON(PATH, new ArrayList<Vehicle>() {
            }.getClass());
        } catch (Exception e) {
            DialogUtil.showReadErrorDialog("vehicles");
            MainApp.logger.error("Cannot read JSON vehicles", e);
        }

    }

    @Override
    public List<Vehicle> get() {
        return vehicles;
    }

    @Override
    public void set(List<Vehicle> list) {
        vehicles.addAll(list);
        try {
            JSONHelper.writeListToJSON(vehicles, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("vehicles");
            MainApp.logger.error("Cannot write JSON vehicles", e);
        }
    }
}
