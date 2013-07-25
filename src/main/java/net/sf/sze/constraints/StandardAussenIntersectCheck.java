// StandardAussenIntersectCheck.java
//
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.sze.model.zeugnis.Schulfach;

import org.springframework.util.CollectionUtils;

/**
 * Stellt sicher, dass die verschiedenen Bewertungstypen überschneidungsfrei sind.
 *
 */
//NICE sollte ZweiNiveauBewertung statt AussenBewertung heißen.
public class StandardAussenIntersectCheck
        extends AbstractAnnotationCheck<StandardAussenIntersect> {

    /**
     * Der Default-Message-Key.
     */
    public static final String MESSAGE =
            "validation.schulfach.standardIntersectAussen";

    /**
     * Erzeugt einen neuen Check.
     */
    public StandardAussenIntersectCheck() {
        setMessage(MESSAGE);
    }

    @Override
    public void configure(StandardAussenIntersect constraintAnnotation) {
        setMessage(constraintAnnotation.message());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(Object validatedObject, Object value,
            OValContext context, Validator validator) {

        Schulfach obj = (Schulfach) validatedObject;
        return !CollectionUtils.containsAny(obj
                .convertStufenMitStandardBewertungToList(), obj
                .convertStufenMitAussenDifferenzierungToList());
    }
}
