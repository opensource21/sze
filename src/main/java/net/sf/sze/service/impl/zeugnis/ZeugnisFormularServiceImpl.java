// ZeugnisFormularServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link ZeugnisFormularService}.
 */
@Transactional(readOnly = true)
@Service
public class ZeugnisFormularServiceImpl implements ZeugnisFormularService {

    /** Minimales Schuljahr. */
    @Value("${schuljahre.min}")
    private int minimalesSchuljahr;

    /** Maximales Schuljahr. */
    @Value("${schuljahre.max}")
    private int maximalesSchuljahr;

    /** Das Dao für {@link ZeugnisFormular}. */
    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;

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
    public Page<ZeugnisFormular> getZeugnisFormular(Pageable page) {
        return zeugnisFormularDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public ZeugnisFormular save(ZeugnisFormular zeugnisFormular) {
        return zeugnisFormularDao.save(zeugnisFormular);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZeugnisFormular read(Long zeugnisFormularId) {
        return zeugnisFormularDao.findOne(zeugnisFormularId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long zeugnisFormularId) {
        final ZeugnisFormular oldZeugnisFormular = zeugnisFormularDao.findOne(zeugnisFormularId);
        zeugnisFormularDao.delete(oldZeugnisFormular);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Klasse> getActiveClasses(ZeugnisFormular zeugnisFormular) {
        final Schulhalbjahr schulhalbjahr = zeugnisFormular.getSchulhalbjahr();
        final int currentJahr;
        if (schulhalbjahr != null) {
            currentJahr = schulhalbjahr.getJahr();
        } else {
            currentJahr = Calendar.getInstance().get(Calendar.YEAR);
        }
        final List<Klasse> klassen = klasseDao
                .findAllByJahrgangBetweenAndGeschlossen(currentJahr
                - maximalesSchuljahr, currentJahr - minimalesSchuljahr, false);
        if (zeugnisFormular.getKlasse() != null && !klassen.contains(zeugnisFormular.getKlasse())) {
            klassen.add(0, zeugnisFormular.getKlasse());
        }
        return klassen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schulhalbjahr getNewestSchulhalbjahr() {
        int currentJahr = Calendar.getInstance().get(Calendar.YEAR);
        final List<Schulhalbjahr> schulhalbjahre = schulhalbjahrDao.
                findAllByJahrGreaterThanOrderByJahrDescHalbjahrDesc(currentJahr - 2);
        if (schulhalbjahre.size() > 0) {
            return schulhalbjahre.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schulhalbjahr> getActiveSchulhalbjahre(
            ZeugnisFormular zeugnisFormular) {
        final List<Schulhalbjahr> halbjahre = schulhalbjahrDao.
                findAllBySelectableOrderByJahrDescHalbjahrDesc(true);
        if (zeugnisFormular.getSchulhalbjahr() != null
                && !halbjahre.contains(zeugnisFormular.getSchulhalbjahr())) {
            halbjahre.add(0, zeugnisFormular.getSchulhalbjahr());
        }
        return halbjahre;
    }
}
