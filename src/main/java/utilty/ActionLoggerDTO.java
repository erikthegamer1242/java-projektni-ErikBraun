package utilty;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class used to store user actions for logging
 * @author erik
 * @version 1.0
 */
@XmlRootElement(name="actions")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionLoggerDTO{

    @XmlElement(name="action")
    private List<String> action = new ArrayList<>();

    /**
     * No-args constructor used for XML
     */
    public ActionLoggerDTO(){}

    /**
     * Constructs an instance with default actions
     * @param action List<String> actions
     */
    public ActionLoggerDTO(List<String> action) {
        this.action = action;
    }

    /**
     * Get the whole action list
     * @return List<String> actions
     */
    public List<String> getAction() {
        return action;
    }

    /**
     * Set a whole list of actions
     * @param action List<String> actions
     */
    public void setAction(List<String> action) {
        this.action = action;
    }

    /**
     * Add one action
     * @param action String action
     */
    public void addAction(String action) {
        this.action.add(action);
    }
}
