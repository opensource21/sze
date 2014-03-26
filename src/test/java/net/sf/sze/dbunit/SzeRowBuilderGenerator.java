//SzeRowBuilderGenerator.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.dbunit.dataset.builder.CustomRowBuilderGenerator;


/**
 * Class SzeRowBuilderGenerator
 *
 */
public class SzeRowBuilderGenerator extends CustomRowBuilderGenerator {


    /**
     * Initiates an object of type SzeRowBuilderGenerator.
     */
    public SzeRowBuilderGenerator() {
        super(new File("src/test/java"), "net.sf.sze.dbunit.rowbuilder", "UTF-8");
        this.addTypeMapping(BigInteger.class, Long.class);
        this.addTypeMapping(BigDecimal.class, Double.class);
    }

}
