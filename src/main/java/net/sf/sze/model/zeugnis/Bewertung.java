// Bewertung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.sze.model.base.RevisionModel;
import net.sf.sze.model.zeugnisconfig.Schulfach;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.StringUtils;
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
public abstract class Bewertung extends RevisionModel implements Serializable,
        Comparable<Bewertung> {

    private static final Map<Long, String> TEXT_MAP = new HashMap<Long,
            String>();

    private static final List<Long> NOTEN;


    static {
        TEXT_MAP.put(Long.valueOf(1), "sehr gut");
        TEXT_MAP.put(Long.valueOf(2), "gut");
        TEXT_MAP.put(Long.valueOf(3), "befriedigend");
        TEXT_MAP.put(Long.valueOf(4), "ausreichend");
        TEXT_MAP.put(Long.valueOf(5), "mangelhaft");
        TEXT_MAP.put(Long.valueOf(6), "ungen\ufffdgend");
        NOTEN = new ArrayList<Long>(TEXT_MAP.keySet());
    }

    /** The note. */
    private Long note;

    /** The leistungsniveau. */
    @Column(length = 255)
    private String leistungsniveau;

    /** Die Sondernote. */
    @Column(name = "sonder_note", nullable = false, length = 255)
    @CheckWith(value = SondernoteCheck.class,
            message = "validation.bewertung.sonderNote.invalid")
    private String sonderNote = "";

    /** The relevant. */
    @Column(nullable = false)
    @CheckWith(value = RelevantCheck.class,
            message = "validation.bewertung.relevant.invalid")
    private boolean relevant = true;

    /** The leistung nur schwach ausreichend. */
    @Column(name = "leistung_nur_schwach_ausreichend", nullable = false)
    @CheckWith(
            message = "validation.bewertung.leistungNurSchwachAusreichend.invalid",
            value = SchwachausreichendCheck.class)
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
     * Die Bewertung im letzten Zeugnis.
     */
    @OneToOne(optional = true)
    private Bewertung previousBewertung;

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

    /**
     * Liefert eine String Repräsentation für die Note zu Darstellung in der GUI.
     * @return eine String Repräsentation für die Note zu Darstellung in der GUI.
     */
    public String notenDarstellung() {
        if (StringUtils.isNotBlank(sonderNote)) {
            return sonderNote;
        }

        if (!relevant) {
            return "";
        }

        final StringBuilder notenDarstellung = new StringBuilder();
        if (leistungsniveau != null) {
            notenDarstellung.append(leistungsniveau).append(" ");
        }

        if (note == null) {
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

    /**
     * Schreibt die Daten in die Printmap.
     * @param printMap die Printmap.
     * @param noteAlsTextDarstellen Kennzeichen ob die Note texttuell dargestellt werden soll.
     */
    public void toPrintMap(final Map<String, Object> printMap,
            final boolean noteAlsTextDarstellen) {
        final String result = createPrintText(noteAlsTextDarstellen);
        printMap.put("bw_" + schulfach.getFormularKeyName(), result);
        printMap.put("bw_" + schulfach.getFormularKeyName() + "_tg", relevant
                ? VariableUtility.PLATZHALTER_AUSGEWAEHLT : VariableUtility
                .PLATZHALTER_LEER);

    }

    /**
     * Stellt die Note in Textform für den Druck.
     * @param noteAlsTextDarstellen Kennzeichen ob die Note texttuell dargestellt werden soll.
     * @return die Note in Textform für den Druck.
     */
    public String createPrintText(final boolean noteAlsTextDarstellen) {
        String result;
        if (!relevant) {
            result = VariableUtility.PLATZHALTER_LEER;
        } else if (StringUtils.isNotBlank(sonderNote)) {
            result = sonderNote;
        } else {
            String noteAlsText;
            if (noteAlsTextDarstellen) {
                noteAlsText = (note != null) ? TEXT_MAP.get(note) : "?";
            } else {
                noteAlsText = (note != null) ? note.toString() : "?";
            }

            if (leistungsniveau != null) {
                result = leistungsniveau + "   " + noteAlsText;
            } else {
                result = noteAlsText;
            }
        }

        return result;
    }

    /**
     * Prüft ob die {@link Bewertung#sonderNote} nicht eine "normale" Note ist.
     *
     */
    private static class SondernoteCheck implements CheckWithCheck.SimpleCheck {

        private static final String[] STANDARDNOTEN = {
            "1", "2", "3", "4", "5", "6"
        };

        @Override
        public boolean isSatisfied(Object validatedObject, Object sonderNote) {
            for (String stdNote : STANDARDNOTEN) {
                if (stdNote.equals(sonderNote)) {
                    return false;
                }
            }

            return true;
        }
    }


    /**
     * Prüft ob die {@link Bewertung#leistungNurSchwachAusreichend} zur Note passt.
     *
     */
    private static class SchwachausreichendCheck implements CheckWithCheck
            .SimpleCheck {

        private static final Long NOTE_VIER = Long.valueOf(4);

        @Override
        public boolean isSatisfied(Object validatedObject,
                Object leistungsNurSchwachAusreichend) {
            if ((leistungsNurSchwachAusreichend != null)
                    && ((Boolean) leistungsNurSchwachAusreichend)
                    .booleanValue() && !NOTE_VIER.equals(
                    ((Bewertung) validatedObject).note)) {
                return false;
            }

            return true;
        }
    }


    /**
     * Prüft ob die {@link Bewertung#relevant} false ist, wenn eine Note
     * gesetzt ist.
     *
     */
    private static class RelevantCheck implements CheckWithCheck.SimpleCheck {

        @Override
        public boolean isSatisfied(Object validatedObject, Object relevant) {
            if (!((relevant != null) && ((Boolean) relevant).booleanValue())
                    && (StringUtils.isNotBlank(((Bewertung) validatedObject).sonderNote)
                    || (((Bewertung) validatedObject).note != null))) {
                return false;
            }

            return true;
        }

    }

    /**
     * Liefert die Möglichen Ausprägungen der Leistungsniveaus.
     * @return die Möglichen Ausprägungen der Leistungsniveaus.
     */
    public abstract List<String> getLeistungsNiveaus();

    /**
     * Liefert die Möglichen Ausprägungen der Noten.
     * @return die Möglichen Ausprägungen der Noten.
     */
    public List<Long> getNoten() {
        return NOTEN;
    }

    /**
     * @return the previousBewertung
     */
    public Bewertung getPreviousBewertung() {
        return previousBewertung;
    }

    /**
     * @param previousBewertung the previousBewertung to set
     */
    public void setPreviousBewertung(Bewertung previousBewertung) {
        this.previousBewertung = previousBewertung;
    }
}
