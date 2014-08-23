//KlasseConverter.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.frontend.converter;

import net.sf.sze.model.zeugnis.ZeugnisFormular;

import org.springframework.core.convert.converter.Converter;


/**
 * Converter for {@link ZeugnisFormular} to String.
 *
 */
public class ZeugnisFormularConverter implements Converter<ZeugnisFormular, String> {


    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(ZeugnisFormular source) {
        return source.toString();

    }

}
