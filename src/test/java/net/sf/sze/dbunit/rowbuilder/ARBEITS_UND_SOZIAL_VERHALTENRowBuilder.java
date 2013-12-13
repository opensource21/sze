package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class ARBEITS_UND_SOZIAL_VERHALTENRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "ARBEITS_UND_SOZIAL_VERHALTEN";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_TYP = "TYP";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_NAME, "");
            with(C_KLASSENSTUFEN, "");
            with(C_TYP, new Integer("0"));
            with(C_VERSION, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_SORTIERUNG, new Long("0"));
        }
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder KLASSENSTUFEN (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder KLASSENSTUFEN (Validator<?> value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder SORTIERUNG (Validator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder TYP (Integer value) {
        with(C_TYP, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder TYP (Validator<?> value) {
        with(C_TYP, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ARBEITS_UND_SOZIAL_VERHALTENRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN(DataSetManipulator builder) {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN(DataSetManipulator builder, String... identifierColumns) {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(builder, true, identifierColumns);
    }

    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN(DataSetManipulator builder, boolean initNotNullValues) {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static ARBEITS_UND_SOZIAL_VERHALTENRowBuilder newARBEITS_UND_SOZIAL_VERHALTEN(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new ARBEITS_UND_SOZIAL_VERHALTENRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
