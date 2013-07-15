// AvSvBewertungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.AvSvBewertung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link AvSvBewertung}.
 *
 */
public interface AvSvBewertungDao
        extends PagingAndSortingRepository<AvSvBewertung, Long> {
    // Noch keine speziellen Methoden.

}
