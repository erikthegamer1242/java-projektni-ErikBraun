package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.JSONUserRepository;
import braun.erik.prijevoz.repository.Repository;

/**
 * Typed class of search view controller of type user
 *
 * @author erik
 * @version 1.0
 */
public class UserSearchViewController extends SearchViewController<User> {

    /**
     * Default constructor.
     */
    public UserSearchViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    public Repository<User> getRepository() {
        return new JSONUserRepository();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
