// SchulamtsBemerkung.java
//
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

import net.sf.oval.constraint.Size;
import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.StringUtil;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.jpa.helper.VersionedModel;

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

    /** The fix text. */
    @Transient
    @Size(max = 600)
    @ValidVariableText
    private String fixText;

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
        setFixText();
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

    private void setFixText() {
        final String schulamtsText = (schulamt != null) ? schulamt
                .getBeschreibenderSatz() : null;
        final String schulamtsBausteinText = (schulamtsBemerkungsBaustein
                != null) ? schulamtsBemerkungsBaustein.getBeschreibenderSatz()
                : null;
        setFixText(schulamtsText + " " + schulamtsBausteinText);
    }

    /**
     * Gets the fix text.
     *
     * @return the fix text
     */
    public String getFixText() {
        return fixText;
    }

    /**
     * Sets the fix text.
     *
     * @param fixText the new fix text
     */
    public void setFixText(final String fixText) {
        this.fixText = fixText;
    }

    @Override
    public int compareTo(final SchulamtsBemerkung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.sortierung, other.sortierung);
        compareBuilder.append(this.schulamt, other.schulamt);
        compareBuilder.append(this.schulamtsBemerkungsBaustein, other
                .schulamtsBemerkungsBaustein);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.fixText, other.fixText);
        compareBuilder.append(this.freiText, other.freiText);
        compareBuilder.append(this.erSieStattNamen, other.erSieStattNamen);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (StringUtil.containsInformation(freiText)) {
            return schulamt.getName() + " " + freiText;
        } else {
            return schulamt.getName() + " " + fixText;
        }
    }

    public String createPrintText(final Schueler schueler, final Date datum,
            final String schuljahr) {
        final String text;
        if (StringUtil.containsInformation(freiText)) {
            text = freiText;
        } else {
            text = fixText;
        }

        return VariableUtility.createPrintText(text, schueler.getRufname(),
                schueler.getVorname(), schueler.getName(), schueler
                .getGeschlecht(), datum, erSieStattNamen, schuljahr);
    }
}
