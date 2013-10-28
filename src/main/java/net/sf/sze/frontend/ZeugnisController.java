// ZeugnisController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.frontend.URL.Common;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.BinnenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.StandardBewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.AgBewertungService;
import net.sf.sze.service.api.BewertungService;
import net.sf.sze.service.api.BewertungWithNeigbors;
import net.sf.sze.service.api.SchulhalbjahrService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
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
     * Die View um eine Bewertung zu editieren im Rahmen der Bewertungenerfassung.
     */
    private static final String BEWERTUNGEN_EDIT_BEWERTUNG_VIEW =
            "bewertungen/editBewertung";

    /**
     * Die View zur Bearbeitung der Zeugnisdetails.
     */
    private static final String EDIT_ZEUGNIS_DETAIL_VIEW = "zeugnis/editDetail";

    /**
     * Die View zur Bearbeitung Arbeitsgruppen.
     */
    private static final String EDIT_ZEUGNIS_AGS = "zeugnis/editArbeitsgruppen";

    /**
     * Die View zur Bearbeitung des BU-Textes und SoL.
     */
    private static final String EDIT_ZEUGNIS_BU_SOL = "zeugnis/editBUundSoL";

    /**
     * Der {@link ZeugnisErfassungsService}.
     */
    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    /**
     * Der {@link BewertungService}.
     */
    @Resource
    private BewertungService bewertungService;

    /**
     * Der {@link SchulhalbjahrService}.
     */
    @Resource
    private SchulhalbjahrService schulhalbjahrService;
    /**
     * Der {@link AgBewertungService}.
     */
    @Resource
    private AgBewertungService agBewertungService;
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

        Collections.sort(selectedZeugnis.getSchulamtsBemerkungen());
        Collections.sort(selectedZeugnis.getBemerkungen());

        final List<Bewertung> wpBewertungen = new ArrayList<>();
        final List<Bewertung> otherBewertungen = new ArrayList<>();
        zeugnisErfassungsService.splitBewertungslist(
                selectedZeugnis.getBewertungen(), wpBewertungen, otherBewertungen);

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

    /**
     * Zeigt die Bewertung zu der entsprechenden Id.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId die Id des Schulfaches.
     * @param bewertung die Bewertung
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value= URL.ZeugnisPath.BEWERTUNG_EDIT, method = RequestMethod.GET)
    public String editBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schulfachId,
            @PathVariable(value = URL.ZeugnisPath.P_BEWERTUNGS_ID)
            Long bewertungsId, Model model) {

        BewertungWithNeigbors bewertungWithNeigbors = zeugnisErfassungsService.
                getBewertungWithNeighbors(bewertungsId);
        setEditBewertungModelValues(halbjahrId, klassenId, schulfachId,
                bewertungWithNeigbors.getBewertung(),
                bewertungWithNeigbors.getPrevBewertungsId(),
                bewertungWithNeigbors.getNextBewertungsId(), model);
        return BEWERTUNGEN_EDIT_BEWERTUNG_VIEW;
    }


    /**
     * Aktualisiert die {@link StandardBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param bewertung die Bewertung
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value= URL.ZeugnisPath.BEWERTUNG_EDIT, method = RequestMethod.POST, params= "type=standard")
    public String updateStandardBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @RequestParam(Common.P_PREV_ID) Long prevId, @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value=Common.P_ACTION, required=false) String action,
            StandardBewertung bewertung,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schuelerId, bewertung,
                prevId, nextId, action, result, model, redirectAttributes);
    }

    /**
     * Aktualisiert die {@link AussenDifferenzierteBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param bewertung die Bewertung
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value= URL.ZeugnisPath.BEWERTUNG_EDIT, method = RequestMethod.POST, params= "type=2niveau")
    public String update2NiveauBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @RequestParam(Common.P_PREV_ID) Long prevId, @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value=Common.P_ACTION, required=false) String action,
            AussenDifferenzierteBewertung bewertung,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schuelerId, bewertung,
                prevId, nextId, action, result, model, redirectAttributes);
    }

    /**
     * Aktualisiert die {@link BinnenDifferenzierteBewertung}.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param bewertung die Bewertung
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value= URL.ZeugnisPath.BEWERTUNG_EDIT, method = RequestMethod.POST, params="type=3niveau")
    public String update3NiveauBewertung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            BinnenDifferenzierteBewertung bewertung,
            @RequestParam(Common.P_PREV_ID) Long prevId, @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value=Common.P_ACTION, required=false) String action,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        return updateBewertung(halbjahrId, klassenId, schuelerId, bewertung,
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
            Long schuelerId, Bewertung bewertung, Long prevId, Long nextId,
            String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        validator.validate(bewertung, result);

        if (result.hasErrors()) {
            LOG.info("Die Bewertung hat Fehler {}", result);
            setEditBewertungModelValues(halbjahrId, klassenId, schuelerId, bewertung, prevId, nextId, model);
            return BEWERTUNGEN_EDIT_BEWERTUNG_VIEW;
        }
        bewertungService.save(bewertung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_PREV)) {
            nextUrl = URL.redirect(URL.ZeugnisPath.BEWERTUNG_EDIT,
                    halbjahrId, klassenId, schuelerId, prevId);
        } else if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirect(URL.ZeugnisPath.BEWERTUNG_EDIT,
                    halbjahrId, klassenId, schuelerId, nextId);

        } else {
            redirectAttributes.addFlashAttribute(Common.P_LASTEDITED_ID, bewertung.getId());
            nextUrl = URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
        }

        return nextUrl;
    }

    /**
     * @param halbjahrId die Id des Halbjahrs.
     * @param klassenId die Id der Klassse
     * @param schuelerId die Id des Schuelers.
     * @param model das Model
     * @param bewertungValues eine Bewertung die direkten Werte enthält.
     */
    private void setEditBewertungModelValues(Long halbjahrId, Long klassenId,
            Long schuelerId, Bewertung bewertung, Long prevId, Long nextId, Model model) {
        final String type;
        if (bewertung instanceof BinnenDifferenzierteBewertung) {
            type = "3niveau";
        } else if (bewertung instanceof AussenDifferenzierteBewertung) {
            type = "2niveau";
        } else {
            type = "standard";
        }
        model.addAttribute("bewertung", bewertung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute(Common.P_PREV_ID, prevId);
        model.addAttribute(Common.P_NEXT_ID, nextId);
        model.addAttribute("saveUrl", URL.filledURL(URL.ZeugnisPath.BEWERTUNG_EDIT,
                halbjahrId, klassenId, schuelerId, bewertung.getId()));
        model.addAttribute("cancelUrl", URL.filledURL(URL.ZeugnisPath.BEWERTUNG_CANCEL,
                halbjahrId, klassenId, schuelerId, bewertung.getId()));
        model.addAttribute("type", type);
        model.addAttribute("helpMessageId", "help.bewertung.edit");
    }


    /**
     * Bricht die Bearbeitung einer Bewertung ab.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schulfachId
     * @return die logische View
     */
    @RequestMapping(value= URL.ZeugnisPath.BEWERTUNG_CANCEL, method = RequestMethod.POST)
    public String cancelEditBewertung(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEWERTUNGS_ID) Long bewertungsId,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Common.P_LASTEDITED_ID, bewertungsId);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Zeigt die Zeugnisdetails an.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_DETAIL, method = RequestMethod.GET)
    public String editZeugnisDetail(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId, klassenId, schuelerId);
        fillZeugnisDetailModel(model, halbjahrId, klassenId, schuelerId,zeugnis);
        return EDIT_ZEUGNIS_DETAIL_VIEW;
    }

    /**
     * @param model
     * @param halbjahrId
     * @param klassenId
     * @param schuelerId
     * @param zeugnis
     */
    private void fillZeugnisDetailModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, final Zeugnis zeugnis) {
        model.addAttribute("zeugnis", zeugnis);
        model.addAttribute("zeugnisArten", zeugnisErfassungsService.getAllZeugnisArten(zeugnis));
        model.addAttribute("helpMessageId", "help.zeugnis.editDetail");
        model.addAttribute("updateUrl", URL.filledURL(URL.ZeugnisPath.ZEUGNIS_EDIT_DETAIL, halbjahrId, klassenId, schuelerId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId, klassenId, schuelerId));
    }

    /**
     * Speichert das veränderte Zeugnis.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_DETAIL, method = RequestMethod.POST)
    public String updateZeugnisDetail(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Zeugnis zeugnis, BindingResult result, Model model) {
        validator.validate(zeugnis, result);

        if (result.hasErrors()) {
            LOG.info("Fehler:" + result.getAllErrors());
            fillZeugnisDetailModel(model, halbjahrId, klassenId, schuelerId,
                    zeugnis);
            return EDIT_ZEUGNIS_DETAIL_VIEW;
        }

        LOG.debug("Update Zeugnis: " + zeugnis);
        zeugnisErfassungsService.save(zeugnis);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }


    /**
     * Zeigt die AG-Bewertungen an.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_AGS, method = RequestMethod.GET)
    public String editArbeitsgruppen(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId, klassenId, schuelerId);
        fillArbeitsgruppenModel(model, halbjahrId, klassenId, schuelerId,zeugnis);
        return EDIT_ZEUGNIS_AGS;
    }

    /**
     * @param model
     * @param halbjahrId
     * @param klassenId
     * @param schuelerId
     * @param zeugnis
     */
    private void fillArbeitsgruppenModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, final Zeugnis zeugnis) {
        Collections.sort(zeugnis.getAgBewertungen());
        model.addAttribute("zeugnis", zeugnis);
        model.addAttribute("updateUrl", URL.filledURL(URL.ZeugnisPath.ZEUGNIS_EDIT_AGS, halbjahrId, klassenId, schuelerId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId, klassenId, schuelerId));
    }

    /**
     * Speichert die neu angelegte AG-Bewertungen.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param newZeugnis als Container für die AG-Bewertungen.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_AGS, method = RequestMethod.POST)
    public String updateArbeitsgruppen(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Zeugnis newZeugnis, BindingResult result, Model model) {
        //Die Validierung ist nicht sinnvoll, da nichts schief gehen kann.
        //Wenn man eine Validierung wollte müsste man sicherstellen, dass auch
        //die Liste validiert wird, was so nicht erfolgt. Ein Form-Object wäre sinnvoll.
        agBewertungService.save(newZeugnis.getAgBewertungen());
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Zeigt die BU und SoL-Bewertung an.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_BU_SOL, method = RequestMethod.GET)
    public String editBuSoL(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId, klassenId, schuelerId);
        fillBuSoLModel(model, halbjahrId, klassenId, schuelerId,zeugnis);
        return EDIT_ZEUGNIS_BU_SOL;
    }

    /**
     * @param model
     * @param halbjahrId
     * @param klassenId
     * @param schuelerId
     * @param zeugnis
     */
    private void fillBuSoLModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, final Zeugnis zeugnis) {
        model.addAttribute("zeugnis", zeugnis);
        model.addAttribute("solBewertungsTexte", zeugnisErfassungsService.getSoLTexte(zeugnis));
        model.addAttribute("updateUrl", URL.filledURL(URL.ZeugnisPath.ZEUGNIS_EDIT_BU_SOL, halbjahrId, klassenId, schuelerId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId, klassenId, schuelerId));
    }

    /**
     * Speichert die neu angelegte BU und SoL-Bewertung .
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param newZeugnis als Container für die AG-Bewertungen.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_BU_SOL, method = RequestMethod.POST)
    public String updateBuSoL(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Zeugnis newZeugnis, BindingResult result, Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId, klassenId, schuelerId);
        zeugnis.setBuBewertungsText(newZeugnis.getBuBewertungsText());
        zeugnis.setSoLBewertungsText(newZeugnis.getSoLBewertungsText());

        validator.validate(zeugnis, result);
        if (result.hasErrors()) {
            LOG.info("Fehler:" + result.getAllErrors());
            fillBuSoLModel(model, halbjahrId, klassenId, schuelerId,
                    zeugnis);
            return EDIT_ZEUGNIS_BU_SOL;
        }

        LOG.debug("Update Zeugnis: " + zeugnis);
        zeugnisErfassungsService.save(zeugnis);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }
}
