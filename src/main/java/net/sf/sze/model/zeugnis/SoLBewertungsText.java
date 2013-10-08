// SoLBewertungsText.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

import net.sf.oval.constraint.Size;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SoL wird mit festen Texten bewertet.
 *
 */
@Entity
@Table(name = "solbewertungs_text",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_SOLBEWERTUNGS_TEXT_NAME"))
public class SoLBewertungsText extends VersionedModel implements Serializable,
        Comparable<SoLBewertungsText> {

    /** The name. */
    @Column(nullable = false, length = 255)

    private String name;

    /** The text. */
    @Column(nullable = false, length = 100)

    @Size(max = 100)
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
