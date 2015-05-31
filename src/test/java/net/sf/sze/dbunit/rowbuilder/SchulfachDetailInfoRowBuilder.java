package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;

public class SchulfachDetailInfoRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULFACH_DETAIL_INFO";

    public static final String C_DETAIL_INFO = "DETAIL_INFO";
    public static final String C_FORMULAR_ID = "FORMULAR_ID";
    public static final String C_ID = "ID";
    public static final String C_SCHULFACH_ID = "SCHULFACH_ID";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_DETAIL_INFO, C_FORMULAR_ID, C_ID, C_SCHULFACH_ID, C_VERSION};

    public SchulfachDetailInfoRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_DETAIL_INFO, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_SCHULFACH_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_FORMULAR_ID, new Long("0"));
    }

    public final SchulfachDetailInfoRowBuilder DetailInfo (String value) {
        with(C_DETAIL_INFO, value);
        return this;
    }

    public final SchulfachDetailInfoRowBuilder FormularId (Number value) {
        with(C_FORMULAR_ID, value);
        return this;
    }

    public final SchulfachDetailInfoRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final SchulfachDetailInfoRowBuilder SchulfachId (Number value) {
        with(C_SCHULFACH_ID, value);
        return this;
    }

    public final SchulfachDetailInfoRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static SchulfachDetailInfoRowBuilder newSchulfachDetailInfo() {
        return new SchulfachDetailInfoRowBuilder(PRIMARY_KEY);
    }

    public static SchulfachDetailInfoRowBuilder newSchulfachDetailInfo(String... identifierColumns) {
        return new SchulfachDetailInfoRowBuilder(identifierColumns);
    }

}
