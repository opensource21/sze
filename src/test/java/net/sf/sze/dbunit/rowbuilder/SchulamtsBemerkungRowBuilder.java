package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SchulamtsBemerkungRowBuilder extends BasicDataRowBuilder {

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

    public SchulamtsBemerkungRowBuilder(String... identifierColumns) {
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

    public final SchulamtsBemerkungRowBuilder ErSieStattNamen (Boolean value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder ErSieStattNamen (IValidator<?> value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder FreiText (String value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder FreiText (IValidator<?> value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder SchulamtsBausteinId (Long value) {
        with(C_SCHULAMTS_BAUSTEIN_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder SchulamtsBausteinId (IValidator<?> value) {
        with(C_SCHULAMTS_BAUSTEIN_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder SchulamtId (Long value) {
        with(C_SCHULAMT_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder SchulamtId (IValidator<?> value) {
        with(C_SCHULAMT_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Sortierung (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Sortierung (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder ZeugnisId (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final SchulamtsBemerkungRowBuilder ZeugnisId (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static SchulamtsBemerkungRowBuilder newSchulamtsBemerkung() {
        return new SchulamtsBemerkungRowBuilder(PRIMARY_KEY);
    }

    public static SchulamtsBemerkungRowBuilder newSchulamtsBemerkung(String... identifierColumns) {
        return new SchulamtsBemerkungRowBuilder(identifierColumns);
    }

}
