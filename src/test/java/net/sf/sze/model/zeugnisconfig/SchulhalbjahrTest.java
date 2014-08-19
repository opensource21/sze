// SchulhalbjahrTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

/**
 * Test für {@link Schulhalbjahr}.
 *
 */
public class SchulhalbjahrTest {

    private Schulhalbjahr createTestee(int jahr, Halbjahr halbjahr) {
        final Schulhalbjahr result = new Schulhalbjahr();
        result.setJahr(jahr);
        result.setHalbjahr(halbjahr);
        return result;
    }

    /**
     * Test method for {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#isErstesHalbjahr()}.
     */
    @Test
    public void testIsErstesHalbjahrTrue() {
        final Schulhalbjahr testee = createTestee(1970, Halbjahr
                .Erstes_Halbjahr);
        assertThat(testee.isErstesHalbjahr()).isTrue();
    }

    /**
     * Test method for {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#isErstesHalbjahr()}.
     */
    @Test
    public void testIsErstesHalbjahrFalse() {
        final Schulhalbjahr testee = createTestee(1970, Halbjahr
                .Beide_Halbjahre);
        assertThat(testee.isErstesHalbjahr()).isFalse();
    }

    /**
     * Test method for {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#toString()}.
     */
    @Test
    public void testToStringErstesHj() {
        final Schulhalbjahr testee = createTestee(2010, Halbjahr
                .Erstes_Halbjahr);
        assertThat(testee.toString()).isEqualTo("2009/10 1. Hj.");
    }

    /**
     * Test method for {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#toString()}.
     */
    @Test
    public void testToStringBeideHj() {
        final Schulhalbjahr testee = createTestee(2010, Halbjahr
                .Beide_Halbjahre);
        assertThat(testee.toString()).isEqualTo("2009/10 1.+2. Hj.");
    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#createRelativePathName()}.
     */
    @Test
    public void testCreateRelativePathNameErsteJahre() {
        final Schulhalbjahr testee = createTestee(2010, Halbjahr
                .Erstes_Halbjahr);
        assertThat(testee.createRelativePathName()).isEqualTo("2009-10/Hj-1");
    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#createRelativePathName()}.
     */
    @Test
    public void testCreateRelativePathNameBeideJahre() {
        final Schulhalbjahr testee = createTestee(2010, Halbjahr
                .Beide_Halbjahre);
        assertThat(testee.createRelativePathName()).isEqualTo("2009-10/Hj-2");
    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#compareTo(net.sf.sze.model.zeugnisconfig.Schulhalbjahr)}.
     */
    @Test
    public void testCompareTo() {
        final Schulhalbjahr schulhalbjahr1 = createTestee(2010, Halbjahr
                .Erstes_Halbjahr);
        final Schulhalbjahr schulhalbjahr2 = createTestee(2010, Halbjahr
                .Beide_Halbjahre);
        final Schulhalbjahr schulhalbjahr3 = createTestee(2011, Halbjahr
                .Erstes_Halbjahr);
        assertThat(schulhalbjahr1.compareTo(schulhalbjahr2)).isEqualTo(-1);
        assertThat(schulhalbjahr1.compareTo(schulhalbjahr3)).isEqualTo(-1);
        assertThat(schulhalbjahr2.compareTo(schulhalbjahr3)).isEqualTo(-1);

    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#toPrintMap(java.util.Map)}.
     */
    @Test
    public void testToPrintMapErsteHalbjahr() {
        Schulhalbjahr testee = createTestee(2010, Halbjahr.Erstes_Halbjahr);
        final Map<String, Object> printMap = new HashMap<String, Object>();

        testee.toPrintMap(printMap);

        assertThat(printMap.get("shj_jahr")).isEqualTo("2009/10");
        assertThat(printMap.get("shj_halbjahr")).isEqualTo("1.");
        assertThat(printMap.get("shj")).isEqualTo("2009/10 1. Hj.");
    }

    /**
     * Test method for
     * {@link net.sf.sze.model.zeugnisconfig.Schulhalbjahr#toPrintMap(java.util.Map)}.
     */
    @Test
    public void testToPrintMapBeideHalbjahr() {
        Schulhalbjahr testee = createTestee(2013, Halbjahr.Beide_Halbjahre);
        final Map<String, Object> printMap = new HashMap<String, Object>();

        testee.toPrintMap(printMap);

        assertThat(printMap.get("shj_jahr")).isEqualTo("2012/13");
        assertThat(printMap.get("shj_halbjahr")).isEqualTo("1.+2.");
        assertThat(printMap.get("shj")).isEqualTo("2012/13 1.+2. Hj.");
    }
}
