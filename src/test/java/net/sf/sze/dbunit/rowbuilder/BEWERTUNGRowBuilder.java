package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class BEWERTUNGRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "BEWERTUNG";

    public static final String C_CLASS = "CLASS";
    public static final String C_ID = "ID";
    public static final String C_LEISTUNGSNIVEAU = "LEISTUNGSNIVEAU";
    public static final String C_LEISTUNG_NUR_SCHWACH_AUSREICHEND = "LEISTUNG_NUR_SCHWACH_AUSREICHEND";
    public static final String C_NOTE = "NOTE";
    public static final String C_RELEVANT = "RELEVANT";
    public static final String C_SCHULFACH_ID = "SCHULFACH_ID";
    public static final String C_SONDER_NOTE = "SONDER_NOTE";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public BEWERTUNGRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_CLASS, "");
            with(C_VERSION, new Long("0"));
            with(C_ZEUGNIS_ID, new Long("0"));
            with(C_SCHULFACH_ID, new Long("0"));
            with(C_RELEVANT, Boolean.FALSE);
            with(C_ID, new Long("0"));
            with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, Boolean.FALSE);
            with(C_SONDER_NOTE, "");
        }
    }

    public final BEWERTUNGRowBuilder CLASS (String value) {
        with(C_CLASS, value);
        return this;
    }

    public final BEWERTUNGRowBuilder CLASS (Validator<?> value) {
        with(C_CLASS, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNGSNIVEAU (String value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNGSNIVEAU (Validator<?> value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNG_NUR_SCHWACH_AUSREICHEND (Boolean value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNG_NUR_SCHWACH_AUSREICHEND (Validator<?> value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BEWERTUNGRowBuilder NOTE (Long value) {
        with(C_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder NOTE (Validator<?> value) {
        with(C_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder RELEVANT (Boolean value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BEWERTUNGRowBuilder RELEVANT (Validator<?> value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SCHULFACH_ID (Long value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SCHULFACH_ID (Validator<?> value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SONDER_NOTE (String value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SONDER_NOTE (Validator<?> value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEWERTUNGRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ZEUGNIS_ID (Validator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static BEWERTUNGRowBuilder newBEWERTUNG(DataSetManipulator builder) {
        return new BEWERTUNGRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static BEWERTUNGRowBuilder newBEWERTUNG(DataSetManipulator builder, String... identifierColumns) {
        return new BEWERTUNGRowBuilder(builder, true, identifierColumns);
    }

    public static BEWERTUNGRowBuilder newBEWERTUNG(DataSetManipulator builder, boolean initNotNullValues) {
        return new BEWERTUNGRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static BEWERTUNGRowBuilder newBEWERTUNG(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new BEWERTUNGRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
