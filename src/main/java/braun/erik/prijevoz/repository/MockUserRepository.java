package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.subclasses.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockUserRepository implements UserRepository {
    private final List<User> users;

    public MockUserRepository() {
        users = new ArrayList<>();
        users.add(new User.UserBuilder("0249156481", "Ivan", "Ivic").email("ivan.ivic@gmail.com").phoneNumber("08426126").dateOfBirth(LocalDate.of(2000, 12, 13)).build());
    }
    @Override
    public List<User> get() {
        return users;
    }

    @Override
    public void set(List<User> list) {
        users.addAll(list);
    }
}
