// SoLBewertungsTextDao.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.zeugnis.SoLBewertungsText;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link SoLBewertungsText}.
 *
 */
public interface SoLBewertungsTextDao
        extends PagingAndSortingRepository<SoLBewertungsText, Long> {

    /**
     * Liefert die sortierte Liste der SoL-Bewertungstexte.
     * @return die sortierte Liste der SoL-Bewertungstexte.
     */
    @Query("select t from SoLBewertungsText t order by t.name asc, t.text asc")
    List<SoLBewertungsText> findAllOrderByNameAscAndTextAsc();
}
