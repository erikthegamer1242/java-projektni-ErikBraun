package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.subclasses.User;

import java.util.List;

public interface UserRepository extends Repository<User> {

    @Override
    public List<User> get();

    @Override
    public void set(List<User> list);
}
