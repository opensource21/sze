// SchulamtsBemerkung.java
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

import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.fuwesta.jpa.helper.VersionedModel;

/**
 * Schulamtsanmerkungen.
 *
 */
@Entity
@Table(name = "schulamts_bemerkung")
public class SchulamtsBemerkung extends VersionedModel implements Serializable,
        Comparable<SchulamtsBemerkung> {

    /** The sortierung. */
    @Column(nullable = false)
    private Long sortierung = Long.valueOf(10);

    /** The er sie statt namen. */
    @Column(name = "er_sie_statt_namen", nullable = false)
    private boolean erSieStattNamen = false;

    /** The frei text. */
    @Column(name = "frei_text", length = 600)
    @ValidVariableText
    private String freiText;

    // bi-directional many-to-one association to Schulamt

    /** The schulamt. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamt_id", nullable = false)
    private Schulamt schulamt;

    // bi-directional many-to-one association to SchulamtsBemerkungsBaustein
    /** The schulamts bemerkungs baustein. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamts_baustein_id", nullable = false)
    private SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein;

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
     * Gets the schulamt.
     *
     * @return the schulamt
     */
    public Schulamt getSchulamt() {
        return this.schulamt;
    }

    /**
     * Sets the schulamt.
     *
     * @param schulamt the new schulamt
     */
    public void setSchulamt(final Schulamt schulamt) {
        this.schulamt = schulamt;
    }

    /**
     * Gets the schulamts bemerkungs baustein.
     *
     * @return the schulamts bemerkungs baustein
     */
    public SchulamtsBemerkungsBaustein getSchulamtsBemerkungsBaustein() {
        return this.schulamtsBemerkungsBaustein;
    }

    /**
     * Sets the schulamts bemerkungs baustein.
     *
     * @param schulamtsBemerkungsBaustein the new schulamts bemerkungs baustein
     */
    public void setSchulamtsBemerkungsBaustein(
            final SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein) {
        this.schulamtsBemerkungsBaustein = schulamtsBemerkungsBaustein;
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


    /**
     * Gets the fix text.
     *
     * @return the fix text
     */
    public String getFixText() {
        final String schulamtsText = (schulamt != null) ? schulamt
                .getBeschreibenderSatz() : null;
        final String schulamtsBausteinText = (schulamtsBemerkungsBaustein
                != null) ? schulamtsBemerkungsBaustein.getBeschreibenderSatz()
                : null;
        return schulamtsText + " " + schulamtsBausteinText;
    }


    @Override
    public int compareTo(final SchulamtsBemerkung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.schulamt, other.schulamt);
        compareBuilder.append(this.schulamtsBemerkungsBaustein, other
                .schulamtsBemerkungsBaustein);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.getFixText(), other.getFixText());
        compareBuilder.append(this.freiText, other.freiText);
        compareBuilder.append(this.erSieStattNamen, other.erSieStattNamen);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (StringUtils.isNotBlank(freiText)) {
            return schulamt.getName() + " " + freiText;
        } else {
            return schulamt.getName() + " " + getFixText();
        }
    }

    /**
     * Erzeugt den Text für den Druck.
     * @return die Bemerkung.
     */
    public String createPrintText() {
        return createPrintText(zeugnis.getSchueler(),
                    zeugnis.getFormular().getNachteilsAusgleichsDatum(),
                    zeugnis.getSchulhalbjahr().getSchuljahr());
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
        final String text;
        if (StringUtils.isNotBlank(freiText)) {
            text = freiText;
        } else {
            text = getFixText();
        }

        return VariableUtility.createPrintText(text, schueler, datum,
                erSieStattNamen, schuljahr);
    }
}
