// ZeugnisFormularDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.calendar.Halbjahr;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link ZeugnisFormular}.
 *
 */
public interface ZeugnisFormularDao
        extends PagingAndSortingRepository<ZeugnisFormular, Long> {

    /**
     * Liefert die Liste aller Zeugnisformulare sortiert zurück.
     * @param selectable true, wenn nur die selektierbaren ausgegeben werden sollen.
     * @return die Zeugnisformulare
     */
    //J-
    List<ZeugnisFormular>
            findAllBySchulhalbjahrSelectableOrderBySchulhalbjahrJahrDescSchulhalbjahrHalbjahrDescKlasseJahrgangDescKlasseSuffixAscBeschreibungDesc(
            boolean selectable);


    /**
     * Findet das ZeugnisFormualar zum {@link Schulhalbjahr} und der {@link Klasse}.
     * @param schuljahr das Schuljahr.
     * @param halbjahr das {@link Halbjahr}.
     * @param klasse die Klasse.
     * @return das {@link ZeugnisFormular}.
     */
    ZeugnisFormular findBySchulhalbjahrJahrAndSchulhalbjahrHalbjahrAndKlasse(
            int schuljahr, Halbjahr halbjahr, Klasse klasse);


    /**
     * Findet das erste ZeugnisFormualar zum {@link Schulhalbjahr} und der
     * Klassenstufe.
     * @param schuljahr das Schuljahr.
     * @param halbjahr das {@link Halbjahr}.
     * @param klassenstufe der Jahrgang des Klasse.
     * @return das {@link ZeugnisFormular}.
     */
    List<ZeugnisFormular>
            findAllBySchulhalbjahrJahrAndSchulhalbjahrHalbjahrAndKlasseJahrgang(
                    int schuljahr, Halbjahr halbjahr, int klassenstufe);


    /**
     * Findet das Zeugnisformular zu dem {@link Schulhalbjahr} und der {@link Klasse}.
     * @param halbjahrId die Id des Schulhalbjahrs
     * @param klassenId die Id der Klasse.
     * @return das Zeugnisformular.
     */
    ZeugnisFormular findBySchulhalbjahrIdAndKlasseId(long halbjahrId,
            long klassenId);
}
