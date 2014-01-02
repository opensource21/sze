package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SCHULFACH_DETAIL_INFORowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULFACH_DETAIL_INFO";

    public static final String C_DETAIL_INFO = "DETAIL_INFO";
    public static final String C_FORMULAR_ID = "FORMULAR_ID";
    public static final String C_ID = "ID";
    public static final String C_SCHULFACH_ID = "SCHULFACH_ID";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_DETAIL_INFO, C_FORMULAR_ID, C_ID, C_SCHULFACH_ID, C_VERSION};

    public SCHULFACH_DETAIL_INFORowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_DETAIL_INFO, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_SCHULFACH_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_FORMULAR_ID, new Long("0"));
    }

    public final SCHULFACH_DETAIL_INFORowBuilder DETAIL_INFO (String value) {
        with(C_DETAIL_INFO, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder DETAIL_INFO (IValidator<?> value) {
        with(C_DETAIL_INFO, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder FORMULAR_ID (Long value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder FORMULAR_ID (IValidator<?> value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder SCHULFACH_ID (Long value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder SCHULFACH_ID (IValidator<?> value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO() {
        return new SCHULFACH_DETAIL_INFORowBuilder(PRIMARY_KEY);
    }

    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO(String... identifierColumns) {
        return new SCHULFACH_DETAIL_INFORowBuilder(identifierColumns);
    }

}
