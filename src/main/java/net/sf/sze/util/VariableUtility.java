// VariableUtility.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.util;

import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * An verschiedenen Stellen werden Textbausteine mit Variablen benötigt. Hier
 * findet man Hilfunktionalitäten.
 * @author niels
 *
 */
public final class VariableUtility {

    // NICE Test schreiben.
    // Führendes Leerzeichen wird von OO geschluckt, daher 00A0 was auch nix anzeigt.

    /** Die Konstanten für > / <. */
    public static final String PLATZHALTER_LEER = "\u00a0/ ";

    /** Die Konstante für > X <. */
    public static final String PLATZHALTER_AUSGEWAEHLT = "\u00A0X ";

    /** Die Konstanten mit den Variablen:
     * <ul>
     * <li> datum - setzt das Datum, welches beim Schulformular definiert ist.</li>
     * <li> name - ersetzt den Rufnamen oder er/sie, </li>
     * <li> Name - ersetzt den Rufnamen oder Er/Sie, </li>
     * <li> NAME - ersetzt den Rufamen, </li>
     * <li> Vorname - setzt den Vornamen, </li>
     * <li> Nachname -  den Nachnamen. </li>
     * <li> schuljahr f\u00fcgt das entsprechende Schuljahr ein. </li>
     * <li> AT - @
     * <li> &lt;M\u00e4nnlicher Text&gt;|&lt;Weiblicher Text&gt; setzt in
     *    Abh\u00e4ngigkeit des Geschlechts den einen oder anderen Text.</li>
     * </ul>. */
    public static final String[] VARIABLE_NAMES = {
        "name", "Name", "NAME", "datum", "schuljahr", "Vorname", "Nachname", "AT"
    };

    /** The Constant VARIABLE_NAMES_LIST. */
    private static final List<String> VARIABLE_NAMES_LIST = Arrays.asList(
            VARIABLE_NAMES);

    /**
     *
     * Initiates an object of type VariableUtility.
     */
    private VariableUtility() {

    }

    /**
     * Liefert die Liste alle Variablen, die nicht verarbeitet werden können.
     * @param text der Text mit den Variablen.
     * @return die Liste alle Variablen, die nicht verarbeitet werden können.
     */
    public static List<String> getInvalidVariables(final String text) {
        final List<String> unknownVariables = new ArrayList<String>();
        final String[] tokens = ("Text" + text).split("@");
        for (int i = 0; i < tokens.length; i++) {
            final String token = tokens[i];
            if ((i % 2 != 0) && !VARIABLE_NAMES_LIST.contains(token) && !token
                    .contains("|")) {
                unknownVariables.add(token);
            }
        }

        return unknownVariables;
    }

    /**
     * Erzeugt den Text für den Druck in dem die Variablen ersetzt werden.
     * @param text der Text
     * @param schueler der Schüler
     * @param datum das aktuelle Datum
     * @param erSieStattNamenRule true wenn er oder sie statt dem Namen
     * genommen werden soll.
     * @param schuljahr das Schuljahr
     * @return der Text für den Druck.
     */
    public static String createPrintText(final String text, Schueler schueler,
            final Date datum, final boolean erSieStattNamenRule,
            final String schuljahr) {
        if (!text.contains("@")) {
            return text;
        }

        final String rufnameNotNull = schueler.getRufnameOrVorname();
        final StringBuffer printText = new StringBuffer();
        boolean erSieStattNamen = erSieStattNamenRule;
        // Der Präfix muss davor, damit der erste Token nie eine Variable ist.
        final String[] tokens = ("Text" + text).split("@");
        for (int i = 0; i < tokens.length; i++) {
            final String token = tokens[i];
            String replacement;
            if (i % 2 != 0) {
                if ("NAME".equals(token)) {
                    replacement = rufnameNotNull;
                } else if ("name".equals(token)) {
                    replacement = buildName(rufnameNotNull, schueler
                            .getGeschlecht(), erSieStattNamen, true);
                    erSieStattNamen = true;
                } else if ("Name".equals(token)) {
                    replacement = buildName(rufnameNotNull, schueler
                            .getGeschlecht(), erSieStattNamen, false);
                    erSieStattNamen = true;
                } else if ("datum".equals(token)) {
                    if (datum != null) {
                        final SimpleDateFormat formatter = new SimpleDateFormat(
                                "dd.MM.yyyy");
                        replacement = formatter.format(datum);
                    } else {
                        replacement = "";
                    }
                } else if ("schuljahr".equals(token)) {
                    replacement = schuljahr;
                } else if ("Vorname".equals(token)) {
                    replacement = schueler.getVorname();
                } else if ("Nachname".equals(token)) {
                    replacement = schueler.getName();
                } else if (token.contains("|")) {
                    replacement = getGenderSpecificText(token, schueler
                            .getGeschlecht());
                } else if ("AT".equals(token)) {
                    replacement = "@";
                } else {
                    throw new IllegalStateException(token + " is not allowed");
                }

                printText.append(replacement);
            } else {
                printText.append(token);
            }

        }

        return printText.substring(4);

    }

    /**
     * Liefert den geschlechtsspezifischen Text.
     * @param token der aktuelle Token.
     * @param geschlecht das gewählte Geschlecht.
     * @return den geschlechtsspezifischen Text.
     */
    private static String getGenderSpecificText(final String token,
            final Geschlecht geschlecht) {
        final String[] texts = token.split("\\|");
        if (geschlecht == Geschlecht.WEIBLICH) {
            return texts[1];
        } else {
            return texts[0];
        }
    }

    /**
     * Erstellt eine Anrede entweder der Name oder Er/Sie.
     * @param rufname der Rufame
     * @param geschlecht das Geschlecht.
     * @param erSieStattNamen Kennzeichen ob der Name verwendet werden soll.
     * @param kleinGeschrieben Kennzeichen ob er/sie groß oder klein sein soll.
     * @return die Anrede.
     */
    private static String buildName(final String rufname,
            final Geschlecht geschlecht, final boolean erSieStattNamen,
            final boolean kleinGeschrieben) {
        if (erSieStattNamen) {
            if (geschlecht == Geschlecht.WEIBLICH) {
                return kleinGeschrieben ? "sie" : "Sie";
            } else {
                return kleinGeschrieben ? "er" : "Er";
            }
        } else {
            return rufname;
        }
    }

    /**
     * Ersetzt in dem Text vorkommene Variablen:
     * <ul>
     * <li> NAME - ersetzt den Rufamen, </li>
     * <li> Vorname - setzt den Vornamen, </li>
     * <li> Nachname -  den Nachnamen. </li>
     * </ul>
     * @param text der urspüngliche Text
     * @param schueler das Schüler-Objekt
     * @return den Text mit den Variablen.
     */
    public static String insertVariables(String text, Schueler schueler) {
        String result = text;
        if (!schueler.getRufnameOrVorname().equals(schueler.getVorname())) {
            result = StringUtils.replace(result, schueler.getVorname(), "@Vorname@");
        }
        result = StringUtils.replace(result, schueler.getName(), "@Nachname@");
        result = StringUtils.replace(result, schueler.getRufnameOrVorname(), "@NAME@");
        return result;
    }
}
