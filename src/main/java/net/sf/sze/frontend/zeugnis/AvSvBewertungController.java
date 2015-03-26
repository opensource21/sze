//AvSvController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.zeugnis;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.AssertValid;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.frontend.base.URL.Common;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AvSvBewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.AvSvNote;
import net.sf.sze.service.api.stammdaten.SchuelerList;
import net.sf.sze.service.api.stammdaten.SchuelerService;
import net.sf.sze.service.api.zeugnis.AvSvBewertungService;
import net.sf.sze.service.api.zeugnis.ZeugnisErfassungsService;

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
 * Controller zum Bearbeiten der AvSvBewertung.
 *
 */
@Controller
public class AvSvBewertungController {


    /**
     * Der {@link ZeugnisErfassungsService}.
     */
    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    /**
     * Der {@link AvSvBewertungService}.
     */
    @Resource
    private AvSvBewertungService avSvBewertungService;

    @Resource
    private SchuelerService schuelerService;

    /**
     * Der Validator.
     */
    @Resource
    private Validator validator;

    private static final String EDIT_ZEUGNIS_AV_SV_VIEW = "avSvBewertung/editAvSvBewertungen";


    /**
     * Zeigt die AG-Bewertungen an.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_AV_SV, method = RequestMethod.GET)
    public String editArbeitsgruppen(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        final Zeugnis zeugnis = zeugnisErfassungsService.getZeugnis(halbjahrId,
                schuelerId);
        final SchuelerList schuelerList = schuelerService.getSchuelerWithZeugnis(
                halbjahrId.longValue(), klassenId.longValue(), schuelerId);
        final AvSvForm avSvForm = new AvSvForm(zeugnis.getAvSvBewertungen(),
                zeugnis.getSchueler(), zeugnis.getFormular());
        Collections.sort(avSvForm.getAvSvBewertungen());
        fillModel(model, avSvForm, halbjahrId, klassenId, schuelerId,
                schuelerList.getPrevSchuelerId(), schuelerList.getNextSchuelerId());
        return EDIT_ZEUGNIS_AV_SV_VIEW;
    }

    /**
     * @param model
     * @param avSvForm
     */
    private void fillModel(Model model, AvSvForm avSvForm, Long halbjahrId,
            Long klassenId, Long schuelerId, Long prevId, Long nextId) {

        model.addAttribute("avSvForm", avSvForm);
        model.addAttribute("noten", AvSvNote.values());
        model.addAttribute("updateUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.ZEUGNIS_EDIT_AV_SV,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));
        model.addAttribute(Common.P_PREV_ID, prevId);
        model.addAttribute(Common.P_NEXT_ID, nextId);
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId,
                klassenId, schuelerId));
    }

    /**
     * Speichert die neu angelegte AG-Bewertungen.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param action String der angibt was zu tun ist.
     * @param prevId die Id des vorherigen Schülers
     * @param nextId die Id des nachfolgenden Schülers
     * @param avSvForm Datencontainer für die AvSv-Daten.
     * @param result das Bindingresult.
     * @param model das Model
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ZEUGNIS_EDIT_AV_SV, method = RequestMethod.POST)
    public String updateArbeitsgruppen(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @RequestParam(Common.P_PREV_ID) Long prevId,
            @RequestParam(Common.P_NEXT_ID) Long nextId,
            @RequestParam(value = Common.P_ACTION, required = false) String action,
            @ModelAttribute("avSvForm") AvSvForm avSvForm,
            BindingResult result, Model model) {
        validator.validate(avSvForm, result);
        //Die Validierung kann eigentlich nicht schiefgehen. Außerdem bekommt
        //man nur einen "Field error in object 'avSvForm' on field 'avSvBewertungen'"
        //Man bräuchte aber avSvBwertungen[0].beurteilung
        if (result.hasErrors()) {
            fillModel(model, avSvForm, halbjahrId, klassenId, schuelerId, prevId, nextId);
            return EDIT_ZEUGNIS_AV_SV_VIEW;
        }

        avSvBewertungService.save(avSvForm.getAvSvBewertungen());
        return URL.getPrevNextUrlOrZeugnisUrl(action,
                URL.ZeugnisPath.ZEUGNIS_EDIT_AV_SV, halbjahrId,
                klassenId, schuelerId, prevId, nextId);
    }

    /**
     * Container für die AvSv-Daten.
     *
     */
    public static class AvSvForm {
        @AssertValid
        private List<AvSvBewertung> avSvBewertungen;

        private Schueler schueler;
        private ZeugnisFormular formular;


        /**
         * Initiates an object of type AvSvForm.
         */
        public AvSvForm() {

        }


        /**
         * Initiates an object of type AvSvForm.
         * @param avSvBewertungen die Bewertungen.
         * @param schueler der Schüler
         * @param formular das Zeugnisformular.
         */
        public AvSvForm(List<AvSvBewertung> avSvBewertungen, Schueler schueler,
                ZeugnisFormular formular) {
            super();
            this.avSvBewertungen = avSvBewertungen;
            this.schueler = schueler;
            this.formular = formular;
        }
        /**
         * @return the avSvBewertungen
         */
        public List<AvSvBewertung> getAvSvBewertungen() {
            return avSvBewertungen;
        }
        /**
         * @param avSvBewertungen the avSvBewertungen to set
         */
        public void setAvSvBewertungen(List<AvSvBewertung> avSvBewertungen) {
            this.avSvBewertungen = avSvBewertungen;
        }
        /**
         * @return the schueler
         */
        public Schueler getSchueler() {
            return schueler;
        }
        /**
         * @param schueler the schueler to set
         */
        public void setSchueler(Schueler schueler) {
            this.schueler = schueler;
        }


        /**
         * @return the formular
         */
        public ZeugnisFormular getFormular() {
            return formular;
        }


        /**
         * @param formular the formular to set
         */
        public void setFormular(ZeugnisFormular formular) {
            this.formular = formular;
        }


    }


}
