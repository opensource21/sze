package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class ARBEITS_UND_SOZIAL_VERHALTENRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "ARBEITS_UND_SOZIAL_VERHALTEN";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_TYP = "TYP";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_KLASSENSTUFEN, C_NAME, C_SORTIERUNG, C_TYP, C_VERSION};

    public ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_KLASSENSTUFEN, "");
        addDefaultValue(C_TYP, new Integer("0"));
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder KLASSENSTUFEN (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder KLASSENSTUFEN (IValidator<?> value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder SORTIERUNG (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder TYP (Integer value) {
        with(C_TYP, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder TYP (IValidator<?> value) {
        with(C_TYP, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN() {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(PRIMARY_KEY);
    }

    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN(String... identifierColumns) {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(identifierColumns);
    }

}
