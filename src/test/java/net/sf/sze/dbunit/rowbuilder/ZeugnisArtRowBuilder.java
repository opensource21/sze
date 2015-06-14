package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class ZeugnisArtRowBuilder extends BasicDataRowBuilder {

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

    public ZeugnisArtRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_PRINT_VERSETZUNGSBEMERKUNG, Boolean.FALSE);
        addDefaultValue(C_TITEL, "");
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_ABSCHLUSS_GRAD, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_PLATZ_FUER_SIEGEL, Boolean.FALSE);
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
        addDefaultValue(C_NOTE_ALS_TEXT_DARSTELLEN, Boolean.FALSE);
    }

    public final ZeugnisArtRowBuilder AbschlussGrad (String value) {
        with(C_ABSCHLUSS_GRAD, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Aktiv (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ZeugnisArtRowBuilder NoteAlsTextDarstellen (Boolean value) {
        with(C_NOTE_ALS_TEXT_DARSTELLEN, value);
        return this;
    }

    public final ZeugnisArtRowBuilder PlatzFuerSiegel (Boolean value) {
        with(C_PLATZ_FUER_SIEGEL, value);
        return this;
    }

    public final ZeugnisArtRowBuilder PrintVersetzungsbemerkung (Boolean value) {
        with(C_PRINT_VERSETZUNGSBEMERKUNG, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Sortierung (Number value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Titel (String value) {
        with(C_TITEL, value);
        return this;
    }

    public final ZeugnisArtRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static ZeugnisArtRowBuilder newZeugnisArt() {
        return new ZeugnisArtRowBuilder(PRIMARY_KEY);
    }

    public static ZeugnisArtRowBuilder newZeugnisArt(String... identifierColumns) {
        return new ZeugnisArtRowBuilder(identifierColumns);
    }

}
