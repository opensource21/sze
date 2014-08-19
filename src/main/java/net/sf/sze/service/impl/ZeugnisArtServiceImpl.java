// ZeugnisArtServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.ZeugnisArtDao;
import net.sf.sze.model.zeugnis.ZeugnisArt;
import net.sf.sze.service.api.ZeugnisArtService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ZeugnisArtService}.
 */
@Transactional(readOnly = true)
@Service
public class ZeugnisArtServiceImpl implements ZeugnisArtService {


    /** Das Dao für {@link ZeugnisArt}. */
    @Resource
    private ZeugnisArtDao zeugnisArtDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ZeugnisArt> getZeugnisArt(Pageable page) {
        return zeugnisArtDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public ZeugnisArt save(ZeugnisArt zeugnisArt) {
        return zeugnisArtDao.save(zeugnisArt);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZeugnisArt read(Long zeugnisArtId) {
        return zeugnisArtDao.findOne(zeugnisArtId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long zeugnisArtId) {
        final ZeugnisArt oldZeugnisArt = zeugnisArtDao.
                findOne(zeugnisArtId);
        zeugnisArtDao.delete(oldZeugnisArt);
    }
}
