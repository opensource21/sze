// ZeugnisErfassungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.KlasseDao;
import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulhalbjahrDao;
import net.sf.sze.dao.api.zeugnisconfig.SoLBewertungsTextDao;
import net.sf.sze.dao.api.zeugnisconfig.ZeugnisArtDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnisconfig.Schulfachtyp;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;
import net.sf.sze.model.zeugnisconfig.SoLBewertungsText;
import net.sf.sze.model.zeugnisconfig.ZeugnisArt;
import net.sf.sze.service.api.zeugnis.BewertungWithNeigbors;
import net.sf.sze.service.api.zeugnis.ZeugnisErfassungsService;
import net.sf.sze.util.VariableUtility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Standardimplementierung vom {@link ZeugnisErfassungsService}.
 *
 */
@Service
@Transactional(readOnly = true)
public class ZeugnisErfassungsServiceImpl implements ZeugnisErfassungsService {

    /** Minimales Schuljahr. */
    @Value("${schuljahre.min}")
    private int minimalesSchuljahr;

    /** Maximales Schuljahr. */
    @Value("${schuljahre.max}")
    private int maximalesSchuljahr;

    /**
     * Das Schulhalbjahrs-DAO.
     */
    @Resource
    private SchulhalbjahrDao schulhalbjahrDao;

    /**
     * Das Klassen-DAO.
     */
    @Resource
    private KlasseDao klasseDao;

    /**
     * Das Zeugnis-DAO.
     */
    @Resource
    private ZeugnisDao zeugnisDao;

    /**
     * Das ZeugnisArt-DAO.
     */
    @Resource
    private ZeugnisArtDao zeugnisArtDao;

    /**
     * Das Bewertung-DAO.
     */
    @Resource
    private BewertungDao bewertungDao;

    /**
     * Das SolBertunge-DAO.
     */
    @Resource
    private SoLBewertungsTextDao soLBewertungsTextDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schulhalbjahr> getActiveSchulhalbjahre() {
        return schulhalbjahrDao.findAllBySelectableOrderByJahrDescHalbjahrDesc(
                true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Klasse> getActiveKlassen(
            List<Schulhalbjahr> acticeSchulhalbjahre) {
        int minJahr = Integer.MAX_VALUE;
        int maxJahr = 0;
        for (Schulhalbjahr schulhalbjahr : acticeSchulhalbjahre) {
            final int currentJahr = schulhalbjahr.getJahr();
            if (currentJahr < minJahr) {
                minJahr = currentJahr;
            }

            if (currentJahr > maxJahr) {
                maxJahr = currentJahr;
            }
        }

        final List<Klasse> klassen = klasseDao
                .findAllByJahrgangBetweenAndGeschlossen(minJahr
                - maximalesSchuljahr, maxJahr - minimalesSchuljahr, false);

        return klassen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ZeugnisArt> getAllZeugnisArten(Zeugnis zeugnis) {
        final List<ZeugnisArt> bausteine = zeugnisArtDao.
                findAllByAktivTrueOrderBySortierungAsc();
        final boolean gotZeugnisArt = zeugnis != null && zeugnis.getZeugnisArt() != null;
        if (gotZeugnisArt && !bausteine.contains(zeugnis.getZeugnisArt())) {
            bausteine.add(zeugnis.getZeugnisArt());
        }
        return bausteine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Zeugnis getZeugnis(Long halbjahrId, Long schuelerId) {
        return zeugnisDao.findByFormularSchulhalbjahrIdAndSchuelerId(halbjahrId,
                schuelerId);
    }


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void splitBewertungslist(List<Bewertung> bewertungen,
            final List<Bewertung> wpBewertungen,
            final List<Bewertung> otherBewertungen) {
        for (Bewertung bewertung : bewertungen) {
            if (Schulfachtyp.WAHLPFLICHT.equals(bewertung.getSchulfach()
                    .getTyp())) {
                wpBewertungen.add(bewertung);
            } else {
                otherBewertungen.add(bewertung);
            }
        }
        Collections.sort(wpBewertungen);
        Collections.sort(otherBewertungen);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BewertungWithNeigbors getBewertungWithNeighbors(Long bewertungsId) {
        final Zeugnis zeugnis = bewertungDao.findOne(bewertungsId).getZeugnis();
        final List<Bewertung> wpBewertungen = new ArrayList<>();
        final List<Bewertung> otherBewertungen = new ArrayList<>();
        splitBewertungslist(zeugnis.getBewertungen(),
                wpBewertungen, otherBewertungen);
        final List<Bewertung> bewertungen = new ArrayList<>();
        bewertungen.addAll(otherBewertungen);
        bewertungen.addAll(wpBewertungen);
        return new BewertungWithNeigbors(bewertungen, bewertungsId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public Zeugnis save(Zeugnis zeugnis) {
        zeugnis.setBuBewertungsText(VariableUtility.insertVariables(
                zeugnis.getBuBewertungsText(), zeugnis.getSchueler()));
        return zeugnisDao.save(zeugnis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SoLBewertungsText> getSoLTexte(Zeugnis zeugnis) {
        List<SoLBewertungsText> result = soLBewertungsTextDao.findAllOrderByNameAscAndTextAsc();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Zeugnis> getAllZeugnisse() {

        return zeugnisDao.findAll();
    }

}
