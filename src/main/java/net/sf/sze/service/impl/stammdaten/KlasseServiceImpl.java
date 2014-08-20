// KlasseServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.stammdaten;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.service.api.stammdaten.KlasseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link KlasseService}.
 */
@Transactional(readOnly = true)
@Service
public class KlasseServiceImpl implements KlasseService {


    /** Das Dao für {@link Klasse}. */
    @Resource
    private KlasseDao klasseDao;



    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Klasse> getKlasse(Pageable page) {
        return klasseDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Klasse save(Klasse klasse) {
        return klasseDao.save(klasse);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Klasse read(Long klasseId) {
        return klasseDao.findOne(klasseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long klasseId) {
        final Klasse oldKlasse = klasseDao.findOne(klasseId);
        klasseDao.delete(oldKlasse);
    }
}
