//AnonymisierungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Bemerkung;
import net.sf.sze.model.zeugnis.SchulamtsBemerkung;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.service.api.AnonymisierungsService;
import net.sf.sze.service.api.BemerkungService;
import net.sf.sze.service.api.SchulamtsBemerkungService;
import net.sf.sze.service.api.ZeugnisErfassungsService;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Anoymisierungsservice.
 *
 */
@Transactional(readOnly = false)
@Service
public class AnonymisierungsServiceImpl implements AnonymisierungsService {

    @Resource
    private ZeugnisErfassungsService zeugnisErfassungsService;

    @Resource
    private BemerkungService bemerkungService;

    @Resource
    private SchulamtsBemerkungService schulamtsBemerkungService;

    @Resource
    private SchuelerDao schuelerDao;

    private static final String[] maedchenNamen = { "Mia", "Emma", "Hannah ",
            "Sofia ", "Anna", "Lea ", "Emilia", "Marie", "Lena", "Leonie",
            "Emily ", "Lina", "Amelie", "Sophie ", "Lilly ", "Luisa ",
            "Johanna", "Laura", "Nele ", "Lara", "Maja ", "Charlotte",
            "Clara ", "Leni", "Sarah ", "Pia", "Mila", "Alina", "Lisa",
            "Lotta", "Ida", "Julia", "Greta", "Mathilda ", "Melina", "Zoe ",
            "Frieda ", "Lia ", "Paula", "Marlene", "Ella", "Emely ", "Jana",
            "Victoria ", "Josephine", "Finja ", "Isabell ", "Helena",
            "Isabella", "Elisa" };

    private static final String[] jungsNamen = { "Ben", "Luca ", "Paul",
            "Jonas", "Finn ", "Leon", "Luis ", "Lukas ", "Maximilian", "Felix",
            "Noah", "Elias", "Julian", "Max", "Tim", "Moritz", "Henry ",
            "Niklas ", "Philipp", "Jakob ", "Tom", "Jan", "Emil", "Alexander",
            "David", "Oskar ", "Fabian", "Anton", "Erik ", "Rafael ", "Matteo",
            "Leo", "Mats ", "Simon", "Jannik ", "Lennard ", "Liam", "Linus",
            "Hannes", "Mika", "Vincent", "Adrian", "Jonathan", "Theo", "Nico ",
            "Till", "Benjamin", "Florian", "Marlon", "Julius" };

    private static final String[] nachnamen = { "Müller", "Schmidt",
            "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
            "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter",
            "Klein", "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann",
            "Braun", "Krüger", "Hofmann", "Hartmann", "Lange", "Schmitt",
            "Werner", "Schmitz", "Krause", "Meier", "Lehmann", "Schmid",
            "Schulze", "Maier", "Köhler", "Herrmann", "König", "Walter",
            "Mayer", "Huber", "Kaiser", "Fuchs", "Peters", "Lang", "Scholz",
            "Möller", "Weiß", "Jung", "Hahn", "Schubert", "Vogel", "Friedrich",
            "Keller", "Günther", "Frank", "Berger", "Winkler", "Roth", "Beck",
            "Lorenz", "Baumann", "Franke", "Albrecht", "Schuster", "Simon",
            "Ludwig", "Böhm", "Winter", "Kraus", "Martin", "Schumacher",
            "Krämer", "Vogt", "Stein", "Jäger", "Otto", "Sommer", "Groß",
            "Seidel", "Heinrich", "Brandt", "Haas", "Schreiber", "Graf",
            "Schulte", "Dietrich", "Ziegler", "Kuhn", "Kühn", "Pohl", "Engel",
            "Horn", "Busch", "Bergmann", "Thomas", "Voigt", "Sauer", "Arnold",
            "Wolff", "Pfeiffer" };

    /**
     * {@inheritDoc}
     */
    @Override
    public void replaceAllNamesWithVariables() {
        //Das simple speichern reicht, da die eigentliche Arbeit inzwischen
        //im Service steckt.
        for (Zeugnis zeugnis : zeugnisErfassungsService.getAllZeugnisse()) {
            zeugnisErfassungsService.save(zeugnis);
            for (Bemerkung bemerkung : zeugnis.getBemerkungen()) {
                bemerkungService.save(bemerkung);
            }
            for (SchulamtsBemerkung bemerkung : zeugnis.getSchulamtsBemerkungen()) {
                schulamtsBemerkungService.save(bemerkung);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void anonymisierSchueler() {
        final Iterable<Schueler> allSchueler = schuelerDao.findAll();
        int i = 0;
        for (Schueler schueler : allSchueler) {
            if (Geschlecht.WEIBLICH.equals(schueler.getGeschlecht())) {
                schueler.setVorname(maedchenNamen[i % maedchenNamen.length]);
            } else {
                schueler.setVorname(jungsNamen[i % jungsNamen.length]);
            }
            schueler.setName(nachnamen[i % nachnamen.length]);
            schueler.setRufname(null);
            if (schueler.getAufnahmeDatum() != null) {
                schueler.setAufnahmeDatum(DateUtils.addDays(
                        schueler.getAufnahmeDatum(), RandomUtils.nextInt(360) - 180));
            }
            schueler.setGeburtstag(DateUtils.addDays(schueler.getGeburtstag(),
                    RandomUtils.nextInt(360) - 180));
            schueler.setGeburtsort("Erde " + schueler.getId());
            schuelerDao.save(schueler);
            i++;
        }

    }

}
