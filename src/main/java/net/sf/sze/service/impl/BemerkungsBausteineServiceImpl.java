// BemerkungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.BemerkungsBausteinDao;
import net.sf.sze.model.zeugnis.BemerkungsBaustein;
import net.sf.sze.service.api.BemerkungsBausteineService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link BemerkungsBausteineService}.
 */
@Transactional(readOnly = true)
@Service
public class BemerkungsBausteineServiceImpl implements BemerkungsBausteineService {


    /** The {@link BemerkungsBausteinDao}. */
    @Resource
    private BemerkungsBausteinDao bemerkungsBausteinDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<BemerkungsBaustein> getAllBemerkungsBausteine() {
        return bemerkungsBausteinDao.findAll((PageRequest) null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<BemerkungsBaustein> getBemerkungsBausteine(int skip, int count, Order... order) {
        final Sort sort = (order.length > 0) ? new Sort(order) : null;
        final PageRequest pr = new PageRequest(skip / count, count, sort);
        return this.getBemerkungsBausteine(pr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<BemerkungsBaustein> getBemerkungsBausteine(Pageable page) {
        return bemerkungsBausteinDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public BemerkungsBaustein save(BemerkungsBaustein bemerkungsBaustein) {
        return bemerkungsBausteinDao.save(bemerkungsBaustein);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BemerkungsBaustein read(Long bemerkungBausteinId) {
        return bemerkungsBausteinDao.findOne(bemerkungBausteinId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long bemerkungId) {
        final BemerkungsBaustein oldBemerkung = bemerkungsBausteinDao.findOne(bemerkungId);
        bemerkungsBausteinDao.delete(oldBemerkung);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNrOfBemerkungsBausteine() {
        return bemerkungsBausteinDao.count();
    }
}
