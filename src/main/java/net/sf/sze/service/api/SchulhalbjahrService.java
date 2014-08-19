// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Schulhalbjahr}.
 *
 * @author niels
 *
 */
public interface SchulhalbjahrService {

    /**
     * Deliver a page of Schulhalbjahre.
     *
     * @param page information about pagination.
     * @return the page of Schulhalbjahre.
     */
    Page<Schulhalbjahr> getSchulhalbjahr(Pageable page);

    /**
     * Save the given schulhalbjahr.
     *
     * @param schulhalbjahr the schulhalbjahr object.
     * @return the schulhalbjahr object which may changed.
     *
     */
    Schulhalbjahr save(Schulhalbjahr schulhalbjahr);

    /**
     * Read the schulhalbjahr.
     *
     * @param schulhalbjahrId the ID of the schulhalbjahr object.
     * @return the schulhalbjahr object.
     *
     */
    Schulhalbjahr read(Long schulhalbjahrId);

    /**
     * Delete the schulhalbjahr.
     *
     * @param schulhalbjahrId the ID of the schulhalbjahr object.
     *
     */
    void delete(Long schulhalbjahrId);


}
