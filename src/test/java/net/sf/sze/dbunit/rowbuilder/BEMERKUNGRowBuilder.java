package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class BEMERKUNGRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "BEMERKUNG";

    public static final String C_BAUSTEIN_ID = "BAUSTEIN_ID";
    public static final String C_ER_SIE_STATT_NAMEN = "ER_SIE_STATT_NAMEN";
    public static final String C_FREI_TEXT = "FREI_TEXT";
    public static final String C_ID = "ID";
    public static final String C_SORTIERUNG = "SORTIERUNG";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_BAUSTEIN_ID, C_ER_SIE_STATT_NAMEN, C_FREI_TEXT, C_ID, C_SORTIERUNG, C_VERSION, C_ZEUGNIS_ID};

    public BEMERKUNGRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SORTIERUNG, new Long("0"));
        addDefaultValue(C_ER_SIE_STATT_NAMEN, Boolean.FALSE);
        addDefaultValue(C_BAUSTEIN_ID, new Long("0"));
    }

    public final BEMERKUNGRowBuilder BAUSTEIN_ID (Long value) {
        with(C_BAUSTEIN_ID, value);
        return this;
    }

    public final BEMERKUNGRowBuilder BAUSTEIN_ID (IValidator<?> value) {
        with(C_BAUSTEIN_ID, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ER_SIE_STATT_NAMEN (Boolean value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ER_SIE_STATT_NAMEN (IValidator<?> value) {
        with(C_ER_SIE_STATT_NAMEN, value);
        return this;
    }

    public final BEMERKUNGRowBuilder FREI_TEXT (String value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final BEMERKUNGRowBuilder FREI_TEXT (IValidator<?> value) {
        with(C_FREI_TEXT, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final BEMERKUNGRowBuilder SORTIERUNG (Long value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final BEMERKUNGRowBuilder SORTIERUNG (IValidator<?> value) {
        with(C_SORTIERUNG, value);
        return this;
    }

    public final BEMERKUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEMERKUNGRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final BEMERKUNGRowBuilder ZEUGNIS_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static BEMERKUNGRowBuilder newBEMERKUNG() {
        return new BEMERKUNGRowBuilder(PRIMARY_KEY);
    }

    public static BEMERKUNGRowBuilder newBEMERKUNG(String... identifierColumns) {
        return new BEMERKUNGRowBuilder(identifierColumns);
    }

}
