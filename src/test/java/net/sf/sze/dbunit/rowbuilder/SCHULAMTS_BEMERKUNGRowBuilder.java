package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SCHULAMTS_BEMERKUNGRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMTS_BEMERKUNG";

    public static final String C_ER_SIE_STATT_NAMEN = "ER_SIE_STATT_NAMEN";
    public static final String C_FREI_TEXT = "FREI_TEXT";
    public static final String C_ID = "ID";
    public static final String C_SCHULAMTS_BAUSTEIN_ID = "SCHULAMTS_BAUSTEIN_ID";
    public static final String C_SCHULAMT_ID = "SCHULAMT_ID";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ER_SIE_STATT_NAMEN, C_FREI_TEXT, C_ID, C_SCHULAMTS_BAUSTEIN_ID, C_SCHULAMT_ID, C_SORTIERUNG, C_VERSION, C_ZEUGNIS_ID};

    public SCHULAMTS_BEMERKUNGRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_SCHULAMT_ID, new Long("0"));
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
        addDefaultValue(C_ER_SIE_STATT_NAMEN, Boolean.FALSE);
        addDefaultValue(C_SCHULAMTS_BAUSTEIN_ID, new Long("0"));
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ER_SIE_STATT_NAMEN (Boolean value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ER_SIE_STATT_NAMEN (IValidator<?> value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder FREI_TEXT (String value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder FREI_TEXT (IValidator<?> value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SCHULAMTS_BAUSTEIN_ID (Long value) {
        with(C_SCHULAMTS_BAUSTEIN_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SCHULAMTS_BAUSTEIN_ID (IValidator<?> value) {
        with(C_SCHULAMTS_BAUSTEIN_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SCHULAMT_ID (Long value) {
        with(C_SCHULAMT_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SCHULAMT_ID (IValidator<?> value) {
        with(C_SCHULAMT_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder SORTIERUNG (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGRowBuilder ZEUGNIS_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static SCHULAMTS_BEMERKUNGRowBuilder newSCHULAMTS_BEMERKUNG() {
        return new SCHULAMTS_BEMERKUNGRowBuilder(PRIMARY_KEY);
    }

    public static SCHULAMTS_BEMERKUNGRowBuilder newSCHULAMTS_BEMERKUNG(String... identifierColumns) {
        return new SCHULAMTS_BEMERKUNGRowBuilder(identifierColumns);
    }

}
