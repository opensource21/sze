package net.sf.sze.dbunit.dataset;

import static net.sf.sze.dbunit.rowbuilder.AG_BEWERTUNGRowBuilder.newAG_BEWERTUNG;
import static net.sf.sze.dbunit.rowbuilder.ARBEITSGRUPPERowBuilder.newARBEITSGRUPPE;
import static net.sf.sze.dbunit.rowbuilder.ARBEITS_UND_SOZIAL_VERHALTENRowBuilder.newARBEITS_UND_SOZIAL_VERHALTEN;
import static net.sf.sze.dbunit.rowbuilder.AV_SV_BEWERTUNGRowBuilder.newAV_SV_BEWERTUNG;
import static net.sf.sze.dbunit.rowbuilder.BEMERKUNGS_BAUSTEINRowBuilder.newBEMERKUNGS_BAUSTEIN;
import static net.sf.sze.dbunit.rowbuilder.BEWERTUNGRowBuilder.newBEWERTUNG;
import static net.sf.sze.dbunit.rowbuilder.KLASSERowBuilder.newKLASSE;
import static net.sf.sze.dbunit.rowbuilder.SCHUELERRowBuilder.newSCHUELER;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTRowBuilder.newSCHULAMT;
import static net.sf.sze.dbunit.rowbuilder.SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder.newSCHULAMTS_BEMERKUNGS_BAUSTEIN;
import static net.sf.sze.dbunit.rowbuilder.SCHULFACHRowBuilder.newSCHULFACH;
import static net.sf.sze.dbunit.rowbuilder.SCHULHALBJAHRRowBuilder.newSCHULHALBJAHR;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNISRowBuilder.newZEUGNIS;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNIS_ARTRowBuilder.newZEUGNIS_ART;
import static net.sf.sze.dbunit.rowbuilder.ZEUGNIS_FORMULARRowBuilder.newZEUGNIS_FORMULAR;

import java.sql.Date;
import java.sql.Timestamp;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.dbunit.validator.GreaterThan;

public class InitZeugnis {

    @SuppressWarnings("boxing")
    public static IDataSet buildInitZeugnis() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newARBEITSGRUPPE().ID(1L).VERSION(0L).KLASSENSTUFEN("3 4 5 6 8 9 10").NAME("AG Ballspiele").SORTIERUNG(10L).addTo(b);
        newARBEITSGRUPPE().ID(2L).VERSION(0L).KLASSENSTUFEN("7 8 9 10").NAME("AG Theater").SORTIERUNG(12L).addTo(b);
        newARBEITSGRUPPE().ID(3L).VERSION(0L).KLASSENSTUFEN("1 2 3 4 5 6 17 8 9 10").NAME("AG Technisches Werken für Fortgeschrittene").SORTIERUNG(20L).addTo(b);
        newARBEITSGRUPPE().ID(4L).VERSION(0L).KLASSENSTUFEN("").NAME("AG Sanitäter").SORTIERUNG(33L).addTo(b);
        newARBEITSGRUPPE().ID(5L).VERSION(0L).KLASSENSTUFEN("6 7").NAME("AG Schülerfirma \"KreaPrint\"").SORTIERUNG(16L).addTo(b);

