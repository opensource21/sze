//BewertungenController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend;

import javax.annotation.Resource;

import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.service.api.BewertungErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ppi.fuwesta.spring.mvc.util.PageWrapper;


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
    @RequestMapping(value = URL.BewertungenPath.SHOW, method = RequestMethod.GET)
    public String showBewertungenPath(@PathVariable(URL.Session
            .P_HALBJAHR_ID) long halbjahrId, @PathVariable(URL.Session
            .P_KLASSEN_ID) long klassenId, @RequestParam(value = URL.Session
            .P_SCHULFACH_ID,
            required = false) Long schufachId,  @PageableDefault(page = 0, value = 20,
            sort = {"zeugnis.schueler.name"}, direction = Direction
                    .ASC) Pageable pageRequest, Model model,
                    RedirectAttributes redirectAttributes) {
         final PageWrapper<Bewertung> bewertungen = new PageWrapper<>(
                 bewertungErfassungsService.getBewertungen(halbjahrId, klassenId, pageRequest),
                 URL.filledURL(URL.BewertungenPath.SHOW, Long.valueOf(halbjahrId),
                 Long.valueOf(klassenId)));
//
//        LOG.debug("SchuldfachId=>{}<)", schufachId);
//
//        if (CollectionUtils.isEmpty(zeugnisse)) {
//            redirectAttributes.addFlashAttribute("message",
//                    "Es wurden keine Zeugnisse gefunden");
//            return URL.redirect(URL.ZeugnisPath.START, Long.valueOf(
//                    halbjahrId), Long.valueOf(klassenId));
//        }
//
//        final List<Schueler> schuelerListe = new ArrayList<Schueler>(zeugnisse
//                .size());
//        Zeugnis selectedZeugnis = null;
//        Long prevSchuelerId = null;
//        Long selectedSchuelerId = schuelerId;
//        Long nextSchuelerId = null;
//        for (Zeugnis zeugnis : zeugnisse) {
//            // Sicherstellen, dass es immer einen selektierten Schüler gibt.
//            if (selectedSchuelerId == null) {
//                selectedSchuelerId = zeugnis.getSchueler().getId();
//            }
//
//            schuelerListe.add(zeugnis.getSchueler());
//
//            if ((selectedZeugnis != null) && (nextSchuelerId == null)) {
//                nextSchuelerId = zeugnis.getSchueler().getId();
//            }
//
//            if (selectedSchuelerId.equals(zeugnis.getSchueler().getId())) {
//                selectedZeugnis = zeugnis;
//            }
//
//            if (selectedZeugnis == null) {
//                prevSchuelerId = zeugnis.getSchueler().getId();
//            }
//        }
//
//        if (selectedZeugnis == null) {
//            redirectAttributes.addFlashAttribute("message",
//                    "Der angegebene Schüler hat kein Zeugnis, gehe zum ersten.");
//            return URL.redirect(URL.ZeugnisPath.SHOW, Long.valueOf(halbjahrId),
//                    Long.valueOf(klassenId));
//        }
//
//        Collections.sort(selectedZeugnis.getBewertungen());
//        Collections.sort(selectedZeugnis.getSchulamtsBemerkungen());
//        Collections.sort(selectedZeugnis.getBemerkungen());
//
//        final List<Bewertung> wpBewertungen = new ArrayList<>();
//        final List<Bewertung> otherBewertungen = new ArrayList<>();
//        for (Bewertung bewertung : selectedZeugnis.getBewertungen()) {
//            if (Schulfachtyp.WAHLPFLICHT.equals(bewertung.getSchulfach()
//                    .getTyp())) {
//                wpBewertungen.add(bewertung);
//            } else {
//                otherBewertungen.add(bewertung);
//            }
//        }
//
//        LOG.debug("Zeugnis von Schueler {}. ", selectedZeugnis.getSchueler());
//        model.addAttribute("schuelerListe", schuelerListe);
//        model.addAttribute("zeugnis", selectedZeugnis);
//        model.addAttribute("prevSchuelerId", prevSchuelerId);
//        model.addAttribute("nextSchuelerId", nextSchuelerId);
//        model.addAttribute("wpBewertungen", wpBewertungen);
//        model.addAttribute("otherBewertungen", otherBewertungen);
//        model.addAttribute("urlShowZeugnis", URL.filledURL(URL.ZeugnisPath
//                .SHOW, Long.valueOf(halbjahrId), Long.valueOf(klassenId)));
//        model.addAttribute("urlPrintZeugnis", URL.filledURL(URL.Zeugnis
//                .ONE_PDF, selectedZeugnis.getSchueler().getId(), Long.valueOf(
//                halbjahrId)));
//        model.addAttribute("arbeitsgruppenSatz", selectedZeugnis
//                .createArbeitsgruppenSatz());
        return "zeugnis/showZeugnis";
    }

    /**
     * Zeigt die Bewertungen der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Bewertungen.SHOW, method = RequestMethod.GET)
    public String showBewertungen(@RequestParam(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId, @RequestParam(URL.Session
            .P_KLASSEN_ID) Long klassenId, Model model,
            RedirectAttributes redirectAttributes) {
        return URL.redirect(URL.BewertungenPath.SHOW, halbjahrId, klassenId);
    }

}
