package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class BemerkungRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "BEMERKUNG";

    public static final String C_BAUSTEIN_ID = "BAUSTEIN_ID";
    public static final String C_ER_SIE_STATT_NAMEN = "ER_SIE_STATT_NAMEN";
    public static final String C_FREI_TEXT = "FREI_TEXT";
    public static final String C_ID = "ID";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_BAUSTEIN_ID, C_ER_SIE_STATT_NAMEN, C_FREI_TEXT, C_ID, C_SORTIERUNG, C_VERSION, C_ZEUGNIS_ID};

    public BemerkungRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
        addDefaultValue(C_ER_SIE_STATT_NAMEN, Boolean.FALSE);
        addDefaultValue(C_BAUSTEIN_ID, new Long("0"));
    }

    public final BemerkungRowBuilder BausteinId (Number value) {
        with(C_BAUSTEIN_ID, value);
        return this;
    }

    public final BemerkungRowBuilder ErSieStattNamen (Boolean value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final BemerkungRowBuilder FreiText (String value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final BemerkungRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final BemerkungRowBuilder Sortierung (Number value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final BemerkungRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }

    public final BemerkungRowBuilder ZeugnisId (Number value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static BemerkungRowBuilder newBemerkung() {
        return new BemerkungRowBuilder(PRIMARY_KEY);
    }

    public static BemerkungRowBuilder newBemerkung(String... identifierColumns) {
        return new BemerkungRowBuilder(identifierColumns);
    }

}
