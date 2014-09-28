//KlasseConverter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.converter;

import net.sf.sze.model.stammdaten.Klasse;

import org.springframework.core.convert.converter.Converter;


/**
 * Converter for {@link Klasse} to String.
 *
 */
public class KlasseConverter implements Converter<Klasse, String> {


    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(Klasse source) {
        return source.calculateKlassenname() + " (" + source.getJahrgang() + ")";

    }

}
