package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import java.sql.Timestamp;

public class RevisionLogRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "REVISION_LOG";

    public static final String C_ACTION = "ACTION";
    public static final String C_COLUMN_NAME = "COLUMN_NAME";
    public static final String C_ENTITY_ID = "ENTITY_ID";
    public static final String C_ENTITY_NAME = "ENTITY_NAME";
    public static final String C_ID = "ID";
    public static final String C_MODIFICATIONTIME = "MODIFICATIONTIME";
    public static final String C_NEW_VALUE = "NEW_VALUE";
    public static final String C_OLD_VALUE = "OLD_VALUE";
    public static final String C_USER = "USER";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ACTION, C_COLUMN_NAME, C_ENTITY_ID, C_ENTITY_NAME, C_ID, C_MODIFICATIONTIME, C_NEW_VALUE, C_OLD_VALUE, C_USER};

    public RevisionLogRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_ENTITY_ID, new Long("0"));
        addDefaultValue(C_COLUMN_NAME, "");
        addDefaultValue(C_ENTITY_NAME, "");
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_MODIFICATIONTIME, new Timestamp(0));
        addDefaultValue(C_ACTION, "");
        addDefaultValue(C_USER, "");
    }

    public final RevisionLogRowBuilder Action (String value) {
        with(C_ACTION, value);
        return this;
    }

    public final RevisionLogRowBuilder ColumnName (String value) {
        with(C_COLUMN_NAME, value);
        return this;
    }

    public final RevisionLogRowBuilder EntityId (Number value) {
        with(C_ENTITY_ID, value);
        return this;
    }

    public final RevisionLogRowBuilder EntityName (String value) {
        with(C_ENTITY_NAME, value);
        return this;
    }

    public final RevisionLogRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final RevisionLogRowBuilder Modificationtime (Timestamp value) {
        with(C_MODIFICATIONTIME, value);
        return this;
    }

    public final RevisionLogRowBuilder NewValue (String value) {
        with(C_NEW_VALUE, value);
        return this;
    }

    public final RevisionLogRowBuilder OldValue (String value) {
        with(C_OLD_VALUE, value);
        return this;
    }

    public final RevisionLogRowBuilder User (String value) {
        with(C_USER, value);
        return this;
    }


    public static RevisionLogRowBuilder newRevisionLog() {
        return new RevisionLogRowBuilder(PRIMARY_KEY);
    }

    public static RevisionLogRowBuilder newRevisionLog(String... identifierColumns) {
        return new RevisionLogRowBuilder(identifierColumns);
    }

}
