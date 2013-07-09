package de.ppi.spring.mvc.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Handles common exceptions.
 * 
 */
public final class DefaultExceptionHandler {

    /**
     * Initiates an object of type DefaultExceptionHandler.
     */
    private DefaultExceptionHandler() {
        // Constructor-class.
    }

    /**
     * Add an Error to the result.
     * 
     * @param result the result to which the error is added.
     * @param olE the exception.
     */
    public static void handleConcurrencyFailureException(BindingResult result,
            ConcurrencyFailureException olE) {
        final RequestAttributes rq =
                RequestContextHolder.currentRequestAttributes();
        String reloadURL = null;
        if (rq != null && rq instanceof ServletRequestAttributes) {
            final ServletRequestAttributes sra = (ServletRequestAttributes) rq;
            final HttpServletRequest request = sra.getRequest();
            reloadURL = request.getHeader("referer");
            if (StringUtils.isEmpty(reloadURL)) {
                reloadURL = request.getRequestURL().toString();
            }
        }

        result.addError(new ObjectError(result.getObjectName(),
                new String[] { "optimisitic.lock.violated" },
                new String[] { reloadURL },
                "The object was changed by another user."));
    }
}
