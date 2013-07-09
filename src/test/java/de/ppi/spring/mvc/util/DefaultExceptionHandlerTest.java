package de.ppi.spring.mvc.util;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Test {@link DefaultExceptionHandler}.
 * 
 */
public class DefaultExceptionHandlerTest {

    /** {@link HttpServletRequest}-mock. */
    @Mock
    private HttpServletRequest request;

    /** {@link BindingResult}-mock. */
    @Mock
    private BindingResult result;

    /**
     * Initialize the test.
     * 
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        final ServletRequestAttributes sra =
                new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(sra);
    }

    /**
     * Test method for
     * {@link de.ppi.spring.mvc.util.DefaultExceptionHandler#handleConcurrencyFailureException(org.springframework.validation.BindingResult, org.springframework.dao.ConcurrencyFailureException)}
     * .
     */
    @Test
    public void testHandleConcurrencyFailureExceptionReferer() {
        // Arrange
        final String reloadUrl = "Test a url";
        when(request.getHeader("referer")).thenReturn(reloadUrl);
        when(result.getObjectName()).thenReturn("ObjectName!");

        ObjectError expectedError =
                new ObjectError(result.getObjectName(),
                        new String[] { "optimisitic.lock.violated" },
                        new String[] { reloadUrl },
                        "The object was changed by another user.");

        // Act
        DefaultExceptionHandler.handleConcurrencyFailureException(result,
                new ConcurrencyFailureException("test"));
        // Assert
        verify(result).addError(expectedError);

    }

    /**
     * Test method for
     * {@link de.ppi.spring.mvc.util.DefaultExceptionHandler#handleConcurrencyFailureException(org.springframework.validation.BindingResult, org.springframework.dao.ConcurrencyFailureException)}
     * .
     */
    @Test
    public void testHandleConcurrencyFailureExceptionNonReferer() {
        // Arrange
        final String reloadUrl = "Test a url";
        when(request.getHeader("referer")).thenReturn(null);
        when(request.getRequestURL()).thenReturn(new StringBuffer(reloadUrl));
        when(result.getObjectName()).thenReturn("ObjectName!");

        ObjectError expectedError =
                new ObjectError(result.getObjectName(),
                        new String[] { "optimisitic.lock.violated" },
                        new String[] { reloadUrl },
                        "The object was changed by another user.");

        // Act
        DefaultExceptionHandler.handleConcurrencyFailureException(result,
                new ConcurrencyFailureException("test"));
        // Assert
        verify(result).addError(expectedError);

    }
}
