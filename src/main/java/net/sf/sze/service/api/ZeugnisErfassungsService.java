// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;

import java.util.List;

/**
 * Service mit Diensten zum Erfassen von Zeugnisse.
 *
 */
public interface ZeugnisErfassungsService {

    /**
     * Liefert eine Liste mit allen auf aktive gesetzten Schulhalbjahren.
     * @return eine Liste mit allen auf aktive gesetzten Schulhalbjahren.
     */
    List<Schulhalbjahr> getActiveSchulhalbjahre();

    /**
     * Liefert eine Liste mit alle möglichen Klassen zu den aktiven Schulhalbjahren.
     * @param acticeSchulhalbjahre aktive Schulhalbjahren.
     * @return eine Liste mit alle möglichen Klassen zu den aktiven Schulhalbjahren.
     */
    List<Klasse> getActiveKlassen(List<Schulhalbjahr> acticeSchulhalbjahre);

    /**
     * Listet alle Zeugnisse zu der Klasse des selektierbaren Schulhalbjahres.
     *
     * @param halbjahrId die Id des Schulhalbjahres.
     * @param klassenId die Id der Klasse.
     * @return alle Zeugnisse zu der Klasse des Schulhalbjahres.
     */
    List<Zeugnis> getZeugnisse(long halbjahrId, long klassenId);
}
