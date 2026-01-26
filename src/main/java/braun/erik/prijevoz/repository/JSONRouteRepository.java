package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of route repository using JSON as backend
 *
 * @author erik
 * @version 1.0
 */
public class JSONRouteRepository implements RouteRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/routes.json";

    private static class DataHolder {
        private static final List<Route> ROUTES = loadRoutes();

        @java.lang.SuppressWarnings({"squid:S2133"})
        private static List<Route> loadRoutes() {
            try {
                return JSONHelper.readListFromJSON(PATH, new ArrayList<Route>() {
                }.getClass());
            } catch (Exception e) {
                DialogUtil.showReadErrorDialog("routes");
                MainApp.logger.error("Cannot read JSON routes", e);
                return new ArrayList<>();
            }
        }
    }

    /**
     * Default constructor
     */
    public JSONRouteRepository() { /* Backwards compatibility before singleton */ }

    @Override
    public List<Route> get() {
        return DataHolder.ROUTES;
    }

    @Override
    public void set(List<Route> list) {
        DataHolder.ROUTES.addAll(list);
        try {
            JSONHelper.writeListToJSON(DataHolder.ROUTES, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("routes");
            MainApp.logger.error("Cannot write JSON routes", e);
        }
    }

    @Override
    public Route getLastInserted() {
        return DataHolder.ROUTES.getLast();
    }
}
