// schulfächer.ervice.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.stammdaten;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that handles work which must be done for {@link Schueler}.
 *
 * @author niels
 *
 */
public interface SchuelerService {

    /**
     * Deliver a page of Schuelere.
     *
     * @param page information about pagination.
     * @param onlyActiveSchueler true wenn nur aktive Schueler gewollt sind.
     * @return the page of Schuelere.
     */
    Page<Schueler> getSchueler(Pageable page, boolean onlyActiveSchueler);

    /**
     * Deliver a page of active Schuelere.
     *
     * @param klasse the class.
     * @return the page of active Schuelere.
     */
    List<Schueler> getActiveSchueler(Klasse klasse);

    /**
     * Save the given schueler.
     *
     * @param schueler the schueler object.
     * @return the schueler object which may changed.
     *
     */
    Schueler save(Schueler schueler);

    /**
     * Read the schueler.
     *
     * @param schuelerId the ID of the schueler object.
     * @return the schueler object.
     *
     */
    Schueler read(Long schuelerId);

    /**
     * Delete the schueler.
     *
     * @param schuelerId the ID of the schueler object.
     *
     */
    void delete(Long schuelerId);

    /**
     * Liefert eine geordnete Liste von Schülern, die ein Zeugnis haben in dem
     * entsprechenden Schuljahr und Halbjahr.
     * @param halbjahrId die Halbjahrs-Id.
     * @param klassenId die Id der Klasse.
     * @param currentSchuelerId Id des aktuellen Schülers oder <code>null</code>,
     *  wenn es keinen gibt.
     * @return die geordnete Liste der Schüler.
     */
    SchuelerList getSchuelerWithZeugnis(long halbjahrId, long klassenId, Long currentSchuelerId);

}
