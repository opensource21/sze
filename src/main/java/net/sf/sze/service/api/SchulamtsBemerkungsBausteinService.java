// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;


import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link SchulamtsBemerkungsBaustein}.
 *
 * @author niels
 *
 */
public interface SchulamtsBemerkungsBausteinService {

    /**
     * Deliver a page of SchulamtsBemerkungsBausteine.
     *
     * @param page information about pagination.
     * @return the page of SchulamtsBemerkungsBausteine.
     */
    Page<SchulamtsBemerkungsBaustein> getSchulamtsBemerkungsBaustein(Pageable page);

    /**
     * Save the given schulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBaustein the schulamtsBemerkungsBaustein object.
     * @return the schulamtsBemerkungsBaustein object which may changed.
     *
     */
    SchulamtsBemerkungsBaustein save(SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein);

    /**
     * Read the schulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBausteinId the ID of the schulamtsBemerkungsBaustein object.
     * @return the schulamtsBemerkungsBaustein object.
     *
     */
    SchulamtsBemerkungsBaustein read(Long schulamtsBemerkungsBausteinId);

    /**
     * Delete the schulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBausteinId the ID of the schulamtsBemerkungsBaustein object.
     *
     */
    void delete(Long schulamtsBemerkungsBausteinId);


}
