//SchulfachTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.model.zeugnis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;


/**
 * Class SchulfachTest
 *
 */
public class SchulfachTest {

    private final Schulfach testee = new Schulfach();

    /**
     * Test method for {@link net.sf.sze.model.zeugnis.Schulfach#convertStufenMitStandardBewertungToList()}.
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
     * Test method for {@link net.sf.sze.model.zeugnis.Schulfach#convertStufenMitBinnenDifferenzierungToList()}.
     */
    @Test
    public void testConvertStufenMitBinnenDifferenzierungToList() {
        //Arrange
        testee.setStufenMitBinnenDifferenzierung(" 3   5 6 7 8 9 10 11");
        //Act
        final List<String> actual = testee.convertStufenMitBinnenDifferenzierungToList();
        //Assert
        assertThat(actual).hasSize(8).contains("3", "5", "6", "7" , "8", "9", "10", "11");

    }

    /**
     * Test method for {@link net.sf.sze.model.zeugnis.Schulfach#convertStufenMitAussenDifferenzierungToList()}.
     */
    @Test
    public void testConvertStufenMitAussenDifferenzierungToList() {
        //Arrange
        testee.setStufenMitAussenDifferenzierung(" 3   5 6 7 8 9 10 11");
        //Act
        final List<String> actual = testee.convertStufenMitAussenDifferenzierungToList();
        //Assert
        assertThat(actual).hasSize(8).contains("3", "5", "6", "7" , "8", "9", "10", "11");

    }

}
