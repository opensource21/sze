package net.sf.sze.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.dao.api.zeugnis.AgBewertungDao;
import net.sf.sze.dao.api.zeugnis.ArbeitsUndSozialVerhaltenDao;
import net.sf.sze.dao.api.zeugnis.ArbeitsgruppeDao;
import net.sf.sze.dao.api.zeugnis.AvSvBewertungDao;
import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.dao.api.zeugnis.SchulfachDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisArtDao;
import net.sf.sze.dao.api.zeugnis.ZeugnisDao;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.AgBewertung;
import net.sf.sze.model.zeugnis.ArbeitsUndSozialVerhalten;
import net.sf.sze.model.zeugnis.Arbeitsgruppe;
import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.AvSvBewertung;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.BinnenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.Schulfach;
import net.sf.sze.model.zeugnis.Schulfachtyp;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.StandardBewertung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.model.zeugnis.ZeugnisArt;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.service.api.ZeugnisInitialierungsService;
import net.sf.sze.util.ResultContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service welcher für die Sch&uuml;ler ein Zeugnis-Objekt mit
 * <ul>
 *  <li>den Schulfach-Bewertungen</li>
 *  <li>den Arbeitsgruppen-Bewertungen</li>
 *  <li>den AvSv-Bewertungen</li>
 * </ul>
 * anlegt.
 * @author niels
 *
 */
@Service
@Transactional(readOnly = false)
public class ZeugnisInitialisierungServiceImpl implements ZeugnisInitialierungsService {

    /**
     * Die Log-Instanz.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            ZeugnisCreatorServiceImpl.class);

    /** Minimales Schuljahr. */
    @Value("${schuljahre.min}")
    private int minimalesSchuljahr;

    /** Maximales Schuljahr. */
    @Value("${schuljahre.max}")
    private int maximalesSchuljahr;


    /**
     * Dao für die {@link Zeugnis}.
     */
    @Resource
    private ZeugnisDao zeugnisDao;

    /**
     * Dao für die {@link SchulfachDao}.
     */
    @Resource
    private SchulfachDao schulfachDao;

    /**
     * Dao für die {@link Schueler}.
     */
    @Resource
    private SchuelerDao schuelerDao;

    /**
     * Dao für die {@link ZeugnisArt}.
     */
    @Resource
    private ZeugnisArtDao zeugnisArtDao;

    /**
     * Dao für die {@link Bewertung}.
     */
    @Resource
    private BewertungDao bewertungDao;

    /**
     * Dao für die {@link Arbeitsgruppe}.
     */
    @Resource
    private ArbeitsgruppeDao arbeitsgruppeDao;

    /**
     * Dao für die {@link AgBewertung}.
     */
    @Resource
    private AgBewertungDao agBewertungDao;


    /**
     * Dao für die {@link ArbeitsUndSozialVerhalten}.
     */
    @Resource
    private ArbeitsUndSozialVerhaltenDao arbeitsUndSozialVerhaltenDao;

    /**
     * Dao für die {@link AvSvBewertung}.
     */
    @Resource
    private AvSvBewertungDao avSvBewertungDao;


    /**
     * {@inheritDoc}
     */
    @Override
    public ResultContainer initZeugnis(ZeugnisFormular formular) {
        final ResultContainer result = new ResultContainer();
        final List<ZeugnisArt> zeugnisArt = zeugnisArtDao.findAllByAktivTrueOrderBySortierungAsc();
        final List<Schueler> schueler = schuelerDao.
                findAllByAbgangsDatumIsNullOrFutureAndKlasse(
                new Date(), formular.getKlasse());

        for (Schueler einSchueler : schueler) {
                result.addResultContainer(initZeugnisForSchueler(
                        formular, zeugnisArt.get(0),  einSchueler));
        }
        return result;
    }


