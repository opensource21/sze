// SchulamtServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.SchulamtDao;
import net.sf.sze.model.zeugnisconfig.Schulamt;
import net.sf.sze.service.api.SchulamtService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulamtService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulamtServiceImpl implements SchulamtService {


    /** Das Dao für {@link Schulamt}. */
    @Resource
    private SchulamtDao schulamtDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Schulamt> getSchulamt(Pageable page) {
        return schulamtDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Schulamt save(Schulamt schulamt) {
        return schulamtDao.save(schulamt);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schulamt read(Long schulamtId) {
        return schulamtDao.findOne(schulamtId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulamtId) {
        final Schulamt oldSchulamt = schulamtDao.findOne(schulamtId);
        schulamtDao.delete(oldSchulamt);
    }
}
