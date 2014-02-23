// URL.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.frontend.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.SchulamtsBemerkung;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import de.ppi.fuwesta.spring.mvc.util.UrlDefinitionsToMessages.ParamFormat;

/**
 * List of all URLs.
 *
 */
// CSOFF: InterfaceIsType You must give the Annotations Strings and can't use
// Enums.
public final class URL {

    /**
     * Prefix für Redirect-Urls.
     */
    private static final String REDIRECT_PREFIX = "redirect:";

    /**
     * Map which stores the UriComponents.
     */
    private static final Map<String, UriComponents> URI_MAP =
            new ConcurrentHashMap<String, UriComponents>();

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

        /**
         * Parameter zum Übergeben der Formular-Ids.
         */
        String P_FORMULAR = "formular";


        /**
         * Url zum initialisieren der Zeugnisse.
         */
        String INIT_ZEUGNISSE = HOME + "/initZeugnisse";

    }

    /**
     * Parameter die in verschiedenen View gebraucht werden und ähnlich wie bei
     * einer Session in die URL abgelegt werden.
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
        String ACTION_NEXT = "next";
        /**
         * Action gehe zum vorherigen.
         */
        String ACTION_PREV = "prev";
        /**
         * Parameter zu letzt editierte Id.
         */
        String P_LASTEDITED_ID = "lasteditedId";
        /**
         * Parameter zum definieren der Aktion.
         */
        String P_ACTION = "action";
        /**
         * Paramter für die nächste ID.
         */
        String P_NEXT_ID = "nextId";
        /**
         * Paramter für die vorherige ID.
         */
        String P_PREV_ID = "prevId";

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

    }

    /**
     * All URLs for the creation of a Zeugnis bei dem die Basis-Informationen
     * Halbjahr und KlassenId fest im Pfad sind.
     */
    public interface ZeugnisPath {

        /**
         * Parameter für die Id der Bewertung.
         */
        @ParamFormat
        String P_BEWERTUNGS_ID = "bewertungsId";

        /**
         * Parameter für die Id der {@link Bemerkung}.
         */
        @ParamFormat
        String P_BEMERKUNGS_ID = "bemerkungsId";

        /**
         * Parameter für die Id der {@link SchulamtsBemerkung}.
         */
        @ParamFormat
        String P_SCHULAMTS_BEMERKUNGS_ID = "schulamtsBemerkungsId";

        /**
         * Zeugniserfassung base-url.
         */
        String HOME = "/zeugnis/{" + Session.P_HALBJAHR_ID + "}/{"
                + Session.P_KLASSEN_ID + "}";

        /**
         * Standard-URL um Details von dem Zeugnis zu bearbeiten.
         */
        String DETAIL = "/zeugnis/{" + Session.P_HALBJAHR_ID + "}/{"
                + Session.P_KLASSEN_ID + "}/{" + Session.P_SCHUELER_ID + "}";

        /**
         * Zeugniserfassung base-url.
         */
        String START = HOME + "/start";

        /**
         * Zeige Zeugnis.
         */
        String SHOW = HOME + "/show";

        /**
         * Breche die Bearbeitung der Bewertung ab.
         */
        String BEWERTUNG_CANCEL = DETAIL + "/cancelBewertung/{"
                + P_BEWERTUNGS_ID + "}";

        /**
         * Bearbeite die Bewertung.
         */
        String BEWERTUNG_EDIT = DETAIL + "/editBewertung/{" + P_BEWERTUNGS_ID
                + "}";

        /**
         * Erstelle die {@link Bemerkung}.
         */
        String BEMERKUNG_CREATE = DETAIL + "/createBemerkung";
        /**
         * Breche die Bearbeitung der {@link Bemerkung} ab.
         */
        String BEMERKUNG_CANCEL = DETAIL + "/cancelBemerkung";
        /**
         * Bearbeite die {@link Bemerkung}.
         */
        String BEMERKUNG_EDIT = DETAIL + "/editBemerkung/{" + P_BEMERKUNGS_ID
                + "}";

        /**
         * Zeige die {@link Bemerkung}.
         */
        String BEMERKUNG_SHOW = DETAIL + "/showBemerkung/{" + P_BEMERKUNGS_ID
                + "}";
        /**
         * Lösche die {@link Bemerkung}.
         */
        String BEMERKUNG_DELETE = DETAIL + "/deleteBemerkung/{"
                + P_BEMERKUNGS_ID + "}";

        /**
         * Erstelle die {@link SchulamtsBemerkung}.
         */
        String SCHULAMTS_BEMERKUNG_CREATE = DETAIL
                + "/createSchulamtsBemerkung";
        /**
         * Breche die Bearbeitung der {@link SchulamtsBemerkung} ab.
         */
        String SCHULAMTS_BEMERKUNG_CANCEL = DETAIL
                + "/cancelSchulamtsBemerkung";
        /**
         * Bearbeite die {@link SchulamtsBemerkung}.
         */
        String SCHULAMTS_BEMERKUNG_EDIT = DETAIL + "/editSchulamtsBemerkung/{"
                + P_SCHULAMTS_BEMERKUNGS_ID + "}";
        /**
         * Zeige die {@link SchulamtsBemerkung}.
         */
        String SCHULAMTS_BEMERKUNG_SHOW = DETAIL + "/showSchulamtsBemerkung/{"
                + P_SCHULAMTS_BEMERKUNGS_ID + "}";
        /**
         * Lösche die {@link SchulamtsBemerkung}.
         */
        String SCHULAMTS_BEMERKUNG_DELETE = DETAIL
                + "/deleteSchulamtsBemerkung/{" + P_SCHULAMTS_BEMERKUNGS_ID
                + "}";

        /**
         * Bearbeite Zeugnis-Details.
         */
        String ZEUGNIS_EDIT_DETAIL = DETAIL + "/editDetail";
        /**
         * Bearbeite Arbeitsgruppen Teilnahme.
         */
        String ZEUGNIS_EDIT_AGS = DETAIL + "/editArbeitsgruppen";
        /**
         * Bearbeite Av-Sv-Bewertung.
         */
        String ZEUGNIS_EDIT_AV_SV = DETAIL + "/editAvSv";
        /**
         * Bearbeite BU-Text und SoL.
         */
        String ZEUGNIS_EDIT_BU_SOL = DETAIL + "/editBuSoL";

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
        String HOME = "/bewertungen/{" + Session.P_HALBJAHR_ID + "}/{"
                + Session.P_KLASSEN_ID + "}";

        /**
         * Parameter mit der ID der {@link Bewertung}.
         */
        @ParamFormat
        String P_BEWERTUNGS_ID = "bewertungsId";

        /**
         * Zeige Bewertungen.
         */
        String LIST = HOME + "/list";

        /**
         * Bearbeite Bewertung.
         */
        String EDIT = HOME + "/edit/{" + Session.P_SCHULFACH_ID + "}/{"
                + P_BEWERTUNGS_ID + "}";

        /**
         * Breche die Bearbeitung der Bewertung ab.
         */
        String CANCEL = HOME + "/cancel/{" + Session.P_SCHULFACH_ID + "}/{"
                + P_BEWERTUNGS_ID + "}";

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
     * All URLS for the {@link Schulhalbjahr}.
     *
     */
    public interface Schulhalbjahr {

        /**
         * Home-Url.
         */
        String HOME = "/schulhalbjahr";

        /**
         * Parametername for the halbjahrs-id.
         */
        String P_SCHULHALBJAHR_ID = "schulhalbjahrId";

        /**
         * Edit-Url.
         */
        String EDIT = HOME + "/edit/{" + P_SCHULHALBJAHR_ID + "}";

        /**
         * Show-Url.
         */
        String SHOW = HOME + "/show/{" + P_SCHULHALBJAHR_ID + "}";

        /**
         * Delete-Url.
         */
        String DELETE = HOME + "/delete";

        /** List-URL. */
        String LIST = HOME + "/list";

        /** Create URL. */
        String CREATE = HOME + "/create";
    }

    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @param parameters the parameters
     * @return the URL with parameters filled in
     */
    @Deprecated
    public static String filledURL(String url, Object... parameters) {
        if ((parameters == null) || (parameters.length == 0)) {
            return url;
        }

        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand(parameters).encode().toUriString();
    }

    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @param parameter the parameter
     * @return the URL with parameters filled in
     */
    public static String filledURL(String url, Object parameter) {
        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand(parameter).encode().toUriString();
    }

    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @return the URL with parameters filled in
     */
    public static String filledURL(String url) {
        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand().encode().toUriString();
    }

    /**
     * Replace all parameters in the URL with the given values.
     *
     * @param url the URL.
     * @param keyValuePairs the parameters as pair of name and value.
     * @return the URL with parameters filled in
     */
    public static String filledURLWithNamedParams(String url, Object... keyValuePairs) {
        if ((keyValuePairs == null) || (keyValuePairs.length == 0)) {
            return url;
        }
        if (keyValuePairs.length % 2 != 0) {
            throw new IllegalArgumentException(
                    "The array has to be of an even size - size is "
                            + keyValuePairs.length);
        }

        final Map<String, Object> values = new HashMap<String, Object>();

        for (int x = 0; x < keyValuePairs.length; x += 2) {
            values.put((String) keyValuePairs[x], keyValuePairs[x + 1]);
        }

        final UriComponents uricomponent = getUriComponent(url);
        return uricomponent.expand(values).encode().toUriString();
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
    public static String filledURL(String url, Map<String, ?> parameters) {
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
    @Deprecated
    public static String redirect(String url, Object... parameters) {
        return REDIRECT_PREFIX + filledURL(url, parameters);
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @param parameter the parameter
     * @return the redirect URL with parameters filled in
     */
    public static String redirect(String url, Object parameter) {
        return REDIRECT_PREFIX + filledURL(url, parameter);
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @return the redirect URL with parameters filled in
     */
    public static String redirect(String url) {
        return REDIRECT_PREFIX + filledURL(url);
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @param keyValuePairs the parameters as pair of name and value
     * @return the redirect URL with parameters filled in
     */
    public static String redirectWithNamedParams(String url, Object... keyValuePairs) {
        return REDIRECT_PREFIX + filledURLWithNamedParams(url, keyValuePairs);
    }

    /**
     * Replace all parameters in the URL with the given values and make a
     * redirect.
     *
     * @param url the URL.
     * @param namedParameters the parameters
     * @return the redirect URL with parameters filled in.
     */
    public static String redirect(String url,
            Map<String, String> namedParameters) {
        return REDIRECT_PREFIX + filledURL(url, namedParameters);
    }

    /**
     * Initiates an object of type URL.
     */
    private URL() {
        // UTILITY-CONSTRUCTOR
    }

    /**
     * Erstellt die URL um zurück auf die Show-Zeugnismaske zu gehen.
     *
     * @param halbjahrId Id des Schulhalbjahres.
     * @param klassenId Id der Klasse
     * @param schuelerId Id des Schülers.
     * @return die Url
     */
    public static String createRedirectToZeugnisUrl(Long halbjahrId,
            Long klassenId, Long schuelerId) {
        return REDIRECT_PREFIX
                + createLinkToZeugnisUrl(halbjahrId, klassenId, schuelerId);
    }

    /**
     * Erstellt die URL um zurück auf die Show-Zeugnismaske zu gehen ohne
     * Redirect.
     *
     * @param halbjahrId Id des Schulhalbjahres.
     * @param klassenId Id der Klasse
     * @param schuelerId Id des Schülers.
     * @return die Url
     */
    public static String createLinkToZeugnisUrl(Long halbjahrId,
            Long klassenId, Long schuelerId) {
        return filledURL(URL.ZeugnisPath.SHOW + "?" + URL.Session.P_SCHUELER_ID
                + "=" + schuelerId, halbjahrId, klassenId);
    }
}
// CSON: InterfaceIsType
