package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;
import java.sql.Timestamp;

public class ZEUGNISRowBuilder extends BasicDataRowBuilder {

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

    public ZEUGNISRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_ANZAHL_FEHLTAGE_GESAMT, new Integer("0"));
        addDefaultValue(C_ANZAHL_VERSPAETUNGEN, new Integer("0"));
        addDefaultValue(C_ZEUGNIS_ART_ID, new Long("0"));
        addDefaultValue(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, new Integer("0"));
        addDefaultValue(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, Boolean.FALSE);
        addDefaultValue(C_FORMULAR_ID, new Long("0"));
        addDefaultValue(C_KLASSEN_ZIEL_GEFAEHRDET, Boolean.FALSE);
        addDefaultValue(C_RUECKT_AUF, Boolean.FALSE);
        addDefaultValue(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, Boolean.FALSE);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_SCHULHALBJAHR_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_KLASSE_ID, new Long("0"));
        addDefaultValue(C_SCHUELER_ID, new Long("0"));
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_GESAMT (Integer value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_GESAMT (IValidator<?> value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_UNENTSCHULDIGT (Integer value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_FEHLTAGE_UNENTSCHULDIGT (IValidator<?> value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_VERSPAETUNGEN (Integer value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder ANZAHL_VERSPAETUNGEN (IValidator<?> value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder BU_BEWERTUNGS_TEXT (String value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZEUGNISRowBuilder BU_BEWERTUNGS_TEXT (IValidator<?> value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZEUGNISRowBuilder FORMULAR_ID (Long value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder FORMULAR_ID (IValidator<?> value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH (IValidator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH2 (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLER_LEITSPRUCH2 (IValidator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLES_AUSGABE_DATUM (Timestamp value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZEUGNISRowBuilder INDIVIDUELLES_AUSGABE_DATUM (IValidator<?> value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_AUSGESCHLOSSEN (Boolean value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_AUSGESCHLOSSEN (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_GEFAEHRDET (Boolean value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_GEFAEHRDET (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_WURDE_NICHT_ERREICHT (Boolean value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSEN_ZIEL_WURDE_NICHT_ERREICHT (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSE_ID (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder KLASSE_ID (IValidator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH (IValidator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH2 (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder QUELLE_INDIVIDUELLER_LEITSPRUCH2 (IValidator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZEUGNISRowBuilder RUECKT_AUF (Boolean value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZEUGNISRowBuilder RUECKT_AUF (IValidator<?> value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHUELER_ID (Long value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHUELER_ID (IValidator<?> value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHULHALBJAHR_ID (Long value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SCHULHALBJAHR_ID (IValidator<?> value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SOLBEWERTUNGS_TEXT_ID (Long value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder SOLBEWERTUNGS_TEXT_ID (IValidator<?> value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZEUGNISRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZEUGNISRowBuilder ZEUGNIS_ART_ID (Long value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }

    public final ZEUGNISRowBuilder ZEUGNIS_ART_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }


    public static ZEUGNISRowBuilder newZEUGNIS() {
        return new ZEUGNISRowBuilder(PRIMARY_KEY);
    }

    public static ZEUGNISRowBuilder newZEUGNIS(String... identifierColumns) {
        return new ZEUGNISRowBuilder(identifierColumns);
    }

}
