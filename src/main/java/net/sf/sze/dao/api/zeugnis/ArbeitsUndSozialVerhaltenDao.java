// ArbeitsUndSozialVerhaltenDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.ArbeitsUndSozialVerhalten;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link ArbeitsUndSozialVerhalten}.
 *
 */
public interface ArbeitsUndSozialVerhaltenDao
        extends PagingAndSortingRepository<ArbeitsUndSozialVerhalten, Long> {
    // Noch keine speziellen Methoden.
}
