// BewertungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.dao.api.zeugnis.AvSvBewertungDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AvSvBewertung;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.AvSvBewertungService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AvSvBewertungService}.
 */
@Transactional(readOnly = true)
@Service
public class AvSvBewertungServiceImpl implements AvSvBewertungService {


    /** Das Dao für {@link AvSvBewertung}. */
    @Resource
    private AvSvBewertungDao avSvBewertungDao;

    /** Das Dao für {@link Schueler}. */
    @Resource
    private SchuelerDao schuelerDao;

    /** Das Dao für {@link Schulhalbjahr}. */
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;

    /** Das Dao für {@link Klasse}. */
    @Resource
    private KlasseDao klasseDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<AvSvBewertung> getBewertung(Pageable page) {
        return avSvBewertungDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public AvSvBewertung save(AvSvBewertung avSvBewertung) {
        return avSvBewertungDao.save(avSvBewertung);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Iterable<AvSvBewertung> save(Iterable<AvSvBewertung> avSvBewertungen) {
        return avSvBewertungDao.save(avSvBewertungen);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AvSvBewertung read(Long avSvBewertungId) {
        return avSvBewertungDao.findOne(avSvBewertungId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long avSvBewertungId) {
        final AvSvBewertung oldBewertung = avSvBewertungDao.findOne(avSvBewertungId);
        avSvBewertungDao.delete(oldBewertung);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Klasse getKlasse(Long klassenId) {
        return klasseDao.findOne(klassenId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schulhalbjahr getSchulhalbjahr(Long schulhalbjahrId) {
        return schulhalbjahrDao.findOne(schulhalbjahrId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schueler getSchueler(Long schuelerId) {
        return schuelerDao.findOne(schuelerId);
    }


}
