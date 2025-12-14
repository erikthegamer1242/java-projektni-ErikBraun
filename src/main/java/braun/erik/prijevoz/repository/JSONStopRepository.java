package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class JSONStopRepository implements StopRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/drivers.json";
    private List<Stop> stops;

    @java.lang.SuppressWarnings({"squid:S2133"})
    public JSONStopRepository() {
        try {
            stops = JSONHelper.readListFromJSON(PATH, new ArrayList<Stop>() {
            }.getClass());
        } catch (Exception e) {
            DialogUtil.showReadErrorDialog("stops");
            MainApp.logger.error("Cannot read JSON stops", e);
        }
    }

    @Override
    public List<Stop> get() {
        return stops;
    }

    @Override
    public void set(List<Stop> list) {
        stops.addAll(list);
        try {
            JSONHelper.writeListToJSON(stops, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("stops");
            MainApp.logger.error("Cannot write JSON stops", e);
        }
    }
}
