// SchulamtsBemerkungsBaustein.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.Size;
import net.sf.sze.constraints.ValidVariableText;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Schüler können verschieden Ämter inne haben. Zu diesnen kann man zusätzliche
 * Anmerkungen machen.
 *
 */
@Entity
@Table(name = "schulamts_bemerkungs_baustein",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_SCHULAMT_BEM_BAUSTEIN_NAME"))
public class SchulamtsBemerkungsBaustein extends VersionedModel
        implements Serializable, Comparable<SchulamtsBemerkungsBaustein> {

    /** The name. */
    @Column(nullable = false, length = 50)

    @Size(max = 50)
    private String name;

    /** The beschreibender satz. */
    @Column(name = "beschreibender_satz", nullable = false, length = 255)

    @ValidVariableText
    private String beschreibenderSatz;

    /** The aktiv. */
    @Column(nullable = false)

    private Boolean aktiv = Boolean.TRUE;

    /** The sortierung. */
    @Column(nullable = false)

    private Long sortierung;

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

    /**
     * Gets the sortierung.
     *
     * @return the sortierung
     */
    public Long getSortierung() {
        return this.sortierung;
    }

    /**
     * Sets the sortierung.
     *
     * @param sortierung the new sortierung
     */
    public void setSortierung(final Long sortierung) {
        this.sortierung = sortierung;
    }

    @SuppressWarnings("boxing")
    @Override
    public int compareTo(final SchulamtsBemerkungsBaustein other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.sortierung, other.sortierung);
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
