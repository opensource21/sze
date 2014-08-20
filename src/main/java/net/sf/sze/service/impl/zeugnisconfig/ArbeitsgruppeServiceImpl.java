// ArbeitsgruppeServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnisconfig;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnisconfig.ArbeitsgruppeDao;
import net.sf.sze.model.zeugnisconfig.Arbeitsgruppe;
import net.sf.sze.service.api.zeugnisconfig.ArbeitsgruppeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ArbeitsgruppeService}.
 */
@Transactional(readOnly = true)
@Service
public class ArbeitsgruppeServiceImpl implements ArbeitsgruppeService {


    /** Das Dao für {@link Arbeitsgruppe}. */
    @Resource
    private ArbeitsgruppeDao arbeitsgruppeDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Arbeitsgruppe> getArbeitsgruppe(Pageable page) {
        return arbeitsgruppeDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Arbeitsgruppe save(Arbeitsgruppe arbeitsgruppe) {
        return arbeitsgruppeDao.save(arbeitsgruppe);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Arbeitsgruppe read(Long arbeitsgruppeId) {
        return arbeitsgruppeDao.findOne(arbeitsgruppeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long arbeitsgruppeId) {
        final Arbeitsgruppe oldArbeitsgruppe = arbeitsgruppeDao.findOne(arbeitsgruppeId);
        arbeitsgruppeDao.delete(oldArbeitsgruppe);
    }
}
