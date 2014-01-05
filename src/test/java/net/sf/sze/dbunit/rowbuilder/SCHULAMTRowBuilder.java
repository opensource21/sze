package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SCHULAMTRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMT";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_BESCHREIBENDER_SATZ = "BESCHREIBENDER_SATZ";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_BESCHREIBENDER_SATZ, C_ID, C_NAME, C_VERSION};

    public SCHULAMTRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_BESCHREIBENDER_SATZ, "");
        addDefaultValue(C_ID, new Long("0"));
    }

    public final SCHULAMTRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTRowBuilder AKTIV (IValidator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTRowBuilder BESCHREIBENDER_SATZ (String value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTRowBuilder BESCHREIBENDER_SATZ (IValidator<?> value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULAMTRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULAMTRowBuilder newSCHULAMT() {
        return new SCHULAMTRowBuilder(PRIMARY_KEY);
    }

    public static SCHULAMTRowBuilder newSCHULAMT(String... identifierColumns) {
        return new SCHULAMTRowBuilder(identifierColumns);
    }

}
