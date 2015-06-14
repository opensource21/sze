package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class BemerkungsBausteinRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "BEMERKUNGS_BAUSTEIN";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_ID, C_NAME, C_TEXT, C_VERSION};

    public BemerkungsBausteinRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_TEXT, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final BemerkungsBausteinRowBuilder Aktiv (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final BemerkungsBausteinRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final BemerkungsBausteinRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final BemerkungsBausteinRowBuilder Text (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final BemerkungsBausteinRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static BemerkungsBausteinRowBuilder newBemerkungsBaustein() {
        return new BemerkungsBausteinRowBuilder(PRIMARY_KEY);
    }

    public static BemerkungsBausteinRowBuilder newBemerkungsBaustein(String... identifierColumns) {
        return new BemerkungsBausteinRowBuilder(identifierColumns);
    }

}
