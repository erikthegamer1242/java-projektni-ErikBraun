package braun.erik.prijevoz.repository;

import java.util.List;

public abstract interface Repository<T> {
    public abstract List<T> get();
    public abstract void set(List<T> list);
}
