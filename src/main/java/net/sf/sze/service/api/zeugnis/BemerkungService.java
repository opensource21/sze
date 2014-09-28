// BemerkungService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnisconfig.BemerkungsBaustein;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

/**
 * Service that handles work which must be done for bemerkung.
 *
 * @author niels
 *
 */
public interface BemerkungService {

    /**
     * Deliver all existing bemerkungs.
     *
     *
     * @return all existing bemerkungs.
     */
    Page<Bemerkung> getAllBemerkung();

    /**
     * Deliver a page of bemerkungs.
     *
     * @param skip the number of entries which should be skipped
     * @param count the number of entries a page should have.
     * @param order information about the ordering of the entries.
     * @return the page of bemerkungs.
     */
    Page<Bemerkung> getBemerkung(int skip, int count, Order... order);

    /**
     * Deliver a page of bemerkungs.
     *
     * @param page information about pagination.
     * @return the page of bemerkungs.
     */
    Page<Bemerkung> getBemerkung(Pageable page);

    /**
     * Save the given bemerkung.
     *
     * @param bemerkung the bemerkung object.
     * @return the bemerkung object which may changed.
     *
     */
    Bemerkung save(Bemerkung bemerkung);

    /**
     * Read the bemerkung.
     *
     * @param bemerkungId the ID of the bemerkung object.
     * @return the bemerkung object.
     *
     */
    Bemerkung read(Long bemerkungId);

    /**
     * Delete the bemerkung.
     *
     * @param bemerkungId the ID of the bemerkung object.
     *
     */
    void delete(Long bemerkungId);

    /**
     * Return the number of bemerkungs.
     *
     * @return number of bemerkungs.
     */
    long getNrOfBemerkungs();

    /**
     * Liefert alle relevanten Bemerkungsbausteine sortiert.
     * @param bemerkung die Bemerkung zu der es angezeigt werden soll.
     * @return alle relevanten Bemerkungsbausteine sortiert.
     */
    List<BemerkungsBaustein> getAllBausteine(Bemerkung bemerkung);
}
