package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.controller.util.NodeProcessorUtil;
import braun.erik.prijevoz.model.Route;
import braun.erik.prijevoz.model.exceptions.FieldArgumentException;
import braun.erik.prijevoz.repository.DBRouteRepository;
import braun.erik.prijevoz.repository.Repository;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.List;

/**
 * Typed class of add view controller of type route
 *
 * @author erik
 * @version 1.0
 */
public class RouteAddViewController extends AddViewController<Route> {

    /**
     * Default constructor.
     */
    public RouteAddViewController() {
        // intentionally empty to remove Javadoc warning
    }

    @Override
    protected Repository<Route> getRepository() {
        return new DBRouteRepository();
    }

    @Override
    protected Class<Route> getEntityClass() {
        return Route.class;
    }

    @Override
    protected void addToRepository() throws FieldArgumentException {
        Route route = new Route();
        route.setId(r.nextInt(BOUND));
        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();

        for (var node : gridPaneChildren) {
            NodeProcessorUtil.processNode(node, route);
        }
        getRepository().set(List.of(route));
    }
}
