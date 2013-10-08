// StandardBewertung.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.model.zeugnis;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Normale Bewertung von 1-6.
 *
 */
@Entity
@DiscriminatorValue("net.sf.sze.zeugnis.StandardBewertung")
public class StandardBewertung extends Bewertung {
    // Leistungsniveau darf nicht mit angezeigt werden.

}
