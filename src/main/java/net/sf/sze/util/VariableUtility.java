// VariableUtility.java
//
// (c) SZE-Development-Team

package net.sf.sze.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.sze.model.stammdaten.Geschlecht;
import net.sf.sze.model.stammdaten.Schueler;

/**
 * An verschiedenen Stellen werden Textbausteine mit Variablen benötigt. Hier
 * findet man Hilfunktionalitäten.
 * @author niels
 *
 */
public final class VariableUtility {

    // NICE Test schreiben.
    // Führendes Leerzeichen wird von OO geschluckt, daher 00A0 was auch nix anzeigt.

    /** The Constant PLATZHALTER_LEER. */
    public static final String PLATZHALTER_LEER = "\u00A0/ ";

    /** The Constant PLATZHALTER_AUSGEWAEHLT. */
    public static final String PLATZHALTER_AUSGEWAEHLT = "\u00A0X ";

    /** The Constant VARIABLE_NAMES. */
    public static final String[] VARIABLE_NAMES = {
        "name", "Name", "NAME", "datum", "schuljahr", "Vorname", "Nachname"
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
            if ((i % 2 == 1) && !VARIABLE_NAMES_LIST.contains(token) && !token
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

        final String rufnameNotNull = (schueler.getRufname() != null) ? schueler
                .getRufname() : schueler.getVorname();
        final StringBuffer printText = new StringBuffer();
        boolean erSieStattNamen = erSieStattNamenRule;
        // Der Präfix muss davor, damit der erste Token nie eine Variable ist.
        final String[] tokens = ("Text" + text).split("@");
        for (int i = 0; i < tokens.length; i++) {
            final String token = tokens[i];
            String replacement;
            if (i % 2 == 1) {
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
}
