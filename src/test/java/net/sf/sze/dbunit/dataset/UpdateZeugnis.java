package net.sf.sze.dbunit.dataset;

import static net.sf.sze.dbunit.rowbuilder.AgBewertungRowBuilder.newAgBewertung;
import static net.sf.sze.dbunit.rowbuilder.ArbeitsgruppeRowBuilder.newArbeitsgruppe;
import static net.sf.sze.dbunit.rowbuilder.ArbeitsUndSozialVerhaltenRowBuilder.newArbeitsUndSozialVerhalten;
import static net.sf.sze.dbunit.rowbuilder.AvSvBewertungRowBuilder.newAvSvBewertung;
import static net.sf.sze.dbunit.rowbuilder.BemerkungsBausteinRowBuilder.newBemerkungsBaustein;
import static net.sf.sze.dbunit.rowbuilder.BewertungRowBuilder.newBewertung;
import static net.sf.sze.dbunit.rowbuilder.KlasseRowBuilder.newKlasse;
import static net.sf.sze.dbunit.rowbuilder.SchuelerRowBuilder.newSchueler;
import static net.sf.sze.dbunit.rowbuilder.SchulamtRowBuilder.newSchulamt;
import static net.sf.sze.dbunit.rowbuilder.SchulamtsBemerkungsBausteinRowBuilder.newSchulamtsBemerkungsBaustein;
import static net.sf.sze.dbunit.rowbuilder.SchulfachRowBuilder.newSchulfach;
import static net.sf.sze.dbunit.rowbuilder.SchulhalbjahrRowBuilder.newSchulhalbjahr;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisRowBuilder.newZeugnis;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisArtRowBuilder.newZeugnisArt;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisFormularRowBuilder.newZeugnisFormular;

import java.sql.Date;
import java.sql.Timestamp;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.dbunit.validator.GreaterThan;

public class UpdateZeugnis {

    @SuppressWarnings("boxing")
    public static IDataSet buildUpdateInit() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newArbeitsgruppe().Id(1L).Klassenstufen("3 4 5 6 7 8 9 10").Name("AG Ballspiele").Sortierung(10L).addTo(b);
        newArbeitsgruppe().Id(2L).Klassenstufen("8 9 10").Name("AG Theater").Sortierung(12L).addTo(b);
        newArbeitsgruppe().Id(3L).Klassenstufen("1 2 3 4 5 6 17 8 9 10").Name("AG Technisches Werken für Fortgeschrittene").Sortierung(20L).addTo(b);
        newArbeitsgruppe().Id(4L).Klassenstufen("").Name("AG Sanitäter").Sortierung(33L).addTo(b);
        newArbeitsgruppe().Id(5L).Klassenstufen("6 7").Name("AG Schülerfirma \"KreaPrint\"").Sortierung(16L).addTo(b);

