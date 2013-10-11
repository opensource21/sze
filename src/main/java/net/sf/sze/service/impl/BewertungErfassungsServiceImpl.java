//BewertungErfassungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.dao.api.zeugnis.SchulfachDao;
import net.sf.sze.dao.api.zeugnis.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Schulfach;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.service.api.BewertungErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementierung des {@link BewertungErfassungsService}.
 *
 */
@Transactional(readOnly = true)
@Service
public class BewertungErfassungsServiceImpl implements
        BewertungErfassungsService {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(BewertungErfassungsServiceImpl.class);

    /**
     * Bertungsdao.
     */
    @Resource
    private BewertungDao bewertungDao;

    /**
     * Dao fürs {@link Schulhalbjahr}.
     */
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;


    /**
     * Dao für eine Schul-{@link  Klasse}.
     */
    @Resource
    private KlasseDao klasseDao;


    /**
     * Dao für eine {@link  Schulfach}.
     */
    @Resource
    private SchulfachDao schulfachDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bewertung> getBewertungen(long halbjahrId, long klassenId,
            long schulfachId) {
        return bewertungDao.findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndZeugnisSchulhalbjahrSelectableIsTrueAndSchulfachIdOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(
                klassenId, halbjahrId, schulfachId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schulfach>
            getActiveSchulfaecher(long halbjahrId, long klassenId) {
        final Schulhalbjahr hj = schulhalbjahrDao.findOne(
                Long.valueOf(halbjahrId));
        final Klasse klasse = klasseDao.findOne(
                Long.valueOf(klassenId));
        final String klassenStufe = String.valueOf(klasse.calculateKlassenstufe(hj.getJahr()));
        final List<Schulfach> alleSchulfaecher = schulfachDao.findAll();
        final List<Schulfach> relevanteSchulfaecher = new ArrayList<>();
        for (Schulfach schulfach : alleSchulfaecher) {
            if (schulfach.convertStufenMitAussenDifferenzierungToList()
                    .contains(klassenStufe)) {
                relevanteSchulfaecher.add(schulfach);
            } else if (schulfach.convertStufenMitBinnenDifferenzierungToList()
                    .contains(klassenStufe)){
                relevanteSchulfaecher.add(schulfach);
            } else if (schulfach.convertStufenMitStandardBewertungToList()
                    .contains(klassenStufe)){
                relevanteSchulfaecher.add(schulfach);
            }
        }

        return relevanteSchulfaecher;
    }

}
