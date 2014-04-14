package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;
import java.sql.Timestamp;

public class ZeugnisRowBuilder extends BasicDataRowBuilder {

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

    public ZeugnisRowBuilder(String... identifierColumns) {
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

    public final ZeugnisRowBuilder AnzahlFehltageGesamt (Integer value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZeugnisRowBuilder AnzahlFehltageGesamt (IValidator<?> value) {
        with(C_ANZAHL_FEHLTAGE_GESAMT, value);
        return this;
    }

    public final ZeugnisRowBuilder AnzahlFehltageUnentschuldigt (Integer value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZeugnisRowBuilder AnzahlFehltageUnentschuldigt (IValidator<?> value) {
        with(C_ANZAHL_FEHLTAGE_UNENTSCHULDIGT, value);
        return this;
    }

    public final ZeugnisRowBuilder AnzahlVerspaetungen (Integer value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZeugnisRowBuilder AnzahlVerspaetungen (IValidator<?> value) {
        with(C_ANZAHL_VERSPAETUNGEN, value);
        return this;
    }

    public final ZeugnisRowBuilder BuBewertungsText (String value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZeugnisRowBuilder BuBewertungsText (IValidator<?> value) {
        with(C_BU_BEWERTUNGS_TEXT, value);
        return this;
    }

    public final ZeugnisRowBuilder FormularId (Long value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder FormularId (IValidator<?> value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellerLeitspruch (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellerLeitspruch (IValidator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellerLeitspruch2 (String value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellerLeitspruch2 (IValidator<?> value) {
        with(C_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellesAusgabeDatum (Timestamp value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZeugnisRowBuilder IndividuellesAusgabeDatum (IValidator<?> value) {
        with(C_INDIVIDUELLES_AUSGABE_DATUM, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielAusgeschlossen (Boolean value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielAusgeschlossen (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_AUSGESCHLOSSEN, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielGefaehrdet (Boolean value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielGefaehrdet (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_GEFAEHRDET, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielWurdeNichtErreicht (Boolean value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZeugnisRowBuilder KlassenZielWurdeNichtErreicht (IValidator<?> value) {
        with(C_KLASSEN_ZIEL_WURDE_NICHT_ERREICHT, value);
        return this;
    }

    public final ZeugnisRowBuilder KlasseId (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder KlasseId (IValidator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder QuelleIndividuellerLeitspruch (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisRowBuilder QuelleIndividuellerLeitspruch (IValidator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisRowBuilder QuelleIndividuellerLeitspruch2 (String value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisRowBuilder QuelleIndividuellerLeitspruch2 (IValidator<?> value) {
        with(C_QUELLE_INDIVIDUELLER_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisRowBuilder RuecktAuf (Boolean value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZeugnisRowBuilder RuecktAuf (IValidator<?> value) {
        with(C_RUECKT_AUF, value);
        return this;
    }

    public final ZeugnisRowBuilder SchuelerId (Long value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder SchuelerId (IValidator<?> value) {
        with(C_SCHUELER_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder SchulhalbjahrId (Long value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder SchulhalbjahrId (IValidator<?> value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder SolbewertungsTextId (Long value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder SolbewertungsTextId (IValidator<?> value) {
        with(C_SOLBEWERTUNGS_TEXT_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZeugnisRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZeugnisRowBuilder ZeugnisArtId (Long value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }

    public final ZeugnisRowBuilder ZeugnisArtId (IValidator<?> value) {
        with(C_ZEUGNIS_ART_ID, value);
        return this;
    }


    public static ZeugnisRowBuilder newZeugnis() {
        return new ZeugnisRowBuilder(PRIMARY_KEY);
    }

    public static ZeugnisRowBuilder newZeugnis(String... identifierColumns) {
        return new ZeugnisRowBuilder(identifierColumns);
    }

}
