package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class BEWERTUNGRowBuilder extends BasicDataRowBuilder {

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

    public static final String[] ALL_COLUMNS = {C_CLASS, C_ID, C_LEISTUNGSNIVEAU, C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, C_NOTE, C_RELEVANT, C_SCHULFACH_ID, C_SONDER_NOTE, C_VERSION, C_ZEUGNIS_ID};

    public BEWERTUNGRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_CLASS, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_SCHULFACH_ID, new Long("0"));
        addDefaultValue(C_RELEVANT, Boolean.FALSE);
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, Boolean.FALSE);
        addDefaultValue(C_SONDER_NOTE, "");
    }

    public final BEWERTUNGRowBuilder CLASS (String value) {
        with(C_CLASS, value);
        return this;
    }

    public final BEWERTUNGRowBuilder CLASS (IValidator<?> value) {
        with(C_CLASS, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNGSNIVEAU (String value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNGSNIVEAU (IValidator<?> value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNG_NUR_SCHWACH_AUSREICHEND (Boolean value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BEWERTUNGRowBuilder LEISTUNG_NUR_SCHWACH_AUSREICHEND (IValidator<?> value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BEWERTUNGRowBuilder NOTE (Long value) {
        with(C_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder NOTE (IValidator<?> value) {
        with(C_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder RELEVANT (Boolean value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BEWERTUNGRowBuilder RELEVANT (IValidator<?> value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SCHULFACH_ID (Long value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SCHULFACH_ID (IValidator<?> value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SONDER_NOTE (String value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder SONDER_NOTE (IValidator<?> value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEWERTUNGRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final BEWERTUNGRowBuilder ZEUGNIS_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static BEWERTUNGRowBuilder newBEWERTUNG() {
        return new BEWERTUNGRowBuilder(PRIMARY_KEY);
    }

    public static BEWERTUNGRowBuilder newBEWERTUNG(String... identifierColumns) {
        return new BEWERTUNGRowBuilder(identifierColumns);
    }

}
