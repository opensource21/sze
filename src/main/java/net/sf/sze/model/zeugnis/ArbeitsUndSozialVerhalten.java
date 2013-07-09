// ArbeitsUndSozialVerhalten.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.util.StringUtil;
import net.sf.sze.zeugnis.AvSvTyp;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Mögliches Arbeits- bzw Sozialverhalten. Im allgemeinen kurz AvSv genannt.
 *
 */
@Entity
@Table(name = "arbeits_und_sozial_verhalten",
        uniqueConstraints = @UniqueConstraint(columnNames = {"typ", "name"},
        name = "UK_AV_SV_TYP_NAME"))
public class ArbeitsUndSozialVerhalten extends VersionedModel
        implements Serializable, Comparable<ArbeitsUndSozialVerhalten> {
    @Column(nullable = false, length = 50)

    private String name;

    @Column(nullable = false)

    @Enumerated(EnumType.ORDINAL)
    private AvSvTyp typ;

    @Column(nullable = false)

    private Long sortierung;

    @Column(nullable = false, length = 255)


    private String klassenstufen;


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

    public AvSvTyp getTyp() {
        return this.typ;
    }

    public void setTyp(final AvSvTyp typ) {
        this.typ = typ;
    }

    // *******************************************************************
    public List<String> convertKlasenStufenToList() {
        return StringUtil.convertStringToList(klassenstufen);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final ArbeitsUndSozialVerhalten other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.typ, other.typ);
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.name, other.name);
        return compareBuilder.toComparison();
    }

    /**
     * Der technische Name enthält keine Sonderzeichen und ist eindeutig.
     * @return ein eindeutiger Name ohne Sonderzeichen.
     */
    public String technicalName() {
        return typ.getShortKey() + " " + StringUtil.deleteSpecialCharaters(
                name);
    }
}
