// ZeugnisFormular.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.model.stammdaten.Klasse;
import net.sf.sze.model.zeugnisconfig.Schulhalbjahr;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.oval.validation.Unique;

/**
 * Alle Informationen zu dem Formular des Zeugnisses.
 *
 */
@Entity
@Table(name = "zeugnis_formular",
        uniqueConstraints = @UniqueConstraint(columnNames = {"schulhalbjahr_id",
        "klasse_id", "beschreibung"},
                name = "UK_ZEUGNIS_FORMULAR_HALBJAHR_KLASSE_BESCHREIBUNG"))
public class ZeugnisFormular extends RevisionModel implements Serializable,
        Comparable<ZeugnisFormular> {

    /** The beschreibung. */
    @Column(nullable = false, length = 50)
    @Unique(value = "schulhalbjahr, klasse",
        message = "validation.unique.schuljahr_klasse")
    private String beschreibung;

    /** The template file name. */
    @Column(name = "template_file_name", nullable = false, length = 255)
    private String templateFileName;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden. */
    @Column(length = 300)
    private String leitspruch;

    /** The quelle leitspruch. */
    @Column(name = "quelle_leitspruch", length = 60)
    private String quelleLeitspruch;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden. */
    @Column(length = 300)
    private String leitspruch2;

    /** Defaultfall für diese Klasse kann im Zeugnis überschrieben werden,
     * wenn leer wird es vom Schulhalbjahr übernommen.. */
    @Temporal(TemporalType.DATE)
    private Date ausgabeDatum;

    /** The nachteils ausgleichs datum, wenn leer wird es vom Schulhalbjahr übernommen.. */
    @Temporal(TemporalType.DATE)
    private Date nachteilsAusgleichsDatum;


    /** The quelle leitspruch2. */
    @Column(name = "quelle_leitspruch2", length = 60)
    private String quelleLeitspruch2;

    // bi-directional many-to-one association to SchulfachDetailInfo

    /** The schulfach detail infos. */
    @OneToMany(mappedBy = "formular")
    private List<SchulfachDetailInfo> schulfachDetailInfos;

    // bi-directional many-to-one association to Zeugnis

    /** The zeugnis. */
    @OneToMany(mappedBy = "formular")
    private List<Zeugnis> zeugnis;

    // bi-directional many-to-one association to Klasse

    /** The klasse. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "klasse_id", nullable = false)
    private Klasse klasse;

    // bi-directional many-to-one association to Schulhalbjahr

    /** The schulhalbjahr. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulhalbjahr_id", nullable = false)
    private Schulhalbjahr schulhalbjahr;

    /**
     * Gets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @return the defaultfall für diese Klasse kann im Zeugnis überschrieben
     *         werden
     */
    public Date getAusgabeDatum() {
        return this.ausgabeDatum;
    }

    /**
     * Sets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @param ausgabeDatum the new defaultfall für diese Klasse kann im Zeugnis
     *            überschrieben werden
     */
    public void setAusgabeDatum(final Date ausgabeDatum) {
        this.ausgabeDatum = ausgabeDatum;
    }

    /**
     * Gets the beschreibung.
     *
     * @return the beschreibung
     */
    public String getBeschreibung() {
        return this.beschreibung;
    }

    /**
     * Sets the beschreibung.
     *
     * @param beschreibung the new beschreibung
     */
    public void setBeschreibung(final String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Gets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @return the defaultfall für diese Klasse kann im Zeugnis überschrieben
     *         werden
     */
    public String getLeitspruch() {
        return this.leitspruch;
    }

    /**
     * Sets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @param leitspruch the new defaultfall für diese Klasse kann im Zeugnis
     *            überschrieben werden
     */
    public void setLeitspruch(final String leitspruch) {
        this.leitspruch = leitspruch;
    }

    /**
     * Gets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @return the defaultfall für diese Klasse kann im Zeugnis überschrieben
     *         werden
     */
    public String getLeitspruch2() {
        return this.leitspruch2;
    }

    /**
     * Sets the defaultfall für diese Klasse kann im Zeugnis überschrieben
     * werden.
     *
     * @param leitspruch2 the new defaultfall für diese Klasse kann im Zeugnis
     *            überschrieben werden
     */
    public void setLeitspruch2(final String leitspruch2) {
        this.leitspruch2 = leitspruch2;
    }

    /**
     * Gets the nachteils ausgleichs datum.
     *
     * @return the nachteils ausgleichs datum
     */
    public Date getNachteilsAusgleichsDatum() {
        return this.nachteilsAusgleichsDatum;
    }

    /**
     * Liefert das {@link #nachteilsAusgleichsDatum} entweder vom Formular
     * oder dem Schulhalbjahr.
     * @return das {@link #nachteilsAusgleichsDatum} entweder vom Formular
     * oder dem Schulhalbjahr.
     */
    public Date findNachteilsAusgleichsDatum() {
        if (nachteilsAusgleichsDatum == null && schulhalbjahr != null) {
            return schulhalbjahr.getNachteilsAusgleichsDatum();
        } else {
            return nachteilsAusgleichsDatum;
        }
    }

    /**
     * Sets the nachteils ausgleichs datum.
     *
     * @param nachteilsAusgleichsDatum the new nachteils ausgleichs datum
     */
    public void setNachteilsAusgleichsDatum(
            final Date nachteilsAusgleichsDatum) {
        this.nachteilsAusgleichsDatum = nachteilsAusgleichsDatum;
    }

    /**
     * Gets the quelle leitspruch.
     *
     * @return the quelle leitspruch
     */
    public String getQuelleLeitspruch() {
        return this.quelleLeitspruch;
    }

    /**
     * Sets the quelle leitspruch.
     *
     * @param quelleLeitspruch the new quelle leitspruch
     */
    public void setQuelleLeitspruch(final String quelleLeitspruch) {
        this.quelleLeitspruch = quelleLeitspruch;
    }

    /**
     * Gets the quelle leitspruch2.
     *
     * @return the quelle leitspruch2
     */
    public String getQuelleLeitspruch2() {
        return this.quelleLeitspruch2;
    }

    /**
     * Sets the quelle leitspruch2.
     *
     * @param quelleLeitspruch2 the new quelle leitspruch2
     */
    public void setQuelleLeitspruch2(final String quelleLeitspruch2) {
        this.quelleLeitspruch2 = quelleLeitspruch2;
    }

    /**
     * Gets the template file name.
     *
     * @return the template file name
     */
    public String getTemplateFileName() {
        return this.templateFileName;
    }

    /**
     * Sets the template file name.
     *
     * @param templateFileName the new template file name
     */
    public void setTemplateFileName(final String templateFileName) {
        this.templateFileName = templateFileName;
    }

    /**
     * Gets the schulfach detail infos.
     *
     * @return the schulfach detail infos
     */
    public List<SchulfachDetailInfo> getSchulfachDetailInfos() {
        return this.schulfachDetailInfos;
    }

    /**
     * Sets the schulfach detail infos.
     *
     * @param schulfachDetailInfos the new schulfach detail infos
     */
    public void setSchulfachDetailInfos(
            final List<SchulfachDetailInfo> schulfachDetailInfos) {
        this.schulfachDetailInfos = schulfachDetailInfos;
    }

    /**
     * Gets the zeugnis.
     *
     * @return the zeugnis
     */
    public List<Zeugnis> getZeugnis() {
        return this.zeugnis;
    }

    /**
     * Sets the zeugnis.
     *
     * @param zeugnis the new zeugnis
     */
    public void setZeugnis(final List<Zeugnis> zeugnis) {
        this.zeugnis = zeugnis;
    }

    /**
     * Gets the klasse.
     *
     * @return the klasse
     */
    public Klasse getKlasse() {
        return this.klasse;
    }

    /**
     * Sets the klasse.
     *
     * @param klasse the new klasse
     */
    public void setKlasse(final Klasse klasse) {
        this.klasse = klasse;
    }

    /**
     * Gets the schulhalbjahr.
     *
     * @return the schulhalbjahr
     */
    public Schulhalbjahr getSchulhalbjahr() {
        return this.schulhalbjahr;
    }

    /**
     * Sets the schulhalbjahr.
     *
     * @param schulhalbjahr the new schulhalbjahr
     */
    public void setSchulhalbjahr(final Schulhalbjahr schulhalbjahr) {
        this.schulhalbjahr = schulhalbjahr;
    }

    // ********************************************************************
    @Override
    public String toString() {

        final String klassenStr;
        if (klasse != null && schulhalbjahr != null) {
            klassenStr = klasse
                    .calculateKlassenname(schulhalbjahr.getJahr());
        } else {
            klassenStr = "unbekannt " + klasse + " in " + schulhalbjahr;
        }
        return beschreibung + " Hj: " + schulhalbjahr + " Klasse: " + klassenStr;
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
