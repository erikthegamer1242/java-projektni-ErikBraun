package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of user repository using JSON as backend
 *
 * @author erik
 * @version 1.0
 */
public class JSONUserRepository implements UserRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/users.json";

    private static class DataHolder {
        private static final List<User> USERS = loadUsers();

        @java.lang.SuppressWarnings({"squid:S2133"})
        private static List<User> loadUsers() {
            try {
                return JSONHelper.readListFromJSON(PATH, new ArrayList<User>() {
                }.getClass());
            } catch (Exception e) {
                DialogUtil.showReadErrorDialog("users");
                MainApp.logger.error("Cannot read JSON users", e);
                return new ArrayList<>();
            }
        }
    }

    /**
     * Default constructor
     */
    public JSONUserRepository() { /* Backwards compatibility before singleton */ }

    @Override
    public List<User> get() {
        return DataHolder.USERS;
    }

    @Override
    public void set(List<User> list) {
        DataHolder.USERS.addAll(list);
        try {
            JSONHelper.writeListToJSON(DataHolder.USERS, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("users");
            MainApp.logger.error("Cannot write JSON users", e);
        }
    }
}
