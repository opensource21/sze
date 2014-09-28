// AvSvTyp.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.model.zeugnisconfig;

/**
 * Unterscheidung in Arbeits- und in Sozialverhalten.
 * @author niels
 *
 */
public enum AvSvTyp {

    /**
     * Arbeitsverhalten.
     */
    ARBEITSVERHALTEN(1, "AV"),

    /** Sozialverhalten. */
    SOZIALVERHALTEN(2, "SV");

    /**
     * Die Id.
     */
    private final int id;

    /**
     * Schlüssel als Kurzform.
     */
    private final String shortKey;

    /**
     * Initiates an object of type AvSvTyp.
     * @param id die Id
     * @param shortKey der Kurzschlüssel.
     */
    private AvSvTyp(int id, String shortKey) {
        this.id = id;
        this.shortKey = shortKey;
    }

    @Override
    public String toString() {
        switch (id) {
        case 1:
            return "Arbeitsverhalten";
        case 2:
            return "Sozialverhalten";
        default:
            throw new IllegalStateException("AvSvTyp " + id
                    + " ist nicht zulässig.");
        }
    }

    /**
     * Liefert den Schlüssel in Kurzform.
     * @return den Schlüssel in Kurzform.
     */
    public String getShortKey() {
        return shortKey;
    }
}
