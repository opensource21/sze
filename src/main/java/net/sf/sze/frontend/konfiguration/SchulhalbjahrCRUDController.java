// SchulhalbjahrCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.Halbjahr;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.ppi.fuwesta.spring.mvc.bind.ServletBindingService;
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
            sort = {"jahr", "halbjahr"}, direction = Direction.DESC) Pageable pageRequest,
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
        final Schulhalbjahr schulhalbjahr = new Schulhalbjahr();
        final Calendar currentDate = Calendar.getInstance();
        if (currentDate.get(Calendar.MONTH) >= Calendar.AUGUST) {
            schulhalbjahr.setJahr(currentDate.get(Calendar.YEAR) + 1);
        } else {
            schulhalbjahr.setJahr(currentDate.get(Calendar.YEAR));
        }
        if (currentDate.get(Calendar.MONTH) >= Calendar.MARCH
                && currentDate.get(Calendar.MONTH) < Calendar.AUGUST) {
            schulhalbjahr.setHalbjahr(Halbjahr.Beide_Halbjahre);
        } else {
            schulhalbjahr.setHalbjahr(Halbjahr.Erstes_Halbjahr);
        }
        schulhalbjahr.setSelectable(true);
        addStandardModelData(schulhalbjahr, URL.Schulhalbjahr.CREATE, false,
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
     * @param id the Id of the Schulhalbjahr.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schulhalbjahrId: " + id);
        model.addAttribute("deleteURL", URL.Schulhalbjahr.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.Schulhalbjahr.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a Schulhalbjahr.
     *
     * @param id the Id of the Schulhalbjahr.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete schulhalbjahrId: " + id);
        schulhalbjahrService.delete(id);
        LOG.debug("Deleted schulhalbjahrId: " + id);
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
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(Schulhalbjahr schulhalbjahr, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("Schulhalbjahr: {}", schulhalbjahr);

        if (schulhalbjahr == null) {
            throw new ResourceNotFoundException();
        }
        int currentYear = schulhalbjahr.getJahr() + 3;
        int[] jahre = new int[7];
        for (int i = 0; i <= 6; i++) {
            jahre[i] = currentYear - i;
        }
        model.addAttribute("halbjahre", Halbjahr.values());
        model.addAttribute("jahre", jahre);
        model.addAttribute("schulhalbjahr", schulhalbjahr);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a Schulhalbjahr.
     *
     * @param schulhalbjahr the Schulhalbjahr.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Schulhalbjahr.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") Schulhalbjahr schulhalbjahr,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schulhalbjahr, "schulhalbjahr")
                .hasErrors()) {
            addStandardModelData(schulhalbjahr, URL.filledURL(URL.Schulhalbjahr.EDIT,
                    schulhalbjahr.getId()), false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Update Schulhalbjahr: " + schulhalbjahr);

        schulhalbjahrService.save(schulhalbjahr);

        return URL.redirect(URL.Schulhalbjahr.LIST);
    }
}
