// BewertungDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Bewertung;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Bewertung}.
 *
 */
public interface BewertungDao extends PagingAndSortingRepository<Bewertung,
        Long> {
    // Noch keine speziellen Methoden.
}
