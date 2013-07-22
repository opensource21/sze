// Test.java
//
// (c) SZE-Development-Team

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Test
 *
 */
public class Test {

    /**
     * The Logger for the controller.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        int jahr = 1971;
        String test = (jahr - 1) + "/" + Integer.toString(jahr).substring(2, 4);
        System.out.println(test);

    }
}
