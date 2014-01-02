package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class AV_SV_BEWERTUNGRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "AV_SV_BEWERTUNG";

    public static final String C_ARBEITS_UND_SOZIAL_VERHALTEN_ID = "ARBEITS_UND_SOZIAL_VERHALTEN_ID";
    public static final String C_BEURTEILUNG = "BEURTEILUNG";
    public static final String C_ID = "ID";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, C_BEURTEILUNG, C_ID, C_VERSION, C_ZEUGNIS_ID};

    public AV_SV_BEWERTUNGRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, new Long("0"));
    }

    public final AV_SV_BEWERTUNGRowBuilder ARBEITS_UND_SOZIAL_VERHALTEN_ID (Long value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ARBEITS_UND_SOZIAL_VERHALTEN_ID (IValidator<?> value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder BEURTEILUNG (Integer value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder BEURTEILUNG (IValidator<?> value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ID (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder VERSION (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ZEUGNIS_ID (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG() {
        return new AV_SV_BEWERTUNGRowBuilder(PRIMARY_KEY);
    }

    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG(String... identifierColumns) {
        return new AV_SV_BEWERTUNGRowBuilder(identifierColumns);
    }

}
