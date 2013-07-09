// Bewertung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.sze.model.base.VersionedModel;
import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * Eine Bewertung ist die Beurteilung zu einem Fach.
 *
 */
@Entity
@Table(name = "bewertung",
        uniqueConstraints = @UniqueConstraint(columnNames = {"schulfach_id",
        "zeugnis_id"}, name = "UK_BEWERTUNG_SCHULFACH_ZEUGNIS"))
@Inheritance
@DiscriminatorColumn(name = "class",
        discriminatorType = DiscriminatorType.STRING, length = 255)
@DiscriminatorValue("net.sf.sze.zeugnis.Bewertung")
public class Bewertung extends VersionedModel implements Serializable,
        Comparable<Bewertung> {
    // TODO Validatoren bauen
//  sonderNote(validator:{value, object ->
//      if (["1","2","3","4","5","6"].contains(value.trim())) {
//          return ['bewertung.sonderNote.invalid',value]
//      }
//  })
//  leistungNurSchwachAusreichend(validator:{value, object ->
//      if (value && (object.note != 4)) {
//          return ['bewertung.leistungNurSchwachAusreichend.invalid']
//      }
//  })
//  relevant(validator:{value, object ->
//      if (!value && (object.sonderNote || object.note)) {
//          return ['bewertung.relevant.invalid']
//      }
//  })

    // TODO einschränken auf den Bereich 1-6 in GUI, kein DB-Constraint, da ABI 1-15
    private Long note;

    @Column(length = 255)
    
    private String leistungsniveau;

    @Column(name = "sonder_note", nullable = false, length = 255)
    
    
    private String sonderNote;

    @Column(nullable = false)
    
    private Boolean relevant = true;

    @Column(name = "leistung_nur_schwach_ausreichend", nullable = false)
    
    private Boolean leistungNurSchwachAusreichend = Boolean.FALSE;

    // bi-directional many-to-one association to Schulfach
    @ManyToOne(optional=false)
    @JoinColumn(name = "schulfach_id", nullable = false)
    
    private Schulfach schulfach;

    // bi-directional many-to-one association to Zeugnis
    @ManyToOne(optional=false)
    @JoinColumn(name = "zeugnis_id", nullable = false)
    
    private Zeugnis zeugnis;



    public Boolean getLeistungNurSchwachAusreichend() {
        return this.leistungNurSchwachAusreichend;
    }

    public void setLeistungNurSchwachAusreichend(
            final Boolean leistungNurSchwachAusreichend) {
        this.leistungNurSchwachAusreichend = leistungNurSchwachAusreichend;
    }

    public String getLeistungsniveau() {
        return this.leistungsniveau;
    }

    public void setLeistungsniveau(final String leistungsniveau) {
        this.leistungsniveau = leistungsniveau;
    }

    public Long getNote() {
        return this.note;
    }

    public void setNote(final Long note) {
        this.note = note;
    }

    public Boolean getRelevant() {
        return this.relevant;
    }

    public void setRelevant(final Boolean relevant) {
        this.relevant = relevant;
    }

    public String getSonderNote() {
        return this.sonderNote;
    }

    public void setSonderNote(final String sonderNote) {
        this.sonderNote = sonderNote;
    }

    public Schulfach getSchulfach() {
        return this.schulfach;
    }

    public void setSchulfach(final Schulfach schulfach) {
        this.schulfach = schulfach;
    }

    public Zeugnis getZeugnis() {
        return this.zeugnis;
    }

    public void setZeugnis(final Zeugnis zeugni) {
        this.zeugnis = zeugni;
    }

    // *******************************************************
    @Override
    public int compareTo(final Bewertung other) {
        final CompareToBuilder compareBuilder = new CompareToBuilder();
        compareBuilder.append(this.schulfach, other.schulfach);
        compareBuilder.append(this.zeugnis, other.zeugnis);
        compareBuilder.append(this.note, other.note);
        compareBuilder.append(this.leistungsniveau, other.leistungsniveau);
        compareBuilder.append(this.sonderNote, other.sonderNote);
        compareBuilder.append(this.relevant, other.relevant);
        compareBuilder.append(this.leistungNurSchwachAusreichend, other
                .leistungNurSchwachAusreichend);
        return compareBuilder.toComparison();

    }

    public String notenDarstellung() {
        if (StringUtil.containsInformation(sonderNote)) {
            return sonderNote;
        }

        if (!relevant) {
            return "";
        }

        final StringBuilder notenDarstellung = new StringBuilder();
        if (leistungsniveau != null) {
            notenDarstellung.append(leistungsniveau).append(" ");
        }

        if (note != null) {
            notenDarstellung.append('?');
        } else {
            notenDarstellung.append(note);
        }

        return notenDarstellung.toString();
    }



    @Override
    public String toString() {
        return schulfach + ": " + notenDarstellung();
    }

    // TODO toPrintMap elegant implementieren, evtl BeanWrapper?.
//  String toPrintMap(final Map<String, String> printMap, final boolean noteAlsTextDarstellen) {
//        textMap = [1L:'sehr gut',
//                         2L:'gut',
//                         3L:'befriedigend',
//                         4L:'ausreichend',
//                         5L:'mangelhaft',
//                         6L:'ungen\ufffdgend']

//      String result
//      if (!relevant) {
//          result = VariableUtility.PLATZHALTER_LEER
//      } else if (sonderNote) {
//          result = sonderNote
//      } else {
//          String noteAlsText
//          if (noteAlsTextDarstellen) {
//              noteAlsText = note?textMap[note]:'?'
//          } else {
//              noteAlsText = note?note.toString():'?'
//          }
//          if (leistungsniveau) {
//              result = "${leistungsniveau}   ${noteAlsText}"
//          } else {
//              result = noteAlsText
//          }
//      }
//      printMap["bw_${schulfach.technicalName()}"] = result
//      printMap["bw_${schulfach.technicalName()}_tg"] = relevant?VariableUtility.PLATZHALTER_AUSGEWAEHLT:VariableUtility.PLATZHALTER_LEER
//      return result
//  }
}
