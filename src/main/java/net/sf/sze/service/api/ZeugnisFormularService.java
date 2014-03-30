// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.ZeugnisFormular;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link ZeugnisFormular}.
 *
 * @author niels
 *
 */
public interface ZeugnisFormularService {

    /**
     * Deliver a page of ZeugnisFormulare.
     *
     * @param page information about pagination.
     * @return the page of ZeugnisFormulare.
     */
    Page<ZeugnisFormular> getZeugnisFormular(Pageable page);

    /**
     * Save the given zeugnisFormular.
     *
     * @param zeugnisFormular the zeugnisFormular object.
     * @return the zeugnisFormular object which may changed.
     *
     */
    ZeugnisFormular save(ZeugnisFormular zeugnisFormular);

    /**
     * Read the zeugnisFormular.
     *
     * @param zeugnisFormularId the ID of the zeugnisFormular object.
     * @return the zeugnisFormular object.
     *
     */
    ZeugnisFormular read(Long zeugnisFormularId);

    /**
     * Delete the zeugnisFormular.
     *
     * @param zeugnisFormularId the ID of the zeugnisFormular object.
     *
     */
    void delete(Long zeugnisFormularId);


}
