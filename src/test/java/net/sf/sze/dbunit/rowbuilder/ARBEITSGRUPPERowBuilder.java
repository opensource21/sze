package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class ARBEITSGRUPPERowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "ARBEITSGRUPPE";

    public static final String C_ID = "ID";
    public static final String C_KLASSENSTUFEN = "KLASSENSTUFEN";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ID, C_KLASSENSTUFEN, C_NAME, C_SORTIERUNG, C_VERSION};

    public ARBEITSGRUPPERowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_KLASSENSTUFEN, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final ARBEITSGRUPPERowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder KLASSENSTUFEN (String value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder KLASSENSTUFEN (IValidator<?> value) {
        with(C_KLASSENSTUFEN, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder SORTIERUNG (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ARBEITSGRUPPERowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE() {
        return new ARBEITSGRUPPERowBuilder(PRIMARY_KEY);
    }

    public static ARBEITSGRUPPERowBuilder newARBEITSGRUPPE(String... identifierColumns) {
        return new ARBEITSGRUPPERowBuilder(identifierColumns);
    }

}
