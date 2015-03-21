// SchuelerDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.stammdaten;

import java.util.Date;
import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO fürs {@link Schueler}.
 *
 */
public interface SchuelerDao extends PagingAndSortingRepository<Schueler,
        Long> {

    /** From und Where-Bedinung für Schüler mit Zeugnissen. */
    String SCHUELER_WITH_ZEUGNIS = "from Zeugnis z join z.schueler s "
            + "where z.klasse.id=:klassenId and z.schulhalbjahr.id=:halbjahrId "
            + "order by s.name, s.vorname";

    /**
     * Liest alle nicht abgegangenen Schüler zu einer Klasse.
     * @param stichtag Datum bis zu dem die Schüler noch mit ausgewählt werden.
     * @param klasse die Klasse.
     * @return eine Liste von Schülern.
     */
    @Query("select s from Schueler as s where "
            + "(s.abgangsDatum is null or s.abgangsDatum >= :stichtag) "
            + "and klasse = :klasse order by name, vorname")
    List<Schueler> findAllByAbgangsDatumIsNullOrFutureAndKlasse(
            @Param("stichtag") Date stichtag,
            @Param("klasse") Klasse klasse);

    /**
     * Liest alle nicht abgegangenen Schüler.
     * @param stichtag Datum bis zu dem die Schüler noch mit ausgewählt werden.
     * @param pageable Angaben zum Paginating.
     * @return eine Liste von Schülern.
     */
    @Query("select s from Schueler as s where "
            + "(s.abgangsDatum is null or s.abgangsDatum >= :stichtag) ")
    Page<Schueler> findAllByAbgangsDatumIsNullOrFuture(
            @Param("stichtag") Date stichtag, Pageable pageable);

    /**
     * Liest alle abgegangenen Schüler.
     * @param stichtag Datum bis zu dem die Schüler noch mit ausgewählt werden.
     * @param pageable Angaben zum Paginating.
     * @return eine Liste von Schülern.
     */
    @Query("select s from Schueler as s where "
            + "s.abgangsDatum < :stichtag")
    Page<Schueler> findAllByAbgangsDatumIsHistory(
            @Param("stichtag") Date stichtag, Pageable pageable);

    /**
     * Liefert alle Schüler die ein Zeugnis haben sortiert nach Name und Vorname.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse.
     * @return die Liste aller Schüler.
     */
    @Query("select s " + SCHUELER_WITH_ZEUGNIS)
    List<Schueler> findSchuelerWithZeugnisOrdered(@Param("halbjahrId") long halbjahrId,
            @Param("klassenId") long klassenId);
}
