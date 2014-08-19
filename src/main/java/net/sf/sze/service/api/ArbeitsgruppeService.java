// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnisconfig.Arbeitsgruppe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Arbeitsgruppe}.
 *
 * @author niels
 *
 */
public interface ArbeitsgruppeService {

    /**
     * Deliver a page of Arbeitsgruppee.
     *
     * @param page information about pagination.
     * @return the page of Arbeitsgruppee.
     */
    Page<Arbeitsgruppe> getArbeitsgruppe(Pageable page);

    /**
     * Save the given arbeitsgruppe.
     *
     * @param arbeitsgruppe the arbeitsgruppe object.
     * @return the arbeitsgruppe object which may changed.
     *
     */
    Arbeitsgruppe save(Arbeitsgruppe arbeitsgruppe);

    /**
     * Read the arbeitsgruppe.
     *
     * @param arbeitsgruppeId the ID of the arbeitsgruppe object.
     * @return the arbeitsgruppe object.
     *
     */
    Arbeitsgruppe read(Long arbeitsgruppeId);

    /**
     * Delete the arbeitsgruppe.
     *
     * @param arbeitsgruppeId the ID of the arbeitsgruppe object.
     *
     */
    void delete(Long arbeitsgruppeId);


}
