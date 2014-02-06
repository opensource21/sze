// ZeugnisFormularDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.ZeugnisFormular;

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
}
