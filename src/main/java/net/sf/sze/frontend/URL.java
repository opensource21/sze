// URL.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * List of all URLs.
 *
 */
//CSOFF: InterfaceIsType You must give the Annotations Strings and can't use
//Enums.
public final class URL {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(URL.class);

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
     * All URLs for the creation of a Zeugnis.
     */
    public interface Zeugnis {

        /**
         * Zeugniserfassung base-url.
         */
        String HOME = "/zeugnis";

        /**
         * Parametername für die Schüler-id.
         */
        String P_SCHUELER_ID = "schueler_id";

        /**
         * Parameter HalbjahresId.
         */
        String P_HALBJAHR_ID = "halbjahr_id";

        /**
         * Parameter KlassenId.
         */
        String P_KLASSEN_ID = "klassen_id";

        /**
         * Zeugniserfassung base-url.
         */
        String START = HOME + "/start";

        /**
         * Zeige Zeugnis.
         */
        String SHOW = HOME + "/show";

        /**
         * Zeige Bewertungen.
         */
        String BEWERTUNGEN = HOME + "/bewertungen";

        /**
         * Zeige PDF einer ganze Klasse.
         */
        String ALL_PDFS = HOME + "/pdfklasse";

        /**
         * Zeige PDF eines Schuelers.
         */
        String ONE_PDF = HOME + "/pdfschueler/{" + P_SCHUELER_ID + "}" + "/{"
                + P_HALBJAHR_ID + "}";

    }


    /**
     * All URLs for the creation of a Zeugnis bei dem die Basis-Informationen
     * Halbjahr und KlassenId fest im Pfad sind.
     */
    public interface ZeugnisPath {

        /**
         * Parametername für die Schüler-id.
         */
        String P_SCHUELER_ID = Zeugnis.P_SCHUELER_ID;

        /**
         * Parameter HalbjahresId.
         */
        String P_HALBJAHR_ID = Zeugnis.P_HALBJAHR_ID;

        /**
         * Parameter schuelerIndex.
         */
        String P_SCHUELER_INDEX = Zeugnis.P_SCHUELER_ID;

        /**
         * Parameter KlassenId.
         */
        String P_KLASSEN_ID = Zeugnis.P_KLASSEN_ID;

        /**
         * Zeugniserfassung base-url.
         */
        String HOME = "/zeugnis/{" + P_HALBJAHR_ID + "}/{" + P_KLASSEN_ID + "}";

        /**
         * Zeugniserfassung base-url.
         */
        String START = HOME + "/start";

        /**
         * Zeige Zeugnis.
         */
        String SHOW = HOME + "/show";

        /**
         * Zeige Bewertungen.
         */
        String BEWERTUNGEN = HOME + "/bewertungen";

        /**
         * Zeige PDF einer ganze Klasse.
         */
        String ALL_PDFS = HOME + "/pdfklasse";

        /**
         * Zeige PDF eines Schuelers.
         */
        String ONE_PDF = HOME + "/pdfschueler/{" + P_SCHUELER_ID + "}" + "/{"
                + P_HALBJAHR_ID + "}";

    }


    /**
     * All URLS for the User.
     *
     */
    public interface User {

        /**
         * User-Url.
         */
        String HOME = "/user";

        /**
         * Parametername for the user-id.
         */
        String P_USERID = "userId";

        /**
         * Edit-User-Url.
         */
        String EDIT = HOME + "/edit/{" + P_USERID + "}";

        /**
         * Show-User-Url.
         */
        String SHOW = HOME + "/show/{" + P_USERID + "}";

        /**
         * Delete-User-Url.
         */
        String DELETE = HOME + "/delete/{" + P_USERID + "}";

        /** List User-URL. */
        String LIST = HOME + "/list";

        /** Create User-URL. */
        String CREATE = HOME + "/create";
    }


    /**
     * All URLS for the {@link net.sf.sze.model.Post}.
     *
     */
    public interface Post {

        /**
         * Post-Url.
         */
        String HOME = "/post";

        /**
         * Parameter for id of the post.
         */
        String P_POSTID = "postId";

        /**
         * Edit-Post-Url.
         */
        String EDIT = HOME + "/edit/{" + P_POSTID + "}";

        /**
         * Show-Post-Url.
         */
        String SHOW = HOME + "/show/{" + P_POSTID + "}";

        /**
         * Delete-Post-Url.
         */
        String DELETE = HOME + "/delete/{" + P_POSTID + "}";

        /** List Post-URL. */
        String LIST = HOME + "/list";

        /** Create Post-URL. */
        String CREATE = HOME + "/create";
    }


    /**
     * All URLS for the {@link net.sf.sze.model.Tag}.
     *
     */
    public interface Tag {

        /**
         * Tag-Url.
         */
        String HOME = "/tag";

        /**
         * Parameter for the if of the tag.
         */
        String P_TAGID = "tagId";

        /**
         * Edit-Tag-Url.
         */
        String EDIT = HOME + "/edit/{" + P_TAGID + "}";

        /**
         * Show-Tag-Url.
         */
        String SHOW = HOME + "/show/{" + P_TAGID + "}";

        /**
         * Delete-Tag-Url.
         */
        String DELETE = HOME + "/delete/{" + P_TAGID + "}";

        /** List Tag-URL. */
        String LIST = HOME + "/list";

        /** Create Tag-URL. */
        String CREATE = HOME + "/create";
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
