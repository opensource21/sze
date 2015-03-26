// BewertungDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Zeugnis;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Bewertung}.
 *
 */
public interface BewertungDao extends PagingAndSortingRepository<Bewertung,
        Long> {

    /**
     * Liefert alle Bewertungen zu den Zeugnissen.
     * @param schulfachId die Id des Schulfachs.
     * @param zeugnisse die Zeugnisse.
     * @return alle zugehörigen Bewertungen.
     */
    List<Bewertung> findAllBySchulfachIdAndZeugnisIn(long schulfachId, List<Zeugnis> zeugnisse);
}
