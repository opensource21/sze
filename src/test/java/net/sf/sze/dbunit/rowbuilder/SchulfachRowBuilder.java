package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class SchulfachRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULFACH";

    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_STUFEN_MIT_DREI_NIVEAUS = "STUFEN_MIT_DREI_NIVEAUS";
    public static final String C_STUFEN_MIT_STANDARD_BEWERTUNG = "STUFEN_MIT_STANDARD_BEWERTUNG";
    public static final String C_STUFEN_MIT_ZWEI_NIVEAUS = "STUFEN_MIT_ZWEI_NIVEAUS";
    public static final String C_TYP = "TYP";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_NAME, C_SORTIERUNG, C_STUFEN_MIT_DREI_NIVEAUS, C_STUFEN_MIT_STANDARD_BEWERTUNG, C_STUFEN_MIT_ZWEI_NIVEAUS, C_TYP, C_VERSION};

    public SchulfachRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_TYP, new Integer("0"));
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final SchulfachRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final SchulfachRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SchulfachRowBuilder Sortierung (Number value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SchulfachRowBuilder StufenMitDreiNiveaus (String value) {
        with(C_STUFEN_MIT_DREI_NIVEAUS, value);
        return this;
    }

    public final SchulfachRowBuilder StufenMitStandardBewertung (String value) {
        with(C_STUFEN_MIT_STANDARD_BEWERTUNG, value);
        return this;
    }

    public final SchulfachRowBuilder StufenMitZweiNiveaus (String value) {
        with(C_STUFEN_MIT_ZWEI_NIVEAUS, value);
        return this;
    }

    public final SchulfachRowBuilder Typ (Number value) {
        with(C_TYP, value);
        return this;
    }

    public final SchulfachRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static SchulfachRowBuilder newSchulfach() {
        return new SchulfachRowBuilder(PRIMARY_KEY);
    }

    public static SchulfachRowBuilder newSchulfach(String... identifierColumns) {
        return new SchulfachRowBuilder(identifierColumns);
    }

}
