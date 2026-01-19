package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.exceptions.DatabaseException;
import braun.erik.prijevoz.model.subclasses.User;

import java.util.List;

/**
 * Typed interface for user repository
 *
 * @author erik
 * @version 1.0
 */
public interface UserRepository extends Repository<User> {

    @Override
    public List<User> get() throws DatabaseException;

    @Override
    public void set(List<User> list) throws DatabaseException;
}
