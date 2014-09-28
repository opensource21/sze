package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class AgBewertungRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "AG_BEWERTUNG";

    public static final String C_ARBEITSGRUPPE_ID = "ARBEITSGRUPPE_ID";
    public static final String C_ID = "ID";
    public static final String C_TEILGENOMMEN = "TEILGENOMMEN";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ARBEITSGRUPPE_ID, C_ID, C_TEILGENOMMEN, C_VERSION, C_ZEUGNIS_ID};

    public AgBewertungRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_TEILGENOMMEN, Boolean.FALSE);
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_ARBEITSGRUPPE_ID, new Long("0"));
    }

    public final AgBewertungRowBuilder ArbeitsgruppeId (Long value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AgBewertungRowBuilder ArbeitsgruppeId (IValidator<?> value) {
        with(C_ARBEITSGRUPPE_ID, value);
        return this;
    }

    public final AgBewertungRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AgBewertungRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AgBewertungRowBuilder Teilgenommen (Boolean value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AgBewertungRowBuilder Teilgenommen (IValidator<?> value) {
        with(C_TEILGENOMMEN, value);
        return this;
    }

    public final AgBewertungRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AgBewertungRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AgBewertungRowBuilder ZeugnisId (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AgBewertungRowBuilder ZeugnisId (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AgBewertungRowBuilder newAgBewertung() {
        return new AgBewertungRowBuilder(PRIMARY_KEY);
    }

    public static AgBewertungRowBuilder newAgBewertung(String... identifierColumns) {
        return new AgBewertungRowBuilder(identifierColumns);
    }

}
