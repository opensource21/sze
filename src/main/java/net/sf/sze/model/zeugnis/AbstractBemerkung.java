//AbstractBemerkung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import net.sf.sze.constraints.ValidVariableText;
import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.model.stammdaten.Schueler;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.StringUtils;

/**
 *Abstrakte Basisklasse für Bemerkungen.
 *
 */
@MappedSuperclass
public abstract class AbstractBemerkung extends RevisionModel implements Serializable {

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

    /** The zeugnis. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    private Zeugnis zeugnis;

    /**
     * Initiates an object of type AbstractBemerkung.
     */
    public AbstractBemerkung() {
        super();
    }

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
     * Liefert den festen Text aus dem Baustein.
     * @return den festen Text aus dem Baustein.
     */
    public abstract String getFixText();

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
        String text;
        if (StringUtils.isNotBlank(freiText)) {
            text = freiText;
        } else {
            text = getFixText();
        }
        text = VariableUtility.createPrintText(text, schueler, datum,
                erSieStattNamen, schuljahr);

        if (!text.endsWith("\n") && !text.endsWith(" ")) {
            text += " ";
        }
        return text;
    }

}
