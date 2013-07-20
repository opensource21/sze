// ResultContainer.java
//
// (c) SZE-Development-Team

package net.sf.sze.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Kleiner Container um die Ergebnisse vom Service aufzunehmen.
 * @author niels
 *
 */
public class ResultContainer {

    /**
     * Meldungen.
     */
    private final Set<String> messages = new HashSet<String>();

    /**
     * Fehlermeldungen.
     */
    private final Set<String> errors = new HashSet<String>();

    /**
     * Fügt eine Meldung hinzu.
     * @param message die Meldung.
     */
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * Fügt eine Fehlermeldung hinzu.
     * @param message die Fehlermeldung
     */
    public void addError(String message) {
        errors.add(message);
    }

    /**
     * Fügt alle Meldungen aus dem übergebenen {@link ResultContainer} hinzu.
     * @param res die anderen Meldungen.
     */
    public void addResultContainer(ResultContainer res) {
        messages.addAll(res.messages);
        errors.addAll(res.errors);
    }
}
