// ArbeitsgruppeCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.Arbeitsgruppe;
import net.sf.sze.service.api.common.SchulkalenderService;
import net.sf.sze.service.api.zeugnisconfig.ArbeitsgruppeService;

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
 * Controller for Create, Read, Update and Delete for the model Arbeitsgruppe.
 *
 */
@Controller()
public class ArbeitsgruppeCRUDController {

    /**
     * View which is used as form.
     */
    private static final String ARBEITSGRUPPE_FORM = "arbeitsgruppe/arbeitsgruppeform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ArbeitsgruppeCRUDController.class);

    /**
     * The arbeitsgruppeService instance.
     */
    @Resource
    private ArbeitsgruppeService arbeitsgruppeService;

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
     * List all Arbeitsgruppes.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Arbeitsgruppe.HOME, URL.Arbeitsgruppe.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"sortierung", "name", "klassenstufen"},
            direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Arbeitsgruppe> arbeitsgruppeList = new PageWrapper<Arbeitsgruppe>(
                arbeitsgruppeService.getArbeitsgruppe(pageRequest), URL.Arbeitsgruppe.LIST);
        if (arbeitsgruppeList.getSize() == 0) {
            LOG.info("No Arbeitsgruppe found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Arbeitsgruppe an.");
            return URL.redirect(URL.Arbeitsgruppe.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("arbeitsgruppeList", arbeitsgruppeList);
        return "arbeitsgruppe/arbeitsgruppelist";
    }

    /**
     * Create a new Arbeitsgruppe form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final Arbeitsgruppe arbeitsgruppe = new Arbeitsgruppe();
        addStandardModelData(arbeitsgruppe, URL.Arbeitsgruppe.CREATE, false,
                    model);
        return ARBEITSGRUPPE_FORM;
    }

    /**
     * Insert a new Arbeitsgruppe.
     *
     * @param arbeitsgruppe the Arbeitsgruppe.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("arbeitsgruppe") Arbeitsgruppe arbeitsgruppe,
            BindingResult result, Model model) {
        validator.validate(arbeitsgruppe, result);

        if (result.hasErrors()) {
            addStandardModelData(arbeitsgruppe, URL.Arbeitsgruppe.CREATE, false, model);
            return ARBEITSGRUPPE_FORM;
        }

        LOG.debug("Create Arbeitsgruppe: " + arbeitsgruppe);
        arbeitsgruppeService.save(arbeitsgruppe);
        return URL.redirect(URL.Arbeitsgruppe.LIST);
    }

    /**
     * Create confirmation for deleting a Arbeitsgruppe.
     *
     * @param id the Id of the Arbeitsgruppe.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete arbeitsgruppeId: " + id);
        model.addAttribute("deleteURL", URL.Arbeitsgruppe.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Arbeitsgruppe.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Arbeitsgruppe.
     *
     * @param id the Id of the Arbeitsgruppe.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete arbeitsgruppeId: " + id);
        arbeitsgruppeService.delete(id);
        LOG.debug("Deleted arbeitsgruppeId: " + id);
        return URL.redirect(URL.Arbeitsgruppe.LIST);
    }

    /**
     * Show a Arbeitsgruppe.
     *
     * @param arbeitsgruppeId the Id of the Arbeitsgruppe.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Arbeitsgruppe
            .P_ARBEITSGRUPPE_ID) Long arbeitsgruppeId, Model model) {
        LOG.debug("Show arbeitsgruppeId: " + arbeitsgruppeId);

        addStandardModelData(arbeitsgruppeService.read(arbeitsgruppeId), URL.Arbeitsgruppe
                .LIST, true, model);
        return ARBEITSGRUPPE_FORM;
    }

    /**
     * Edit a Arbeitsgruppe.
     *
     * @param arbeitsgruppeId the Id of the Arbeitsgruppe.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Arbeitsgruppe
            .P_ARBEITSGRUPPE_ID) Long arbeitsgruppeId, Model model) {
        LOG.debug("Edit arbeitsgruppeId: " + arbeitsgruppeId);
        addStandardModelData(arbeitsgruppeService.read(arbeitsgruppeId), URL.filledURL(
                URL.Arbeitsgruppe.EDIT, arbeitsgruppeId), false, model);
        return ARBEITSGRUPPE_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param arbeitsgruppe the Arbeitsgruppe
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Arbeitsgruppe arbeitsgruppe, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Arbeitsgruppe: {}", arbeitsgruppe);

        if (arbeitsgruppe == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("arbeitsgruppe", arbeitsgruppe);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Arbeitsgruppe.
     *
     * @param arbeitsgruppe the Arbeitsgruppe.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Arbeitsgruppe.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Arbeitsgruppe arbeitsgruppe,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, arbeitsgruppe, "arbeitsgruppe")
                .hasErrors()) {
            addStandardModelData(arbeitsgruppe, URL.filledURL(URL.Arbeitsgruppe.EDIT,
                    arbeitsgruppe.getId()), false, model);
            return ARBEITSGRUPPE_FORM;
        }

        LOG.debug("Update Arbeitsgruppe: " + arbeitsgruppe);

        arbeitsgruppeService.save(arbeitsgruppe);

        return URL.redirect(URL.Arbeitsgruppe.LIST);
    }
}
