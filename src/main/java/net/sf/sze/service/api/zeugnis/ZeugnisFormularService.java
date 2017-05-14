// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;

/**
 * Service that handles work which must be done for {@link ZeugnisFormular}.
 *
 * @author niels
 *
 */
public interface ZeugnisFormularService {

    /**
     * Deliver a page of ZeugnisFormulare.
     *
     * @param page information about pagination.
     * @return the page of ZeugnisFormulare.
     */
    Page<ZeugnisFormular> getZeugnisFormular(Pageable page);

    /**
     * Save the given zeugnisFormular.
     *
     * @param zeugnisFormular the zeugnisFormular object.
     * @return the zeugnisFormular object which may changed.
     *
     */
    ZeugnisFormular save(ZeugnisFormular zeugnisFormular);

    /**
     * Read the zeugnisFormular.
     *
     * @param zeugnisFormularId the ID of the zeugnisFormular object.
     * @return the zeugnisFormular object.
     *
     */
    ZeugnisFormular read(Long zeugnisFormularId);

    /**
     * Delete the zeugnisFormular.
     *
     * @param zeugnisFormularId the ID of the zeugnisFormular object.
     *
     */
    void delete(Long zeugnisFormularId);

    /**
     * Liefert die Liste aller aktiven Klassen.
     * @param zeugnisFormular das Zeugnisformular.
     * @return die Liste aller Klassen
     */
    List<Klasse> getActiveClasses(ZeugnisFormular zeugnisFormular);

    /**
     * Liefert das neueste Schulhalbjahr.
     * @return das neuste Schulhalbjahr.
     */
    Schulhalbjahr getNewestSchulhalbjahr();

    /**
     * Liefert aller aktiven Schulhalbjahre.
     * @param zeugnisFormular aktuelles Zeugnisformular.
     * @return alle aktiven Schulhalbjahre.
     */
    List<Schulhalbjahr> getActiveSchulhalbjahre(ZeugnisFormular zeugnisFormular);

    /**
     * Findet das Zeugnisformular zu dem {@link Schulhalbjahr} und der {@link Klasse}.
     * @param halbjahrId die Id des Schulhalbjahrs
     * @param klassenId die Id der Klasse.
     * @return das Zeugnisformular.
     */
    ZeugnisFormular getZeugnisFormular(long halbjahrId, long klassenId);

    /**
     * Initialisiert für den gegebenen Tag alle Zeugnisformulare.
     * @param referenceDay dsa Bezugsdatum.
     */
    void init(Calendar referenceDay);

    /**
     * Findet das letzte Zeugnisformular für das aktuelle Schulhalbjahr und die Klasse.
     * @param shj das aktuelle Schulhalbjahr
     * @param klasse die Klasse.
     * @return das letzte Zeugnisformular, wenn es eins gibt.
     */
    ZeugnisFormular getLastZeugnisFormular(final Schulhalbjahr shj, Klasse klasse);

    /**
     * Liefert die Dateinamen möglicher Templates, zeitlich sortiert.
     * @return eine Liste mit Dateinamen von templates.
     */
    List<String> getFileNames();


    /**
     * Liefert alle Zeugnisformular zu aktiven Schulhalbjahren, sortiert nach Zeit und Klasse,
     * wobei die neuesten und niedrigsten Klassen zu erst kommen.
     * @return die Liste mit den Formularen.
     */
    List<ZeugnisFormular> getActiveZeugnisFormulare();

}
