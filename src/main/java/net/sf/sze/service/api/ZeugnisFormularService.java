// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
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

    /**
     * Liefert die Liste aller aktiven Klassen.
     * @param zeugnisFormular das Zeugnisformular.
     * @return die Liste aller Klassen
     */
    List<Klasse> getActiveClasses(ZeugnisFormular zeugnisFormular);

    /**
     * Liefert das neueste Schulhalbjahr.
     * @return das neuste Schulhalbjahr.
     */
    Schulhalbjahr getNewestSchulhalbjahr();


}
