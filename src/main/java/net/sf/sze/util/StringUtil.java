// StringUtil.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility-Class welches einige Konvertierungen bei Strings vornimmt.
 * @author niels
 *
 */
public class StringUtil {

    /**
     * Wandelt einen String, der eine Komma oder Leerzeichen separierte Liste
     * darstellt in eine Liste um.
     * @param listAsString die Liste als String.
     * @return die Liste.
     */
    public static List<String> convertStringToList(String listAsString) {
        if (containsInformation(listAsString)) {
            return Arrays.asList(listAsString.split("[ ,]+/"));
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * /&auml;&ouml;&uuml;&Auml;&Ouml;&Uuml;&szlig;+- werden gelöscht.
     * @param text ein Text
     * @return der bereinigte Text
     */
    public static String deleteSpecialCharaters(String text) {
        return text.replaceAll("[ /äöüÄÖÜß+-]", "");
    }

    /**
     * Prüft ob ein Text nicht null und nicht nur Leerzeichen enthält.
     * @param text der zu prüfende Text.
     * @return false wenn er leer ist oder nur Leerzeichen enthält.
     */
    public static boolean containsInformation(String text) {
        return (text != null) && !text.trim().isEmpty();
    }
}
