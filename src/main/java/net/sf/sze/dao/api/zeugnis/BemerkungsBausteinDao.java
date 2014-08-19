// BemerkungsBausteinDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnisconfig.BemerkungsBaustein;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link BemerkungsBaustein}.
 *
 */
public interface BemerkungsBausteinDao
        extends PagingAndSortingRepository<BemerkungsBaustein, Long> {

    /**
     * Findet alle aktive sortiert nach Name.
     * @return alle aktive sortiert nach Name.
     */
    List<BemerkungsBaustein> findAllByAktivTrueOrderByNameAsc();
}
