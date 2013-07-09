// BemerkungsBaustein.java
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
import net.sf.sze.model.base.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Textbausteine für Bemerkungen.
 *
 */
@Entity
@Table(name = "bemerkungs_baustein",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_BEM_BAUSTEIN_NAME"))
public class BemerkungsBaustein extends VersionedModel implements Serializable {
    @Column(nullable = false, length = 20)
    
    @Size(max = 20)
    private String name;

    @Column(nullable = false, length = 500)
    
    @Size(max = 500)
    @ValidVariableText
    private String text;

    @Column(nullable = false)
    
    private Boolean aktiv = Boolean.TRUE;

    public Boolean getAktiv() {
        return this.aktiv;
    }

    public void setAktiv(final Boolean aktiv) {
        this.aktiv = aktiv;
    }

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
    public String toString() {
        return name;
    }

    public boolean needsDatum() {
        return text.contains("@datum@");
    }

    public boolean containsName() {
        return text.contains("@name@") || text.contains("@Name@");
    }

    public int compareTo(final BemerkungsBaustein other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.name, other.name);
        compareBuilder.append(this.text, other.text);
        return compareBuilder.toComparison();
    }
}
