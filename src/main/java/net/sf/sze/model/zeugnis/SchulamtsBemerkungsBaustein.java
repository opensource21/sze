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
import net.sf.sze.model.base.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

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
    @Column(nullable = false, length = 50)
    
    @Size(max = 50)
    private String name;

    @Column(name = "beschreibender_satz", nullable = false, length = 255)
    
    
    @ValidVariableText
    private String beschreibenderSatz;

    @Column(nullable = false)
    
    private Boolean aktiv = Boolean.TRUE;

    @Column(nullable = false)
    
    private Long sortierung;


    public SchulamtsBemerkungsBaustein() {
    }

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

    public Long getSortierung() {
        return this.sortierung;
    }

    public void setSortierung(final Long sortierung) {
        this.sortierung = sortierung;
    }

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
