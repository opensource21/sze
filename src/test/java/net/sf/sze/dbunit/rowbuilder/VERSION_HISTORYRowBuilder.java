package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;
import java.sql.Timestamp;

public class VERSION_HISTORYRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "VERSION_HISTORY";

    public static final String C_APP_VERSION = "APP_VERSION";
    public static final String C_ID = "ID";
    public static final String C_INSTALL_DATE = "INSTALL_DATE";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_APP_VERSION, C_ID, C_INSTALL_DATE, C_VERSION};

    public VERSION_HISTORYRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_INSTALL_DATE, new Timestamp(0));
        addDefaultValue(C_APP_VERSION, "");
    }

    public final VERSION_HISTORYRowBuilder APP_VERSION (String value) {
        with(C_APP_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder APP_VERSION (IValidator<?> value) {
        with(C_APP_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder INSTALL_DATE (Timestamp value) {
        with(C_INSTALL_DATE, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder INSTALL_DATE (IValidator<?> value) {
        with(C_INSTALL_DATE, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final VERSION_HISTORYRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY() {
        return new VERSION_HISTORYRowBuilder(PRIMARY_KEY);
    }

    public static VERSION_HISTORYRowBuilder newVERSION_HISTORY(String... identifierColumns) {
        return new VERSION_HISTORYRowBuilder(identifierColumns);
    }

}
