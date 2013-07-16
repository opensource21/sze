// ZeugnisController.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
     * @param schuelerIndex der Indes des Schülers.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Zeugnis.SHOW, method = RequestMethod.GET)
    public String showZeugnis(@RequestParam("halbjahrId") long halbjahrId,
            @RequestParam("klassenId") long klassenId, @RequestParam(
            value = "schulerIndex",
            required = false, defaultValue = "0") int schuelerIndex,
                    Model model, RedirectAttributes redirectAttributes) {
        final List<Zeugnis> zeugnisse = zeugnisErfassungsService.getZeugnisse(
                halbjahrId, klassenId);

        if (CollectionUtils.isEmpty(zeugnisse)) {
            redirectAttributes.addFlashAttribute("message",
                    "Es wurden keine Zeugnisse gefunden");
            return URL.redirect(URL.Zeugnis.START);
        }

        final List<Schueler> schueler = new ArrayList<Schueler>(zeugnisse
                .size());
        for (Zeugnis zeugnis : zeugnisse) {
            schueler.add(zeugnis.getSchueler());
        }

        final Zeugnis selektiertesZeugnis = zeugnisse.get(schuelerIndex);

        model.addAttribute("schueler", schueler);
        model.addAttribute("zeugnis", selektiertesZeugnis);

        return "zeugnis/showZeugnis";
    }
}
