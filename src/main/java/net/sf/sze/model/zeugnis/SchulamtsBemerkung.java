// SchulamtsBemerkung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import net.sf.oval.constraint.Size;
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
 * Schulamtsanmerkungen.
 *
 */
@Entity
@Table(name = "schulamts_bemerkung")
public class SchulamtsBemerkung extends VersionedModel implements Serializable,
        Comparable<SchulamtsBemerkung> {
    @Column(nullable = false)

    private Long sortierung = Long.valueOf(10);

    @Column(name = "er_sie_statt_namen", nullable = false)
    private boolean erSieStattNamen = Boolean.FALSE;

    @Transient
    @Size(max = 600)
    @ValidVariableText
    private String fixText;

    @Column(name = "frei_text", length = 600)
    @ValidVariableText
    private String freiText;

    // bi-directional many-to-one association to Schulamt
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamt_id", nullable = false)

    private Schulamt schulamt;

    // bi-directional many-to-one association to SchulamtsBemerkungsBaustein
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulamts_baustein_id", nullable = false)

    private SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein;

    // bi-directional many-to-one association to Zeugnis
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

    public Schulamt getSchulamt() {
        return this.schulamt;
    }

    public void setSchulamt(final Schulamt schulamt) {
        this.schulamt = schulamt;
    }

    public SchulamtsBemerkungsBaustein getSchulamtsBemerkungsBaustein() {
        return this.schulamtsBemerkungsBaustein;
    }

    public void setSchulamtsBemerkungsBaustein(
            final SchulamtsBemerkungsBaustein schulamtsBemerkungsBaustein) {
        this.schulamtsBemerkungsBaustein = schulamtsBemerkungsBaustein;
        setFixText();
    }

    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

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

    public String getFixText() {
        return fixText;
    }


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
