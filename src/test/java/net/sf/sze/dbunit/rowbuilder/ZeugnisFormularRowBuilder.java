package net.sf.sze.dbunit.rowbuilder;

import org.dbunit.dataset.builder.BasicDataRowBuilder;
import org.dbunit.validator.IValidator;
import java.sql.Date;

public class ZeugnisFormularRowBuilder extends BasicDataRowBuilder {

    public static final String TABLE_NAME = "ZEUGNIS_FORMULAR";

    public static final String C_AUSGABE_DATUM = "AUSGABE_DATUM";
    public static final String C_BESCHREIBUNG = "BESCHREIBUNG";
    public static final String C_ID = "ID";
    public static final String C_KLASSE_ID = "KLASSE_ID";
    public static final String C_LEITSPRUCH = "LEITSPRUCH";
    public static final String C_LEITSPRUCH2 = "LEITSPRUCH2";
    public static final String C_NACHTEILS_AUSGLEICHS_DATUM = "NACHTEILS_AUSGLEICHS_DATUM";
    public static final String C_QUELLE_LEITSPRUCH = "QUELLE_LEITSPRUCH";
    public static final String C_QUELLE_LEITSPRUCH2 = "QUELLE_LEITSPRUCH2";
    public static final String C_SCHULHALBJAHR_ID = "SCHULHALBJAHR_ID";
    public static final String C_TEMPLATE_FILE_NAME = "TEMPLATE_FILE_NAME";
    public static final String C_VERSION = "VERSION";

    public static final String[] PRIMARY_KEY = {C_ID};

    public static final String[] ALL_COLUMNS = {C_AUSGABE_DATUM, C_BESCHREIBUNG, C_ID, C_KLASSE_ID, C_LEITSPRUCH, C_LEITSPRUCH2, C_NACHTEILS_AUSGLEICHS_DATUM, C_QUELLE_LEITSPRUCH, C_QUELLE_LEITSPRUCH2, C_SCHULHALBJAHR_ID, C_TEMPLATE_FILE_NAME, C_VERSION};

    public ZeugnisFormularRowBuilder(String... identifierColumns) {
        super(TABLE_NAME, identifierColumns);
        setAllColumnNames(ALL_COLUMNS);
        addDefaultValue(C_VERSION, new Long("0"));
        addDefaultValue(C_AUSGABE_DATUM, new Date(0));
        addDefaultValue(C_SCHULHALBJAHR_ID, new Long("0"));
        addDefaultValue(C_KLASSE_ID, new Long("0"));
        addDefaultValue(C_ID, new Long("0"));
        addDefaultValue(C_TEMPLATE_FILE_NAME, "");
        addDefaultValue(C_BESCHREIBUNG, "");
        addDefaultValue(C_NACHTEILS_AUSGLEICHS_DATUM, new Date(0));
    }

    public final ZeugnisFormularRowBuilder AusgabeDatum (Date value) {
        with(C_AUSGABE_DATUM, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder AusgabeDatum (IValidator<?> value) {
        with(C_AUSGABE_DATUM, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Beschreibung (String value) {
        with(C_BESCHREIBUNG, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Beschreibung (IValidator<?> value) {
        with(C_BESCHREIBUNG, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Id (Long value) {
        with(C_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Id (IValidator<?> value) {
        with(C_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder KlasseId (Long value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder KlasseId (IValidator<?> value) {
        with(C_KLASSE_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Leitspruch (String value) {
        with(C_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Leitspruch (IValidator<?> value) {
        with(C_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Leitspruch2 (String value) {
        with(C_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Leitspruch2 (IValidator<?> value) {
        with(C_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder NachteilsAusgleichsDatum (Date value) {
        with(C_NACHTEILS_AUSGLEICHS_DATUM, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder NachteilsAusgleichsDatum (IValidator<?> value) {
        with(C_NACHTEILS_AUSGLEICHS_DATUM, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder QuelleLeitspruch (String value) {
        with(C_QUELLE_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder QuelleLeitspruch (IValidator<?> value) {
        with(C_QUELLE_LEITSPRUCH, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder QuelleLeitspruch2 (String value) {
        with(C_QUELLE_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder QuelleLeitspruch2 (IValidator<?> value) {
        with(C_QUELLE_LEITSPRUCH2, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder SchulhalbjahrId (Long value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder SchulhalbjahrId (IValidator<?> value) {
        with(C_SCHULHALBJAHR_ID, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder TemplateFileName (String value) {
        with(C_TEMPLATE_FILE_NAME, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder TemplateFileName (IValidator<?> value) {
        with(C_TEMPLATE_FILE_NAME, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Version (Long value) {
        with(C_VERSION, value);
        return this;
    }

    public final ZeugnisFormularRowBuilder Version (IValidator<?> value) {
        with(C_VERSION, value);
        return this;
    }


    public static ZeugnisFormularRowBuilder newZeugnisFormular() {
        return new ZeugnisFormularRowBuilder(PRIMARY_KEY);
    }

    public static ZeugnisFormularRowBuilder newZeugnisFormular(String... identifierColumns) {
        return new ZeugnisFormularRowBuilder(identifierColumns);
    }

}
