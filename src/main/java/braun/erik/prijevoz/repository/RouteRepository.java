package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Route;

import java.util.List;

/**
 * Typed interface for route repository
 *
 * @author erik
 * @version 1.0
 */
public interface RouteRepository extends Repository<Route> {

    @Override
    public List<Route> get();

    @Override
    public void set(List<Route> list);
}
