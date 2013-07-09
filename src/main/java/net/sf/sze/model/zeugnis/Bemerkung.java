// Bemerkung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.StringUtil;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Bemerkungen zu Zeugnissen.
 *
 */
@Entity
@Table(name = "bemerkung")
public class Bemerkung extends VersionedModel implements Serializable,
        Comparable<Bemerkung> {
    @Column(nullable = false)

    private Long sortierung = Long.valueOf(10);

    @Column(name = "er_sie_statt_namen", nullable = false)
    private boolean erSieStattNamen = false;

    @Transient
    @ValidVariableText
    private String fixText;

    @Column(name = "frei_text", length = 500)
    @ValidVariableText
    private String freiText;

    // bi-directional many-to-one association to BemerkungsBaustein
    @ManyToOne(optional = false)
    @JoinColumn(name = "baustein_id", nullable = false)

    private BemerkungsBaustein baustein;

    // bi-directional many-to-one association to Zeugni
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)

    private Zeugnis zeugnis;


    public Boolean getErSieStattNamen() {
        return this.erSieStattNamen;
    }

    public void setErSieStattNamen(final Boolean erSieStattNamen) {
        this.erSieStattNamen = erSieStattNamen;
    }

    public String getFreiText() {
        return this.freiText;
    }

    public void setFreiText(final String freiText) {
        this.freiText = freiText;
    }

    public Long getSortierung() {
        return this.sortierung;
    }

    public void setSortierung(final Long sortierung) {
        this.sortierung = sortierung;
    }

    public BemerkungsBaustein getBaustein() {
        return this.baustein;
    }

    public void setBaustein(final BemerkungsBaustein bemerkungsBaustein) {
        this.baustein = bemerkungsBaustein;
        this.fixText = this.baustein.getText();
    }

    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

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
        if (StringUtil.containsInformation(freiText)) {
            return baustein.getName() + " " + freiText;
        } else {
            return baustein.getName() + " " + fixText;
        }
    }

    public String createPrintText(final Schueler schueler, final Date datum,
            final String schuljahr) {
        String text = StringUtil.containsInformation(freiText) ? freiText
                : fixText;
        text = VariableUtility.createPrintText(text, schueler.getRufname(),
                schueler.getVorname(), schueler.getName(), schueler
                .getGeschlecht(), datum, erSieStattNamen, schuljahr);

        if (!text.endsWith("\n") && !text.endsWith(" ")) {
            text += " ";
        }

        return text;
    }
}
