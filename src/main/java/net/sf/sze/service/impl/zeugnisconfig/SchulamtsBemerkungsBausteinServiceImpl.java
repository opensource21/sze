// SchulamtsBemerkungsBausteinServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnisconfig;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnisconfig.SchulamtsBemerkungsBausteinDao;
import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;
import net.sf.sze.service.api.zeugnisconfig.SchulamtsBemerkungsBausteinService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulamtsBemerkungsBausteinService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulamtsBemerkungsBausteinServiceImpl implements SchulamtsBemerkungsBausteinService {


    /** Das Dao für {@link SchulamtsBemerkungsBaustein}. */
    @Resource
    private SchulamtsBemerkungsBausteinDao schulamtsBemerkungsBausteinDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SchulamtsBemerkungsBaustein> getSchulamtsBemerkungsBaustein(Pageable page) {
        return schulamtsBemerkungsBausteinDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public SchulamtsBemerkungsBaustein save(SchulamtsBemerkungsBaustein
            schulamtsBemerkungsBaustein) {
        return schulamtsBemerkungsBausteinDao.save(schulamtsBemerkungsBaustein);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchulamtsBemerkungsBaustein read(Long schulamtsBemerkungsBausteinId) {
        return schulamtsBemerkungsBausteinDao.findOne(schulamtsBemerkungsBausteinId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulamtsBemerkungsBausteinId) {
        final SchulamtsBemerkungsBaustein oldSchulamtsBemerkungsBaustein =
                schulamtsBemerkungsBausteinDao.findOne(schulamtsBemerkungsBausteinId);
        schulamtsBemerkungsBausteinDao.delete(oldSchulamtsBemerkungsBaustein);
    }
}
