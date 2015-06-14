package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import java.sql.Timestamp;

public class SchuelerRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHUELER";

    public static final String C_ABGANGS_DATUM = "ABGANGS_DATUM";
    public static final String C_AUFNAHME_DATUM = "AUFNAHME_DATUM";
    public static final String C_GEBURTSORT = "GEBURTSORT";
    public static final String C_GEBURTSTAG = "GEBURTSTAG";
    public static final String C_GESCHLECHT = "GESCHLECHT";
    public static final String C_ID = "ID";
    public static final String C_KLASSE_ID = "KLASSE_ID";
    public static final String C_NAME = "NAME";
    public static final String C_NUMMER = "NUMMER";
    public static final String C_RUFNAME = "RUFNAME";
    public static final String C_VERSION = "VERSION";
    public static final String C_VORNAME = "VORNAME";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ABGANGS_DATUM, C_AUFNAHME_DATUM, C_GEBURTSORT, C_GEBURTSTAG, C_GESCHLECHT, C_ID, C_KLASSE_ID, C_NAME, C_NUMMER, C_RUFNAME, C_VERSION, C_VORNAME};

    public SchuelerRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_GEBURTSTAG, new Timestamp(0));
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_VORNAME, "");
        addDefaultValue(C_GESCHLECHT, "");
        addDefaultValue(C_KLASSE_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_GEBURTSORT, "");
    }

    public final SchuelerRowBuilder AbgangsDatum (Timestamp value) {
        with(C_ABGANGS_DATUM, value);
        return this;
    }

    public final SchuelerRowBuilder AufnahmeDatum (Timestamp value) {
        with(C_AUFNAHME_DATUM, value);
        return this;
    }

    public final SchuelerRowBuilder Geburtsort (String value) {
        with(C_GEBURTSORT, value);
        return this;
    }

    public final SchuelerRowBuilder Geburtstag (Timestamp value) {
        with(C_GEBURTSTAG, value);
        return this;
    }

    public final SchuelerRowBuilder Geschlecht (String value) {
        with(C_GESCHLECHT, value);
        return this;
    }

    public final SchuelerRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final SchuelerRowBuilder KlasseId (Number value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final SchuelerRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SchuelerRowBuilder Nummer (Number value) {
        with(C_NUMMER, value);
        return this;
    }

    public final SchuelerRowBuilder Rufname (String value) {
        with(C_RUFNAME, value);
        return this;
    }

    public final SchuelerRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }

    public final SchuelerRowBuilder Vorname (String value) {
        with(C_VORNAME, value);
        return this;
    }


    public static SchuelerRowBuilder newSchueler() {
        return new SchuelerRowBuilder(PRIMARY_KEY);
    }

    public static SchuelerRowBuilder newSchueler(String... identifierColumns) {
        return new SchuelerRowBuilder(identifierColumns);
    }

}
