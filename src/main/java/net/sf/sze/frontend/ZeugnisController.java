// ZeugnisController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Schulfachtyp;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Haupt-Controlller für die Zeugniserfassung.
 *
 */
@Controller
public class ZeugnisController {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisController.class);

    /**
     * Der {@link ZeugnisErfassungsService}.
     */
    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    /**
     * Der Validator.
     */
    @Resource
    private Validator validator;

    /**
     * Zeigt den Auswahl-Dialog für die Klasse.
     *
     * @param model das Modell.
     * @return den View-Namen.
     */
    @RequestMapping(value = {URL.Zeugnis.START, URL.Zeugnis.HOME},
            method = RequestMethod.GET)
    public String chooseClass(Model model) {
        return chooseClass(Long.valueOf(0), Long.valueOf(0), model);
    }

    /**
     * Zeigt den Auswahl-Dialog für die Klasse.
     * @param halbjahrId die Id des Halbjahres
     * @param klassenId die Id der Klasse
     * @param model das Modell
     * @return den View-Namen
     */
    @RequestMapping(value = {URL.ZeugnisPath.START}, method = RequestMethod.GET)
    public String chooseClass(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId, @PathVariable(URL.Session
            .P_KLASSEN_ID) Long klassenId, Model model) {
        final List<Schulhalbjahr> halbjahre = zeugnisErfassungsService
                .getActiveSchulhalbjahre();
        if (CollectionUtils.isEmpty(halbjahre)) {
            return URL.redirect(URL.Configuration.MAIN);
        }

        final List<Klasse> klassen = zeugnisErfassungsService.getActiveKlassen(
                halbjahre);
        model.addAttribute("klassen", klassen);
        model.addAttribute("halbjahre", halbjahre);
        model.addAttribute("helpMessageId", "help.chooseClass");
        model.addAttribute("urlShowZeugnis", URL.filledURL(URL.Zeugnis.SHOW));
        model.addAttribute("urlShowBewertung", URL.filledURL(URL.Bewertungen.LIST));
        model.addAttribute("urlPrintZeugnis", URL.filledURL(URL.Zeugnis
                .ALL_PDFS));
//      model.addAttribute(URL.Zeugnis.P_HALBJAHR_ID, halbjahrId);
//      model.addAttribute(URL.Zeugnis.P_KLASSEN_ID, klassenId);

        return "zeugnis/chooseClass";
    }

    /**
     * Zeigt das Zeugnis des entsprechenden Schülers der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId der Indes des Schülers.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SHOW, method = RequestMethod.GET)
    public String showZeugnisPath(@PathVariable(URL.Session
            .P_HALBJAHR_ID) long halbjahrId, @PathVariable(URL.Session
            .P_KLASSEN_ID) long klassenId, @RequestParam(value = URL.Session
            .P_SCHUELER_ID,
            required = false) Long schuelerId, Model model,
                    RedirectAttributes redirectAttributes) {
        final List<Zeugnis> zeugnisse = zeugnisErfassungsService.getZeugnisse(
                halbjahrId, klassenId);

        LOG.debug("SchülerId=>{}<)", schuelerId);

        if (CollectionUtils.isEmpty(zeugnisse)) {
            redirectAttributes.addFlashAttribute("message",
                    "Es wurden keine Zeugnisse gefunden");
            return URL.redirect(URL.ZeugnisPath.START, Long.valueOf(
                    halbjahrId), Long.valueOf(klassenId));
        }

        final List<Schueler> schuelerListe = new ArrayList<Schueler>(zeugnisse
                .size());
        Zeugnis selectedZeugnis = null;
        Long prevSchuelerId = null;
        Long selectedSchuelerId = schuelerId;
        Long nextSchuelerId = null;
        for (Zeugnis zeugnis : zeugnisse) {
            // Sicherstellen, dass es immer einen selektierten Schüler gibt.
            if (selectedSchuelerId == null) {
                selectedSchuelerId = zeugnis.getSchueler().getId();
            }

            schuelerListe.add(zeugnis.getSchueler());

            if ((selectedZeugnis != null) && (nextSchuelerId == null)) {
                nextSchuelerId = zeugnis.getSchueler().getId();
            }

            if (selectedSchuelerId.equals(zeugnis.getSchueler().getId())) {
                selectedZeugnis = zeugnis;
            }

            if (selectedZeugnis == null) {
                prevSchuelerId = zeugnis.getSchueler().getId();
            }
        }

        if (selectedZeugnis == null) {
            redirectAttributes.addFlashAttribute("message",
                    "Der angegebene Schüler hat kein Zeugnis, gehe zum ersten.");
            return URL.redirect(URL.ZeugnisPath.SHOW, Long.valueOf(halbjahrId),
                    Long.valueOf(klassenId));
        }

        Collections.sort(selectedZeugnis.getBewertungen());
        Collections.sort(selectedZeugnis.getSchulamtsBemerkungen());
        Collections.sort(selectedZeugnis.getBemerkungen());

        final List<Bewertung> wpBewertungen = new ArrayList<>();
        final List<Bewertung> otherBewertungen = new ArrayList<>();
        for (Bewertung bewertung : selectedZeugnis.getBewertungen()) {
            if (Schulfachtyp.WAHLPFLICHT.equals(bewertung.getSchulfach()
                    .getTyp())) {
                wpBewertungen.add(bewertung);
            } else {
                otherBewertungen.add(bewertung);
            }
        }

        LOG.debug("Zeugnis von Schueler {}. ", selectedZeugnis.getSchueler());
        model.addAttribute("schuelerListe", schuelerListe);
        model.addAttribute("zeugnis", selectedZeugnis);
        model.addAttribute("prevSchuelerId", prevSchuelerId);
        model.addAttribute("nextSchuelerId", nextSchuelerId);
        model.addAttribute("wpBewertungen", wpBewertungen);
        model.addAttribute("otherBewertungen", otherBewertungen);
        model.addAttribute("urlShowZeugnis", URL.filledURL(URL.ZeugnisPath
                .SHOW, Long.valueOf(halbjahrId), Long.valueOf(klassenId)));
        model.addAttribute("urlPrintZeugnis", URL.filledURL(URL.Zeugnis
                .ONE_PDF, selectedZeugnis.getSchueler().getId(), Long.valueOf(
                halbjahrId)));
        model.addAttribute("arbeitsgruppenSatz", selectedZeugnis
                .createArbeitsgruppenSatz());
        return "zeugnis/showZeugnis";
    }

    /**
     * Zeigt das Zeugnis des ersten Schülers der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Zeugnis.SHOW, method = RequestMethod.GET)
    public String showZeugnis(@RequestParam(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId, @RequestParam(URL.Session
            .P_KLASSEN_ID) Long klassenId, Model model,
            RedirectAttributes redirectAttributes) {
        return URL.redirect(URL.ZeugnisPath.SHOW, halbjahrId, klassenId);
    }
}
