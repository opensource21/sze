//SchulfachTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.model.zeugnisconfig;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;


/**
 * Test für das {@link Schulfach}.
 *
 */
public class SchulfachTest {

    private final Schulfach testee = new Schulfach();

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulfach#convertStufenMitStandardBewertungToList()}.
     */
    @Test
    public void testConvertStufenMitStandardBewertungToList() {
        //Arrange
        testee.setStufenMitStandardBewertung(" 3   5 6 7 8 9 10 11");
        //Act
        final List<String> actual = testee.convertStufenMitStandardBewertungToList();
        //Assert
        assertThat(actual).hasSize(8).contains("3", "5", "6", "7" , "8", "9", "10", "11");

    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulfach#convertStufenMitBinnenDifferenzierungToList()}.
     */
    @Test
    public void testConvertStufenMitBinnenDifferenzierungToList() {
        //Arrange
        testee.setStufenMitDreiNiveaus(" 3   5 6 7 8 9 10 11");
        //Act
        final List<String> actual = testee.convertStufenMitBinnenDifferenzierungToList();
        //Assert
        assertThat(actual).hasSize(8).contains("3", "5", "6", "7" , "8", "9", "10", "11");

    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulfach#convertStufenMitAussenDifferenzierungToList()}.
     */
    @Test
    public void testConvertStufenMitAussenDifferenzierungToList() {
        //Arrange
        testee.setStufenMitZweiNiveaus(" 3   5 6 7 8 9 10 11");
        //Act
        final List<String> actual = testee.convertStufenMitAussenDifferenzierungToList();
        //Assert
        assertThat(actual).hasSize(8).contains("3", "5", "6", "7" , "8", "9", "10", "11");

    }

}
