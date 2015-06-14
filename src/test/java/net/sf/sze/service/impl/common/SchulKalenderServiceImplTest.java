//SchulKalenderServiceImplTest.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.sze.model.zeugnisconfig.Halbjahr;
import net.sf.sze.service.api.common.SchulkalenderService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Test der Klasse {@link SchulKalenderServiceImpl}.
 *
 */
@RunWith(Parameterized.class)
public class SchulKalenderServiceImplTest {

    /**
     * Die Parameter für den Test: Eingangsdatum, Schuljahr, Halbjahr, Abgangsdatum.
     * @return die Liste der Testdaten.
     */
    @Parameters(name = "{0}")
    public static List<Object[]> getParameter() {
        final List<Object[]> result = new ArrayList<Object[]>();
        result.add(new Object[]{"2104-12-02", nc(2014, 12, 2), Integer.valueOf(2015),
                Halbjahr.Erstes_Halbjahr, nc(2014, 8, 1)});
        result.add(new Object[]{"2014-08-01", nc(2014, 8, 1), Integer.valueOf(2015),
                Halbjahr.Erstes_Halbjahr, nc(2014, 2, 1)});
        result.add(new Object[]{"2014-10-01", nc(2014, 10, 1), Integer.valueOf(2015),
                Halbjahr.Erstes_Halbjahr, nc(2014, 8, 1)});
        result.add(new Object[]{"2014-01-31", nc(2014, 1, 31), Integer.valueOf(2014),
                Halbjahr.Erstes_Halbjahr, nc(2013, 8, 1)});
        result.add(new Object[]{"2014-03-31", nc(2014, 1, 31), Integer.valueOf(2014),
                Halbjahr.Erstes_Halbjahr, nc(2013, 8, 1)});
        result.add(new Object[]{"2014-04-01", nc(2014, 4, 1), Integer.valueOf(2014),
                Halbjahr.Beide_Halbjahre, nc(2014, 2, 1)});
        return result;
    }

    /**
     * Create a new calender-object to the given data.
     * @param year the year.
     * @param month the month.
     * @param day the day.
     * @return the new calendar object.
     */
    private static Calendar nc(int year, int month, int day) {
        final Calendar result = Calendar.getInstance();
        result.set(year, month - 1, day, 0, 0, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }



    /**das Eingangsdatum.*/
    private final Calendar refernceDay;

    /**der Stichtag wann einer als Abgegangen gilt.*/
    private final Calendar abgangsstichtag;

    private final int schuljahr;

    private final Halbjahr halbjahr;

    private SchulkalenderService testee = new SchulKalenderServiceImpl();

    /**
     * Initiates an object of type SchulKalenderServiceImplTest.
     * @param name name des Tests.
     * @param refernceDay das Eingangsdatum
     * @param abgangsstichtag der Stichtag wann einer als Abgegangen gilt.
     * @param schuljahr das Schulhalbjahr.
     * @param halbjahr das Halbjahr.
     */
    public SchulKalenderServiceImplTest(@SuppressWarnings("unused") String name,
            Calendar refernceDay, int schuljahr,
            Halbjahr halbjahr, Calendar abgangsstichtag) {
        super();
        this.refernceDay = refernceDay;
        this.abgangsstichtag = abgangsstichtag;
        this.schuljahr = schuljahr;
        this.halbjahr = halbjahr;
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.common.SchulKalenderServiceImpl#getSchuljahr(java.util.Calendar)}.
     */
    @Test
    public void testGetSchuljahr() {
        assertThat(testee.getSchuljahr(refernceDay)).isEqualTo(schuljahr);
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.common.SchulKalenderServiceImpl#getHalbjahr(java.util.Calendar)}.
     */
    @Test
    public void testGetHalbjahr() {
        assertThat(testee.getHalbjahr(refernceDay)).isEqualTo(halbjahr);
    }

    /**
     * Test method for
     * {@link net.sf.sze.service.impl.common.SchulKalenderServiceImpl#getLeavedSchoolDate(java.util.Calendar)}.
     */
    @Test
    public void testGetLeavedSchoolDateCalendar() {
        assertThat(testee.getLeavedSchoolDate(refernceDay).getTime()).isEqualTo(abgangsstichtag.getTime());
    }

}
