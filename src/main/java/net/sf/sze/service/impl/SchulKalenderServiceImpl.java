//SchulKalenderServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import java.util.Calendar;

import net.sf.sze.model.zeugnisconfig.Halbjahr;
import net.sf.sze.service.api.SchulkalenderService;

import org.springframework.stereotype.Service;

/**
 * Service welcher die entscheidenden Termin für Schulhalbjahre kennt und in
 * Bezug auf das aktuelle Datum dann entscheidungen trifft.
 *
 */
@Service
public class SchulKalenderServiceImpl implements SchulkalenderService {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentSchuljahr() {
        return getSchuljahr(Calendar.getInstance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSchuljahr(final Calendar currentDate) {
        if (currentDate.get(Calendar.MONTH) >= Calendar.AUGUST) {
            return currentDate.get(Calendar.YEAR) + 1;
        } else {
            return currentDate.get(Calendar.YEAR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Halbjahr getCurrentHalbjahr() {
        return getHalbjahr(Calendar.getInstance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Halbjahr getHalbjahr(final Calendar currentDate) {
        if (currentDate.get(Calendar.MONTH) >= Calendar.MARCH
                && currentDate.get(Calendar.MONTH) < Calendar.AUGUST) {
            return Halbjahr.Beide_Halbjahre;
        } else {
            return Halbjahr.Erstes_Halbjahr;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getLeavedSchoolDate() {
        final Calendar currentDate = Calendar.getInstance();
        return getLeavedSchoolDate(currentDate);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getLeavedSchoolDate(final Calendar currentDate) {
        final Calendar result = Calendar.getInstance();
        if (currentDate.get(Calendar.MONTH) >= Calendar.APRIL
                && currentDate.get(Calendar.MONTH) < Calendar.OCTOBER) {
            result.set(currentDate.get(Calendar.YEAR), Calendar.FEBRUARY, 1, 0, 0, 0);
        } else if (currentDate.get(Calendar.MONTH) < Calendar.APRIL) {
            result.set(currentDate.get(Calendar.YEAR) - 1, Calendar.AUGUST, 1, 0, 0, 0);
        } else {
            result.set(currentDate.get(Calendar.YEAR), Calendar.AUGUST, 1, 0, 0, 0);
        }
        result.set(Calendar.MILLISECOND, 0);
        return result;
    }

}
