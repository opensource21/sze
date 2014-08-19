// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.SoLBewertungsText;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link SoLBewertungsText}.
 *
 * @author niels
 *
 */
public interface SoLBewertungsTextService {

    /**
     * Deliver a page of SoLBewertungsTexte.
     *
     * @param page information about pagination.
     * @return the page of SoLBewertungsTexte.
     */
    Page<SoLBewertungsText> getSoLBewertungsText(Pageable page);

    /**
     * Save the given solBewertungsText.
     *
     * @param solBewertungsText the solBewertungsText object.
     * @return the solBewertungsText object which may changed.
     *
     */
    SoLBewertungsText save(SoLBewertungsText solBewertungsText);

    /**
     * Read the solBewertungsText.
     *
     * @param solBewertungsTextId the ID of the solBewertungsText object.
     * @return the solBewertungsText object.
     *
     */
    SoLBewertungsText read(Long solBewertungsTextId);

    /**
     * Delete the solBewertungsText.
     *
     * @param solBewertungsTextId the ID of the solBewertungsText object.
     *
     */
    void delete(Long solBewertungsTextId);


}
