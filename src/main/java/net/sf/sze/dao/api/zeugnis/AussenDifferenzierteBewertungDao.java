// AussenDifferenzierteBewertungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link AussenDifferenzierteBewertung}.
 *
 */
public interface AussenDifferenzierteBewertungDao
        extends PagingAndSortingRepository<AussenDifferenzierteBewertung,
        Long> {
    // Noch keine speziellen Methoden.

}
