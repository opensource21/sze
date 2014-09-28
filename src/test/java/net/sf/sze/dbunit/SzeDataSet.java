//SzeDataSet.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.sql.SQLException;

import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.filter.ITableFilter;


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

    /**
     * Druckt die Datenbanktabelle in der Reihenfolge der Abhängigkeiten.
     * @param conn eine Datenbankverbindung.
     * @throws DataSetException Fehler im DataSet.
     * @throws SQLException Fehler von der Datenbank.
     */
    public static void printTableNames(IDatabaseConnection conn) throws DataSetException, SQLException {
        final ITableFilter filter = new DatabaseSequenceFilter(conn);
        final ITableIterator tables = filter.iterator(conn.createDataSet(), false);
        System.out.println("########### Datenbanktabellen");
        while (tables.next()) {
            System.out.print("\"" + tables.getTableMetaData().getTableName() + "\", ");
        }
        System.out.println();
    }

}
