// SchulfachCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.model.zeugnisconfig.Schulfachtyp;
import net.sf.sze.service.api.SchulkalenderService;
import net.sf.sze.service.api.zeugnisconfig.SchulfachService;

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
 * Controller for Create, Read, Update and Delete for the model Schulfach.
 *
 */
@Controller()
public class SchulfachCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULFACH_FORM = "schulfach/schulfachform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SchulfachCRUDController.class);

    /**
     * The schulfachService instance.
     */
    @Resource
    private SchulfachService schulfachService;

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
     * List all Schulfachs.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Schulfach.HOME, URL.Schulfach.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"sortierung", "typ", "name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Schulfach> schulfachList = new PageWrapper<Schulfach>(
                schulfachService.getSchulfach(pageRequest), URL.Schulfach.LIST);
        if (schulfachList.getSize() == 0) {
            LOG.info("No Schulfach found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Schulfach an.");
            return URL.redirect(URL.Schulfach.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schulfachList", schulfachList);
        return "schulfach/schulfachlist";
    }

    /**
     * Create a new Schulfach form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final Schulfach schulfach = new Schulfach();
        addStandardModelData(schulfach, URL.Schulfach.CREATE, false,
                    model);
        return SCHULFACH_FORM;
    }

    /**
     * Insert a new Schulfach.
     *
     * @param schulfach the Schulfach.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("schulfach") Schulfach schulfach,
            BindingResult result, Model model) {
        validator.validate(schulfach, result);

        if (result.hasErrors()) {
            addStandardModelData(schulfach, URL.Schulfach.CREATE, false, model);
            return SCHULFACH_FORM;
        }

        LOG.debug("Create Schulfach: " + schulfach);
        schulfachService.save(schulfach);
        return URL.redirect(URL.Schulfach.LIST);
    }

    /**
     * Create confirmation for deleting a Schulfach.
     *
     * @param id the Id of the Schulfach.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schulfachId: " + id);
        model.addAttribute("deleteURL", URL.Schulfach.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Schulfach.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Schulfach.
     *
     * @param id the Id of the Schulfach.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete schulfachId: " + id);
        schulfachService.delete(id);
        LOG.debug("Deleted schulfachId: " + id);
        return URL.redirect(URL.Schulfach.LIST);
    }

    /**
     * Show a Schulfach.
     *
     * @param schulfachId the Id of the Schulfach.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Schulfach
            .P_SCHULFACH_ID) Long schulfachId, Model model) {
        LOG.debug("Show schulfachId: " + schulfachId);

        addStandardModelData(schulfachService.read(schulfachId), URL.Schulfach
                .LIST, true, model);
        return SCHULFACH_FORM;
    }

    /**
     * Edit a Schulfach.
     *
     * @param schulfachId the Id of the Schulfach.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Schulfach
            .P_SCHULFACH_ID) Long schulfachId, Model model) {
        LOG.debug("Edit schulfachId: " + schulfachId);
        addStandardModelData(schulfachService.read(schulfachId), URL.filledURL(
                URL.Schulfach.EDIT, schulfachId), false, model);
        return SCHULFACH_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schulfach the Schulfach
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Schulfach schulfach, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Schulfach: {}", schulfach);

        if (schulfach == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("schulfachtypen", Schulfachtyp.values());
        model.addAttribute("schulfach", schulfach);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Schulfach.
     *
     * @param schulfach the Schulfach.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulfach.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Schulfach schulfach,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schulfach, "schulfach")
                .hasErrors()) {
            addStandardModelData(schulfach, URL.filledURL(URL.Schulfach.EDIT,
                    schulfach.getId()), false, model);
            return SCHULFACH_FORM;
        }

        LOG.debug("Update Schulfach: " + schulfach);

        schulfachService.save(schulfach);

        return URL.redirect(URL.Schulfach.LIST);
    }
}
