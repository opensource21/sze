//InitJobs.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.jobs;

import java.util.Calendar;

import javax.annotation.Resource;

import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;
import net.sf.sze.service.api.zeugnisconfig.SchulhalbjahrService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Job zum regelmäßigen Initialisieren der Zeugnisformulare.
 *
 */
@Service
public class InitJobs {


    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(InitJobs.class);

    /**
     * Service der die Erstellung der Halbjahre übernimmt.
     */
    @Resource
    private SchulhalbjahrService schulhalbjahrService;

    /**
     * Service der die Erstellung der Formulare übernimmt.
     */
    @Resource
    private ZeugnisFormularService zeugnisFormularService;

    /**
     * Erzeugt alle PDFs.
     */
    //Seconds, Minutes, Hours, Day-of-Month, Month,Day-of-Week
    @Scheduled(cron = "${cron.halbjahresInit}")
    public void initHalbjahreAndFormulare() {
        Calendar today = Calendar.getInstance();
        LOG.info("Starte Schulhalbjahr Erstellung");
        schulhalbjahrService.init(today);
        LOG.info("Beende Schulhalbjahr Erstellung");
        LOG.info("Starte Formular Erstellung");
        zeugnisFormularService.init(today);
        LOG.info("Beende Formular Erstellung");
    }

}
