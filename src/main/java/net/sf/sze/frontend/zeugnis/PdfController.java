// ZeugnisController.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.zeugnis;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.sze.frontend.base.ModelAttributes;
import net.sf.sze.frontend.base.URL;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.converter.ZeugnisCreatorService;
import net.sf.sze.service.api.zeugnis.ZeugnisErfassungsService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import de.ppi.fuwesta.spring.mvc.view.FileContentView;

/**
 * Controlller für die PDF-Erstellung.
 *
 */
@Controller
public class PdfController implements ModelAttributes {


    /**
     * Modell-Attribut, ob es sich um einen Remote-Call handelt.
     */
    private static final String MODELL_ATTRIBUTE_IS_REMOTE_CALL = "isRemoteCall";

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            PdfController.class);

    /**
     * Der {@link ZeugnisErfassungsService}.
     */
    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;


    @Resource
    private ZeugnisCreatorService zeugnisCreatorService;

    /**
     * Entscheidung ob es sich um einen Remote-Call handelt oder nicht.
     * @param request der Request.
     * @return true - wenn es sich um einen Remotecall handelt.
     */
    @ModelAttribute(MODELL_ATTRIBUTE_IS_REMOTE_CALL)
    public boolean isRemoteCall(HttpServletRequest request) {
        return request.isSecure();
    }


    /**
     * Erstellt das PDF für einen Schüler.
     * @param halbjahrId die Id des Schulhalbjahres
     * @param klassenId die Id der Klasse
     * @param schuelerId die Id des Schuelers
     * @param isRemoteCall true wenn die Anfrage remote erfolgt.
     * @param redirectAttributes die Meldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.ZeugnisPath.ONE_PDF, method = RequestMethod.GET)
    public View createPDF(@PathVariable(URL.Session
            .P_HALBJAHR_ID) Long halbjahrId,
            @PathVariable(URL.Session.P_KLASSEN_ID) Long klassenId,
            @PathVariable(URL.Session.P_SCHUELER_ID) Long schuelerId,
            @ModelAttribute(MODELL_ATTRIBUTE_IS_REMOTE_CALL) boolean isRemoteCall,
            RedirectAttributes redirectAttributes) {
        final File zeugnisDatei = zeugnisCreatorService.createZeugnis(
                zeugnisErfassungsService.getZeugnis(halbjahrId, schuelerId));
        if (zeugnisDatei.exists() && zeugnisDatei.canRead()) {
            final Subject currentUser = SecurityUtils.getSubject();
            if (!isRemoteCall || currentUser.isPermitted("print:remoteAll")) {
                return new FileContentView(zeugnisDatei);
            } else {
                redirectAttributes.addFlashAttribute(MESSAGE, "Zeugniss wurde erstellt, "
                       + "aber Sie sind nicht berechtigt diese im Browser anzusehen.");
                LOG.info(zeugnisDatei.getAbsolutePath() + " wurde angelegt, aber "
                        + "mangels Berechtigung nicht an {} ausgeliefert.",
                        currentUser.getPrincipal());
            }
        } else {
            redirectAttributes.addFlashAttribute(MESSAGE, "Zeugnis erstellt, aber nicht lesbar.");
            LOG.warn("Kann " + zeugnisDatei.getAbsolutePath() + " nicht lesen. "
                    + "Exists: " + zeugnisDatei.exists() + ", canRead: "
                    + zeugnisDatei.canRead());
        }
        return new RedirectView(URL.createLinkToZeugnisUrl(halbjahrId, klassenId,
                schuelerId), true);
    }

    /**
     * Erstellt das PDF für eine Klasse.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse
     * @param isRemoteCall true wenn die Anfrage remote erfolgt.
     * @param redirectAttributes die Meldungen.
     * @return die logische View
     */
    @RequestMapping(value = URL.Zeugnis.ALL_PDFS, method = RequestMethod.GET)
    public View createAllPDFS(@RequestParam(URL.Session
            .P_HALBJAHR_ID) Schulhalbjahr halbjahr,
            @RequestParam(URL.Session.P_KLASSEN_ID) Klasse klasse,
            @ModelAttribute(MODELL_ATTRIBUTE_IS_REMOTE_CALL) boolean isRemoteCall,
            RedirectAttributes redirectAttributes) {
        final File zeugnisDatei = zeugnisCreatorService.createZeugnisse(halbjahr, klasse);
        if (zeugnisDatei == null) {
            redirectAttributes.addFlashAttribute(MESSAGE, "Es sind keine Zeugnisse vorhanden.");
        } else if (zeugnisDatei.exists() && zeugnisDatei.canRead()) {
            final Subject currentUser = SecurityUtils.getSubject();
            if (!isRemoteCall || currentUser.isPermitted("print:remoteAll")) {
                return new FileContentView(zeugnisDatei, "Klasse_"
                        + klasse.calculateKlassenname() + ".pdf");
            } else {
                redirectAttributes.addFlashAttribute(MESSAGE, "Zeugnisse wurden erstellt, "
                       + "aber Sie sind nicht berechtigt diese im Browser anzusehen.");
                LOG.info(zeugnisDatei.getAbsolutePath() + " wurde angelegt, aber "
                        + "mangels Berechtigung nicht an {} ausgeliefert.",
                        currentUser.getPrincipal());
            }
        } else {
            redirectAttributes.addFlashAttribute(MESSAGE, "Zeugnis erstellt, aber nicht lesbar.");
            LOG.warn("Kann " + zeugnisDatei.getAbsolutePath() + " nicht lesen. "
                    + "Exists: " + zeugnisDatei.exists() + ", canRead: "
                    + zeugnisDatei.canRead());
        }
        return new RedirectView(URL.filledURLWithNamedParams(
                URL.ZeugnisPath.START,
                URL.Session.P_HALBJAHR_ID, halbjahr.getId(),
                URL.Session.P_KLASSEN_ID, klasse.getId()), true);
    }
}
