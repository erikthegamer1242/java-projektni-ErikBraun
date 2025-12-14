package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.MockUserRepository;
import braun.erik.prijevoz.repository.Repository;

public class UserSearchViewController extends SearchViewController<User> {

    @Override
    public Repository<User> getRepository() {
        return new MockUserRepository();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
