// Schulfachtyp.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.model.zeugnis;

/**
 * Schulfächer kann man in verschiedene Gruppen einsortieren. Diese sind hier
 * definiert.
 * @author niels
 *
 */
public enum Schulfachtyp {

    /** The kernfach. */
    KERNFACH(1, "KF"),

    /** The ergaenzungsfach. */
    ERGAENZUNGSFACH(2, "EF"),

    /** The wahlpflicht. */
    WAHLPFLICHT(3, "WP");

    /** The id. */
    private final int id;

    /** The short key. */
    private final String shortKey;

    /**
     * Gets the short key.
     *
     * @return the short key
     */
    public String getShortKey() {
        return shortKey;
    }

    /**
     *
     * Initiates an object of type Schulfachtyp.
     * @param id die Id
     * @param shortKey der Kurzschlüssel.
     */
    private Schulfachtyp(final int id, final String shortKey) {
        this.id = id;
        this.shortKey = shortKey;
    }

    @Override
    public String toString() {
        switch (id) {
        case 1:
            return "Kernfach";
        case 2:
            return "Ergänzungsfach";
        case 3:
            return "Wahlpflicht";
        default:
            throw new IllegalStateException("Schulfachtype " + id
                    + " ist nicht zulässig.");
        }
    }
}