        newArbeitsUndSozialVerhalten().Id(1L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Lern-/Leistungsbereitschaft / Mitarbeit").Sortierung(10L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(2L).Klassenstufen("5, 6, 7, 8, 9, 10").Name("Ziel- und Ergebnisorientierung").Sortierung(11L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(3L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Kooperationsfähigkeit").Sortierung(12L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(4L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Selbstständigkeit").Sortierung(13L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(5L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Sorgfalt und Ausdauer").Sortierung(14L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(6L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Verlässlichkeit").Sortierung(15L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(7L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Reflexionsfähigkeit").Sortierung(16L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(8L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Konfliktfähigkeit").Sortierung(17L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(9L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Vereinbaren + Einhalten von Regeln / Fairness").Sortierung(18L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(10L).Klassenstufen("5, 6, 7, 8, 9, 10").Name("Hilfsbereitschaft / Achtung anderer").Sortierung(19L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(11L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Übernahme von Verantwortung").Sortierung(20L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(12L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Mitgestaltung des Gemeinschaftslebens").Sortierung(21L).Typ(1).addTo(b);

        newBemerkungsBaustein().Id(1L).Aktiv(Boolean.TRUE).Name("Rechtschreibschwäche").Text("Auf Beschluss der Klassenkonferenz vom @datum@ ist im Rechtschreiben von den Grundsätzen der Leistungsbewertung im Schuljahr @schuljahr@ abgewichen worden.").addTo(b);

        newKlasse().Id(1L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("").addTo(b);
        newKlasse().Id(2L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("b").addTo(b);
        newSchueler().Id(1L).Geburtsort("Hamburg").Geburtstag(Timestamp.valueOf("2000-06-15 00:00:00.0")).Geschlecht("m").Name("MUSTERMANN").Vorname("ERWIN").KlasseId(2L).addTo(b);
        newSchueler().Id(2L).Geburtsort("Kiel").Geburtstag(Timestamp.valueOf("2000-03-15 00:00:00.0")).Geschlecht("w").Name("MUSTERFRAU").Vorname("ERNA").KlasseId(2L).addTo(b);
        newSchulamt().Id(1L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ übte das Amt @des Klassensprechers|der Klassensprecherin@ aus.").Name("Klassensprecher").addTo(b);
        newSchulamtsBemerkungsBaustein().Id(1L).Aktiv(Boolean.TRUE).BeschreibenderSatz("").Name("_LEER_").Sortierung(10L).addTo(b);
        newSchulamtsBemerkungsBaustein().Id(2L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ zeichnete sich dabei durch große Zuverlässigkeit aus.").Name("Zuverlässigkeit").Sortierung(20L).addTo(b);
        newSchulfach().Id(1L).Name("Mathematik").Sortierung(101L).StufenMitZweiNiveaus("5 6 7 8 9 10").Typ(0).addTo(b);
        newSchulfach().Id(2L).Name("Deutsch").Sortierung(102L).StufenMitDreiNiveaus("5 6 7 8 9 10").StufenMitZweiNiveaus("17").Typ(0).addTo(b);
        newSchulfach().Id(3L).Name("Englisch").Sortierung(103L).StufenMitStandardBewertung("5 6 7 8 9 10").Typ(0).addTo(b);

        newSchulfach().Id(4L).Name("Französisch").Sortierung(104L).StufenMitDreiNiveaus("7 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(5L).Name("Naturwissenschaften").Sortierung(105L).StufenMitStandardBewertung("5 6 7 9 10").StufenMitDreiNiveaus("17").Typ(1).addTo(b);
        newSchulfach().Id(6L).Name("Gesellschaftslehre").Sortierung(106L).StufenMitZweiNiveaus("5 6 7 8").StufenMitStandardBewertung("17").Typ(1).addTo(b);

        newSchulfach().Id(7L).Name("Arbeit/Wirtschaft/Technik").Sortierung(107L).StufenMitStandardBewertung(" 17 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(8L).Name("Sport").Sortierung(108L).StufenMitStandardBewertung("5 6 17 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(9L).Name("Musik").Sortierung(109L).StufenMitStandardBewertung("5 6").Typ(1).addTo(b);
        newSchulfach().Id(10L).Name("Musik").Sortierung(110L).StufenMitStandardBewertung("8 9 10").Typ(2).addTo(b);
        newSchulfach().Id(11L).Name("Kunst").Sortierung(111L).StufenMitDreiNiveaus("5 6 7 8 9 10").Typ(2).addTo(b);
        newSchulfach().Id(12L).Name("EDV").Sortierung(112L).StufenMitZweiNiveaus("7").Typ(2).addTo(b);
        newSchulfach().Id(13L).Name("Textiles Werken").Sortierung(114L).StufenMitStandardBewertung("5 6 7").Typ(2).addTo(b);

        newSchulhalbjahr().Id(1L).Halbjahr(0).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);
        newSchulhalbjahr().Id(2L).Halbjahr(1).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);
        newZeugnisArt().Id(1L).AbschlussGrad("").Aktiv(Boolean.TRUE).Name("Standard-Zeugnis").NoteAlsTextDarstellen(Boolean.FALSE).PlatzFuerSiegel(Boolean.FALSE).PrintVersetzungsbemerkung(Boolean.TRUE).Sortierung(10L).Titel("Zeugnis").addTo(b);
        newZeugnisFormular().Id(1L).AusgabeDatum(Date.valueOf("2013-01-31")).Beschreibung("2013 erstes Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(1L).SchulhalbjahrId(1L).addTo(b);
        newZeugnisFormular().Id(2L).AusgabeDatum(Date.valueOf("2013-07-01")).Beschreibung("2013 zweites Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(2L).SchulhalbjahrId(1L).addTo(b);
        newZeugnis().Id(3L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(1L).KlasseId(1L).SchuelerId(1L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newZeugnis().Id(4L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(1L).KlasseId(1L).SchuelerId(2L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newAgBewertung().Id(5L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(2L).ZeugnisId(3L).addTo(b);
        newAgBewertung().Id(6L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(3L).addTo(b);
        newAgBewertung().Id(7L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(2L).ZeugnisId(4L).addTo(b);
        newAgBewertung().Id(8L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(4L).addTo(b);
        newAvSvBewertung().Id(5L).ArbeitsUndSozialVerhaltenId(1L).ZeugnisId(3L).addTo(b);
        newAvSvBewertung().Id(6L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(3L).addTo(b);
        newAvSvBewertung().Id(7L).ArbeitsUndSozialVerhaltenId(1L).ZeugnisId(4L).addTo(b);
        newAvSvBewertung().Id(8L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(19L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(1L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(20L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(2L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(21L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(3L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(22L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(4L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(23L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(5L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(24L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(6L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(25L).LeistungNurSchwachAusreichend(Boolean.TRUE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(10L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(26L).LeistungNurSchwachAusreichend(Boolean.TRUE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(27L).LeistungNurSchwachAusreichend(Boolean.TRUE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(3L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(28L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(1L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(29L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(2L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(30L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(3L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(31L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(4L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(32L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(5L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(33L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(6L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(34L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(10L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(35L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(4L).addTo(b);
        newBewertung().Class("StandardBewertung").Id(36L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(4L).addTo(b);
        return b.build();
    }

    @SuppressWarnings("boxing")
    public static IDataSet buildUpdateResult(Long zeugnisId1, Long zeugnisId2) throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newZeugnis().Id(zeugnisId1).Version(1L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(2L).KlasseId(2L).SchuelerId(1L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newZeugnis().Id(zeugnisId2).Version(1L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(2L).KlasseId(2L).SchuelerId(2L).SchulhalbjahrId(1L).ZeugnisArtId(1L).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(zeugnisId1).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(zeugnisId2).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(1L).ZeugnisId(zeugnisId1).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(1L).ZeugnisId(zeugnisId2).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(zeugnisId1).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(zeugnisId2).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(2L).ZeugnisId(zeugnisId1).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(2L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(1L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("E").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(4L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(1L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("Z").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(4L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(2L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(3L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(5L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(6L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(13L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(2L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(3L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(5L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("X").SchulfachId(6L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.TRUE).Leistungsniveau("G").Note(3L).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(13L).ZeugnisId(zeugnisId2).addTo(b);
        return b.build();
    }
}

