// StandardBinnenIntersectCheck.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
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
//NICE sollte DreiNiveauBewertung statt BinnenBewertung heißen.
public class StandardBinnenIntersectCheck
        extends AbstractAnnotationCheck<StandardBinnenIntersect> {

    /**
     * Der Default-Message-Key.
     */
    public static final String MESSAGE =
            "validation.schulfach.standardIntersectBinnen";

    /**
     * Erzeugt einen neuen Check.
     */
    public StandardBinnenIntersectCheck() {
        setMessage(MESSAGE);
    }

    @Override
    public void configure(StandardBinnenIntersect constraintAnnotation) {
        setMessage(constraintAnnotation.message());
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(Object validatedObject, Object value,
            OValContext context, Validator validator) {

        final Schulfach obj = (Schulfach) validatedObject;
        return !CollectionUtils.containsAny(obj
                .convertStufenMitStandardBewertungToList(), obj
                .convertStufenMitBinnenDifferenzierungToList());

    }
}
