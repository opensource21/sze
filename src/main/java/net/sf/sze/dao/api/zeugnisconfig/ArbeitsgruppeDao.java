// ArbeitsgruppeDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnisconfig;

import java.util.List;

import net.sf.sze.model.zeugnisconfig.Arbeitsgruppe;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Arbeitsgruppe}.
 *
 */
public interface ArbeitsgruppeDao
        extends PagingAndSortingRepository<Arbeitsgruppe, Long> {

    /**
    *
    * {@inheritDoc}
    */
   @Override
   List<Arbeitsgruppe> findAll();

}
