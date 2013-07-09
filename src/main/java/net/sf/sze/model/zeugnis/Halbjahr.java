// Halbjahr.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

/**
 * Aufzählungsklasse für Halbjahre.
 * @author niels
 *
 */
public enum Halbjahr {

    /** The Erstes_ halbjahr. */
    Erstes_Halbjahr(1),

    /** The Beide_ halbjahre. */
    Beide_Halbjahre(2);

    /** The id. */
    private final int id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    private Halbjahr(final int halbjahr) {
        this.id = halbjahr;
    }

    @Override
    public String toString() {
        switch (id) {
        case 1:
            return "1. Hj.";
        case 2:
            return "1.+2. Hj.";
        default:
            throw new IllegalStateException(
                    "Halbjahr kann nur 1 oder 2 sein nicht " + id);
        }
    }

    /**
     * Liefert den relativen Pfad.
     * @return der relative Pfad.
     */
    public String createRelativePathName() {
        switch (id) {
        case 1:
            return "Hj-1";
        case 2:
            return "Hj-2";
        default:
            throw new IllegalStateException(
                    "Halbjahr kann nur 1 oder 2 sein nicht " + id);
        }
    }
}
