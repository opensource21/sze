//ZeugnisController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.konfiguration;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.zeugnisconfig.ZeugnisInitialierungsService;
import net.sf.sze.util.ResultContainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller für die Steuerung bei der Konfiguration.
 *
 */
@Controller
public class KonfigurationController {

    @Resource
    private ZeugnisInitialierungsService zeugnisInitialierungsService;

    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;

    /**
     * Zeigt die Übersichtseite für die Konfiguraztion an.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Configuration.HOME, method = RequestMethod.GET)
    public String home() {
        return "konfiguration/index";
    }


    /**
     * Zeigt die Seite zur Zeugnisinitialisierung an.
     * @param model das Viewmodel.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Configuration.INIT_ZEUGNISSE, method = RequestMethod.GET)
    public String initializeZeugnisse(Model model) {
        final List<ZeugnisFormular> formulare = zeugnisFormularDao.
                findAllBySchulhalbjahrSelectableOrderBySchulhalbjahrJahrDescSchulhalbjahrHalbjahrDescKlasseJahrgangDescKlasseSuffixAscBeschreibungDesc(true);
        model.addAttribute("formularList", formulare);
        return "konfiguration/initZeugnisse";
    }

    /**
     * Initialisiert die Zeugnisse.
     * @param formulare die Formulare für die die Zeugnisse zu initialisieren sind.
     * @param redirectAttributes der Datenspeicher für den Redirect.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Configuration.INIT_ZEUGNISSE, method = RequestMethod.POST)
    public String initializeZeugnisse(@RequestParam(value = URL.Configuration.
            P_FORMULAR, required = false) List<ZeugnisFormular> formulare,
            RedirectAttributes redirectAttributes) {
        if (CollectionUtils.isEmpty(formulare)) {
            redirectAttributes.addFlashAttribute("message", "Es wurde kein Formular ausgewählt.");
        } else {
            final ResultContainer result = new ResultContainer();
            for (ZeugnisFormular zeugnisFormular : formulare) {
                result.addResultContainer(zeugnisInitialierungsService.initZeugnis(zeugnisFormular));
            }
            redirectAttributes.addFlashAttribute("result", result);
        }
        return URL.redirect(URL.Configuration.INIT_ZEUGNISSE);
    }
}
