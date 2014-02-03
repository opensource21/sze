//VariableUtilityTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;

import org.junit.Test;


/**
 * Test der Klasse {@link VariableUtility}.
 *
 */
public class VariableUtilityTest {

    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#getInvalidVariables(java.lang.String)}.
     */
    @Test
    public void testGetInvalidVariablesGoodCase() {
        //Arrange
        final String text = "@name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@, @AT@";
        //Act
        final List<String> wrongParamNames = VariableUtility.getInvalidVariables(text);
        //Assert
        assertThat(wrongParamNames).isEmpty();
    }


    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#getInvalidVariables(java.lang.String)}.
     */
    @Test
    public void testGetInvalidVariablesBadCase() {
        //Arrange
        final String text = "@name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@"
                + "@name @, @NAme@, @ NAME@, @Datum@, @Schuljahr@, "
                + "@VorName@, @NachName@, @Na ";
        //Act
        final List<String> wrongParamNames = VariableUtility.getInvalidVariables(text);
        //Assert
        assertThat(wrongParamNames).containsExactly("name ", "NAme", " NAME", "Datum",
                "Schuljahr", "VorName", "NachName", "Na ");
    }

    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @Test
    public void testCreatePrintTextErRufnameNullDatumNull() {
        //Arrange
        final String text = "@name@, @Name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@, @Sein|Ihr@";
        final Schueler schueler = new Schueler();
        schueler.setVorname("Willi");
        schueler.setName("Mustermann");
        schueler.setGeschlecht(Geschlecht.MAENNLICH);
        //Act
        final String result = VariableUtility.createPrintText(text, schueler,
                null, true, "2014");
        //Assert
        assertThat(result).isEqualTo("er, Er, Er, Willi, , 2014, "
                + "Willi, Mustermann, Sein");
    }

    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @Test
    public void testCreatePrintTextSieRufnameNullDatumNull() {
        //Arrange
        final String text = "@name@, @Name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@, @Sein|Ihr@";
        final Schueler schueler = new Schueler();
        schueler.setVorname("Susi");
        schueler.setName("Mustermann");
        schueler.setGeschlecht(Geschlecht.WEIBLICH);
        //Act
        final String result = VariableUtility.createPrintText(text, schueler,
                null, true, "2014");
        //Assert
        assertThat(result).isEqualTo("sie, Sie, Sie, Susi, , 2014, "
                + "Susi, Mustermann, Ihr");
    }


    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @Test
    public void testCreatePrintTextRufnameNullDatumNull() {
        //Arrange
        final String text = "_@name@, @Name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@";
        final Schueler schueler = new Schueler();
        schueler.setVorname("Willi");
        schueler.setName("Mustermann");
        schueler.setGeschlecht(Geschlecht.MAENNLICH);
        //Act
        final String result = VariableUtility.createPrintText(text, schueler,
                null, false, "2014");
        //Assert
        assertThat(result).isEqualTo("_Willi, Er, Er, Willi, , 2014, "
                + "Willi, Mustermann");
    }


    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void testCreatePrintTextRufnameDatumNotNull() {
        //Arrange
        String text = "@name@, @Name@, @Name@, @NAME@, @datum@, @schuljahr@, "
                + "@Vorname@, @Nachname@, @AT@";
        final Schueler schueler = new Schueler();
        schueler.setVorname("Willfried Siegbert");
        schueler.setName("Mustermann");
        schueler.setRufname("Willi");
        schueler.setGeschlecht(Geschlecht.MAENNLICH);
        //Act
        final String result = VariableUtility.createPrintText(text, schueler,
                new Date(114, 11, 31), false, "2014");
        //Assert
        assertThat(result).isEqualTo("Willi, Er, Er, Willi, 31.12.2014, 2014, "
                + "Willfried Siegbert, Mustermann, @");
    }

    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @Test
    public void testCreatePrintTextNoVar() {
        //Arrange
        final String text = "Testen ist gut!";
        //Act
        final String result = VariableUtility.createPrintText(text, null,
                null, false, "2014");
        //Assert
        assertThat(result).isEqualTo("Testen ist gut!");
    }

    /**
     * Test method for
     * {@link net.sf.sze.util.VariableUtility#createPrintText(java.lang.String, net.sf.sze.model.stammdaten.Schueler, java.util.Date, boolean, java.lang.String)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testCreatePrintTextIllegalVar() {
        //Arrange
        final String text = "Testen ist gut@!";
        final Schueler schueler = new Schueler();
        schueler.setVorname("Willfried Siegbert");
        schueler.setName("Mustermann");
        schueler.setRufname("Willi");
        schueler.setGeschlecht(Geschlecht.MAENNLICH);
        //Act
        VariableUtility.createPrintText(text, schueler, null, false, "2014");
        fail("IllegalStateEception expected");
    }

    /**
     * Test method for {@link net.sf.sze.util.VariableUtility#insertVariables(java.lang.String, net.sf.sze.model.stammdaten.Schueler)}.
     */
    @Test
    public void testInsertVariables() {
        fail("Not yet implemented");
    }

}
