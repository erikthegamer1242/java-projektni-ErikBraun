package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class JSONDriverRepository implements DriverRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/drivers.json";
    private List<Driver> drivers;

    @java.lang.SuppressWarnings({"squid:S2133"})
    public JSONDriverRepository() {
        try {
            drivers = JSONHelper.readListFromJSON(PATH, new ArrayList<Driver>() {
            }.getClass());
        } catch (Exception e) {
            DialogUtil.showReadErrorDialog("drivers");
            MainApp.logger.error("Cannot read JSON drivers", e);
        }
    }

    @Override
    public List<Driver> get() {
        return drivers;
    }

    @Override
    public void set(List<Driver> list) {
        drivers.addAll(list);
        try {
            JSONHelper.writeListToJSON(drivers, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("drivers");
            MainApp.logger.error("Cannot write JSON drivers", e);
        }
    }
}
