// Geschlecht.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.stammdaten;

/**
 * Das Geschlecht.
 *
 * @author niels
 */
public enum Geschlecht {

    /** The maennlich. */
    MAENNLICH('m'),

    /** The weiblich. */
    WEIBLICH('w');

    /** The id. */
    private final char id;

    /**
     *
     * Initiates an object of type Geschlecht.
     * @param id die Id
     */
    private Geschlecht(char id) {
        this.id = id;
    }

    @Override
    public String toString() {
        switch (id) {
        case 'm':
            return "männlich";
        case 'w':
            return "weiblich";
        default:
            throw new IllegalStateException("Geschlecht " + id
                    + " ist nicht zulässig.");
        }
    }
}
