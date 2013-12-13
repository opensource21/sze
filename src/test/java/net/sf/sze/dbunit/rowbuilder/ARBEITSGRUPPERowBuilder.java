package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class ARBEITSGRUPPERowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "ARBEITSGRUPPE";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_KLASSENSTUFEN, C_NAME, C_SORTIERUNG, C_VERSION};

    public ARBEITSGRUPPERowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        if (initNotNullValues) {
            with(C_NAME, "");
            with(C_KLASSENSTUFEN, "");
            with(C_VERSION, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_SORTIERUNG, new Long("0"));
        }
    }

    public final ARBEITSGRUPPERowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder KLASSENSTUFEN (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder KLASSENSTUFEN (Validator<?> value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder SORTIERUNG (Validator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE(DataSetManipulator builder) {
        return new ARBEITSGRUPPERowBuilder(builder, true, PRIMARY_KEY);
    }

    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE(DataSetManipulator builder, String... identifierColumns) {
        return new ARBEITSGRUPPERowBuilder(builder, true, identifierColumns);
    }

    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE(DataSetManipulator builder, boolean initNotNullValues) {
        return new ARBEITSGRUPPERowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new ARBEITSGRUPPERowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
