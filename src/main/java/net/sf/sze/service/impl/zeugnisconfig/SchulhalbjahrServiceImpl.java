// SchulhalbjahrServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnisconfig;

import java.util.Calendar;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.calendar.Halbjahr;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.calendar.SchulkalenderService;
import net.sf.sze.service.api.zeugnisconfig.SchulhalbjahrService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulhalbjahrService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulhalbjahrServiceImpl implements SchulhalbjahrService {

    private static final Logger LOG = LoggerFactory.getLogger(SchulhalbjahrServiceImpl.class);

    /** Das Dao für {@link Schulhalbjahr}. */
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;

    @Resource
    private SchulkalenderService schulkalenderService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Schulhalbjahr> getSchulhalbjahr(Pageable page) {
        return schulhalbjahrDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Schulhalbjahr save(Schulhalbjahr schulhalbjahr) {
        return schulhalbjahrDao.save(schulhalbjahr);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schulhalbjahr read(Long schulhalbjahrId) {
        return schulhalbjahrDao.findOne(schulhalbjahrId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulhalbjahrId) {
        final Schulhalbjahr oldSchulhalbjahr =
                schulhalbjahrDao.findOne(schulhalbjahrId);
        schulhalbjahrDao.delete(oldSchulhalbjahr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void init(Calendar currentDate) {
        final Halbjahr hj = schulkalenderService.getHalbjahr(currentDate);
        final int schuljahr = schulkalenderService.getSchuljahr(currentDate);
        if (schulhalbjahrDao.findByJahrAndHalbjahr(schuljahr, hj) == null) {
            final Schulhalbjahr shj = new Schulhalbjahr();
            shj.setHalbjahr(hj);
            shj.setJahr(schuljahr);
            shj.setSelectable(false);
            if (hj == Halbjahr.Beide_Halbjahre) {
                final Schulhalbjahr lastShj =
                        schulhalbjahrDao.findByJahrAndHalbjahr(schuljahr,
                                Halbjahr.Erstes_Halbjahr);
                if (lastShj == null) {
                    LOG.error("F\u00fcr das Schuljahr {} und Halbjahr {} existiert "
                            + "kein Schulhalbjahr.", Integer.valueOf(schuljahr),
                            Halbjahr.Erstes_Halbjahr);

                } else {
                    shj.setNachteilsAusgleichsDatum(lastShj
                            .getNachteilsAusgleichsDatum());
                }
            }
            schulhalbjahrDao.save(shj);
        } else {
            LOG.info("F\u00fcr das Schuljahr {} und Halbjahr {} existiert "
                    + "schon ein Schulhalbjahr.", Integer.valueOf(schuljahr), hj);
        }
    }
}
