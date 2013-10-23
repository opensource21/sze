// URL.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import de.ppi.fuwesta.spring.mvc.util.UrlDefinitionsToMessages.ParamFormat;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * List of all URLs.
 *
 */
//CSOFF: InterfaceIsType You must give the Annotations Strings and can't use
//Enums.
public final class URL {

    /**
     * Map which stores the UriComponents.
     */
    private static final Map<String, UriComponents> URI_MAP =
            new ConcurrentHashMap<String, UriComponents>();

    /**
     * Home-Url.
     */
    public static final String HOME = "/home";

    /**
     * Eine Url. für Todos.
     */
    public static final String TODO = "/todo";

    /**
     * All URLs for the configuration of a zeugnis.
     */
    public interface Configuration {

        /**
         * Zeugnisconfiguration base-url.
         */
        String HOME = "/zeugnisKonfiguration";

        /**
         * Zeugnisconfiguration start-url.
         */
        String MAIN = HOME + "/";

    }

    /**
     * Parameter die in verschiedenen View gebraucht werden
     * und ähnlich wie bei einer Session in die URL abgelegt werden.
     *
     */
    public interface Session {

        /**
         * Parameter HalbjahresId.
         */
        @ParamFormat
        String P_HALBJAHR_ID = "halbjahr_id";

        /**
         * Parameter KlassenId.
         */
        @ParamFormat
        String P_KLASSEN_ID = "klassen_id";

        /**
         * Parametername für die Schüler-id.
         */
        @ParamFormat
        String P_SCHUELER_ID = "schueler_id";

        /**
         * Parametername für die Id des Schulfachs.
         */
        @ParamFormat
        String P_SCHULFACH_ID = "schulfach_id";

    }

    /**
     * Einige allgemein gültige Parameter und Konstanten.
     */
    public interface Common {

        /**
         * Aktion gehe zum Nächsten.
         */
        static final String ACTION_NEXT = "next";
        /**
         * Action gehe zum vorherigen.
         */
        static final String ACTION_PREV = "prev";
        /**
         * Parameter zu letzt editierte Id.
         */
        static final String P_LASTEDITED_ID = "lasteditedId";
        /**
         * Parameter zum definieren der Aktion.
         */
        static final String P_ACTION = "action";
        /**
         * Paramter für die nächste ID.
         */
        static final String P_NEXT_ID = "nextId";
        /**
         * Paramter für die vorherige ID.
         */
        static final String P_PREV_ID = "prevId";

    }

    /**
     * All URLs for the creation of a Zeugnis.
     */
    public interface Zeugnis {

        /**
         * Zeugniserfassung base-url.
         */
        String HOME = "/zeugnis";


        /**
         * Zeugniserfassung base-url.
         */
        String START = HOME + "/start";

        /**
         * Zeige Zeugnis.
         */
        String SHOW = HOME + "/show";


        /**
         * Zeige PDF einer ganze Klasse.
         */
        String ALL_PDFS = HOME + "/pdfklasse";

        /**
         * Zeige PDF eines Schuelers.
         */
        String ONE_PDF = HOME + "/pdfschueler/{" + Session.P_SCHUELER_ID + "}" + "/{"
                + Session.P_HALBJAHR_ID + "}";

    }


    /**
     * All URLs for the creation of a Zeugnis bei dem die Basis-Informationen
     * Halbjahr und KlassenId fest im Pfad sind.
     */
    public interface ZeugnisPath {

        @ParamFormat
        String P_BEWERTUNGS_ID = "bewertungsId";

        @ParamFormat
        String P_BEMERKUNGS_ID = "bemerkungsId";


        /**
         * Zeugniserfassung base-url.
         */
        String HOME = "/zeugnis/{" + Session.P_HALBJAHR_ID + "}/{" + Session.P_KLASSEN_ID + "}";

        /**
         * Standard-URL um Details von dem Zeugnis zu bearbeiten.
         */
        String DETAIL = "/zeugnis/{" + Session.P_HALBJAHR_ID + "}/{" + Session.P_KLASSEN_ID + "}/{" + Session.P_SCHUELER_ID + "}";

        /**
         * Zeugniserfassung base-url.
         */
        String START = HOME + "/start";

        /**
         * Zeige Zeugnis.
         */
        String SHOW = HOME + "/show";

        String BEWERTUNG_CANCEL = DETAIL + "/cancelBewertung/{"+ P_BEWERTUNGS_ID+ "}";

