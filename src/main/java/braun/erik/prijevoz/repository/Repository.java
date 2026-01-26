package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.DisplayOption;
import braun.erik.prijevoz.model.exceptions.DatabaseException;

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
     * Date of birth DB column name
     */
    static final String DOB_COLUMN = "dateofbirth";

    /**
     * Subscriber ID column name
     */
    static final String SID_COLUMN = "subscriberid";

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

    public abstract T getLastInserted() throws DatabaseException;
}
