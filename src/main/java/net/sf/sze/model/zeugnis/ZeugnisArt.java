// ZeugnisArt.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;

import org.apache.commons.lang.builder.CompareToBuilder;

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

    @Column(nullable = false, length = 30)
    
    @Size(max = 30)
    private String name;

    @Column(nullable = false)
    
    private Long sortierung = Long.valueOf(10);

    /** Titel des Zeugnisses */
    @Column(nullable = false, length = 255)
    
    
    private String titel = "Zeugnis";

    @Column(name = "abschluss_grad", nullable = false, length = 255)
    
    
    private String abschlussGrad = "";



    @Column(name = "note_als_text_darstellen", nullable = false)
    
    private Boolean noteAlsTextDarstellen;

    @Column(name = "platz_fuer_siegel", nullable = false)
    
    private Boolean platzFuerSiegel;

    @Column(name = "print_versetzungsbemerkung", nullable = false)
    
    private Boolean printVersetzungsbemerkung;

    @Column(nullable = false)
    
    private Boolean aktiv = Boolean.TRUE;


    public String getAbschlussGrad() {
        return this.abschlussGrad;
    }

    public void setAbschlussGrad(final String abschlussGrad) {
        this.abschlussGrad = abschlussGrad;
    }

    public Boolean getAktiv() {
        return this.aktiv;
    }

    public void setAktiv(final Boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Boolean getNoteAlsTextDarstellen() {
        return this.noteAlsTextDarstellen;
    }

    public void setNoteAlsTextDarstellen(final Boolean noteAlsTextDarstellen) {
        this.noteAlsTextDarstellen = noteAlsTextDarstellen;
    }

    public Boolean getPlatzFuerSiegel() {
        return this.platzFuerSiegel;
    }

    public void setPlatzFuerSiegel(final Boolean platzFuerSiegel) {
        this.platzFuerSiegel = platzFuerSiegel;
    }

    public Boolean getPrintVersetzungsbemerkung() {
        return this.printVersetzungsbemerkung;
    }

    public void setPrintVersetzungsbemerkung(
            final Boolean printVersetzungsbemerkung) {
        this.printVersetzungsbemerkung = printVersetzungsbemerkung;
    }

    public Long getSortierung() {
        return this.sortierung;
    }

    public void setSortierung(final Long sortierung) {
        this.sortierung = sortierung;
    }

    public String getTitel() {
        return this.titel;
    }

    public void setTitel(final String titel) {
        this.titel = titel;
    }

    // ********************************************************
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final ZeugnisArt other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(!this.aktiv, !other.aktiv);
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.name, other.name);
        return compareBuilder.toComparison();
    }
}
