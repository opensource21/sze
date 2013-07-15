// ZeugnisDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Zeugnis;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Zeugnis}.
 *
 */
public interface ZeugnisDao extends PagingAndSortingRepository<Zeugnis, Long> {
    // Noch keine speziellen Methoden.
}
