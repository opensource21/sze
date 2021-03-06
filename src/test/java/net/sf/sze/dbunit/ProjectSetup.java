package net.sf.sze.dbunit;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Testclass to generate some files.
 *
 */

public class ProjectSetup extends AbstractSzeDbUnitTest {

    /**
     * Print all TableNames.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @Ignore("Only for project-setup")
    public void printTableNames() throws Exception {
        getSzeDatabase().printTableNames();
    }

    /**
     * @see de.ppi.samples.fuwesta.dbunit.FuWeStaSampleDatabase#generateRowBuilder()
     */
    @Test
    @Ignore("Only for project-setup")
    public void generateRowBuilder() throws Exception {
        getSzeDatabase().generateRowBuilder();
    }

    /**
     * @see de.ppi.samples.fuwesta.dbunit.FuWeStaSampleDatabase#generateRowBuilder()
     */
    @Test
    @Ignore("Only for project-setup")
    public void dumpData() throws Exception {
        getSzeDatabase().dumpResult("Dump");
    }

}
