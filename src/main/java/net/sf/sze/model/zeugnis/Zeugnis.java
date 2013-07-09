// Zeugnis.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

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

import net.sf.oval.constraint.Size;
import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.AgBewertung;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.StringUtil;
import net.sf.sze.util.VariableUtility;
import net.sf.sze.zeugnis.AvSvTyp;

import org.apache.commons.lang.builder.CompareToBuilder;

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
    @Column(name = "individueller_leitspruch", length = 300)
    @Size(max = 300)
    private String individuellerLeitspruch;

    @Column(name = "quelle_individueller_leitspruch", length = 60)
    @Size(max = 60)
    private String quelleIndividuellerLeitspruch;


    /* Individuelle Abweichung vom zweiten Leitspruch des Formulars. */
    @Column(name = "individueller_leitspruch2", length = 300)
    @Size(max = 300)
    private String individuellerLeitspruch2;

    @Column(name = "quelle_individueller_leitspruch2", length = 60)
    @Size(max = 60)
    private String quelleIndividuellerLeitspruch2;


    /* Individuelle Abweichung vom Ausgabe-Datum des Formulars. */
    @Column(name = "individuelles_ausgabe_datum")
    private Date individuellesAusgabeDatum;


    @Column(name = "bu_bewertungs_text", length = 1500)
    @Size(max = 1500)
    @ValidVariableText
    private String buBewertungsText = "";

    @Column(name = "anzahl_fehltage_gesamt", nullable = false)
    
    private Integer anzahlFehltageGesamt = Integer.valueOf(0);

    @Column(name = "anzahl_fehltage_unentschuldigt", nullable = false)
    
    private Integer anzahlFehltageUnentschuldigt = Integer.valueOf(0);

    @Column(name = "anzahl_verspaetungen", nullable = false)
    
    private Integer anzahlVerspaetungen = Integer.valueOf(0);

    // 2.HJ
    @Column(name = "rueckt_auf", nullable = false)
    
    private Boolean ruecktAuf = Boolean.TRUE;

    @Column(name = "klassen_ziel_wurde_nicht_erreicht", nullable = false)
    
    private Boolean klassenZielWurdeNichtErreicht = Boolean.FALSE;


    // 1.HJ
    @Column(name = "klassen_ziel_gefaehrdet", nullable = false)
    
    private Boolean klassenZielGefaehrdet = Boolean.FALSE;

    @Column(name = "klassen_ziel_ausgeschlossen", nullable = false)
    
    private Boolean klassenZielAusgeschlossen = Boolean.FALSE;



    // bi-directional many-to-one association to AgBewertung
    @OneToMany(mappedBy = "zeugnis")
    private List<AgBewertung> agBewertungen;

    // bi-directional many-to-one association to AvSvBewertung
    @OneToMany(mappedBy = "zeugnis")
    private List<AvSvBewertung> avSvBewertungen;

    // bi-directional many-to-one association to Bemerkung
    @OneToMany(mappedBy = "zeugnis")
    private List<Bemerkung> bemerkungen;

    // bi-directional many-to-one association to Bewertung
    @OneToMany(mappedBy = "zeugnis")
    private List<Bewertung> bewertungen;

    // bi-directional many-to-one association to SchulamtsBemerkung
    @OneToMany(mappedBy = "zeugnis")
    private List<SchulamtsBemerkung> schulamtsBemerkungen;

    // bi-directional many-to-one association to Klasse
    @ManyToOne(optional=false)
    @JoinColumn(name = "klasse_id", nullable = false)
    
    private Klasse klasse;

    // bi-directional many-to-one association to Schueler
    @ManyToOne(optional=false)
    @JoinColumn(name = "schueler_id", nullable = false)
    
    private Schueler schueler;

    // bi-directional many-to-one association to Schulhalbjahr
    @ManyToOne(optional=false)
    @JoinColumn(name = "schulhalbjahr_id", nullable = false)
    private Schulhalbjahr schulhalbjahr;

    // bi-directional many-to-one association to SolbewertungsText
    @ManyToOne(optional=false)
    @JoinColumn(name = "solbewertungs_text_id")
    private SoLBewertungsText soLBewertungsText;

    // bi-directional many-to-one association to ZeugnisArt
    @ManyToOne(optional=false)
    @JoinColumn(name = "zeugnis_art_id", nullable = false)
    
    private ZeugnisArt zeugnisArt;

    // bi-directional many-to-one association to ZeugnisFormular
    @ManyToOne(optional=false)
    @JoinColumn(name = "formular_id", nullable = false)
    
    private ZeugnisFormular formular;


    public Integer getAnzahlFehltageGesamt() {
        return this.anzahlFehltageGesamt;
    }

    public void setAnzahlFehltageGesamt(final Integer anzahlFehltageGesamt) {
        this.anzahlFehltageGesamt = anzahlFehltageGesamt;
    }

    public Integer getAnzahlFehltageUnentschuldigt() {
        return this.anzahlFehltageUnentschuldigt;
    }

    public void setAnzahlFehltageUnentschuldigt(
            final Integer anzahlFehltageUnentschuldigt) {
        this.anzahlFehltageUnentschuldigt = anzahlFehltageUnentschuldigt;
    }

    public Integer getAnzahlVerspaetungen() {
        return this.anzahlVerspaetungen;
    }

    public void setAnzahlVerspaetungen(final Integer anzahlVerspaetungen) {
        this.anzahlVerspaetungen = anzahlVerspaetungen;
    }

    public String getBuBewertungsText() {
        return this.buBewertungsText;
    }

    public void setBuBewertungsText(final String buBewertungsText) {
        this.buBewertungsText = buBewertungsText;
    }

    public String getIndividuellerLeitspruch() {
        return this.individuellerLeitspruch;
    }

    public void setIndividuellerLeitspruch(
            final String individuellerLeitspruch) {
        this.individuellerLeitspruch = individuellerLeitspruch;
    }

    public String getIndividuellerLeitspruch2() {
        return this.individuellerLeitspruch2;
    }

    public void setIndividuellerLeitspruch2(
            final String individuellerLeitspruch2) {
        this.individuellerLeitspruch2 = individuellerLeitspruch2;
    }

    public Date getIndividuellesAusgabeDatum() {
        return this.individuellesAusgabeDatum;
    }

    public void setIndividuellesAusgabeDatum(
            final Date individuellesAusgabeDatum) {
        this.individuellesAusgabeDatum = individuellesAusgabeDatum;
    }

    public Boolean getKlassenZielAusgeschlossen() {
        return this.klassenZielAusgeschlossen;
    }

    public void setKlassenZielAusgeschlossen(
            final Boolean klassenZielAusgeschlossen) {
        this.klassenZielAusgeschlossen = klassenZielAusgeschlossen;
    }

    public Boolean getKlassenZielGefaehrdet() {
        return this.klassenZielGefaehrdet;
    }

    public void setKlassenZielGefaehrdet(final Boolean klassenZielGefaehrdet) {
        this.klassenZielGefaehrdet = klassenZielGefaehrdet;
    }

    public Boolean getKlassenZielWurdeNichtErreicht() {
        return this.klassenZielWurdeNichtErreicht;
    }

    public void setKlassenZielWurdeNichtErreicht(
            final Boolean klassenZielWurdeNichtErreicht) {
        this.klassenZielWurdeNichtErreicht = klassenZielWurdeNichtErreicht;
    }

    public String getQuelleIndividuellerLeitspruch() {
        return this.quelleIndividuellerLeitspruch;
    }

    public void setQuelleIndividuellerLeitspruch(
            final String quelleIndividuellerLeitspruch) {
        this.quelleIndividuellerLeitspruch = quelleIndividuellerLeitspruch;
    }

    public String getQuelleIndividuellerLeitspruch2() {
        return this.quelleIndividuellerLeitspruch2;
    }

    public void setQuelleIndividuellerLeitspruch2(
            final String quelleIndividuellerLeitspruch2) {
        this.quelleIndividuellerLeitspruch2 = quelleIndividuellerLeitspruch2;
    }

    public Boolean getRuecktAuf() {
        return this.ruecktAuf;
    }

    public void setRuecktAuf(final Boolean ruecktAuf) {
        this.ruecktAuf = ruecktAuf;
    }

    public List<AgBewertung> getAgBewertungen() {
        return this.agBewertungen;
    }

    public void setAgBewertungen(final List<AgBewertung> agBewertungs) {
        this.agBewertungen = agBewertungs;
    }

    public List<AvSvBewertung> getAvSvBewertungen() {
        return this.avSvBewertungen;
    }

    public void setAvSvBewertungen(final List<AvSvBewertung> avSvBewertungs) {
        this.avSvBewertungen = avSvBewertungs;
    }

    public List<Bemerkung> getBemerkungen() {
        return this.bemerkungen;
    }

    public void setBemerkungen(final List<Bemerkung> bemerkungs) {
        this.bemerkungen = bemerkungs;
    }

    public List<Bewertung> getBewertungen() {
        return this.bewertungen;
    }

    public void setBewertungen(final List<Bewertung> bewertungs) {
        this.bewertungen = bewertungs;
    }

    public List<SchulamtsBemerkung> getSchulamtsBemerkungen() {
        return this.schulamtsBemerkungen;
    }

    public void setSchulamtsBemerkungen(
            final List<SchulamtsBemerkung> schulamtsBemerkungs) {
        this.schulamtsBemerkungen = schulamtsBemerkungs;
    }

    public Klasse getKlasse() {
        return this.klasse;
    }

    public void setKlasse(final Klasse klasse) {
        this.klasse = klasse;
    }

    public Schueler getSchueler() {
        return this.schueler;
    }

    public void setSchueler(final Schueler schueler) {
        this.schueler = schueler;
    }

    public Schulhalbjahr getSchulhalbjahr() {
        return this.schulhalbjahr;
    }

    public void setSchulhalbjahr(final Schulhalbjahr schulhalbjahr) {
        this.schulhalbjahr = schulhalbjahr;
    }

    public SoLBewertungsText getSoLBewertungsText() {
        return this.soLBewertungsText;
    }

    public void setSoLBewertungsText(
            final SoLBewertungsText solbewertungsText) {
        this.soLBewertungsText = solbewertungsText;
    }

    public ZeugnisArt getZeugnisArt() {
        return this.zeugnisArt;
    }

    public void setZeugnisArt(final ZeugnisArt zeugnisArt) {
        this.zeugnisArt = zeugnisArt;
    }

    public ZeugnisFormular getFormular() {
        return this.formular;
    }

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
//      printMap['anzahlVerspaetungen'] = (anzahlVerspaetungen==0)?VariableUtility.PLATZHALTER_LEER:"${anzahlVerspaetungen}";
//      def versetzungsBemerkung = ""
//      if (schulhalbjahr.halbjahr==Halbjahr.Beide_Halbjahre) {
//          if (klassenZielWurdeNichtErreicht) {
//              versetzungsBemerkung = "Das Klassenziel wurde nicht erreicht. "
//          }
//          if (ruecktAuf && zeugnisArt.printVersetzungsbemerkung) {
//              def nextKlassenstufe = klasse.calculateKlassenstufe(schulhalbjahr.jahr) +1
//              versetzungsBemerkung = versetzungsBemerkung + "${schueler.vorname} r\u00fcckt auf in Klasse ${nextKlassenstufe}. "
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
//              buBewertungsText?:VariableUtility.PLATZHALTER_LEER, schueler.rufname, schueler.vorname, schueler.geschlecht,
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
//      printMap['bemerkung_schulamt'] = schulamtsBemerkungsText.toString()?:VariableUtility.PLATZHALTER_LEER
//  }

    private String dayToString(final Long day) {
        if (day == null) {
            return VariableUtility.PLATZHALTER_LEER;
        } else if (day == 1) {
            return "1 Tag";
        } else {
            return day + " Tage";
        }
    }

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
