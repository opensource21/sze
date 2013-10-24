// SchulamtsBemerkungsBausteinDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.SchulamtsBemerkungsBaustein;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link SchulamtsBemerkungsBaustein}.
 *
 */
public interface SchulamtsBemerkungsBausteinDao
        extends PagingAndSortingRepository<SchulamtsBemerkungsBaustein, Long> {

    /**
     * Findet alle aktiven Bausteine sortiert nach Name.
     * @return alle aktiven Bausteine sortiert nach Name.
     */
    List<SchulamtsBemerkungsBaustein> findAllByAktivTrueOrderByNameAsc();
}
