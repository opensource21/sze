//ModelAttributes.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.base;



/**
 * Konstante für Model-Attribute.
 *
 */
//CSOFF: InterfaceIsType Konstanten müssen String sein.
public interface ModelAttributes {

    /**
     * Attribut-Name für Bemerkungen.
     */
    String BEMERKUNG = "bemerkung";


    /**
     * Attributname für {@link Bewertung}.
     */
    String BEWERTUNG = "bewertung";

    /**
     * Attribut-Name für Nachrichten.
     */
    String MESSAGE = "message";


    /**
     * Attributname für die cancelURL.
     */
    String CANCEL_URL = "cancelUrl";
}
//CSON: InterfaceIsType
