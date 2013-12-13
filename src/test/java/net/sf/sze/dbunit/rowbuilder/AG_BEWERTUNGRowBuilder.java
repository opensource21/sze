package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.DataRowBuilder;
import org.dbunit.dataset.builder.DataSetManipulator;
import org.dbunit.validator.Validator;

public class AG_BEWERTUNGRowBuilder extends DataRowBuilder {

    public static final String TABLE_NAME = "AG_BEWERTUNG";

    public static final String C_ARBEITSGRUPPE_ID = "ARBEITSGRUPPE_ID";
    public static final String C_ID = "ID";
    public static final String C_TEILGENOMMEN = "TEILGENOMMEN";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public AG_BEWERTUNGRowBuilder(DataSetManipulator dataSetManipulator, boolean initNotNullValues, String... identifierColumns) {
        super(dataSetManipulator, TABLE_NAME, identifierColumns);
        if (initNotNullValues) {
            with(C_VERSION, new Long("0"));
            with(C_ZEUGNIS_ID, new Long("0"));
            with(C_TEILGENOMMEN, Boolean.FALSE);
            with(C_ID, new Long("0"));
            with(C_ARBEITSGRUPPE_ID, new Long("0"));
        }
    }

    public final AG_BEWERTUNGRowBuilder ARBEITSGRUPPE_ID (Long value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ARBEITSGRUPPE_ID (Validator<?> value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ID (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ID (Validator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder TEILGENOMMEN (Boolean value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder TEILGENOMMEN (Validator<?> value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder VERSION (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder VERSION (Validator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ZEUGNIS_ID (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AG_BEWERTUNGRowBuilder ZEUGNIS_ID (Validator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG(DataSetManipulator builder) {
        return new AG_BEWERTUNGRowBuilder(builder, true, PRIMARY_KEY);
    }

    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG(DataSetManipulator builder, String... identifierColumns) {
        return new AG_BEWERTUNGRowBuilder(builder, true, identifierColumns);
    }

    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG(DataSetManipulator builder, boolean initNotNullValues) {
        return new AG_BEWERTUNGRowBuilder(builder, initNotNullValues, PRIMARY_KEY);
    }

    public static AG_BEWERTUNGRowBuilder newAG_BEWERTUNG(DataSetManipulator builder, boolean initNotNullValues, String... identifierColumns) {
        return new AG_BEWERTUNGRowBuilder(builder, initNotNullValues, identifierColumns);
    }
}
