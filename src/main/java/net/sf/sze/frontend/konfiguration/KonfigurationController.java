//ZeugnisController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.konfiguration;

import net.sf.sze.frontend.base.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller für die Steuerung bei der Konfiguration.
 *
 */
@Controller
public class KonfigurationController {

    /**
     * Zeigt die Übersichtseite für die Konfiguraztion an.
     * @return die anzuzeigende View.
     */
    @RequestMapping(value = URL.Configuration.MAIN, method = RequestMethod.GET)
    public String home() {
        return "konfiguration/index";
    }
}
