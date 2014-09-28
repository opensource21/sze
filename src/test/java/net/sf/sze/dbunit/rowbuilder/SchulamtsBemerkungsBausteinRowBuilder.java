package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class SchulamtsBemerkungsBausteinRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULAMTS_BEMERKUNGS_BAUSTEIN";

    public static final String C_AKTIV = "AKTIV";
    public static final String C_BESCHREIBENDER_SATZ = "BESCHREIBENDER_SATZ";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AKTIV, C_BESCHREIBENDER_SATZ, C_ID, C_NAME, C_SORTIERUNG, C_VERSION};

    public SchulamtsBemerkungsBausteinRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_AKTIV, Boolean.FALSE);
        addDefaultValue(C_NAME, "");
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_BESCHREIBENDER_SATZ, "");
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Aktiv (Boolean value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Aktiv (IValidator<?> value) {
        with(C_AKTIV, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder BeschreibenderSatz (String value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder BeschreibenderSatz (IValidator<?> value) {
        with(C_BESCHREIBENDER_SATZ, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Name (String value) {
        with(C_NAME, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Name (IValidator<?> value) {
        with(C_NAME, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Sortierung (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Sortierung (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final SchulamtsBemerkungsBausteinRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static SchulamtsBemerkungsBausteinRowBuilder newSchulamtsBemerkungsBaustein() {
        return new SchulamtsBemerkungsBausteinRowBuilder(PRIMARY_KEY);
    }

    public static SchulamtsBemerkungsBausteinRowBuilder newSchulamtsBemerkungsBaustein(String... identifierColumns) {
        return new SchulamtsBemerkungsBausteinRowBuilder(identifierColumns);
    }

}
