// ZeugnisFormular.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.model.stammdaten.Klasse;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Alle Informationen zu dem Formular des Zeugnisses.
 *
 */
@Entity
@Table(name = "zeugnis_formular",
        uniqueConstraints = @UniqueConstraint(columnNames = {"schulhalbjahr_id",
        "klasse_id", "beschreibung"},
                name = "UK_ZEUGNIS_FORMULAR_HALBJAHR_KLASSE_BESCHREIBUNG"))
public class ZeugnisFormular extends VersionedModel implements Serializable,
        Comparable<ZeugnisFormular> {

    @Column(nullable = false, length = 50)
    private String beschreibung;

    @Column(name = "template_file_name", nullable = false, length = 255)


    private String templateFileName;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden */
    @Column(length = 300)
    private String leitspruch;

    @Column(name = "quelle_leitspruch", length = 60)
    private String quelleLeitspruch;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden */
    @Column(length = 300)
    private String leitspruch2;

    @Column(name = "quelle_leitspruch2", length = 60)
    private String quelleLeitspruch2;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden */
    @Column(name = "ausgabe_datum", nullable = false)

    private Date ausgabeDatum;

    @Column(name = "nachteils_ausgleichs_datum", nullable = false)

    private Date nachteilsAusgleichsDatum;

    // bi-directional many-to-one association to SchulfachDetailInfo
    @OneToMany(mappedBy = "formular")
    private List<SchulfachDetailInfo> schulfachDetailInfos;

    // bi-directional many-to-one association to Zeugnis
    @OneToMany(mappedBy = "formular")
    private List<Zeugnis> zeugnis;

    // bi-directional many-to-one association to Klasse
    @ManyToOne(optional = false)
    @JoinColumn(name = "klasse_id", nullable = false)

    private Klasse klasse;

    // bi-directional many-to-one association to Schulhalbjahr
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulhalbjahr_id", nullable = false)

    private Schulhalbjahr schulhalbjahr;

    public ZeugnisFormular() {
    }

    public Date getAusgabeDatum() {
        return this.ausgabeDatum;
    }

    public void setAusgabeDatum(final Date ausgabeDatum) {
        this.ausgabeDatum = ausgabeDatum;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public void setBeschreibung(final String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getLeitspruch() {
        return this.leitspruch;
    }

    public void setLeitspruch(final String leitspruch) {
        this.leitspruch = leitspruch;
    }

    public String getLeitspruch2() {
        return this.leitspruch2;
    }

    public void setLeitspruch2(final String leitspruch2) {
        this.leitspruch2 = leitspruch2;
    }

    public Date getNachteilsAusgleichsDatum() {
        return this.nachteilsAusgleichsDatum;
    }

    public void setNachteilsAusgleichsDatum(
            final Date nachteilsAusgleichsDatum) {
        this.nachteilsAusgleichsDatum = nachteilsAusgleichsDatum;
    }

    public String getQuelleLeitspruch() {
        return this.quelleLeitspruch;
    }

    public void setQuelleLeitspruch(final String quelleLeitspruch) {
        this.quelleLeitspruch = quelleLeitspruch;
    }

    public String getQuelleLeitspruch2() {
        return this.quelleLeitspruch2;
    }

    public void setQuelleLeitspruch2(final String quelleLeitspruch2) {
        this.quelleLeitspruch2 = quelleLeitspruch2;
    }

    public String getTemplateFileName() {
        return this.templateFileName;
    }

    public void setTemplateFileName(final String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public List<SchulfachDetailInfo> getSchulfachDetailInfos() {
        return this.schulfachDetailInfos;
    }

    public void setSchulfachDetailInfos(
            final List<SchulfachDetailInfo> schulfachDetailInfos) {
        this.schulfachDetailInfos = schulfachDetailInfos;
    }

    public List<Zeugnis> getZeugnis() {
        return this.zeugnis;
    }

    public void setZeugnis(final List<Zeugnis> zeugnis) {
        this.zeugnis = zeugnis;
    }

    public Klasse getKlasse() {
        return this.klasse;
    }

    public void setKlasse(final Klasse klasse) {
        this.klasse = klasse;
    }

    public Schulhalbjahr getSchulhalbjahr() {
        return this.schulhalbjahr;
    }

    public void setSchulhalbjahr(final Schulhalbjahr schulhalbjahr) {
        this.schulhalbjahr = schulhalbjahr;
    }

    // ********************************************************************
    @Override
    public String toString() {
        return beschreibung + " Hj: " + schulhalbjahr + " Klasse: " + klasse
                .calculateKlassenname(schulhalbjahr.getJahr());
    }

    @Override
    public int compareTo(final ZeugnisFormular other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.schulhalbjahr, other.schulhalbjahr);
        compareBuilder.append(this.klasse, other.klasse);
        compareBuilder.append(this.beschreibung, other.beschreibung);
        return compareBuilder.toComparison();
    }
}
