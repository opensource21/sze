package net.sf.sze.service.api.stammdaten;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.sze.model.stammdaten.Schueler;

import org.junit.Test;


/**
 * Test of class {@link SchuelerList}.
 *
 *
 */
public class SchuelerListTest {


    /**
     * Ein Tag in Millisekunden.
     */
    private static final long ONE_DAY = 24 * 60 * 60 * 1000L;

    /**
     * Test der SchuelerList, wenn kein aktueller Schüler existiert.
     */
    @Test
    public void testSchuelerListNoCurrentSchueler() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, null);

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(0));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(1));
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der erste Schüler aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListFirstCurrentSchueler() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(0));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(0));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(1));
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der letzte Schüler aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListLastCurrentSchueler() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(9));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isEqualTo(schueler.get(8));
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(9));
        assertThat(testee.getNextSchueler()).isNull();
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der fünfte Schüler aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListWithCurrentSchueler() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(5));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isEqualTo(schueler.get(4));
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(5));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(6));
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn ein nicht vorhandere Schüler gewählt wurde.
     */
    @Test
    public void testSchuelerListWithUnkownCurrentSchueler() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(10));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isNull();
        assertThat(testee.getNextSchueler()).isNull();
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Erzeugt eine Liste mit 10 Schülern.
     * @return eine Liste mit 10 Schülern.
     */
    private List<Schueler> create10Schueler() {
        final List<Schueler> schueler = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Schueler aSchueler = new Schueler();
            aSchueler.setId(Long.valueOf(i));
            aSchueler.setName("Name" + i);
            schueler.add(aSchueler);
        }
        return schueler;
    }


    /**
     * Test der SchuelerList, wenn die Liste leer ist.
     */
    @Test
    public void testSchuelerListEmptyList() {
        final List<Schueler> schueler = new ArrayList<>();

        final SchuelerList testee = new SchuelerList(schueler, null);

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isNull();
        assertThat(testee.getNextSchueler()).isNull();
        assertThat(testee.isEmpty()).isTrue();
    }

    /**
     * Test der SchuelerList, wenn die Liste null ist.
     */
    @Test
    public void testSchuelerListNull() {

        final SchuelerList testee = new SchuelerList(null, null);

        assertThat(testee.getSchuelerList()).isNull();
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isNull();
        assertThat(testee.getNextSchueler()).isNull();
        assertThat(testee.isEmpty()).isTrue();
    }

    /**
     * Testet den Zugriff auf die Ids wenn die Schueler Null sind.
     */
    @Test
    public void testGetIdsNull() {
        final SchuelerList testee = new SchuelerList(null, null);

        assertThat(testee.getPrevSchuelerId()).isNull();
        assertThat(testee.getCurrentSchuelerId()).isNull();
        assertThat(testee.getNextSchuelerId()).isNull();
    }

    /**
     * Testet den Zugriff auf die Ids wenn die Schueler nicht Null sind.
     */
    @Test
    public void testGetIdsNotNull() {
        final List<Schueler> schueler = create10Schueler();

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(5));

        assertThat(testee.getPrevSchuelerId()).isEqualTo(4);
        assertThat(testee.getCurrentSchuelerId()).isEqualTo(5);
        assertThat(testee.getNextSchuelerId()).isEqualTo(6);
    }

    /**
     * Test der SchuelerList, wenn der erste Schüler inaktiv ist und der
     * zweite der aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListNoCurrentSchuelerFirstInaktiv() {
        final List<Schueler> schueler = create10Schueler();
        schueler.get(0).setAbgangsDatum(new Date(System.currentTimeMillis() - ONE_DAY));

        final SchuelerList testee = new SchuelerList(schueler, null);

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(1));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(2));
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der erste Schüler inaktiv ist und der
     * zweite der aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListSecondCurrentSchuelerFirstInaktiv() {
        final List<Schueler> schueler = create10Schueler();
        schueler.get(0).setAbgangsDatum(new Date(System.currentTimeMillis() - ONE_DAY));

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(1));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isNull();
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(1));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(2));
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der letzte Schüler aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListOneBeforeLastCurrentSchuelerLastInaktiv() {
        final List<Schueler> schueler = create10Schueler();
        schueler.get(9).setAbgangsDatum(new Date(System.currentTimeMillis() - ONE_DAY));

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(8));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isEqualTo(schueler.get(7));
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(8));
        assertThat(testee.getNextSchueler()).isNull();
        assertThat(testee.isEmpty()).isFalse();
    }

    /**
     * Test der SchuelerList, wenn der fünfte Schüler aktueller Schüler ist.
     */
    @Test
    public void testSchuelerListWithCurrentSchuelerInaktiv() {
        final List<Schueler> schueler = create10Schueler();
        schueler.get(5).setAbgangsDatum(new Date(System.currentTimeMillis() - ONE_DAY));

        final SchuelerList testee = new SchuelerList(schueler, Long.valueOf(5));

        assertThat(testee.getSchuelerList()).isEqualTo(schueler);
        assertThat(testee.getPrevSchueler()).isEqualTo(schueler.get(4));
        assertThat(testee.getCurrentSchueler()).isEqualTo(schueler.get(5));
        assertThat(testee.getNextSchueler()).isEqualTo(schueler.get(6));
        assertThat(testee.isEmpty()).isFalse();
    }


}
