// AgBewertung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.jpa.helper.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    /** Die Arbeitsgruppen. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "arbeitsgruppe_id", nullable = false)
    private Arbeitsgruppe arbeitsgruppe;

    /** Das Zeugnis. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    private Zeugnis zeugnis;

    /**
     * Gets the kennzeichen, dass der Schüler teilgenommen hat.
     *
     * @return the kennzeichen, dass der Schüler teilgenommen hat
     */
    public Boolean getTeilgenommen() {
        return this.teilgenommen;
    }

    /**
     * Sets the kennzeichen, dass der Schüler teilgenommen hat.
     *
     * @param teilgenommen the new kennzeichen, dass der Schüler teilgenommen
     *            hat
     */
    public void setTeilgenommen(final Boolean teilgenommen) {
        this.teilgenommen = teilgenommen;
    }

    /**
     * Gets the die Arbeitsgruppen.
     *
     * @return the die Arbeitsgruppen
     */
    public Arbeitsgruppe getArbeitsgruppe() {
        return this.arbeitsgruppe;
    }

    /**
     * Sets the die Arbeitsgruppen.
     *
     * @param arbeitsgruppe the new die Arbeitsgruppen
     */
    public void setArbeitsgruppe(final Arbeitsgruppe arbeitsgruppe) {
        this.arbeitsgruppe = arbeitsgruppe;
    }

    /**
     * Gets the das Zeugnis.
     *
     * @return the das Zeugnis
     */
    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

    /**
     * Sets the das Zeugnis.
     *
     * @param zeugni the new das Zeugnis
     */
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
