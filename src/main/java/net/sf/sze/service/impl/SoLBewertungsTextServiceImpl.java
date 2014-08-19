// SoLBewertungsTextServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.SoLBewertungsTextDao;
import net.sf.sze.model.zeugnisconfig.SoLBewertungsText;
import net.sf.sze.service.api.SoLBewertungsTextService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SoLBewertungsTextService}.
 */
@Transactional(readOnly = true)
@Service
public class SoLBewertungsTextServiceImpl implements SoLBewertungsTextService {


    /** Das Dao für {@link SoLBewertungsText}. */
    @Resource
    private SoLBewertungsTextDao solBewertungsTextDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SoLBewertungsText> getSoLBewertungsText(Pageable page) {
        return solBewertungsTextDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public SoLBewertungsText save(SoLBewertungsText solBewertungsText) {
        return solBewertungsTextDao.save(solBewertungsText);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoLBewertungsText read(Long solBewertungsTextId) {
        return solBewertungsTextDao.findOne(solBewertungsTextId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long solBewertungsTextId) {
        final SoLBewertungsText oldSoLBewertungsText = solBewertungsTextDao.
                findOne(solBewertungsTextId);
        solBewertungsTextDao.delete(oldSoLBewertungsText);
    }
}