    private ResultContainer initZeugnisForSchueler(ZeugnisFormular formular,
            ZeugnisArt zeugnisArt, Schueler schueler) {
        final ResultContainer result = new ResultContainer();
        final Schulhalbjahr halbjahr = formular.getSchulhalbjahr();
        final Klasse klasse = schueler.getKlasse();
        Zeugnis zeugnis = zeugnisDao.findBySchulhalbjahrIdAndKlasseIdAndSchuelerId(
                halbjahr.getId(), klasse.getId(), schueler.getId());

        final int klassenstufeAsInt = klasse.calculateKlassenstufe(halbjahr.getJahr());
        final String klassenstufe = String.valueOf(klassenstufeAsInt);
        if (minimalesSchuljahr > klassenstufeAsInt || maximalesSchuljahr < klassenstufeAsInt) {
            final String message = "Für den Sch\u00fcler " + schueler
                    + " und das Halbjahr " + halbjahr
                    + " wird kein Zeugnis angelegt, da die Klassenstufe "
                    + klassenstufe + " nicht relevant ist.";
            LOG.info(message);
            result.addError(message);
            return result;
        }

        final StringBuilder changeMessage = new StringBuilder();
        if (zeugnis != null) {
            changeMessage.append("Für den Sch\u00fcler ").append(schueler).
                    append(" und das Halbjahr ").append(halbjahr).
                    append(" existiert schon ein Zeugnis.<ul>");
        } else {
            zeugnis = new Zeugnis();
            zeugnis.setSchueler(schueler); //Owning
            zeugnis.setSchulhalbjahr(halbjahr); //Owning
            zeugnis.setZeugnisArt(zeugnisArt); //Owning
            zeugnis.setFormular(formular); //Owning
            zeugnis.setKlasse(klasse); //Owning
            zeugnisDao.save(zeugnis);
        }

        handleBewertungen(zeugnis, klassenstufe, changeMessage);

        handleAgBewertungen(zeugnis, klassenstufe, changeMessage);

        handleAvSvBewertungen(zeugnis, klassenstufe, changeMessage);

        //TODO Validierung?
        if (changeMessage.length() > 0) {
            changeMessage.append("</ul>");
            String changeMessageString = changeMessage.toString();
            LOG.info(changeMessageString);

            result.addMessage(changeMessageString);
        } else {
            result.addMessage("Für den Sch\u00fcler " + schueler
                    + " und das Halbjahr " + halbjahr
                    + " wurde ein Zeugnis anlegt.");
        }
        return result;
    }


    /**
     * @param zeugnis
     * @param klassenstufe
     * @param changeMessage
     */
    private void handleAvSvBewertungen(Zeugnis zeugnis,
            final String klassenstufe, final StringBuilder changeMessage) {
        final List<ArbeitsUndSozialVerhalten> arbeitsUndSozialVerhalten =
                arbeitsUndSozialVerhaltenDao.findAll();
        Collections.sort(arbeitsUndSozialVerhalten);

        for (ArbeitsUndSozialVerhalten avSv : arbeitsUndSozialVerhalten) {
            AvSvBewertung oldAvSvBw = null;
            for (AvSvBewertung avSvBw : zeugnis.getAvSvBewertungen()) {
                if (avSv.equals(avSvBw.getArbeitsUndSozialVerhalten())) {
                    oldAvSvBw = avSvBw;
                    break;
                }
            }
            final AvSvBewertung newAvSvBw;
            if (avSv.convertKlasenStufenToList().contains(klassenstufe)) {
                newAvSvBw = new AvSvBewertung();
                newAvSvBw.setArbeitsUndSozialVerhalten(avSv);
                newAvSvBw.setZeugnis(zeugnis);
            } else {
                newAvSvBw = null;
            }
            if (oldAvSvBw != null && newAvSvBw == null) {
                changeMessage.append("\n\t <li>Arbeits- und Sozialverhalten ").
                    append(avSv.getName()).
                    append(" wurde gel\u00f6scht.</li>");
                zeugnis.getAgBewertungen().remove(oldAvSvBw);
                avSvBewertungDao.delete(oldAvSvBw);
            } else if (oldAvSvBw == null && newAvSvBw != null) {
                if (changeMessage.length() > 0) {
                    changeMessage.append("\n\t <li>Arbeits- und Sozialverhalten ").
                        append(avSv.getName()).
                        append(" wurde hinzugef\u00fcgt.</li>");
                }
                zeugnis.getAvSvBewertungen().add(newAvSvBw);
                avSvBewertungDao.save(newAvSvBw);
            }
        }
    }


