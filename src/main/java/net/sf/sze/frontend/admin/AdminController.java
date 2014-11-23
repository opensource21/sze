//AdminController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.admin;

import javax.annotation.Resource;

import net.sf.sze.frontend.base.URL;
import net.sf.sze.service.api.common.AnonymisierungsService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller ür Admin-Aufgaben.
 *
 */
@Controller
public class AdminController {

    /** Der {@link AnonymisierungsService}.*/
    @Resource
    private AnonymisierungsService anonymisierungsService;

    /**
     * Zeigt die Übersichtseite für die Konfiguraztion an.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Admin.MAIN, method = RequestMethod.GET)
    public String home() {
        return "admin/index";
    }


    /**
     * Zeigt die Seite zur Anonymisierung an.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Admin.ANONYM, method = RequestMethod.GET)
    public String anonym() {
        return "admin/anonymForm";
    }

    /**
     * Führt die anonymsierung aus.
     * @param anonym muss "fullanonym" lauten, damit die Anonymiserung gemacht wird.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Admin.ANONYM, method = RequestMethod.POST)
    public String anonym(@RequestParam(value = URL.Admin.P_ANONYM) String anonym) {
        anonymisierungsService.replaceAllNamesWithVariables();
        if ("fullanonym".equals(anonym)) {
            anonymisierungsService.anonymisierSchueler();
        }
        return "admin/index";
    }

}
