//BewertungenController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.zeugnis;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.frontend.base.URL.Common;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.DreiNiveauBewertung;
import net.sf.sze.model.zeugnis.StandardBewertung;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.zeugnis.BewertungErfassungsService;
import net.sf.sze.service.api.zeugnis.BewertungService;
import net.sf.sze.service.api.zeugnis.BewertungWithNeigbors;
import net.sf.sze.service.api.zeugnisconfig.SchulhalbjahrService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller zur Erfassung der Bewertungen.
 *
 */
@Controller
public class BewertungenController implements ModelAttributes {



    /**
     * Die View um eine Bewertung zu editieren im Rahmen der Bewertungenerfassung.
     */
    private static final String BEWERTUNGEN_EDIT_BEWERTUNG_VIEW =
            "bewertungen/editBewertung";


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
     * Der {@link SchulhalbjahrService}.
     */
    @Resource
    private SchulhalbjahrService schulhalbjahrService;

    /**
     * Der {@link BewertungService}.
     */
    @Resource
    private BewertungService bewertungService;

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
    public String showBewertungenPath(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @RequestParam(value = URL.Session.P_SCHULFACH_ID, required = false)
            Long schulfachId, Model model,
            RedirectAttributes redirectAttributes) {

        final Klasse klasse = bewertungErfassungsService.getKlasse(klassenId.longValue());
        final Schulhalbjahr schulhalbjahr = schulhalbjahrService.read(halbjahrId);

        if (!schulhalbjahr.isSelectable()) {
            redirectAttributes.addFlashAttribute("message",
                    "Das Schulhalbjahr ist nicht mehr selektierbar.");
            return URL.redirectWithNamedParams(URL.ZeugnisPath.START,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId);
        }
        final List<Schulfach> schulfaecher = bewertungErfassungsService.
                getActiveSchulfaecherOrderByName(schulhalbjahr, klasse);

        if (CollectionUtils.isEmpty(schulfaecher)) {
            redirectAttributes.addFlashAttribute("message",
                    "Es wurden keine Schulfächer gefunden.");
            return URL.redirectWithNamedParams(URL.ZeugnisPath.START,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId);
        }
        final Long usedSchulfachId;
        if (schulfachId == null) {
            LOG.debug("Nehme das erste Schulfach");
            usedSchulfachId = schulfaecher.get(0).getId();
        } else {
            usedSchulfachId = schulfachId;
        }

        final List<Bewertung> bewertungen = bewertungErfassungsService.
                getSortedBewertungen(schulhalbjahr.getId().longValue(),
                klasse.getId().longValue(), usedSchulfachId.longValue());

        model.addAttribute("bewertungen", bewertungen);
        model.addAttribute("schulfaecher", schulfaecher);
        model.addAttribute("klasse", klasse);
        model.addAttribute("schulhalbjahr", schulhalbjahr);
        model.addAttribute(URL.Session.P_SCHULFACH_ID, usedSchulfachId);
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
        return URL.redirectWithNamedParams(URL.BewertungenPath.LIST,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId);
    }


