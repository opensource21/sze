package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import java.sql.Date;

public class SchulhalbjahrRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "SCHULHALBJAHR";

    public static final String C_AUSGABE_DATUM = "AUSGABE_DATUM";
    public static final String C_HALBJAHR = "HALBJAHR";
    public static final String C_ID = "ID";
    public static final String C_JAHR = "JAHR";
    public static final String C_NACHTEILS_AUSGLEICHS_DATUM = "NACHTEILS_AUSGLEICHS_DATUM";
    public static final String C_SELECTABLE = "SELECTABLE";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AUSGABE_DATUM, C_HALBJAHR, C_ID, C_JAHR, C_NACHTEILS_AUSGLEICHS_DATUM, C_SELECTABLE, C_VERSION};

    public SchulhalbjahrRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_JAHR, new Integer("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_SELECTABLE, Boolean.FALSE);
        addDefaultValue(C_HALBJAHR, new Integer("0"));
    }

    public final SchulhalbjahrRowBuilder AusgabeDatum (Date value) {
        with(C_AUSGABE_DATUM, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder Halbjahr (Number value) {
        with(C_HALBJAHR, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder Id (Number value) {
        with(C_ID, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder Jahr (Number value) {
        with(C_JAHR, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder NachteilsAusgleichsDatum (Date value) {
        with(C_NACHTEILS_AUSGLEICHS_DATUM, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder Selectable (Boolean value) {
        with(C_SELECTABLE, value);
        return this;
    }

    public final SchulhalbjahrRowBuilder Version (Number value) {
        with(C_VERSION, value);
        return this;
    }


    public static SchulhalbjahrRowBuilder newSchulhalbjahr() {
        return new SchulhalbjahrRowBuilder(PRIMARY_KEY);
    }

    public static SchulhalbjahrRowBuilder newSchulhalbjahr(String... identifierColumns) {
        return new SchulhalbjahrRowBuilder(identifierColumns);
    }

}
