// StringUtil.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Utility-Class welches einige Konvertierungen bei Strings vornimmt.
 * @author niels
 *
 */
public final class StringUtil {

    /**
     * Initiates an object of type StringUtil.
     */
    private StringUtil() {

    }

    /**
     * Wandelt einen String, der eine Komma oder Leerzeichen separierte Liste
     * darstellt in eine Liste um.
     * @param listAsString die Liste als String.
     * @return die Liste.
     */
    public static List<String> convertStringToList(String listAsString) {
        if (StringUtils.isNotEmpty(listAsString)) {
            return Arrays.asList(listAsString.trim().split("[ ,]+"));
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
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        return text.replaceAll("[ /äöüÄÖÜß+-]", "");
    }

}
