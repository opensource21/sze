// Schulamt.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.base.RevisionModel;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.spring.mvc.formatter.NonEmpty;

/**
 * Schüler können verschieden Ämter inne haben, diese werden hier definiert.
 *
 */
@Entity
@Table(name = "schulamt",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_SCHULAMT_NAME"))
public class Schulamt extends RevisionModel implements Serializable,
        Comparable<Schulamt> {

    /** The name. */
    @Column(nullable = false, length = 20)
    @NonEmpty
    private String name;

    /** The beschreibender satz. */
    @Column(name = "beschreibender_satz", nullable = false, length = 255)
    @ValidVariableText
    private String beschreibenderSatz;

    /** The aktiv. */
    @Column(nullable = false)
    private Boolean aktiv = Boolean.TRUE;

    /**
     * Gets the aktiv.
     *
     * @return the aktiv
     */
    public Boolean getAktiv() {
        return this.aktiv;
    }

    /**
     * Sets the aktiv.
     *
     * @param aktiv the new aktiv
     */
    public void setAktiv(final Boolean aktiv) {
        this.aktiv = aktiv;
    }

    /**
     * Gets the beschreibender satz.
     *
     * @return the beschreibender satz
     */
    public String getBeschreibenderSatz() {
        return this.beschreibenderSatz;
    }

    /**
     * Sets the beschreibender satz.
     *
     * @param beschreibenderSatz the new beschreibender satz
     */
    public void setBeschreibenderSatz(final String beschreibenderSatz) {
        this.beschreibenderSatz = beschreibenderSatz;
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

    @SuppressWarnings("boxing")
    @Override
    public int compareTo(final Schulamt other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.name, other.name);
        compareBuilder.append(this.beschreibenderSatz, other
                .beschreibenderSatz);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        return name;
    }
}
