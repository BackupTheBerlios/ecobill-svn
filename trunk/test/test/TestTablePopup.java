package test;

import javax.swing.*;
import javax.swing.event.MenuKeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Roman Georg Rädle
 * Date: 06.11.2005
 * Time: 02:08:33
 * To change this template use File | Settings | File Templates.
 */
public class TestTablePopup extends JFrame {

    public static void main(String[] args) {

        JTable table = new JTable(new String[][]{{"a", "b", "c"}, {"a", "b", "c"}, {"a", "b", "c"}}, new String[]{"a", "b", "c"});

        final JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("asdf");

        popupMenu.add(menuItem);

        table.add(popupMenu);

        table.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        TestTablePopup testTablePopup = new TestTablePopup();
        testTablePopup.getContentPane().add(table);
        testTablePopup.pack();
        testTablePopup.setVisible(true);
    }
}
