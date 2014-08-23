// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import net.sf.sze.model.zeugnis.SchulfachDetailInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link SchulfachDetailInfo}.
 *
 * @author niels
 *
 */
public interface SchulfachDetailInfoService {

    /**
     * Deliver a page of SchulfachDetailInfoe.
     *
     * @param page information about pagination.
     * @return the page of SchulfachDetailInfoe.
     */
    Page<SchulfachDetailInfo> getSchulfachDetailInfo(Pageable page);

    /**
     * Save the given schulfachDetailInfo.
     *
     * @param schulfachDetailInfo the schulfachDetailInfo object.
     * @return the schulfachDetailInfo object which may changed.
     *
     */
    SchulfachDetailInfo save(SchulfachDetailInfo schulfachDetailInfo);

    /**
     * Read the schulfachDetailInfo.
     *
     * @param schulfachDetailInfoId the ID of the schulfachDetailInfo object.
     * @return the schulfachDetailInfo object.
     *
     */
    SchulfachDetailInfo read(Long schulfachDetailInfoId);

    /**
     * Delete the schulfachDetailInfo.
     *
     * @param schulfachDetailInfoId the ID of the schulfachDetailInfo object.
     *
     */
    void delete(Long schulfachDetailInfoId);


}
