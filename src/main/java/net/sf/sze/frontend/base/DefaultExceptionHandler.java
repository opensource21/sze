package net.sf.sze.frontend.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import de.ppi.fuwesta.spring.mvc.exception.BasicGlobalExceptionHandler;

/**
 * Default Exceptionhandler.
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler extends BasicGlobalExceptionHandler {

    /**
     * Initiates an object of type DefaultExceptionHandler.
     *
     */
    public DefaultExceptionHandler() {
        super("error");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ExceptionHandler(Exception.class)
    protected ModelAndView handleException(HttpServletRequest req, Exception e)
            throws Exception {
        return super.handleException(req, e);
    }

}
