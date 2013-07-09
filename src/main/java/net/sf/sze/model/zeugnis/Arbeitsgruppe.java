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

import net.sf.sze.model.AgBewertung;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Arbeitsgruppen
 *
 */
@Entity
@Table(name = "arbeitsgruppe",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"},
        name = "UK_AG_NAME"))
public class Arbeitsgruppe extends VersionedModel implements Serializable,
        Comparable<Arbeitsgruppe> {

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false)

    private Long sortierung;

    @Column(nullable = false, length = 255)
    

    private String klassenstufen;

    // bi-directional many-to-one association to AgBewertung
    @OneToMany(mappedBy = "arbeitsgruppe")
    private List<AgBewertung> agBewertungs;

    public Arbeitsgruppe() {
    }

    public String getKlassenstufen() {
        return this.klassenstufen;
    }

    public void setKlassenstufen(final String klassenstufen) {
        this.klassenstufen = klassenstufen;
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

    public List<AgBewertung> getAgBewertungs() {
        return this.agBewertungs;
    }

    public void setAgBewertungs(final List<AgBewertung> agBewertungs) {
        this.agBewertungs = agBewertungs;
    }

    // ******************************
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
