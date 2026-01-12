package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.Stop;
import braun.erik.prijevoz.repository.JSONStopRepository;
import braun.erik.prijevoz.repository.Repository;

public class StopAddViewController extends AddViewController<Stop> {

    @Override
    protected Repository<Stop> getRepository() {
        return new JSONStopRepository();
    }

    @Override
    protected Class<Stop> getEntityClass() {
        return Stop.class;
    }

    @Override
    protected void addToRepository() {
        System.out.println("StopAddViewController.addToRepository");
    }
}
