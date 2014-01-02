package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class BEMERKUNGS_BAUSTEINRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "BEMERKUNGS_BAUSTEIN";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_ID, C_NAME, C_TEXT, C_VERSION};

    public BEMERKUNGS_BAUSTEINRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_TEXT, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder AKTIV (IValidator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder TEXT (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder TEXT (IValidator<?> value) {
        with(C_TEXT, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN() {
        return new BEMERKUNGS_BAUSTEINRowBuilder(PRIMARY_KEY);
    }

    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN(String... identifierColumns) {
        return new BEMERKUNGS_BAUSTEINRowBuilder(identifierColumns);
    }

}
