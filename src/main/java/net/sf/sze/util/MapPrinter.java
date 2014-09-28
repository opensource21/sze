//MapPrinter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Schreibt den Inhalt einer Map in eine Datei.
 *
 */
public class MapPrinter {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MapPrinter.class);

    private final String outputName;

    private final boolean keysOnly;

    private final List<String> listOfEntries = new ArrayList<>();

    /**
     * Initiates an object of type MapPrinter.
     * @param outputName der Dateiname wohin die Daten geschrieben werden sollen.
     * @param keysOnly true wenn nur die Schlüssel geschrieben werden sollen.
     */
    public MapPrinter(String outputName, boolean keysOnly) {
        super();
        this.outputName = outputName;
        this.keysOnly = keysOnly;
    }

    /**
     * Initiates an object of type MapPrinter.
     * @param output der Dateiname wohin die Daten geschrieben werden sollen.
     */
    public MapPrinter(String output) {
        this(output, false);
    }

    /**
     * Schreibt eine Map in die Datei.
     * @param map die Map.
     */
    public void print(Map<String, Object> map) {
        try (PrintStream output = new PrintStream(new File(outputName))) {
            collectEntries("", map);
            Collections.sort(listOfEntries);
            for (String entry : listOfEntries) {
                output.println(entry);
            }
        } catch (FileNotFoundException e) {
            LOG.error("Fehler beim Schreiben der Map", e);
            throw new RuntimeException("Map konnte nicht geschrieben werden", e);
        }
    }

    @SuppressWarnings("unchecked")
    private void collectEntries(String prefix, Map<String, ?> map) {
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            final String key = entry.getKey();
            final Object value = entry.getValue();
            if (value instanceof Map) {
                collectEntries(prefix + key + ".", (Map<String, ?>) value);
            } else {
                StringBuilder entryAsString = new StringBuilder(prefix + key);
                if (!keysOnly) {
                    entryAsString.append(" -> " + value.toString().replaceAll("\\n", "#@NL@#"));
                }
                listOfEntries.add(entryAsString.toString());
            }
        }
    }

}
