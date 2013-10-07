// ZeugnisDao.java
//
// (c) SZE-Development-Team

package net.sf.sze.dao.api.zeugnis;

import java.util.List;

import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.model.zeugnis.Schulhalbjahr;
import net.sf.sze.model.zeugnis.Zeugnis;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * DAO fürs {@link Zeugnis}.
 *
 */
public interface ZeugnisDao extends PagingAndSortingRepository<Zeugnis, Long> {

    /**
     * Liefert alle Zeugnisse zu der Klasse und dem Halbjahr.
     * @param klasseId die Id der Klasse
     * @param halbjahrId die Id des Schulhalbjahr
     * @return die Liste der Zeugnisse.
     */
    //J-
    List<Zeugnis>
            findAllByKlasseIdAndSchulhalbjahrIdAndSchulhalbjahrSelectableIsTrueOrderBySchuelerNameAscSchuelerVornameAsc(
            long klasseId, long halbjahrId);
    //J+

    /**
     * Liefert alle Zeugnisse zu einem Schulhalbjahr.
     * @param halbjahr das Schulhalbjahr.
     * @return alle Zeugnisse zu einem Schulhalbjahr.
     */
    List<Zeugnis> findAllBySchulhalbjahr(Schulhalbjahr halbjahr);

    /**
     * Liefert alle Zeugnisse zu einem Schulhalbjahr und einer Klasse.
     * @param halbjahr das Schulhalbjahr.
     * @param klasse die Klasse.
     * @return alle Zeugnisse zu einem Schulhalbjahr und einer Klasse.
     */
    List<Zeugnis> findAllBySchulhalbjahrAndKlasse(Schulhalbjahr halbjahr,
            Klasse klasse);

    /**
     * Liefert alle Zeugnisse eines Schülers in einem Schulhalbjahr.
     * @param schueler der Schüler.
     * @param schulhalbjahr das Halbjahr.
     * @return alle Zeugnisse eines Schülers in einem Schulhalbjahr.
     */
    Zeugnis findBySchuelerAndSchulhalbjahr(Schueler schueler,
            Schulhalbjahr schulhalbjahr);

    /**
     * Liefert alle Zeugnisse eines Schülers.
     * @param schueler der Schüler.
     * @return alle Zeugnisse eines Schülers.
     */
    List<Zeugnis> findAllBySchuelerOrderBySchulhalbjahrAsc(Schueler schueler);
}
