// BinnenAussenIntersect.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import net.sf.oval.configuration.annotation.Constraint;
import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.BinnenDifferenzierteBewertung;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Prüft ob es Schulfächer gibt die {@link BinnenDifferenzierteBewertung} und
 * {@link AussenDifferenzierteBewertung}en haben sollen.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = StandardAussenIntersectCheck.class)
public @interface BinnenAussenIntersect {

    /**
     * Der Default-Value.
     *
     */
    String value() default "";

    /**
     * The message, default "validation.schulfach.binnenIntersectAussen".
     *
     */
    String message() default BinnenAussenIntersectCheck.MESSAGE;

}
