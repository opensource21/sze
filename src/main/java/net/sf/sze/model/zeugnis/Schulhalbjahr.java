// Schulhalbjahr.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.jpa.helper.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Beschreibt ein Schulhalbjahr.
 *
 */
@Entity
@Table(name = "schulhalbjahr",
        uniqueConstraints = @UniqueConstraint(columnNames = {"jahr",
        "halbjahr"}, name = "UK_SCHULAHLBJAHR_JAHR_HALBJAHR"))
public class Schulhalbjahr extends VersionedModel implements Serializable,
        Comparable<Schulhalbjahr> {

    /**
     * Das Jahr in dem die Zeugnisse für das Schuljahr erstellt werden.
     */
    @Column(nullable = false)
    // TODO Gui-Darstellung mit CB 2000..2050, besser Default raten.
    private int jahr;

    /**
     * erstes oder 2.Halbjahr.
     */
    @Column(nullable = false)

    private Halbjahr halbjahr;

    /**
     * Kennzeichen, ob diese Jahr in der Auswahlliste erscheinen soll.
     */
    @Column(nullable = false)

    private Boolean selectable = Boolean.FALSE;

    /**
     * Gets the erstes oder 2.
     *
     * @return the erstes oder 2
     */
    public Halbjahr getHalbjahr() {
        return this.halbjahr;
    }

    /**
     * Sets the erstes oder 2.
     *
     * @param halbjahr the new erstes oder 2
     */
    public void setHalbjahr(final Halbjahr halbjahr) {
        this.halbjahr = halbjahr;
    }

    /**
     * Gets the das Jahr in dem die Zeugnisse für das Schuljahr erstellt werden.
     *
     * @return the das Jahr in dem die Zeugnisse für das Schuljahr erstellt
     *         werden
     */
    public int getJahr() {
        return this.jahr;
    }

    /**
     * Sets the das Jahr in dem die Zeugnisse für das Schuljahr erstellt werden.
     *
     * @param jahr the new das Jahr in dem die Zeugnisse für das Schuljahr
     *            erstellt werden
     */
    public void setJahr(final int jahr) {
        this.jahr = jahr;
    }

    /**
     * Gets the kennzeichen, ob diese Jahr in der Auswahlliste erscheinen soll.
     *
     * @return the kennzeichen, ob diese Jahr in der Auswahlliste erscheinen
     *         soll
     */
    public Boolean getSelectable() {
        return this.selectable;
    }

    /**
     * Sets the kennzeichen, ob diese Jahr in der Auswahlliste erscheinen soll.
     *
     * @param selectable the new kennzeichen, ob diese Jahr in der Auswahlliste
     *            erscheinen soll
     */
    public void setSelectable(final Boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * Kennzeichen ob es das erste Halbjahr ist.
     * @return true wenn es das erste Halbjahr ist.
     */
    public boolean isErstesHalbjahr() {
        return Halbjahr.Erstes_Halbjahr.equals(halbjahr);
    }

    @Override
    public String toString() {
        return (jahr - 1) + "/" + jahr % 100 + " " + halbjahr;
    }

    /**
     * Liefert den relativen Pfad.
     * @return der relative Pfad.
     */
    public String createRelativePathName() {
        return (jahr - 1) + "-" + jahr % 100 + "/" + halbjahr
                .createRelativePathName();
    }

    @Override
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
