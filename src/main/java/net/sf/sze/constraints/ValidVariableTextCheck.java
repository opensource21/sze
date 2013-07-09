// ValidVariableTextCheck.java
//
// (c) SZE-Development-Team

package net.sf.sze.constraints;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.sze.util.VariableUtility;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prüft ob ein Text nur gültige Variablen enthält.
 *
 */
public class ValidVariableTextCheck
        extends AbstractAnnotationCheck<ValidVariableText> {

    /**
     * The default message-key.
     */
    static final String MESSAGE = "validation.valid.variable";

    /**
     * Variablen, die unzulässig sind.
     */
    private List<String> illegalVariables;

    /**
     * Instantiates a new unique check.
     */
    public ValidVariableTextCheck() {
        setMessage(MESSAGE);
    }

    @Override
    public void configure(ValidVariableText constraintAnnotation) {
        setMessage(constraintAnnotation.message());
    }

    @Override
    public Map<String, String> createMessageVariables() {
        final Map<String, String> messageVariables = new HashMap<String,
                String>();
        messageVariables.put("illegalVariable", StringUtils.join(
                illegalVariables, ", "));
        messageVariables.put("allowedVariable", StringUtils.join(VariableUtility
                .VARIABLE_NAMES, ", "));
        return messageVariables;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(Object validatedObject, Object value,
            OValContext context, Validator validator) {
        requireMessageVariablesRecreation();

        if (value == null) {
            return true;
        }

        illegalVariables = VariableUtility.getInvalidVariables((String) value);
        return illegalVariables.isEmpty();
    }
}
