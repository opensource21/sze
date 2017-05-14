// ZeugnisFormularCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.zeugnis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;

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
 * Controller for Create, Read, Update and Delete for the model ZeugnisFormular.
 *
 */
@Controller()
public class ZeugnisFormularCRUDController {

    /**
     * Maximale Anzahl an anzuzeigenden Templates.
     */
    private static final int MAX_NR_OF_TEMPLATES = 10;

    /**
     * View which is used as form.
     */
    private static final String SCHULHALBJAHR_FORM = "zeugnisFormular/zeugnisFormularform";

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
     * Erzeugt die aktuellen Zeugnisformulare.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.ZeugnisFormular.CREATE_CURRENT},
            method = RequestMethod.GET)
    public String createCurrent() {
        zeugnisFormularService.init(Calendar.getInstance());
        return URL.redirect(URL.ZeugnisFormular.LIST);
    }

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
        return "zeugnisFormular/zeugnisFormularlist";
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
        zeugnisFormular.setSchulhalbjahr(zeugnisFormularService.getNewestSchulhalbjahr());
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
    public String insert(@ModelAttribute("zeugnisFormular") ZeugnisFormular zeugnisFormular,
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

        final List<String> templates = new ArrayList<>();
        final String currentFileName = zeugnisFormular.getTemplateFileName();
        final List<String> fileNames = zeugnisFormularService.getFileNames();
        final int currentPos = fileNames.indexOf(currentFileName);
        final int maxNrOfTemplates = Math.min(MAX_NR_OF_TEMPLATES, fileNames.size());
        templates.addAll(fileNames.subList(0, maxNrOfTemplates));
        model.addAttribute("unkownTemplate", Boolean.valueOf(currentPos == -1));
        model.addAttribute("klassenListe", zeugnisFormularService.
                getActiveClasses(zeugnisFormular));
        model.addAttribute("schulhalbjahre", zeugnisFormularService.
                getActiveSchulhalbjahre(zeugnisFormular));
        model.addAttribute("zeugnisFormular", zeugnisFormular);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("templates", templates);
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a ZeugnisFormular.
     *
     * @param zeugnisFormular the ZeugnisFormular.
     * @param request request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ZeugnisFormular.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") ZeugnisFormular zeugnisFormular,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, zeugnisFormular,
                "zeugnisFormular").hasErrors()) {
            addStandardModelData(zeugnisFormular, URL.filledURL(URL.ZeugnisFormular.EDIT,
                    zeugnisFormular.getId()), false, model);
            return SCHULHALBJAHR_FORM;
        }
        LOG.debug("Update ZeugnisFormular: " + zeugnisFormular);
        zeugnisFormularService.save(zeugnisFormular);

        return URL.redirect(URL.ZeugnisFormular.LIST);
    }
}
