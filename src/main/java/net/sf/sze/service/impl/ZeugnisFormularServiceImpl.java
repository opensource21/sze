// ZeugnisFormularServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisFormularService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ZeugnisFormularService}.
 */
@Transactional(readOnly = true)
@Service
public class ZeugnisFormularServiceImpl implements ZeugnisFormularService {


    /** Das Dao für {@link ZeugnisFormular}. */
    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ZeugnisFormular> getZeugnisFormular(Pageable page) {
        return zeugnisFormularDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public ZeugnisFormular save(ZeugnisFormular zeugnisFormular) {
        return zeugnisFormularDao.save(zeugnisFormular);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZeugnisFormular read(Long zeugnisFormularId) {
        return zeugnisFormularDao.findOne(zeugnisFormularId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long zeugnisFormularId) {
        final ZeugnisFormular oldZeugnisFormular = zeugnisFormularDao.findOne(zeugnisFormularId);
        zeugnisFormularDao.delete(oldZeugnisFormular);
    }
}
