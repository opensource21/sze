// KlasseDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.stammdaten;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Klasse}.
 *
 */
public interface KlasseDao extends PagingAndSortingRepository<Klasse, Long> {

    /**
     * Liefert alle Klassen zu den Parametern.
     * @param min minimales Startjahr.
     * @param max maximales Startjahr.
     * @param closed geschlossen
     * @return die angeforderten Klassen.
     */
    List<Klasse> findAllByJahrgangBetweenAndGeschlossen(int min, int max,
            boolean closed);
}
