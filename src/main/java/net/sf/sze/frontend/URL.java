// URL.java
//
// (c) SZE-Development-Team

package net.sf.sze.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

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
     * TODO-Url.
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
        String P_SCHUELER_ID = "schuelerId";

        /**
         * Parameter HalbjahresId.
         */
        String P_HALBJAHR_ID = "halbjahrId";

        /**
         * Parameter schuelerIndex.
         */
        String P_SCHUELER_INDEX = "schuelerIndex";

        /**
         * Parameter KlassenId.
         */
        String P_KLASSEN_ID = "klassenId";

        String PG_CHOOSE_CLASS = P_KLASSEN_ID + "," + P_HALBJAHR_ID;

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
     * Add all URL constants to a {@link Properties}.
     *
     * @return a {@link Properties} object with all constant information.
     */
    public static Properties urlsAsMessages() {
        final Properties props = new Properties();
        final Class<?>[] innerClasses = URL.class.getDeclaredClasses();
        addConstantInfosFromClass("url", innerClasses, props);
        return props;
    }

    /**
     * Add all Params constants to a {@link Properties}.
     *
     * @return a {@link Properties} object with all constant information.
     */
    public static Properties paramsAsMessages() {
        final Properties props = new Properties();
        final Class<?>[] innerClasses = URL.class.getDeclaredClasses();
        addConstantInfosFromClass("par", innerClasses, props);
        return props;
    }

    /**
     * Add all Params constants to a {@link Properties}.
     *
     * @return a {@link Properties} object with all constant information.
     */
    public static Properties paramGroupAsMessages() {
        final Properties props = new Properties();
        final Class<?>[] innerClasses = URL.class.getDeclaredClasses();
        addConstantInfosFromClass("pg", innerClasses, props);
        return props;
    }

    /**
     * Add all class Constants to a {@link Properties}.
     *
     * @param prefix the prefix for the key.
     * @param classes the list of classes.
     * @param props the properties object.
     */
    private static void addConstantInfosFromClass(String prefix,
            Class<?>[] classes, final Properties props) {
        for (Class<?> class1 : classes) {
            if (Modifier.isInterface(class1.getModifiers())) {
                Field[] fields = class1.getDeclaredFields();
                for (Field field : fields) {
                    if (Modifier.isPublic(field.getModifiers())) {
                        try {

                            if (prefix.startsWith("url")) {
                                if (!field.getName().startsWith("P_") && !field
                                        .getName().startsWith("PG_")) {
                                    final String keyName = createKey(prefix,
                                            class1.getSimpleName(), field
                                            .getName());
                                    final String urlValue = createUrl(field.get(
                                            null).toString());
                                    props.put(keyName, urlValue);
                                }
                            } else if (prefix.startsWith("par")) {
                                if (field.getName().startsWith("P_")) {
                                    final String keyName = createKey(prefix,
                                            class1.getSimpleName(), field
                                            .getName().substring(2));
                                    props.put(keyName, field.get(null)
                                            .toString());
                                }
                            } else if (prefix.startsWith("pg")) {
                                if (field.getName().startsWith("PG_")) {
                                    final String keyName = createKey(prefix,
                                            class1.getSimpleName(), field
                                            .getName().substring(3));
                                    final String pgValue = createParamGroup(
                                            field.get(null).toString());
                                    props.put(keyName, pgValue);
                                }

                            } else {
                                throw new IllegalArgumentException(
                                        "Invalid Prefix " + prefix);
                            }
                        } catch (IllegalArgumentException e) {
                            LOG.error("Error reading the field " + field
                                    .getDeclaringClass() + "." + field
                                    .getName(), e);
                        } catch (IllegalAccessException e) {
                            LOG.error("Error reading the field " + field
                                    .getDeclaringClass() + "." + field
                                    .getName(), e);
                        }
                    }
                }

                addConstantInfosFromClass(prefix + "." + class1
                        .getSimpleName(), class1.getDeclaredClasses(), props);

            }
        }
    }

    /**
     * Creates the URL from the constant as a message, i.e. named parameters
     * like {user_id} will be replaced by {0}.
     *
     * @param fieldValue the field value.
     * @return the URL as parameterized message.
     */
    private static String createUrl(String fieldValue) {
        final StringBuilder result = new StringBuilder(fieldValue.length());
        final StringTokenizer tokens = new StringTokenizer(fieldValue, "{}");
        boolean isVariable = (fieldValue.charAt(0) == '{');
        int i = 0;
        while (tokens.hasMoreTokens()) {
            final String key = tokens.nextToken();
            if (isVariable) {
                result.append('{').append(i).append('}');
                i++;
            } else {
                result.append(key);
            }

            isVariable = !isVariable;
        }

        return result.toString();
    }

    /**
     * Creates the Paramgroup from the constant as a message, i.e. paramaters
     * will be enriched with ={}
     *
     * @param fieldValue the field value.
     * @return the paramgroup as parameterized message.
     */
    private static String createParamGroup(String fieldValue) {
        return fieldValue.replaceAll(",", " = {},") + " = {}";
    }

    /**
     * Create a key name.
     *
     * @param prefix the prefix for the key.
     * @param className the simple name of the declaring class.
     * @param fieldName the field name.
     * @return a message-key.
     */
    private static String createKey(String prefix, String className,
            String fieldName) {
        return (prefix + "." + className + "." + fieldName).toLowerCase();
    }

    /**
     * Initiates an object of type URL.
     */
    private URL() {
        // UTILITY-CONSTRUCTOR
    }
}
//CSON: InterfaceIsType
