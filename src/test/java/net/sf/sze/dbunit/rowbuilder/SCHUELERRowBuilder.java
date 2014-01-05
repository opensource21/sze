package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;
import java.sql.Timestamp;

public class SCHUELERRowBuilder extends BasicDataRowBuilder {

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

    public SCHUELERRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_GEBURTSTAG, new Timestamp(0));
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_VORNAME, "");
        addDefaultValue(C_GESCHLECHT, new Integer("0"));
        addDefaultValue(C_KLASSE_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_GEBURTSORT, "");
    }

    public final SCHUELERRowBuilder ABGANGS_DATUM (Timestamp value) {
        with(C_ABGANGS_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder ABGANGS_DATUM (IValidator<?> value) {
        with(C_ABGANGS_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder AUFNAHME_DATUM (Timestamp value) {
        with(C_AUFNAHME_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder AUFNAHME_DATUM (IValidator<?> value) {
        with(C_AUFNAHME_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSORT (String value) {
        with(C_GEBURTSORT, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSORT (IValidator<?> value) {
        with(C_GEBURTSORT, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSTAG (Timestamp value) {
        with(C_GEBURTSTAG, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSTAG (IValidator<?> value) {
        with(C_GEBURTSTAG, value);
        return this;
    }

    public final SCHUELERRowBuilder GESCHLECHT (Integer value) {
        with(C_GESCHLECHT, value);
        return this;
    }

    public final SCHUELERRowBuilder GESCHLECHT (IValidator<?> value) {
        with(C_GESCHLECHT, value);
        return this;
    }

    public final SCHUELERRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder KLASSE_ID (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder KLASSE_ID (IValidator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHUELERRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHUELERRowBuilder NUMMER (Long value) {
        with(C_NUMMER, value);
        return this;
    }

    public final SCHUELERRowBuilder NUMMER (IValidator<?> value) {
        with(C_NUMMER, value);
        return this;
    }

    public final SCHUELERRowBuilder RUFNAME (String value) {
        with(C_RUFNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder RUFNAME (IValidator<?> value) {
        with(C_RUFNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHUELERRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHUELERRowBuilder VORNAME (String value) {
        with(C_VORNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder VORNAME (IValidator<?> value) {
        with(C_VORNAME, value);
        return this;
    }


    public static SCHUELERRowBuilder newSCHUELER() {
        return new SCHUELERRowBuilder(PRIMARY_KEY);
    }

    public static SCHUELERRowBuilder newSCHUELER(String... identifierColumns) {
        return new SCHUELERRowBuilder(identifierColumns);
    }

}
