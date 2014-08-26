// StandardZweiNiveauIntersect.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;
import net.sf.sze.model.zeugnis.ZweiNiveauBewertung;
import net.sf.sze.model.zeugnis.Bewertung;

/**
 * Prüft ob es Schulfächer gibt die Standard- {@link Bewertung} und
 * {@link ZweiNiveauBewertung}en haben sollen.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = StandardZweiNiveauIntersectCheck.class)
public @interface StandardZweiNiveauIntersect {

    /**
     * Der Default-Value.
     *
     */
    String value() default "";

    /**
     * The message, default "validation.schulfach.standardIntersectAussen".
     *
     */
    String message() default StandardZweiNiveauIntersectCheck.MESSAGE;

}
