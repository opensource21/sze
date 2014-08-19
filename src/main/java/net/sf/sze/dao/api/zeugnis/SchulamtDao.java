// SchulamtDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnisconfig.Schulamt;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Schulamt}.
 *
 */
public interface SchulamtDao extends PagingAndSortingRepository<Schulamt,
        Long> {

    /**
     * Findet alle aktiven Schulämter sortiert nach Name.
     * @return alle aktiven Schulämter sortiert nach Name.
     */
    List<Schulamt> findAllByAktivTrueOrderByNameAsc();
}
