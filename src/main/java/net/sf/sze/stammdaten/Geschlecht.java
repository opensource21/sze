// Geschlecht.java
//
// (c) SZE-Development-Team

package net.sf.sze.stammdaten;

/**
 * Das Geschlecht
 * @author niels
 *
 */
public enum Geschlecht {
    MAENNLICH('m'), WEIBLICH('w');

    final char id;

    Geschlecht(char id) {
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
