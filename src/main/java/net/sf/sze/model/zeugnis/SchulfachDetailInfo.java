// SchulfachDetailInfo.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.Size;
import net.sf.sze.model.base.VersionedModel;

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
public class SchulfachDetailInfo extends VersionedModel
        implements Serializable, Comparable<SchulfachDetailInfo> {
    @Column(name = "detail_info", nullable = false, length = 50)
    
    @Size(max = 50)
    private String detailInfo;

    // bi-directional many-to-one association to Schulfach
    @ManyToOne(optional=false)
    @JoinColumn(name = "schulfach_id", nullable = false)
    
    private Schulfach schulfach;

    // bi-directional many-to-one association to ZeugnisFormular
    @ManyToOne(optional=false)
    @JoinColumn(name = "formular_id", nullable = false)
    
    private ZeugnisFormular formular;


    public String getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(final String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public Schulfach getSchulfach() {
        return this.schulfach;
    }

    public void setSchulfach(final Schulfach schulfach) {
        this.schulfach = schulfach;
    }

    public ZeugnisFormular getFormular() {
        return this.formular;
    }

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
        return schulfach.getName() + " " + detailInfo;
    }
}
