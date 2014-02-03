//AnonymisierungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnis.SchulamtsBemerkung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.AnonymisierungsService;
import net.sf.sze.service.api.BemerkungService;
import net.sf.sze.service.api.SchulamtsBemerkungService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Anoymisierungsservice.
 *
 */
@Transactional(readOnly = false)
@Service
public class AnonymisierungsServiceImpl implements AnonymisierungsService {

    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    @Resource
    private BemerkungService bemerkungService;

    @Resource
    private SchulamtsBemerkungService schulamtsBemerkungService;

    @Resource
    private SchuelerDao schuelerDao;


    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceAllNamesWithVariables() {
        //Das simple speichern reicht, da die eigentliche Arbeit inzwischen
        //im Service steckt.
        for (Zeugnis zeugnis : zeugnisErfassungsService.getAllZeugnisse()) {
            zeugnisErfassungsService.save(zeugnis);
            for (Bemerkung bemerkung : zeugnis.getBemerkungen()) {
                bemerkungService.save(bemerkung);
            }
            for (SchulamtsBemerkung bemerkung : zeugnis.getSchulamtsBemerkungen()) {
                schulamtsBemerkungService.save(bemerkung);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void anonymisierSchueler() {
        final Iterable<Schueler> allSchueler = schuelerDao.findAll();
        //TODO 30 Jungs und Mädchennamen besorgen und Nachnamen.
        for (Schueler schueler : allSchueler) {
            if (Geschlecht.WEIBLICH.equals(schueler.getGeschlecht())) {
                schueler.setVorname("Maria" + schueler.getId());
            } else {
                schueler.setVorname("Peter" + schueler.getId());
            }
            schueler.setName("Mueller " + schueler.getId());
            schueler.setRufname(null);
            if (schueler.getAufnahmeDatum() != null) {
                schueler.setAufnahmeDatum(DateUtils.addDays(
                        schueler.getAufnahmeDatum(), RandomUtils.nextInt(360) - 180));
            }
            schueler.setGeburtstag(DateUtils.addDays(schueler.getGeburtstag(),
                    RandomUtils.nextInt(360) - 180));
            schueler.setGeburtsort("Erde " + schueler.getId());
            schuelerDao.save(schueler);
        }

    }

}
