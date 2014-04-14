package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;

public class AvSvBewertungRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "AV_SV_BEWERTUNG";

    public static final String C_ARBEITS_UND_SOZIAL_VERHALTEN_ID = "ARBEITS_UND_SOZIAL_VERHALTEN_ID";
    public static final String C_BEURTEILUNG = "BEURTEILUNG";
    public static final String C_ID = "ID";
    public static final String C_VERSION = "VERSION";
    public static final String C_ZEUGNIS_ID = "ZEUGNIS_ID";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, C_BEURTEILUNG, C_ID, C_VERSION, C_ZEUGNIS_ID};

    public AvSvBewertungRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_ZEUGNIS_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, new Long("0"));
    }

    public final AvSvBewertungRowBuilder ArbeitsUndSozialVerhaltenId (Long value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AvSvBewertungRowBuilder ArbeitsUndSozialVerhaltenId (IValidator<?> value) {
        with(C_ARBEITS_UND_SOZIAL_VERHALTEN_ID, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Beurteilung (Integer value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Beurteilung (IValidator<?> value) {
        with(C_BEURTEILUNG, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final AvSvBewertungRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }

    public final AvSvBewertungRowBuilder ZeugnisId (Long value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }

    public final AvSvBewertungRowBuilder ZeugnisId (IValidator<?> value) {
        with(C_ZEUGNIS_ID, value);
        return this;
    }


    public static AvSvBewertungRowBuilder newAvSvBewertung() {
        return new AvSvBewertungRowBuilder(PRIMARY_KEY);
    }

    public static AvSvBewertungRowBuilder newAvSvBewertung(String... identifierColumns) {
        return new AvSvBewertungRowBuilder(identifierColumns);
    }

}