        newARBEITS_UND_SOZIAL_VERHALTEN().ID(1L).VERSION(0L).KLASSENSTUFEN("5, 6, 7, 8, 9, 10").NAME("Lern-/Leistungsbereitschaft / Mitarbeit").SORTIERUNG(10L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(2L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Ziel- und Ergebnisorientierung").SORTIERUNG(11L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(3L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Kooperationsfähigkeit").SORTIERUNG(12L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(4L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Selbstständigkeit").SORTIERUNG(13L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(5L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Sorgfalt und Ausdauer").SORTIERUNG(14L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(6L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Verlässlichkeit").SORTIERUNG(15L).TYP(0).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(7L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Reflexionsfähigkeit").SORTIERUNG(16L).TYP(1).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(8L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Konfliktfähigkeit").SORTIERUNG(17L).TYP(1).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(9L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Vereinbaren + Einhalten von Regeln / Fairness").SORTIERUNG(18L).TYP(1).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(10L).VERSION(0L).KLASSENSTUFEN("5, 6, 7, 8, 9, 10").NAME("Hilfsbereitschaft / Achtung anderer").SORTIERUNG(19L).TYP(1).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(11L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Übernahme von Verantwortung").SORTIERUNG(20L).TYP(1).addTo(b);
        newARBEITS_UND_SOZIAL_VERHALTEN().ID(12L).VERSION(0L).KLASSENSTUFEN("5, 6, 17, 8, 9, 10").NAME("Mitgestaltung des Gemeinschaftslebens").SORTIERUNG(21L).TYP(1).addTo(b);

        newBEMERKUNGS_BAUSTEIN().ID(1L).VERSION(0L).AKTIV(Boolean.TRUE).NAME("Rechtschreibschwäche").TEXT("Auf Beschluss der Klassenkonferenz vom @datum@ ist im Rechtschreiben von den Grundsätzen der Leistungsbewertung im Schuljahr @schuljahr@ abgewichen worden.").addTo(b);
        newKLASSE().ID(1L).VERSION(0L).GESCHLOSSEN(Boolean.FALSE).JAHRGANG(2006).SUFFIX("").addTo(b);

        newSCHUELER().ID(1L).VERSION(0L).GEBURTSORT("Hamburg").GEBURTSTAG(Timestamp.valueOf("2000-06-15 00:00:00.0")).GESCHLECHT(0).NAME("MUSTERMANN").VORNAME("ERWIN").KLASSE_ID(1L).addTo(b);
        newSCHUELER().ID(2L).VERSION(0L).GEBURTSORT("Kiel").GEBURTSTAG(Timestamp.valueOf("2000-03-15 00:00:00.0")).GESCHLECHT(1).NAME("MUSTERFRAU").VORNAME("ERNA").KLASSE_ID(1L).addTo(b);

        newSCHULAMT().ID(1L).VERSION(0L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("@Name@ übte das Amt @des Klassensprechers|der Klassensprecherin@ aus.").NAME("Klassensprecher").addTo(b);
        newSCHULAMTS_BEMERKUNGS_BAUSTEIN().ID(1L).VERSION(0L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("").NAME("_LEER_").SORTIERUNG(10L).addTo(b);
        newSCHULAMTS_BEMERKUNGS_BAUSTEIN().ID(2L).VERSION(0L).AKTIV(Boolean.TRUE).BESCHREIBENDER_SATZ("@Name@ zeichnete sich dabei durch große Zuverlässigkeit aus.").NAME("Zuverlässigkeit").SORTIERUNG(20L).addTo(b);
        newSCHULFACH().ID(1L).VERSION(0L).NAME("Mathematik").SORTIERUNG(101L).STUFEN_MIT_AUSSEN_DIFFERENZIERUNG("5 6 7 8 9 10").TYP(0).addTo(b);
        newSCHULFACH().ID(2L).VERSION(0L).NAME("Deutsch").SORTIERUNG(102L).STUFEN_MIT_AUSSEN_DIFFERENZIERUNG("5 6 7 8 9 10").STUFEN_MIT_BINNEN_DIFFERENZIERUNG("17").TYP(0).addTo(b);
        newSCHULFACH().ID(3L).VERSION(0L).NAME("Englisch").SORTIERUNG(103L).STUFEN_MIT_AUSSEN_DIFFERENZIERUNG("5 6 7 8 9 10").TYP(0).addTo(b);
        newSCHULFACH().ID(4L).VERSION(0L).NAME("Französisch").SORTIERUNG(104L).STUFEN_MIT_BINNEN_DIFFERENZIERUNG("7 8 9 10").TYP(1).addTo(b);
        newSCHULFACH().ID(5L).VERSION(0L).NAME("Naturwissenschaften").SORTIERUNG(105L).STUFEN_MIT_BINNEN_DIFFERENZIERUNG("5 6 7 9 10").STUFEN_MIT_STANDARD_BEWERTUNG("17").TYP(1).addTo(b);
        newSCHULFACH().ID(6L).VERSION(0L).NAME("Gesellschaftslehre").SORTIERUNG(106L).STUFEN_MIT_BINNEN_DIFFERENZIERUNG("5 6 7 8").STUFEN_MIT_STANDARD_BEWERTUNG("17").TYP(1).addTo(b);
        newSCHULFACH().ID(7L).VERSION(0L).NAME("Arbeit/Wirtschaft/Technik").SORTIERUNG(107L).STUFEN_MIT_STANDARD_BEWERTUNG(" 17 8 9 10").TYP(1).addTo(b);
        newSCHULFACH().ID(8L).VERSION(0L).NAME("Sport").SORTIERUNG(108L).STUFEN_MIT_STANDARD_BEWERTUNG("5 6 17 8 9 10").TYP(1).addTo(b);
        newSCHULFACH().ID(9L).VERSION(0L).NAME("Musik").SORTIERUNG(109L).STUFEN_MIT_STANDARD_BEWERTUNG("5 6").TYP(1).addTo(b);
        newSCHULFACH().ID(10L).VERSION(0L).NAME("Musik").SORTIERUNG(110L).STUFEN_MIT_STANDARD_BEWERTUNG("7 8 9 10").TYP(2).addTo(b);
        newSCHULFACH().ID(11L).VERSION(0L).NAME("Kunst").SORTIERUNG(111L).STUFEN_MIT_STANDARD_BEWERTUNG("5 6 7 8 9 10").TYP(2).addTo(b);
        newSCHULFACH().ID(12L).VERSION(0L).NAME("EDV").SORTIERUNG(112L).STUFEN_MIT_STANDARD_BEWERTUNG("7").TYP(2).addTo(b);
        newSCHULFACH().ID(13L).VERSION(0L).NAME("Textiles Werken").SORTIERUNG(114L).STUFEN_MIT_STANDARD_BEWERTUNG("5 6").TYP(2).addTo(b);
        newSCHULHALBJAHR().ID(1L).VERSION(0L).HALBJAHR(0).JAHR(2013).SELECTABLE(Boolean.TRUE).addTo(b);
        newSCHULHALBJAHR().ID(2L).VERSION(0L).HALBJAHR(1).JAHR(2013).SELECTABLE(Boolean.TRUE).addTo(b);
        newZEUGNIS_ART().ID(1L).VERSION(0L).ABSCHLUSS_GRAD("").AKTIV(Boolean.TRUE).NAME("Standard-Zeugnis").NOTE_ALS_TEXT_DARSTELLEN(Boolean.FALSE).PLATZ_FUER_SIEGEL(Boolean.FALSE).PRINT_VERSETZUNGSBEMERKUNG(Boolean.TRUE).SORTIERUNG(10L).TITEL("Zeugnis").addTo(b);
        newZEUGNIS_FORMULAR().ID(1L).VERSION(0L).AUSGABE_DATUM(Date.valueOf("2013-01-31")).BESCHREIBUNG("2013 erstes Halbjahr").NACHTEILS_AUSGLEICHS_DATUM(Date.valueOf("2012-09-14")).TEMPLATE_FILE_NAME("egal").KLASSE_ID(1L).SCHULHALBJAHR_ID(1L).addTo(b);
        newZEUGNIS_FORMULAR().ID(2L).VERSION(0L).AUSGABE_DATUM(Date.valueOf("2013-07-01")).BESCHREIBUNG("2013 zweites Halbjahr").NACHTEILS_AUSGLEICHS_DATUM(Date.valueOf("2012-09-14")).TEMPLATE_FILE_NAME("egal").KLASSE_ID(1L).SCHULHALBJAHR_ID(2L).addTo(b);

        return b.build();
    }

    @SuppressWarnings("boxing")
    public static IDataSet buildInitResult(Long zeugnisId1, Long zeugnisId2,
            Long formularId, Long halbjahrId) throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newZEUGNIS().ID(zeugnisId1).VERSION(0L).ANZAHL_FEHLTAGE_GESAMT(0).ANZAHL_FEHLTAGE_UNENTSCHULDIGT(0).ANZAHL_VERSPAETUNGEN(0).BU_BEWERTUNGS_TEXT("").KLASSEN_ZIEL_AUSGESCHLOSSEN(Boolean.FALSE).KLASSEN_ZIEL_GEFAEHRDET(Boolean.FALSE).KLASSEN_ZIEL_WURDE_NICHT_ERREICHT(Boolean.FALSE).RUECKT_AUF(Boolean.TRUE).FORMULAR_ID(formularId).KLASSE_ID(1L).SCHUELER_ID(1L).SCHULHALBJAHR_ID(halbjahrId).ZEUGNIS_ART_ID(1L).addTo(b);
        newZEUGNIS().ID(zeugnisId2).VERSION(0L).ANZAHL_FEHLTAGE_GESAMT(0).ANZAHL_FEHLTAGE_UNENTSCHULDIGT(0).ANZAHL_VERSPAETUNGEN(0).BU_BEWERTUNGS_TEXT("").KLASSEN_ZIEL_AUSGESCHLOSSEN(Boolean.FALSE).KLASSEN_ZIEL_GEFAEHRDET(Boolean.FALSE).KLASSEN_ZIEL_WURDE_NICHT_ERREICHT(Boolean.FALSE).RUECKT_AUF(Boolean.TRUE).FORMULAR_ID(formularId).KLASSE_ID(1L).SCHUELER_ID(2L).SCHULHALBJAHR_ID(halbjahrId).ZEUGNIS_ART_ID(1L).addTo(b);
        newAG_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).TEILGENOMMEN(Boolean.FALSE).ARBEITSGRUPPE_ID(2L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newAG_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).TEILGENOMMEN(Boolean.FALSE).ARBEITSGRUPPE_ID(5L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newAG_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).TEILGENOMMEN(Boolean.FALSE).ARBEITSGRUPPE_ID(2L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newAG_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).TEILGENOMMEN(Boolean.FALSE).ARBEITSGRUPPE_ID(5L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newAV_SV_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).ARBEITS_UND_SOZIAL_VERHALTEN_ID(1L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newAV_SV_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).ARBEITS_UND_SOZIAL_VERHALTEN_ID(10L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newAV_SV_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).ARBEITS_UND_SOZIAL_VERHALTEN_ID(1L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newAV_SV_BEWERTUNG().ID(new GreaterThan(0)).VERSION(0L).ARBEITS_UND_SOZIAL_VERHALTEN_ID(10L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(1L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(2L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(3L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(4L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(5L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(6L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(10L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(11L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(12L).ZEUGNIS_ID(zeugnisId1).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(1L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(2L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.AussenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(3L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(4L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(5L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.BinnenDifferenzierteBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).LEISTUNGSNIVEAU("G").RELEVANT(Boolean.TRUE).SONDER_NOTE("").SCHULFACH_ID(6L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(10L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(11L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        newBEWERTUNG().CLASS("net.sf.sze.zeugnis.StandardBewertung").ID(new GreaterThan(0)).VERSION(0L).LEISTUNG_NUR_SCHWACH_AUSREICHEND(Boolean.FALSE).RELEVANT(Boolean.FALSE).SONDER_NOTE("").SCHULFACH_ID(12L).ZEUGNIS_ID(zeugnisId2).addTo(b);
        return b.build();

    }
}

