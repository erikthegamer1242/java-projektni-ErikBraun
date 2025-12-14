package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Route;

import java.util.List;

public interface RouteRepository extends Repository<Route> {

    @Override
    public List<Route> get();

    @Override
    public void set(List<Route> list);

}
