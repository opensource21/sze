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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller zur Erfassung der {@link Bemerkung}.
 * TODOs
 * <ul>
 * <li> Create Anlegen und "Anlegen und weiter" Abbrechen
 * <li> Bearbeiten Speichern, Erneut laden, Löschen! Abbrechen
 * <li> Anzeige Bearbeiten, Löschen Abbrechen
 *
 * </ul>
 *
 */
@Controller
public class BemerkungController {


    /**
     * Comment for <code>BEMERKUNG_FORM</code>
     */
    private static final String BEMERKUNG_FORM = "bemerkung/editBemerkung";


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
        model.addAttribute("saveUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CREATE, halbjahrId, klassenId, schuelerId));

        return BEMERKUNG_FORM;
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
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            model.addAttribute("saveUrl", URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CREATE, halbjahrId, klassenId, schuelerId));
            fillModel(model, halbjahrId, klassenId, schuelerId, bemerkung);
            return BEMERKUNG_FORM;
        }

        LOG.debug("Create Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        final String nextUrl;
        if (StringUtils.equalsIgnoreCase(action, Common.ACTION_NEXT)) {
            nextUrl = URL.redirect(URL.ZeugnisPath.BEMERKUNG_CREATE,
                    halbjahrId, klassenId, schuelerId);
        } else {
            nextUrl = createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
        }

        return nextUrl;
    }

    @RequestMapping(value = URL.ZeugnisPath.BEMERKUNG_CANCEL, method = RequestMethod.POST)
    public String insertBemerkung(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId) {
        return createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Erstellt die URL um zurück auf die Show-Zeugnismaske zu gehen.
     * @param halbjahrId Id des Schulhalbjahres.
     * @param klassenId Id der Klasse
     * @param schuelerId Id des Schülers.
     * @return die Url
     */
    private String createRedirectToZeugnisUrl(Long halbjahrId, Long klassenId,
            Long schuelerId) {
        return URL.redirect(URL.ZeugnisPath.SHOW +"?" +
                URL.Session.P_SCHUELER_ID + "=" + schuelerId, halbjahrId, klassenId);
    }


    private void fillModel(Model model, Long halbjahrId,
            Long klassenId, Long schuelerId, Bemerkung bemerkung) {
        final List<BemerkungsBaustein> bemerkungsBausteine =
                bemerkungService.getAllBausteine(bemerkung);
        model.addAttribute("bemerkung", bemerkung);
        model.addAttribute("schulhalbjahr", schulhalbjahrService.read(halbjahrId));
        model.addAttribute("bemerkungsBausteine", bemerkungsBausteine);
        model.addAttribute("helpMessageId", "help.bemerkung.edit");
        model.addAttribute("cancelUrl",
                URL.filledURL(URL.ZeugnisPath.BEMERKUNG_CANCEL, halbjahrId, klassenId, schuelerId));
    }

}
