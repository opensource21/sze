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
import net.sf.sze.service.api.stammdaten.KlasseService;
import net.sf.sze.service.api.stammdaten.SchuelerService;

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
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(SchuelerCRUDController.class);



    /**
     * Kennzeichen das nur aktive Schüler angezeigt werden sollen.
     */
    private static final String AKTIV = "aktiv";

    /**
     * Kennzeichen das nur passive Schüler angezeigt werden sollen.
     */
    private static final String PASSIV = "passiv";


    /**
     * View which is used as form.
     */
    private static final String SCHUELER_FORM = "schueler/schuelerform";

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
     * Einstiegspunkt in die Schülerverwaltung.
     * @return Redirect zur aktiven Liste.
     */
    @RequestMapping(value = URL.Schueler.HOME, method = RequestMethod.GET)
    public String start() {
        return URL.redirectWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, AKTIV);
    }

    /**
     * List all Schuelers.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.LIST, method = RequestMethod.GET)
    public String list(@PathVariable(URL.Schueler.P_AKTIV) String aktiv, Model model,
            @PageableDefault(page = 0, size = 1000, sort = { "name", "vorname" },
            direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final boolean onlyActiveSchueler = !PASSIV.equalsIgnoreCase(aktiv);
        final PageWrapper<Schueler> schuelerList =
                new PageWrapper<Schueler>(
                schuelerService.getSchueler(pageRequest, onlyActiveSchueler),
                URL.filledURLWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv));
        if (schuelerList.getSize() == 0) {
            LOG.info("No Schueler found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Schueler an.");
            return URL.redirectWithNamedParams(URL.Schueler.CREATE, URL.Schueler.P_AKTIV, aktiv);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schuelerList", schuelerList);
        return "schueler/schuelerlist";
    }

    /**
     * Create a new Schueler form.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.CREATE, method = RequestMethod.GET)
    public String create(@PathVariable(URL.Schueler.P_AKTIV) String aktiv, Model model) {
        final Schueler schueler = new Schueler();
        addStandardModelData(schueler,
                URL.filledURLWithNamedParams(URL.Schueler.CREATE, URL.Schueler.P_AKTIV, aktiv) ,
                false, model);
        return SCHUELER_FORM;
    }

    /**
     * Insert a new Schueler.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param schueler the Schueler.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.CREATE, method = RequestMethod.POST)
    public String insert(@PathVariable(URL.Schueler.P_AKTIV) String aktiv,
            @ModelAttribute("schueler") Schueler schueler,
            BindingResult result, Model model) {
        validator.validate(schueler, result);

        if (result.hasErrors()) {
            addStandardModelData(schueler,
                    URL.filledURLWithNamedParams(URL.Schueler.CREATE, URL.Schueler.P_AKTIV, aktiv),
                    false, model);
            return SCHUELER_FORM;
        }

        LOG.debug("Create Schueler: " + schueler);
        schuelerService.save(schueler);
        return URL.redirectWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv);
    }

    /**
     * Create confirmation for deleting a Schueler.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param id the Id of the Schueler.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@PathVariable(URL.Schueler.P_AKTIV) String aktiv,
            @RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schuelerId: " + id);
        model.addAttribute("deleteURL", URL.filledURLWithNamedParams(
                URL.Schueler.DELETE, URL.Schueler.P_AKTIV, aktiv));
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURLWithNamedParams(
                URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv));
        return "confirmDelete";
    }

    /**
     * Delete a Schueler.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param id the Id of the Schueler.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.DELETE, method = {
            RequestMethod.DELETE, RequestMethod.POST })
    public String delete(@PathVariable(URL.Schueler.P_AKTIV) String aktiv, Long id) {
        LOG.debug("Delete schuelerId: " + id);
        schuelerService.delete(id);
        LOG.debug("Deleted schuelerId: " + id);
        return URL.redirectWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv);
    }

    /**
     * Show a Schueler.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param schuelerId the Id of the Schueler.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Schueler.P_AKTIV) String aktiv,
            @PathVariable(URL.Schueler.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        LOG.debug("Show schuelerId: " + schuelerId);

        addStandardModelData(schuelerService.read(schuelerId),
                URL.filledURLWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv),
                true, model);
        return SCHUELER_FORM;
    }

    /**
     * Edit a Schueler.
     *
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param schuelerId the Id of the Schueler.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Schueler.P_AKTIV) String aktiv,
            @PathVariable(URL.Schueler.P_SCHUELER_ID) Long schuelerId,
            Model model) {
        LOG.debug("Edit schuelerId: " + schuelerId);
        addStandardModelData(schuelerService.read(schuelerId),
                URL.filledURLWithNamedParams(URL.Schueler.EDIT,
                        URL.Schueler.P_AKTIV, aktiv,
                        URL.Schueler.P_SCHUELER_ID, schuelerId), false, model);
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
     * @param aktiv Kennzeichen ob die aktiven oder passiven Schüler bearbeitet werden sollen.
     * @param schueler the Schueler.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schueler.EDIT, method = RequestMethod.POST)
    public String update(@PathVariable(URL.Schueler.P_AKTIV) String aktiv,
            @RequestParam("id") Schueler schueler,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schueler,
                "schueler").hasErrors()) {
            addStandardModelData(schueler,
                    URL.filledURLWithNamedParams(URL.Schueler.EDIT,
                            URL.Schueler.P_AKTIV, aktiv,
                            URL.Schueler.P_SCHUELER_ID, schueler.getId()), false,
                    model);
            return SCHUELER_FORM;
        }

        LOG.debug("Update Schueler: " + schueler);

        schuelerService.save(schueler);

        return URL.redirectWithNamedParams(URL.Schueler.LIST, URL.Schueler.P_AKTIV, aktiv);
    }
}
