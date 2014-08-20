// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnisconfig;

import net.sf.sze.model.zeugnisconfig.ArbeitsUndSozialVerhalten;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link ArbeitsUndSozialVerhalten}.
 *
 * @author niels
 *
 */
public interface ArbeitsUndSozialVerhaltenService {

    /**
     * Deliver a page of ArbeitsUndSozialVerhaltene.
     *
     * @param page information about pagination.
     * @return the page of ArbeitsUndSozialVerhaltene.
     */
    Page<ArbeitsUndSozialVerhalten> getArbeitsUndSozialVerhalten(Pageable page);

    /**
     * Save the given arbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhalten the arbeitsUndSozialVerhalten object.
     * @return the arbeitsUndSozialVerhalten object which may changed.
     *
     */
    ArbeitsUndSozialVerhalten save(ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten);

    /**
     * Read the arbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhaltenId the ID of the arbeitsUndSozialVerhalten object.
     * @return the arbeitsUndSozialVerhalten object.
     *
     */
    ArbeitsUndSozialVerhalten read(Long arbeitsUndSozialVerhaltenId);

    /**
     * Delete the arbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhaltenId the ID of the arbeitsUndSozialVerhalten object.
     *
     */
    void delete(Long arbeitsUndSozialVerhaltenId);


}