        String BEWERTUNG_EDIT = DETAIL + "/editBewertung/{"+ P_BEWERTUNGS_ID+ "}";

        String BEMERKUNG_CREATE = DETAIL + "/createBemerkung";
        String BEMERKUNG_EDIT = DETAIL + "/editBemerkung/{" + P_BEMERKUNGS_ID + "}";
        String BEMERKUNG_CANCEL = DETAIL + "/cancelBemerkung";
        String BEMERKUNG_DELETE = DETAIL + "/deleteBemerkung/{" + P_BEMERKUNGS_ID + "}";

        /**
         * Zeige PDF einer ganze Klasse.
         */
        String ALL_PDFS = HOME + "/pdfklasse";

        /**
         * Zeige PDF eines Schuelers.
         */
        String ONE_PDF = DETAIL + "/pdfSchueler";



    }


    /**
     * Alle URLs für die Bewertungserfassung.
     */
    public interface Bewertungen {

        /**
         * Bewertungen base-url.
         */
        String HOME = "/bewertungen";

        /**
         * Zeige Bewertungen.
         */
        String LIST = HOME + "/list";


    }

    /**
     * Alle URLs zur Anzeige der Bewertungen bei dem die Basis-Informationen
     * Halbjahr und KlassenId fest im Pfad sind.
     */
    public interface BewertungenPath {

        /**
         * Bewertungserfassung base-url.
         */
        String HOME = "/bewertungen/{" + Session.P_HALBJAHR_ID + "}/{" +
                Session.P_KLASSEN_ID  + "}";

        @ParamFormat
        String P_BEWERTUNGS_ID = "bewertungsId";

        /**
         * Zeige Bewertungen.
         */
        String LIST = HOME + "/list";

        String EDIT = HOME + "/edit/{" +Session.P_SCHULFACH_ID  + "}/{"+ P_BEWERTUNGS_ID+ "}";

        String CANCEL = HOME + "/cancel/{" + Session.P_SCHULFACH_ID + "}/{"+ P_BEWERTUNGS_ID+ "}";


    }

    /**
     * All URLS for the Bemerkung.
     *
     */
    public interface Bemerkung {

        /**
         * Bemerkung-Url.
         */
        String HOME = "/bemerkung";

        /**
         * Parametername for the bemerkung-id.
         */
        String P_BEMERKUNGID = "bemerkungId";

        /**
         * Edit-User-Url.
         */
        String EDIT = HOME + "/edit/{" + P_BEMERKUNGID + "}";

        /**
         * Show-User-Url.
         */
        String SHOW = HOME + "/show/{" + P_BEMERKUNGID + "}";

        /**
         * Delete-User-Url.
         */
        String DELETE = HOME + "/delete/{" + P_BEMERKUNGID + "}";

        /** List User-URL. */
        String LIST = HOME + "/list";

        /** Create User-URL. */
        String CREATE = HOME + "/create";
    }







    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @param parameters the parameters
     * @return the URL with parameters filled in
     */
    public static String filledURL(String url, Object... parameters) {
        if ((parameters == null) || (parameters.length == 0)) {
            return url;
        }

        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand(parameters).encode().toUriString();
    }

    /**
     * Give a {@link UriComponents} to the URL.
     *
     * @param url a url as String
     * @return the {@link UriComponents}
     */
    private static UriComponents getUriComponent(String url) {
        if (!URI_MAP.containsKey(url)) {
            URI_MAP.put(url, UriComponentsBuilder.fromUriString(url).build());
        }

        return URI_MAP.get(url);
    }

    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @param parameters the parameters
     * @return the URL with parameters filled in
     */
    public static String filledURL(String url, Map<String, String> parameters) {
        if ((parameters == null) || (parameters.size() == 0)) {
            return url;
        }

        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand(parameters).encode().toUriString();
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @param parameters the parameters
     * @return the redirect URL with parameters filled in
     */
    public static String redirect(String url, Object... parameters) {
        return "redirect:" + filledURL(url, parameters);
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @param namedParameters the parameters
     * @return the redirect URL with parameters filled in.
     */
    public static String redirect(String url, Map<String,
            String> namedParameters) {
        return "redirect:" + filledURL(url, namedParameters);
    }

    /**
     * Initiates an object of type URL.
     */
    private URL() {
        // UTILITY-CONSTRUCTOR
    }
}
//CSON: InterfaceIsType
