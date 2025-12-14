package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.MainApp;
import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.model.subclasses.User;
import braun.erik.prijevoz.repository.util.JSONHelper;
import braun.erik.prijevoz.util.DialogUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JSONUserRepository implements UserRepository {

    private static final String PATH = "src/main/resources/braun/erik/prijevoz/data/drivers.json";
    private List<User> users;

    @java.lang.SuppressWarnings({"squid:S2133"})
    public JSONUserRepository() {
        try {
            users = JSONHelper.readListFromJSON(PATH, new ArrayList<User>() {
            }.getClass());
        } catch (Exception e) {
            DialogUtil.showReadErrorDialog("users");
            MainApp.logger.error("Cannot read JSON users", e);
        }
    }

    @Override
    public List<User> get() {
        return users;
    }

    @Override
    public void set(List<User> list) {
        users.addAll(list);
        try {
            JSONHelper.writeListToJSON(users, PATH);
        } catch (Exception e) {
            DialogUtil.showWriteErrorDialog("users");
            MainApp.logger.error("Cannot write JSON users", e);
        }
    }
}
