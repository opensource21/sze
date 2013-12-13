package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;
import java.sql.Timestamp;

public class VERSION_HISTORYRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "VERSION_HISTORY";

    public static final String C_APP_VERSION = "APP_VERSION";
    public static final String C_ID = "ID";
    public static final String C_INSTALL_DATE = "INSTALL_DATE";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public VERSION_HISTORYRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_VERSION, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_INSTALL_DATE, new Timestamp(0));
            with(C_APP_VERSION, "");
        }
    }

    public final VERSION_HISTORYRowBuilder APP_VERSION (String value) {
        with(C_APP_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder APP_VERSION (Validator<?> value) {
        with(C_APP_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder INSTALL_DATE (Timestamp value) {
        with(C_INSTALL_DATE, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder INSTALL_DATE (Validator<?> value) {
        with(C_INSTALL_DATE, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY(DataSetManipulator builder) {
        return new VERSION_HISTORYRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY(DataSetManipulator builder, String... identifierColumns) {
        return new VERSION_HISTORYRowBuilder(builder, true, identifierColumns);
    }

    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY(DataSetManipulator builder, boolean initNotNullValues) {
        return new VERSION_HISTORYRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new VERSION_HISTORYRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
