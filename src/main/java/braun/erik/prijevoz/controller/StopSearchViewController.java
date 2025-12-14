package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.repository.MockStopRepository;
import braun.erik.prijevoz.repository.Repository;

public class StopSearchViewController extends SearchViewController<Stop> {

    @Override
    protected Repository<Stop> getRepository() {
        return new MockStopRepository();
    }

    @Override
    protected Class<Stop> getEntityClass() {
        return Stop.class;
    }
}
