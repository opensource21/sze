// ZeugnisErfassungsServiceImpl.java
//
// (c) SZE-Development-Team

package net.sf.sze.service.impl;

import net.sf.sze.dao.api.KlasseDao;
import net.sf.sze.dao.api.SchulhalbjahrDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Standardimplementierung vom {@link ZeugnisErfassungsService}.
 *
 */
@Service
@Transactional(readOnly = true)
public class ZeugnisErfassungsServiceImpl implements ZeugnisErfassungsService {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisErfassungsServiceImpl.class);

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
}
