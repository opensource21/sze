// Schulhalbjahr.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.zeugnis.Halbjahr;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Beschreibt ein Schulhalbjahr.
 *
 */
@Entity
@Table(name = "schulhalbjahr",
        uniqueConstraints = @UniqueConstraint(columnNames = {"jahr",
        "halbjahr"}, name = "UK_SCHULAHLBJAHR_JAHR_HALBJAHR"))
public class Schulhalbjahr extends VersionedModel implements Serializable {

    /**
     * Das Jahr in dem die Zeugnisse für das Schuljahr erstellt werden.
     */
    @Column(nullable = false)
    
    // TODO Gui-Darstellung mit CB 2000..2050, besser Default raten.
    private Integer jahr;

    /**
     * erstes oder 2.Halbjahr
     */
    @Column(nullable = false)
    
    private Halbjahr halbjahr;

    /**
     * Kennzeichen, ob diese Jahr in der Auswahlliste erscheinen soll.
     */
    @Column(nullable = false)
    
    private Boolean selectable = Boolean.FALSE;



    public Halbjahr getHalbjahr() {
        return this.halbjahr;
    }

    public void setHalbjahr(final Halbjahr halbjahr) {
        this.halbjahr = halbjahr;
    }

    public Integer getJahr() {
        return this.jahr;
    }

    public void setJahr(final Integer jahr) {
        this.jahr = jahr;
    }

    public Boolean getSelectable() {
        return this.selectable;
    }

    public void setSelectable(final Boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public String toString() {
        return (jahr - 1) + "/" + jahr % 100 + " " + halbjahr;
    }

    /**
     * Liefert den relativen Pfad
     */
    public String createRelativePathName() {
        return (jahr - 1) + "-" + jahr % 100 + "/" + halbjahr
                .createRelativePathName();
    }

    public int compareTo(final Schulhalbjahr other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.jahr, other.jahr);
        compareBuilder.append(this.halbjahr, other.halbjahr);
        compareBuilder.append(this.selectable, other.selectable);
        return compareBuilder.toComparison();
    }

    // TODO toPrintMap elegant implementieren, evtl BeanWrapper?.
//  String toPrintMap(Map<String, String> printMap) {
//      printMap['shj_jahr'] = "${jahr-1}/${jahr.toString()[-2..-1]}";
//      printMap['shj_halbjahr'] = "${halbjahr.toString()[0..-5]}";
//      printMap['shj'] = this.toString();
//  }
}
