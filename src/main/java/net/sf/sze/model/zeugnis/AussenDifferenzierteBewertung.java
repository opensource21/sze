// AussenDifferenzierteBewertung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.model.zeugnis;

import java.util.Arrays;
import java.util.List;

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
    // NICE sollte ZweiNiveauBewertung statt AussenBewertung heißen.
    // TODO der NotNull-Constraint gilt nur für diese SubKlasse :-/
    // leistungsniveau(inList:['G','E'], nullable:false)

    private static final List<String> NIVEAUS = Arrays.asList("G", "E");

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getLeistungsNiveaus() {
        return NIVEAUS;
    }


}
