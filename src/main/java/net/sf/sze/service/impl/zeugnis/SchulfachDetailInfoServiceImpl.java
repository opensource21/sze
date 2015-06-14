// SchulfachDetailInfoServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.SchulfachDetailInfoDao;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.service.api.zeugnis.SchulfachDetailInfoService;

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

    /** Das Dao für {@link SchulfachDetailInfo}. */
    @Resource
    private SchulfachDetailInfoDao schulfachDetailInfoDao;

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
