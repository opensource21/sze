// ZeugnisArt.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

import net.sf.oval.constraint.Size;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Beschreibt die Art des Zeugnisses, wie Abschluss-Zeugnis oder nicht,
 * welcher Abschluss.
 */
@Entity
@Table(name = "zeugnis_art",
        uniqueConstraints = @UniqueConstraint(columnNames = "name",
        name = "UK_ZEUGNIS_ART_NAME"))
public class ZeugnisArt extends VersionedModel implements Serializable,
        Comparable<ZeugnisArt> {

    /** The name. */
    @Column(nullable = false, length = 30)

    @Size(max = 30)
    private String name;

    /** The sortierung. */
    @Column(nullable = false)
    private Long sortierung = Long.valueOf(10);

    /** Titel des Zeugnisses. */
    @Column(nullable = false, length = 255)
    private String titel = "Zeugnis";

    /** The abschluss grad. */
    @Column(name = "abschluss_grad", nullable = false, length = 255)

    private String abschlussGrad = "";

    /** The note als text darstellen. */
    @Column(name = "note_als_text_darstellen", nullable = false)

    private Boolean noteAlsTextDarstellen;

    /** The platz fuer siegel. */
    @Column(name = "platz_fuer_siegel", nullable = false)

    private Boolean platzFuerSiegel;

    /** The print versetzungsbemerkung. */
    @Column(name = "print_versetzungsbemerkung", nullable = false)

    private Boolean printVersetzungsbemerkung;

    /** The aktiv. */
    @Column(nullable = false)

    private Boolean aktiv = Boolean.TRUE;

    /**
     * Gets the abschluss grad.
     *
     * @return the abschluss grad
     */
    public String getAbschlussGrad() {
        return this.abschlussGrad;
    }

    /**
     * Sets the abschluss grad.
     *
     * @param abschlussGrad the new abschluss grad
     */
    public void setAbschlussGrad(final String abschlussGrad) {
        this.abschlussGrad = abschlussGrad;
    }

    /**
     * Gets the aktiv.
     *
     * @return the aktiv
     */
    public Boolean getAktiv() {
        return this.aktiv;
    }

    /**
     * Sets the aktiv.
     *
     * @param aktiv the new aktiv
     */
    public void setAktiv(final Boolean aktiv) {
        this.aktiv = aktiv;
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
     * Gets the note als text darstellen.
     *
     * @return the note als text darstellen
     */
    public Boolean getNoteAlsTextDarstellen() {
        return this.noteAlsTextDarstellen;
    }

    /**
     * Sets the note als text darstellen.
     *
     * @param noteAlsTextDarstellen the new note als text darstellen
     */
    public void setNoteAlsTextDarstellen(final Boolean noteAlsTextDarstellen) {
        this.noteAlsTextDarstellen = noteAlsTextDarstellen;
    }

    /**
     * Gets the platz fuer siegel.
     *
     * @return the platz fuer siegel
     */
    public Boolean getPlatzFuerSiegel() {
        return this.platzFuerSiegel;
    }

    /**
     * Sets the platz fuer siegel.
     *
     * @param platzFuerSiegel the new platz fuer siegel
     */
    public void setPlatzFuerSiegel(final Boolean platzFuerSiegel) {
        this.platzFuerSiegel = platzFuerSiegel;
    }

    /**
     * Gets the prints the versetzungsbemerkung.
     *
     * @return the prints the versetzungsbemerkung
     */
    public Boolean getPrintVersetzungsbemerkung() {
        return this.printVersetzungsbemerkung;
    }

    /**
     * Sets the prints the versetzungsbemerkung.
     *
     * @param printVersetzungsbemerkung the new prints the versetzungsbemerkung
     */
    public void setPrintVersetzungsbemerkung(
            final Boolean printVersetzungsbemerkung) {
        this.printVersetzungsbemerkung = printVersetzungsbemerkung;
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
     * Gets the titel des Zeugnisses.
     *
     * @return the titel des Zeugnisses
     */
    public String getTitel() {
        return this.titel;
    }

    /**
     * Sets the titel des Zeugnisses.
     *
     * @param titel the new titel des Zeugnisses
     */
    public void setTitel(final String titel) {
        this.titel = titel;
    }

    // ********************************************************
    @Override
    public String toString() {
        return name;
    }

    @SuppressWarnings("boxing")
    @Override
    public int compareTo(final ZeugnisArt other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.name, other.name);
        return compareBuilder.toComparison();
    }
}
