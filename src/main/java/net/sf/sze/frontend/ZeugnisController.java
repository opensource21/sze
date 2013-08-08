// ZeugnisController.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

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
    @RequestMapping(value = {URL.Zeugnis.HOME, URL.Zeugnis.START},
            method = RequestMethod.GET)
    public String chooseClass(Model model) {
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
        model.addAttribute("urlShowBewertung", URL.filledURL(URL.Zeugnis
                .BEWERTUNGEN));
        model.addAttribute("urlPrintZeugnis", URL.filledURL(URL.Zeugnis
                .ALL_PDFS));

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
    public String showZeugnisPath(@PathVariable(URL.ZeugnisPath
            .P_HALBJAHR_ID) long halbjahrId, @PathVariable(URL.ZeugnisPath
            .P_KLASSEN_ID) long klassenId, @RequestParam(value = URL.Zeugnis
            .P_SCHUELER_ID,
            required = false, defaultValue = "0") Long schuelerId, Model model,
                    RedirectAttributes redirectAttributes) {
        return showZeugnis(halbjahrId, klassenId, schuelerId, model,
                redirectAttributes);
    }

    /**
     * Zeigt das Zeugnis des entsprechenden Schülers der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des auszuwählenden Schüler.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Zeugnis.SHOW, method = RequestMethod.GET)
    public String showZeugnis(@RequestParam(value = URL.Zeugnis.P_HALBJAHR_ID,
            required = false) long halbjahrId, @RequestParam(URL.Zeugnis
                    .P_KLASSEN_ID) long klassenId, @RequestParam(value = URL
                    .Zeugnis.P_SCHUELER_ID,
            required = false) Long schuelerId, Model model,
                    RedirectAttributes redirectAttributes) {
        final List<Zeugnis> zeugnisse = zeugnisErfassungsService.getZeugnisse(
                halbjahrId, klassenId);

        if (CollectionUtils.isEmpty(zeugnisse)) {
            redirectAttributes.addFlashAttribute("message",
                    "Es wurden keine Zeugnisse gefunden");
            redirectAttributes.addFlashAttribute(URL.Zeugnis.P_HALBJAHR_ID, Long
                    .valueOf(halbjahrId));
            redirectAttributes.addFlashAttribute(URL.Zeugnis.P_KLASSEN_ID, Long
                    .valueOf(klassenId));
            return URL.redirect(URL.Zeugnis.START);
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
}
