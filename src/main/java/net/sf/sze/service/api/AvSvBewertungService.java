// avSvBewertungen.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AvSvBewertung;
import net.sf.sze.model.zeugnis.Schulhalbjahr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link AvSvBewertung}.
 *
 * @author niels
 *
 */
public interface AvSvBewertungService {

    /**
     * Deliver a page of avSvBewertungen.
     *
     * @param page information about pagination.
     * @return the page of avSvBewertungen.
     */
    Page<AvSvBewertung> getBewertung(Pageable page);

    /**
     * Save the given avSvBewertung.
     *
     * @param avSvBewertung the avSvBewertung object.
     * @return the avSvBewertung object which may changed.
     *
     */
    AvSvBewertung save(AvSvBewertung avSvBewertung);

    /**
     * Save the given avSvBewertung.
     *
     * @param avSvBewertungen the avSvBewertung object.
     * @return the avSvBewertung object which may changed.
     *
     */
    Iterable<AvSvBewertung> save(Iterable<AvSvBewertung> avSvBewertungen);

    /**
     * Read the avSvBewertung.
     *
     * @param avSvBewertungId the ID of the avSvBewertung object.
     * @return the avSvBewertung object.
     *
     */
    AvSvBewertung read(Long avSvBewertungId);

    /**
     * Delete the avSvBewertung.
     *
     * @param avSvBewertungId the ID of the avSvBewertung object.
     *
     */
    void delete(Long avSvBewertungId);


    /**
     * Liefert die Klasse.
     * @param klassenId Id der Klasse
     * @return die Klasse.
     */
    Klasse getKlasse(Long klassenId);

    /**
     * Liefert das Schulhalbjahr.
     * @param schulhalbjahrId Id der Schulhalbjahr
     * @return das Schulhalbjahr.
     */
    Schulhalbjahr getSchulhalbjahr(Long schulhalbjahrId);


    /**
     * Liefert den Schüler.
     * @param schuelerId Id des Schüler
     * @return den Schüler.
     */
    Schueler getSchueler(Long schuelerId);


}
