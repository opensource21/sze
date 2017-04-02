// Arbeitsgruppe.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.oval.validation.Unique;
import de.ppi.fuwesta.spring.mvc.formatter.NonEmpty;

/**
 * Arbeitsgruppen.
 *
 */
@Entity
@Table(name = "arbeitsgruppe",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"},
        name = "UK_AG_NAME"))
public class Arbeitsgruppe extends RevisionModel implements Serializable,
        Comparable<Arbeitsgruppe> {

    /** The name. */
    @Column(nullable = false, length = 70)
    @NonEmpty
    @Unique
    private String name;

    /** The sortierung. */
    @Column(nullable = false)
    private Long sortierung = Long.valueOf(100);

    /** The klassenstufen. */
    @Column(nullable = false, length = 255)
    @NonEmpty
    private String klassenstufen;


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
