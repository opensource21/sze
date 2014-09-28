// SchulfachDetailInfoServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.SchulfachDetailInfoDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.zeugnis.SchulfachDetailInfoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulfachDetailInfoService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulfachDetailInfoServiceImpl implements SchulfachDetailInfoService {

    /** Minimales Schuljahr. */
    @Value("${schuljahre.min}")
    private int minimalesSchuljahr;

    /** Maximales Schuljahr. */
    @Value("${schuljahre.max}")
    private int maximalesSchuljahr;

    /** Das Dao für {@link SchulfachDetailInfo}. */
    @Resource
    private SchulfachDetailInfoDao schulfachDetailInfoDao;

    /** Das Dao für {@link Klasse}.*/
    @Resource
    private KlasseDao klasseDao;

    /** Das Dao für {@link Schulhalbjahr}.*/
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SchulfachDetailInfo> getSchulfachDetailInfo(Pageable page) {
        return schulfachDetailInfoDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public SchulfachDetailInfo save(SchulfachDetailInfo schulfachDetailInfo) {
        return schulfachDetailInfoDao.save(schulfachDetailInfo);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchulfachDetailInfo read(Long schulfachDetailInfoId) {
        return schulfachDetailInfoDao.findOne(schulfachDetailInfoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulfachDetailInfoId) {
        final SchulfachDetailInfo oldSchulfachDetailInfo =
                schulfachDetailInfoDao.findOne(schulfachDetailInfoId);
        schulfachDetailInfoDao.delete(oldSchulfachDetailInfo);
    }

}
