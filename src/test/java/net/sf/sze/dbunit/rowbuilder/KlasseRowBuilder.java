package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class KlasseRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "KLASSE";

    public static final String C_GESCHLOSSEN = "GESCHLOSSEN";
    public static final String C_ID = "ID";
    public static final String C_JAHRGANG = "JAHRGANG";
    public static final String C_SUFFIX = "SUFFIX";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_GESCHLOSSEN, C_ID, C_JAHRGANG, C_SUFFIX, C_VERSION};

    public KlasseRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_JAHRGANG, new Integer("0"));
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_SUFFIX, "");
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_GESCHLOSSEN, Boolean.FALSE);
    }

    public final KlasseRowBuilder Geschlossen (Boolean value) {
        with(C_GESCHLOSSEN, value);
        return this;
    }

    public final KlasseRowBuilder Geschlossen (IValidator<?> value) {
        with(C_GESCHLOSSEN, value);
        return this;
    }

    public final KlasseRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final KlasseRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final KlasseRowBuilder Jahrgang (Integer value) {
        with(C_JAHRGANG, value);
        return this;
    }

    public final KlasseRowBuilder Jahrgang (IValidator<?> value) {
        with(C_JAHRGANG, value);
        return this;
    }

    public final KlasseRowBuilder Suffix (String value) {
        with(C_SUFFIX, value);
        return this;
    }

    public final KlasseRowBuilder Suffix (IValidator<?> value) {
        with(C_SUFFIX, value);
        return this;
    }

    public final KlasseRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final KlasseRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static KlasseRowBuilder newKlasse() {
        return new KlasseRowBuilder(PRIMARY_KEY);
    }

    public static KlasseRowBuilder newKlasse(String... identifierColumns) {
        return new KlasseRowBuilder(identifierColumns);
    }

}
