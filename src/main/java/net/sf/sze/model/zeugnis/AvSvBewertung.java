// AvSvBewertung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.ppi.jpa.helper.VersionedModel;

/**
 * Bewertung zum Arbeits- und Sozialverhalten.
 *
 */
@Entity
@Table(name = "av_sv_bewertung")
public class AvSvBewertung extends VersionedModel implements Serializable,
        Comparable<AvSvBewertung> {

    /** The beurteilung. */
    @Enumerated(EnumType.ORDINAL)
    private AvSvNote beurteilung;

    // bi-directional many-to-one association to ArbeitsUndSozialVerhalten

    /** The arbeits und sozial verhalten. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "arbeits_und_sozial_verhalten_id", nullable = false)

    private ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten;

    // bi-directional many-to-one association to Zeugnis

    /** The zeugnis. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)

    private Zeugnis zeugnis;

    public AvSvBewertung() {
    }

    /**
     * Gets the beurteilung.
     *
     * @return the beurteilung
     */
    public AvSvNote getBeurteilung() {
        return this.beurteilung;
    }

    /**
     * Sets the beurteilung.
     *
     * @param beurteilung the new beurteilung
     */
    public void setBeurteilung(final AvSvNote beurteilung) {
        this.beurteilung = beurteilung;
    }

    /**
     * Gets the arbeits und sozial verhalten.
     *
     * @return the arbeits und sozial verhalten
     */
    public ArbeitsUndSozialVerhalten getArbeitsUndSozialVerhalten() {
        return this.arbeitsUndSozialVerhalten;
    }

    /**
     * Sets the arbeits und sozial verhalten.
     *
     * @param arbeitsUndSozialVerhalten the new arbeits und sozial verhalten
     */
    public void setArbeitsUndSozialVerhalten(
            final ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten) {
        this.arbeitsUndSozialVerhalten = arbeitsUndSozialVerhalten;
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

    // ******************************************************
    @Override
    public int compareTo(final AvSvBewertung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.arbeitsUndSozialVerhalten, other
                .arbeitsUndSozialVerhalten);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.beurteilung, other.beurteilung);
        return compareBuilder.toComparison();
    }

    @Override
    public String toString() {
        if (beurteilung == null) {
            return arbeitsUndSozialVerhalten.getName() + beurteilung;
        } else {
            return arbeitsUndSozialVerhalten.getName() + " ?";
        }

    }

    // TODO toPrintMap elegant implementieren, evtl BeanWrapper?.
//  def toPrintMap(final Map<String, String> printMap) {
//      AvSvNote.values().each {note ->
//          def result=""
//          if (beurteilung == note) {
//             result=VariableUtility.PLATZHALTER_AUSGEWAEHLT
//          }
//          printMap["avsvbw_${arbeitsUndSozialVerhalten.technicalName()}_${note.id}"] = result
//      }
//
//  }
}
