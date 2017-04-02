// BemerkungsBaustein.java
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

import de.ppi.fuwesta.oval.validation.Unique;
import de.ppi.fuwesta.spring.mvc.formatter.NonEmpty;

/**
 * Textbausteine für Bemerkungen.
 *
 */
@Entity
@Table(name = "bemerkungs_baustein",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_BEM_BAUSTEIN_NAME"))
public class BemerkungsBaustein extends RevisionModel implements Serializable,
        Comparable<BemerkungsBaustein> {

    /** The name. */
    @Column(nullable = false, length = 20)
    @NonEmpty
    @Unique()
    private String name;

    /** The text. */
    @Column(nullable = false, length = 500)
    @ValidVariableText
    private String text;

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
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the text.
     *
     * @param text the new text
     */
    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return name;
    }

    @SuppressWarnings("boxing")
    @Override
    public int compareTo(final BemerkungsBaustein other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.name, other.name);
        compareBuilder.append(this.text, other.text);
        return compareBuilder.toComparison();
    }
}
