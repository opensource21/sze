package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class SolbewertungsTextRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SOLBEWERTUNGS_TEXT";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_NAME, C_TEXT, C_VERSION};

    public SolbewertungsTextRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_TEXT, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final SolbewertungsTextRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final SolbewertungsTextRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SolbewertungsTextRowBuilder Text (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final SolbewertungsTextRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static SolbewertungsTextRowBuilder newSolbewertungsText() {
        return new SolbewertungsTextRowBuilder(PRIMARY_KEY);
    }

    public static SolbewertungsTextRowBuilder newSolbewertungsText(String... identifierColumns) {
        return new SolbewertungsTextRowBuilder(identifierColumns);
    }

}
