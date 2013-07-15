// BootstrapDialect.java
//
// (c) SZE-Development-Team

package de.ppi.thymeleaf.bootstrap;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * The dialect for Twitter Bootstrap.
 *
 */
public class BootstrapDialect extends AbstractDialect {

    /**
     * The prefix of this Dialect.
     */
    public static final String PREFIX = "bs";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return PREFIX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLenient() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new BootstrapFieldAttrProcessor());
        processors.add(new BootstrapNameAttrProcessor());
        return processors;
    }
}
