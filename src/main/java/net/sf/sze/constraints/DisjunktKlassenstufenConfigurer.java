//DisjunktKlassenstufenConfigurer.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.constraints;

import java.util.List;

/**
 * Interface zum Anzeigen, dass für verschiedene Klassenstufe unterschiedliche
 * Vorgänge vorgenommen werden.
 *
 */
public interface DisjunktKlassenstufenConfigurer {

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    List<String> convertStufenMitStandardBewertungToList();

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    List<String> convertStufenMitBinnenDifferenzierungToList();

    /**
     * Konvertiert den String der Klassenstufen in eine Liste.
     * @return den String der Klassenstufen als Liste.
     */
    List<String> convertStufenMitAussenDifferenzierungToList();

}