    /**
     * Zeigt die Bewertung zu der entsprechenden Id.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfaches.
     * @param bewertungsId die Id der Bewertung
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.EDIT, method = RequestMethod.GET)
    public String editBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHULFACH_ID) Long schulfachId,
            @PathVariable(value = URL.BewertungenPath.P_BEWERTUNGS_ID)
            Long bewertungsId, Model model) {

        BewertungWithNeigbors bewertungWithNeigbors = bewertungErfassungsService.
                getBewertungWithNeighbors(halbjahrId, klassenId, schulfachId, bewertungsId);
        setEditModelValues(halbjahrId, klassenId, schulfachId,
                bewertungWithNeigbors.getBewertung(),
                bewertungWithNeigbors.getPrevBewertungsId(),
                bewertungWithNeigbors.getNextBewertungsId(), model);
        return BEWERTUNGEN_EDIT_BEWERTUNG_VIEW;
    }


    /**
     * Aktualisiert die {@link StandardBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfachs.
     * @param prevId die Id der vorherigen Bewertung.
     * @param nextId die Id der nachfolgenden Bewertung.
     * @param bewertung die Bewertung
     * @param action die als nächstes auszuführende Aktion.
     * @param result das Bindingresult.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.EDIT, method = RequestMethod.POST,
            params = "type=standard")
    public String updateStandardBewertung(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHULFACH_ID) Long schulfachId,
            @RequestParam(Common.P_PREV_ID) Long prevId,
            @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value = Common.P_ACTION, required = false) String action,
            @ModelAttribute(BEWERTUNG) StandardBewertung bewertung,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schulfachId, bewertung,
                prevId, nextId, action, result, model, redirectAttributes);
    }

    /**
     * Aktualisiert die {@link AussenDifferenzierteBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfachs.
     * @param prevId die Id der vorherigen Bewertung.
     * @param nextId die Id der nachfolgenden Bewertung.
     * @param bewertung die Bewertung
     * @param action die als nächstes auszuführende Aktion.
     * @param result das Bindingresult.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.EDIT, method = RequestMethod.POST,
            params = "type=2niveau")
    public String update2NiveauBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHULFACH_ID) Long schulfachId,
            @RequestParam(Common.P_PREV_ID) Long prevId,
            @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value = Common.P_ACTION, required = false) String action,
            @ModelAttribute(BEWERTUNG) AussenDifferenzierteBewertung bewertung,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schulfachId, bewertung,
                prevId, nextId, action, result, model, redirectAttributes);
    }

    /**
     * Aktualisiert die {@link DreiNiveauBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfachs.
     * @param prevId die Id der vorherigen Bewertung.
     * @param nextId die Id der nachfolgenden Bewertung.
     * @param bewertung die Bewertung
     * @param action die als nächstes auszuführende Aktion.
     * @param result das Bindingresult.
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.EDIT,
            method = RequestMethod.POST, params = "type=3niveau")
    public String update3NiveauBewertung(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHULFACH_ID) Long schulfachId,
            @ModelAttribute(BEWERTUNG)DreiNiveauBewertung bewertung,
            @RequestParam(Common.P_PREV_ID) Long prevId,
            @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value = Common.P_ACTION, required = false) String action,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schulfachId, bewertung,
                prevId, nextId, action, result, model, redirectAttributes);
    }


    /**
     * Aktualisiert die Bewertung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfachs
     * @param bewertung die Bewertung
     * @param action String der angibt was zu tun ist.
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    private String updateBewertung(Long halbjahrId, Long klassenId,
            Long schulfachId, Bewertung bewertung, Long prevId, Long nextId,
            String action, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        validator.validate(bewertung, result);

        if (result.hasErrors()) {
            LOG.info("Die Bewertung hat Fehler {}", result);
            setEditModelValues(halbjahrId, klassenId, schulfachId, bewertung,
                    prevId, nextId, model);
            return BEWERTUNGEN_EDIT_BEWERTUNG_VIEW;
        }
        bewertungService.save(bewertung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_PREV)) {
            nextUrl = URL.redirectWithNamedParams(URL.BewertungenPath.EDIT,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHULFACH_ID, schulfachId,
                    URL.BewertungenPath.P_BEWERTUNGS_ID, prevId);
        } else if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirectWithNamedParams(URL.BewertungenPath.EDIT,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHULFACH_ID, schulfachId,
                    URL.BewertungenPath.P_BEWERTUNGS_ID, nextId);

        } else {
            redirectAttributes.addFlashAttribute(Common.P_LASTEDITED_ID, bewertung.getId());
            nextUrl = createRedirectToList(halbjahrId, klassenId, schulfachId);
        }

        return nextUrl;
    }

    /**
     * @param halbjahrId die Id des Halbjahrs.
     * @param klassenId die Id der Klassse
     * @param schulfachId die Id des Schulfachs.
     * @param model das Model
     * @param bewertungValues eine Bewertung die direkten Werte enthält.
     */
    private void setEditModelValues(Long halbjahrId, Long klassenId,
            Long schulfachId, Bewertung bewertung, Long prevId, Long nextId, Model model) {
        final String type;
        if (bewertung instanceof DreiNiveauBewertung) {
            type = "3niveau";
        } else if (bewertung instanceof AussenDifferenzierteBewertung) {
            type = "2niveau";
        } else {
            type = "standard";
        }
        model.addAttribute(BEWERTUNG, bewertung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute(Common.P_PREV_ID, prevId);
        model.addAttribute(Common.P_NEXT_ID, nextId);
        model.addAttribute("saveUrl",
                URL.filledURLWithNamedParams(URL.BewertungenPath.EDIT,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHULFACH_ID, schulfachId,
                URL.BewertungenPath.P_BEWERTUNGS_ID, bewertung.getId()));
        model.addAttribute("cancelUrl",
                URL.filledURLWithNamedParams(URL.BewertungenPath.CANCEL,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHULFACH_ID, schulfachId,
                URL.BewertungenPath.P_BEWERTUNGS_ID, bewertung.getId()));
        model.addAttribute("type", type);
    }


    /**
     * @param halbjahrId
     * @param klassenId
     * @param schulfachId
     * @return
     */
    private String createRedirectToList(Long halbjahrId, Long klassenId,
            final Long schulfachId) {
        return URL.redirectWithNamedParams(URL.BewertungenPath.LIST + "?"
                + URL.Session.P_SCHULFACH_ID + "=" + schulfachId ,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId);
    }


    /**
     * Bricht die Bearbeitung einer Bewertung ab.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfachs.
     * @param redirectAttributes Fehlermeldungen.
     * @param bewertungsId die Id der Bewertung.
     * @return die logische View
     */
    @RequestMapping(value = URL.BewertungenPath.CANCEL, method = RequestMethod.POST)
    public String cancelEditBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHULFACH_ID) Long schulfachId,
            @PathVariable(URL.BewertungenPath.P_BEWERTUNGS_ID) Long bewertungsId,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Common.P_LASTEDITED_ID, bewertungsId);
        return createRedirectToList(halbjahrId, klassenId, schulfachId);
    }

}
