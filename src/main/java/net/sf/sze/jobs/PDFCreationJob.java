//PDFCreationJobs.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.jobs;

import javax.annotation.Resource;

import net.sf.sze.service.api.document.ZeugnisCreatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Job zum regelmäßigen Erstellen aller Zeugnisse.
 *
 */
@Component
public class PDFCreationJob {


    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(PDFCreationJob.class);

    /**
     * Service der die eigentliche Erstellung übernimmt.
     */
    @Resource
    private ZeugnisCreatorService zeugnisCreatorService;


    /**
     * Erzeugt alle PDFs.
     */
    //Seconds, Minutes, Hours, Day-of-Month, Month,Day-of-Week
    @Scheduled(cron = "${cron.pdfCreation}")
    public void createAllPDFs() {
        LOG.info("Starte pdfCreation Job");
        zeugnisCreatorService.createAllZeugnisse();
        LOG.info("Beende pdfCreation Job");
    }

}
