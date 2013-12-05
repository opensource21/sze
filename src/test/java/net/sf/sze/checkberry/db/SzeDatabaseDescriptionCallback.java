//SzeDatabaseDescriptionCallback.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.checkberry.db;

import de.conceptpeople.checkerberry.db.bridge.context.Cacheable;
import de.conceptpeople.checkerberry.db.bridge.context.DatabaseDescription;
import de.conceptpeople.checkerberry.db.bridge.context.DatabaseDescriptionCallback;
import de.conceptpeople.checkerberry.db.bridge.context.DatabaseTableDescription;


/**
 * Beschreibung der Datenbanktabellen für den Test.
 *
 */
public class SzeDatabaseDescriptionCallback implements
        DatabaseDescriptionCallback {

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDatabaseDescription(
            DatabaseDescription databaseDescription) {
        //Schlüsseltabellen
        databaseDescription.addTableDescription("ARBEITSGRUPPE", Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("ARBEITS_UND_SOZIAL_VERHALTEN",
                Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("BEMERKUNGS_BAUSTEIN", Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("SCHULAMT", Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("SCHULAMTS_BEMERKUNGS_BAUSTEIN",
                Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("SCHULFACH", Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("SCHULFACH_DETAIL_INFO", Cacheable.Yes, "ID");
        databaseDescription.addTableDescription("ZEUGNIS_ART", Cacheable.Yes, "ID");


        //Stammdaten
        databaseDescription.addTableDescription("KLASSE", Cacheable.Yes, "JAHRGANG", "SUFFIX");
        databaseDescription.addTableDescription("SCHUELER", Cacheable.Yes, "ID");

        //Zeugnisdaten
        databaseDescription.addTableDescription("SCHULHALBJAHR", Cacheable.No, "ID");
        databaseDescription.addTableDescription("ZEUGNIS", Cacheable.No,
                "FORMULAR_ID", "KLASSE_ID", "SCHUELER_ID");
        databaseDescription.addTableDescription("ZEUGNIS_FORMULAR", Cacheable.No, "ID");

        //Bewertungen
        databaseDescription.addTableDescription("AG_BEWERTUNG", Cacheable.No,
                "ARBEITSGRUPPE_ID", "ZEUGNIS_ID");
        databaseDescription.addTableDescription("AV_SV_BEWERTUNG", Cacheable.No,
                "ARBEITS_UND_SOZIAL_VERHALTEN_ID", "ZEUGNIS_ID");
        databaseDescription.addTableDescription("BEWERTUNG", Cacheable.No,
                "SCHULFACH_ID", "ZEUGNIS_ID");

        databaseDescription.addTableDescription("BEMERKUNG", Cacheable.No, "ID");
        databaseDescription.addTableDescription("SCHULAMTS_BEMERKUNG", Cacheable.No, "ID");
        databaseDescription.addTableDescription("SOLBEWERTUNGS_TEXT", Cacheable.No, "ID");

        for (DatabaseTableDescription databaseTableDescription
                : databaseDescription.getTableDescriptions()) {
            databaseTableDescription.addExcludedColumns("VERSION");
        }
    }

}
