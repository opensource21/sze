// BemerkungsBausteinCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.BemerkungsBaustein;
import net.sf.sze.service.api.BemerkungsBausteinService;
import net.sf.sze.service.api.SchulkalenderService;

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
 * Controller for Create, Read, Update and Delete for the model BemerkungsBaustein.
 *
 */
@Controller()
public class BemerkungsBausteinCRUDController {

    /**
     * View which is used as form.
     */
    private static final String BEMERKUNGS_BAUSTEIN_FORM =
            "bemerkungsBaustein/bemerkungsBausteinform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            BemerkungsBausteinCRUDController.class);

    /**
     * The bemerkungsBausteinService instance.
     */
    @Resource
    private BemerkungsBausteinService bemerkungsBausteinService;

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
     * List all BemerkungsBausteins.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.BemerkungsBaustein.HOME, URL.BemerkungsBaustein.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<BemerkungsBaustein> bemerkungsBausteinList =
                new PageWrapper<BemerkungsBaustein>(
                bemerkungsBausteinService.getBemerkungsBaustein(pageRequest),
                URL.BemerkungsBaustein.LIST);
        if (bemerkungsBausteinList.getSize() == 0) {
            LOG.info("No BemerkungsBaustein found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste BemerkungsBaustein an.");
            return URL.redirect(URL.BemerkungsBaustein.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("bemerkungsBausteinList", bemerkungsBausteinList);
        return "bemerkungsBaustein/bemerkungsBausteinlist";
    }

    /**
     * Create a new BemerkungsBaustein form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final BemerkungsBaustein bemerkungsBaustein = new BemerkungsBaustein();
        addStandardModelData(bemerkungsBaustein, URL.BemerkungsBaustein.CREATE, false,
                    model);
        return BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Insert a new BemerkungsBaustein.
     *
     * @param bemerkungsBaustein the BemerkungsBaustein.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("bemerkungsBaustein") BemerkungsBaustein
            bemerkungsBaustein, BindingResult result, Model model) {
        validator.validate(bemerkungsBaustein, result);

        if (result.hasErrors()) {
            addStandardModelData(bemerkungsBaustein, URL.BemerkungsBaustein.CREATE, false, model);
            return BEMERKUNGS_BAUSTEIN_FORM;
        }

        LOG.debug("Create BemerkungsBaustein: " + bemerkungsBaustein);
        bemerkungsBausteinService.save(bemerkungsBaustein);
        return URL.redirect(URL.BemerkungsBaustein.LIST);
    }

    /**
     * Create confirmation for deleting a BemerkungsBaustein.
     *
     * @param id the Id of the BemerkungsBaustein.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete bemerkungsBausteinId: " + id);
        model.addAttribute("deleteURL", URL.BemerkungsBaustein.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.BemerkungsBaustein.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a BemerkungsBaustein.
     *
     * @param id the Id of the BemerkungsBaustein.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete bemerkungsBausteinId: " + id);
        bemerkungsBausteinService.delete(id);
        LOG.debug("Deleted bemerkungsBausteinId: " + id);
        return URL.redirect(URL.BemerkungsBaustein.LIST);
    }

    /**
     * Show a BemerkungsBaustein.
     *
     * @param bemerkungsBausteinId the Id of the BemerkungsBaustein.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.BemerkungsBaustein
            .P_BEMERKUNGS_BAUSTEIN_ID) Long bemerkungsBausteinId, Model model) {
        LOG.debug("Show bemerkungsBausteinId: " + bemerkungsBausteinId);

        addStandardModelData(bemerkungsBausteinService.read(bemerkungsBausteinId),
                URL.BemerkungsBaustein.LIST, true, model);
        return BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Edit a BemerkungsBaustein.
     *
     * @param bemerkungsBausteinId the Id of the BemerkungsBaustein.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.BemerkungsBaustein
            .P_BEMERKUNGS_BAUSTEIN_ID) Long bemerkungsBausteinId, Model model) {
        LOG.debug("Edit bemerkungsBausteinId: " + bemerkungsBausteinId);
        addStandardModelData(bemerkungsBausteinService.read(bemerkungsBausteinId), URL.filledURL(
                URL.BemerkungsBaustein.EDIT, bemerkungsBausteinId), false, model);
        return BEMERKUNGS_BAUSTEIN_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param bemerkungsBaustein the BemerkungsBaustein
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(BemerkungsBaustein bemerkungsBaustein, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("BemerkungsBaustein: {}", bemerkungsBaustein);

        if (bemerkungsBaustein == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("bemerkungsBaustein", bemerkungsBaustein);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a BemerkungsBaustein.
     *
     * @param bemerkungsBaustein the BemerkungsBaustein.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.BemerkungsBaustein.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") BemerkungsBaustein bemerkungsBaustein,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, bemerkungsBaustein,
                "bemerkungsBaustein").hasErrors()) {
            addStandardModelData(bemerkungsBaustein, URL.filledURL(URL.BemerkungsBaustein.EDIT,
                    bemerkungsBaustein.getId()), false, model);
            return BEMERKUNGS_BAUSTEIN_FORM;
        }

        LOG.debug("Update BemerkungsBaustein: " + bemerkungsBaustein);

        bemerkungsBausteinService.save(bemerkungsBaustein);

        return URL.redirect(URL.BemerkungsBaustein.LIST);
    }
}
