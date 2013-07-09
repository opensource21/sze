// Klasse.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.stammdaten;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Beschreibt eine Schulklasse
 *
 */
@Entity
@Table(name = "klasse")
public class Klasse extends VersionedModel implements Serializable,
        Comparable<Klasse> {

    // TODO 3 Zahlenbereich in der GUI einschränken :-/
//  static constraints = {
//      jahrgang(range:2000..2020)
//  }


    /**
     * Jahr in dem diese Klasse begonnen wurde.
     */
    @Column(nullable = false)
    
    private Integer jahrgang;

    /**
     * Ergänzung also 1a
     */
    @Column(nullable = false, length = 1)
    
    @Size(max = 1)
    private String suffix;

    /**
     * Kennzeichen, ob eine Klasse geschlossen ist oder nicht.
     */
    @Column(nullable = false)
    
    private Boolean geschlossen = Boolean.FALSE;


    // bi-directional many-to-one association to Schueler
    @OneToMany(mappedBy = "klasse")
    private Set<Schueler> schueler;


    public Boolean getGeschlossen() {
        return this.geschlossen;
    }

    public void setGeschlossen(final Boolean geschlossen) {
        this.geschlossen = geschlossen;
    }

    public Integer getJahrgang() {
        return this.jahrgang;
    }

    public void setJahrgang(final Integer jahrgang) {
        this.jahrgang = jahrgang;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    public Set<Schueler> getSchueler() {
        return this.schueler;
    }

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

    public String calculateKlassenname(final int schuljahresEnde) {
        if (StringUtil.containsInformation(suffix)) {
            return calculateKlassenstufe(schuljahresEnde) + suffix;
        } else {
            return String.valueOf(calculateKlassenstufe(schuljahresEnde));
        }
    }

    public int calculateKlassenstufe(final int schuljahresEnde) {
        return (schuljahresEnde - jahrgang);
    }



    public int calculateKlassenstufe() {
        return calculateKlassenstufe(calculateSchuljahresEnde());
    }

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
