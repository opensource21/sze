package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class ZEUGNIS_ARTRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "ZEUGNIS_ART";

    public static final String C_ABSCHLUSS_GRAD = "ABSCHLUSS_GRAD";
    public static final String C_AKTIV = "AKTIV";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_NOTE_ALS_TEXT_DARSTELLEN = "NOTE_ALS_TEXT_DARSTELLEN";
    public static final String C_PLATZ_FUER_SIEGEL = "PLATZ_FUER_SIEGEL";
    public static final String C_PRINT_VERSETZUNGSBEMERKUNG = "PRINT_VERSETZUNGSBEMERKUNG";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_TITEL = "TITEL";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ABSCHLUSS_GRAD, C_AKTIV, C_ID, C_NAME, C_NOTE_ALS_TEXT_DARSTELLEN, C_PLATZ_FUER_SIEGEL, C_PRINT_VERSETZUNGSBEMERKUNG, C_SORTIERUNG, C_TITEL, C_VERSION};

    public ZEUGNIS_ARTRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        if (initNotNullValues) {
            with(C_PRINT_VERSETZUNGSBEMERKUNG, Boolean.FALSE);
            with(C_TITEL, "");
            with(C_AKTIV, Boolean.FALSE);
            with(C_NAME, "");
            with(C_ABSCHLUSS_GRAD, "");
            with(C_VERSION, new Long("0"));
            with(C_PLATZ_FUER_SIEGEL, Boolean.FALSE);
            with(C_ID, new Long("0"));
            with(C_SORTIERUNG, new Long("0"));
            with(C_NOTE_ALS_TEXT_DARSTELLEN, Boolean.FALSE);
        }
    }

    public final ZEUGNIS_ARTRowBuilder ABSCHLUSS_GRAD (String value) {
        with(C_ABSCHLUSS_GRAD, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder ABSCHLUSS_GRAD (Validator<?> value) {
        with(C_ABSCHLUSS_GRAD, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder AKTIV (Validator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder NAME (Validator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder NOTE_ALS_TEXT_DARSTELLEN (Boolean value) {
        with(C_NOTE_ALS_TEXT_DARSTELLEN, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder NOTE_ALS_TEXT_DARSTELLEN (Validator<?> value) {
        with(C_NOTE_ALS_TEXT_DARSTELLEN, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder PLATZ_FUER_SIEGEL (Boolean value) {
        with(C_PLATZ_FUER_SIEGEL, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder PLATZ_FUER_SIEGEL (Validator<?> value) {
        with(C_PLATZ_FUER_SIEGEL, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder PRINT_VERSETZUNGSBEMERKUNG (Boolean value) {
        with(C_PRINT_VERSETZUNGSBEMERKUNG, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder PRINT_VERSETZUNGSBEMERKUNG (Validator<?> value) {
        with(C_PRINT_VERSETZUNGSBEMERKUNG, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder SORTIERUNG (Validator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder TITEL (String value) {
        with(C_TITEL, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder TITEL (Validator<?> value) {
        with(C_TITEL, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZEUGNIS_ARTRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ZEUGNIS_ARTRowBuilder newZEUGNIS_ART(DataSetManipulator builder) {
        return new ZEUGNIS_ARTRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static ZEUGNIS_ARTRowBuilder newZEUGNIS_ART(DataSetManipulator builder, String... identifierColumns) {
        return new ZEUGNIS_ARTRowBuilder(builder, true, identifierColumns);
    }

    public static ZEUGNIS_ARTRowBuilder newZEUGNIS_ART(DataSetManipulator builder, boolean initNotNullValues) {
        return new ZEUGNIS_ARTRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static ZEUGNIS_ARTRowBuilder newZEUGNIS_ART(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new ZEUGNIS_ARTRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
