// BemerkungsBausteinService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.BemerkungsBaustein;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

/**
 * Service that handles work which must be done for {@link BemerkungsBausteinsBaustein}.
 *
 * @author niels
 *
 */
public interface BemerkungsBausteineService {

    /**
     * Deliver all existing {@link BemerkungsBausteinsBaustein}.
     *
     *
     * @return all existing {@link BemerkungsBausteinsBaustein}.
     */
    Page<BemerkungsBaustein> getAllBemerkungsBausteine();

    /**
     * Deliver a page of bemerkungs.
     *
     * @param skip the number of entries which should be skipped
     * @param count the number of entries a page should have.
     * @param order information about the ordering of the entries.
     * @return the page of bemerkungs.
     */
    Page<BemerkungsBaustein> getBemerkungsBausteine(int skip, int count, Order... order);

    /**
     * Deliver a page of {@link BemerkungsBaustein}.
     *
     * @param page information about pagination.
     * @return the page of {@link BemerkungsBaustein}.
     */
    Page<BemerkungsBaustein> getBemerkungsBausteine(Pageable page);

    /**
     * Save the given {@link BemerkungsBaustein}.
     *
     * @param bemerkung the {@link BemerkungsBaustein} object.
     * @return the {@link BemerkungsBaustein} object which may changed.
     *
     */
    BemerkungsBaustein save(BemerkungsBaustein bemerkung);

    /**
     * Read the {@link BemerkungsBaustein}.
     *
     * @param bemerkungsBausteinId the ID of the {@link BemerkungsBaustein} object.
     * @return the {@link BemerkungsBaustein} object.
     *
     */
    BemerkungsBaustein read(Long bemerkungsBausteinId);

    /**
     * Delete the {@link BemerkungsBaustein}.
     *
     * @param bemerkungsBausteinId the ID of the {@link BemerkungsBaustein} object.
     *
     */
    void delete(Long bemerkungsBausteinId);

    /**
     * Return the number of {@link BemerkungsBaustein}.
     *
     * @return number of {@link BemerkungsBaustein}.
     */
    long getNrOfBemerkungsBausteine();
}
