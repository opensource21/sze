// StandardBinnenIntersect.java
//
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;
import net.sf.sze.model.zeugnis.Bewertung;
import net.sf.sze.model.zeugnis.BinnenDifferenzierteBewertung;

/**
 * Prüft ob es Schulfächer gibt die Standard- {@link Bewertung} und
 * {@link BinnenDifferenzierteBewertung}en haben sollen.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = StandardBinnenIntersectCheck.class)
public @interface StandardBinnenIntersect {

    /**
     * Der Default-Value.
     *
     */
    String value() default "";

    /**
     * The message, default "validation.schulfach.standardIntersectBinnen".
     *
     */
    String message() default StandardBinnenIntersectCheck.MESSAGE;

}
