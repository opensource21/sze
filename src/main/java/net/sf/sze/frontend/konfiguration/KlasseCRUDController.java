// KlasseCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Halbjahr;
import net.sf.sze.service.api.KlasseService;
import net.sf.sze.service.api.SchuelerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ppi.fuwesta.spring.mvc.bind.ServletBindingService;
import de.ppi.fuwesta.spring.mvc.util.PageWrapper;
import de.ppi.fuwesta.spring.mvc.util.ResourceNotFoundException;

/**
 * Controller for Create, Read, Update and Delete for the model Klasse.
 *
 */
@Controller()
public class KlasseCRUDController {

    /**
     * View which is used as form.
     */
    private static final String KLASSE_FORM = "klasse/klasseform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            KlasseCRUDController.class);

    /**
     * The klasseService instance.
     */
    @Resource
    private KlasseService klasseService;

    /**
     * Der {@link SchuelerService}.
     */
    @Resource
    private SchuelerService schuelerService;



    /**
     * Small service which helps to bind requestdata to an object.
     */
    @Resource
    private ServletBindingService servletBindingService;

    /**
     * The generic validator.
     */
    @Resource
    private Validator validator;

    /**
     * List all Klasses.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Klasse.HOME, URL.Klasse.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"jahrgang", "geschlossen", "suffix"}, direction = Direction.DESC)
            Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Klasse> klasseList = new PageWrapper<Klasse>(
                klasseService.getKlasse(pageRequest), URL.Klasse.LIST);
        if (klasseList.getSize() == 0) {
            LOG.info("No Klasse found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Klasse an.");
            return URL.redirect(URL.Klasse.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("klasseList", klasseList);
        return "klasse/klasselist";
    }

    /**
     * Create a new Klasse form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final Klasse klasse = new Klasse();
        addStandardModelData(klasse, URL.Klasse.CREATE, false,
                    model);
        return KLASSE_FORM;
    }

    /**
     * Insert a new Klasse.
     *
     * @param klasse the Klasse.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("klasse") Klasse klasse,
            BindingResult result, Model model) {
        validator.validate(klasse, result);

        if (result.hasErrors()) {
            addStandardModelData(klasse, URL.Klasse.CREATE, false, model);
            return KLASSE_FORM;
        }

        LOG.debug("Create Klasse: " + klasse);
        klasseService.save(klasse);
        return URL.redirect(URL.Klasse.LIST);
    }

    /**
     * Create confirmation for deleting a Klasse.
     *
     * @param id the Id of the Klasse.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete klasseId: " + id);
        model.addAttribute("deleteURL", URL.Klasse.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Klasse.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Klasse.
     *
     * @param id the Id of the Klasse.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete klasseId: " + id);
        klasseService.delete(id);
        LOG.debug("Deleted klasseId: " + id);
        return URL.redirect(URL.Klasse.LIST);
    }

    /**
     * Show a Klasse.
     *
     * @param klasseId the Id of the Klasse.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Klasse
            .P_KLASSE_ID) Long klasseId, Model model) {
        LOG.debug("Show klasseId: " + klasseId);
        final Klasse klasse = klasseService.read(klasseId);
        addStandardModelData(klasse, URL.Klasse
                .LIST, true, model);

        model.addAttribute("schuelerList", schuelerService.getActiveSchueler(klasse));
        return KLASSE_FORM;
    }

    /**
     * Edit a Klasse.
     *
     * @param klasseId the Id of the Klasse.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Klasse
            .P_KLASSE_ID) Long klasseId, Model model) {
        LOG.debug("Edit klasseId: " + klasseId);
        addStandardModelData(klasseService.read(klasseId), URL.filledURL(
                URL.Klasse.EDIT, klasseId), false, model);
        return KLASSE_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param klasse the Klasse
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Klasse klasse, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Klasse: {}", klasse);

        if (klasse == null) {
            throw new ResourceNotFoundException();
        }
//        int currentYear = klasse.getJahr() + 3;
        int[] jahre = new int[7];
        for (int i = 0; i <= 6; i++) {
//            jahre[i] = currentYear - i;
        }
        model.addAttribute("halbjahre", Halbjahr.values());
        model.addAttribute("jahre", jahre);
        model.addAttribute("klasse", klasse);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Klasse.
     *
     * @param klasse the Klasse.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Klasse.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Klasse klasse,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, klasse, "klasse")
                .hasErrors()) {
            addStandardModelData(klasse, URL.filledURL(URL.Klasse.EDIT,
                    klasse.getId()), false, model);
            return KLASSE_FORM;
        }

        LOG.debug("Update Klasse: " + klasse);

        klasseService.save(klasse);

        return URL.redirect(URL.Klasse.LIST);
    }
}
