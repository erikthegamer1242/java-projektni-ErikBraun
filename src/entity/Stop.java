package entity;

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
) {
    /**
     * Overriding toString to give out a formatted stop
     * @return Formatted string that better represents the record
     */
    @Override
    public String toString() {
        return ("Stop Location: " + location);
    }
}

