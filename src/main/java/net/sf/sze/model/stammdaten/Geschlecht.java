// Geschlecht.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
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
    private final Character id;


    /**
     *
     * Initiates an object of type Geschlecht.
     * @param id die Id
     */
    private Geschlecht(char id) {
        this.id = Character.valueOf(id);
    }

    /**
     * Liefert den Typ zu der Id.
     * @param id die Id des Typen.
     * @return den Typ zu der Id.
     */
    public static Geschlecht getType(Character id) {
        if (id == null) {
            return null;
        }
        switch (id.charValue()) {
        case 'm':
            return MAENNLICH;
        case 'w':
            return WEIBLICH;
        default:
            throw new IllegalStateException("Geschlecht " + id
                    + " ist nicht zulässig.");
        }
    }

    /**
     * Returns the identifier.
     * @return the identifier.
     */
    public Character getId() {
        return id;
    }


    @Override
    public String toString() {
        switch (id.charValue()) {
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
