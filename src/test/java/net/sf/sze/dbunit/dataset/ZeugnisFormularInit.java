//ZeugnisFormularInit.java
//
// Licensed under the AGPL - http://www.gnu.org/licenses/agpl-3.0.txt
// (c) SZE-Development Team
package net.sf.sze.dbunit.dataset;

import static net.sf.sze.dbunit.rowbuilder.KlasseRowBuilder.newKlasse;
import static net.sf.sze.dbunit.rowbuilder.SchulhalbjahrRowBuilder.newSchulhalbjahr;
import static net.sf.sze.dbunit.rowbuilder.ZeugnisFormularRowBuilder.newZeugnisFormular;

import java.sql.Date;

import net.sf.sze.service.api.zeugnis.ZeugnisFormularService;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;


/**
 * Testdaten für den {@link ZeugnisFormularService}.
 *
 */
@SuppressWarnings("boxing")
public class ZeugnisFormularInit {


    /**
     * Comment for <code>QUELLE_LEITSPRUCH2_COPIED</code>
     */
    private static final String QUELLE_LEITSPRUCH2_COPIED = "Spontane Idee";
    /**
     * Comment for <code>LEITSPRUCH2_COPIED</code>
     */
    private static final String LEITSPRUCH2_COPIED = "Nur Bahnfahren ist schöner";
    /**
     * Comment for <code>QUELLE_LEITSPRUCH1_COPIED</code>
     */
    private static final String QUELLE_LEITSPRUCH1_COPIED = "Aus dem Kopf";
    /**
     * Comment for <code>LEITSPRUCH1_COPIED</code>
     */
    private static final String LEITSPRUCH1_COPIED = "Ein besser Grund kann nicht gelegt werden";
    /**
     * Comment for <code>TEMPLATE_FILENAME_COPIED</code>
     */
    private static final String TEMPLATE_FILENAME_COPIED = "SollteKopiertWerden";

    public static IDataSet buildInitZeugnisFormular1Hj() throws DataSetException {
        final DataSetBuilder b = createInitZeugnisFormular1HJBuilder();
        return b.build();
    }

    private static DataSetBuilder createInitZeugnisFormular1HJBuilder()
            throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newKlasse().Id(1L).Version(0L).Geschlossen(Boolean.TRUE).Jahrgang(2006).Suffix("c").addTo(b);
        newKlasse().Id(2L).Version(0L).Geschlossen(Boolean.FALSE).Jahrgang(2007).Suffix("a").addTo(b);

        newSchulhalbjahr().Id(1L).Version(0L).Halbjahr(0).Jahr(2013).Selectable(Boolean.FALSE).addTo(b);
        newSchulhalbjahr().Id(2L).Version(0L).Halbjahr(1).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);

        newSchulhalbjahr().Id(3L).Version(0L).Halbjahr(0).Jahr(2014).Selectable(Boolean.TRUE).addTo(b);

        newZeugnisFormular().Id(1L).Version(0L).AusgabeDatum(Date.valueOf("2013-01-31")).Beschreibung("2013 erstes Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName("egal1").KlasseId(1L).SchulhalbjahrId(1L).addTo(b);
        newZeugnisFormular().Id(2L).Version(0L).AusgabeDatum(Date.valueOf("2013-07-01")).Beschreibung("2013 zweites Halbjahr").NachteilsAusgleichsDatum(Date.valueOf("2012-09-14")).TemplateFileName(TEMPLATE_FILENAME_COPIED).KlasseId(1L).SchulhalbjahrId(2L).addTo(b);
        return b;
    }

    public static IDataSet buildErgebnisInit1HjDataSet() throws DataSetException {
        final DataSetBuilder b = createInitZeugnisFormular1HJBuilder();
        newZeugnisFormular().Id(3L).Version(0L)
                .Beschreibung("2013-14/Hj-1/Kl-7a").TemplateFileName(TEMPLATE_FILENAME_COPIED)
                .KlasseId(2L).SchulhalbjahrId(3L).addTo(b);
        return b.build();

    }

    public static IDataSet buildInitZeugnisFormular2Hj() throws DataSetException {
        final DataSetBuilder b = createInitZeugnisFormular2HJBuilder();
        return b.build();
    }

    public static IDataSet buildErgebnisInit2HjDataSet() throws DataSetException {
        final DataSetBuilder b = createInitZeugnisFormular2HJBuilder();

        newZeugnisFormular().Id(4L).Version(0L)
                .Beschreibung("2012-13/Hj-2/Kl-7c")
                .Leitspruch(LEITSPRUCH1_COPIED).Leitspruch2(LEITSPRUCH2_COPIED)
                .QuelleLeitspruch(QUELLE_LEITSPRUCH1_COPIED)
                .QuelleLeitspruch2(QUELLE_LEITSPRUCH2_COPIED)
                .TemplateFileName(TEMPLATE_FILENAME_COPIED).KlasseId(1L)
                .SchulhalbjahrId(2L).addTo(b);

        return b.build();
    }

    private static DataSetBuilder createInitZeugnisFormular2HJBuilder()
            throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newKlasse().Id(1L).Version(0L).Geschlossen(Boolean.FALSE).Jahrgang(2006).Suffix("c").addTo(b);
        newKlasse().Id(2L).Version(0L).Geschlossen(Boolean.TRUE).Jahrgang(2007).Suffix("a").addTo(b);

        newSchulhalbjahr().Id(1L).Version(0L).Halbjahr(0).Jahr(2013).Selectable(Boolean.FALSE).addTo(b);
        newSchulhalbjahr().Id(2L).Version(0L).Halbjahr(1).Jahr(2013).Selectable(Boolean.TRUE).addTo(b);

        //TODO Schulfachdetail-Infos ergänzen!!!!

        newZeugnisFormular().Id(1L).Version(0L)
                .AusgabeDatum(Date.valueOf("2013-01-31"))
                .Beschreibung("2013 erstes Halbjahr")
                .NachteilsAusgleichsDatum(Date.valueOf("2012-09-14"))
                .TemplateFileName(TEMPLATE_FILENAME_COPIED)
                .Leitspruch(LEITSPRUCH1_COPIED)
                .QuelleLeitspruch(QUELLE_LEITSPRUCH1_COPIED)
                .Leitspruch2(LEITSPRUCH2_COPIED)
                .QuelleLeitspruch2(QUELLE_LEITSPRUCH2_COPIED)
                .KlasseId(1L).SchulhalbjahrId(1L)
                .addTo(b);


        return b;
    }

}
