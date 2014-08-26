// StandardBewertung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Normale Bewertung von 1-6.
 *
 */
@Entity
@DiscriminatorValue("StandardBewertung")
public class StandardBewertung extends Bewertung {
    private static final List<String> NIVEAUS = new ArrayList<String>();

    @Override
    public List<String> getLeistungsNiveaus() {
        return NIVEAUS;
    }

}
