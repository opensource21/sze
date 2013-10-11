// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Schulfach;
import net.sf.sze.model.zeugnis.Schulhalbjahr;

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
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return eine Liste der möglichen Fächer.
     */
    List<Schulfach> getActiveSchulfaecher(Schulhalbjahr halbjahr, Klasse klasse);

}
