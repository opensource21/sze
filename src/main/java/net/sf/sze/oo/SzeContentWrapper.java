// SzeContentWrapper.java
//
// (c) SZE-Development-Team

/**
 *
 */
package net.sf.sze.oo;

import net.sf.jooreports.templates.DocumentTemplate.ContentWrapper;

/**
 * Content Wrapper der auch \t durch Tab ersetzt.
 * @author niels
 *
 */
public class SzeContentWrapper implements ContentWrapper {

    /**
     * {@inheritDoc}
     */
    @Override
    public String wrapContent(String content) {
        return "[#ftl]\n"
                + "[#escape any as any?xml?replace(\"\\n\",\"<text:line-break />\")?replace(\"\\t\",\"<text:tab/>\")]\n"
                + content + "[/#escape]";
    }
}
