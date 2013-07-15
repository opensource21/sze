// ZeugnisFormularDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.ZeugnisFormular;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link ZeugnisFormular}.
 *
 */
public interface ZeugnisFormularDao
        extends PagingAndSortingRepository<ZeugnisFormular, Long> {
    // Noch keine speziellen Methoden.
}
