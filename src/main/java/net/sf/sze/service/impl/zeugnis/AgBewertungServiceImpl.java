// BewertungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.AgBewertungDao;
import net.sf.sze.model.zeugnis.AgBewertung;
import net.sf.sze.service.api.zeugnis.AgBewertungService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link AgBewertungService}.
 */
@Transactional(readOnly = true)
@Service
public class AgBewertungServiceImpl implements AgBewertungService {


    /** Das Dao für {@link AgBewertung}. */
    @Resource
    private AgBewertungDao agBewertungDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<AgBewertung> getBewertung(Pageable page) {
        return agBewertungDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public AgBewertung save(AgBewertung agBewertung) {
        return agBewertungDao.save(agBewertung);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Iterable<AgBewertung> save(Iterable<AgBewertung> agBewertungen) {
        return agBewertungDao.save(agBewertungen);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AgBewertung read(Long agBewertungId) {
        return agBewertungDao.findOne(agBewertungId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long agBewertungId) {
        final AgBewertung oldBewertung = agBewertungDao.findOne(agBewertungId);
        agBewertungDao.delete(oldBewertung);
    }


}
