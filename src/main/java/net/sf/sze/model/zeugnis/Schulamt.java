// Schulamt.java
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
 * Schüler können verschieden Ämter inne haben, diese werden hier definiert.
 *
 */
@Entity
@Table(name = "schulamt",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_SCHULAMT_NAME"))
public class Schulamt extends VersionedModel implements Serializable,
        Comparable<Schulamt> {

    @Column(nullable = false, length = 20)
    
    @Size(max = 20)
    private String name;

    @Column(name = "beschreibender_satz", nullable = false, length = 255)
    
    
    @ValidVariableText
    private String beschreibenderSatz;


    @Column(nullable = false)
    
    private Boolean aktiv = Boolean.TRUE;


    public Boolean getAktiv() {
        return this.aktiv;
    }

    public void setAktiv(final Boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String getBeschreibenderSatz() {
        return this.beschreibenderSatz;
    }

    public void setBeschreibenderSatz(final String beschreibenderSatz) {
        this.beschreibenderSatz = beschreibenderSatz;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

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
