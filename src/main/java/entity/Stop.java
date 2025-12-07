package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Record used for storing a stop
 * @param id Unique stop identifier
 * @param location String representing the address/name
 * @author erik
 * @version 1.0
 */
public record Stop (
        Integer id,
        String location
) implements Serializable {
    /**
     * Overriding toString to give out a formatted stop
     * @return Formatted string that better represents the record
     */
    @Override
    public String toString() {
        return ("Stop Location: " + location);
    }

    /**
     * Overriding equals to return proper matching for custom class.
     *
     * @param o the reference object with which to compare.
     * @return true if the object are equal false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stop(Integer id1, String location1))) return false;
        return Objects.equals(id, id1) && Objects.equals(location, location1);
    }

    /**
     * Overriding hashCode to return proper hash for custom class.
     *
     * @return int hash of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}

