package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SOLBEWERTUNGS_TEXTRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SOLBEWERTUNGS_TEXT";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_NAME, C_TEXT, C_VERSION};

    public SOLBEWERTUNGS_TEXTRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_TEXT, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder TEXT (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder TEXT (IValidator<?> value) {
        with(C_TEXT, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT() {
        return new SOLBEWERTUNGS_TEXTRowBuilder(PRIMARY_KEY);
    }

    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT(String... identifierColumns) {
        return new SOLBEWERTUNGS_TEXTRowBuilder(identifierColumns);
    }

}
