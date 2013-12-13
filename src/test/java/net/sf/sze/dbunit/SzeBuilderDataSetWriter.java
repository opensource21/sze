//Sze.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.dbunit.dataset.builder.BuilderDataSetWriter;


/**
 * Class Sze
 *
 */
public class SzeBuilderDataSetWriter extends BuilderDataSetWriter {


    /**
     * Initiates an object of type Sze.
     * @param packageName
     * @param className
     * @param importStatements
     */
    public SzeBuilderDataSetWriter(boolean includeEmptyColumns, String packageName, String className,
            String... importStatements) {
        super(includeEmptyColumns, new File("src/test/java"), packageName, className, "UTF-8",
                "net.sf.sze.dbunit.rowbuilder", importStatements);
        this.addTypeMapping(BigInteger.class, Long.class);
        this.addTypeMapping(BigDecimal.class, Double.class);
    }

}
