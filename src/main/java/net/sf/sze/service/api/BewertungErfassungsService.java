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
     * Listet alle Bewertungen zu der Klasse des Schulhalbjahres, sortiert
     * nach Schulfach, Zeugnis, Note...
     *
     * @param halbjahrId die Id des Schulhalbjahres.
     * @param klassenId die Id der Klasse.
     * @param schulfachId die Id des Schulfachs.
     * @return alle Bewertungrn zu der Klasse des Schulhalbjahres.
     */
    List<Bewertung> getSortedBewertungen(long halbjahrId, long klassenId, long schulfachId);


    /**
     * Liefert eine Bewertung zu der Bewertungs-Id so wie die Information zum
     * Vorgänger und Nachfolger, die Sortierung entspricht dabei der von
     * {@link BewertungErfassungsService#getSortedBewertungen(long, long, long)}.
     * @param halbjahrId die Id des Schulhalbjahres.
     * @param klassenId die Id der Klasse.
     * @param schulfachId die Id des Schulfachs.
     * @param bewertungsId die Id der Bewertung.
     * @return die Bewertung mit Vorgänger und Nachfolger-Info.
     */
    BewertungWithNeigbors getBewertungWithNeighbors(Long halbjahrId,
            Long klassenId, Long schulfachId, Long bewertungsId);

    /**
     * Liefert die für die Klasse relevanten Schulfächer, sortiert nach Name.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return eine Liste der möglichen Fächer.
     */
    List<Schulfach> getActiveSchulfaecherOrderByName(Schulhalbjahr halbjahr, Klasse klasse);

    /**
     * Liefert die Klasse zu der Id.
     * @param klassenId die Id der Klasse.
     * @return die Klasse.
     */
    Klasse getKlasse(long klassenId);

    /**
     * Das Schulhalbjahr.
     * @param schulhalbjahrId die Id des gewünschten Schulhalbjahrs.
     * @return das Schulhalbjahr.
     */
    Schulhalbjahr getSchulhalbjahr(long schulhalbjahrId);




}
