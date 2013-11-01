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
     * Listet alle Bewertungen zu einer Klasse und einem Halbjahr.
     * @param klasseId Id der Klasse.
     * @param halbjahrId Id des Halbjahrs.
     * @return Die Liste aller Zeugnisse.
     */
    //TODO diese Methode scheint zu komplex zu sein, als das Hibernate sie
    //schafft , kann man wohl löschen.
    List<Bewertung>
    findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndSchulfachIdOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(
            long klasseId, long halbjahrId, long schulfachId);

    /**
     * Liefert alle Bewertungen zu den Zeugnissen.
     * @param schulfachId die Id des Schulfachs.
     * @param zeugnisse die Zeugnisse.
     * @return alle zugehörigen Bewertungen.
     */
    List<Bewertung> findAllBySchulfachIdAndZeugnisIn(long schulfachId, List<Zeugnis> zeugnisse);
}
