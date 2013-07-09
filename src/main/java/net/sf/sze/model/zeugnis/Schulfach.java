// Schulfach.java
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

import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.util.StringUtil;
import net.sf.sze.zeugnis.Schulfachtyp;

import org.apache.commons.lang.builder.CompareToBuilder;

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

    // TODO Validatoren schreiben
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

    @Column(nullable = false, length = 30)

    @Size(max = 30)
    private String name;

    @Column(nullable = false)

    private Long sortierung;

    @Column(nullable = false)

    @Enumerated(EnumType.ORDINAL)
    private Schulfachtyp typ;

    @Column(name = "stufen_mit_aussen_differenzierung", length = 255)

    // TODO stufenMitZweiNiveaus
    private String stufenMitAussenDifferenzierung;

    @Column(name = "stufen_mit_binnen_differenzierung", length = 255)

    // TODO stufenMitDreiNiveaus
    private String stufenMitBinnenDifferenzierung;

    @Column(name = "stufen_mit_standard_bewertung", length = 255)

    private String stufenMitStandardBewertung;

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

    public String getStufenMitAussenDifferenzierung() {
        return this.stufenMitAussenDifferenzierung;
    }

    public void setStufenMitAussenDifferenzierung(
            final String stufenMitAussenDifferenzierung) {
        this.stufenMitAussenDifferenzierung = stufenMitAussenDifferenzierung;
    }

    public String getStufenMitBinnenDifferenzierung() {
        return this.stufenMitBinnenDifferenzierung;
    }

    public void setStufenMitBinnenDifferenzierung(
            final String stufenMitBinnenDifferenzierung) {
        this.stufenMitBinnenDifferenzierung = stufenMitBinnenDifferenzierung;
    }

    public String getStufenMitStandardBewertung() {
        return this.stufenMitStandardBewertung;
    }

    public void setStufenMitStandardBewertung(
            final String stufenMitStandardBewertung) {
        this.stufenMitStandardBewertung = stufenMitStandardBewertung;
    }

    public Schulfachtyp getTyp() {
        return this.typ;
    }

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
