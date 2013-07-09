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

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.zeugnis.AvSvNote;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Bewertung zum Arbeits- und Sozialverhalten.
 *
 */
@Entity
@Table(name = "av_sv_bewertung")
public class AvSvBewertung extends VersionedModel implements Serializable,
        Comparable<AvSvBewertung> {

    @Enumerated(EnumType.ORDINAL)
    private AvSvNote beurteilung;

    // bi-directional many-to-one association to ArbeitsUndSozialVerhalten
    @ManyToOne(optional=false)
    @JoinColumn(name = "arbeits_und_sozial_verhalten_id", nullable = false)
    
    private ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten;

    // bi-directional many-to-one association to Zeugnis
    @ManyToOne(optional=false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    
    private Zeugnis zeugnis;

    public AvSvBewertung() {
    }

    public AvSvNote getBeurteilung() {
        return this.beurteilung;
    }

    public void setBeurteilung(final AvSvNote beurteilung) {
        this.beurteilung = beurteilung;
    }

    public ArbeitsUndSozialVerhalten getArbeitsUndSozialVerhalten() {
        return this.arbeitsUndSozialVerhalten;
    }

    public void setArbeitsUndSozialVerhalten(
            final ArbeitsUndSozialVerhalten arbeitsUndSozialVerhalten) {
        this.arbeitsUndSozialVerhalten = arbeitsUndSozialVerhalten;
    }

    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

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
