// SchulfachDetailInfo.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.model.zeugnisconfig.Schulfach;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Manche Schulfächer umfassen mehrere Themen, in diesem Fall wird es im Detail
 * noch einmal genauer spezifiziert.
 *
 */
@Entity
@Table(name = "schulfach_detail_info",
        uniqueConstraints = @UniqueConstraint(columnNames = {"formular_id",
        "schulfach_id"}, name = "UK_SCHULFACH_DETAIL_FORMULAR_SCHULFACH"))
public class SchulfachDetailInfo extends RevisionModel
        implements Serializable, Comparable<SchulfachDetailInfo> {

    /** The detail info. */
    @Column(name = "detail_info", nullable = false, length = 50)
    private String detailInfo;

    // bi-directional many-to-one association to Schulfach

    /** The schulfach. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulfach_id", nullable = false)
    private Schulfach schulfach;

    // bi-directional many-to-one association to ZeugnisFormular

    /** The formular. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "formular_id", nullable = false)
    private ZeugnisFormular formular;

    /**
     * Gets the detail info.
     *
     * @return the detail info
     */
    public String getDetailInfo() {
        return this.detailInfo;
    }

    /**
     * Sets the detail info.
     *
     * @param detailInfo the new detail info
     */
    public void setDetailInfo(final String detailInfo) {
        this.detailInfo = detailInfo;
    }

    /**
     * Gets the schulfach.
     *
     * @return the schulfach
     */
    public Schulfach getSchulfach() {
        return this.schulfach;
    }

    /**
     * Sets the schulfach.
     *
     * @param schulfach the new schulfach
     */
    public void setSchulfach(final Schulfach schulfach) {
        this.schulfach = schulfach;
    }

    /**
     * Gets the formular.
     *
     * @return the formular
     */
    public ZeugnisFormular getFormular() {
        return this.formular;
    }

    /**
     * Sets the formular.
     *
     * @param zeugnisFormular the new formular
     */
    public void setFormular(final ZeugnisFormular zeugnisFormular) {
        this.formular = zeugnisFormular;
    }

    @Override
    public int compareTo(final SchulfachDetailInfo other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.formular, other.formular);
        compareBuilder.append(this.schulfach, other.schulfach);
        compareBuilder.append(this.detailInfo, other.detailInfo);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (schulfach == null) {
            return detailInfo;
        }
        return schulfach.getName() + " " + detailInfo;
    }
}
