// Schulfach.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnisconfig;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.constraints.ZweiDreiNivauIntersect;
import net.sf.sze.constraints.DisjunktKlassenstufenConfigurer;
import net.sf.sze.constraints.StandardZweiNiveauIntersect;
import net.sf.sze.constraints.StandardDreiNiveauIntersect;
import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.spring.mvc.formatter.NonEmpty;

/**
 * Ein Schulfach.
 *
 */
@Entity
@Table(name = "schulfach",
        uniqueConstraints = @UniqueConstraint(columnNames = {"typ", "name"},
        name = "UK_SCHULFACH_TYP_NAME"))
public class Schulfach extends RevisionModel implements Serializable,
        Comparable<Schulfach>, DisjunktKlassenstufenConfigurer {

    /** The name. */
    @Column(nullable = false, length = 30)
    @NonEmpty
    private String name;

    /** The sortierung. */
    @Column(nullable = false)
    @NonEmpty
    private Long sortierung = Long.valueOf(100);

    /** The typ. */
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Schulfachtyp typ;

    /** The stufen mit Differenzierung auf 2 Niveaus. */
    @Column(length = 255)
    @ZweiDreiNivauIntersect
    @StandardZweiNiveauIntersect
    private String stufenMitZweiNiveaus;

    /** The stufen mit Differenzierung auf 3 Niveaus. */
    @Column(length = 255)
    @StandardDreiNiveauIntersect
    @ZweiDreiNivauIntersect
    private String stufenMitDreiNiveaus;

    /** The stufen mit standard bewertung. */
    @Column(length = 255)
    @StandardDreiNiveauIntersect
    @StandardZweiNiveauIntersect
    private String stufenMitStandardBewertung;

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
     * Gets the stufen mit Differenzierung auf 2 Niveaus.
     *
     * @return the stufen mit Differenzierung auf 2 Niveaus.
     */
    public String getStufenMitZweiNiveaus() {
        return this.stufenMitZweiNiveaus;
    }

    /**
     * Sets the stufen mit Differenzierung auf 2 Niveaus..
     *
     * @param stufenMitZweiNiveaus the new stufen mit Differenzierung auf 2 Niveaus.
     */
    public void setStufenMitZweiNiveaus(final String stufenMitZweiNiveaus) {
        this.stufenMitZweiNiveaus = stufenMitZweiNiveaus;
    }

    /**
     * Gets the stufen mit Differenzierung auf 3 Niveaus.
     *
     * @return the stufen mit Differenzierung auf 3 Niveaus
     */
    public String getStufenMitDreiNiveaus() {
        return this.stufenMitDreiNiveaus;
    }

    /**
     * Sets the stufen mit Differenzierung auf 3 Niveaus.
     *
     * @param stufenMitDreiNiveaus the new stufen mit Differenzierung auf 3 Niveaus.
     */
    public void setStufenMitDreiNiveaus(final String stufenMitDreiNiveaus) {
        this.stufenMitDreiNiveaus = stufenMitDreiNiveaus;
    }

    /**
     * Gets the stufen mit standard bewertung.
     *
     * @return the stufen mit standard bewertung
     */
    public String getStufenMitStandardBewertung() {
        return this.stufenMitStandardBewertung;
    }

    /**
     * Sets the stufen mit standard bewertung.
     *
     * @param stufenMitStandardBewertung the new stufen mit standard bewertung
     */
    public void setStufenMitStandardBewertung(
            final String stufenMitStandardBewertung) {
        this.stufenMitStandardBewertung = stufenMitStandardBewertung;
    }

    /**
     * Gets the typ.
     *
     * @return the typ
     */
    public Schulfachtyp getTyp() {
        return this.typ;
    }

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    public void setTyp(final Schulfachtyp typ) {
        this.typ = typ;
    }

    // **************************************************

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    @Override
    public List<String> convertStufenMitStandardBewertungToList() {
        return StringUtil.convertStringToList(stufenMitStandardBewertung);
    }

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    @Override
    public List<String> convertStufenMitDreiNiveausToList() {
        return StringUtil.convertStringToList(stufenMitDreiNiveaus);
    }

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    @Override
    public List<String> convertStufenMitZweiNiveausToList() {
        return StringUtil.convertStringToList(stufenMitZweiNiveaus);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final Schulfach other) {
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
        return StringUtil.deleteSpecialCharaters(typ.getShortKey() + "_"
                + name);
    }
}
