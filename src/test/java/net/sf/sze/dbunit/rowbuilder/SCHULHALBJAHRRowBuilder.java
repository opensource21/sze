package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SCHULHALBJAHRRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULHALBJAHR";

    public static final String C_HALBJAHR = "HALBJAHR";
    public static final String C_ID = "ID";
    public static final String C_JAHR = "JAHR";
    public static final String C_SELECTABLE = "SELECTABLE";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_HALBJAHR, C_ID, C_JAHR, C_SELECTABLE, C_VERSION};

    public SCHULHALBJAHRRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_JAHR, new Integer("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SELECTABLE, Boolean.FALSE);
        addDefaultValue(C_HALBJAHR, new Integer("0"));
    }

    public final SCHULHALBJAHRRowBuilder HALBJAHR (Integer value) {
        with(C_HALBJAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder HALBJAHR (IValidator<?> value) {
        with(C_HALBJAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder JAHR (Integer value) {
        with(C_JAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder JAHR (IValidator<?> value) {
        with(C_JAHR, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder SELECTABLE (Boolean value) {
        with(C_SELECTABLE, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder SELECTABLE (IValidator<?> value) {
        with(C_SELECTABLE, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULHALBJAHRRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR() {
        return new SCHULHALBJAHRRowBuilder(PRIMARY_KEY);
    }

    public static SCHULHALBJAHRRowBuilder newSCHULHALBJAHR(String... identifierColumns) {
        return new SCHULHALBJAHRRowBuilder(identifierColumns);
    }

}
