// ValidVariableText.java
//
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Prüft ob ein Text nur gültige Variablen enthält.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(checkWith = ValidVariableTextCheck.class)
public @interface ValidVariableText {

    /**
     * The context of the unqiue-key.
     *
     */
    String value() default "";

    /**
     * The message, default "validation.unique".
     *
     */
    String message() default ValidVariableTextCheck.MESSAGE;

}
