package net.sf.sze.dbunit.dataset;

import static java.lang.Boolean.FALSE;
import static net.sf.sze.dbunit.rowbuilder.SchulhalbjahrRowBuilder.newSchulhalbjahr;
import static org.dbunit.dataset.builder.ObjectFactory.d;
import static org.dbunit.validator.Validators.gt;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;


/**
 * Testdaten für die Schulhalbjahr Initialisierung.
 * Class SchulhalbjahrInit
 *
 */
@SuppressWarnings("boxing")
public class SchulhalbjahrInit {


    public static IDataSet buildInitErstesHalbjahr() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();

        return b.build();
    }

    public static IDataSet buildResultErstesHalbjahr() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newSchulhalbjahr().Id(gt(0)).Version(0L).Halbjahr(0).Jahr(2014).Selectable(FALSE).addTo(b);
        return b.build();
    }


    public static IDataSet buildInitZweitesHalbjahr() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newSchulhalbjahr().Id(1L).Version(0L).Halbjahr(0).Jahr(2014).Selectable(FALSE).NachteilsAusgleichsDatum(d("2014-08-13")).addTo(b);
        return b.build();
    }


    public static IDataSet buildResultZweitesHalbjahr() throws DataSetException {
        final DataSetBuilder b = new DataSetBuilder();
        newSchulhalbjahr().Id(1L).Version(0L).Halbjahr(0).Jahr(2014).Selectable(FALSE).NachteilsAusgleichsDatum(d("2014-08-13")).addTo(b);
        newSchulhalbjahr().Id(gt(1)).Version(0L).Halbjahr(1).Jahr(2014).Selectable(FALSE).NachteilsAusgleichsDatum(d("2014-08-13")).addTo(b);
        return b.build();
    }


}

