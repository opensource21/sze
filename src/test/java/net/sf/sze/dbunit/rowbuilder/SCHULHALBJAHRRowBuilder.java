package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class SCHULHALBJAHRRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "SCHULHALBJAHR";

    public static final String C_HALBJAHR = "HALBJAHR";
    public static final String C_ID = "ID";
    public static final String C_JAHR = "JAHR";
    public static final String C_SELECTABLE = "SELECTABLE";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public SCHULHALBJAHRRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_VERSION, new Long("0"));
            with(C_JAHR, new Integer("0"));
            with(C_ID, new Long("0"));
            with(C_SELECTABLE, Boolean.FALSE);
            with(C_HALBJAHR, new Integer("0"));
        }
    }

    public final SCHULHALBJAHRRowBuilder HALBJAHR (Integer value) {
        with(C_HALBJAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder HALBJAHR (Validator<?> value) {
        with(C_HALBJAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder JAHR (Integer value) {
        with(C_JAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder JAHR (Validator<?> value) {
        with(C_JAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder SELECTABLE (Boolean value) {
        with(C_SELECTABLE, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder SELECTABLE (Validator<?> value) {
        with(C_SELECTABLE, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR(DataSetManipulator builder) {
        return new SCHULHALBJAHRRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR(DataSetManipulator builder, String... identifierColumns) {
        return new SCHULHALBJAHRRowBuilder(builder, true, identifierColumns);
    }

    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR(DataSetManipulator builder, boolean initNotNullValues) {
        return new SCHULHALBJAHRRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SCHULHALBJAHRRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
