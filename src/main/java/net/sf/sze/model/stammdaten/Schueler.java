// Schueler.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.stammdaten;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.oval.constraint.Past;
import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.model.zeugnis.Zeugnis;
import net.sf.sze.stammdaten.Geschlecht;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Ein Schüler einer Schule.
 *
 */
@Entity
@Table(name = "schueler")
public class Schueler extends VersionedModel implements Serializable,
        Comparable<Schueler> {

    @Column(nullable = false, length = 40)
    
    @Size(max = 40)
    private String name;

    @Column(nullable = false, length = 40)
    
    @Size(max = 40)
    private String vorname;

    @Column(length = 20)
    @Size(max = 20)
    private String rufname;

    @Column(nullable = false)
    
    @Past
    private Date geburtstag;

    @Column(nullable = false, length = 40)
    
    @Size(max = 40)
    private String geburtsort;

    private Long nummer;

    @Column(name = "aufnahme_datum")
    private Date aufnahmeDatum;

    @Column(name = "abgangs_datum")
    private Date abgangsDatum;

    @Column(nullable = false, length = 1)
    
    @Enumerated(EnumType.ORDINAL)
    private Geschlecht geschlecht;

    // bi-directional many-to-one association to Klasse
    @ManyToOne(optional=false)
    @JoinColumn(name = "klasse_id", nullable = false)
    
    private Klasse klasse;

    // bi-directional many-to-one association to Zeugni
    @OneToMany(mappedBy = "schueler", cascade=CascadeType.ALL)
    private Set<Zeugnis> zeugnisse;


    public Date getAbgangsDatum() {
        return this.abgangsDatum;
    }

    public void setAbgangsDatum(final Date abgangsDatum) {
        this.abgangsDatum = abgangsDatum;
    }

    public Date getAufnahmeDatum() {
        return this.aufnahmeDatum;
    }

    public void setAufnahmeDatum(final Date aufnahmeDatum) {
        this.aufnahmeDatum = aufnahmeDatum;
    }

    public String getGeburtsort() {
        return this.geburtsort;
    }

    public void setGeburtsort(final String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public Date getGeburtstag() {
        return this.geburtstag;
    }

    public void setGeburtstag(final java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public Geschlecht getGeschlecht() {
        return this.geschlecht;
    }

    public void setGeschlecht(final Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getNummer() {
        return this.nummer;
    }

    public void setNummer(final Long nummer) {
        this.nummer = nummer;
    }

    public String getRufname() {
        return this.rufname;
    }

    public void setRufname(final String rufname) {
        this.rufname = rufname;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(final String vorname) {
        this.vorname = vorname;
    }

    public Klasse getKlasse() {
        return this.klasse;
    }

    public void setKlasse(final Klasse klasse) {
        this.klasse = klasse;
    }

    public Set<Zeugnis> getZeugnisse() {
        return this.zeugnisse;
    }

    public void setZeugnisse(final Set<Zeugnis> zeugnis) {
        this.zeugnisse = zeugnis;
    }

    // Hier beginnen die echten Methoden
    @Override
    public String toString() {
        return name + ", " + vorname;
    }

    @Override
    public int compareTo(final Schueler other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.klasse, other.klasse);
        compareBuilder.append(this.name, other.name);
        compareBuilder.append(this.vorname, other.vorname);
        compareBuilder.append(this.geburtstag, other.geburtstag);
        compareBuilder.append(this.geburtsort, other.geburtsort);
        compareBuilder.append(this.nummer, other.nummer);
        compareBuilder.append(this.aufnahmeDatum, other.aufnahmeDatum);
        compareBuilder.append(this.abgangsDatum, other.abgangsDatum);
        compareBuilder.append(this.zeugnisse, other.zeugnisse);
        return compareBuilder.toComparison();
    }

    public void toPrintMap(final Map<String, String> printMap) {
        final String[] simpleAttributes = {
            "name", "vorname", "geburtsort", "nummer"
        };
        // TODO toPrintMap elegant implementieren, evtl BeanWrapper?.
        for (final String attr : simpleAttributes) {
            printMap.put("schueler_" + attr, "TODO"); // this."$attr"?:"";
        }
//      def dateAttributes = ["geburtstag", "aufnahmeDatum", "abgangsDatum"]
//      final formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
//      dateAttributes.each{attr ->
//          printMap["schueler_${attr}"] = this."$attr"?formatter.format(this."$attr"):""
//      }
    }
}
