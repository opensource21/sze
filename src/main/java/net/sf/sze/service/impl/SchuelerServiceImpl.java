// SchuelerServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.service.api.SchuelerService;
import net.sf.sze.service.api.SchulkalenderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchuelerService}.
 */
@Transactional(readOnly = true)
@Service
public class SchuelerServiceImpl implements SchuelerService {


    /** Das Dao für {@link Schueler}. */
    @Resource
    private SchuelerDao schuelerDao;

    /**
     * The {@link SchulkalenderService}.
     */
    @Resource
    private SchulkalenderService schulkalenderService;


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Schueler> getSchueler(Pageable page, boolean onlyActiveSchueler) {
        final Date leavedSchoolDate = schulkalenderService.getLeavedSchoolDate().getTime();
        if (onlyActiveSchueler) {
            return schuelerDao.findAllByAbgangsDatumIsNullOrFuture(
                    leavedSchoolDate, page);
        } else {
            return schuelerDao.findAllByAbgangsDatumIsHistory(leavedSchoolDate, page);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schueler> getActiveSchueler(Klasse klasse) {
        return schuelerDao.findAllByAbgangsDatumIsNullOrFutureAndKlasse(
                schulkalenderService.getLeavedSchoolDate().getTime(), klasse);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Schueler save(Schueler schueler) {
        return schuelerDao.save(schueler);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schueler read(Long schuelerId) {
        return schuelerDao.findOne(schuelerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schuelerId) {
        final Schueler oldSchueler = schuelerDao.findOne(schuelerId);
        schuelerDao.delete(oldSchueler);
    }


}
