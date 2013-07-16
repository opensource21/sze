// Zeugnis.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.jpa.helper.VersionedModel;

import net.sf.oval.constraint.Size;
import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.AgBewertung;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.StringUtil;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Ein Schulzeugnis.
 *
 */
@Entity
@Table(name = "zeugnis",
        uniqueConstraints = @UniqueConstraint(columnNames = {"schulhalbjahr_id",
        "schueler_id"}, name = "UK_ZEUGNIS_HALBJAHR_SCHUELER"))
public class Zeugnis extends VersionedModel implements Serializable,
        Comparable<Zeugnis> {

    // TODO Objekt-Constraint.
//  if (value < obj.anzahlFehltageUnentschuldigt) {
//      return 'zeugnis.formular.fehltageUnentschuldigtGtGesamt'
//  }

    /* Individuelle Abweichung vom Leitspruch des Formulars. */

    /** The individueller leitspruch. */
    @Column(name = "individueller_leitspruch", length = 300)
    @Size(max = 300)
    private String individuellerLeitspruch;

    /** The quelle individueller leitspruch. */
    @Column(name = "quelle_individueller_leitspruch", length = 60)
    @Size(max = 60)
    private String quelleIndividuellerLeitspruch;

    /* Individuelle Abweichung vom zweiten Leitspruch des Formulars. */

    /** The individueller leitspruch2. */
    @Column(name = "individueller_leitspruch2", length = 300)
    @Size(max = 300)
    private String individuellerLeitspruch2;

    /** The quelle individueller leitspruch2. */
    @Column(name = "quelle_individueller_leitspruch2", length = 60)
    @Size(max = 60)
    private String quelleIndividuellerLeitspruch2;

    /* Individuelle Abweichung vom Ausgabe-Datum des Formulars. */

    /** The individuelles ausgabe datum. */
    @Column(name = "individuelles_ausgabe_datum")
    private Date individuellesAusgabeDatum;

    /** The bu bewertungs text. */
    @Column(name = "bu_bewertungs_text", length = 1500)
    @Size(max = 1500)
    @ValidVariableText
    private String buBewertungsText = "";

    /** The anzahl fehltage gesamt. */
    @Column(name = "anzahl_fehltage_gesamt", nullable = false)

    private Integer anzahlFehltageGesamt = Integer.valueOf(0);

    /** The anzahl fehltage unentschuldigt. */
    @Column(name = "anzahl_fehltage_unentschuldigt", nullable = false)

    private Integer anzahlFehltageUnentschuldigt = Integer.valueOf(0);

    /** The anzahl verspaetungen. */
    @Column(name = "anzahl_verspaetungen", nullable = false)

    private Integer anzahlVerspaetungen = Integer.valueOf(0);

    // 2.HJ

    /** The rueckt auf. */
    @Column(name = "rueckt_auf", nullable = false)

    private Boolean ruecktAuf = Boolean.TRUE;

    /** The klassen ziel wurde nicht erreicht. */
    @Column(name = "klassen_ziel_wurde_nicht_erreicht", nullable = false)

    private Boolean klassenZielWurdeNichtErreicht = Boolean.FALSE;

    // 1.HJ

    /** The klassen ziel gefaehrdet. */
    @Column(name = "klassen_ziel_gefaehrdet", nullable = false)

    private Boolean klassenZielGefaehrdet = Boolean.FALSE;

    /** The klassen ziel ausgeschlossen. */
    @Column(name = "klassen_ziel_ausgeschlossen", nullable = false)

    private Boolean klassenZielAusgeschlossen = Boolean.FALSE;

    // bi-directional many-to-one association to AgBewertung

    /** The ag bewertungen. */
    @OneToMany(mappedBy = "zeugnis")
    private List<AgBewertung> agBewertungen;

    // bi-directional many-to-one association to AvSvBewertung

    /** The av sv bewertungen. */
    @OneToMany(mappedBy = "zeugnis")
    private List<AvSvBewertung> avSvBewertungen;

    // bi-directional many-to-one association to Bemerkung

    /** The bemerkungen. */
    @OneToMany(mappedBy = "zeugnis")
    private List<Bemerkung> bemerkungen;

    // bi-directional many-to-one association to Bewertung

    /** The bewertungen. */
    @OneToMany(mappedBy = "zeugnis")
    private List<Bewertung> bewertungen;

    // bi-directional many-to-one association to SchulamtsBemerkung

    /** The schulamts bemerkungen. */
    @OneToMany(mappedBy = "zeugnis")
    private List<SchulamtsBemerkung> schulamtsBemerkungen;

    // bi-directional many-to-one association to Klasse

    /** The klasse. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "klasse_id", nullable = false)
    private Klasse klasse;

    // bi-directional many-to-one association to Schueler

    /** The schueler. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schueler_id", nullable = false)
    private Schueler schueler;

    // bi-directional many-to-one association to Schulhalbjahr

    /** The schulhalbjahr. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulhalbjahr_id", nullable = false)
    private Schulhalbjahr schulhalbjahr;

    // bi-directional many-to-one association to SolbewertungsText

    /** The so l bewertungs text. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "solbewertungs_text_id")
    private SoLBewertungsText soLBewertungsText;

    // bi-directional many-to-one association to ZeugnisArt

    /** The zeugnis art. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_art_id", nullable = false)

    private ZeugnisArt zeugnisArt;

    // bi-directional many-to-one association to ZeugnisFormular

    /** The formular. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "formular_id", nullable = false)

    private ZeugnisFormular formular;

    /**
     * Gets the anzahl fehltage gesamt.
     *
     * @return the anzahl fehltage gesamt
     */
    public Integer getAnzahlFehltageGesamt() {
        return this.anzahlFehltageGesamt;
    }

    /**
     * Sets the anzahl fehltage gesamt.
     *
     * @param anzahlFehltageGesamt the new anzahl fehltage gesamt
     */
    public void setAnzahlFehltageGesamt(final Integer anzahlFehltageGesamt) {
        this.anzahlFehltageGesamt = anzahlFehltageGesamt;
    }

    /**
     * Gets the anzahl fehltage unentschuldigt.
     *
     * @return the anzahl fehltage unentschuldigt
     */
    public Integer getAnzahlFehltageUnentschuldigt() {
        return this.anzahlFehltageUnentschuldigt;
    }

    /**
     * Sets the anzahl fehltage unentschuldigt.
     *
     * @param anzahlFehltageUnentschuldigt the new anzahl fehltage
     *            unentschuldigt
     */
    public void setAnzahlFehltageUnentschuldigt(
            final Integer anzahlFehltageUnentschuldigt) {
        this.anzahlFehltageUnentschuldigt = anzahlFehltageUnentschuldigt;
    }

    /**
     * Gets the anzahl verspaetungen.
     *
     * @return the anzahl verspaetungen
     */
    public Integer getAnzahlVerspaetungen() {
        return this.anzahlVerspaetungen;
    }

    /**
     * Sets the anzahl verspaetungen.
     *
     * @param anzahlVerspaetungen the new anzahl verspaetungen
     */
    public void setAnzahlVerspaetungen(final Integer anzahlVerspaetungen) {
        this.anzahlVerspaetungen = anzahlVerspaetungen;
    }

    /**
     * Gets the bu bewertungs text.
     *
     * @return the bu bewertungs text
     */
    public String getBuBewertungsText() {
        return this.buBewertungsText;
    }

    /**
     * Sets the bu bewertungs text.
     *
     * @param buBewertungsText the new bu bewertungs text
     */
    public void setBuBewertungsText(final String buBewertungsText) {
        this.buBewertungsText = buBewertungsText;
    }

    /**
     * Gets the individueller leitspruch.
     *
     * @return the individueller leitspruch
     */
    public String getIndividuellerLeitspruch() {
        return this.individuellerLeitspruch;
    }

    /**
     * Sets the individueller leitspruch.
     *
     * @param individuellerLeitspruch the new individueller leitspruch
     */
    public void setIndividuellerLeitspruch(
            final String individuellerLeitspruch) {
        this.individuellerLeitspruch = individuellerLeitspruch;
    }

    /**
     * Gets the individueller leitspruch2.
     *
     * @return the individueller leitspruch2
     */
    public String getIndividuellerLeitspruch2() {
        return this.individuellerLeitspruch2;
    }

    /**
     * Sets the individueller leitspruch2.
     *
     * @param individuellerLeitspruch2 the new individueller leitspruch2
     */
    public void setIndividuellerLeitspruch2(
            final String individuellerLeitspruch2) {
        this.individuellerLeitspruch2 = individuellerLeitspruch2;
    }

    /**
     * Gets the individuelles ausgabe datum.
     *
     * @return the individuelles ausgabe datum
     */
    public Date getIndividuellesAusgabeDatum() {
        return this.individuellesAusgabeDatum;
    }

    /**
     * Sets the individuelles ausgabe datum.
     *
     * @param individuellesAusgabeDatum the new individuelles ausgabe datum
     */
    public void setIndividuellesAusgabeDatum(
            final Date individuellesAusgabeDatum) {
        this.individuellesAusgabeDatum = individuellesAusgabeDatum;
    }

    /**
     * Gets the klassen ziel ausgeschlossen.
     *
     * @return the klassen ziel ausgeschlossen
     */
    public Boolean getKlassenZielAusgeschlossen() {
        return this.klassenZielAusgeschlossen;
    }

    /**
     * Sets the klassen ziel ausgeschlossen.
     *
     * @param klassenZielAusgeschlossen the new klassen ziel ausgeschlossen
     */
    public void setKlassenZielAusgeschlossen(
            final Boolean klassenZielAusgeschlossen) {
        this.klassenZielAusgeschlossen = klassenZielAusgeschlossen;
    }

    /**
     * Gets the klassen ziel gefaehrdet.
     *
     * @return the klassen ziel gefaehrdet
     */
    public Boolean getKlassenZielGefaehrdet() {
        return this.klassenZielGefaehrdet;
    }

    /**
     * Sets the klassen ziel gefaehrdet.
     *
     * @param klassenZielGefaehrdet the new klassen ziel gefaehrdet
     */
    public void setKlassenZielGefaehrdet(final Boolean klassenZielGefaehrdet) {
        this.klassenZielGefaehrdet = klassenZielGefaehrdet;
    }

    /**
     * Gets the klassen ziel wurde nicht erreicht.
     *
     * @return the klassen ziel wurde nicht erreicht
     */
    public Boolean getKlassenZielWurdeNichtErreicht() {
        return this.klassenZielWurdeNichtErreicht;
    }

    /**
     * Sets the klassen ziel wurde nicht erreicht.
     *
     * @param klassenZielWurdeNichtErreicht the new klassen ziel wurde nicht
     *            erreicht
     */
    public void setKlassenZielWurdeNichtErreicht(
            final Boolean klassenZielWurdeNichtErreicht) {
        this.klassenZielWurdeNichtErreicht = klassenZielWurdeNichtErreicht;
    }

    /**
     * Gets the quelle individueller leitspruch.
     *
     * @return the quelle individueller leitspruch
     */
    public String getQuelleIndividuellerLeitspruch() {
        return this.quelleIndividuellerLeitspruch;
    }

    /**
     * Sets the quelle individueller leitspruch.
     *
     * @param quelleIndividuellerLeitspruch the new quelle individueller
     *            leitspruch
     */
    public void setQuelleIndividuellerLeitspruch(
            final String quelleIndividuellerLeitspruch) {
        this.quelleIndividuellerLeitspruch = quelleIndividuellerLeitspruch;
    }

    /**
     * Gets the quelle individueller leitspruch2.
     *
     * @return the quelle individueller leitspruch2
     */
    public String getQuelleIndividuellerLeitspruch2() {
        return this.quelleIndividuellerLeitspruch2;
    }

    /**
     * Sets the quelle individueller leitspruch2.
     *
     * @param quelleIndividuellerLeitspruch2 the new quelle individueller
     *            leitspruch2
     */
    public void setQuelleIndividuellerLeitspruch2(
            final String quelleIndividuellerLeitspruch2) {
        this.quelleIndividuellerLeitspruch2 = quelleIndividuellerLeitspruch2;
    }

    /**
     * Gets the rueckt auf.
     *
     * @return the rueckt auf
     */
    public Boolean getRuecktAuf() {
        return this.ruecktAuf;
    }

    /**
     * Sets the rueckt auf.
     *
     * @param ruecktAuf the new rueckt auf
     */
    public void setRuecktAuf(final Boolean ruecktAuf) {
        this.ruecktAuf = ruecktAuf;
    }

    /**
     * Gets the ag bewertungen.
     *
     * @return the ag bewertungen
     */
    public List<AgBewertung> getAgBewertungen() {
        return this.agBewertungen;
    }

    /**
     * Sets the ag bewertungen.
     *
     * @param agBewertungs the new ag bewertungen
     */
    public void setAgBewertungen(final List<AgBewertung> agBewertungs) {
        this.agBewertungen = agBewertungs;
    }

    /**
     * Gets the av sv bewertungen.
     *
     * @return the av sv bewertungen
     */
    public List<AvSvBewertung> getAvSvBewertungen() {
        return this.avSvBewertungen;
    }

    /**
     * Sets the av sv bewertungen.
     *
     * @param avSvBewertungs the new av sv bewertungen
     */
    public void setAvSvBewertungen(final List<AvSvBewertung> avSvBewertungs) {
        this.avSvBewertungen = avSvBewertungs;
    }

    /**
     * Gets the bemerkungen.
     *
     * @return the bemerkungen
     */
    public List<Bemerkung> getBemerkungen() {
        return this.bemerkungen;
    }

    /**
     * Sets the bemerkungen.
     *
     * @param bemerkungs the new bemerkungen
     */
    public void setBemerkungen(final List<Bemerkung> bemerkungs) {
        this.bemerkungen = bemerkungs;
    }

    /**
     * Gets the bewertungen.
     *
     * @return the bewertungen
     */
    public List<Bewertung> getBewertungen() {
        return this.bewertungen;
    }

    /**
     * Sets the bewertungen.
     *
     * @param bewertungs the new bewertungen
     */
    public void setBewertungen(final List<Bewertung> bewertungs) {
        this.bewertungen = bewertungs;
    }

    /**
     * Gets the schulamts bemerkungen.
     *
     * @return the schulamts bemerkungen
     */
    public List<SchulamtsBemerkung> getSchulamtsBemerkungen() {
        return this.schulamtsBemerkungen;
    }

    /**
     * Sets the schulamts bemerkungen.
     *
     * @param schulamtsBemerkungs the new schulamts bemerkungen
     */
    public void setSchulamtsBemerkungen(
            final List<SchulamtsBemerkung> schulamtsBemerkungs) {
        this.schulamtsBemerkungen = schulamtsBemerkungs;
    }

    /**
     * Gets the klasse.
     *
     * @return the klasse
     */
    public Klasse getKlasse() {
        return this.klasse;
    }

    /**
     * Sets the klasse.
     *
     * @param klasse the new klasse
     */
    public void setKlasse(final Klasse klasse) {
        this.klasse = klasse;
    }

    /**
     * Gets the schueler.
     *
     * @return the schueler
     */
    public Schueler getSchueler() {
        return this.schueler;
    }

    /**
     * Sets the schueler.
     *
     * @param schueler the new schueler
     */
    public void setSchueler(final Schueler schueler) {
        this.schueler = schueler;
    }

    /**
     * Gets the schulhalbjahr.
     *
     * @return the schulhalbjahr
     */
    public Schulhalbjahr getSchulhalbjahr() {
        return this.schulhalbjahr;
    }

    /**
     * Sets the schulhalbjahr.
     *
     * @param schulhalbjahr the new schulhalbjahr
     */
    public void setSchulhalbjahr(final Schulhalbjahr schulhalbjahr) {
        this.schulhalbjahr = schulhalbjahr;
    }

    /**
     * Gets the so l bewertungs text.
     *
     * @return the so l bewertungs text
     */
    public SoLBewertungsText getSoLBewertungsText() {
        return this.soLBewertungsText;
    }

    /**
     * Sets the so l bewertungs text.
     *
     * @param solbewertungsText the new so l bewertungs text
     */
    public void setSoLBewertungsText(
            final SoLBewertungsText solbewertungsText) {
        this.soLBewertungsText = solbewertungsText;
    }

    /**
     * Gets the zeugnis art.
     *
     * @return the zeugnis art
     */
    public ZeugnisArt getZeugnisArt() {
        return this.zeugnisArt;
    }

    /**
     * Sets the zeugnis art.
     *
     * @param zeugnisArt the new zeugnis art
     */
    public void setZeugnisArt(final ZeugnisArt zeugnisArt) {
        this.zeugnisArt = zeugnisArt;
    }

    /**
     * Gets the formular.
     *
     * @return the formular
     */
    public ZeugnisFormular getFormular() {
        return this.formular;
    }

    /**
     * Sets the formular.
     *
     * @param zeugnisFormular the new formular
     */
    public void setFormular(final ZeugnisFormular zeugnisFormular) {
        this.formular = zeugnisFormular;
    }

    // ***********************************************************************

    @Override
    public int compareTo(final Zeugnis other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.schulhalbjahr, other.schulhalbjahr);
        compareBuilder.append(this.klasse, other.klasse);
        compareBuilder.append(this.schueler, other.schueler);
        return compareBuilder.toComparison();
    }

    /**
     * Erstellt eine Liste aller Arbeits- und Sozialverhalten-Bewertungen,
     * wobei sichergestellt wird, dass die Arbeitsverhalten-Bewertungen immer
     * an ungerader Position sind und die SVBewertungen an gerader. Lücken
     * werden mit null aufgefüllt.
     * @return die geordnete Liste.
     */
    public List<AvSvBewertung> createAvSvBewertungsList() {
        final List<AvSvBewertung> avSvList = new ArrayList<AvSvBewertung>();
        final List<AvSvBewertung> avBewertungen =
                new ArrayList<AvSvBewertung>();
        final List<AvSvBewertung> svBewertungen =
                new ArrayList<AvSvBewertung>();
        // Aufteilung der Bewertungen auf die beide Spezialfälle.
        for (final AvSvBewertung avSvBewertung : avSvBewertungen) {
            final AvSvTyp typ = avSvBewertung.getArbeitsUndSozialVerhalten()
                    .getTyp();
            switch (typ) {
            case SOZIALVERHALTEN:
                svBewertungen.add(avSvBewertung);
                break;
            case ARBEITSVERHALTEN:
                avBewertungen.add(avSvBewertung);
                break;
            default:
                throw new IllegalArgumentException("Typ ist ungültig: " + typ);
            }
        }

        // /Sortieren der Listen.
        Collections.sort(avBewertungen);
        Collections.sort(svBewertungen);

        for (int i = 0;
                i < Math.max(avBewertungen.size(), svBewertungen.size()); i++) {
            if (i < avBewertungen.size()) {
                avSvList.add(avBewertungen.get(i));
            } else {
                avSvList.add(null);
            }

            if (i < svBewertungen.size()) {
                avSvList.add(svBewertungen.get(i));
            } else {
                avSvList.add(null);
            }
        }

        return avSvList;
    }

    @Override
    public String toString() {
        return schulhalbjahr + " " + klasse.calculateKlassenname(schulhalbjahr
                .getJahr()) + " " + schueler;
    }

    // TODO toPrintMap elegant implementieren, evtl BeanWrapper?.
