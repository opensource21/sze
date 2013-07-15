// SoLBewertungsTextDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.SoLBewertungsText;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link SoLBewertungsText}.
 *
 */
public interface SoLBewertungsTextDao
        extends PagingAndSortingRepository<SoLBewertungsText, Long> {
    // Noch keine speziellen Methoden.
}
