// Bewertung.java
//
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import de.ppi.jpa.helper.VersionedModel;

import net.sf.sze.util.StringUtil;

import org.apache.commons.lang.builder.CompareToBuilder;

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

    /** The note. */
    private Long note;

    /** The leistungsniveau. */
    @Column(length = 255)

    private String leistungsniveau;

    /** The sonder note. */
    @Column(name = "sonder_note", nullable = false, length = 255)

    private String sonderNote;

    /** The relevant. */
    @Column(nullable = false)
    private boolean relevant = true;

    /** The leistung nur schwach ausreichend. */
    @Column(name = "leistung_nur_schwach_ausreichend", nullable = false)

    private Boolean leistungNurSchwachAusreichend = Boolean.FALSE;

    // bi-directional many-to-one association to Schulfach

    /** The schulfach. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "schulfach_id", nullable = false)

    private Schulfach schulfach;

    // bi-directional many-to-one association to Zeugnis

    /** The zeugnis. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "zeugnis_id", nullable = false)

    private Zeugnis zeugnis;

    /**
     * Gets the leistung nur schwach ausreichend.
     *
     * @return the leistung nur schwach ausreichend
     */
    public Boolean getLeistungNurSchwachAusreichend() {
        return this.leistungNurSchwachAusreichend;
    }

    /**
     * Sets the leistung nur schwach ausreichend.
     *
     * @param leistungNurSchwachAusreichend the new leistung nur schwach
     *            ausreichend
     */
    public void setLeistungNurSchwachAusreichend(
            final Boolean leistungNurSchwachAusreichend) {
        this.leistungNurSchwachAusreichend = leistungNurSchwachAusreichend;
    }

    /**
     * Gets the leistungsniveau.
     *
     * @return the leistungsniveau
     */
    public String getLeistungsniveau() {
        return this.leistungsniveau;
    }

    /**
     * Sets the leistungsniveau.
     *
     * @param leistungsniveau the new leistungsniveau
     */
    public void setLeistungsniveau(final String leistungsniveau) {
        this.leistungsniveau = leistungsniveau;
    }

    /**
     * Gets the note.
     *
     * @return the note
     */
    public Long getNote() {
        return this.note;
    }

    /**
     * Sets the note.
     *
     * @param note the new note
     */
    public void setNote(final Long note) {
        this.note = note;
    }

    /**
     * Gets the relevant.
     *
     * @return the relevant
     */
    public boolean getRelevant() {
        return this.relevant;
    }

    /**
     * Sets the relevant.
     *
     * @param relevant the new relevant
     */
    public void setRelevant(final boolean relevant) {
        this.relevant = relevant;
    }

    /**
     * Gets the sonder note.
     *
     * @return the sonder note
     */
    public String getSonderNote() {
        return this.sonderNote;
    }

    /**
     * Sets the sonder note.
     *
     * @param sonderNote the new sonder note
     */
    public void setSonderNote(final String sonderNote) {
        this.sonderNote = sonderNote;
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

        if (leistungNurSchwachAusreichend.booleanValue()) {
            notenDarstellung.append(" -");
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
//      printMap["bw_${schulfach.technicalName()}_tg"] =
    // relevant?VariableUtility.PLATZHALTER_AUSGEWAEHLT:VariableUtility.PLATZHALTER_LEER
//      return result
//  }
}
