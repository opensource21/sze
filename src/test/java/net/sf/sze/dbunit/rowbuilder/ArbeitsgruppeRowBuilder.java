package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class ArbeitsgruppeRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "ARBEITSGRUPPE";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_KLASSENSTUFEN, C_NAME, C_SORTIERUNG, C_VERSION};

    public ArbeitsgruppeRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_KLASSENSTUFEN, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final ArbeitsgruppeRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final ArbeitsgruppeRowBuilder Klassenstufen (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ArbeitsgruppeRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ArbeitsgruppeRowBuilder Sortierung (Number value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ArbeitsgruppeRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static ArbeitsgruppeRowBuilder newArbeitsgruppe() {
        return new ArbeitsgruppeRowBuilder(PRIMARY_KEY);
    }

    public static ArbeitsgruppeRowBuilder newArbeitsgruppe(String... identifierColumns) {
        return new ArbeitsgruppeRowBuilder(identifierColumns);
    }

}
