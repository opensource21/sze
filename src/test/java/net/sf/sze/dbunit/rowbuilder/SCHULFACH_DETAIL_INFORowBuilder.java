package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class SCHULFACH_DETAIL_INFORowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "SCHULFACH_DETAIL_INFO";

    public static final String C_DETAIL_INFO = "DETAIL_INFO";
    public static final String C_FORMULAR_ID = "FORMULAR_ID";
    public static final String C_ID = "ID";
    public static final String C_SCHULFACH_ID = "SCHULFACH_ID";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public SCHULFACH_DETAIL_INFORowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_DETAIL_INFO, "");
            with(C_VERSION, new Long("0"));
            with(C_SCHULFACH_ID, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_FORMULAR_ID, new Long("0"));
        }
    }

    public final SCHULFACH_DETAIL_INFORowBuilder DETAIL_INFO (String value) {
        with(C_DETAIL_INFO, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder DETAIL_INFO (Validator<?> value) {
        with(C_DETAIL_INFO, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder FORMULAR_ID (Long value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder FORMULAR_ID (Validator<?> value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder SCHULFACH_ID (Long value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder SCHULFACH_ID (Validator<?> value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULFACH_DETAIL_INFORowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO(DataSetManipulator builder) {
        return new SCHULFACH_DETAIL_INFORowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO(DataSetManipulator builder, String... identifierColumns) {
        return new SCHULFACH_DETAIL_INFORowBuilder(builder, true, identifierColumns);
    }

    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO(DataSetManipulator builder, boolean initNotNullValues) {
        return new SCHULFACH_DETAIL_INFORowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SCHULFACH_DETAIL_INFORowBuilder newSCHULFACH_DETAIL_INFO(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SCHULFACH_DETAIL_INFORowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
