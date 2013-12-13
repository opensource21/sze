package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class SCHULAMTRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMT";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_BESCHREIBENDER_SATZ = "BESCHREIBENDER_SATZ";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_BESCHREIBENDER_SATZ, C_ID, C_NAME, C_VERSION};

    public SCHULAMTRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        if (initNotNullValues) {
            with(C_AKTIV, Boolean.FALSE);
            with(C_NAME, "");
            with(C_VERSION, new Long("0"));
            with(C_BESCHREIBENDER_SATZ, "");
            with(C_ID, new Long("0"));
        }
    }

    public final SCHULAMTRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTRowBuilder AKTIV (Validator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTRowBuilder BESCHREIBENDER_SATZ (String value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTRowBuilder BESCHREIBENDER_SATZ (Validator<?> value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULAMTRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULAMTRowBuilder newSCHULAMT(DataSetManipulator builder) {
        return new SCHULAMTRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SCHULAMTRowBuilder newSCHULAMT(DataSetManipulator builder, String... identifierColumns) {
        return new SCHULAMTRowBuilder(builder, true, identifierColumns);
    }

    public static SCHULAMTRowBuilder newSCHULAMT(DataSetManipulator builder, boolean initNotNullValues) {
        return new SCHULAMTRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SCHULAMTRowBuilder newSCHULAMT(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SCHULAMTRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
