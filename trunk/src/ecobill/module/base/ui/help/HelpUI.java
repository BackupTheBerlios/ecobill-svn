package ecobill.module.base.ui.help;

import ecobill.core.system.WorkArea;
import ecobill.core.system.Constants;

import javax.swing.*;
import java.awt.*;

// @todo document me!

/**
 * Die <code>HelpUI</code> erstellt das User Interface zur Hilfe.
 * <p/>
 * User: Andreas Weiler
 * Date: 21.10.2005
 * Time: 16:16:40
 *
 * @author Andreas Weiler
 * @version $Id: HelpUI.java,v 1.27 2006/01/30 23:43:14 raedler Exp $
 * @since EcoBill 1.0
 */
public class HelpUI extends JFrame {

    /**
     * Erstellt ein neues JFrame
     */
    public HelpUI() {

        initComponents();
        reinitI18N();
        center();
    }

    // Initialisierung aller Komponenten
    public JPanel imagePanel = new JPanel();
    public JLabel imageLabel = new JLabel(new ImageIcon("images/Topic.gif"));
    public HelpBrowser helpBrowser1 = new HelpBrowser("./html/help/help1.html");
    public HelpBrowser helpBrowser2 = new HelpBrowser("./html/help/help2.html");
    public HelpBrowser helpBrowser3 = new HelpBrowser("./html/help/help3.html");
    public HelpBrowser helpBrowser5 = new HelpBrowser("./html/help/help5.html");
    public HelpBrowser dOBrowser1 = new HelpBrowser("./html/help/do1.html");
    public HelpBrowser dOBrowser2 = new HelpBrowser("./html/help/do2.html");
    public HelpBrowser rBrowser1 = new HelpBrowser("./html/help/r1.html");
    public HelpBrowser rBrowser2 = new HelpBrowser("./html/help/r2.html");
    private JTabbedPane deliveryOrder = new JTabbedPane();
    private JTabbedPane billTabbedPane = new JTabbedPane();

    /**
     * Initialisiert die Komponenten
     */
    private void initComponents() {

        setIconImage(Toolkit.getDefaultToolkit().getImage("images/ico/currency_dollar.png"));
        helpTabbedPane = new javax.swing.JTabbedPane();

        // setzt den ToolTipText im HTML-Format
        imageLabel.setToolTipText("<html><body><p><b><font face=\"Tahoma\">Sebastian Gath:</font></b></p><ul><li><b><font face=\"Tahoma\">gath@inf.uni-konstanz.de</font></b></li></ul><p><b><font face=\"Tahoma\">Roman R�dle:</font></b></p><ul>  <li><b><font face=\"Tahoma\">raedler@inf.uni-konstanz.de</font></b></li></ul><p><b><font face=\"Tahoma\">Andreas Weiler:</font></b></p><ul>  <li><b><font face=\"Tahoma\">weiler@inf.uni-konstanz.de</font></b></li></ul></body></html>");
        imagePanel.add(imageLabel);

        // f�gt die einzelnen Panels den Tabs hinzu
        deliveryOrder.add(dOBrowser1);
        deliveryOrder.add(dOBrowser2);

        // setzt die Icons der einzelnen Tabs
        deliveryOrder.setIconAt(0, new ImageIcon("images/help.png"));
        deliveryOrder.setIconAt(1, new ImageIcon("images/help.png"));

        // f�gt die einzelnen Panels den Tabs hinzu
        billTabbedPane.add(rBrowser1);
        billTabbedPane.add(rBrowser2);

        // setzt die Icons der einzelnen Tabs
        billTabbedPane.setIconAt(0, new ImageIcon("images/help.png"));
        billTabbedPane.setIconAt(1, new ImageIcon("images/help.png"));

        // f�gt die einzelnen Panels den Tabs hinzu
        helpTabbedPane.add(imagePanel);
        helpTabbedPane.add(helpBrowser1);
        helpTabbedPane.add(helpBrowser2);
        helpTabbedPane.add(helpBrowser3);
        helpTabbedPane.add(deliveryOrder);
        helpTabbedPane.add(billTabbedPane);

        // setzt die Icons der einzelnen Tabs
        helpTabbedPane.setIconAt(0, new ImageIcon("images/help.png"));
        helpTabbedPane.setIconAt(1, new ImageIcon("images/help.png"));
        helpTabbedPane.setIconAt(2, new ImageIcon("images/help.png"));
        helpTabbedPane.setIconAt(3, new ImageIcon("images/help.png"));
        helpTabbedPane.setIconAt(4, new ImageIcon("images/help.png"));
        helpTabbedPane.setIconAt(5, new ImageIcon("images/help.png"));


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(helpTabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(helpTabbedPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap()));
        pack();
    }

    // Deklaration des JTabbedPane
    private javax.swing.JTabbedPane helpTabbedPane;

    // setzt das HilfeFrame in die Mitte
    private void center() {

        // Gr��e der eingestellten Bildschirmaufl�sung.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        // Gr��e des <code>JFrame</code>.
        Dimension frameSize = this.getSize();

        width -= frameSize.getWidth();
        height -= frameSize.getHeight();

        this.setLocation((int) width / 2, (int) height / 2);
    }

    // setzt die Titel der TabbedPanes
    public void reinitI18N() {

        setTitle(WorkArea.getMessage(Constants.HELP));

        deliveryOrder.setTitleAt(0, WorkArea.getMessage(Constants.DO_TABBED1));
        deliveryOrder.setTitleAt(1, WorkArea.getMessage(Constants.DO_TABBED2));

        billTabbedPane.setTitleAt(0, WorkArea.getMessage(Constants.DO_TABBED1));
        billTabbedPane.setTitleAt(1, WorkArea.getMessage(Constants.DO_TABBED2));

        helpTabbedPane.setTitleAt(0, WorkArea.getMessage(Constants.HELP_TABBED_PANE0));
        helpTabbedPane.setTitleAt(1, WorkArea.getMessage(Constants.HELP_TABBED_PANE1));
        helpTabbedPane.setTitleAt(2, WorkArea.getMessage(Constants.HELP_TABBED_PANE2));
        helpTabbedPane.setTitleAt(3, WorkArea.getMessage(Constants.HELP_TABBED_PANE3));
        helpTabbedPane.setTitleAt(4, WorkArea.getMessage(Constants.HELP_TABBED_PANE4));
        helpTabbedPane.setTitleAt(5, WorkArea.getMessage(Constants.HELP_TABBED_PANE5));

    }

}

