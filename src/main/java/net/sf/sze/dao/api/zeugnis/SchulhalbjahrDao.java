// SchulhalbjahrDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Schulhalbjahr;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * DAO fürs {@link Schulhalbjahr}.
 *
 */
public interface SchulhalbjahrDao
        extends PagingAndSortingRepository<Schulhalbjahr, Long> {

    /**
     * Liefert alle aktiven Schulhalbjahre sortiert nach Schuljahr und Schulhalbjahr.
     *
     * @param selectable selektierbar.
     * @return alle aktiven Schulhalbjahre sortiert nach Schuljahr und Schulhalbjahr.
     */
    List<Schulhalbjahr> findAllBySelectableOrderByJahrDescHalbjahrDesc(
            boolean selectable);
}
