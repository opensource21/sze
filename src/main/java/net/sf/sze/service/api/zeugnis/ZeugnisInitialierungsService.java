//ZeugnisInitialierungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.api.zeugnis;

import net.sf.sze.model.zeugnis.ZeugnisFormular;
import net.sf.sze.util.ResultContainer;

/**
 * Service zum Initialisieren der Zeugnisse.
 *
 */
public interface ZeugnisInitialierungsService {

    /**
     * Initialisiert die Zeugniss zum Formular.
     * @param formular das Zeugnisformular.
     * @return Meldungen.
     */
    ResultContainer initZeugnis(ZeugnisFormular formular);

}