    /**
     * @param zeugnis
     * @param klassenstufe
     * @param changeMessage
     */
    private void handleAgBewertungen(Zeugnis zeugnis,
            final String klassenstufe, final StringBuilder changeMessage) {
        final List<Arbeitsgruppe> arbeitsgruppen = arbeitsgruppeDao.findAll();
        Collections.sort(arbeitsgruppen);

        for (Arbeitsgruppe arbeitsgruppe : arbeitsgruppen) {
            AgBewertung oldAgBw = null;
            for (AgBewertung agBw : zeugnis.getAgBewertungen()) {
                if (arbeitsgruppe.equals(agBw.getArbeitsgruppe())) {
                    oldAgBw = agBw;
                    break;
                }
            }
            final AgBewertung newAgBw;
            if (arbeitsgruppe.convertKlasenStufenToList().contains(klassenstufe)) {
                newAgBw = new AgBewertung();
                newAgBw.setArbeitsgruppe(arbeitsgruppe);
                newAgBw.setZeugnis(zeugnis);
            } else {
                newAgBw = null;
            }
            if (oldAgBw != null && newAgBw == null) {
                changeMessage.append("\n\t <li>Arbeitsgruppe ").
                    append(arbeitsgruppe.getName()).
                    append(" wurde gel\u00f6scht.</li>");
                zeugnis.getAgBewertungen().remove(oldAgBw);
                agBewertungDao.delete(oldAgBw);
            } else if (oldAgBw == null && newAgBw != null) {
                if (changeMessage.length() > 0) {
                    changeMessage.append("\n\t <li>Arbeitsgruppe ").
                        append(arbeitsgruppe.getName()).
                        append(" wurde hinzugef\u00fcgt.</li>");
                }
                zeugnis.getAgBewertungen().add(newAgBw);
                agBewertungDao.save(newAgBw);
            }
        }
    }

    /**
     * @param zeugnis
     * @param klassenstufe
     * @param changeMessage
     */
    private void handleBewertungen(Zeugnis zeugnis, final String klassenstufe,
            final StringBuilder changeMessage) {
        final List<Schulfach> schulfaecher = schulfachDao.findAll();
        Collections.sort(schulfaecher);
        //Sicherstellen, dass die Liste der Bewertungen gelesen wurde
        zeugnis.getBewertungen().size();
        for (Schulfach schulfach : schulfaecher) {
            Bewertung oldBw = null;
            for (Bewertung oldBewertung : zeugnis.getBewertungen()) {
                if (schulfach.equals(oldBewertung.getSchulfach())) {
                    oldBw = oldBewertung;
                    break;
                }
            }
            final Bewertung newBw = createBewertung(schulfach, klassenstufe, zeugnis);
            if (oldBw != null) {
                //Pruefen, ob der Typ richtig ist.
                if (newBw == null) {
                    changeMessage.append('\n').append('\t').append(
                            "<li>Bewertung für ${schulfach.name} wurde gel\u00f6scht.</li>");
                    zeugnis.getBewertungen().remove(oldBw);
                    oldBw.setZeugnis(null);
                    bewertungDao.delete(oldBw);
                } else if (newBw.getClass() != oldBw.getClass()) {
                    changeMessage.append("\n\t <li>Bewertung für ").
                            append(schulfach.getName()).append(
                            " wurde konvertiert von ")
                            .append(oldBw.getClass().getSimpleName())
                            .append(" nach ").append(newBw.getClass().getSimpleName());
                    zeugnis.getBewertungen().remove(oldBw);
                    oldBw.setZeugnis(null);
                    bewertungDao.delete(oldBw);
                    newBw.setLeistungNurSchwachAusreichend(oldBw.getLeistungNurSchwachAusreichend());
                    newBw.setNote(oldBw.getNote());
                    newBw.setSonderNote(oldBw.getSonderNote());
                    newBw.setRelevant(oldBw.getRelevant());

                    zeugnis.getBewertungen().add(newBw);
                    bewertungDao.save(newBw);
                }
            } else {
                if (newBw == null) {
                    LOG.debug("{} ist für {} nicht relevant", schulfach, klassenstufe);
                } else {
                    if (changeMessage.length() > 0) {
                        changeMessage.append('\n').append('\t').append(
                                "<li>Bewertung für ${schulfach.name} wurde erg\u00e4nzt.</li>");
                    }
                    zeugnis.getBewertungen().add(newBw);
                    bewertungDao.save(newBw);
                }
            }
        }
    }

    private Bewertung createBewertung(Schulfach schulfach, String klassenStufe, Zeugnis zeugnis) {
        Bewertung result = null;
        if (schulfach.convertStufenMitStandardBewertungToList().contains(klassenStufe)) {
            result = new StandardBewertung();
        } else if (schulfach.convertStufenMitBinnenDifferenzierungToList().contains(klassenStufe)) {
            result = new BinnenDifferenzierteBewertung();
        } else if (schulfach.convertStufenMitAussenDifferenzierungToList().contains(klassenStufe)) {
            result = new AussenDifferenzierteBewertung();
        } else {
            return null;
        }
        result.setSchulfach(schulfach);
        result.setZeugnis(zeugnis);
        result.setRelevant(!Schulfachtyp.WAHLPFLICHT.equals(schulfach.getTyp()));
        return result;
    }

}
