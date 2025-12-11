package braun.erik.prijevoz.model.subclasses;

import braun.erik.prijevoz.model.superclasses.Person;

import java.util.List;

/**
 * Adds methods only available to administrators
 * @author erik
 * @version 1.0
 */

public sealed interface AdministratorActions permits Administrator {

    /**
     * Add a person, or it's subclass to an existing list
     * @param people list to be added to
     * @param person object to add
     */
    default <T extends Person> void addPersonToList(List<? super T> people, T person) {
        people.add(person);
    }

    /**
     * Remove a person, or it's subclass from an existing list
     * @param people list to be removed from
     * @param person object to remove
     */
    default <T extends Person> void removePersonFromList(List<? super T> people, T person) {
        people.remove(person);
    }
}
