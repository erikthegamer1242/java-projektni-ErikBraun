package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.repository.DBRouteRepository;
import braun.erik.prijevoz.repository.Repository;

/**
 * Typed class of search view controller of type route
 *
 * @author erik
 * @version 1.0
 */
public class RouteSearchViewController extends SearchViewController<Route> {

    /**
     * Default constructor.
     */
    public RouteSearchViewController() {
        // intentionally empty to remove Javadoc warning
    }
    @Override
    protected Repository<Route> getRepository() {
        return new DBRouteRepository();
    }

    @Override
    protected Class<Route> getEntityClass() {
        return Route.class;
    }
}
