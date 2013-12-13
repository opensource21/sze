package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class BEMERKUNGS_BAUSTEINRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "BEMERKUNGS_BAUSTEIN";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public BEMERKUNGS_BAUSTEINRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_AKTIV, Boolean.FALSE);
            with(C_NAME, "");
            with(C_VERSION, new Long("0"));
            with(C_TEXT, "");
            with(C_ID, new Long("0"));
        }
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder AKTIV (Validator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder TEXT (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder TEXT (Validator<?> value) {
        with(C_TEXT, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEMERKUNGS_BAUSTEINRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN(DataSetManipulator builder) {
        return new BEMERKUNGS_BAUSTEINRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN(DataSetManipulator builder, String... identifierColumns) {
        return new BEMERKUNGS_BAUSTEINRowBuilder(builder, true, identifierColumns);
    }

    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN(DataSetManipulator builder, boolean initNotNullValues) {
        return new BEMERKUNGS_BAUSTEINRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static BEMERKUNGS_BAUSTEINRowBuilder newBEMERKUNGS_BAUSTEIN(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new BEMERKUNGS_BAUSTEINRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
