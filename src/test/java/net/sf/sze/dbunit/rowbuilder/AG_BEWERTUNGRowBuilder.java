package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class AG_BEWERTUNGRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "AG_BEWERTUNG";

    public static final String C_ARBEITSGRUPPE_ID = "ARBEITSGRUPPE_ID";
    public static final String C_ID = "ID";
    public static final String C_TEILGENOMMEN = "TEILGENOMMEN";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ARBEITSGRUPPE_ID, C_ID, C_TEILGENOMMEN, C_VERSION, C_ZEUGNIS_ID};

    public AG_BEWERTUNGRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_TEILGENOMMEN, Boolean.FALSE);
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_ARBEITSGRUPPE_ID, new Long("0"));
    }

    public final AG_BEWERTUNGRowBuilder ARBEITSGRUPPE_ID (Long value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ARBEITSGRUPPE_ID (IValidator<?> value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder TEILGENOMMEN (Boolean value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder TEILGENOMMEN (IValidator<?> value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ZEUGNIS_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG() {
        return new AG_BEWERTUNGRowBuilder(PRIMARY_KEY);
    }

    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG(String... identifierColumns) {
        return new AG_BEWERTUNGRowBuilder(identifierColumns);
    }

}
