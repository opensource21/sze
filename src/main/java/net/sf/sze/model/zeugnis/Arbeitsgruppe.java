// Arbeitsgruppe.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Arbeitsgruppen.
 *
 */
@Entity
@Table(name = "arbeitsgruppe",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"},
        name = "UK_AG_NAME"))
public class Arbeitsgruppe extends VersionedModel implements Serializable,
        Comparable<Arbeitsgruppe> {

    /** The name. */
    @Column(nullable = false, length = 70)
    private String name;

    /** The sortierung. */
    @Column(nullable = false)

    private Long sortierung;

    /** The klassenstufen. */
    @Column(nullable = false, length = 255)

    private String klassenstufen;

    // bi-directional many-to-one association to AgBewertung

    /** The ag bewertungs. */
    @OneToMany(mappedBy = "arbeitsgruppe")
    private List<AgBewertung> agBewertungs;

    /**
     * Gets the klassenstufen.
     *
     * @return the klassenstufen
     */
    public String getKlassenstufen() {
        return this.klassenstufen;
    }

    /**
     * Sets the klassenstufen.
     *
     * @param klassenstufen the new klassenstufen
     */
    public void setKlassenstufen(final String klassenstufen) {
        this.klassenstufen = klassenstufen;
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

    /**
     * Gets the ag bewertungs.
     *
     * @return the ag bewertungs
     */
    public List<AgBewertung> getAgBewertungs() {
        return this.agBewertungs;
    }

    /**
     * Sets the ag bewertungs.
     *
     * @param agBewertungs the new ag bewertungs
     */
    public void setAgBewertungs(final List<AgBewertung> agBewertungs) {
        this.agBewertungs = agBewertungs;
    }

    // ******************************

    /**
     * Konvertiert den Sting der Klassenstufen in eine Liste.
     * @return den Sting der Klassenstufen als Liste.
     */
    public List<String> convertKlasenStufenToList() {
        return StringUtil.convertStringToList(klassenstufen);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final Arbeitsgruppe other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.name, other.name);
        return compareBuilder.toComparison();
    }
}
