// Bemerkung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Bemerkungen zu Zeugnissen.
 *
 */
@Entity
@Table(name = "bemerkung")
public class Bemerkung extends VersionedModel implements Serializable,
        Comparable<Bemerkung> {

    /** The sortierung. */
    @Column(nullable = false)

    private Long sortierung = Long.valueOf(10);

    /** The er sie statt namen. */
    @Column(name = "er_sie_statt_namen", nullable = false)
    private boolean erSieStattNamen = false;

    /** The fix text. */
    @Transient
    @ValidVariableText
    private String fixText;

    /** The frei text. */
    @Column(name = "frei_text", length = 500)
    @ValidVariableText
    private String freiText;

    // bi-directional many-to-one association to BemerkungsBaustein

    /** The baustein. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "baustein_id", nullable = false)
    private BemerkungsBaustein baustein;

    // bi-directional many-to-one association to Zeugnis

    /** The zeugnis. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    private Zeugnis zeugnis;

    /**
     * Gets the er sie statt namen.
     *
     * @return the er sie statt namen
     */
    public boolean getErSieStattNamen() {
        return this.erSieStattNamen;
    }

    /**
     * Sets the er sie statt namen.
     *
     * @param erSieStattNamen the new er sie statt namen
     */
    public void setErSieStattNamen(final boolean erSieStattNamen) {
        this.erSieStattNamen = erSieStattNamen;
    }

    /**
     * Gets the frei text.
     *
     * @return the frei text
     */
    public String getFreiText() {
        return this.freiText;
    }

    /**
     * Sets the frei text.
     *
     * @param freiText the new frei text
     */
    public void setFreiText(final String freiText) {
        this.freiText = freiText;
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
     * Gets the baustein.
     *
     * @return the baustein
     */
    public BemerkungsBaustein getBaustein() {
        return this.baustein;
    }

    /**
     * Sets the baustein.
     *
     * @param bemerkungsBaustein the new baustein
     */
    public void setBaustein(final BemerkungsBaustein bemerkungsBaustein) {
        this.baustein = bemerkungsBaustein;
        this.fixText = this.baustein.getText();
    }

    /**
     * Gets the zeugnis.
     *
     * @return the zeugnis
     */
    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

    /**
     * Sets the zeugnis.
     *
     * @param zeugni the new zeugnis
     */
    public void setZeugnis(final Zeugnis zeugni) {
        this.zeugnis = zeugni;
    }

    @Override
    public int compareTo(final Bemerkung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.baustein, other.baustein);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.fixText, other.fixText);
        compareBuilder.append(this.freiText, other.freiText);
        compareBuilder.append(this.erSieStattNamen, other.erSieStattNamen);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (StringUtils.isNotBlank(freiText)) {
            return baustein.getName() + " " + freiText;
        } else {
            return baustein.getName() + " " + fixText;
        }
    }

    /**
     * Erzeugt den Text für den Druck.
     * @param schueler der zugehörige Schüler.
     * @param datum das Zeugnisausgabedatum.
     * @param schuljahr das aktuelle Schuljahr.
     * @return die Bemerkung.
     */
    public String createPrintText(final Schueler schueler, final Date datum,
            final String schuljahr) {
        String text = StringUtils.isNotBlank(freiText) ? freiText
                : fixText;
        text = VariableUtility.createPrintText(text, schueler, datum,
                erSieStattNamen, schuljahr);

        if (!text.endsWith("\n") && !text.endsWith(" ")) {
            text += " ";
        }

        return text;
    }
}
