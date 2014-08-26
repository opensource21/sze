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

public class InitZeugnis {

    @SuppressWarnings("boxing")
    public static IDataSet buildInitZeugnis() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newArbeitsgruppe().Id(1L).Version(0L).Klassenstufen("3 4 5 6 8 9 10").Name("AG Ballspiele").Sortierung(10L).addTo(b);
        newArbeitsgruppe().Id(2L).Version(0L).Klassenstufen("7 8 9 10").Name("AG Theater").Sortierung(12L).addTo(b);
        newArbeitsgruppe().Id(3L).Version(0L).Klassenstufen("1 2 3 4 5 6 17 8 9 10").Name("AG Technisches Werken für Fortgeschrittene").Sortierung(20L).addTo(b);
        newArbeitsgruppe().Id(4L).Version(0L).Klassenstufen("").Name("AG Sanitäter").Sortierung(33L).addTo(b);
        newArbeitsgruppe().Id(5L).Version(0L).Klassenstufen("6 7").Name("AG Schülerfirma \"KreaPrint\"").Sortierung(16L).addTo(b);

        newArbeitsUndSozialVerhalten().Id(1L).Version(0L).Klassenstufen("5, 6, 7, 8, 9, 10").Name("Lern-/Leistungsbereitschaft / Mitarbeit").Sortierung(10L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(2L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Ziel- und Ergebnisorientierung").Sortierung(11L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(3L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Kooperationsfähigkeit").Sortierung(12L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(4L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Selbstständigkeit").Sortierung(13L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(5L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Sorgfalt und Ausdauer").Sortierung(14L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(6L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Verlässlichkeit").Sortierung(15L).Typ(0).addTo(b);
        newArbeitsUndSozialVerhalten().Id(7L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Reflexionsfähigkeit").Sortierung(16L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(8L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Konfliktfähigkeit").Sortierung(17L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(9L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Vereinbaren + Einhalten von Regeln / Fairness").Sortierung(18L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(10L).Version(0L).Klassenstufen("5, 6, 7, 8, 9, 10").Name("Hilfsbereitschaft / Achtung anderer").Sortierung(19L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(11L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Übernahme von Verantwortung").Sortierung(20L).Typ(1).addTo(b);
        newArbeitsUndSozialVerhalten().Id(12L).Version(0L).Klassenstufen("5, 6, 17, 8, 9, 10").Name("Mitgestaltung des Gemeinschaftslebens").Sortierung(21L).Typ(1).addTo(b);

        newBemerkungsBaustein().Id(1L).Version(0L).Aktiv(Boolean.TRUE).Name("Rechtschreibschwäche").Text("Auf Beschluss der Klassenkonferenz vom @datum@ ist im Rechtschreiben von den Grundsätzen der Leistungsbewertung im Schuljahr @schuljahr@ abgewichen worden.").addTo(b);
        newKlasse().Id(1L).Version(0L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("").addTo(b);

        newSchueler().Id(1L).Version(0L).Geburtsort("Hamburg").Geburtstag(Timestamp.valueOf("2000-06-15 00:00:00.0")).Geschlecht("m").Name("MUSTERMANN").Vorname("ERWIN").KlasseId(1L).addTo(b);
        newSchueler().Id(2L).Version(0L).Geburtsort("Kiel").Geburtstag(Timestamp.valueOf("2000-03-15 00:00:00.0")).Geschlecht("w").Name("MUSTERFRAU").Vorname("ERNA").KlasseId(1L).addTo(b);

        newSchulamt().Id(1L).Version(0L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ übte das Amt @des Klassensprechers|der Klassensprecherin@ aus.").Name("Klassensprecher").addTo(b);
        newSchulamtsBemerkungsBaustein().Id(1L).Version(0L).Aktiv(Boolean.TRUE).BeschreibenderSatz("").Name("_LEER_").Sortierung(10L).addTo(b);
        newSchulamtsBemerkungsBaustein().Id(2L).Version(0L).Aktiv(Boolean.TRUE).BeschreibenderSatz("@Name@ zeichnete sich dabei durch große Zuverlässigkeit aus.").Name("Zuverlässigkeit").Sortierung(20L).addTo(b);
        newSchulfach().Id(1L).Version(0L).Name("Mathematik").Sortierung(101L).StufenMitAussenDifferenzierung("5 6 7 8 9 10").Typ(0).addTo(b);
        newSchulfach().Id(2L).Version(0L).Name("Deutsch").Sortierung(102L).StufenMitAussenDifferenzierung("5 6 7 8 9 10").StufenMitBinnenDifferenzierung("17").Typ(0).addTo(b);
        newSchulfach().Id(3L).Version(0L).Name("Englisch").Sortierung(103L).StufenMitAussenDifferenzierung("5 6 7 8 9 10").Typ(0).addTo(b);
        newSchulfach().Id(4L).Version(0L).Name("Französisch").Sortierung(104L).StufenMitBinnenDifferenzierung("7 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(5L).Version(0L).Name("Naturwissenschaften").Sortierung(105L).StufenMitBinnenDifferenzierung("5 6 7 9 10").StufenMitStandardBewertung("17").Typ(1).addTo(b);
        newSchulfach().Id(6L).Version(0L).Name("Gesellschaftslehre").Sortierung(106L).StufenMitBinnenDifferenzierung("5 6 7 8").StufenMitStandardBewertung("17").Typ(1).addTo(b);
        newSchulfach().Id(7L).Version(0L).Name("Arbeit/Wirtschaft/Technik").Sortierung(107L).StufenMitStandardBewertung(" 17 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(8L).Version(0L).Name("Sport").Sortierung(108L).StufenMitStandardBewertung("5 6 17 8 9 10").Typ(1).addTo(b);
        newSchulfach().Id(9L).Version(0L).Name("Musik").Sortierung(109L).StufenMitStandardBewertung("5 6").Typ(1).addTo(b);
        newSchulfach().Id(10L).Version(0L).Name("Musik").Sortierung(110L).StufenMitStandardBewertung("7 8 9 10").Typ(2).addTo(b);
        newSchulfach().Id(11L).Version(0L).Name("Kunst").Sortierung(111L).StufenMitStandardBewertung("5 6 7 8 9 10").Typ(2).addTo(b);
        newSchulfach().Id(12L).Version(0L).Name("EDV").Sortierung(112L).StufenMitStandardBewertung("7").Typ(2).addTo(b);
        newSchulfach().Id(13L).Version(0L).Name("Textiles Werken").Sortierung(114L).StufenMitStandardBewertung("5 6").Typ(2).addTo(b);
        newSchulhalbjahr().Id(1L).Version(0L).Halbjahr(0).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);
        newSchulhalbjahr().Id(2L).Version(0L).Halbjahr(1).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);
        newZeugnisArt().Id(1L).Version(0L).AbschlussGrad("").Aktiv(Boolean.TRUE).Name("Standard-Zeugnis").NoteAlsTextDarstellen(Boolean.FALSE).PlatzFuerSiegel(Boolean.FALSE).PrintVersetzungsbemerkung(Boolean.TRUE).Sortierung(10L).Titel("Zeugnis").addTo(b);
        newZeugnisFormular().Id(1L).Version(0L).AusgabeDatum(Date.valueOf("2013-01-31")).Beschreibung("2013 erstes Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(1L).SchulhalbjahrId(1L).addTo(b);
        newZeugnisFormular().Id(2L).Version(0L).AusgabeDatum(Date.valueOf("2013-07-01")).Beschreibung("2013 zweites Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal").KlasseId(1L).SchulhalbjahrId(2L).addTo(b);

        return b.build();
    }

    @SuppressWarnings("boxing")
    public static IDataSet buildInitResult(Long zeugnisId1, Long zeugnisId2,
            Long formularId, Long halbjahrId) throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newZeugnis().Id(zeugnisId1).Version(0L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(formularId).KlasseId(1L).SchuelerId(1L).SchulhalbjahrId(halbjahrId).ZeugnisArtId(1L).addTo(b);
        newZeugnis().Id(zeugnisId2).Version(0L).AnzahlFehltageGesamt(0).AnzahlFehltageUnentschuldigt(0).AnzahlVerspaetungen(0).BuBewertungsText("").KlassenZielAusgeschlossen(Boolean.FALSE).KlassenZielGefaehrdet(Boolean.FALSE).KlassenZielWurdeNichtErreicht(Boolean.FALSE).RuecktAuf(Boolean.TRUE).FormularId(formularId).KlasseId(1L).SchuelerId(2L).SchulhalbjahrId(halbjahrId).ZeugnisArtId(1L).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(2L).ZeugnisId(zeugnisId1).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(zeugnisId1).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(2L).ZeugnisId(zeugnisId2).addTo(b);
        newAgBewertung().Id(new GreaterThan(0)).Version(0L).Teilgenommen(Boolean.FALSE).ArbeitsgruppeId(5L).ZeugnisId(zeugnisId2).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(1L).ZeugnisId(zeugnisId1).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(zeugnisId1).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(1L).ZeugnisId(zeugnisId2).addTo(b);
        newAvSvBewertung().Id(new GreaterThan(0)).Version(0L).ArbeitsUndSozialVerhaltenId(10L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(1L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(2L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(3L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(4L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(5L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(6L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(10L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(zeugnisId1).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(1L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(2L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("ZweiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(3L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(4L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(5L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("DreiNiveauBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Leistungsniveau("G").Relevant(Boolean.TRUE).SonderNote("").SchulfachId(6L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(10L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(11L).ZeugnisId(zeugnisId2).addTo(b);
        newBewertung().Class("StandardBewertung").Id(new GreaterThan(0)).Version(0L).LeistungNurSchwachAusreichend(Boolean.FALSE).Relevant(Boolean.FALSE).SonderNote("").SchulfachId(12L).ZeugnisId(zeugnisId2).addTo(b);
        return b.build();

    }
}

