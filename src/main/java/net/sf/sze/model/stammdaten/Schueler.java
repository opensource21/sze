// Schueler.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.stammdaten;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.oval.constraint.Past;
import net.sf.oval.constraint.Size;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Ein Schüler einer Schule.
 *
 */
@Entity
@Table(name = "schueler")
public class Schueler extends VersionedModel implements Serializable,
        Comparable<Schueler> {

    /** The name. */
    @Column(nullable = false, length = 40)
    @Size(max = 40)
    private String name;

    /** The vorname. */
    @Column(nullable = false, length = 40)

    @Size(max = 40)
    private String vorname;

    /** The rufname. */
    @Column(length = 20)
    @Size(max = 20)
    private String rufname;

    /** The geburtstag. */
    @Column(nullable = false)
    @Past
    private Date geburtstag;

    /** The geburtsort. */
    @Column(nullable = false, length = 40)
    @Size(max = 40)
    private String geburtsort;

    /** The nummer. */
    private Long nummer;

    /** The aufnahme datum. */
    @Column(name = "aufnahme_datum")
    private Date aufnahmeDatum;

    /** The abgangs datum. */
    @Column(name = "abgangs_datum")
    private Date abgangsDatum;

    /** The geschlecht. */
    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.ORDINAL)
    private Geschlecht geschlecht;

    // bi-directional many-to-one association to Klasse

    /** The klasse. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "klasse_id", nullable = false)
    private Klasse klasse;

    /**
     * Gets the abgangs datum.
     *
     * @return the abgangs datum
     */
    public Date getAbgangsDatum() {
        return this.abgangsDatum;
    }

    /**
     * Sets the abgangs datum.
     *
     * @param abgangsDatum the new abgangs datum
     */
    public void setAbgangsDatum(final Date abgangsDatum) {
        this.abgangsDatum = abgangsDatum;
    }

    /**
     * Gets the aufnahme datum.
     *
     * @return the aufnahme datum
     */
    public Date getAufnahmeDatum() {
        return this.aufnahmeDatum;
    }

    /**
     * Sets the aufnahme datum.
     *
     * @param aufnahmeDatum the new aufnahme datum
     */
    public void setAufnahmeDatum(final Date aufnahmeDatum) {
        this.aufnahmeDatum = aufnahmeDatum;
    }

    /**
     * Gets the geburtsort.
     *
     * @return the geburtsort
     */
    public String getGeburtsort() {
        return this.geburtsort;
    }

    /**
     * Sets the geburtsort.
     *
     * @param geburtsort the new geburtsort
     */
    public void setGeburtsort(final String geburtsort) {
        this.geburtsort = geburtsort;
    }

    /**
     * Gets the geburtstag.
     *
     * @return the geburtstag
     */
    public Date getGeburtstag() {
        return this.geburtstag;
    }

    /**
     * Sets the geburtstag.
     *
     * @param geburtstag the new geburtstag
     */
    public void setGeburtstag(final java.util.Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    /**
     * Gets the geschlecht.
     *
     * @return the geschlecht
     */
    public Geschlecht getGeschlecht() {
        return this.geschlecht;
    }

    /**
     * Sets the geschlecht.
     *
     * @param geschlecht the new geschlecht
     */
    public void setGeschlecht(final Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the nummer.
     *
     * @return the nummer
     */
    public Long getNummer() {
        return this.nummer;
    }

    /**
     * Sets the nummer.
     *
     * @param nummer the new nummer
     */
    public void setNummer(final Long nummer) {
        this.nummer = nummer;
    }

    /**
     * Gets the rufname.
     *
     * @return the rufname
     */
    public String getRufname() {
        return this.rufname;
    }

    /**
     * Sets the rufname.
     *
     * @param rufname the new rufname
     */
    public void setRufname(final String rufname) {
        this.rufname = rufname;
    }

    /**
     * Gets the vorname.
     *
     * @return the vorname
     */
    public String getVorname() {
        return this.vorname;
    }

    /**
     * Sets the vorname.
     *
     * @param vorname the new vorname
     */
    public void setVorname(final String vorname) {
        this.vorname = vorname;
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

    // Hier beginnen die echten Methoden

    /**
     * Kennzeichen ob der Schüler noch aktuell an der Schule ist.
     * @return true, wenn er noch in der Schule ist.
     */
    public boolean isActive() {
        return (abgangsDatum == null) || (abgangsDatum.getTime() > System
                .currentTimeMillis());
    }

    @Override
    public String toString() {
        return name + ", " + vorname;
    }

    @Override
    public int compareTo(final Schueler other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.getKlasse(), other.getKlasse());
        compareBuilder.append(this.getName(), other.getName());
        compareBuilder.append(this.getVorname(), other.getVorname());
        compareBuilder.append(this.getGeburtstag(), other.getGeburtstag());
        compareBuilder.append(this.getGeburtsort(), other.getGeburtsort());
        compareBuilder.append(this.getNummer(), other.getNummer());
        compareBuilder.append(this.getAufnahmeDatum(), other.getAufnahmeDatum());
        compareBuilder.append(this.getAbgangsDatum(), other.getAbgangsDatum());
        return compareBuilder.toComparison();
    }

    /**
     * Schreibt die Daten in die Printmap.
     * @param printMap die Printmap.
     */
    public void toPrintMap(final Map<String, Object> printMap) {
        printMap.put("schueler_name", name);
        printMap.put("schueler_vorname", vorname);
        printMap.put("schueler_geburtsort", geburtsort);
        printMap.put("schueler_nummer", nummer);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",
                Locale.GERMANY);
        printMap.put("schueler_geburtstag", formatter.format(geburtstag));
        printMap.put("schueler_aufnahmeDatum",
                aufnahmeDatum == null ? null : formatter.format(aufnahmeDatum));
        printMap.put("schueler_abgangsDatum",
                abgangsDatum == null ? null : formatter.format(abgangsDatum));
    }
}
