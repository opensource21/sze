// AvSvNote.java
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
public enum AvSvNote {
    BESONDERE_ANERKENNUNG(1), ERWARTUNGEN_IM_VOLLEN_UMFANG(2),
            ERWARTUNGEN_ERFUELLT(3), ERWARTUNGEN_MIT_EINSCHRAENKUNGEN(4),
            ERWARTUNGEN_NICHT_ERFUELLT(5);

    final int id;

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
}
