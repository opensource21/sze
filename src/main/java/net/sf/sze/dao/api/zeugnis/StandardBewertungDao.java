// StandardBewertungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.StandardBewertung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link StandardBewertung}.
 *
 */
public interface StandardBewertungDao
        extends PagingAndSortingRepository<StandardBewertung, Long> {
    // Noch keine speziellen Methoden.
}
