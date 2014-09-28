// BemerkungServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.service.impl.zeugnis;

import java.util.List;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.SchulamtsBemerkungDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulamtDao;
import net.sf.sze.dao.api.zeugnisconfig.SchulamtsBemerkungsBausteinDao;
import net.sf.sze.model.zeugnis.SchulamtsBemerkung;
import net.sf.sze.model.zeugnisconfig.Schulamt;
import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;
import net.sf.sze.service.api.zeugnis.SchulamtsBemerkungService;
import net.sf.sze.util.VariableUtility;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link SchulamtsBemerkungeService}.
 */
@Transactional(readOnly = true)
@Service
public class SchulamtsBemerkungsServiceImpl implements SchulamtsBemerkungService {


    /** The {@link SchulamtsBemerkungDao}. */
    @Resource
    private SchulamtsBemerkungDao schulamtsBemerkungDao;

    /** The {@link SchulamtsBemerkungsBausteinDao}. */
    @Resource
    private SchulamtsBemerkungsBausteinDao schulamtsBemerkungsBausteinDao;


    /** The {@link SchulamtDao}. */
    @Resource
    private SchulamtDao schulamtDao;


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SchulamtsBemerkung> getAllSchulamtsBemerkungen() {
        return schulamtsBemerkungDao.findAll((PageRequest) null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SchulamtsBemerkung> getSchulamtsBemerkungen(int skip, int count, Order... order) {
        final Sort sort = (order.length > 0) ? new Sort(order) : null;
        final PageRequest pr = new PageRequest(skip / count, count, sort);
        return this.getSchulamtsBemerkungen(pr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SchulamtsBemerkung> getSchulamtsBemerkungen(Pageable page) {
        return schulamtsBemerkungDao.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public SchulamtsBemerkung save(SchulamtsBemerkung schulamtsBemerkung) {
        schulamtsBemerkung.setFreiText(VariableUtility.insertVariables(
                schulamtsBemerkung.getFreiText(),
                schulamtsBemerkung.getZeugnis().getSchueler()));

        return schulamtsBemerkungDao.save(schulamtsBemerkung);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchulamtsBemerkung read(Long bemerkungBausteinId) {
        return schulamtsBemerkungDao.findOne(bemerkungBausteinId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long schulamtsBemerkungsId) {
        final SchulamtsBemerkung oldBemerkung = schulamtsBemerkungDao.
                findOne(schulamtsBemerkungsId);
        schulamtsBemerkungDao.delete(oldBemerkung);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNrOfSchulamtsBemerkungen() {
        return schulamtsBemerkungDao.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SchulamtsBemerkungsBaustein> getAllSchulamtsBemerkungsBausteine(
                    SchulamtsBemerkung schulamtsBemerkung) {
        final List<SchulamtsBemerkungsBaustein> bausteine = schulamtsBemerkungsBausteinDao.
                findAllByAktivTrueOrderByNameAsc();
        final boolean gotSchulamtsBemerkungsBausteine = schulamtsBemerkung != null
                && schulamtsBemerkung.getSchulamtsBemerkungsBaustein() != null;
        if (gotSchulamtsBemerkungsBausteine && !bausteine.contains(
                schulamtsBemerkung.getSchulamtsBemerkungsBaustein())) {
            bausteine.add(schulamtsBemerkung.getSchulamtsBemerkungsBaustein());
        }
        return bausteine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Schulamt> getAllSchulaemter(
            SchulamtsBemerkung schulamtsBemerkung) {
        final List<Schulamt> bausteine = schulamtDao.
                findAllByAktivTrueOrderByNameAsc();
        final boolean gotSchulaemter = schulamtsBemerkung != null
                && schulamtsBemerkung.getSchulamt() != null;
        if (gotSchulaemter && !bausteine.contains(schulamtsBemerkung.getSchulamt())) {
            bausteine.add(schulamtsBemerkung.getSchulamt());
        }
        return bausteine;

    }
}
