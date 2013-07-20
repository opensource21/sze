// SchulhalbjahrDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Halbjahr;
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

    /**
     * Selektierbare bzw nicht selektierbare Schulhalbjahre.
     * @param selectable true or false.
     * @return die Schulhalbjahre.
     */
    List<Schulhalbjahr> findAllBySelectable(boolean selectable);

    /**
     * Liefert das Schulhalbjahr.
     * @param jahr das entsprechende Jahr.
     * @param halbjahr das Halbjahr.
     * @return das Schulhalbjahr.
     */
    Schulhalbjahr findByJahrAndHalbjahr(int jahr, Halbjahr halbjahr);
}
