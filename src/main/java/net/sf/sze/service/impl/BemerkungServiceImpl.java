// BemerkungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.BemerkungDao;
import net.sf.sze.dao.api.zeugnis.BemerkungsBausteinDao;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnis.BemerkungsBaustein;
import net.sf.sze.service.api.BemerkungService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link BemerkungService}.
 */
@Transactional(readOnly = true)
@Service
public class BemerkungServiceImpl implements BemerkungService {


    /** The bemerkung-Dao. */
    @Resource
    private BemerkungDao bemerkungDao;

    /** The {@link BemerkungsBausteinDao}. */
    @Resource
    private BemerkungsBausteinDao bemerkungsBausteinDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Bemerkung> getAllBemerkung() {
        return bemerkungDao.findAll((PageRequest) null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Bemerkung> getBemerkung(int skip, int count, Order... order) {
        final Sort sort = (order.length > 0) ? new Sort(order) : null;
        final PageRequest pr = new PageRequest(skip / count, count, sort);
        return this.getBemerkung(pr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Bemerkung> getBemerkung(Pageable page) {
        return bemerkungDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Bemerkung save(Bemerkung bemerkung) {
        return bemerkungDao.save(bemerkung);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bemerkung read(Long bemerkungId) {
        return bemerkungDao.findOne(bemerkungId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long bemerkungId) {
        final Bemerkung oldBemerkung = bemerkungDao.findOne(bemerkungId);
        bemerkungDao.delete(oldBemerkung);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNrOfBemerkungs() {
        return bemerkungDao.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BemerkungsBaustein> getAllBausteine(Bemerkung bemerkung) {
        final List<BemerkungsBaustein> bausteine = bemerkungsBausteinDao.
                findAllByAktivTrueOrderByNameAsc();
        if (bemerkung != null && bemerkung.getBaustein() != null ) {
            if (!bausteine.contains(bemerkung.getBaustein())) {
                bausteine.add(bemerkung.getBaustein());
            }
        }
        return bausteine;
    }
}
