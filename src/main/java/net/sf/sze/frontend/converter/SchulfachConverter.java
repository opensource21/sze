//KlasseConverter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.converter;

import net.sf.sze.model.zeugnisconfig.Schulfach;

import org.springframework.core.convert.converter.Converter;


/**
 * Converter for {@link Schulfach} to String.
 *
 */
public class SchulfachConverter implements Converter<Schulfach, String> {


    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(Schulfach source) {
        return source.getName();

    }

}
