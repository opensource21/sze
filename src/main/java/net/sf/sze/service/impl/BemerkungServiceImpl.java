// BemerkungServiceImpl.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import net.sf.sze.dao.api.zeugnis.BemerkungDao;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.service.api.BemerkungService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Implementation of {@link BemerkungService}.
 */
@Transactional(readOnly = true)
@Service
public class BemerkungServiceImpl implements BemerkungService {

    /**
     * The Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            BemerkungServiceImpl.class);

    /** The bemerkung-Dao. */
    @Resource
    private BemerkungDao bemerkungDao;

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
        // return bemerkungDao.findAll(pr);
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
        if (bemerkung.getId() != null) {
            final Bemerkung oldBemerkung = bemerkungDao.findOne(bemerkung
                    .getId());
//          TODO            try {
//                          // This is need because post is the owning site.
//                          oldBemerkung.setPostings(bemerkung.getPostings());
//                          // At the safe both are merged.
//                      } catch (LazyInitializationException lie) {
//                          LOG.debug("Try to safe a detached object, "
//                                  + "if there are no postings nothing is to do.");
//                      }
        }

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
        // This is need because post is the owning site.
        // TODO oldBemerkung.setPostings(null);
        bemerkungDao.delete(oldBemerkung);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNrOfBemerkungs() {
        return bemerkungDao.count();
    }
}
