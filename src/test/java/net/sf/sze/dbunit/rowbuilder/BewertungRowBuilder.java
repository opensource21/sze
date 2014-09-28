package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class BewertungRowBuilder extends BasicDataRowBuilder {

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

    public BewertungRowBuilder(String... identifierColumns) {
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

    public final BewertungRowBuilder Class (String value) {
        with(C_CLASS, value);
        return this;
    }

    public final BewertungRowBuilder Class (IValidator<?> value) {
        with(C_CLASS, value);
        return this;
    }

    public final BewertungRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BewertungRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BewertungRowBuilder Leistungsniveau (String value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BewertungRowBuilder Leistungsniveau (IValidator<?> value) {
        with(C_LEISTUNGSNIVEAU, value);
        return this;
    }

    public final BewertungRowBuilder LeistungNurSchwachAusreichend (Boolean value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BewertungRowBuilder LeistungNurSchwachAusreichend (IValidator<?> value) {
        with(C_LEISTUNG_NUR_SCHWACH_AUSREICHEND, value);
        return this;
    }

    public final BewertungRowBuilder Note (Long value) {
        with(C_NOTE, value);
        return this;
    }

    public final BewertungRowBuilder Note (IValidator<?> value) {
        with(C_NOTE, value);
        return this;
    }

    public final BewertungRowBuilder Relevant (Boolean value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BewertungRowBuilder Relevant (IValidator<?> value) {
        with(C_RELEVANT, value);
        return this;
    }

    public final BewertungRowBuilder SchulfachId (Long value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BewertungRowBuilder SchulfachId (IValidator<?> value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final BewertungRowBuilder SonderNote (String value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BewertungRowBuilder SonderNote (IValidator<?> value) {
        with(C_SONDER_NOTE, value);
        return this;
    }

    public final BewertungRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BewertungRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final BewertungRowBuilder ZeugnisId (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final BewertungRowBuilder ZeugnisId (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static BewertungRowBuilder newBewertung() {
        return new BewertungRowBuilder(PRIMARY_KEY);
    }

    public static BewertungRowBuilder newBewertung(String... identifierColumns) {
        return new BewertungRowBuilder(identifierColumns);
    }

}
