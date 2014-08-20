// SoLBewertungsTextCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.SoLBewertungsText;
import net.sf.sze.service.api.common.SchulkalenderService;
import net.sf.sze.service.api.zeugnisconfig.SoLBewertungsTextService;

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
 * Controller for Create, Read, Update and Delete for the model SoLBewertungsText.
 *
 */
@Controller()
public class SoLBewertungsTextCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SOL_BEWERTUNGS_TEXT_FORM =
            "solBewertungsText/solBewertungsTextform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SoLBewertungsTextCRUDController.class);

    /**
     * The solBewertungsTextService instance.
     */
    @Resource
    private SoLBewertungsTextService solBewertungsTextService;

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
     * List all SoLBewertungsTexts.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.SoLBewertungsText.HOME, URL.SoLBewertungsText.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"name"}, direction = Direction.ASC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<SoLBewertungsText> solBewertungsTextList =
                new PageWrapper<SoLBewertungsText>(
                solBewertungsTextService.getSoLBewertungsText(pageRequest),
                URL.SoLBewertungsText.LIST);
        if (solBewertungsTextList.getSize() == 0) {
            LOG.info("No SoLBewertungsText found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste SoLBewertungsText an.");
            return URL.redirect(URL.SoLBewertungsText.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("solBewertungsTextList", solBewertungsTextList);
        return "solBewertungsText/solBewertungsTextlist";
    }

    /**
     * Create a new SoLBewertungsText form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final SoLBewertungsText solBewertungsText = new SoLBewertungsText();
        addStandardModelData(solBewertungsText, URL.SoLBewertungsText.CREATE, false,
                    model);
        return SOL_BEWERTUNGS_TEXT_FORM;
    }

    /**
     * Insert a new SoLBewertungsText.
     *
     * @param solBewertungsText the SoLBewertungsText.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("solBewertungsText") SoLBewertungsText
            solBewertungsText, BindingResult result, Model model) {
        validator.validate(solBewertungsText, result);

        if (result.hasErrors()) {
            addStandardModelData(solBewertungsText, URL.SoLBewertungsText.CREATE, false, model);
            return SOL_BEWERTUNGS_TEXT_FORM;
        }

        LOG.debug("Create SoLBewertungsText: " + solBewertungsText);
        solBewertungsTextService.save(solBewertungsText);
        return URL.redirect(URL.SoLBewertungsText.LIST);
    }

    /**
     * Create confirmation for deleting a SoLBewertungsText.
     *
     * @param id the Id of the SoLBewertungsText.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete solBewertungsTextId: " + id);
        model.addAttribute("deleteURL", URL.SoLBewertungsText.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.SoLBewertungsText.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a SoLBewertungsText.
     *
     * @param id the Id of the SoLBewertungsText.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete solBewertungsTextId: " + id);
        solBewertungsTextService.delete(id);
        LOG.debug("Deleted solBewertungsTextId: " + id);
        return URL.redirect(URL.SoLBewertungsText.LIST);
    }

    /**
     * Show a SoLBewertungsText.
     *
     * @param solBewertungsTextId the Id of the SoLBewertungsText.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.SoLBewertungsText
            .P_SOL_BEWERTUNGS_TEXT_ID) Long solBewertungsTextId, Model model) {
        LOG.debug("Show solBewertungsTextId: " + solBewertungsTextId);

        addStandardModelData(solBewertungsTextService.read(solBewertungsTextId),
                URL.SoLBewertungsText.LIST, true, model);
        return SOL_BEWERTUNGS_TEXT_FORM;
    }

    /**
     * Edit a SoLBewertungsText.
     *
     * @param solBewertungsTextId the Id of the SoLBewertungsText.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.SoLBewertungsText
            .P_SOL_BEWERTUNGS_TEXT_ID) Long solBewertungsTextId, Model model) {
        LOG.debug("Edit solBewertungsTextId: " + solBewertungsTextId);
        addStandardModelData(solBewertungsTextService.read(solBewertungsTextId), URL.filledURL(
                URL.SoLBewertungsText.EDIT, solBewertungsTextId), false, model);
        return SOL_BEWERTUNGS_TEXT_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param solBewertungsText the SoLBewertungsText
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(SoLBewertungsText solBewertungsText, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("SoLBewertungsText: {}", solBewertungsText);

        if (solBewertungsText == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("solBewertungsText", solBewertungsText);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a SoLBewertungsText.
     *
     * @param solBewertungsText the SoLBewertungsText.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SoLBewertungsText.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") SoLBewertungsText solBewertungsText,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, solBewertungsText,
                "solBewertungsText").hasErrors()) {
            addStandardModelData(solBewertungsText, URL.filledURL(URL.SoLBewertungsText.EDIT,
                    solBewertungsText.getId()), false, model);
            return SOL_BEWERTUNGS_TEXT_FORM;
        }

        LOG.debug("Update SoLBewertungsText: " + solBewertungsText);

        solBewertungsTextService.save(solBewertungsText);

        return URL.redirect(URL.SoLBewertungsText.LIST);
    }
}
