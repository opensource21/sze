// SchulamtsBemerkung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.sze.model.zeugnisconfig.Schulamt;
import net.sf.sze.model.zeugnisconfig.SchulamtsBemerkungsBaustein;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Schulamtsanmerkungen.
 *
 */
@Entity
@Table(name = "schulamts_bemerkung")
public class SchulamtsBemerkung extends AbstractBemerkung implements Serializable,
        Comparable<SchulamtsBemerkung> {

    /** The schulamt. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamt_id", nullable = false)
    private Schulamt schulamt;

    // bi-directional many-to-one association to SchulamtsBemerkungsBaustein
    /** The schulamts bemerkungs baustein. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamts_baustein_id", nullable = false)
    private SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein;


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
     * Gets the fix text.
     *
     * @return the fix text
     */
    @Override
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
        compareBuilder.append(this.getSortierung(), other.getSortierung());
        compareBuilder.append(this.schulamt, other.schulamt);
        compareBuilder.append(this.schulamtsBemerkungsBaustein, other
                .schulamtsBemerkungsBaustein);
        compareBuilder.append(this.getZeugnis(), other.getZeugnis());
        compareBuilder.append(this.getFixText(), other.getFixText());
        compareBuilder.append(this.getFreiText(), other.getFreiText());
        compareBuilder.append(this.getErSieStattNamen(), other.getErSieStattNamen());
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (StringUtils.isNotBlank(getFreiText())) {
            return schulamt.getName() + " " + getFreiText();
        } else {
            return schulamt.getName() + " " + getFixText();
        }
    }

}
