// agBewertungen.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import net.sf.sze.model.zeugnis.AgBewertung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link AgBewertung}.
 *
 * @author niels
 *
 */
public interface AgBewertungService {

    /**
     * Deliver a page of agBewertungen.
     *
     * @param page information about pagination.
     * @return the page of agBewertungen.
     */
    Page<AgBewertung> getBewertung(Pageable page);

    /**
     * Save the given agBewertung.
     *
     * @param agBewertung the agBewertung object.
     * @return the agBewertung object which may changed.
     *
     */
    AgBewertung save(AgBewertung agBewertung);

    /**
     * Save the given agBewertung.
     *
     * @param agBewertungen the agBewertung object.
     * @return the agBewertung object which may changed.
     *
     */
    Iterable<AgBewertung> save(Iterable<AgBewertung> agBewertungen);

    /**
     * Read the agBewertung.
     *
     * @param agBewertungId the ID of the agBewertung object.
     * @return the agBewertung object.
     *
     */
    AgBewertung read(Long agBewertungId);

    /**
     * Delete the agBewertung.
     *
     * @param agBewertungId the ID of the agBewertung object.
     *
     */
    void delete(Long agBewertungId);


}
