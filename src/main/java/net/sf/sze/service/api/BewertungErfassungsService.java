// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import java.util.List;

import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Schulfach;

/**
 * Service mit Diensten zum Erfassen von Bewertungen.
 *
 */
public interface BewertungErfassungsService {



    /**
     * Listet alle Bewertungen zu der Klasse des Schulhalbjahres.
     *
     * @param halbjahrId die Id des Schulhalbjahres.
     * @param klassenId die Id der Klasse.
     * @param schulfachId die Id des Schulfachs.
     * @return alle Bewertungrn zu der Klasse des Schulhalbjahres.
     */
    List<Bewertung> getBewertungen(long halbjahrId, long klassenId, long schulfachId);

    /**
     * Liefert die für die Klasse relevanten Schulfächer.
     * @param halbjahrId die Id des Shculhalbjahres.
     * @param klassenId Die Id der Klasse.
     * @return eine Liste der möglichen Fächer.
     */
    List<Schulfach> getActiveSchulfaecher(long halbjahrId, long klassenId);

}
