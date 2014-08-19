// ZeugnisArtCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.ZeugnisArt;
import net.sf.sze.service.api.ZeugnisArtService;
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
 * Controller for Create, Read, Update and Delete for the model ZeugnisArt.
 *
 */
@Controller()
public class ZeugnisArtCRUDController {

    /**
     * View which is used as form.
     */
    private static final String ZEUGNIS_ART_FORM =
            "zeugnisArt/zeugnisArtform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisArtCRUDController.class);

    /**
     * The zeugnisArtService instance.
     */
    @Resource
    private ZeugnisArtService zeugnisArtService;

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
     * List all ZeugnisArts.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.ZeugnisArt.HOME, URL.ZeugnisArt.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"sortierung", "name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<ZeugnisArt> zeugnisArtList =
                new PageWrapper<ZeugnisArt>(
                zeugnisArtService.getZeugnisArt(pageRequest),
                URL.ZeugnisArt.LIST);
        if (zeugnisArtList.getSize() == 0) {
            LOG.info("No ZeugnisArt found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste ZeugnisArt an.");
            return URL.redirect(URL.ZeugnisArt.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("zeugnisArtList", zeugnisArtList);
        return "zeugnisArt/zeugnisArtlist";
    }

    /**
     * Create a new ZeugnisArt form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final ZeugnisArt zeugnisArt = new ZeugnisArt();
        addStandardModelData(zeugnisArt, URL.ZeugnisArt.CREATE, false,
                    model);
        return ZEUGNIS_ART_FORM;
    }

    /**
     * Insert a new ZeugnisArt.
     *
     * @param zeugnisArt the ZeugnisArt.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("zeugnisArt") ZeugnisArt
            zeugnisArt, BindingResult result, Model model) {
        validator.validate(zeugnisArt, result);

        if (result.hasErrors()) {
            addStandardModelData(zeugnisArt, URL.ZeugnisArt.CREATE, false, model);
            return ZEUGNIS_ART_FORM;
        }

        LOG.debug("Create ZeugnisArt: " + zeugnisArt);
        zeugnisArtService.save(zeugnisArt);
        return URL.redirect(URL.ZeugnisArt.LIST);
    }

    /**
     * Create confirmation for deleting a ZeugnisArt.
     *
     * @param id the Id of the ZeugnisArt.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete zeugnisArtId: " + id);
        model.addAttribute("deleteURL", URL.ZeugnisArt.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.ZeugnisArt.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a ZeugnisArt.
     *
     * @param id the Id of the ZeugnisArt.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete zeugnisArtId: " + id);
        zeugnisArtService.delete(id);
        LOG.debug("Deleted zeugnisArtId: " + id);
        return URL.redirect(URL.ZeugnisArt.LIST);
    }

    /**
     * Show a ZeugnisArt.
     *
     * @param zeugnisArtId the Id of the ZeugnisArt.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.ZeugnisArt
            .P_ZEUGNIS_ART_ID) Long zeugnisArtId, Model model) {
        LOG.debug("Show zeugnisArtId: " + zeugnisArtId);

        addStandardModelData(zeugnisArtService.read(zeugnisArtId),
                URL.ZeugnisArt.LIST, true, model);
        return ZEUGNIS_ART_FORM;
    }

    /**
     * Edit a ZeugnisArt.
     *
     * @param zeugnisArtId the Id of the ZeugnisArt.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.ZeugnisArt
            .P_ZEUGNIS_ART_ID) Long zeugnisArtId, Model model) {
        LOG.debug("Edit zeugnisArtId: " + zeugnisArtId);
        addStandardModelData(zeugnisArtService.read(zeugnisArtId), URL.filledURL(
                URL.ZeugnisArt.EDIT, zeugnisArtId), false, model);
        return ZEUGNIS_ART_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param zeugnisArt the ZeugnisArt
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(ZeugnisArt zeugnisArt, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("ZeugnisArt: {}", zeugnisArt);

        if (zeugnisArt == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("zeugnisArt", zeugnisArt);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a ZeugnisArt.
     *
     * @param zeugnisArt the ZeugnisArt.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisArt.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") ZeugnisArt zeugnisArt,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, zeugnisArt,
                "zeugnisArt").hasErrors()) {
            addStandardModelData(zeugnisArt, URL.filledURL(URL.ZeugnisArt.EDIT,
                    zeugnisArt.getId()), false, model);
            return ZEUGNIS_ART_FORM;
        }

        LOG.debug("Update ZeugnisArt: " + zeugnisArt);

        zeugnisArtService.save(zeugnisArt);

        return URL.redirect(URL.ZeugnisArt.LIST);
    }
}
