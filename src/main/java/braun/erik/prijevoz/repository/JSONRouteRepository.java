package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class JSONRouteRepository implements RouteRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/routes.json";
    private List<Route> routes;

    @java.lang.SuppressWarnings({"squid:S2133"})
    public JSONRouteRepository() {
        try {
            routes = JSONHelper.readListFromJSON(PATH, new ArrayList<Route>() {
            }.getClass());
        } catch (Exception e) {
            DialogUtil.showReadErrorDialog("routes");
            MainApp.logger.error("Cannot read JSON routes", e);
        }
    }

    @Override
    public List<Route> get() {
        return routes;
    }

    @Override
    public void set(List<Route> list) {
        routes.addAll(list);
        try {
            JSONHelper.writeListToJSON(routes, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("routes");
            MainApp.logger.error("Cannot write JSON routes", e);
        }
    }
}
