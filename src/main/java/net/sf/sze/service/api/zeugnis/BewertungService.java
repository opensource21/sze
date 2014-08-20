// bewertungen.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import net.sf.sze.model.zeugnis.Bewertung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Bewertung}.
 *
 * @author niels
 *
 */
public interface BewertungService {

    /**
     * Deliver a page of bewertungen.
     *
     * @param page information about pagination.
     * @return the page of bewertungen.
     */
    Page<Bewertung> getBewertung(Pageable page);

    /**
     * Save the given bewertung.
     *
     * @param bewertung the bewertung object.
     * @return the bewertung object which may changed.
     *
     */
    Bewertung save(Bewertung bewertung);

    /**
     * Read the bewertung.
     *
     * @param bewertungId the ID of the bewertung object.
     * @return the bewertung object.
     *
     */
    Bewertung read(Long bewertungId);

    /**
     * Delete the bewertung.
     *
     * @param bewertungId the ID of the bewertung object.
     *
     */
    void delete(Long bewertungId);


}
