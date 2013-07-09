// Schulfachtyp.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.zeugnis;

/**
 * Schulfächer kann man in verschiedene Gruppen einsortieren. Diese sind hier
 * definiert.
 * @author niels
 *
 */
public enum Schulfachtyp {
    KERNFACH(1, "KF"), ERGAENZUNGSFACH(2, "EF"), WAHLPFLICHT(3, "WP");

    final int id;
    final String shortKey;


    public String getShortKey() {
        return shortKey;
    }

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
