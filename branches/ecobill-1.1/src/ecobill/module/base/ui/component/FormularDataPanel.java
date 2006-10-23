package ecobill.module.base.ui.component;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Date;
import java.awt.*;

import ecobill.core.system.Internationalization;
import ecobill.core.system.WorkArea;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

// @todo document me!

/**
 * FormularDataPanel.
 * <p/>
 * User: rro
 * Date: 10.12.2005
 * Time: 20:46:47
 *
 * @author Roman R&auml;dle
 * @version $Id: FormularDataPanel.java,v 1.3 2006/02/02 22:18:27 raedler Exp $
 * @since EcoBill 1.1
 */
public class FormularDataPanel extends JPanel implements Internationalization {

    // The <code>Icon</code> for the <code>JDateChooser</code>.
    private final ImageIcon ICON_DATE_CHOOSE = new ImageIcon("images/component/date_choose.gif");

    /**
     * Die Konstante f�r den <code>JPanel</code> Rahmen.
     */
    private final String BORDER_TITLE_KEY;

    /**
     * Die Konstante f�r den Nummern <code>JLabel</code> Text.
     */
    private final String NUMBER_LABEL_TEXT;

    /**
     * Die Konstante f�r den Datum <code>JLabel</code> Text.
     */
    private final String DATE_LABEL_TEXT;

    /**
     * Der Rahmen der im <code>JPanel</code> liegt.
     */
    private TitledBorder border;

    /**
     * Das <code>JLabel</code> f�r das Datum.
     */
    private JLabel dateL = new JLabel();

    /**
     * The <code>JDateChooser</code> shows a date choosing panel.
     */
    private JCalendar calendar = new JCalendar(new Date(), true);
    private JDateChooser dateChooser = new JDateChooser(calendar, "dd.MM.yyyy", false, ICON_DATE_CHOOSE);

    /**
     * Das <code>JLabel</code> f�r die Formularnummer.
     */
    private JLabel numberL = new JLabel();

    /**
     * Das <code>JTextField</code> f�r Formularnummer Angaben.
     */
    private JTextField numberTF = new JTextField();

    /**
     * TODO: document me!!!
     */
    public FormularDataPanel(final String BORDER_TITLE_KEY, final String NUMBER_LABEL_TEXT, final String DATE_LABEL_TEXT) {

        // Setzt den Konstanten
        this.BORDER_TITLE_KEY = BORDER_TITLE_KEY;
        this.NUMBER_LABEL_TEXT = NUMBER_LABEL_TEXT;
        this.DATE_LABEL_TEXT = DATE_LABEL_TEXT;

        // Initialisiert den Rahmen zum ersten Mal.
        border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), WorkArea.getMessage(BORDER_TITLE_KEY), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 11), new Color(0, 0, 0));

        // Initialisiert die Komponenten.
        initComponents();

        // Initialisiert das Layout.
        initLayout();

        reinitI18N();
    }

    /**
     * TODO: document me!!!
     */
    private void initComponents() {

        // Setzt den Rahmen f�r das <code>JPanel</code>.
        setBorder(border);

        dateChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
        dateChooser.setMinimumSize(new Dimension(100, 20));
        dateChooser.setPreferredSize(new Dimension(100, 20));
    }

    private void initLayout() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.LEADING)
                        .add(GroupLayout.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(GroupLayout.LEADING, false)
                                .add(numberL)
                                .add(GroupLayout.TRAILING, dateL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(numberTF, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.LEADING)
                        .add(GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(numberL)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(numberTF, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(dateL)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(dateChooser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * @see ecobill.core.system.Internationalization#reinitI18N()
     */
    public void reinitI18N() {

        // Erneuert den Titel des <code>JPanel</code> in die landesspezifisch
        // eingestellte Sprache.
        border.setTitle(WorkArea.getMessage(BORDER_TITLE_KEY));

        // Erneuert den Text der <code>JLabel</code> in den landesspezifischen
        // eingestellten Texten.
        numberL.setText(WorkArea.getMessage(NUMBER_LABEL_TEXT));
        dateL.setText(WorkArea.getMessage(DATE_LABEL_TEXT));
    }

    /**
     * TODO: document me!!!
     *
     * @return
     */
    public Date getDate() {
        return (Date) dateChooser.getDate();//getValue();
    }

    /**
     * TODO: document me!!!
     *
     * @param date
     */
    public void setDate(Date date) {
        this.dateChooser.setDate(date);//setValue(date);
    }

    /**
     * TODO: document me!!!
     *
     * @return
     */
    public String getNumber() {
        return numberTF.getText();
    }

    /**
     * TODO: document me!!!
     *
     * @param number
     */
    public void setNumber(String number) {
        this.numberTF.setText(number);
    }
}
