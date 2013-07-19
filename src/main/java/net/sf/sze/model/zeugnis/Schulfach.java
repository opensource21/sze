// Schulfach.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.jpa.helper.VersionedModel;

import net.sf.oval.constraint.Size;
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
 * Ein Schulfach.
 *
 */
@Entity
@Table(name = "schulfach",
        uniqueConstraints = @UniqueConstraint(columnNames = {"typ", "name"},
        name = "UK_SCHULFACH_TYP_NAME"))
public class Schulfach extends VersionedModel implements Serializable,
        Comparable<Schulfach> {

    // TODO 3 Validatoren schreiben, die sicherstellen, dass die Stufen überschneidungsfrei sind.
    // org.springframework.util.CollectionUtils.containsAny(Collection, Collection)
//  private static standardIntersectCheck = {value,obj ->
//  if (obj.convertStufenMitStandardBewertungToList().intersect(
//                  obj.convertStufenMitBinnenDifferenzierungToList())) {
//      return 'schulfach.standardIntersectBinnen'
//  }
//  if (obj.convertStufenMitStandardBewertungToList().intersect(
//              obj.convertStufenMitAussenDifferenzierungToList())) {
//      return 'schulfach.standardIntersectAussen'
//  }
//}
//private static binnenIntersectCheck = {value,obj ->
//      if (obj.convertStufenMitStandardBewertungToList().intersect(
//              obj.convertStufenMitBinnenDifferenzierungToList())) {
//          return 'schulfach.standardIntersectBinnen'
//      }
//  if (obj.convertStufenMitBinnenDifferenzierungToList().intersect(
//              obj.convertStufenMitAussenDifferenzierungToList())) {
//      return 'schulfach.binnenIntersectAussen'
//  }
// }
//
//private static aussenIntersectCheck = {value,obj ->
//      if (obj.convertStufenMitStandardBewertungToList().intersect(
//              obj.convertStufenMitAussenDifferenzierungToList())) {
//          return 'schulfach.standardIntersectAussen'
//      }
//      if (obj.convertStufenMitBinnenDifferenzierungToList().intersect(
//              obj.convertStufenMitAussenDifferenzierungToList())) {
//          return 'schulfach.binnenIntersectAussen'
//      }
//   }

    /** The name. */
    @Column(nullable = false, length = 30)

    @Size(max = 30)
    private String name;

    /** The sortierung. */
    @Column(nullable = false)

    private Long sortierung;

    /** The typ. */
    @Column(nullable = false)

    @Enumerated(EnumType.ORDINAL)
    private Schulfachtyp typ;

    /** The stufen mit aussen differenzierung. */
    @Column(name = "stufen_mit_aussen_differenzierung", length = 255)
    // NICE stufenMitZweiNiveaus
    private String stufenMitAussenDifferenzierung;

    /** The stufen mit binnen differenzierung. */
    @Column(name = "stufen_mit_binnen_differenzierung", length = 255)
    // NICE stufenMitDreiNiveaus
    private String stufenMitBinnenDifferenzierung;

    /** The stufen mit standard bewertung. */
    @Column(name = "stufen_mit_standard_bewertung", length = 255)

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
     * Gets the stufen mit aussen differenzierung.
     *
     * @return the stufen mit aussen differenzierung
     */
    public String getStufenMitAussenDifferenzierung() {
        return this.stufenMitAussenDifferenzierung;
    }

    /**
     * Sets the stufen mit aussen differenzierung.
     *
     * @param stufenMitAussenDifferenzierung the new stufen mit aussen
     *            differenzierung
     */
    public void setStufenMitAussenDifferenzierung(
            final String stufenMitAussenDifferenzierung) {
        this.stufenMitAussenDifferenzierung = stufenMitAussenDifferenzierung;
    }

    /**
     * Gets the stufen mit binnen differenzierung.
     *
     * @return the stufen mit binnen differenzierung
     */
    public String getStufenMitBinnenDifferenzierung() {
        return this.stufenMitBinnenDifferenzierung;
    }

    /**
     * Sets the stufen mit binnen differenzierung.
     *
     * @param stufenMitBinnenDifferenzierung the new stufen mit binnen
     *            differenzierung
     */
    public void setStufenMitBinnenDifferenzierung(
            final String stufenMitBinnenDifferenzierung) {
        this.stufenMitBinnenDifferenzierung = stufenMitBinnenDifferenzierung;
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
    public List<String> convertStufenMitStandardBewertungToList() {
        return StringUtil.convertStringToList(stufenMitStandardBewertung);
    }

    public List<String> convertStufenMitBinnenDifferenzierungToList() {
        return StringUtil.convertStringToList(stufenMitBinnenDifferenzierung);
    }

    public List<String> convertStufenMitAussenDifferenzierungToList() {
        return StringUtil.convertStringToList(stufenMitAussenDifferenzierung);
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
        return StringUtil.deleteSpecialCharaters(typ.getShortKey() + " "
                + name);
    }
}
