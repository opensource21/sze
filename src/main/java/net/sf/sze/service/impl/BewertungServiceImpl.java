// BewertungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.service.api.BewertungService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link BewertungService}.
 */
@Transactional(readOnly = true)
@Service
public class BewertungServiceImpl implements BewertungService {


    /** Das Dao für {@link Bewertung}. */
    @Resource
    private BewertungDao bewertungDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Bewertung> getBewertung(Pageable page) {
        return bewertungDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Bewertung save(Bewertung bewertung) {
        return bewertungDao.save(bewertung);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bewertung read(Long bewertungId) {
        return bewertungDao.findOne(bewertungId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long bewertungId) {
        final Bewertung oldBewertung = bewertungDao.findOne(bewertungId);
        bewertungDao.delete(oldBewertung);
    }
}
