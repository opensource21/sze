// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnisconfig;

import net.sf.sze.model.zeugnisconfig.Schulfach;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Schulfach}.
 *
 * @author niels
 *
 */
public interface SchulfachService {

    /**
     * Deliver a page of schulfächer.
     *
     * @param page information about pagination.
     * @return the page of schulfächer.
     */
    Page<Schulfach> getSchulfach(Pageable page);

    /**
     * Save the given schulfach.
     *
     * @param schulfach the schulfach object.
     * @return the schulfach object which may changed.
     *
     */
    Schulfach save(Schulfach schulfach);

    /**
     * Read the schulfach.
     *
     * @param schulfachId the ID of the schulfach object.
     * @return the schulfach object.
     *
     */
    Schulfach read(Long schulfachId);

    /**
     * Delete the schulfach.
     *
     * @param schulfachId the ID of the schulfach object.
     *
     */
    void delete(Long schulfachId);


}
