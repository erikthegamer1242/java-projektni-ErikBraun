package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.repository.JSONRouteRepository;
import braun.erik.prijevoz.repository.Repository;

public class RouteSearchViewController extends SearchViewController<Route> {

    @Override
    protected Repository<Route> getRepository() {
        return new JSONRouteRepository();
    }

    @Override
    protected Class<Route> getEntityClass() {
        return Route.class;
    }
}
