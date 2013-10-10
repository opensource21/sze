// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.Bewertung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @return alle Bewertungrn zu der Klasse des Schulhalbjahres.
     */
    Page<Bewertung> getBewertungen(long halbjahrId, long klassenId, Pageable pageable);

}
