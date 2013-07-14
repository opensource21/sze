// ZeugnisController.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Haupt-Controlller für die Zeugniserfassung.
 *
 */
@Controller
public class ZeugnisController {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisController.class);

    /**
     * Zeigt den Auswahl-Dialog für die Klasse.
     *
     * @param model das Modell.
     * @return den View-Namen.
     */
    @RequestMapping({URL.Zeugnis.HOME, URL.Zeugnis.START})
    public String chooseClass(Model model) {
        model.addAttribute("klassen", null);
        model.addAttribute("halbjahre", null);
        model.addAttribute("helpMessageId", "help.chooseClass");
        return "zeugnis/chooseClass";
    }

    /**
     * Show an edit view for a user.
     *
     * @param userId the id of the user.
     * @param model the model where to safe the data for the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/xx/{userId}")
    public String editUser(@PathVariable("userId") String userId, Model model) {
        LOG.info("UserId: {}", userId);
        model.addAttribute("userId", userId);
        return "example/editBootstrap";
    }
}
