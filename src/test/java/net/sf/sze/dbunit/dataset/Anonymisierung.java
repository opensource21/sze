package net.sf.sze.dbunit.dataset;

import static net.sf.sze.dbunit.rowbuilder.BemerkungRowBuilder.newBemerkung;
import static net.sf.sze.dbunit.rowbuilder.BemerkungsBausteinRowBuilder.newBemerkungsBaustein;
import static net.sf.sze.dbunit.rowbuilder.KlasseRowBuilder.newKlasse;
import static net.sf.sze.dbunit.rowbuilder.SchuelerRowBuilder.newSchueler;
import static net.sf.sze.dbunit.rowbuilder.SchulamtRowBuilder.newSchulamt;
import static net.sf.sze.dbunit.rowbuilder.SchulamtsBemerkungRowBuilder.newSchulamtsBemerkung;
import static net.sf.sze.dbunit.rowbuilder.SchulamtsBemerkungsBausteinRowBuilder.newSchulamtsBemerkungsBaustein;
import static net.sf.sze.dbunit.rowbuilder.SchulhalbjahrRowBuilder.newSchulhalbjahr;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisArtRowBuilder.newZeugnisArt;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisFormularRowBuilder.newZeugnisFormular;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisRowBuilder.newZeugnis;
import static org.dbunit.validator.Validators.gt;

import java.sql.Date;
import java.sql.Timestamp;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.dbunit.dataset.builder.DataSetRowChanger;

public class Anonymisierung {

    @SuppressWarnings("boxing")
    public static IDataSet buildVariableInit() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();

        newBemerkungsBaustein().Id(1L).Aktiv(Boolean.TRUE).Name("Rechtschreibschwäche").Text("Auf Beschluss der Klassenkonferenz vom @datum@ ist im Rechtschreiben von den Grundsätzen der Leistungsbewertung im Schuljahr @schuljahr@ abgewichen worden.").addTo(b);

