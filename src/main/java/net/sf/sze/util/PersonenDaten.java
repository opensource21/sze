//PersonDaten.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.util;


/**
 * Wesentliche Informationen zu einer Person.
 *
 */
public interface PersonenDaten {

    /**
     * Gets the geschlecht.
     *
     * @return the geschlecht
     */
    Geschlecht getGeschlecht();

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the rufname.
     *
     * @return the rufname
     */
    String getRufname();

    /**
     * Gets the vorname.
     *
     * @return the vorname
     */
    String getVorname();

    /**
     * Liefert falls vorhanden den Rufnamen, sonst den Vornamen.
     * @return falls vorhanden den Rufnamen, sonst den Vornamen.
     */
    String getRufnameOrVorname();

}
