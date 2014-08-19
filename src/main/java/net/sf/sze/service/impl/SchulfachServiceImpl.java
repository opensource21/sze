// SchulfachServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.SchulfachDao;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.service.api.SchulfachService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulfachService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulfachServiceImpl implements SchulfachService {


    /** Das Dao für {@link Schulfach}. */
    @Resource
    private SchulfachDao schulfachDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Schulfach> getSchulfach(Pageable page) {
        return schulfachDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Schulfach save(Schulfach schulfach) {
        return schulfachDao.save(schulfach);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schulfach read(Long schulfachId) {
        return schulfachDao.findOne(schulfachId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulfachId) {
        final Schulfach oldSchulfach = schulfachDao.findOne(schulfachId);
        schulfachDao.delete(oldSchulfach);
    }
}
