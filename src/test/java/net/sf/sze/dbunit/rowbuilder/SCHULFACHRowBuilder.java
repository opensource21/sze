package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class SCHULFACHRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "SCHULFACH";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_STUFEN_MIT_AUSSEN_DIFFERENZIERUNG = "STUFEN_MIT_AUSSEN_DIFFERENZIERUNG";
    public static final String C_STUFEN_MIT_BINNEN_DIFFERENZIERUNG = "STUFEN_MIT_BINNEN_DIFFERENZIERUNG";
    public static final String C_STUFEN_MIT_STANDARD_BEWERTUNG = "STUFEN_MIT_STANDARD_BEWERTUNG";
    public static final String C_TYP = "TYP";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_NAME, C_SORTIERUNG, C_STUFEN_MIT_AUSSEN_DIFFERENZIERUNG, C_STUFEN_MIT_BINNEN_DIFFERENZIERUNG, C_STUFEN_MIT_STANDARD_BEWERTUNG, C_TYP, C_VERSION};

    public SCHULFACHRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        if (initNotNullValues) {
            with(C_NAME, "");
            with(C_TYP, new Integer("0"));
            with(C_VERSION, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_SORTIERUNG, new Long("0"));
        }
    }

    public final SCHULFACHRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACHRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULFACHRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULFACHRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULFACHRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder SORTIERUNG (Validator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_AUSSEN_DIFFERENZIERUNG (String value) {
        with(C_STUFEN_MIT_AUSSEN_DIFFERENZIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_AUSSEN_DIFFERENZIERUNG (Validator<?> value) {
        with(C_STUFEN_MIT_AUSSEN_DIFFERENZIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_BINNEN_DIFFERENZIERUNG (String value) {
        with(C_STUFEN_MIT_BINNEN_DIFFERENZIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_BINNEN_DIFFERENZIERUNG (Validator<?> value) {
        with(C_STUFEN_MIT_BINNEN_DIFFERENZIERUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_STANDARD_BEWERTUNG (String value) {
        with(C_STUFEN_MIT_STANDARD_BEWERTUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder STUFEN_MIT_STANDARD_BEWERTUNG (Validator<?> value) {
        with(C_STUFEN_MIT_STANDARD_BEWERTUNG, value);
        return this;
    }

    public final SCHULFACHRowBuilder TYP (Integer value) {
        with(C_TYP, value);
        return this;
    }

    public final SCHULFACHRowBuilder TYP (Validator<?> value) {
        with(C_TYP, value);
        return this;
    }

    public final SCHULFACHRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULFACHRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULFACHRowBuilder newSCHULFACH(DataSetManipulator builder) {
        return new SCHULFACHRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static SCHULFACHRowBuilder newSCHULFACH(DataSetManipulator builder, String... identifierColumns) {
        return new SCHULFACHRowBuilder(builder, true, identifierColumns);
    }

    public static SCHULFACHRowBuilder newSCHULFACH(DataSetManipulator builder, boolean initNotNullValues) {
        return new SCHULFACHRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static SCHULFACHRowBuilder newSCHULFACH(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new SCHULFACHRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