//  String toPrintMap(final Map<String, String> printMap) {
//      //Schuler, ergänzen
//      schueler.toPrintMap(printMap)
//      //Klasse ergänzen
//      printMap['klasse'] = klasse.calculateKlassenname(schulhalbjahr.jahr)
//      //schulhalbjahr
//      schulhalbjahr.toPrintMap(printMap)
//      //formular
//      final formatter = new SimpleDateFormat('dd.MM.yyyy', Locale.GERMANY);
//      printMap['titel'] = zeugnisArt.titel
//      printMap['abschlussGrad'] = zeugnisArt.abschlussGrad
//
//      if (individuellerLeitspruch) {
//          printMap['leitspruch'] = individuellerLeitspruch
//      } else {
//          printMap['leitspruch'] = formular.leitspruch
//      }
//
//      if (quelleIndividuellerLeitspruch) {
//          printMap['quelleLeitspruch'] = quelleIndividuellerLeitspruch
//      } else {
//          printMap['quelleLeitspruch'] = formular.quelleLeitspruch
//      }
//
//
//      if (individuellerLeitspruch2) {
//          printMap['leitspruch2'] = individuellerLeitspruch2
//      } else {
//          printMap['leitspruch2'] = formular.leitspruch2
//      }
//
//      if (quelleIndividuellerLeitspruch2) {
//          printMap['quelleLeitspruch2'] = quelleIndividuellerLeitspruch2
//      } else {
//          printMap['quelleLeitspruch2'] = formular.quelleLeitspruch2
//      }
//
//      if (individuellesAusgabeDatum) {
//          printMap['ausgabeDatum'] = formatter.format(individuellesAusgabeDatum)
//      } else {
//          printMap['ausgabeDatum'] = formatter.format(formular.ausgabeDatum)
//      }
//      printMap['arbeitsgruppen']=createArbeitsgruppenSatz()
//      printMap['anzahlFehltageGesamt'] = dayToString(anzahlFehltageGesamt)
//      printMap['anzahlFehltageUnentschuldigt'] = dayToString(anzahlFehltageUnentschuldigt)
//      printMap['anzahlVerspaetungen'] = (anzahlVerspaetungen==0)?
//    VariableUtility.PLATZHALTER_LEER:"${anzahlVerspaetungen}";
//      def versetzungsBemerkung = ""
//      if (schulhalbjahr.halbjahr==Halbjahr.Beide_Halbjahre) {
//          if (klassenZielWurdeNichtErreicht) {
//              versetzungsBemerkung = "Das Klassenziel wurde nicht erreicht. "
//          }
//          if (ruecktAuf && zeugnisArt.printVersetzungsbemerkung) {
//              def nextKlassenstufe = klasse.calculateKlassenstufe(schulhalbjahr.jahr) +1
//              versetzungsBemerkung = versetzungsBemerkung +
//    "${schueler.vorname} r\u00fcckt auf in Klasse ${nextKlassenstufe}. "
//          }
//      } else {
//          if (klassenZielGefaehrdet) {
//              versetzungsBemerkung = "Das Erreichen des Klassenziels ist gef\u00e4hrdet. "
//          }
//          if (klassenZielAusgeschlossen) {
//              versetzungsBemerkung = "Das Erreichen des Klassenziels erscheint ausgeschlossen. "
//          }
//      }
//      printMap['bemerkung_versetzung'] = versetzungsBemerkung;
//      printMap['buBewertungsText'] = VariableUtility.createPrintText(
//              buBewertungsText?:VariableUtility.PLATZHALTER_LEER,
//    schueler.rufname, schueler.vorname, schueler.geschlecht,
//              formular.nachteilsAusgleichsDatum, false, printMap['shj_jahr'])
//      printMap['soLBewertungsTextFix'] = soLBewertungsText?.text?:VariableUtility.PLATZHALTER_LEER
//      def schwachAusreichendFaecher = []
//      bewertungen.sort().each{Bewertung bw ->
//          bw.toPrintMap(printMap, zeugnisArt.noteAlsTextDarstellen)
//          if (bw.leistungNurSchwachAusreichend) {
//              schwachAusreichendFaecher << bw.schulfach.name
//          }
//      }
//      avSvBewertungen.each{it.toPrintMap(printMap)}
//      def schwachausreichendBemerkung
//      switch (schwachAusreichendFaecher.size()) {
//      case 0:
//          //Keine Bemerkung nötig
//          schwachausreichendBemerkung = ""
//          break;
//      case 1:
//          schwachausreichendBemerkung = "Die Leistungen im Fach " +
//                  "${schwachAusreichendFaecher[0]} waren nur schwach ausreichend. "
//          break;
//      default:
//          schwachausreichendBemerkung = "Die Leistungen in den Fächern " +
//                  "${schwachAusreichendFaecher[0..-2].join(', ')} und " +
//                  "${schwachAusreichendFaecher[-1]} waren nur schwach ausreichend. "
//      }
//      printMap['bemerkung_schwachausreichend'] = schwachausreichendBemerkung
//      StringBuffer allgemeineBemerkungen = new StringBuffer("")
//      bemerkungen?.sort()?.each {Bemerkung aBemerkung ->
//          allgemeineBemerkungen.append(aBemerkung.createPrintText(schueler,
//                  formular.nachteilsAusgleichsDatum, printMap['shj_jahr']))
//      }
//      printMap['bemerkung_allgemein'] = allgemeineBemerkungen.toString()
//      StringBuffer schulamtsBemerkungsText = new StringBuffer("")
//      schulamtsBemerkungen?.sort()?.each {aBemerkung ->
//          schulamtsBemerkungsText.append(aBemerkung.createPrintText(schueler,
//                  null, printMap['shj_jahr'])).append(' ')
//      }
//      printMap['bemerkung_schulamt'] = schulamtsBemerkungsText.toString()
//    ?:VariableUtility.PLATZHALTER_LEER
//  }

    /**
     * Wandelt die Anzahl an Tage in Textform.
     * @param day die Anzahl an Tage.
     * @return die Anzahl an Tage in Textform.
     */
    private String dayToString(final Long day) {
        if (day == null) {
            return VariableUtility.PLATZHALTER_LEER;
        } else if (day.longValue() == 1) {
            return "1 Tag";
        } else {
            return day + " Tage";
        }
    }

    /**
     * Erzeugt eine Satz der den Einsatz in Arbeitsgruppen beschreibt.
     * @return ein Satz der den Einsatz in Arbeitsgruppen beschreibt.
     */
    public String createArbeitsgruppenSatz() {
        final List<String> besuchteArbeitsgruppen = new ArrayList<String>();
        if ((agBewertungen != null) && !agBewertungen.isEmpty()) {
            Collections.sort(agBewertungen);

            for (final AgBewertung agBewertung : agBewertungen) {
                besuchteArbeitsgruppen.add(agBewertung.getArbeitsgruppe()
                        .getName());
            }
        }

        final StringBuilder arbeitsgruppenSatz = new StringBuilder();
        final String name = StringUtil.containsInformation(schueler
                .getRufname()) ? schueler.getRufname() : schueler.getVorname();
        final int anzAGs = besuchteArbeitsgruppen.size();
        switch (anzAGs) {
        case 0:
            // Keine Bemerkung nötig
            arbeitsgruppenSatz.append(VariableUtility.PLATZHALTER_LEER);
            break;
        case 1:
            arbeitsgruppenSatz.append(name).append(" hat an der ").append(
                    besuchteArbeitsgruppen.get(0)).append(" teilgenommen. ");
            break;
        case 2:
            arbeitsgruppenSatz.append(name).append(" hat an der ");
            arbeitsgruppenSatz.append(besuchteArbeitsgruppen.get(anzAGs - 2))
                    .append(" und der ").append(besuchteArbeitsgruppen.get(
                    anzAGs - 1)).append(" teilgenommen. ");
        default:
            arbeitsgruppenSatz.append(name).append(" hat an der ");

            for (int i = 0; i < anzAGs - 2; i++) {
                arbeitsgruppenSatz.append(besuchteArbeitsgruppen.get(i)).append(
                        ", der ");
            }

            arbeitsgruppenSatz.append(besuchteArbeitsgruppen.get(anzAGs - 2))
                    .append(" und der ").append(besuchteArbeitsgruppen.get(
                    anzAGs - 1)).append(" teilgenommen. ");

        }

        return arbeitsgruppenSatz.toString();
    }

    /**
     * Berechnet einen Index, der jedes Zeugnis einer Klassenstufe und einem
     * Halbjahr eindeutig zuweist. Dabei gilt, dass der Index Streng aufsteigend
     * ist.
     * @return einen Index, der jedes Zeugnis einer Klassenstufe und einem
     * Halbjahr eindeutig zuweist
     */
    public int calculateKlasssenstufenHalbjahresIndex() {
        final int klassenstufe = klasse.calculateKlassenstufe(schulhalbjahr
                .getJahr());
        final int halbjahresId = schulhalbjahr.getHalbjahr().getId();
        return klassenstufe * 10 + halbjahresId;
    }
}
