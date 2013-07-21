// Klasse.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.stammdaten;

import de.ppi.jpa.helper.VersionedModel;

import net.sf.oval.constraint.Range;
import net.sf.oval.constraint.Size;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Beschreibt eine Schulklasse.
 */
@Entity
@Table(name = "klasse")
public class Klasse extends VersionedModel implements Serializable,
        Comparable<Klasse> {

    // TODO GUI Zahlenbereich in der GUI einschränken :-/
//  static constraints = {
//      jahrgang(range:2000..2020)
//  }

    /**
     * Jahr in dem diese Klasse begonnen wurde.
     */
    @Column(nullable = false)
    @Range(min = 2000, max = 2030)
    private int jahrgang;

    /** Ergänzung also 1a. */
    @Column(nullable = false, length = 1)

    @Size(max = 1)
    private String suffix;

    /**
     * Kennzeichen, ob eine Klasse geschlossen ist oder nicht.
     */
    @Column(nullable = false)

    private Boolean geschlossen = Boolean.FALSE;

    // bi-directional many-to-one association to Schueler

    /** The schueler. */
    @OneToMany(mappedBy = "klasse")
    private Set<Schueler> schueler;

    /**
     * Gets the kennzeichen, ob eine Klasse geschlossen ist oder nicht.
     *
     * @return the kennzeichen, ob eine Klasse geschlossen ist oder nicht
     */
    public Boolean getGeschlossen() {
        return this.geschlossen;
    }

    /**
     * Sets the kennzeichen, ob eine Klasse geschlossen ist oder nicht.
     *
     * @param geschlossen the new kennzeichen, ob eine Klasse geschlossen ist
     *            oder nicht
     */
    public void setGeschlossen(final Boolean geschlossen) {
        this.geschlossen = geschlossen;
    }

    /**
     * Gets the jahr in dem diese Klasse begonnen wurde.
     *
     * @return the jahr in dem diese Klasse begonnen wurde
     */
    public int getJahrgang() {
        return this.jahrgang;
    }

    /**
     * Sets the jahr in dem diese Klasse begonnen wurde.
     *
     * @param jahrgang the new jahr in dem diese Klasse begonnen wurde
     */
    public void setJahrgang(final int jahrgang) {
        this.jahrgang = jahrgang;
    }

    /**
     * Gets the ergänzung also 1a.
     *
     * @return the ergänzung also 1a
     */
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * Sets the ergänzung also 1a.
     *
     * @param suffix the new ergänzung also 1a
     */
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    /**
     * Gets the schueler.
     *
     * @return the schueler
     */
    public Set<Schueler> getSchueler() {
        return this.schueler;
    }

    /**
     * Sets the schueler.
     *
     * @param schuelers the new schueler
     */
    public void setSchueler(final Set<Schueler> schuelers) {
        this.schueler = schuelers;
    }

    // Real Functions
    @Override
    public int compareTo(final Klasse other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(-this.jahrgang, -other.jahrgang);
        compareBuilder.append(this.suffix, other.suffix);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        return calculateKlassenname() + " (" + jahrgang + ")";
    }

    /**
     * Berechnet den Klassennamen in Abhängigkeit vom Schuljahr.
     * @param schuljahresEnde das Ende des Schuljahrs
     * @return den Klassennamen in Abhängigkeit vom Schuljahr.
     */
    public String calculateKlassenname(final int schuljahresEnde) {
        if (StringUtil.containsInformation(suffix)) {
            return calculateKlassenstufe(schuljahresEnde) + suffix;
        } else {
            return String.valueOf(calculateKlassenstufe(schuljahresEnde));
        }
    }

    /**
     * Berechnet die Klassenstufe in Abhängigkeit vom Schuljahr.
     * @param schuljahresEnde das Ende des Schuljahrs
     * @return die Klassenstufe in Abhängigkeit vom Schuljahr.
     */
    public int calculateKlassenstufe(final int schuljahresEnde) {
        return (schuljahresEnde - jahrgang);
    }

    /**
     * Berechnet die Klassenstufe in Abhängigkeit zu Heute.
     * @return die Klassenstufe.
     */
    public int calculateKlassenstufe() {
        return calculateKlassenstufe(calculateSchuljahresEnde());
    }

    /**
     * Berechnet den Klassennamen in Abhängigkeit zu Heute.
     * @return den Klassennamen.
     */
    public String calculateKlassenname() {
        return calculateKlassenname(calculateSchuljahresEnde());
    }

    private int calculateSchuljahresEnde() {
        final Calendar cal = GregorianCalendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        if (month <= Calendar.JULY) {
            return year;
        } else {
            return year + 1;
        }
    }
}
