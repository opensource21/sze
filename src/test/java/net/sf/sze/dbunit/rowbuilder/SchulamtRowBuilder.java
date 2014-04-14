package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SchulamtRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMT";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_BESCHREIBENDER_SATZ = "BESCHREIBENDER_SATZ";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_BESCHREIBENDER_SATZ, C_ID, C_NAME, C_VERSION};

    public SchulamtRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_BESCHREIBENDER_SATZ, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final SchulamtRowBuilder Aktiv (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SchulamtRowBuilder Aktiv (IValidator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SchulamtRowBuilder BeschreibenderSatz (String value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SchulamtRowBuilder BeschreibenderSatz (IValidator<?> value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SchulamtRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SchulamtRowBuilder Name (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SchulamtRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SchulamtRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SchulamtRowBuilder newSchulamt() {
        return new SchulamtRowBuilder(PRIMARY_KEY);
    }

    public static SchulamtRowBuilder newSchulamt(String... identifierColumns) {
        return new SchulamtRowBuilder(identifierColumns);
    }

}
