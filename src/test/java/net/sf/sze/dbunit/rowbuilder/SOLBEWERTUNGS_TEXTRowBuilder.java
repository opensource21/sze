package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class SOLBEWERTUNGS_TEXTRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "SOLBEWERTUNGS_TEXT";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_TEXT = "TEXT";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public SOLBEWERTUNGS_TEXTRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_NAME, "");
            with(C_VERSION, new Long("0"));
            with(C_TEXT, "");
            with(C_ID, new Long("0"));
        }
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder TEXT (String value) {
        with(C_TEXT, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder TEXT (Validator<?> value) {
        with(C_TEXT, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SOLBEWERTUNGS_TEXTRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT(DataSetManipulator builder) {
        return new SOLBEWERTUNGS_TEXTRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT(DataSetManipulator builder, String... identifierColumns) {
        return new SOLBEWERTUNGS_TEXTRowBuilder(builder, true, identifierColumns);
    }

    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT(DataSetManipulator builder, boolean initNotNullValues) {
        return new SOLBEWERTUNGS_TEXTRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SOLBEWERTUNGS_TEXTRowBuilder newSOLBEWERTUNGS_TEXT(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SOLBEWERTUNGS_TEXTRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
