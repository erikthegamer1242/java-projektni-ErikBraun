package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.repository.JSONStopRepository;
import braun.erik.prijevoz.repository.Repository;

/**
 * Typed class of search view controller of type stop
 *
 * @author erik
 * @version 1.0
 */
public class StopSearchViewController extends SearchViewController<Stop> {

    /**
     * Default constructor.
     */
    public StopSearchViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Stop> getRepository() {
        return new JSONStopRepository();
    }

    @Override
    protected Class<Stop> getEntityClass() {
        return Stop.class;
    }
}
