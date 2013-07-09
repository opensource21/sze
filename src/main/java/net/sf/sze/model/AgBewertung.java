// AgBewertung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.model.zeugnis.Arbeitsgruppe;
import net.sf.sze.model.zeugnis.Zeugnis;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Bewertung zur Teilnahme an einer AG. Zur Zeit nur tg oder nicht teilgenommen,
 * aber ich möchte mir die Zukunft nicht verbauen.
 *
 */
@Entity
@Table(name = "ag_bewertung")
public class AgBewertung extends VersionedModel implements Serializable,
        Comparable<AgBewertung> {

    /**
     * Kennzeichen, dass der Schüler teilgenommen hat.
     */
    @Column(nullable = false)
    private Boolean teilgenommen;

    // bi-directional many-to-one association to Arbeitsgruppe
    @ManyToOne(optional = false)
    @JoinColumn(name = "arbeitsgruppe_id", nullable = false)
    
    private Arbeitsgruppe arbeitsgruppe;

    // bi-directional many-to-one association to Zeugnis
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    
    private Zeugnis zeugnis;


    public Boolean getTeilgenommen() {
        return this.teilgenommen;
    }

    public void setTeilgenommen(final Boolean teilgenommen) {
        this.teilgenommen = teilgenommen;
    }

    public Arbeitsgruppe getArbeitsgruppe() {
        return this.arbeitsgruppe;
    }

    public void setArbeitsgruppe(final Arbeitsgruppe arbeitsgruppe) {
        this.arbeitsgruppe = arbeitsgruppe;
    }

    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

    public void setZeugnis(final Zeugnis zeugni) {
        this.zeugnis = zeugni;
    }


    @Override
    public int compareTo(final AgBewertung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.arbeitsgruppe, other.arbeitsgruppe);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.teilgenommen, other.teilgenommen);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        return arbeitsgruppe.getName() + " " + teilgenommen;
    }
}