        newKlasse().Id(1L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("").addTo(b);
        newSchueler().Id(1L).Geburtsort("Hamburg").Geburtstag(Timestamp.valueOf("2000-06-15 00:00:00.0")).Geschlecht("m").Name("MUSTERMANN").Vorname("ERWIN").KlasseId(1L).addTo(b);
        newSchueler().Id(2L).Geburtsort("Kiel").Geburtstag(Timestamp.valueOf("2000-03-15 00:00:00.0")).Geschlecht("w").Name("MUSTERFRAU").Vorname("ERNA").KlasseId(1L).addTo(b);
        newSchulamt().Id(1L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ übte das Amt @des Klassensprechers|der Klassensprecherin@ aus.").Name("Klassensprecher").addTo(b);
        newSchulamtsBemerkungsBaustein().Id(1L).Aktiv(Boolean.TRUE).BeschreibenderSatz("").Name("_LEER_").Sortierung(10L).addTo(b);
        newSchulamtsBemerkungsBaustein().Id(2L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ zeichnete sich dabei durch große Zuverlässigkeit aus.").Name("Zuverlässigkeit").Sortierung(20L).addTo(b);

        newSchulhalbjahr().Id(1L).Halbjahr(0).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);
        newSchulhalbjahr().Id(2L).Halbjahr(1).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);

        newZeugnisArt().Id(1L).AbschlussGrad("").Aktiv(Boolean.TRUE).Name("Standard-Zeugnis").NoteAlsTextDarstellen(Boolean.FALSE).PlatzFuerSiegel(Boolean.FALSE).PrintVersetzungsbemerkung(Boolean.TRUE).Sortierung(10L).Titel("Zeugnis").addTo(b);
        newZeugnisFormular().Id(1L).AusgabeDatum(Date.valueOf("2013-01-31")).Beschreibung("2013 erstes Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(1L).SchulhalbjahrId(1L).addTo(b);
        newZeugnisFormular().Id(2L).AusgabeDatum(Date.valueOf("2013-07-01")).Beschreibung("2013 zweites Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(1L).SchulhalbjahrId(2L).addTo(b);
        newZeugnis().Id(3L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("ERWIN MUSTERMANN IST fleissigA").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(1L).KlasseId(1L).SchuelerId(1L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newZeugnis().Id(4L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("ERNA MUSTERFRAU ist fleissigB").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(1L).KlasseId(1L).SchuelerId(2L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newBemerkung().Id(1L).ErSieStattNamen(Boolean.FALSE).FreiText("ERWIN MUSTERMANN ist fleissig1").BausteinId(1L).ZeugnisId(3L).addTo(b);
        newBemerkung().Id(2L).ErSieStattNamen(Boolean.FALSE).FreiText("ERNA MUSTERFRAU ist fleissig2").BausteinId(1L).ZeugnisId(4L).addTo(b);
        newSchulamtsBemerkung().Id(1L).ErSieStattNamen(Boolean.FALSE).FreiText("ERWIN MUSTERMANN ist fleissig3").SchulamtsBausteinId(1L).ZeugnisId(3L).SchulamtId(1L).addTo(b);
        newSchulamtsBemerkung().Id(2L).ErSieStattNamen(Boolean.FALSE).FreiText("ERNA MUSTERFRAU ist fleissig4").SchulamtsBausteinId(2L).ZeugnisId(4L).SchulamtId(1L).addTo(b);
        return b.build();
    }

    @SuppressWarnings("boxing")
    public static IDataSet buildVariableResult(IDataSet startDataSet) throws DataSetException  {
        final DataSetRowChanger b = new DataSetRowChanger(startDataSet);
        newZeugnis("ID").Id(3L).BuBewertungsText("@NAME@ @Nachname@ IST fleissigA").Version(gt(0)).addTo(b);
        newZeugnis("ID").Id(4L).BuBewertungsText("@NAME@ @Nachname@ ist fleissigB").Version(gt(0)).addTo(b);
        newBemerkung("ID").Id(1L).FreiText("@NAME@ @Nachname@ ist fleissig1").Version(gt(0)).addTo(b);
        newBemerkung("ID").Id(2L).FreiText("@NAME@ @Nachname@ ist fleissig2").Version(gt(0)).addTo(b);
        newSchulamtsBemerkung("ID").Id(1L).FreiText("@NAME@ @Nachname@ ist fleissig3").Version(gt(0)).addTo(b);
        newSchulamtsBemerkung("ID").Id(2L).FreiText("@NAME@ @Nachname@ ist fleissig4").Version(gt(0)).addTo(b);
        return b.build();
    }


    @SuppressWarnings("boxing")
    public static IDataSet buildSchuelerInit() throws DataSetException {

        final DataSetBuilder b = new DataSetBuilder();

        newKlasse().Id(1L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("").addTo(b);
        newSchueler().Id(1L).Geburtsort("Hamburg").Geburtstag(Timestamp.valueOf("2000-06-15 00:00:00.0")).Geschlecht("m").Name("MUSTERMANN").Vorname("ERWIN").KlasseId(1L).addTo(b);
        newSchueler().Id(2L).Geburtsort("Kiel").Geburtstag(Timestamp.valueOf("2000-03-15 00:00:00.0")).Geschlecht("w").Name("MUSTERFRAU").Vorname("ERNA").KlasseId(1L).addTo(b);

        return b.build();
    }

    @SuppressWarnings("boxing")
    //NICE Testen um das sauber zu testen bräuchte man sehr viele Validatoren.
    public static IDataSet buildSchuelerResult() throws DataSetException {

        final DataSetBuilder b = new DataSetBuilder();

        newKlasse().Id(1L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("").addTo(b);
//        newSchueler().Id(1L).Geburtsort("Hamburg").Geburtstag(Timestamp.valueOf("2000-06-15 00:00:00.0")).Geschlecht("m").Name("MUSTERMANN").Vorname("ERWIN").KlasseId(1L).addTo(b);
//        newSchueler().Id(2L).Geburtsort("Kiel").Geburtstag(Timestamp.valueOf("2000-03-15 00:00:00.0")).Geschlecht("w").Name("MUSTERFRAU").Vorname("ERNA").KlasseId(1L).addTo(b);

        return b.build();
    }

}

