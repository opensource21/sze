// SchulamtsBemerkungService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.SchulamtsBemerkung;
import net.sf.sze.model.zeugnisconfig.Schulamt;
import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

/**
 * Service that handles work which must be done for {@link SchulamtsBemerkung}.
 *
 * @author niels
 *
 */
public interface SchulamtsBemerkungService {

    /**
     * Deliver all existing {@link SchulamtsBemerkung}.
     *
     *
     * @return all existing {@link SchulamtsBemerkung}.
     */
    Page<SchulamtsBemerkung> getAllSchulamtsBemerkungen();

    /**
     * Deliver a page of bemerkungs.
     *
     * @param skip the number of entries which should be skipped
     * @param count the number of entries a page should have.
     * @param order information about the ordering of the entries.
     * @return the page of bemerkungs.
     */
    Page<SchulamtsBemerkung> getSchulamtsBemerkungen(int skip, int count, Order... order);

    /**
     * Deliver a page of {@link SchulamtsBemerkung}.
     *
     * @param page information about pagination.
     * @return the page of {@link SchulamtsBemerkung}.
     */
    Page<SchulamtsBemerkung> getSchulamtsBemerkungen(Pageable page);

    /**
     * Save the given {@link SchulamtsBemerkung}.
     *
     * @param bemerkung the {@link SchulamtsBemerkung} object.
     * @return the {@link SchulamtsBemerkung} object which may changed.
     *
     */
    SchulamtsBemerkung save(SchulamtsBemerkung bemerkung);

    /**
     * Read the {@link SchulamtsBemerkung}.
     *
     * @param schulamtsBemerkungId the ID of the {@link SchulamtsBemerkung} object.
     * @return the {@link SchulamtsBemerkung} object.
     *
     */
    SchulamtsBemerkung read(Long schulamtsBemerkungId);

    /**
     * Delete the {@link SchulamtsBemerkung}.
     *
     * @param schulamtsBemerkungId the ID of the {@link SchulamtsBemerkung} object.
     *
     */
    void delete(Long schulamtsBemerkungId);

    /**
     * Return the number of {@link SchulamtsBemerkung}.
     *
     * @return number of {@link SchulamtsBemerkung}.
     */
    long getNrOfSchulamtsBemerkungen();

    /**
     * Liefert alle relevanten SchulamtsBemerkungsbausteine sortiert.
     * @param schulamtsBemerkung die SchulamtsBemerkung zu der es
     * angezeigt werden soll.
     * @return alle relevanten SchulamtsBemerkungsbausteine sortiert.
     */
    List<SchulamtsBemerkungsBaustein> getAllSchulamtsBemerkungsBausteine(
            SchulamtsBemerkung schulamtsBemerkung);

    /**
     * Liefert alle relevanten Schulämter sortiert.
     * @param schulamtsBemerkung die SchulamtsBemerkung zu der es
     * angezeigt werden soll.
     * @return alle relevanten Schulämter sortiert.
     */
    List<Schulamt> getAllSchulaemter(SchulamtsBemerkung schulamtsBemerkung);

}
