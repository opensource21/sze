//BewertungErfassungsServiceImpl.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.impl;

import javax.annotation.Resource;

import net.sf.sze.dao.api.zeugnis.BewertungDao;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.service.api.BewertungErfassungsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementierung des {@link BewertungErfassungsService}.
 *
 */
@Transactional(readOnly = true)
@Service
public class BewertungErfassungsServiceImpl implements
        BewertungErfassungsService {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(BewertungErfassungsServiceImpl.class);

    /**
     * Bertungsdao.
     */
    @Resource
    private BewertungDao bewertungDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Bewertung> getBewertungen(long halbjahrId, long klassenId,
            Pageable pageable) {
        return bewertungDao.findAllByZeugnisKlasseIdAndZeugnisSchulhalbjahrIdAndZeugnisSchulhalbjahrSelectableIsTrueOrderByZeugnisSchuelerNameAscZeugnisSchuelerVornameAsc(klassenId, halbjahrId, pageable);
    }

}
