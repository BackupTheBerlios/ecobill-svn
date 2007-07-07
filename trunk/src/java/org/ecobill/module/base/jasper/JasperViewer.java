package org.ecobill.module.base.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.awt.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ecobill.core.ui.MainFrame;

/**
 * Der <code>JasperViewer</code> bietet die M�glichkeit einen Report auf einem <code>JPanel</code>
 * anzuzeigen. Der Viewer enth�lt auch Operationen wie drucken, vergr��ern,... eines Reports.
 * <p/>
 * User: Paul Chef
 * Date: 13.08.2005
 * Time: 12:43:59
 *
 * @author Andreas Weiler
 * @version $Id: JasperViewer.java,v 1.7 2005/12/11 17:16:01 raedler Exp $
 * @since EcoBill 1.0
 */
public class JasperViewer {

    /**
     * In diesem <code>Log</code> k�nnen Fehler, Info oder sonstige Ausgaben erfolgen.
     * Diese Ausgaben k�nnen in einem separaten File spezifiziert werden.
     */
    private static final Log LOG = LogFactory.getLog(JasperViewer.class);

    /**
     * JPanel auf das der JRViewer gelegt wird.
     */
    private JPanel viewerPanel;

    /**
     * Der <code>JRViewer</code> der alle Optionen wie Vergr��erung, Drucken, etc.
     * enth�lt.
     */
    private JRViewer viewer;

    /**
     * Diese <code>Map</code> enth�lt Parameter die an die Jasper Engine �bergeben werden und
     * zur Anzeige des Reports ben�tigt werden.
     */
    private Map<Object, Object> parameters;

    /**
     * Erzeugt einen neuen <code>JasperViewer</code>, der als Parameter ein <code>JPanel</code>
     * erh�lt, auf dem dieser Viewer angezeigt werden soll.
     *
     * @param viewerPanel Das <code>JPanel</code> auf dem der Viewer angezeigt werden soll.
     */
    public JasperViewer(JPanel viewerPanel) {
        this.viewerPanel = viewerPanel;
        this.parameters = new HashMap<Object, Object>();
    }

    /**
     * Gibt die <code>Map</code>, die f�r den Report evtl. ben�tigte Parameter enth�lt,
     * zur�ck.
     *
     * @return Eine <code>Map</code> die f�r den Report evtl. Parameter enth�lt.
     */
    public Map<Object, Object> getParameters() {
        return parameters;
    }

    /**
     * Setzt eine <code>Map</code>, die f�r den Report evtl. ben�tigte Parameter enthalten kann.
     *
     * @param parameters Eine <code>Map</code> die f�r den Report evtl. Parameter enthalten kann.
     */
    public void setParameters(Map<Object, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * F�gt der Parameter <code>Map</code> einen Wert hinzu.
     *
     * @param key   Der Schl�ssel f�r diesen Wert.
     * @param value Der Wert der unter dem Schl�ssel gespeichert werden soll.
     */
    public void addParameter(Object key, Object value) {
        parameters.put(key, value);
    }

    /**
     * Entfernt einen Parameter aus der <code>Map</code> und gibt diesen noch
     * zur�ck.
     *
     * @param key Der Schl�ssel dessen Wert gel�scht werden soll.
     * @return Der Wert der unter diesem Schl�ssel gefunden und gel�scht wurde.
     */
    public Object removeParameter(Object key) {
        return parameters.remove(key);
    }

    /**
     * Zeigt den <code>JRViewer</code> auf dem <code>JPanel</code> an, das beim Erzeugen des
     * <code>JasperViewer</code> �bergeben wurde.
     *
     * @param jrxmlFilename Der Dateiname der spezifischen jrxml Datei.
     * @param datasets      Eine <code>Collection</code> die alle dynamischen Datens�tze, die im
     *                      Report angezeigt werden sollen, enth�lt.
     * @throws Exception Diese wird geworfen wenn ein Fehler w�hrend des erzeugens auftreten
     *                   sollte.
     */
    public void view(MainFrame mainFrame, String jrxmlFilename, Collection<Object> datasets) throws Exception {

        try {

            JasperReport report = JasperCompileManager.compileReport(jrxmlFilename);

            mainFrame.setProgressPercentage(60);

            JasperDataSource dataSource = new JasperDataSource(datasets);

            mainFrame.setProgressPercentage(70);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            mainFrame.setProgressPercentage(80);

            viewer = new JRViewer(print);

            viewerPanel.add(viewer, BorderLayout.CENTER);
            viewerPanel.validate();

            mainFrame.setProgressPercentage(90);
        }
        catch (JRException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Entfernt den <code>JasperViewer</code>, von diesem als Parameter �bergebenen
     * <code>JPanel</code>.
     */
    public void remove() {
        viewerPanel.remove(viewer);
        viewerPanel.repaint();
    }
}
