package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.DisplayOption;

import java.util.List;

public abstract interface Repository<T extends DisplayOption> {
    public abstract List<T> get();
    public abstract void set(List<T> list);
}
