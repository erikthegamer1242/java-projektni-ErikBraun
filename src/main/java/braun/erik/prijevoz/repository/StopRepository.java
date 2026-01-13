package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Stop;

import java.util.List;

/**
 * Typed interface for stop repository
 *
 * @author erik
 * @version 1.0
 */
public interface StopRepository extends Repository<Stop> {

    @Override
    public List<Stop> get();

    @Override
    public void set(List<Stop> list);
}
