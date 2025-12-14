package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Stop;

import java.util.List;

public interface StopRepository extends Repository<Stop> {

    @Override
    public List<Stop> get();

    @Override
    public void set(List<Stop> list);
}
