// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnisconfig;

import net.sf.sze.model.zeugnisconfig.ZeugnisArt;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link ZeugnisArt}.
 *
 * @author niels
 *
 */
public interface ZeugnisArtService {

    /**
     * Deliver a page of ZeugnisArte.
     *
     * @param page information about pagination.
     * @return the page of ZeugnisArte.
     */
    Page<ZeugnisArt> getZeugnisArt(Pageable page);

    /**
     * Save the given zeugnisArt.
     *
     * @param zeugnisArt the zeugnisArt object.
     * @return the zeugnisArt object which may changed.
     *
     */
    ZeugnisArt save(ZeugnisArt zeugnisArt);

    /**
     * Read the zeugnisArt.
     *
     * @param zeugnisArtId the ID of the zeugnisArt object.
     * @return the zeugnisArt object.
     *
     */
    ZeugnisArt read(Long zeugnisArtId);

    /**
     * Delete the zeugnisArt.
     *
     * @param zeugnisArtId the ID of the zeugnisArt object.
     *
     */
    void delete(Long zeugnisArtId);


}
