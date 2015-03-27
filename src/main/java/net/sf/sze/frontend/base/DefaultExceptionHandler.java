package net.sf.sze.frontend.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(DefaultExceptionHandler.class);

    /**
     * Initiates an object of type DefaultExceptionHandler.
     *
     */
    public DefaultExceptionHandler() {
        super("errorWithMail");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ExceptionHandler(Exception.class)
    protected ModelAndView handleException(HttpServletRequest req, Exception e)
            throws Exception {
        if (e.getClass().equals(DataIntegrityViolationException.class)
                && req.getMethod().equals("POST")
                && req.getRequestURI().contains("delete")) {
            //Das ist mit hoher Wahrscheinlichkeit eine FK Verletzung.
            LOG.warn("Fehler beim Löschen eines Objektes", e);
            final String backUrl = "javascript:history.go(-2)";
            final ModelAndView mav = new ModelAndView();
            mav.addObject("message", e.getLocalizedMessage());
            mav.addObject("backurl", backUrl);
            mav.setViewName("fkexception");
            return mav;
        }

        return super.handleException(req, e);
    }

}
