// ArbeitsUndSozialVerhaltenServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.ArbeitsUndSozialVerhaltenDao;
import net.sf.sze.model.zeugnis.ArbeitsUndSozialVerhalten;
import net.sf.sze.service.api.ArbeitsUndSozialVerhaltenService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ArbeitsUndSozialVerhaltenService}.
 */
@Transactional(readOnly = true)
@Service
public class ArbeitsUndSozialVerhaltenServiceImpl implements ArbeitsUndSozialVerhaltenService {


    /** Das Dao für {@link ArbeitsUndSozialVerhalten}. */
    @Resource
    private ArbeitsUndSozialVerhaltenDao arbeitsUndSozialVerhaltenDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ArbeitsUndSozialVerhalten> getArbeitsUndSozialVerhalten(Pageable page) {
        return arbeitsUndSozialVerhaltenDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public ArbeitsUndSozialVerhalten save(ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten) {
        return arbeitsUndSozialVerhaltenDao.save(arbeitsUndSozialVerhalten);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArbeitsUndSozialVerhalten read(Long arbeitsUndSozialVerhaltenId) {
        return arbeitsUndSozialVerhaltenDao.findOne(arbeitsUndSozialVerhaltenId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long arbeitsUndSozialVerhaltenId) {
        final ArbeitsUndSozialVerhalten oldArbeitsUndSozialVerhalten =
                arbeitsUndSozialVerhaltenDao.findOne(arbeitsUndSozialVerhaltenId);
        arbeitsUndSozialVerhaltenDao.delete(oldArbeitsUndSozialVerhalten);
    }
}
