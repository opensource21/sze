// ArbeitsUndSozialVerhaltenDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnisconfig;

import java.util.List;

import net.sf.sze.model.zeugnisconfig.ArbeitsUndSozialVerhalten;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link ArbeitsUndSozialVerhalten}.
 *
 */
public interface ArbeitsUndSozialVerhaltenDao
        extends PagingAndSortingRepository<ArbeitsUndSozialVerhalten, Long> {
    /**
    *
    * {@inheritDoc}
    */
   @Override
   List<ArbeitsUndSozialVerhalten> findAll();
}
