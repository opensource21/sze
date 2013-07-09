// SoLBewertungsText.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

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
    @Column(nullable = false, length = 255)
    
    
    private String name;

    @Column(nullable = false, length = 100)
    
    @Size(max = 100)
    private String text;


    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

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
