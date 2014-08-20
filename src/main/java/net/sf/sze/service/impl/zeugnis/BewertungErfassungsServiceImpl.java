//BewertungErfassungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.zeugnis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulfachDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.zeugnis.BewertungErfassungsService;
import net.sf.sze.service.api.zeugnis.BewertungWithNeigbors;

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
     * Dao für eine Schul-{@link  Klasse}.
     */
    @Resource
    private KlasseDao klasseDao;

    /**
     * Dao für eine {@link  Zeugnis}.
     */
    @Resource
    private ZeugnisDao zeugnisDao;


    /**
     * Dao für eine {@link  Schulfach}.
     */
    @Resource
    private SchulfachDao schulfachDao;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("boxing")
    @Override
    public List<Bewertung> getSortedBewertungen(long halbjahrId, long klassenId,
            long schulfachId) {
        LOG.debug("Suche Bewertungn für {}, {} und {}", halbjahrId, klassenId, schulfachId);
        final List<Zeugnis>  zeugnisse = zeugnisDao.
                findAllByKlasseIdAndSchulhalbjahrIdAndSchulhalbjahrSelectableIsTrueOrderBySchuelerNameAscSchuelerVornameAsc(
                klassenId, halbjahrId);
        //PERFORMANCE: 2 SQLs und eine handisches Suchen ist sicherlich nicht die schnellste Lösung.
        //Der Ansatz mit List<Bewertung>
        //findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndSchulfachIdOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(
        // long klasseId, long halbjahrId, long schulfachId); Scheitert. Es
        // gab eine Fehlermeldung, dass er kein Element zu einer ZeugnisID
        // findet. Das klingt nach einem Fehler in dem darunter liegenden Framework.
        final List<Bewertung> bewertungen = bewertungDao.
                findAllBySchulfachIdAndZeugnisIn(schulfachId, zeugnisse);
        Collections.sort(bewertungen);
        return bewertungen;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public BewertungWithNeigbors getBewertungWithNeighbors(Long halbjahrId,
            Long klassenId, Long schulfachId, Long bewertungsId) {
        final List<Bewertung> bewertungen = getSortedBewertungen(halbjahrId.longValue(),
                klassenId.longValue(), schulfachId.longValue());
        return new BewertungWithNeigbors(bewertungen, bewertungsId);
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schulfach>
            getActiveSchulfaecherOrderByName(Schulhalbjahr hj, Klasse klasse) {
        final String klassenStufe = String.valueOf(klasse.calculateKlassenstufe(hj.getJahr()));
        final List<Schulfach> alleSchulfaecher = schulfachDao.findAll();
        final List<Schulfach> relevanteSchulfaecher = new ArrayList<>();
        for (Schulfach schulfach : alleSchulfaecher) {
            if (schulfach.convertStufenMitAussenDifferenzierungToList()
                    .contains(klassenStufe)) {
                relevanteSchulfaecher.add(schulfach);
            } else if (schulfach.convertStufenMitBinnenDifferenzierungToList()
                    .contains(klassenStufe)) {
                relevanteSchulfaecher.add(schulfach);
            } else if (schulfach.convertStufenMitStandardBewertungToList()
                    .contains(klassenStufe)) {
                relevanteSchulfaecher.add(schulfach);
            }
        }
        //PERFORMANCE Besser bei der DB - Abfrage sortieren.
        Collections.sort(relevanteSchulfaecher, new Comparator<Schulfach>() {
            @Override
            public int compare(Schulfach o1, Schulfach o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return relevanteSchulfaecher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Klasse getKlasse(long klassenId) {
        return klasseDao.findOne(Long.valueOf(klassenId));
    }


}
