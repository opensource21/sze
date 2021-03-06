// ZeugnisErfassungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.api.zeugnis;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.model.zeugnisconfig.SoLBewertungsText;
import net.sf.sze.model.zeugnisconfig.ZeugnisArt;

import java.util.List;

/**
 * Service mit Diensten zum Erfassen von Zeugnisse.
 *
 */
public interface ZeugnisErfassungsService {

    /**
     * Liefert eine Liste mit allen auf aktive gesetzten Schulhalbjahren.
     * @return eine Liste mit allen auf aktive gesetzten Schulhalbjahren.
     */
    List<Schulhalbjahr> getActiveSchulhalbjahre();

    /**
     * Liefert eine Liste mit alle möglichen Klassen zu den aktiven Schulhalbjahren.
     * @param acticeSchulhalbjahre aktive Schulhalbjahren.
     * @return eine Liste mit alle möglichen Klassen zu den aktiven Schulhalbjahren.
     */
    List<Klasse> getActiveKlassen(List<Schulhalbjahr> acticeSchulhalbjahre);

    /**
     * Liefert eine Liste mit alle aktiven Zeugnisarten und der aktuell verwendeten.
     * @param zeugnis das aktuelle Zeugnis.
     * @return eine Liste mit alle aktiven Zeugnisarten und der aktuell verwendeten.
     */
    List<ZeugnisArt> getAllZeugnisArten(Zeugnis zeugnis);

    /**
     * Teilt die Bewertungen auf die beiden Listen auf und sortiert diese.
     * @param bewertungen alle Bewertungen.
     * @param wpBewertungen Wahlpflichtbewertungen
     * @param otherBewertungen andere Bewertungen.
     */
    void splitBewertungslist(List<Bewertung> bewertungen,
            final List<Bewertung> wpBewertungen,
            final List<Bewertung> otherBewertungen);

    /**
     * Liefert die Bewertungen mit den Nachbarn.
     * @param bewertungsId die Bewertungs-Id
     * @return eine Bewertung mit den Ids des Nachbarbewertungen.
     */
    BewertungWithNeigbors getBewertungWithNeighbors(Long bewertungsId);

    /**
     * Liest ein Zeugnis.
     * @param halbjahrId die HalbjahrsId
     * @param schuelerId die Id des Schülers
     * @return das Zeugnis.
     */
    Zeugnis getZeugnis(Long halbjahrId, Long schuelerId);


    /**
     * Sichert das Zeugnis, wobei Veränderungen auf der <b>Inverse-Side nicht
     * berücksichtigt</b> werden!
     *
     * @param zeugnis das zu speichernde Zeugnis.
     * @return das gespeicherte Zeugnis.
     *
     */
    Zeugnis save(Zeugnis zeugnis);

    /**
     * Liefert alle SoL-Bewertungstexte, sortiert und passend zum Zeugnis.
     * @param zeugnis das aktuelle bvZeugnis.
     * @return alle SoL-Bewertungstexte, sortiert und passend zum Zeugnis.
     */
    List<SoLBewertungsText> getSoLTexte(Zeugnis zeugnis);

    /**
     * Liefert alle Zeugnisse.
     * @return alle Zeugnisse.
     */
    List<Zeugnis> getAllZeugnisse();
}
