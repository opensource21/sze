// ZeugnisDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Zeugnis;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * DAO fürs {@link Zeugnis}.
 *
 */
public interface ZeugnisDao extends PagingAndSortingRepository<Zeugnis, Long> {

    /**
     * Liefert alle Zeugnisse zu der Klasse und dem Halbjahr.
     * @param klasseId die Id der Klasse
     * @param halbjahrId die Id des Schulhalbjahr
     * @return die Liste der Zeugnisse.
     */
    List<Zeugnis> findAllByKlasseIdAndSchulhalbjahrIdAndSchulhalbjahrSelectableIsTrueOrderBySchuelerNameAscSchuelerVornameAsc(
            long klasseId, long halbjahrId);
}
