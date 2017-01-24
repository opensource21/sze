//AnonymisierungsServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.admin;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.sze.dao.api.stammdaten.SchuelerDao;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.service.api.admin.AnonymisierungsService;
import net.sf.sze.util.Geschlecht;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Testet den {@link AnonymisierungsServiceImpl}.
 *
 */
public class AnonymisierungsServiceImplTest {

    /**
     * Die Log-Instanz.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            AnonymisierungsServiceImplTest.class);

    @InjectMocks
    private AnonymisierungsService anonymisierungsService = new AnonymisierungsServiceImpl();

    @Mock
    private SchuelerDao schuelerDao;


    /**
     * Initialisiert die Mocks.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.admin.AnonymisierungsServiceImpl#anonymisierSchueler()}.
     */
    @Test
    public void testAnonymisierSchuelerMinimalMaenlich() {
        //Arrange
        final List<Schueler> schuelerList = new ArrayList<Schueler>();
        final Schueler schueler = createMinimalSchuelerMaennlich();
        schuelerList.add(schueler);
        when(schuelerDao.findAll()).thenReturn(schuelerList);
        //Act
        anonymisierungsService.anonymisierSchueler();
        //Assert
        verify(schuelerDao).save(
                argThat(new SchuelerMatcher(createMinimalSchuelerMaennlich())));
    }

    private Schueler createMinimalSchuelerMaennlich() {
        final Schueler schueler = new Schueler();
        schueler.setVorname("Abcd");
        schueler.setName("asdsa");
        schueler.setGeburtstag(new Date());
        schueler.setGeburtsort("ldlfglfd");
        schueler.setGeschlecht(Geschlecht.MAENNLICH);
        return schueler;
    }


    /**
     * Test method for
     * {@link net.sf.sze.service.impl.admin.AnonymisierungsServiceImpl#anonymisierSchueler()}.
     */
    @Test
    public void testAnonymisierSchuelerFullWeiblich() {
        //Arrange
        final List<Schueler> schuelerList = new ArrayList<Schueler>();
        final Schueler schueler = createMinimalFullWeiblich();
        schuelerList.add(schueler);
        when(schuelerDao.findAll()).thenReturn(schuelerList);
        //Act
        anonymisierungsService.anonymisierSchueler();
        //Assert
        verify(schuelerDao).save(
                argThat(new SchuelerMatcher(createMinimalFullWeiblich())));
    }

    private Schueler createMinimalFullWeiblich() {
        final Schueler schueler = new Schueler();
        schueler.setVorname("Abcd");
        schueler.setName("asdsa");
        schueler.setRufname("sahdlsa");
        schueler.setGeburtstag(new Date());
        schueler.setGeburtsort("ldlfglfd");
        schueler.setAufnahmeDatum(new Date());
        schueler.setGeschlecht(Geschlecht.WEIBLICH);
        return schueler;
    }

    /**
     * Matcher der nur dann zuschlägt, wenn sich alle personen relevanten
     * geändert haben.
     *
     */
    private static final class SchuelerMatcher extends ArgumentMatcher<Schueler> {

        private final Schueler schueler;

        /**
         * Initiates an object of type SchuelerMatcher.
         * @param schueler der Schueler.
         */
        public SchuelerMatcher(Schueler schueler) {
            super();
            this.schueler = schueler;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean matches(Object argument) {
            Schueler other = (Schueler) argument;
            if (schueler.getName().equals(other.getName())) {
                AnonymisierungsServiceImplTest.LOG.error("Name ist gleich.");
                return false;
            }
            if (schueler.getVorname().equals(other.getVorname())) {
                AnonymisierungsServiceImplTest.LOG.error("Vorname ist gleich.");
                return false;
            }
            if (other.getRufname() != null) {
                AnonymisierungsServiceImplTest.LOG.error("Rufname ist nicht null.");
                return false;
            }
            if (schueler.getGeburtsort().equals(other.getGeburtsort())) {
                AnonymisierungsServiceImplTest.LOG.error("Geburtsort ist gleich.");
                return false;
            }
            if (schueler.getGeburtstag().equals(other.getGeburtstag())) {
                AnonymisierungsServiceImplTest.LOG.error("Geburtstag ist gleich.");
                return false;
            }
            if (schueler.getAufnahmeDatum() != null
                    && schueler.getAufnahmeDatum().equals(other.getAufnahmeDatum())) {
                AnonymisierungsServiceImplTest.LOG.error("Aufnahmedatum ist gleich.");
                return false;
            }
            return true;
        }





    }
}
