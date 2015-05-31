package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class ArbeitsUndSozialVerhaltenRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "ARBEITS_UND_SOZIAL_VERHALTEN";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_TYP = "TYP";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_KLASSENSTUFEN, C_NAME, C_SORTIERUNG, C_TYP, C_VERSION};

    public ArbeitsUndSozialVerhaltenRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_KLASSENSTUFEN, "");
        addDefaultValue(C_TYP, new Integer("0"));
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Klassenstufen (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Sortierung (Number value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Typ (Number value) {
        with(C_TYP, value);
        return this;
    }

    public final ArbeitsUndSozialVerhaltenRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static ArbeitsUndSozialVerhaltenRowBuilder newArbeitsUndSozialVerhalten() {
        return new ArbeitsUndSozialVerhaltenRowBuilder(PRIMARY_KEY);
    }

    public static ArbeitsUndSozialVerhaltenRowBuilder newArbeitsUndSozialVerhalten(String... identifierColumns) {
        return new ArbeitsUndSozialVerhaltenRowBuilder(identifierColumns);
    }

}
