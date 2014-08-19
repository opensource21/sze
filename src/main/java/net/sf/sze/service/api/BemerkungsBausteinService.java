// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.BemerkungsBaustein;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link BemerkungsBaustein}.
 *
 * @author niels
 *
 */
public interface BemerkungsBausteinService {

    /**
     * Deliver a page of BemerkungsBausteine.
     *
     * @param page information about pagination.
     * @return the page of BemerkungsBausteine.
     */
    Page<BemerkungsBaustein> getBemerkungsBaustein(Pageable page);

    /**
     * Save the given bemerkungsBaustein.
     *
     * @param bemerkungsBaustein the bemerkungsBaustein object.
     * @return the bemerkungsBaustein object which may changed.
     *
     */
    BemerkungsBaustein save(BemerkungsBaustein bemerkungsBaustein);

    /**
     * Read the bemerkungsBaustein.
     *
     * @param bemerkungsBausteinId the ID of the bemerkungsBaustein object.
     * @return the bemerkungsBaustein object.
     *
     */
    BemerkungsBaustein read(Long bemerkungsBausteinId);

    /**
     * Delete the bemerkungsBaustein.
     *
     * @param bemerkungsBausteinId the ID of the bemerkungsBaustein object.
     *
     */
    void delete(Long bemerkungsBausteinId);


}
