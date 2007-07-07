package org.ecobill.module.base.jasper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.Collection;

import ognl.Ognl;
import ognl.OgnlException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Die <code>JasperDataSource</code> ist eine f�r EcoBill allgemeine DataSource, die in der Lage
 * ist eine <code>Collection</code> aufzunehmen und diese im Report auszugeben.
 * <p/>
 * User: basti
 * Date: 26.09.2005
 * Time: 15:54:10
 *
 * @author Sebastian Gath
 * @version $Id: JasperDataSource.java,v 1.3 2005/11/07 21:49:30 raedler Exp $
 * @since EcoBill 1.0
 */
public class JasperDataSource implements JRDataSource {

    /**
     * In diesem <code>Log</code> k�nnen Fehler, Info oder sonstige Ausgaben erfolgen.
     * Diese Ausgaben k�nnen in einem separaten File spezifiziert werden.
     */
    private static final Log LOG = LogFactory.getLog(JasperViewer.class);

    /**
     * Diese <code>Collection</code> beinhaltet die einzelnen Datens�tze die im Report
     * angezeigt werden sollen.
     */
    private Collection<Object> datasets;

    /**
     * Der <code>Iterator</code> wird beim Erzeugen der DataSource oder beim neuen Setzen
     * der Datens�tze aus der <code>Collection</code> geholt und ist somit in der Lage
     * �ber alle Elemente der <code>Collection</code> zu iterieren.
     */
    private Iterator iterator;

    /**
     * Ein Objekt aus der <code>Collection</code>, d.h. es entspricht einem Datensatz
     * des Reports.
     */
    private Object dataset;

    /**
     * Erzeugt eine neue <code>JasperDataSource</code>, die alle Datens�tze in einer
     * <code>Collection</code> bereitstellt.
     *
     * @param datasets Die Datens�tze die im Report ausgegeben werden sollen.
     */
    public JasperDataSource(Collection<Object> datasets) {
        this.datasets = datasets;
        this.iterator = this.datasets.iterator();
    }

    /**
     * Gibt die <code>Collection</code>, die alle Datens�tze des Reports
     * enth�lt, zur�ck.
     *
     * @return Die <code>Collection</code> die alle Datens�tze enth�lt.
     */
    public Collection<Object> getDatasets() {
        return datasets;
    }

    /**
     * Setzt eine <code>Collection</code>, die alle Datens�tze enth�lt, die im
     * Report ausgegeben werden sollen.
     *
     * @param datasets Die <code>Collection</code> die alle Datens�tze enth�lt.
     */
    public void setDatasets(Collection<Object> datasets) {
        this.datasets = datasets;
        this.iterator = this.datasets.iterator();
    }

    /**
     * @see JRDataSource#next()
     */
    public boolean next() throws JRException {

        if (iterator.hasNext()) {
            dataset = iterator.next();
            return true;
        }
        return false;
    }

    /**
     * @see JRDataSource#getFieldValue(JRField)
     */
    public Object getFieldValue(JRField jrField) throws JRException {

        // Der Name des Feldes im Report.
        String fieldName = jrField.getName();

        try {
            return Ognl.getValue(fieldName, dataset);
        }
        catch (OgnlException ognle) {

            if (LOG.isErrorEnabled()) {
                LOG.error("Konnte die Property [\"" + fieldName + "\"] in der Klasse [\"" + dataset.getClass().getName() + "\"] nicht finden.", ognle);
            }

            Class clazz = jrField.getValueClass();

            try {
                return clazz.newInstance();
            }
            catch (InstantiationException ie) {
                if (LOG.isErrorEnabled()) {
                    LOG.error("Konnte keine Instanz der Klasse [\"" + clazz.getName() + "\"] erzeugen.", ie);
                }
            }
            catch (IllegalAccessException iae) {
                if (LOG.isErrorEnabled()) {
                    LOG.error("Konnte keine Instanz der Klasse [\"" + clazz.getName() + "\"] erzeugen.", iae);
                }
            }
        }

        throw new JRException("Konnte Report nicht erstellen.");
    }
}
