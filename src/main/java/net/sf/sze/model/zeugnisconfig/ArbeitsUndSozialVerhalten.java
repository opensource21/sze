// ArbeitsUndSozialVerhalten.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    /** The name. */
    @Column(nullable = false, length = 50)
    private String name;

    /** The typ. */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AvSvTyp typ;

    /** The sortierung. */
    @Column(nullable = false)
    private Long sortierung;

    /** The klassenstufen. */
    @Column(nullable = false, length = 255)
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

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    public AvSvTyp getTyp() {
        return this.typ;
    }

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    public void setTyp(final AvSvTyp typ) {
        this.typ = typ;
    }

    // *******************************************************************

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
        return typ.getShortKey() + "_" + StringUtil.deleteSpecialCharaters(
                name);
    }
}
