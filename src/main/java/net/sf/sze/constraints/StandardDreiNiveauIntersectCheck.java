// StandardDreiNiveauIntersectCheck.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;

import org.springframework.util.CollectionUtils;

/**
 * Stellt sicher, dass die verschiedenen Bewertungstypen überschneidungsfrei sind.
 *
 */
public class StandardDreiNiveauIntersectCheck
        extends AbstractAnnotationCheck<StandardDreiNiveauIntersect> {

    /**
     * Der Default-Message-Key.
     */
    public static final String MESSAGE =
            "validation.schulfach.standardIntersectDreiNiveau";

    /**
     * Erzeugt einen neuen Check.
     */
    public StandardDreiNiveauIntersectCheck() {
        setMessage(MESSAGE);
    }

    @Override
    public void configure(StandardDreiNiveauIntersect constraintAnnotation) {
        setMessage(constraintAnnotation.message());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(Object validatedObject, Object value,
            OValContext context, Validator validator) {

        final DisjunktKlassenstufenConfigurer obj =
                (DisjunktKlassenstufenConfigurer) validatedObject;
        return !CollectionUtils.containsAny(obj
                .convertStufenMitStandardBewertungToList(), obj
                .convertStufenMitDreiNiveausToList());

    }
}
