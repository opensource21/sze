package net.sf.sze.dbunit.dataset;

import static net.sf.sze.dbunit.rowbuilder.BEMERKUNGS_BAUSTEINRowBuilder.newBEMERKUNGS_BAUSTEIN;
import static net.sf.sze.dbunit.rowbuilder.KLASSERowBuilder.newKLASSE;
import static net.sf.sze.dbunit.rowbuilder.SCHUELERRowBuilder.newSCHUELER;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTRowBuilder.newSCHULAMT;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder.newSCHULAMTS_BEMERKUNGS_BAUSTEIN;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNISRowBuilder.newZEUGNIS;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNIS_ARTRowBuilder.newZEUGNIS_ART;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNIS_FORMULARRowBuilder.newZEUGNIS_FORMULAR;
import static net.sf.sze.dbunit.rowbuilder.BEMERKUNGRowBuilder.newBEMERKUNG;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGRowBuilder.newSCHULAMTS_BEMERKUNG;
import static net.sf.sze.dbunit.rowbuilder.SCHULHALBJAHRRowBuilder.newSCHULHALBJAHR;
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

        newBEMERKUNGS_BAUSTEIN().ID(1L).AKTIV(Boolean.TRUE).NAME("Rechtschreibschwäche").TEXT("Auf Beschluss der Klassenkonferenz vom @datum@ ist im Rechtschreiben von den Grundsätzen der Leistungsbewertung im Schuljahr @schuljahr@ abgewichen worden.").addTo(b);

        newKLASSE().ID(1L).GESCHLOSSEN(Boolean.FALSE).JAHRGANG(2006).SUFFIX("").addTo(b);
        newSCHUELER().ID(1L).GEBURTSORT("Hamburg").GEBURTSTAG(Timestamp.valueOf("2000-06-15 00:00:00.0")).GESCHLECHT(0).NAME("MUSTERMANN").VORNAME("ERWIN").KLASSE_ID(1L).addTo(b);
        newSCHUELER().ID(2L).GEBURTSORT("Kiel").GEBURTSTAG(Timestamp.valueOf("2000-03-15 00:00:00.0")).GESCHLECHT(1).NAME("MUSTERFRAU").VORNAME("ERNA").KLASSE_ID(1L).addTo(b);
        newSCHULAMT().ID(1L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("@Name@ übte das Amt @des Klassensprechers|der Klassensprecherin@ aus.").NAME("Klassensprecher").addTo(b);
        newSCHULAMTS_BEMERKUNGS_BAUSTEIN().ID(1L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("").NAME("_LEER_").SORTIERUNG(10L).addTo(b);
        newSCHULAMTS_BEMERKUNGS_BAUSTEIN().ID(2L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("@Name@ zeichnete sich dabei durch große Zuverlässigkeit aus.").NAME("Zuverlässigkeit").SORTIERUNG(20L).addTo(b);

        newSCHULHALBJAHR().ID(1L).HALBJAHR(0).JAHR(2013).SELECTABLE(Boolean.TRUE).addTo(b);
        newSCHULHALBJAHR().ID(2L).HALBJAHR(1).JAHR(2013).SELECTABLE(Boolean.TRUE).addTo(b);

        newZEUGNIS_ART().ID(1L).ABSCHLUSS_GRAD("").AKTIV(Boolean.TRUE).NAME("Standard-Zeugnis").NOTE_ALS_TEXT_DARSTELLEN(Boolean.FALSE).PLATZ_FUER_SIEGEL(Boolean.FALSE).PRINT_VERSETZUNGSBEMERKUNG(Boolean.TRUE).SORTIERUNG(10L).TITEL("Zeugnis").addTo(b);
        newZEUGNIS_FORMULAR().ID(1L).AUSGABE_DATUM(Date.valueOf("2013-01-31")).BESCHREIBUNG("2013 erstes Halbjahr").NACHTEILS_AUSGLEICHS_DATUM(Date.valueOf("2012-09-14")).TEMPLATE_FILE_NAME("egal").KLASSE_ID(1L).SCHULHALBJAHR_ID(1L).addTo(b);
        newZEUGNIS_FORMULAR().ID(2L).AUSGABE_DATUM(Date.valueOf("2013-07-01")).BESCHREIBUNG("2013 zweites Halbjahr").NACHTEILS_AUSGLEICHS_DATUM(Date.valueOf("2012-09-14")).TEMPLATE_FILE_NAME("egal").KLASSE_ID(1L).SCHULHALBJAHR_ID(2L).addTo(b);
        newZEUGNIS().ID(3L).ANZAHL_FEHLTAGE_GESAMT(0).ANZAHL_FEHLTAGE_UNENTSCHULDIGT(0).ANZAHL_VERSPAETUNGEN(0).BU_BEWERTUNGS_TEXT("ERWIN MUSTERMANN IST fleissigA").KLASSEN_ZIEL_AUSGESCHLOSSEN(Boolean.FALSE).KLASSEN_ZIEL_GEFAEHRDET(Boolean.FALSE).KLASSEN_ZIEL_WURDE_NICHT_ERREICHT(Boolean.FALSE).RUECKT_AUF(Boolean.TRUE).FORMULAR_ID(1L).KLASSE_ID(1L).SCHUELER_ID(1L).SCHULHALBJAHR_ID(1L).ZEUGNIS_ART_ID(1L).addTo(b);
        newZEUGNIS().ID(4L).ANZAHL_FEHLTAGE_GESAMT(0).ANZAHL_FEHLTAGE_UNENTSCHULDIGT(0).ANZAHL_VERSPAETUNGEN(0).BU_BEWERTUNGS_TEXT("ERNA MUSTERFRAU ist fleissigB").KLASSEN_ZIEL_AUSGESCHLOSSEN(Boolean.FALSE).KLASSEN_ZIEL_GEFAEHRDET(Boolean.FALSE).KLASSEN_ZIEL_WURDE_NICHT_ERREICHT(Boolean.FALSE).RUECKT_AUF(Boolean.TRUE).FORMULAR_ID(1L).KLASSE_ID(1L).SCHUELER_ID(2L).SCHULHALBJAHR_ID(1L).ZEUGNIS_ART_ID(1L).addTo(b);
        newBEMERKUNG().ID(1L).ER_SIE_STATT_NAMEN(Boolean.FALSE).FREI_TEXT("ERWIN MUSTERMANN ist fleissig1").BAUSTEIN_ID(1L).ZEUGNIS_ID(3L).addTo(b);
        newBEMERKUNG().ID(2L).ER_SIE_STATT_NAMEN(Boolean.FALSE).FREI_TEXT("ERNA MUSTERFRAU ist fleissig2").BAUSTEIN_ID(1L).ZEUGNIS_ID(4L).addTo(b);
        newSCHULAMTS_BEMERKUNG().ID(1L).ER_SIE_STATT_NAMEN(Boolean.FALSE).FREI_TEXT("ERWIN MUSTERMANN ist fleissig3").SCHULAMTS_BAUSTEIN_ID(1L).ZEUGNIS_ID(3L).SCHULAMT_ID(1L).addTo(b);
        newSCHULAMTS_BEMERKUNG().ID(2L).ER_SIE_STATT_NAMEN(Boolean.FALSE).FREI_TEXT("ERNA MUSTERFRAU ist fleissig4").SCHULAMTS_BAUSTEIN_ID(2L).ZEUGNIS_ID(4L).SCHULAMT_ID(1L).addTo(b);
        return b.build();
    }

    @SuppressWarnings("boxing")
    public static IDataSet buildVariableResult(IDataSet startDataSet) throws DataSetException  {
        final DataSetRowChanger b = new DataSetRowChanger(startDataSet);
        newZEUGNIS("ID").ID(3L).BU_BEWERTUNGS_TEXT("@NAME@ @Nachname@ IST fleissigA").VERSION(gt(0)).addTo(b);
        newZEUGNIS("ID").ID(4L).BU_BEWERTUNGS_TEXT("@NAME@ @Nachname@ ist fleissigB").VERSION(gt(0)).addTo(b);
        newBEMERKUNG("ID").ID(1L).FREI_TEXT("@NAME@ @Nachname@ ist fleissig1").VERSION(gt(0)).addTo(b);
        newBEMERKUNG("ID").ID(2L).FREI_TEXT("@NAME@ @Nachname@ ist fleissig2").VERSION(gt(0)).addTo(b);
        newSCHULAMTS_BEMERKUNG("ID").ID(1L).FREI_TEXT("@NAME@ @Nachname@ ist fleissig3").VERSION(gt(0)).addTo(b);
        newSCHULAMTS_BEMERKUNG("ID").ID(2L).FREI_TEXT("@NAME@ @Nachname@ ist fleissig4").VERSION(gt(0)).addTo(b);
        return b.build();
    }


    @SuppressWarnings("boxing")
    public static IDataSet buildSchuelerInit() throws DataSetException {

        final DataSetBuilder b = new DataSetBuilder();

        newKLASSE().ID(1L).GESCHLOSSEN(Boolean.FALSE).JAHRGANG(2006).SUFFIX("").addTo(b);
        newSCHUELER().ID(1L).GEBURTSORT("Hamburg").GEBURTSTAG(Timestamp.valueOf("2000-06-15 00:00:00.0")).GESCHLECHT(0).NAME("MUSTERMANN").VORNAME("ERWIN").KLASSE_ID(1L).addTo(b);
        newSCHUELER().ID(2L).GEBURTSORT("Kiel").GEBURTSTAG(Timestamp.valueOf("2000-03-15 00:00:00.0")).GESCHLECHT(1).NAME("MUSTERFRAU").VORNAME("ERNA").KLASSE_ID(1L).addTo(b);

        return b.build();
    }

    @SuppressWarnings("boxing")
    //TODO um das sauber zu testen bräuchte man sehr viele Validatoren.
    public static IDataSet buildSchuelerResult() throws DataSetException {

        final DataSetBuilder b = new DataSetBuilder();

        newKLASSE().ID(1L).GESCHLOSSEN(Boolean.FALSE).JAHRGANG(2006).SUFFIX("").addTo(b);
//        newSCHUELER().ID(1L).GEBURTSORT("Hamburg").GEBURTSTAG(Timestamp.valueOf("2000-06-15 00:00:00.0")).GESCHLECHT(0).NAME("MUSTERMANN").VORNAME("ERWIN").KLASSE_ID(1L).addTo(b);
//        newSCHUELER().ID(2L).GEBURTSORT("Kiel").GEBURTSTAG(Timestamp.valueOf("2000-03-15 00:00:00.0")).GESCHLECHT(1).NAME("MUSTERFRAU").VORNAME("ERNA").KLASSE_ID(1L).addTo(b);

        return b.build();
    }

}

