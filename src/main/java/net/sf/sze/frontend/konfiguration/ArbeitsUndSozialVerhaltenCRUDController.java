// ArbeitsUndSozialVerhaltenCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.konfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnisconfig.ArbeitsUndSozialVerhalten;
import net.sf.sze.model.zeugnisconfig.AvSvTyp;
import net.sf.sze.service.api.zeugnisconfig.ArbeitsUndSozialVerhaltenService;

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
 * Controller for Create, Read, Update and Delete for the model ArbeitsUndSozialVerhalten.
 *
 */
@Controller()
public class ArbeitsUndSozialVerhaltenCRUDController {

    /**
     * View which is used as form.
     */
    private static final String ARBEITS_UND_SOZIAL_VERHALTEN_FORM
            = "arbeitsUndSozialVerhalten/arbeitsUndSozialVerhaltenform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ArbeitsUndSozialVerhaltenCRUDController.class);

    /**
     * The arbeitsUndSozialVerhaltenService instance.
     */
    @Resource
    private ArbeitsUndSozialVerhaltenService arbeitsUndSozialVerhaltenService;

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
     * List all ArbeitsUndSozialVerhaltens.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.ArbeitsUndSozialVerhalten.HOME,
            URL.ArbeitsUndSozialVerhalten.LIST}, method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"typ", "sortierung", "name"}, direction = Direction.DESC) Pageable pageRequest,
            RedirectAttributes redirectAttributes) {
        final PageWrapper<ArbeitsUndSozialVerhalten> arbeitsUndSozialVerhaltenList =
                new PageWrapper<ArbeitsUndSozialVerhalten>(
                arbeitsUndSozialVerhaltenService.getArbeitsUndSozialVerhalten(pageRequest),
                URL.ArbeitsUndSozialVerhalten.LIST);
        if (arbeitsUndSozialVerhaltenList.getSize() == 0) {
            LOG.info("No ArbeitsUndSozialVerhalten found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste ArbeitsUndSozialVerhalten an.");
            return URL.redirect(URL.ArbeitsUndSozialVerhalten.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("arbeitsUndSozialVerhaltenList", arbeitsUndSozialVerhaltenList);
        return "arbeitsUndSozialVerhalten/arbeitsUndSozialVerhaltenlist";
    }

    /**
     * Create a new ArbeitsUndSozialVerhalten form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten = new ArbeitsUndSozialVerhalten();
        addStandardModelData(arbeitsUndSozialVerhalten, URL.ArbeitsUndSozialVerhalten.CREATE, false,
                    model);
        return ARBEITS_UND_SOZIAL_VERHALTEN_FORM;
    }

    /**
     * Insert a new ArbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhalten the ArbeitsUndSozialVerhalten.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("arbeitsUndSozialVerhalten") ArbeitsUndSozialVerhalten
            arbeitsUndSozialVerhalten, BindingResult result, Model model) {
        validator.validate(arbeitsUndSozialVerhalten, result);

        if (result.hasErrors()) {
            addStandardModelData(arbeitsUndSozialVerhalten, URL.ArbeitsUndSozialVerhalten.CREATE,
                    false, model);
            return ARBEITS_UND_SOZIAL_VERHALTEN_FORM;
        }

        LOG.debug("Create ArbeitsUndSozialVerhalten: " + arbeitsUndSozialVerhalten);
        arbeitsUndSozialVerhaltenService.save(arbeitsUndSozialVerhalten);
        return URL.redirect(URL.ArbeitsUndSozialVerhalten.LIST);
    }

    /**
     * Create confirmation for deleting a ArbeitsUndSozialVerhalten.
     *
     * @param id the Id of the ArbeitsUndSozialVerhalten.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete arbeitsUndSozialVerhaltenId: " + id);
        model.addAttribute("deleteURL", URL.ArbeitsUndSozialVerhalten.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.ArbeitsUndSozialVerhalten.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a ArbeitsUndSozialVerhalten.
     *
     * @param id the Id of the ArbeitsUndSozialVerhalten.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete arbeitsUndSozialVerhaltenId: " + id);
        arbeitsUndSozialVerhaltenService.delete(id);
        LOG.debug("Deleted arbeitsUndSozialVerhaltenId: " + id);
        return URL.redirect(URL.ArbeitsUndSozialVerhalten.LIST);
    }

    /**
     * Show a ArbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhaltenId the Id of the ArbeitsUndSozialVerhalten.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.ArbeitsUndSozialVerhalten
            .P_ARBEITS_UND_SOZIAL_VERHALTEN_ID) Long arbeitsUndSozialVerhaltenId, Model model) {
        LOG.debug("Show arbeitsUndSozialVerhaltenId: " + arbeitsUndSozialVerhaltenId);

        addStandardModelData(arbeitsUndSozialVerhaltenService.read(arbeitsUndSozialVerhaltenId),
                URL.ArbeitsUndSozialVerhalten.LIST, true, model);
        return ARBEITS_UND_SOZIAL_VERHALTEN_FORM;
    }

    /**
     * Edit a ArbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhaltenId the Id of the ArbeitsUndSozialVerhalten.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.ArbeitsUndSozialVerhalten
            .P_ARBEITS_UND_SOZIAL_VERHALTEN_ID) Long arbeitsUndSozialVerhaltenId, Model model) {
        LOG.debug("Edit arbeitsUndSozialVerhaltenId: " + arbeitsUndSozialVerhaltenId);
        addStandardModelData(arbeitsUndSozialVerhaltenService.read(arbeitsUndSozialVerhaltenId),
                URL.filledURL(URL.ArbeitsUndSozialVerhalten.EDIT, arbeitsUndSozialVerhaltenId),
                false, model);
        return ARBEITS_UND_SOZIAL_VERHALTEN_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param arbeitsUndSozialVerhalten the ArbeitsUndSozialVerhalten
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten,
            String saveUrl, boolean disabled, Model model) {
        LOG.info("ArbeitsUndSozialVerhalten: {}", arbeitsUndSozialVerhalten);

        if (arbeitsUndSozialVerhalten == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("avsvtypen", AvSvTyp.values());
        model.addAttribute("arbeitsUndSozialVerhalten", arbeitsUndSozialVerhalten);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a ArbeitsUndSozialVerhalten.
     *
     * @param arbeitsUndSozialVerhalten the ArbeitsUndSozialVerhalten.
     * @param request the request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.ArbeitsUndSozialVerhalten.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, arbeitsUndSozialVerhalten,
                "arbeitsUndSozialVerhalten").hasErrors()) {
            addStandardModelData(arbeitsUndSozialVerhalten,
                    URL.filledURL(URL.ArbeitsUndSozialVerhalten.EDIT,
                    arbeitsUndSozialVerhalten.getId()), false, model);
            return ARBEITS_UND_SOZIAL_VERHALTEN_FORM;
        }

        LOG.debug("Update ArbeitsUndSozialVerhalten: " + arbeitsUndSozialVerhalten);

        arbeitsUndSozialVerhaltenService.save(arbeitsUndSozialVerhalten);

        return URL.redirect(URL.ArbeitsUndSozialVerhalten.LIST);
    }
}
