// SchulamtsBemerkungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.SchulamtsBemerkung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link SchulamtsBemerkung}.
 *
 */
public interface SchulamtsBemerkungDao
        extends PagingAndSortingRepository<SchulamtsBemerkung, Long> {
    // Noch keine speziellen Methoden.
}
