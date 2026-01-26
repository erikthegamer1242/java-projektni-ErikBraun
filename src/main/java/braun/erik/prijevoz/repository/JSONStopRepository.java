package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of stop repository using JSON as backend
 *
 * @author erik
 * @version 1.0
 */
public class JSONStopRepository implements StopRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/stops.json";

    private static class DataHolder {
        private static final List<Stop> STOPS = loadStops();

        @java.lang.SuppressWarnings({"squid:S2133"})
        private static List<Stop> loadStops() {
            try {
                return JSONHelper.readListFromJSON(PATH, new ArrayList<Stop>() {
                }.getClass());
            } catch (Exception e) {
                DialogUtil.showReadErrorDialog("stops");
                MainApp.logger.error("Cannot read JSON stops", e);
                return new ArrayList<>();
            }
        }
    }

    /**
     * Default constructor
     */
    public JSONStopRepository() { /* Backwards compatibility before singleton */ }

    @Override
    public List<Stop> get() {
        return DataHolder.STOPS;
    }

    @Override
    public void set(List<Stop> list) {
        DataHolder.STOPS.addAll(list);
        try {
            JSONHelper.writeListToJSON(DataHolder.STOPS, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("stops");
            MainApp.logger.error("Cannot write JSON stops", e);
        }
    }

    @Override
    public Stop getLastInserted() {
        return DataHolder.STOPS.getLast();
    }
}
