// SchulamtCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.Schulamt;
import net.sf.sze.service.api.common.SchulkalenderService;
import net.sf.sze.service.api.zeugnisconfig.SchulamtService;

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
 * Controller for Create, Read, Update and Delete for the model Schulamt.
 *
 */
@Controller()
public class SchulamtCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULAMT_FORM =
            "schulamt/schulamtform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SchulamtCRUDController.class);

    /**
     * The schulamtService instance.
     */
    @Resource
    private SchulamtService schulamtService;

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
     * List all Schulamts.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Schulamt.HOME, URL.Schulamt.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Schulamt> schulamtList =
                new PageWrapper<Schulamt>(
                schulamtService.getSchulamt(pageRequest),
                URL.Schulamt.LIST);
        if (schulamtList.getSize() == 0) {
            LOG.info("No Schulamt found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Schulamt an.");
            return URL.redirect(URL.Schulamt.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schulamtList", schulamtList);
        return "schulamt/schulamtlist";
    }

    /**
     * Create a new Schulamt form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final Schulamt schulamt = new Schulamt();
        addStandardModelData(schulamt, URL.Schulamt.CREATE, false,
                    model);
        return SCHULAMT_FORM;
    }

    /**
     * Insert a new Schulamt.
     *
     * @param schulamt the Schulamt.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("schulamt") Schulamt
            schulamt, BindingResult result, Model model) {
        validator.validate(schulamt, result);

        if (result.hasErrors()) {
            addStandardModelData(schulamt, URL.Schulamt.CREATE, false, model);
            return SCHULAMT_FORM;
        }

        LOG.debug("Create Schulamt: " + schulamt);
        schulamtService.save(schulamt);
        return URL.redirect(URL.Schulamt.LIST);
    }

    /**
     * Create confirmation for deleting a Schulamt.
     *
     * @param id the Id of the Schulamt.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schulamtId: " + id);
        model.addAttribute("deleteURL", URL.Schulamt.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Schulamt.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Schulamt.
     *
     * @param id the Id of the Schulamt.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete schulamtId: " + id);
        schulamtService.delete(id);
        LOG.debug("Deleted schulamtId: " + id);
        return URL.redirect(URL.Schulamt.LIST);
    }

    /**
     * Show a Schulamt.
     *
     * @param schulamtId the Id of the Schulamt.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Schulamt
            .P_SCHULAMT_ID) Long schulamtId, Model model) {
        LOG.debug("Show schulamtId: " + schulamtId);

        addStandardModelData(schulamtService.read(schulamtId),
                URL.Schulamt.LIST, true, model);
        return SCHULAMT_FORM;
    }

    /**
     * Edit a Schulamt.
     *
     * @param schulamtId the Id of the Schulamt.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Schulamt
            .P_SCHULAMT_ID) Long schulamtId, Model model) {
        LOG.debug("Edit schulamtId: " + schulamtId);
        addStandardModelData(schulamtService.read(schulamtId), URL.filledURL(
                URL.Schulamt.EDIT, schulamtId), false, model);
        return SCHULAMT_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schulamt the Schulamt
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Schulamt schulamt, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Schulamt: {}", schulamt);

        if (schulamt == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("schulamt", schulamt);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Schulamt.
     *
     * @param schulamt the Schulamt.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulamt.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Schulamt schulamt,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schulamt,
                "schulamt").hasErrors()) {
            addStandardModelData(schulamt, URL.filledURL(URL.Schulamt.EDIT,
                    schulamt.getId()), false, model);
            return SCHULAMT_FORM;
        }

        LOG.debug("Update Schulamt: " + schulamt);

        schulamtService.save(schulamt);

        return URL.redirect(URL.Schulamt.LIST);
    }
}
