// SchuelerCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.service.api.KlasseService;
import net.sf.sze.service.api.SchuelerService;
import net.sf.sze.service.api.SchulkalenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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
 * Controller for Create, Read, Update and Delete for the model Schueler.
 *
 */
@Controller()
public class SchuelerCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHUELER_FORM = "schueler/schuelerform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(SchuelerCRUDController.class);

    /**
     * The schuelerService instance.
     */
    @Resource
    private SchuelerService schuelerService;

    /**
     * The klassenService instance.
     */
    @Resource
    private KlasseService klasseService;

    /**
     * Ein Schulkalenderservice.
     */
    @Resource
    private SchulkalenderService schulkalenderService;

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
     * List all Schuelers.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = { URL.Schueler.HOME, URL.Schueler.LIST },
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = { "name", "vorname" }, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Schueler> schuelerList =
                new PageWrapper<Schueler>(
                        schuelerService.getSchueler(pageRequest),
                        URL.Schueler.LIST);
        if (schuelerList.getSize() == 0) {
            LOG.info("No Schueler found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Schueler an.");
            return URL.redirect(URL.Schueler.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schuelerList", schuelerList);
        return "schueler/schuelerlist";
    }

    /**
     * Create a new Schueler form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final Schueler schueler = new Schueler();
        addStandardModelData(schueler, URL.Schueler.CREATE, false, model);
        return SCHUELER_FORM;
    }

    /**
     * Insert a new Schueler.
     *
     * @param schueler the Schueler.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("schueler") Schueler schueler,
            BindingResult result, Model model) {
        validator.validate(schueler, result);

        if (result.hasErrors()) {
            addStandardModelData(schueler, URL.Schueler.CREATE, false, model);
            return SCHUELER_FORM;
        }

        LOG.debug("Create Schueler: " + schueler);
        schuelerService.save(schueler);
        return URL.redirect(URL.Schueler.LIST);
    }

    /**
     * Create confirmation for deleting a Schueler.
     *
     * @param id the Id of the Schueler.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schuelerId: " + id);
        model.addAttribute("deleteURL", URL.Schueler.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Schueler.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Schueler.
     *
     * @param id the Id of the Schueler.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.DELETE, method = {
            RequestMethod.DELETE, RequestMethod.POST })
    public String delete(Long id) {
        LOG.debug("Delete schuelerId: " + id);
        schuelerService.delete(id);
        LOG.debug("Deleted schuelerId: " + id);
        return URL.redirect(URL.Schueler.LIST);
    }

    /**
     * Show a Schueler.
     *
     * @param schuelerId the Id of the Schueler.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.SHOW, method = RequestMethod.GET)
    public String show(
            @PathVariable(URL.Schueler.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        LOG.debug("Show schuelerId: " + schuelerId);

        addStandardModelData(schuelerService.read(schuelerId),
                URL.Schueler.LIST, true, model);
        return SCHUELER_FORM;
    }

    /**
     * Edit a Schueler.
     *
     * @param schuelerId the Id of the Schueler.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.EDIT, method = RequestMethod.GET)
    public String edit(
            @PathVariable(URL.Schueler.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        LOG.debug("Edit schuelerId: " + schuelerId);
        addStandardModelData(schuelerService.read(schuelerId),
                URL.filledURL(URL.Schueler.EDIT, schuelerId), false, model);
        return SCHUELER_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schueler the Schueler
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Schueler schueler, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Schueler: {}", schueler);

        if (schueler == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("schueler", schueler);
        model.addAttribute("klassen", klasseService.getKlasse(
                new PageRequest(0, 100, Direction.DESC, "jahrgang", "suffix")).getContent());
        model.addAttribute("geschlechter", Geschlecht.values());
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Schueler.
     *
     * @param schueler the Schueler.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Schueler schueler,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schueler,
                "schueler").hasErrors()) {
            addStandardModelData(schueler,
                    URL.filledURL(URL.Schueler.EDIT, schueler.getId()), false,
                    model);
            return SCHUELER_FORM;
        }

        LOG.debug("Update Schueler: " + schueler);

        schuelerService.save(schueler);

        return URL.redirect(URL.Schueler.LIST);
    }
}
