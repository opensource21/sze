//AnonymisierungsService.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.api.common;



/**
 * Service zum anonymisieren des Bestandes.
 *
 */
public interface AnonymisierungsService {

    /**
     * Ersetzt bei allen Texten die Namen durch Variablen.
     */
    void replaceAllNamesWithVariables();

    /**
     * Ersetzt bei allen Schülern die Namen durch Fantasienamen
     * und verändert die Geburtsdaten und den Geburtsort.
     */
    void anonymisierSchueler();

}
