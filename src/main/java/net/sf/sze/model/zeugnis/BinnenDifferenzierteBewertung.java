// BinnenDifferenzierteBewertung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.util.Arrays;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Bewertung mit drei verschiedenen Niveaus.
 *
 */
@Entity
@DiscriminatorValue("DreiNiveauBewertung")
public class BinnenDifferenzierteBewertung extends Bewertung {
    // NICE sollte DreiNiveauBewertung statt BinnenBewertung heißen.

    // TODO der NotNull-Constraint gilt nur für diese SubKlasse :-/
    // leistungsniveau(inList:['G','E','Z'], nullable:false)

    private static final List<String> NIVEAUS = Arrays.asList("G", "E", "Z");

    /**
     *
     * Initiates an object of type BinnenDifferenzierteBewertung.
     */
    public BinnenDifferenzierteBewertung() {
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
