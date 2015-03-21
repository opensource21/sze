//SchuelerList.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.service.api.stammdaten;

import java.util.List;

import org.springframework.util.CollectionUtils;

import net.sf.sze.model.stammdaten.Schueler;



/**
 * Datencontainer, welcher Informationen über Schüler vorhält.
 *
 */
public class SchuelerList {

    private final Schueler prevSchueler;

    private final Schueler currentSchueler;

    private final Schueler nextSchueler;

    private final List<Schueler> schuelerList;

    /**
     * Initialisiert einen neue Instanz.
     * @param schuelerList Liste aller Schueler.
     * @param currentSchuelerId die Id des aktuellen Schülers.
     */
    public SchuelerList(List<Schueler> schuelerList, Long currentSchuelerId) {
        super();
        this.schuelerList = schuelerList;
        Schueler beforeSelectedSchueler = null;
        Schueler selectedSchueler = null;
        Schueler afterSelectedSchueler = null;
        if (schuelerList != null) {
            for (Schueler schueler : schuelerList) {
                if (selectedSchueler != null && afterSelectedSchueler == null) {
                    afterSelectedSchueler = schueler;
                }
                // Sicherstellen, dass es immer einen selektierten Schüler gibt.
                if (currentSchuelerId == null && selectedSchueler == null) {
                    selectedSchueler = schueler;
                } else if (schueler.getId().equals(currentSchuelerId)) {
                    selectedSchueler = schueler;
                }
                if (selectedSchueler == null) {
                    beforeSelectedSchueler = schueler;
                }
            }
        }
        if (selectedSchueler == null) {
            beforeSelectedSchueler = null;
        }
        this.prevSchueler = beforeSelectedSchueler;
        this.currentSchueler = selectedSchueler;
        this.nextSchueler = afterSelectedSchueler;
    }

    /**
     * @return the prevSchueler
     */
    public Schueler getPrevSchueler() {
        return prevSchueler;
    }

    /**
     * @return the currentSchueler
     */
    public Schueler getCurrentSchueler() {
        return currentSchueler;
    }

    /**
     * @return the nextSchueler
     */
    public Schueler getNextSchueler() {
        return nextSchueler;
    }


    /**
     * @return the prevSchueler
     */
    public Long getPrevSchuelerId() {
        return prevSchueler == null ? null : prevSchueler.getId();
    }

    /**
     * @return the currentSchueler
     */
    public Long getCurrentSchuelerId() {
        return currentSchueler == null ? null : currentSchueler.getId();
    }

    /**
     * @return the nextSchueler
     */
    public Long getNextSchuelerId() {
        return nextSchueler == null ? null : nextSchueler.getId();
    }

    /**
     * @return the schuelerList
     */
    public List<Schueler> getSchuelerList() {
        return schuelerList;
    }

    /**
     * @return true wenn es keine Schueler gibt.
     */
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(schuelerList);
    }

}
