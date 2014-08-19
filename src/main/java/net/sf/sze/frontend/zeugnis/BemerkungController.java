//BemerkungController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.zeugnis;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.frontend.base.URL.Common;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnisconfig.BemerkungsBaustein;
import net.sf.sze.service.api.BemerkungService;
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
 * Controller zur Erfassung der {@link Bemerkung}.
 *
 */
@Controller
public class BemerkungController implements ModelAttributes {


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
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CREATE, method = RequestMethod.GET)
    public String createBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId, Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId,
                schuelerId);
        final Bemerkung bemerkung = new Bemerkung();
        bemerkung.setZeugnis(zeugnis);
        fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
        model.addAttribute("insertUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_CREATE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));

        return EDIT_BEMERKUNG_VIEW;
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param bemerkung die zu speichernde Bemerkung.
     * @param action die als nächstes auszuführende Aktion.
     * @param model das Model
     * @param result das Bindingresult.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CREATE, method = RequestMethod.POST)
    public String insertBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @ModelAttribute(BEMERKUNG) Bemerkung bemerkung,
            @RequestParam(value = URL.Common.P_ACTION, required = false) String action,
            BindingResult result, Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            model.addAttribute("insertUrl", URL.filledURLWithNamedParams(
                    URL.ZeugnisPath.BEMERKUNG_CREATE,
                    URL.Session.P_HALBJAHR_ID, halbjahrId,
                    URL.Session.P_KLASSEN_ID, klassenId,
                    URL.Session.P_SCHUELER_ID, schuelerId));
            fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
            return EDIT_BEMERKUNG_VIEW;
        }

        LOG.debug("Create Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirectWithNamedParams(URL.ZeugnisPath.BEMERKUNG_CREATE,
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
     * @param bemerkungsId die Id der Bemerkung
     * @param model das Model
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
        model.addAttribute(BEMERKUNG, bemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId,
                klassenId, schuelerId));
        model.addAttribute("editUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_EDIT,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_BEMERKUNGS_ID, bemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_DELETE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_BEMERKUNGS_ID, bemerkungsId));
        return "bemerkung/showBemerkung";
    }

    /**
     * Zeigt die Bemerkung des entsprechenden Faches der Klasse in dem Halbjahr.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param bemerkungsId die Id der Bemerkung
     * @param model das Model
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
        fillModelWithUpdateAndDeleteURLs(model, halbjahrId, klassenId,
                schuelerId, bemerkungsId);
        return EDIT_BEMERKUNG_VIEW;
    }

    /**
     * @param model
     * @param halbjahrId
     * @param klassenId
     * @param schuelerId
     * @param bemerkungsId
     */
    private void fillModelWithUpdateAndDeleteURLs(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, Long bemerkungsId) {
        model.addAttribute("updateUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_EDIT,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_BEMERKUNGS_ID, bemerkungsId));
        model.addAttribute("deleteUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_DELETE,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId,
                URL.ZeugnisPath.P_BEMERKUNGS_ID, bemerkungsId));
    }

    /**
     * Speichert die neu angelegte Bemerkung.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param bemerkungsId die Id der Bemerkung
     * @param bemerkung die zu speichernde Bemerkung.
     * @param action die als nächstes auszuführende Aktion.
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_EDIT, method = RequestMethod.POST)
    public String updateBemerkung(
            @PathVariable(URL.Session.P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @PathVariable(URL.ZeugnisPath.P_BEMERKUNGS_ID) Long bemerkungsId,
            @ModelAttribute(BEMERKUNG) Bemerkung bemerkung, BindingResult result,
            @RequestParam(value = URL.Common.P_ACTION, required = false) String action,
             Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            fillModelWithUpdateAndDeleteURLs(model, halbjahrId, klassenId,
                    schuelerId, bemerkungsId);
            fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
            return EDIT_BEMERKUNG_VIEW;
        }

        LOG.debug("Update Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Breche die Bearbeitung der Bemerkung ab.
     * @param halbjahrId Id des {@link Schulhalbjahr}.
     * @param klassenId Id der {@link Klasse}.
     * @param schuelerId Id des {@link Schueler}.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CANCEL, method = RequestMethod.POST)
    public String cancelEditBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId) {
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Löscht die Bemerkung.
     * @param halbjahrId Id des {@link Schulhalbjahr}.
     * @param klassenId Id der {@link Klasse}.
     * @param schuelerId Id des {@link Schueler}.
     * @param bemerkungsId die Id der zu löschenden {@link Bemerkung}.
     * @return die anzuzeigende View.
     */
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
        model.addAttribute(BEMERKUNG, bemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("cancelUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.BEMERKUNG_CANCEL,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));
        model.addAttribute("bemerkungsBausteine", bemerkungsBausteine);
        model.addAttribute("helpMessageId", "help.bemerkung.edit");
    }

}
