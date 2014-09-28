// SchulfachDetailInfoCRUDController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.zeugnis;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.service.api.zeugnis.SchulfachDetailInfoService;
import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;
import net.sf.sze.service.api.zeugnisconfig.SchulfachService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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
 * Controller for Create, Read, Update and Delete for the model SchulfachDetailInfo.
 *
 */
@Controller()
public class SchulfachDetailInfoCRUDController {

    /**
     * View which is used as form.
     */
    private static final String SCHULHALBJAHR_FORM = "schulfachDetailInfo/schulfachDetailInfoform";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SchulfachDetailInfoCRUDController.class);

    /**
     * The schulfachDetailInfoService instance.
     */
    @Resource
    private SchulfachDetailInfoService schulfachDetailInfoService;


    /**
     * Der {@link SchulfachService}.
     */
    @Resource
    private SchulfachService schulfachService;

    /**
     * ein {@link ZeugnisFormularService}.
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
     * List all SchulfachDetailInfos.
     *
     * @param model the model.
     * @param pageRequest attributes about paginating.
     * @param redirectAttributes die {@link RedirectAttributes}.
     * @return String which defines the next page.
     */
    @RequestMapping(value = {URL.SchulfachDetailInfo.HOME, URL.SchulfachDetailInfo.LIST},
            method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(page = 0, size = 1000,
            sort = {"formular_id"}, direction = Direction.DESC)
            Pageable pageRequest, RedirectAttributes redirectAttributes) {
        final PageWrapper<SchulfachDetailInfo> schulfachDetailInfoList =
                new PageWrapper<SchulfachDetailInfo>(
                schulfachDetailInfoService.getSchulfachDetailInfo(pageRequest),
                URL.SchulfachDetailInfo.LIST);
        if (schulfachDetailInfoList.getSize() == 0) {
            LOG.info("No SchulfachDetailInfo found redirect to create");
            redirectAttributes.addFlashAttribute(ModelAttributes.MESSAGE,
                    "Bitte legen sie das erste SchulfachDetailInfo an.");
            return URL.redirect(URL.SchulfachDetailInfo.CREATE);
        }

        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("schulfachDetailInfoList", schulfachDetailInfoList);
        return "schulfachDetailInfo/schulfachDetailInfolist";
    }

    /**
     * Create a new SchulfachDetailInfo form.
     *
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        final SchulfachDetailInfo schulfachDetailInfo = new SchulfachDetailInfo();
//        schulfachDetailInfo.setSchulhalbjahr(schulfachDetailInfoService.getNewestSchulhalbjahr());
        addStandardModelData(schulfachDetailInfo, URL.SchulfachDetailInfo.CREATE, false,
                    model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Insert a new SchulfachDetailInfo.
     *
     * @param schulfachDetailInfo the SchulfachDetailInfo.
     * @param result the bindingsresult.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.CREATE, method = RequestMethod.POST)
    public String insert(@ModelAttribute("schulfachDetailInfo")
            SchulfachDetailInfo schulfachDetailInfo, BindingResult result, Model model) {
        validator.validate(schulfachDetailInfo, result);

        if (result.hasErrors()) {
            addStandardModelData(schulfachDetailInfo, URL.SchulfachDetailInfo.CREATE, false, model);
            return SCHULHALBJAHR_FORM;
        }

        LOG.debug("Create SchulfachDetailInfo: " + schulfachDetailInfo);
        schulfachDetailInfoService.save(schulfachDetailInfo);
        return URL.redirect(URL.SchulfachDetailInfo.LIST);
    }

    /**
     * Create confirmation for deleting a SchulfachDetailInfo.
     *
     * @param id the Id of the SchulfachDetailInfo.
     * @param model the datamodel.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.DELETE, method = RequestMethod.GET)
    public String deleteConfirm(@RequestParam("id") Long id, Model model) {
        LOG.debug("Confirm delete schulfachDetailInfoId: " + id);
        model.addAttribute("deleteURL", URL.SchulfachDetailInfo.DELETE);
        model.addAttribute("id", id);
        model.addAttribute("cancelURL", URL.filledURL(URL.SchulfachDetailInfo.LIST));
        return "confirmDelete";
    }

    /**
     * Delete a SchulfachDetailInfo.
     *
     * @param id the Id of the SchulfachDetailInfo.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.DELETE,
            method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(Long id) {
        LOG.debug("Delete schulfachDetailInfoId: " + id);
        schulfachDetailInfoService.delete(id);
        LOG.debug("Deleted schulfachDetailInfoId: " + id);
        return URL.redirect(URL.SchulfachDetailInfo.LIST);
    }

    /**
     * Show a SchulfachDetailInfo.
     *
     * @param schulfachDetailInfoId the Id of the SchulfachDetailInfo.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.SHOW, method = RequestMethod.GET)
    public String show(@PathVariable(URL.SchulfachDetailInfo
            .P_SCHULFACH_DETAIL_INFO_ID) Long schulfachDetailInfoId, Model model) {
        LOG.debug("Show schulfachDetailInfoId: " + schulfachDetailInfoId);

        addStandardModelData(schulfachDetailInfoService.read(schulfachDetailInfoId),
                URL.SchulfachDetailInfo.LIST, true, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Edit a SchulfachDetailInfo.
     *
     * @param schulfachDetailInfoId the Id of the SchulfachDetailInfo.
     * @param model the model.
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable(URL.SchulfachDetailInfo
            .P_SCHULFACH_DETAIL_INFO_ID) Long schulfachDetailInfoId, Model model) {
        LOG.debug("Edit schulfachDetailInfoId: " + schulfachDetailInfoId);
        addStandardModelData(schulfachDetailInfoService.read(schulfachDetailInfoId), URL.filledURL(
                URL.SchulfachDetailInfo.EDIT, schulfachDetailInfoId), false, model);
        return SCHULHALBJAHR_FORM;
    }

    /**
     * Adds the standard model data.
     *
     * @param schulfachDetailInfo the SchulfachDetailInfo
     * @param disabled true if the data should be only show.
     * @param saveUrl die URL zum speichern.
     * @param model the model
     */
    private void addStandardModelData(SchulfachDetailInfo schulfachDetailInfo, String saveUrl,
            boolean disabled, Model model) {
        LOG.info("SchulfachDetailInfo: {}", schulfachDetailInfo);

        if (schulfachDetailInfo == null) {
            throw new ResourceNotFoundException();
        }
        List<Schulfach> schulfachList = schulfachService.getSchulfach(
                new PageRequest(0, 100, Direction.ASC, "name")).getContent();
        List<ZeugnisFormular> formularList = zeugnisFormularService.getZeugnisFormular(
                new PageRequest(0, 100, Direction.DESC, "schulhalbjahr")).getContent();
        model.addAttribute("schulfaecher", schulfachList);
        model.addAttribute("formulare", formularList);
        model.addAttribute("schulfachDetailInfo", schulfachDetailInfo);
        model.addAttribute("disabled", Boolean.valueOf(disabled));
        model.addAttribute("saveUrl", saveUrl);
    }

    /**
     * Update a SchulfachDetailInfo.
     *
     * @param schulfachDetailInfo the SchulfachDetailInfo.
     * @param request request-data
     * @param model the model
     * @return String which defines the next page.
     */
    @RequestMapping(value = URL.SchulfachDetailInfo.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam("id") SchulfachDetailInfo schulfachDetailInfo,
            HttpServletRequest request, Model model) {
        if (servletBindingService.bindAndValidate(request, model, schulfachDetailInfo,
                "schulfachDetailInfo").hasErrors()) {
            addStandardModelData(schulfachDetailInfo, URL.filledURL(URL.SchulfachDetailInfo.EDIT,
                    schulfachDetailInfo.getId()), false, model);
            return SCHULHALBJAHR_FORM;
        }
        LOG.debug("Update SchulfachDetailInfo: " + schulfachDetailInfo);
        schulfachDetailInfoService.save(schulfachDetailInfo);

        return URL.redirect(URL.SchulfachDetailInfo.LIST);
    }
}
