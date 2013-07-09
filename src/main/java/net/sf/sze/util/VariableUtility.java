// VariableUtility.java
//
// (c) SZE-Development-Team

package net.sf.sze.util;

import net.sf.sze.stammdaten.Geschlecht;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * An verschiedenen Stellen werden Textbausteine mit Variablen benötigt. Hier
 * findet man Hilfunktionalitäten.
 * @author niels
 *
 */
public class VariableUtility {
    // TODO Test schreiben.
    // Führendes Leerzeichen wird von OO geschluckt, daher 00A0 was auch nix anzeigt.
    public static final String PLATZHALTER_LEER = "\u00A0/ ";
    public static final String PLATZHALTER_AUSGEWAEHLT = "\u00A0X ";

    public static final String[] VARIABLE_NAMES = {
        "name", "Name", "NAME", "datum", "schuljahr", "Vorname", "Nachname"
    };

    private static final List<String> VARIABLE_NAMES_LIST = Arrays.asList(
            VARIABLE_NAMES);

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

    public static String createPrintText(final String text,
            final String rufname, final String vorname, final String nachname,
            final Geschlecht geschlecht, final Date datum,
            final boolean erSieStattNamenRule, final String schuljahr) {
        if (!text.contains("@")) {
            return text;
        }

        final String rufnameNotNull = (rufname != null) ? rufname : vorname;
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
                    replacement = buildName(rufnameNotNull, geschlecht,
                            erSieStattNamen, true);
                    erSieStattNamen = true;
                } else if ("Name".equals(token)) {
                    replacement = buildName(rufnameNotNull, geschlecht,
                            erSieStattNamen, false);
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
                    replacement = vorname;
                } else if ("Nachname".equals(token)) {
                    replacement = nachname;
                } else if (token.contains("|")) {
                    replacement = getGenderSpecificText(token, geschlecht);
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

    private static String getGenderSpecificText(final String token,
            final Geschlecht geschlecht) {
        final String[] texts = token.split("\\|");
        if (geschlecht == Geschlecht.WEIBLICH) {
            return texts[1];
        } else {
            return texts[0];
        }
    }

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
