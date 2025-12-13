package braun.erik.prijevoz.repository;

import braun.erik.prijevoz.model.Stop;

import java.util.ArrayList;
import java.util.List;

public class MockStopRepository implements StopRepository {

    private final List<Stop> stops = new ArrayList<>();

    public MockStopRepository() {
        stops.add(new Stop(1, "Caviceva"));
        stops.add(new Stop(2, "Peroviceva"));
        stops.add(new Stop(3, "Mirkoviceva"));
    }

    @Override
    public List<Stop> getStops() {
        return stops;
    }
}
