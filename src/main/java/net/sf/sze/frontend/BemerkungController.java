//BemerkungController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.frontend.URL.Common;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnis.BemerkungsBaustein;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.BemerkungService;
import net.sf.sze.service.api.BemerkungsBausteineService;
import net.sf.sze.service.api.SchulhalbjahrService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller zur Erfassung der {@link Bemerkung}.
 *
 */
@Controller
public class BemerkungController {


    /**
     * View zum Editieren der Bemerkung.
     */
    private static final String EDIT_BEMERKUNG_VIEW = "bemerkung/editBemerkung";


    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(BemerkungController.class);


    /**
     * Der {@link BemerkungService}.
     */
    @Resource
    private BemerkungService bemerkungService;

    /**
     * Der {@link BemerkungsBausteineService}.
     */
    @Resource
    private BemerkungsBausteineService bemerkungsBausteineService;


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
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CREATE, method = RequestMethod.GET)
    public String createBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId, Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId, klassenId, schuelerId);
        final Bemerkung bemerkung = new Bemerkung();
        bemerkung.setZeugnis(zeugnis);
        fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
        model.addAttribute("insertUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CREATE, halbjahrId, klassenId, schuelerId));

        return EDIT_BEMERKUNG_VIEW;
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CREATE, method = RequestMethod.POST)
    public String insertBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Bemerkung bemerkung, @RequestParam(value=URL.Common.P_ACTION, required=false) String action,
            BindingResult result, Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            model.addAttribute("insertUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CREATE, halbjahrId, klassenId, schuelerId));
            fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
            return EDIT_BEMERKUNG_VIEW;
        }

        LOG.debug("Create Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirect(URL.ZeugnisPath.BEMERKUNG_CREATE,
                    halbjahrId, klassenId, schuelerId);
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
     * @param bemerkungsId die Id der Bemerkung
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_SHOW, method = RequestMethod.GET)
    public String showBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEMERKUNGS_ID) Long bemerkungsId,
            Model model) {
        final Bemerkung bemerkung = bemerkungService.read(bemerkungsId);
        model.addAttribute("bemerkung", bemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId, klassenId, schuelerId));
        model.addAttribute("editUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_EDIT, halbjahrId, klassenId, schuelerId, bemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_DELETE, halbjahrId, klassenId, schuelerId, bemerkungsId));
        return "bemerkung/showBemerkung";
    }

    /**
     * Zeigt die Bemerkung des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param bemerkungsId die Id der Bemerkung
     * @param model das Model
     * @param redirectAttributes Fehlermeldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_EDIT, method = RequestMethod.GET)
    public String editBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEMERKUNGS_ID) Long bemerkungsId,
            Model model) {
        final Bemerkung bemerkung = bemerkungService.read(bemerkungsId);
        fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
        model.addAttribute("updateUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_EDIT, halbjahrId, klassenId, schuelerId, bemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_DELETE, halbjahrId, klassenId, schuelerId, bemerkungsId));
        return EDIT_BEMERKUNG_VIEW;
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param bemerkungsId die Id der Bemerkung
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_EDIT, method = RequestMethod.POST)
    public String updateBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEMERKUNGS_ID) Long bemerkungsId,
            Bemerkung bemerkung, @RequestParam(value=URL.Common.P_ACTION, required=false) String action,
            BindingResult result, Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            model.addAttribute("updateUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_EDIT, halbjahrId, klassenId, schuelerId, bemerkungsId));
            model.addAttribute("deleteUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_DELETE, halbjahrId, klassenId, schuelerId, bemerkungsId));
            fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
            return EDIT_BEMERKUNG_VIEW;
        }

        LOG.debug("Update Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CANCEL, method = RequestMethod.POST)
    public String cancelEditBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId) {
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_DELETE, method = RequestMethod.POST)
    public String deleteBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEMERKUNGS_ID) Long bemerkungsId) {
        bemerkungService.delete(bemerkungsId);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }


    private void fillModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, Bemerkung bemerkung) {
        final List<BemerkungsBaustein> bemerkungsBausteine =
                bemerkungService.getAllBausteine(bemerkung);
        model.addAttribute("bemerkung", bemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl",
                URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CANCEL, halbjahrId, klassenId, schuelerId));
        model.addAttribute("bemerkungsBausteine", bemerkungsBausteine);
        model.addAttribute("helpMessageId", "help.bemerkung.edit");
    }

}
