package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class AV_SV_BEWERTUNGRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "AV_SV_BEWERTUNG";

    public static final String C_ARBEITS_UND_SOZIAL_VERHALTEN_ID = "ARBEITS_UND_SOZIAL_VERHALTEN_ID";
    public static final String C_BEURTEILUNG = "BEURTEILUNG";
    public static final String C_ID = "ID";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public AV_SV_BEWERTUNGRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_VERSION, new Long("0"));
            with(C_ZEUGNIS_ID, new Long("0"));
            with(C_ID, new Long("0"));
            with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, new Long("0"));
        }
    }

    public final AV_SV_BEWERTUNGRowBuilder ARBEITS_UND_SOZIAL_VERHALTEN_ID (Long value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ARBEITS_UND_SOZIAL_VERHALTEN_ID (Validator<?> value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder BEURTEILUNG (Integer value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder BEURTEILUNG (Validator<?> value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AV_SV_BEWERTUNGRowBuilder ZEUGNIS_ID (Validator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG(DataSetManipulator builder) {
        return new AV_SV_BEWERTUNGRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG(DataSetManipulator builder, String... identifierColumns) {
        return new AV_SV_BEWERTUNGRowBuilder(builder, true, identifierColumns);
    }

    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG(DataSetManipulator builder, boolean initNotNullValues) {
        return new AV_SV_BEWERTUNGRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static AV_SV_BEWERTUNGRowBuilder newAV_SV_BEWERTUNG(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new AV_SV_BEWERTUNGRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
