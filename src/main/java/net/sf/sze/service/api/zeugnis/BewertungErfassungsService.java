// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Schulfach;

/**
 * Service mit Diensten zum Erfassen von Bewertungen.
 *
 */
public interface BewertungErfassungsService {



    /**
     * Listet alle Bewertungen zu der Klasse des Schulhalbjahres, sortiert
     * nach Schulfach, Zeugnis, Note...
     * @param formular das ZeugnisFormular.
     * @param schulfachId die Id des Schulfachs.
     *
     * @return alle Bewertungrn zu der Klasse des Schulhalbjahres.
     */
    List<Bewertung> getSortedBewertungen(ZeugnisFormular formular, long schulfachId);


    /**
     * Liefert eine Bewertung zu der Bewertungs-Id so wie die Information zum
     * Vorgänger und Nachfolger, die Sortierung entspricht dabei der von
     * {@link BewertungErfassungsService#getSortedBewertungen(ZeugnisFormular, long)}.
     * @param formular das Zeugnisformular
     * @param schulfachId die Id des Schulfachs.
     * @param bewertungsId die Id der Bewertung.
     * @return die Bewertung mit Vorgänger und Nachfolger-Info.
     */
    BewertungWithNeigbors getBewertungWithNeighbors(ZeugnisFormular formular,
            Long schulfachId, Long bewertungsId);

    /**
     * Liefert die für die Klasse relevanten Schulfächer, sortiert nach Name.
     * @param formular das ZeignisFormular.
     * @return eine Liste der möglichen Fächer.
     */
    List<Schulfach> getActiveSchulfaecherOrderByName(ZeugnisFormular formular);

    /**
     * Liefert die Klasse zu der Id.
     * @param klassenId die Id der Klasse.
     * @return die Klasse.
     */
    Klasse getKlasse(long klassenId);


}
