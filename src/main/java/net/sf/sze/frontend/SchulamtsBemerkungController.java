//BemerkungController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.frontend.URL.Common;
import net.sf.sze.model.zeugnis.Schulamt;
import net.sf.sze.model.zeugnis.SchulamtsBemerkung;
import net.sf.sze.model.zeugnis.SchulamtsBemerkungsBaustein;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.BemerkungsBausteineService;
import net.sf.sze.service.api.SchulamtsBemerkungService;
import net.sf.sze.service.api.SchulhalbjahrService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller zur Erfassung der {@link SchulamtsBemerkung}.
 *
 */
@Controller
public class SchulamtsBemerkungController {


    /**
     * View zum Editieren der Bemerkung.
     */
    private static final String EDIT_SCHULAMTS_BEMERKUNG_VIEW =
            "schulamtsBemerkung/editSchulamtsBemerkung";


    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(SchulamtsBemerkungController.class);


    /**
     * Der {@link SchulamtsBemerkungService}.
     */
    @Resource
    private SchulamtsBemerkungService schulamtsBemerkungService;

    /**
     * Der {@link BemerkungsBausteineService}.
     */
    @Resource
    private BemerkungsBausteineService schulamtsBemerkungsBausteineService;


    /**
     * Der {@link ZeugnisErfassungsService}.
     */
    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    /**
     * Der {@link SchulhalbjahrService}.
     */
    @Resource
    private SchulhalbjahrService schulhalbjahrService;

    /**
     * Der Validator.
     */
    @Resource
    private Validator validator;

    /**
     * Zeigt die Bemerkung des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CREATE, method = RequestMethod.GET)
    public String createSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId, Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(
                halbjahrId, klassenId, schuelerId);
        final SchulamtsBemerkung schulamtsBemerkung = new SchulamtsBemerkung();
        schulamtsBemerkung.setZeugnis(zeugnis);
        fillModel(model, halbjahrId, klassenId, schuelerId, schulamtsBemerkung);
        model.addAttribute("insertUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CREATE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));

        return EDIT_SCHULAMTS_BEMERKUNG_VIEW;
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param schulamtsBemerkung die Schulamtsbemerkung.
     * @param action die als nächstes auszuführende Aktion.
     * @param model das Model
     * @param result das Bindingresult.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CREATE, method = RequestMethod.POST)
    public String insertSchulamtsBemerkung(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @ModelAttribute("schulamtsBemerkung") SchulamtsBemerkung schulamtsBemerkung,
            BindingResult result,
            @RequestParam(value = URL.Common.P_ACTION, required = false) String action,
             Model model) {
        validator.validate(schulamtsBemerkung, result);

        if (result.hasErrors()) {
            LOG.info("Fehler beim Speichern der Schulamtsbemerkung: {}", result.getAllErrors());
            model.addAttribute("insertUrl", URL.filledURLWithNamedParams(
                    URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CREATE,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHUELER_ID, schuelerId));
            fillModel(model, halbjahrId, klassenId, schuelerId, schulamtsBemerkung);
            return EDIT_SCHULAMTS_BEMERKUNG_VIEW;
        }

        LOG.debug("Create Bemerkung: " + schulamtsBemerkung);
        schulamtsBemerkungService.save(schulamtsBemerkung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirectWithNamedParams(
                    URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CREATE,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHUELER_ID, schuelerId);
        } else {
            nextUrl = URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
        }

        return nextUrl;
    }

    /**
     * Zeigt die Bemerkung des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param schulamtsBemerkungsId die Id der Bemerkung
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_SHOW, method = RequestMethod.GET)
    public String showSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID)
            Long schulamtsBemerkungsId, Model model) {
        final SchulamtsBemerkung schulamtsBemerkung =
                schulamtsBemerkungService.read(schulamtsBemerkungsId);
        model.addAttribute("bemerkung", schulamtsBemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(
                halbjahrId, klassenId, schuelerId));
        model.addAttribute("editUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_EDIT,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_DELETE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
        return "schulamtsBemerkung/showSchulamtsBemerkung";
    }

    /**
     * Zeigt die Bemerkung des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param schulamtsBemerkungsId die Id der Bemerkung
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_EDIT, method = RequestMethod.GET)
    public String editSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID) Long schulamtsBemerkungsId,
            Model model) {
        final SchulamtsBemerkung schulamtsBemerkung = schulamtsBemerkungService.
                read(schulamtsBemerkungsId);
        fillModel(model, halbjahrId, klassenId, schuelerId, schulamtsBemerkung);
        model.addAttribute("updateUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_EDIT,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_DELETE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
        return EDIT_SCHULAMTS_BEMERKUNG_VIEW;
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param schulamtsBemerkungsId die Id der Bemerkung
     * @param schulamtsBemerkung die SchulamtsBemerkung
     * @param result das Bindingresult.
     * @param action die als nächstes auszuführende Aktion.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_EDIT, method = RequestMethod.POST)
    public String updateSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID) Long schulamtsBemerkungsId,
            SchulamtsBemerkung schulamtsBemerkung, BindingResult result,
            @RequestParam(value = URL.Common.P_ACTION, required = false)
            String action,
            Model model) {
        validator.validate(schulamtsBemerkung, result);

        if (result.hasErrors()) {
            model.addAttribute("updateUrl", URL.filledURLWithNamedParams(
                    URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_EDIT,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHUELER_ID, schuelerId,
                    URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
            model.addAttribute("deleteUrl", URL.filledURLWithNamedParams(
                    URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_DELETE,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHUELER_ID, schuelerId,
                    URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID, schulamtsBemerkungsId));
            fillModel(model, halbjahrId, klassenId, schuelerId, schulamtsBemerkung);
            return EDIT_SCHULAMTS_BEMERKUNG_VIEW;
        }

        LOG.debug("Update Bemerkung: " + schulamtsBemerkung);
        schulamtsBemerkungService.save(schulamtsBemerkung);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Brichte die Bearbeitung des SchulamtsBemerkung ab.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CANCEL, method = RequestMethod.POST)
    public String cancelEditSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId) {
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }


    /**
     * Löscht die Schulamts-Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param schulamtsBemerkungsId die Id der Bemerkung
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_DELETE, method = RequestMethod.POST)
    public String deleteSchulamtsBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_SCHULAMTS_BEMERKUNGS_ID) Long schulamtsBemerkungsId) {
        schulamtsBemerkungService.delete(schulamtsBemerkungsId);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }


    private void fillModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, SchulamtsBemerkung schulamtsBemerkung) {
        final List<SchulamtsBemerkungsBaustein> schulamtsBemerkungsBausteine =
                schulamtsBemerkungService.getAllSchulamtsBemerkungsBausteine(schulamtsBemerkung);
        final List<Schulamt> schulaemter =
                schulamtsBemerkungService.getAllSchulaemter(schulamtsBemerkung);
        model.addAttribute("schulamtsBemerkung", schulamtsBemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.SCHULAMTS_BEMERKUNG_CANCEL,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));
        model.addAttribute("bemerkungsBausteine", schulamtsBemerkungsBausteine);
        model.addAttribute("schulaemter", schulaemter);
        model.addAttribute("helpMessageId", "help.schulamtsBemerkung.edit");
    }

}
