// SchulfachDetailInfoDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.SchulfachDetailInfo;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link SchulfachDetailInfo}.
 *
 */
public interface SchulfachDetailInfoDao
        extends PagingAndSortingRepository<SchulfachDetailInfo, Long> {
    // Noch keine speziellen Methoden.
}
