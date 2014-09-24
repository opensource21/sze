// HomeController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Simple start-controller.
 *
 */
@Controller
public class HomeController {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            HomeController.class);

    /**
     * Show the home page.
     *
     * @return the logical view-name.
     */
    @RequestMapping({"/"})
    public String showHomePage() {
        return URL.redirect(URL.Zeugnis.START);
    }

    /**
     * Show an edit view for a user.
     *
     * @param userId the id of the user.
     * @param model the model where to safe the data for the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/example/{userId}")
    public String editUser(@PathVariable("userId") String userId, Model model) {
        LOG.info("UserId: {}", userId);
        model.addAttribute("userId", userId);
        return "example/editBootstrap";
    }


    /**
     * Wirft eine Exception.
     * @return wirft immer eine Exception.
     */
    @RequestMapping("/showException")
    public String showException() {
        throw new RuntimeException("Die angeforderte Exception.");
    }

    /**
     * Show the login-view.
     *
     * @param username optional the username
     * @param password optional the password
     * @param model the Spring model
     * @return the login-view-name
     */
    //J-
    @RequestMapping(value = URL.Security.LOGIN, method = { RequestMethod.GET,
            RequestMethod.POST })
    public String loginPage(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login";
    }

    /**
     * Seite bei mangelnden Rechten.
     * @return the unquthorized-view-name.
     */
    @RequestMapping(URL.Security.UNAUTHORIZED)
    public String unauthorizedPage() {
        return "unauthorized";
    }

}
