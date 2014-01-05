//SzeDataSet.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;


/**
 * Dataset welches die Sortierung für SZE-Db übernimmt.
 *
 */
public class SzeDataSet extends FilteredDataSet {


    private static final String[] TABLE_NAMES =
            {"ARBEITSGRUPPE", "ARBEITS_UND_SOZIAL_VERHALTEN",
            "BEMERKUNGS_BAUSTEIN", "KLASSE", "SCHUELER", "SCHULAMT",
            "SCHULAMTS_BEMERKUNGS_BAUSTEIN", "SCHULFACH",
            "SCHULHALBJAHR", "SOLBEWERTUNGS_TEXT", "VERSION_HISTORY",
            "ZEUGNIS_ART", "ZEUGNIS_FORMULAR", "SCHULFACH_DETAIL_INFO",
            "ZEUGNIS", "AG_BEWERTUNG", "AV_SV_BEWERTUNG", "BEMERKUNG",
            "BEWERTUNG", "SCHULAMTS_BEMERKUNG" };

    /**
     * Initiates an object of type SzeDataSet.
     * @param tableNames
     * @param dataSet
     * @throws AmbiguousTableNameException
     */
    public SzeDataSet(IDataSet dataSet)
            throws AmbiguousTableNameException {
        super(TABLE_NAMES, dataSet);
    }

}
