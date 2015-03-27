// SchulamtsBemerkungsBausteinCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;
import net.sf.sze.service.api.zeugnisconfig.SchulamtsBemerkungsBausteinService;

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
 * Controller for Create, Read, Update and Delete for the model SchulamtsBemerkungsBaustein.
 *
 */
@Controller()
public class SchulamtsBemerkungsBausteinCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM =
            "schulamtsBemerkungsBaustein/schulamtsBemerkungsBausteinform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SchulamtsBemerkungsBausteinCRUDController.class);

    /**
     * The schulamtsBemerkungsBausteinService instance.
     */
    @Resource
    private SchulamtsBemerkungsBausteinService schulamtsBemerkungsBausteinService;

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
     * List all SchulamtsBemerkungsBausteins.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.SchulamtsBemerkungsBaustein.HOME,
            URL.SchulamtsBemerkungsBaustein.LIST}, method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"sortierung", "name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<SchulamtsBemerkungsBaustein> schulamtsBemerkungsBausteinList =
                new PageWrapper<SchulamtsBemerkungsBaustein>(
                schulamtsBemerkungsBausteinService.getSchulamtsBemerkungsBaustein(pageRequest),
                URL.SchulamtsBemerkungsBaustein.LIST);
        if (schulamtsBemerkungsBausteinList.getSize() == 0) {
            LOG.info("No SchulamtsBemerkungsBaustein found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste SchulamtsBemerkungsBaustein an.");
            return URL.redirect(URL.SchulamtsBemerkungsBaustein.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schulamtsBemerkungsBausteinList", schulamtsBemerkungsBausteinList);
        return "schulamtsBemerkungsBaustein/schulamtsBemerkungsBausteinlist";
    }

    /**
     * Create a new SchulamtsBemerkungsBaustein form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein =
                new SchulamtsBemerkungsBaustein();
        addStandardModelData(schulamtsBemerkungsBaustein, URL.SchulamtsBemerkungsBaustein.CREATE,
                false, model);
        return SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Insert a new SchulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBaustein the SchulamtsBemerkungsBaustein.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("schulamtsBemerkungsBaustein") SchulamtsBemerkungsBaustein
            schulamtsBemerkungsBaustein, BindingResult result, Model model) {
        validator.validate(schulamtsBemerkungsBaustein, result);

        if (result.hasErrors()) {
            addStandardModelData(schulamtsBemerkungsBaustein,
                    URL.SchulamtsBemerkungsBaustein.CREATE, false, model);
            return SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM;
        }

        LOG.debug("Create SchulamtsBemerkungsBaustein: " + schulamtsBemerkungsBaustein);
        schulamtsBemerkungsBausteinService.save(schulamtsBemerkungsBaustein);
        return URL.redirect(URL.SchulamtsBemerkungsBaustein.LIST);
    }

    /**
     * Create confirmation for deleting a SchulamtsBemerkungsBaustein.
     *
     * @param id the Id of the SchulamtsBemerkungsBaustein.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schulamtsBemerkungsBausteinId: " + id);
        model.addAttribute("deleteURL", URL.SchulamtsBemerkungsBaustein.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.SchulamtsBemerkungsBaustein.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a SchulamtsBemerkungsBaustein.
     *
     * @param id the Id of the SchulamtsBemerkungsBaustein.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete schulamtsBemerkungsBausteinId: " + id);
        schulamtsBemerkungsBausteinService.delete(id);
        LOG.debug("Deleted schulamtsBemerkungsBausteinId: " + id);
        return URL.redirect(URL.SchulamtsBemerkungsBaustein.LIST);
    }

    /**
     * Show a SchulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBausteinId the Id of the SchulamtsBemerkungsBaustein.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.SchulamtsBemerkungsBaustein
            .P_SCHULAMTS_BEMERKUNGS_BAUSTEIN_ID) Long schulamtsBemerkungsBausteinId, Model model) {
        LOG.debug("Show schulamtsBemerkungsBausteinId: " + schulamtsBemerkungsBausteinId);

        addStandardModelData(schulamtsBemerkungsBausteinService.read(schulamtsBemerkungsBausteinId),
                URL.SchulamtsBemerkungsBaustein.LIST, true, model);
        return SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Edit a SchulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBausteinId the Id of the SchulamtsBemerkungsBaustein.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.SchulamtsBemerkungsBaustein
            .P_SCHULAMTS_BEMERKUNGS_BAUSTEIN_ID) Long schulamtsBemerkungsBausteinId, Model model) {
        LOG.debug("Edit schulamtsBemerkungsBausteinId: " + schulamtsBemerkungsBausteinId);
        addStandardModelData(schulamtsBemerkungsBausteinService.read(schulamtsBemerkungsBausteinId),
                URL.filledURL(URL.SchulamtsBemerkungsBaustein.EDIT, schulamtsBemerkungsBausteinId),
                false, model);
        return SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schulamtsBemerkungsBaustein the SchulamtsBemerkungsBaustein
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein,
            String saveUrl, boolean disabled, Model model) {
        LOG.info("SchulamtsBemerkungsBaustein: {}", schulamtsBemerkungsBaustein);

        if (schulamtsBemerkungsBaustein == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("schulamtsBemerkungsBaustein", schulamtsBemerkungsBaustein);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a SchulamtsBemerkungsBaustein.
     *
     * @param schulamtsBemerkungsBaustein the SchulamtsBemerkungsBaustein.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulamtsBemerkungsBaustein.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") SchulamtsBemerkungsBaustein
            schulamtsBemerkungsBaustein, HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schulamtsBemerkungsBaustein,
                "schulamtsBemerkungsBaustein").hasErrors()) {
            addStandardModelData(schulamtsBemerkungsBaustein,
                    URL.filledURL(URL.SchulamtsBemerkungsBaustein.EDIT,
                    schulamtsBemerkungsBaustein.getId()), false, model);
            return SCHULAMTS_BEMERKUNGS_BAUSTEIN_FORM;
        }

        LOG.debug("Update SchulamtsBemerkungsBaustein: " + schulamtsBemerkungsBaustein);

        schulamtsBemerkungsBausteinService.save(schulamtsBemerkungsBaustein);

        return URL.redirect(URL.SchulamtsBemerkungsBaustein.LIST);
    }
}
