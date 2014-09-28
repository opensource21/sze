// SoLBewertungsText.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.RevisionModel;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.spring.mvc.formatter.NonEmpty;

/**
 * SoL wird mit festen Texten bewertet.
 *
 */
@Entity
@Table(name = "solbewertungs_text",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_SOLBEWERTUNGS_TEXT_NAME"))
public class SoLBewertungsText extends RevisionModel implements Serializable,
        Comparable<SoLBewertungsText> {

    /** The name. */
    @Column(nullable = false, length = 50)
    @NonEmpty
    private String name;

    /** The text. */
    @Column(nullable = false, length = 100)
    @NonEmpty
    private String text;

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
    public int compareTo(final SoLBewertungsText other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.name, other.name);
        compareBuilder.append(this.text, other.text);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        return name;

    }
}
