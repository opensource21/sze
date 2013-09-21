// BemerkungCRUDController.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend.crud;

import de.ppi.spring.mvc.util.DefaultExceptionHandler;
import de.ppi.spring.mvc.util.PageWrapper;
import de.ppi.spring.mvc.util.ResourceNotFoundException;

import net.sf.sze.frontend.URL;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.service.api.BemerkungService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Controller for Create, Read, Update and Delete for the model Bemerkung.
 *
 */
@Controller()
public class BemerkungCRUDController {

    /**
     * View which is used as form.
     */
    private static final String BEMERKUNG_FORM =
            "example/bemerkung/bemerkungform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            BemerkungCRUDController.class);

    /**
     * The BemerkungService instance.
     */
    @Resource
    private BemerkungService bemerkungService;

    /**
     * The generic validator.
     */
    @Resource
    private Validator validator;

    /**
     * List all bemerkungs.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.Bemerkung.HOME, URL.Bemerkung.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefaults(pageNumber = 0, value = 5,
            sort = {"bemerkungId"}, sortDir = Direction
                    .ASC) Pageable pageRequest) {
        final PageWrapper<Bemerkung> bemerkungList = new PageWrapper<Bemerkung>(
                bemerkungService.getBemerkung(pageRequest), URL.Bemerkung.LIST);
        if (bemerkungList.getSize() == 0) {
            LOG.info("No bemerkung found redirect to create");
            return URL.redirect(URL.Bemerkung.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("bemerkungList", bemerkungList);
        // model.addAttribute("viewUrl", URL.Bemerkung.VIEW);
        // model.addAttribute("editUrl", URL.Bemerkung.EDIT);
        // model.addAttribute("deleteUrl", URL.Bemerkung.DELETE);
        return "example/bemerkung/list";
    }

    /**
     * Create a new bemerkung form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        if (bemerkungService.getNrOfBemerkungs() == 0) {
            model.addAttribute("message", "bemerkung.list_empty");
        }

        addStandardModelData(new Bemerkung(), URL.Bemerkung.CREATE, false,
                model);
        return BEMERKUNG_FORM;
    }

    /**
     * Insert a new bemerkung.
     *
     * @param bemerkung the bemerkung.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("bemerkung") Bemerkung bemerkung,
            BindingResult result, Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            addStandardModelData(bemerkung, URL.Bemerkung.CREATE, false, model);
            return BEMERKUNG_FORM;
        }

        LOG.debug("Create Bemerkung: " + bemerkung);
        bemerkungService.save(bemerkung);
        return URL.redirect(URL.Bemerkung.LIST);
    }

    /**
     * Create confirmation for deleting a bemerkung.
     *
     * @param bemerkungId the Id of the bemerkung.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@PathVariable(URL.Bemerkung
            .P_BEMERKUNGID) Long bemerkungId, Model model) {
        LOG.debug("Confirm delete BemerkungId: " + bemerkungId);
        model.addAttribute("deleteURL", URL.filledURL(URL.Bemerkung.DELETE,
                bemerkungId));
        model.addAttribute("cancelURL", URL.filledURL(URL.Bemerkung.LIST));
        return "example/confirmDelete";
    }

    /**
     * Delete a bemerkung.
     *
     * @param bemerkungId the Id of the bemerkung.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(@PathVariable(URL.Bemerkung
            .P_BEMERKUNGID) Long bemerkungId) {
        LOG.debug("Delete BemerkungId: " + bemerkungId);
        bemerkungService.delete(bemerkungId);
        LOG.debug("Deleted BemerkungId: " + bemerkungId);
        return URL.redirect(URL.Bemerkung.LIST);
    }

    /**
     * Show a bemerkung.
     *
     * @param bemerkungId the Id of the bemerkung.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.Bemerkung
            .P_BEMERKUNGID) Long bemerkungId, Model model) {
        LOG.debug("Show BemerkungId: " + bemerkungId);

        addStandardModelData(bemerkungService.read(bemerkungId), URL.Bemerkung
                .LIST, true, model);
        return BEMERKUNG_FORM;
    }

    /**
     * Edit a bemerkung.
     *
     * @param bemerkungId the Id of the bemerkung.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.Bemerkung
            .P_BEMERKUNGID) Long bemerkungId, Model model) {
        LOG.debug("Edit BemerkungId: " + bemerkungId);
        addStandardModelData(bemerkungService.read(bemerkungId), URL.filledURL(
                URL.Bemerkung.EDIT, bemerkungId), false, model);
        return BEMERKUNG_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param bemerkung the bemerkung
     * @param disabled true if the data should be only show.
     * @param url the action URL.
     * @param model the model
     */
    private void addStandardModelData(Bemerkung bemerkung, String url,
            boolean disabled, Model model) {
        LOG.info("Bemerkung: {}", bemerkung);

        if (bemerkung == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("bemerkung", bemerkung);
//      model.addAttribute("posts", bemerkungService.getPostingSelectOptions());
//      model.addAttribute("sexList", Sex.values());
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("url", url);
    }

    /**
     * Update a bemerkung.
     *
     * @param bemerkung the bemerkung.
     * @param result the bindings result.
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.Bemerkung.EDIT, method = RequestMethod.POST)
    public String update(@ModelAttribute("bemerkung") Bemerkung bemerkung,
            BindingResult result, Model model) {
        validator.validate(bemerkung, result);

        if (result.hasErrors()) {
            addStandardModelData(bemerkung, URL.filledURL(URL.Bemerkung.EDIT,
                    bemerkung.getId()), false, model);
            return BEMERKUNG_FORM;
        }

        LOG.debug("Update Bemerkung: " + bemerkung);

        try {
            bemerkungService.save(bemerkung);
        } catch (ConcurrencyFailureException olE) {
            DefaultExceptionHandler.handleConcurrencyFailureException(result,
                    olE);
            addStandardModelData(bemerkung, URL.filledURL(URL.Bemerkung.EDIT,
                    bemerkung.getId()), false, model);
            return BEMERKUNG_FORM;
        }

        return URL.redirect(URL.Bemerkung.LIST);
    }
}
