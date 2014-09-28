// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.stammdaten;

import net.sf.sze.model.stammdaten.Klasse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Klasse}.
 *
 * @author niels
 *
 */
public interface KlasseService {

    /**
     * Deliver a page of Klassee.
     *
     * @param page information about pagination.
     * @return the page of Klassee.
     */
    Page<Klasse> getKlasse(Pageable page);

    /**
     * Save the given klasse.
     *
     * @param klasse the klasse object.
     * @return the klasse object which may changed.
     *
     */
    Klasse save(Klasse klasse);

    /**
     * Read the klasse.
     *
     * @param klasseId the ID of the klasse object.
     * @return the klasse object.
     *
     */
    Klasse read(Long klasseId);

    /**
     * Delete the klasse.
     *
     * @param klasseId the ID of the klasse object.
     *
     */
    void delete(Long klasseId);


}
