// SchulhalbjahrCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.service.api.SchulhalbjahrService;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ppi.fuwesta.spring.mvc.util.PageWrapper;
import de.ppi.fuwesta.spring.mvc.util.ResourceNotFoundException;

/**
 * Controller for Create, Read, Update and Delete for the model Schulhalbjahr.
 *
 */
@Controller()
public class SchulhalbjahrCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULHALBJAHR_FORM = "schulhalbjahr/form";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SchulhalbjahrCRUDController.class);

    /**
     * The schulhalbjahrService instance.
     */
    @Resource
    private SchulhalbjahrService schulhalbjahrService;

    /**
     * The generic validator.
     */
    @Resource
    private Validator validator;

    /**
     * List all Schulhalbjahrs.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Schulhalbjahr.HOME, URL.Schulhalbjahr.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"jahr", "halbjahr"}, direction = Direction.ASC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<Schulhalbjahr> schulhalbjahrList = new PageWrapper<Schulhalbjahr>(
                schulhalbjahrService.getSchulhalbjahr(pageRequest), URL.Schulhalbjahr.LIST);
        if (schulhalbjahrList.getSize() == 0) {
            LOG.info("No Schulhalbjahr found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste Schulhalbjahr an.");
            return URL.redirect(URL.Schulhalbjahr.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schulhalbjahrList", schulhalbjahrList);
        return "schulhalbjahr/list";
    }

    /**
     * Create a new Schulhalbjahr form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        addStandardModelData(new Schulhalbjahr(), URL.Schulhalbjahr.CREATE, false,
                model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Insert a new Schulhalbjahr.
     *
     * @param schulhalbjahr the Schulhalbjahr.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("Schulhalbjahr") Schulhalbjahr schulhalbjahr,
            BindingResult result, Model model) {
        validator.validate(schulhalbjahr, result);

        if (result.hasErrors()) {
            addStandardModelData(schulhalbjahr, URL.Schulhalbjahr.CREATE, false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Create Schulhalbjahr: " + schulhalbjahr);
        schulhalbjahrService.save(schulhalbjahr);
        return URL.redirect(URL.Schulhalbjahr.LIST);
    }

    /**
     * Create confirmation for deleting a Schulhalbjahr.
     *
     * @param schulhalbjahrId the Id of the Schulhalbjahr.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@PathVariable(URL.Schulhalbjahr
            .P_SCHULHALBJAHR_ID) Long schulhalbjahrId, Model model) {
        LOG.debug("Confirm delete schulhalbjahrId: " + schulhalbjahrId);
        model.addAttribute("deleteURL", URL.filledURLWithNamedParams(URL.Schulhalbjahr.DELETE,
                URL.Schulhalbjahr.P_SCHULHALBJAHR_ID, schulhalbjahrId));
        model.addAttribute("cancelURL", URL.filledURL(URL.Schulhalbjahr.LIST));
        return "example/confirmDelete";
    }

    /**
     * Delete a Schulhalbjahr.
     *
     * @param schulhalbjahrId the Id of the Schulhalbjahr.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(@PathVariable(URL.Schulhalbjahr
            .P_SCHULHALBJAHR_ID) Long schulhalbjahrId) {
        LOG.debug("Delete schulhalbjahrId: " + schulhalbjahrId);
        schulhalbjahrService.delete(schulhalbjahrId);
        LOG.debug("Deleted schulhalbjahrId: " + schulhalbjahrId);
        return URL.redirect(URL.Schulhalbjahr.LIST);
    }

    /**
     * Show a Schulhalbjahr.
     *
     * @param schulhalbjahrId the Id of the Schulhalbjahr.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Schulhalbjahr
            .P_SCHULHALBJAHR_ID) Long schulhalbjahrId, Model model) {
        LOG.debug("Show schulhalbjahrId: " + schulhalbjahrId);

        addStandardModelData(schulhalbjahrService.read(schulhalbjahrId), URL.Schulhalbjahr
                .LIST, true, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Edit a Schulhalbjahr.
     *
     * @param schulhalbjahrId the Id of the Schulhalbjahr.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Schulhalbjahr
            .P_SCHULHALBJAHR_ID) Long schulhalbjahrId, Model model) {
        LOG.debug("Edit schulhalbjahrId: " + schulhalbjahrId);
        addStandardModelData(schulhalbjahrService.read(schulhalbjahrId), URL.filledURL(
                URL.Schulhalbjahr.EDIT, schulhalbjahrId), false, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schulhalbjahr the Schulhalbjahr
     * @param disabled true if the data should be only show.
     * @param url the action URL.
     * @param model the model
     */
    private void addStandardModelData(Schulhalbjahr schulhalbjahr, String url,
            boolean disabled, Model model) {
        LOG.info("Schulhalbjahr: {}", schulhalbjahr);

        if (schulhalbjahr == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("Schulhalbjahr", schulhalbjahr);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("url", url);
    }

    /**
     * Update a Schulhalbjahr.
     *
     * @param schulhalbjahr the Schulhalbjahr.
     * @param result the bindings result.
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.EDIT, method = RequestMethod.POST)
    public String update(@ModelAttribute("Schulhalbjahr") Schulhalbjahr schulhalbjahr,
            BindingResult result, Model model) {
        validator.validate(schulhalbjahr, result);

        if (result.hasErrors()) {
            addStandardModelData(schulhalbjahr, URL.filledURL(URL.Schulhalbjahr.EDIT,
                    schulhalbjahr.getId()), false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Update Schulhalbjahr: " + schulhalbjahr);

        schulhalbjahrService.save(schulhalbjahr);

        return URL.redirect(URL.Schulhalbjahr.LIST);
    }
}
