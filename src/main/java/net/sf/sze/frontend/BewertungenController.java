//BewertungenController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Schulfach;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.service.api.BewertungErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller zur Erfassung der Bertungen.
 *
 */
@Controller
public class BewertungenController {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(BewertungenController.class);


    /**
     * Der {@link BewertungErfassungsService}.
     */
    @Resource
    private BewertungErfassungsService bewertungErfassungsService;

    /**
     * Der Validator.
     */
    @Resource
    private Validator validator;


    /**
     * Zeigt die Bewertungen des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfaches.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.LIST, method = RequestMethod.GET)
    public String showBewertungenPath(@PathVariable(URL.Session
            .P_HALBJAHR_ID) long schulhalbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) long klassenId, @RequestParam(value = URL.Session
            .P_SCHULFACH_ID, required = false) Long schulfachId, Model model,
            RedirectAttributes redirectAttributes) {

        final Klasse klasse = bewertungErfassungsService.getKlasse(klassenId);
        final Schulhalbjahr schulhalbjahr = bewertungErfassungsService.getSchulhalbjahr(schulhalbjahrId);

        final List<Schulfach> schulfaecher = bewertungErfassungsService.
                getActiveSchulfaecher(schulhalbjahr, klasse);
        //TODO Sortierung nach Name?
        Collections.sort(schulfaecher, new Comparator<Schulfach>() {
            @Override
            public int compare(Schulfach o1, Schulfach o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        if (schulfachId == null) {
            LOG.debug("Nehme das erste Schulfach");
            schulfachId = schulfaecher.get(0).getId();
        }

        final List<Bewertung> bewertungen = bewertungErfassungsService.
                getBewertungen(schulhalbjahr.getId().longValue(),
                klasse.getId().longValue(), schulfachId.longValue());

        model.addAttribute("bewertungen", bewertungen);
        model.addAttribute("schulfaecher", schulfaecher);
        model.addAttribute("klasse", klasse);
        model.addAttribute("schulhalbjahr", schulhalbjahr);
        model.addAttribute(URL.Session.P_SCHULFACH_ID, schulfachId);
        return "bewertungen/listBewertungen";

    }

    /**
     * Zeigt die Bewertungen der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Bewertungen.LIST, method = RequestMethod.GET)
    public String showBewertungen(@RequestParam(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId, @RequestParam(URL.Session
            .P_KLASSEN_ID) Long klassenId, Model model,
            RedirectAttributes redirectAttributes) {
        return URL.redirect(URL.BewertungenPath.LIST, halbjahrId, klassenId);
    }

}
