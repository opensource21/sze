// BinnenDifferenzierteBewertungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.BinnenDifferenzierteBewertung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link BinnenDifferenzierteBewertung}.
 *
 */
public interface BinnenDifferenzierteBewertungDao
        extends PagingAndSortingRepository<BinnenDifferenzierteBewertung,
        Long> {
    // Noch keine speziellen Methoden.
}
