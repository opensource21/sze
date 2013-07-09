// AvSvTyp.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.zeugnis;

/**
 * Unterscheidung in Arbeits- und in Sozialverhalten.
 * @author niels
 *
 */
public enum AvSvTyp {
    ARBEITSVERHALTEN(1, "AV"), SOZIALVERHALTEN(2, "SV");

    private final int id;
    private final String shortKey;

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

    public String getShortKey() {
        return shortKey;
    }
}
