package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMTS_BEMERKUNGS_BAUSTEIN";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_BESCHREIBENDER_SATZ = "BESCHREIBENDER_SATZ";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_BESCHREIBENDER_SATZ, C_ID, C_NAME, C_SORTIERUNG, C_VERSION};

    public SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_BESCHREIBENDER_SATZ, "");
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder AKTIV (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder AKTIV (IValidator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder BESCHREIBENDER_SATZ (String value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder BESCHREIBENDER_SATZ (IValidator<?> value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder NAME (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder NAME (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder SORTIERUNG (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder newSCHULAMTS_BEMERKUNGS_BAUSTEIN() {
        return new SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder(PRIMARY_KEY);
    }

    public static SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder newSCHULAMTS_BEMERKUNGS_BAUSTEIN(String... identifierColumns) {
        return new SCHULAMTS_BEMERKUNGS_BAUSTEINRowBuilder(identifierColumns);
    }

}
