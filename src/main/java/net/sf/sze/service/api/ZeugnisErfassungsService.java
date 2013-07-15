// ZeugnisErfassungsService.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Schulhalbjahr;

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
}
