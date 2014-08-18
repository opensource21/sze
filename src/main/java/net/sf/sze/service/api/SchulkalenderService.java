//SchulkalendarService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.api;

import java.util.Calendar;

import net.sf.sze.model.zeugnis.Halbjahr;


/**
 * Service welcher die entscheidenden Termin für Schulhalbjahre kennt und in
 * Bezug auf das aktuelle Datum dann entscheidungen trifft.
 *
 */
public interface SchulkalenderService {

    /**
     * Liefert das aktuelle Schuljahr.
     * @return das aktuelle Schuljahr.
     */
    int getCurrentSchuljahr();

    /**
     * Liefert das Schuljahr zum Bezugsdatun.
     * @param currentDate das Bezugsdatum.
     * @return das Schuljahr zum Bezugsdatun.
     */
    int getSchuljahr(Calendar currentDate);


    /**
     * Liefert das aktuelle Halbjahr.
     * @return das aktuelle Halbjahr.
     */
    Halbjahr getCurrentHalbjahr();

    /**
     * Liefert das Halbjahr zum Bezugsdatun.
     * @param currentDate das Bezugsdatum.
     * @return das Halbjahr zum Bezugsdatun.
     */
    Halbjahr getHalbjahr(Calendar currentDate);


    /**
     * Liefert das Datum für das ein Schüler als abgegangen gilt, wenn sein
     * Abgangsdatum davor liegt. Der Hintergrund ist, dass man auch für Schüler,
     * die abgehen, man noch eine gewisse Zeit die Zeugnisse erstellen möchte etc..
     * Die Logik ist, dass bis zum 1. Oktober der 1.Feb. genommen wird und
     * bis 1.4. der 1.August.
     * @return das Datum ab dem der Schüler als abgegegangen gilt.
     */
    Calendar getLeavedSchoolDate();

    /**
     * Liefert das Datum für das ein Schüler als abgegangen gilt, wenn sein
     * Abgangsdatum davor liegt.
     * @param currentDate das referenz Datum.
     * @return das Datum ab dem der Schüler als abgegegangen gilt.
     */
    Calendar getLeavedSchoolDate(Calendar currentDate);




}
