// ZeugnisFormularServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.SchulfachDetailInfoDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisFormularDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.SchulfachDetailInfo;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Halbjahr;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.service.api.common.SchulkalenderService;
import net.sf.sze.service.api.stammdaten.KlasseService;
import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(ZeugnisErfassungsServiceImpl.class);

    /** Das Dao für {@link ZeugnisFormular}. */
    @Resource
    private ZeugnisFormularDao zeugnisFormularDao;

    /** Das Dao für {@link SchulfachDetailInfo}. */
    @Resource
    private SchulfachDetailInfoDao schulfachDetailInfoDao;

    /** Das Dao für {@link Klasse}. */
    @Resource
    private KlasseDao klasseDao;

    /** Der Service für {@link Klasse}.*/
    @Resource
    private KlasseService klasseService;

    /** Das Dao für {@link Schulhalbjahr}.*/
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;

    /**
     * Service zum ermitteln des Schuljahres.
     */
    @Resource
    private SchulkalenderService schulkalenderService;


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
        final List<Klasse> klassen = klasseService.getActiveKlassen(currentJahr);
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

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void init(Calendar referenceDay) {
        final Halbjahr hj = schulkalenderService.getHalbjahr(referenceDay);
        final int schuljahr = schulkalenderService.getSchuljahr(referenceDay);
        final Schulhalbjahr shj = schulhalbjahrDao.findByJahrAndHalbjahr(schuljahr, hj);
        if (shj == null) {
            LOG.warn("Zum Schuljahr {} und Halbjahr {} kann "
                    + "kein Schulhalbjahr gefunden werden.", Integer.valueOf(schuljahr), hj);
            return;
        }
        final List<Klasse> klassen = klasseService.getActiveKlassen(schuljahr);
        for (Klasse klasse : klassen) {
            if (zeugnisFormularDao.
                    findBySchulhalbjahrJahrAndSchulhalbjahrHalbjahrAndKlasse(
                    schuljahr, hj, klasse) == null) {
                createNewFormular(shj, klasse);
            }
        }
    }

    /**
     * Erstellt ein neuees Zeugnisformular für das Schulhalbjahr und die Klasse.
     * @param shj das Schulhalbjahr
     * @param klasse die Klasse.
     */
    private void createNewFormular(final Schulhalbjahr shj, Klasse klasse) {
        final Halbjahr hj = shj.getHalbjahr();
        final int schuljahr = shj.getJahr();
        final ZeugnisFormular formular = new ZeugnisFormular();
        formular.setKlasse(klasse);
        formular.setSchulhalbjahr(shj);
        formular.setBeschreibung(shj.createRelativePathName() + "/Kl-"
                + klasse.calculateKlassenname(schuljahr));
        formular.setTemplateFileName("UNKNOWN");
        ZeugnisFormular lastZeugnisFormular = null;
        if (Halbjahr.Erstes_Halbjahr.equals(hj)) {
            final Klasse klassenStufe = klasseDao.
                    findFirstByJahrgang(klasse.getJahrgang() - 1);
            lastZeugnisFormular = zeugnisFormularDao.
                    findBySchulhalbjahrJahrAndSchulhalbjahrHalbjahrAndKlasse(
                    schuljahr - 1, Halbjahr.Beide_Halbjahre, klassenStufe);
            if (lastZeugnisFormular != null) {
                formular.setTemplateFileName(lastZeugnisFormular.getTemplateFileName());
            }
        } else if (Halbjahr.Beide_Halbjahre.equals(hj)) {
            lastZeugnisFormular = zeugnisFormularDao.
                    findBySchulhalbjahrJahrAndSchulhalbjahrHalbjahrAndKlasse(
                    schuljahr, Halbjahr.Erstes_Halbjahr, klasse);
            if (lastZeugnisFormular != null) {
                formular.setLeitspruch(lastZeugnisFormular.getLeitspruch());
                formular.setLeitspruch2(lastZeugnisFormular.getLeitspruch2());
                formular.setQuelleLeitspruch(lastZeugnisFormular.getQuelleLeitspruch());
                formular.setQuelleLeitspruch2(lastZeugnisFormular.getQuelleLeitspruch2());
                formular.setTemplateFileName(lastZeugnisFormular.getTemplateFileName());
            }
        } else {
            throw new IllegalStateException("Unültiges hj " + hj);
        }
        zeugnisFormularDao.save(formular);
        copySchulfachDetailInfo(lastZeugnisFormular, formular);
    }

    /**
     * Kopiert die {@link SchulfachDetailInfo}s.
     * @param lastZeugnisFormular das alte Formular.
     * @param formular das neue Formular.
     */
    private void copySchulfachDetailInfo(ZeugnisFormular lastZeugnisFormular,
            final ZeugnisFormular formular) {
        if (lastZeugnisFormular != null) {
            for (SchulfachDetailInfo detailInfo: lastZeugnisFormular.
                    getSchulfachDetailInfos()) {
                final SchulfachDetailInfo newDetailInfo = new SchulfachDetailInfo();
                newDetailInfo.setDetailInfo(detailInfo.getDetailInfo());
                newDetailInfo.setFormular(formular);
                newDetailInfo.setSchulfach(detailInfo.getSchulfach());
                schulfachDetailInfoDao.save(newDetailInfo);
            }
        } else {
            LOG.warn("Es konnte keine letztes Zeugnisformular ermittelt werden");
        }
    }
}
