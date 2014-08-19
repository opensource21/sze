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
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AvSvBewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnisconfig.AvSvNote;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.AvSvBewertungService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        AvSvForm avSvForm = new AvSvForm(zeugnis.getAvSvBewertungen(),
                zeugnis.getSchueler(), zeugnis.getKlasse(),
                zeugnis.getSchulhalbjahr());
        Collections.sort(avSvForm.getAvSvBewertungen());
        fillModel(model, avSvForm, halbjahrId, klassenId, schuelerId);
        return EDIT_ZEUGNIS_AV_SV_VIEW;
    }

    /**
     * @param model
     * @param avSvForm
     */
    private void fillModel(Model model, AvSvForm avSvForm, Long halbjahrId,
            Long klassenId, Long schuelerId) {

        model.addAttribute("avSvForm", avSvForm);
        model.addAttribute("noten", AvSvNote.values());
        model.addAttribute("updateUrl", URL.filledURLWithNamedParams(
                URL.ZeugnisPath.ZEUGNIS_EDIT_AV_SV,
                URL.Session.P_HALBJAHR_ID, halbjahrId,
                URL.Session.P_KLASSEN_ID, klassenId,
                URL.Session.P_SCHUELER_ID, schuelerId));
        model.addAttribute("cancelUrl", URL.createLinkToZeugnisUrl(halbjahrId,
                klassenId, schuelerId));
    }

    /**
     * Speichert die neu angelegte AG-Bewertungen.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
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
            @ModelAttribute("avSvForm") AvSvForm avSvForm,
            BindingResult result, Model model) {
        validator.validate(avSvForm, result);
        //Die Validierung kann eigentlich nicht schiefgehen. Außerdem bekommt
        //man nur einen "Field error in object 'avSvForm' on field 'avSvBewertungen'"
        //Man bräuchte aber avSvBwertungen[0].beurteilung
        if (result.hasErrors()) {
            fillModel(model, avSvForm, halbjahrId, klassenId, schuelerId);
            return EDIT_ZEUGNIS_AV_SV_VIEW;
        }

        avSvBewertungService.save(avSvForm.getAvSvBewertungen());
        return URL.createRedirectToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Container für die AvSv-Daten.
     *
     */
    public static class AvSvForm {
        @AssertValid
        private List<AvSvBewertung> avSvBewertungen;

        private Schueler schueler;
        private Klasse klasse;
        private Schulhalbjahr schulhalbjahr;

        /**
         * Initiates an object of type AvSvForm.
         */
        public AvSvForm() {

        }


        /**
         * Initiates an object of type AvSvForm.
         * @param avSvBewertungen die Bewertungen.
         * @param schueler der Schüler
         * @param klasse die Klasse.
         * @param schulhalbjahr das Schulhalbjahr.
         */
        public AvSvForm(List<AvSvBewertung> avSvBewertungen, Schueler schueler,
                Klasse klasse, Schulhalbjahr schulhalbjahr) {
            super();
            this.avSvBewertungen = avSvBewertungen;
            this.schueler = schueler;
            this.klasse = klasse;
            this.schulhalbjahr = schulhalbjahr;
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
         * @return the klasse
         */
        public Klasse getKlasse() {
            return klasse;
        }
        /**
         * @param klasse the klasse to set
         */
        public void setKlasse(Klasse klasse) {
            this.klasse = klasse;
        }
        /**
         * @return the schulhalbjahr
         */
        public Schulhalbjahr getSchulhalbjahr() {
            return schulhalbjahr;
        }
        /**
         * @param schulhalbjahr the schulhalbjahr to set
         */
        public void setSchulhalbjahr(Schulhalbjahr schulhalbjahr) {
            this.schulhalbjahr = schulhalbjahr;
        }

    }


}
