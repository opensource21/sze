// StandardAussenIntersect.java
//
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;
import net.sf.sze.model.zeugnis.AussenDifferenzierteBewertung;
import net.sf.sze.model.zeugnis.Bewertung;

/**
 * Prüft ob es Schulfächer gibt die Standard- {@link Bewertung} und
 * {@link AussenDifferenzierteBewertung}en haben sollen.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = StandardAussenIntersectCheck.class)
public @interface StandardAussenIntersect {

    /**
     * Der Default-Value.
     *
     */
    String value() default "";

    /**
     * The message, default "validation.schulfach.standardIntersectAussen".
     *
     */
    String message() default StandardAussenIntersectCheck.MESSAGE;

}
