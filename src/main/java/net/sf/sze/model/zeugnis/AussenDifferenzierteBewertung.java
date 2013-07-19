// AussenDifferenzierteBewertung.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.model.zeugnis;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Bewertung mit zwei verschiedenen Niveaus.
 *
 * @author niels
 *
 */
@Entity
@DiscriminatorValue("net.sf.sze.zeugnis.AussenDifferenzierteBewertung")
public class AussenDifferenzierteBewertung extends Bewertung {

    // TODO der NotNull-Constraint gilt nur für diese SubKlasse :-/
    // leistungsniveau(inList:['G','E'], nullable:false)

    // NICE sollte ZweiNiveauBewertung heißen.

    /**
     *
     * Initiates an object of type AussenDifferenzierteBewertung.
     */
    public AussenDifferenzierteBewertung() {
        super();
        setLeistungsniveau("G");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
