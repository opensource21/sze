// BemerkungsBausteinDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.BemerkungsBaustein;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link BemerkungsBaustein}.
 *
 */
public interface BemerkungsBausteinDao
        extends PagingAndSortingRepository<BemerkungsBaustein, Long> {
    // Noch keine speziellen Methoden.
}
