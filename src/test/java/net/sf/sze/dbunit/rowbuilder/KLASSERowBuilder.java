package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class KLASSERowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "KLASSE";

    public static final String C_GESCHLOSSEN = "GESCHLOSSEN";
    public static final String C_ID = "ID";
    public static final String C_JAHRGANG = "JAHRGANG";
    public static final String C_SUFFIX = "SUFFIX";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public KLASSERowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_JAHRGANG, new Integer("0"));
            with(C_VERSION, new Long("0"));
            with(C_SUFFIX, "");
            with(C_ID, new Long("0"));
            with(C_GESCHLOSSEN, Boolean.FALSE);
        }
    }

    public final KLASSERowBuilder GESCHLOSSEN (Boolean value) {
        with(C_GESCHLOSSEN, value);
        return this;
    }

    public final KLASSERowBuilder GESCHLOSSEN (Validator<?> value) {
        with(C_GESCHLOSSEN, value);
        return this;
    }

    public final KLASSERowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final KLASSERowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final KLASSERowBuilder JAHRGANG (Integer value) {
        with(C_JAHRGANG, value);
        return this;
    }

    public final KLASSERowBuilder JAHRGANG (Validator<?> value) {
        with(C_JAHRGANG, value);
        return this;
    }

    public final KLASSERowBuilder SUFFIX (String value) {
        with(C_SUFFIX, value);
        return this;
    }

    public final KLASSERowBuilder SUFFIX (Validator<?> value) {
        with(C_SUFFIX, value);
        return this;
    }

    public final KLASSERowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final KLASSERowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static KLASSERowBuilder newKLASSE(DataSetManipulator builder) {
        return new KLASSERowBuilder(builder, true, PRIMARY_KEY);
    }

    public static KLASSERowBuilder newKLASSE(DataSetManipulator builder, String... identifierColumns) {
        return new KLASSERowBuilder(builder, true, identifierColumns);
    }

    public static KLASSERowBuilder newKLASSE(DataSetManipulator builder, boolean initNotNullValues) {
        return new KLASSERowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static KLASSERowBuilder newKLASSE(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new KLASSERowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
