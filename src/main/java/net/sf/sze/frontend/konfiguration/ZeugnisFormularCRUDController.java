// ZeugnisFormularCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisFormularService;

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

import de.ppi.fuwesta.spring.mvc.util.PageWrapper;
import de.ppi.fuwesta.spring.mvc.util.ResourceNotFoundException;

/**
 * Controller for Create, Read, Update and Delete for the model ZeugnisFormular.
 *
 */
@Controller()
public class ZeugnisFormularCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULHALBJAHR_FORM = "zeugnisFormular/form";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisFormularCRUDController.class);

    /**
     * The zeugnisFormularService instance.
     */
    @Resource
    private ZeugnisFormularService zeugnisFormularService;

    /**
     * The generic validator.
     */
    @Resource
    private Validator validator;

    /**
     * List all ZeugnisFormulars.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.ZeugnisFormular.HOME, URL.ZeugnisFormular.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"beschreibung"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<ZeugnisFormular> zeugnisFormularList = new PageWrapper<ZeugnisFormular>(
                zeugnisFormularService.getZeugnisFormular(pageRequest), URL.ZeugnisFormular.LIST);
        if (zeugnisFormularList.getSize() == 0) {
            LOG.info("No ZeugnisFormular found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste ZeugnisFormular an.");
            return URL.redirect(URL.ZeugnisFormular.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("zeugnisFormularList", zeugnisFormularList);
        return "zeugnisFormular/list";
    }

    /**
     * Create a new ZeugnisFormular form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final ZeugnisFormular zeugnisFormular = new ZeugnisFormular();
        addStandardModelData(zeugnisFormular, URL.ZeugnisFormular.CREATE, false,
                    model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Insert a new ZeugnisFormular.
     *
     * @param zeugnisFormular the ZeugnisFormular.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("ZeugnisFormular") ZeugnisFormular zeugnisFormular,
            BindingResult result, Model model) {
        validator.validate(zeugnisFormular, result);

        if (result.hasErrors()) {
            addStandardModelData(zeugnisFormular, URL.ZeugnisFormular.CREATE, false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Create ZeugnisFormular: " + zeugnisFormular);
        zeugnisFormularService.save(zeugnisFormular);
        return URL.redirect(URL.ZeugnisFormular.LIST);
    }

    /**
     * Create confirmation for deleting a ZeugnisFormular.
     *
     * @param id the Id of the ZeugnisFormular.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete zeugnisFormularId: " + id);
        model.addAttribute("deleteURL", URL.ZeugnisFormular.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.ZeugnisFormular.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a ZeugnisFormular.
     *
     * @param id the Id of the ZeugnisFormular.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete zeugnisFormularId: " + id);
        zeugnisFormularService.delete(id);
        LOG.debug("Deleted zeugnisFormularId: " + id);
        return URL.redirect(URL.ZeugnisFormular.LIST);
    }

    /**
     * Show a ZeugnisFormular.
     *
     * @param zeugnisFormularId the Id of the ZeugnisFormular.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.ZeugnisFormular
            .P_ZEUGNISFORMULAR_ID) Long zeugnisFormularId, Model model) {
        LOG.debug("Show zeugnisFormularId: " + zeugnisFormularId);

        addStandardModelData(zeugnisFormularService.read(zeugnisFormularId), URL.ZeugnisFormular
                .LIST, true, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Edit a ZeugnisFormular.
     *
     * @param zeugnisFormularId the Id of the ZeugnisFormular.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.ZeugnisFormular
            .P_ZEUGNISFORMULAR_ID) Long zeugnisFormularId, Model model) {
        LOG.debug("Edit zeugnisFormularId: " + zeugnisFormularId);
        addStandardModelData(zeugnisFormularService.read(zeugnisFormularId), URL.filledURL(
                URL.ZeugnisFormular.EDIT, zeugnisFormularId), false, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param zeugnisFormular the ZeugnisFormular
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(ZeugnisFormular zeugnisFormular, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("ZeugnisFormular: {}", zeugnisFormular);

        if (zeugnisFormular == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("zeugnisFormular", zeugnisFormular);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a ZeugnisFormular.
     *
     * @param zeugnisFormular the ZeugnisFormular.
     * @param result the bindings result.
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.EDIT, method = RequestMethod.POST)
    public String update(@ModelAttribute("zeugnisFormular") ZeugnisFormular zeugnisFormular,
            BindingResult result, Model model) {
        validator.validate(zeugnisFormular, result);

        if (result.hasErrors()) {
            addStandardModelData(zeugnisFormular, URL.filledURL(URL.ZeugnisFormular.EDIT,
                    zeugnisFormular.getId()), false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Update ZeugnisFormular: " + zeugnisFormular);

        zeugnisFormularService.save(zeugnisFormular);

        return URL.redirect(URL.ZeugnisFormular.LIST);
    }
}
