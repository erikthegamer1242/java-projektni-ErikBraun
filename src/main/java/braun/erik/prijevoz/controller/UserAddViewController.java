package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.JSONUserRepository;
import braun.erik.prijevoz.repository.Repository;

public class UserAddViewController extends AddViewController<User> {

    @Override
    public Repository<User> getRepository() {
        return new JSONUserRepository();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected void addToRepository() {
        System.out.println("UserAddViewController.addToRepository");
    }
}
