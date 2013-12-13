package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;
import java.sql.Timestamp;

public class SCHUELERRowBuilder extends DataRowBuilder {

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

    public SCHUELERRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_GEBURTSTAG, new Timestamp(0));
            with(C_NAME, "");
            with(C_VERSION, new Long("0"));
            with(C_VORNAME, "");
            with(C_GESCHLECHT, new Integer("0"));
            with(C_KLASSE_ID, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_GEBURTSORT, "");
        }
    }

    public final SCHUELERRowBuilder ABGANGS_DATUM (Timestamp value) {
        with(C_ABGANGS_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder ABGANGS_DATUM (Validator<?> value) {
        with(C_ABGANGS_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder AUFNAHME_DATUM (Timestamp value) {
        with(C_AUFNAHME_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder AUFNAHME_DATUM (Validator<?> value) {
        with(C_AUFNAHME_DATUM, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSORT (String value) {
        with(C_GEBURTSORT, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSORT (Validator<?> value) {
        with(C_GEBURTSORT, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSTAG (Timestamp value) {
        with(C_GEBURTSTAG, value);
        return this;
    }

    public final SCHUELERRowBuilder GEBURTSTAG (Validator<?> value) {
        with(C_GEBURTSTAG, value);
        return this;
    }

    public final SCHUELERRowBuilder GESCHLECHT (Integer value) {
        with(C_GESCHLECHT, value);
        return this;
    }

    public final SCHUELERRowBuilder GESCHLECHT (Validator<?> value) {
        with(C_GESCHLECHT, value);
        return this;
    }

    public final SCHUELERRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder KLASSE_ID (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder KLASSE_ID (Validator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final SCHUELERRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHUELERRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHUELERRowBuilder NUMMER (Long value) {
        with(C_NUMMER, value);
        return this;
    }

    public final SCHUELERRowBuilder NUMMER (Validator<?> value) {
        with(C_NUMMER, value);
        return this;
    }

    public final SCHUELERRowBuilder RUFNAME (String value) {
        with(C_RUFNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder RUFNAME (Validator<?> value) {
        with(C_RUFNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHUELERRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHUELERRowBuilder VORNAME (String value) {
        with(C_VORNAME, value);
        return this;
    }

    public final SCHUELERRowBuilder VORNAME (Validator<?> value) {
        with(C_VORNAME, value);
        return this;
    }


    public static SCHUELERRowBuilder newSCHUELER(DataSetManipulator builder) {
        return new SCHUELERRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SCHUELERRowBuilder newSCHUELER(DataSetManipulator builder, String... identifierColumns) {
        return new SCHUELERRowBuilder(builder, true, identifierColumns);
    }

    public static SCHUELERRowBuilder newSCHUELER(DataSetManipulator builder, boolean initNotNullValues) {
        return new SCHUELERRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SCHUELERRowBuilder newSCHUELER(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SCHUELERRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
