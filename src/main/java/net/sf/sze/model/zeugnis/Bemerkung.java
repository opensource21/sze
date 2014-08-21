// Bemerkung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.sze.model.zeugnisconfig.BemerkungsBaustein;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Bemerkungen zu Zeugnissen.
 *
 */
@Entity
@Table(name = "bemerkung")
public class Bemerkung extends AbstractBemerkung implements Serializable,
        Comparable<Bemerkung> {


    /** The baustein. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "baustein_id", nullable = false)
    private BemerkungsBaustein baustein;


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
    }

    @Override
    public int compareTo(final Bemerkung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.getSortierung(), other.getSortierung());
        compareBuilder.append(this.baustein, other.baustein);
        compareBuilder.append(this.getZeugnis(), other.getZeugnis());
        compareBuilder.append(this.getFixText(), other.getFixText());
        compareBuilder.append(this.getFreiText(), other.getFreiText());
        compareBuilder.append(this.getErSieStattNamen(), other.getErSieStattNamen());
        return compareBuilder.toComparison();
    }


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getFixText() {
        if (baustein == null) {
            return "";
        }
        return baustein.getText();
    }

    @Override
    public String toString() {
        if (StringUtils.isNotBlank(getFreiText())) {
            return baustein.getName() + " " + getFreiText();
        } else {
            return baustein.getName() + " " + getFixText();
        }
    }
}
