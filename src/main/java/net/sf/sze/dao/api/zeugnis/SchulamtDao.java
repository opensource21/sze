// SchulamtDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Schulamt;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Schulamt}.
 *
 */
public interface SchulamtDao extends PagingAndSortingRepository<Schulamt,
        Long> {
    // Noch keine speziellen Methoden.
}
