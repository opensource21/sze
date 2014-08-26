// BinnenAussenIntersect.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;

/**
 * Prüft ob es Schulfächer gibt die {@link DreiNiveauBewertung} und
 * {@link AussenDifferenzierteBewertung}en haben sollen.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = BinnenAussenIntersectCheck.class)
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
