package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;
import java.sql.Timestamp;

public class ZEUGNISRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "ZEUGNIS";

    public static final String C_ANZAHL_FEHLTAGE_GESAMT = "ANZAHL_FEHLTAGE_GESAMT";
    public static final String C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT = "ANZAHL_FEHLTAGE_UNENTSCHULDIGT";
    public static final String C_ANZAHL_VERSPAETUNGEN = "ANZAHL_VERSPAETUNGEN";
    public static final String C_BU_BEWERTUNGS_TEXT = "BU_BEWERTUNGS_TEXT";
    public static final String C_FORMULAR_ID = "FORMULAR_ID";
    public static final String C_ID = "ID";
    public static final String C_INDIVIDUELLER_LEITSPRUCH = "INDIVIDUELLER_LEITSPRUCH";
    public static final String C_INDIVIDUELLER_LEITSPRUCH2 = "INDIVIDUELLER_LEITSPRUCH2";
    public static final String C_INDIVIDUELLES_AUSGABE_DATUM = "INDIVIDUELLES_AUSGABE_DATUM";
    public static final String C_KLASSEN_ZIEL_AUSGESCHLOSSEN = "KLASSEN_ZIEL_AUSGESCHLOSSEN";
    public static final String C_KLASSEN_ZIEL_GEFAEHRDET = "KLASSEN_ZIEL_GEFAEHRDET";
    public static final String C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT = "KLASSEN_ZIEL_WURDE_NICHT_ERREICHT";
    public static final String C_KLASSE_ID = "KLASSE_ID";
    public static final String C_QUELLE_INDIVIDUELLER_LEITSPRUCH = "QUELLE_INDIVIDUELLER_LEITSPRUCH";
    public static final String C_QUELLE_INDIVIDUELLER_LEITSPRUCH2 = "QUELLE_INDIVIDUELLER_LEITSPRUCH2";
    public static final String C_RUECKT_AUF = "RUECKT_AUF";
    public static final String C_SCHUELER_ID = "SCHUELER_ID";
    public static final String C_SCHULHALBJAHR_ID = "SCHULHALBJAHR_ID";
    public static final String C_SOLBEWERTUNGS_TEXT_ID = "SOLBEWERTUNGS_TEXT_ID";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ART_ID = "ZEUGNIS_ART_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ANZAHL_FEHLTAGE_GESAMT, C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, C_ANZAHL_VERSPAETUNGEN, C_BU_BEWERTUNGS_TEXT, C_FORMULAR_ID, C_ID, C_INDIVIDUELLER_LEITSPRUCH, C_INDIVIDUELLER_LEITSPRUCH2, C_INDIVIDUELLES_AUSGABE_DATUM, C_KLASSEN_ZIEL_AUSGESCHLOSSEN, C_KLASSEN_ZIEL_GEFAEHRDET, C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, C_KLASSE_ID, C_QUELLE_INDIVIDUELLER_LEITSPRUCH, C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, C_RUECKT_AUF, C_SCHUELER_ID, C_SCHULHALBJAHR_ID, C_SOLBEWERTUNGS_TEXT_ID, C_VERSION, C_ZEUGNIS_ART_ID};

    public ZEUGNISRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        if (initNotNullValues) {
            with(C_ANZAHL_FEHLTAGE_GESAMT, new Integer("0"));
            with(C_ANZAHL_VERSPAETUNGEN, new Integer("0"));
            with(C_ZEUGNIS_ART_ID, new Long("0"));
            with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, new Integer("0"));
            with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, Boolean.FALSE);
            with(C_FORMULAR_ID, new Long("0"));
            with(C_KLASSEN_ZIEL_GEFAEHRDET, Boolean.FALSE);
            with(C_RUECKT_AUF, Boolean.FALSE);
            with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, Boolean.FALSE);
            with(C_VERSION, new Long("0"));
            with(C_SCHULHALBJAHR_ID, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_KLASSE_ID, new Long("0"));
            with(C_SCHUELER_ID, new Long("0"));
        }
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_GESAMT (Integer value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_GESAMT (Validator<?> value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_UNENTSCHULDIGT (Integer value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_UNENTSCHULDIGT (Validator<?> value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_VERSPAETUNGEN (Integer value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_VERSPAETUNGEN (Validator<?> value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder BU_BEWERTUNGS_TEXT (String value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZEUGNISRowBuilder BU_BEWERTUNGS_TEXT (Validator<?> value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZEUGNISRowBuilder FORMULAR_ID (Long value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder FORMULAR_ID (Validator<?> value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH (Validator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH2 (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH2 (Validator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLES_AUSGABE_DATUM (Timestamp value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLES_AUSGABE_DATUM (Validator<?> value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_AUSGESCHLOSSEN (Boolean value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_AUSGESCHLOSSEN (Validator<?> value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_GEFAEHRDET (Boolean value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_GEFAEHRDET (Validator<?> value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_WURDE_NICHT_ERREICHT (Boolean value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_WURDE_NICHT_ERREICHT (Validator<?> value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSE_ID (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSE_ID (Validator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH (Validator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH2 (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH2 (Validator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder RUECKT_AUF (Boolean value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZEUGNISRowBuilder RUECKT_AUF (Validator<?> value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHUELER_ID (Long value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHUELER_ID (Validator<?> value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHULHALBJAHR_ID (Long value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHULHALBJAHR_ID (Validator<?> value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SOLBEWERTUNGS_TEXT_ID (Long value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SOLBEWERTUNGS_TEXT_ID (Validator<?> value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZEUGNISRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZEUGNISRowBuilder ZEUGNIS_ART_ID (Long value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ZEUGNIS_ART_ID (Validator<?> value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }


    public static ZEUGNISRowBuilder newZEUGNIS(DataSetManipulator builder) {
        return new ZEUGNISRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static ZEUGNISRowBuilder newZEUGNIS(DataSetManipulator builder, String... identifierColumns) {
        return new ZEUGNISRowBuilder(builder, true, identifierColumns);
    }

    public static ZEUGNISRowBuilder newZEUGNIS(DataSetManipulator builder, boolean initNotNullValues) {
        return new ZEUGNISRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static ZEUGNISRowBuilder newZEUGNIS(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new ZEUGNISRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
