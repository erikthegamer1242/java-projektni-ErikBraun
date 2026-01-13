package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.DisplayOption;

import java.util.List;

/**
 * Abstract interface used for all entity types
 *
 * @param <T> type of entity
 * @author erik
 * @version 1.0
 */
public abstract interface Repository<T extends DisplayOption> {
    /**
     * Gets data from repository
     * @return list of data
     */
    public abstract List<T> get();

    /**
     * Sets data to repository
     * @param list list of data to be added
     */
    public abstract void set(List<T> list);
}
