// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnisconfig;

import net.sf.sze.model.zeugnisconfig.Schulamt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Schulamt}.
 *
 * @author niels
 *
 */
public interface SchulamtService {

    /**
     * Deliver a page of Schulamte.
     *
     * @param page information about pagination.
     * @return the page of Schulamte.
     */
    Page<Schulamt> getSchulamt(Pageable page);

    /**
     * Save the given schulamt.
     *
     * @param schulamt the schulamt object.
     * @return the schulamt object which may changed.
     *
     */
    Schulamt save(Schulamt schulamt);

    /**
     * Read the schulamt.
     *
     * @param schulamtId the ID of the schulamt object.
     * @return the schulamt object.
     *
     */
    Schulamt read(Long schulamtId);

    /**
     * Delete the schulamt.
     *
     * @param schulamtId the ID of the schulamt object.
     *
     */
    void delete(Long schulamtId);


}
