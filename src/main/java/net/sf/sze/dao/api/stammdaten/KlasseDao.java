// KlasseDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.stammdaten;

import net.sf.sze.model.stammdaten.Klasse;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

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

    /**
     * Findet die erste Klasse der entsprechenden Klassenstufe.
     * @param jahrgang der Jahrgang.
     * @return eine Klasse des Jahrgangs.
     */
    Klasse findFirstByJahrgang(int jahrgang);
}
