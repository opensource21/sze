// AvSvNote.java
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
public enum AvSvNote {

    /** The besondere anerkennung. */
    BESONDERE_ANERKENNUNG(1),

    /** The erwartungen im vollen umfang. */
    ERWARTUNGEN_IM_VOLLEN_UMFANG(2),

    /** The erwartungen erfuellt. */
    ERWARTUNGEN_ERFUELLT(3),

    /** The erwartungen mit einschraenkungen. */
    ERWARTUNGEN_MIT_EINSCHRAENKUNGEN(4),

    /** The erwartungen nicht erfuellt. */
    ERWARTUNGEN_NICHT_ERFUELLT(5);

    /** The id. */
    private final int id;

    /**
     *
     * Initiates an object of type AvSvNote.
     * @param id die ID der Note.
     */
    private AvSvNote(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        switch (id) {
        case 1:
            return "A - verdient besondere Anerkennung";
        case 2:
            return "B - entspricht den Erwartungen in vollem Umfang";
        case 3:
            return "C - entspricht den Erwartungen";
        case 4:
            return "D - entspricht den Erwartungen mit Einschränkungen";
        case 5:
            return "E - entspricht nicht den Erwartungen";
        default:
            throw new IllegalStateException("AvSvNote " + id
                    + " ist nicht zulässig.");
        }
    }

    /**
     * Gets the buchstabe.
     *
     * @return the buchstabe
     */
    public String getBuchstabe() {
        switch (id) {
        case 1:
            return "A";
        case 2:
            return "B";
        case 3:
            return "C";
        case 4:
            return "D";
        case 5:
            return "E";
        default:
            throw new IllegalStateException("AvSvNote " + id
                    + " ist nicht zulässig.");
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
