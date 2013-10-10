// BewertungDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import net.sf.sze.model.zeugnis.Bewertung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @param pageable information zum Paginating.
     * @return ein Page-Objekt.
     */
    Page<Bewertung> findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndZeugnisSchulhalbjahrSelectableIsTrueOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(
            long klasseId, long halbjahrId, Pageable pageable);


    /**
     * Listet alle Bewertungen zu einer Klasse und einem Halbjahr.
     * @param klasseId Id der Klasse.
     * @param halbjahrId Id des Halbjahrs.
     * @param pageable information zum Paginating.
     * @return ein Page-Objekt.
     */
    Page<Bewertung> findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndZeugnisSchulhalbjahrSelectableIsTrueAndSchulfachIdOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(
            long klasseId, long halbjahrId, long schulfachId, Pageable page);
}
