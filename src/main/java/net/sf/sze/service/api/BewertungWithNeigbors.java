//BewertungWithNeigbors.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.api;

import net.sf.sze.model.zeugnis.Bewertung;


/**
 * Container-Klasse um zu einer Bewertung auch die BewertungsId des
 * Vorgängers  und des Nachfolgers liefern zu können.
 *
 */
public class BewertungWithNeigbors {
    private final Bewertung bewertung;
    private final Long prevBewertungsId;
    private final Long nextBewertungsId;

    /**
     * Initiates an object of type BewertungWithNeigbors.
     * @param bewertung
     * @param prevBewertungsId
     * @param nextBewertungsId
     */
    public BewertungWithNeigbors(Bewertung bewertung,
            Long prevBewertungsId, Long nextBewertungsId) {
        super();
        this.bewertung = bewertung;
        this.prevBewertungsId = prevBewertungsId;
        this.nextBewertungsId = nextBewertungsId;
    }

    /**
     * @return the bewertung
     */
    public Bewertung getBewertung() {
        return bewertung;
    }

    /**
     * @return the prevBewertungsId
     */
    public Long getPrevBewertungsId() {
        return prevBewertungsId;
    }

    /**
     * @return the nextBewertungsId
     */
    public Long getNextBewertungsId() {
        return nextBewertungsId;
    }




}