// SchulfachDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnisconfig.Schulfach;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Schulfach}.
 *
 */
public interface SchulfachDao extends PagingAndSortingRepository<Schulfach,
        Long> {
    /**
     *
     * {@inheritDoc}
     */
    @Override
    List<Schulfach> findAll();
}
