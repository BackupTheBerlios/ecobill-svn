package test;

import ecobill.module.base.ui.component.AbstractTablePanel;
import ecobill.module.base.service.impl.BaseServiceImpl;
import ecobill.core.util.I18NItem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Vector;
import java.util.Collection;
import java.util.LinkedList;

// TODO: document me!!!

/**
 * TestSortableAbstractTable.
 * <p/>
 * User: raedler
 * Date: 09.12.2005
 * Time: 21:27:37
 *
 * @author Roman R&auml;dle
 * @version $Id$
 * @since UniVis Explorer 1.0
 */
public class TestSortableAbstractTable extends JFrame {

    public TestSortableAbstractTable() throws HeadlessException {

        this.getContentPane().add(new AbstractTablePanel(new BaseServiceImpl(), true) {
            protected Border createPanelBorder() {
                return BorderFactory.createTitledBorder("TEST");
            }

            protected Vector<I18NItem> createTableColumnOrder() {

                Vector<I18NItem> vector = new Vector<I18NItem>();

                vector.add(new I18NItemModi("title"));
                vector.add(new I18NItemModi("firstname"));

                return vector;
            }

            protected Collection getDataCollection() {

                java.util.List<Object[]> collection = new LinkedList<Object[]>();

                collection.add(new Object[]{"aab", "1243"});
                collection.add(new Object[]{"aac", "3423"});
                collection.add(new Object[]{"ace", "232"});
                collection.add(new Object[]{"bdae", "124"});

                return collection;
            }

            protected Vector createLineVector(Object o) {

                Object[] obj = (Object[]) o;

                Vector<Object> line = new Vector<Object>();

                line.add(obj[0]);
                line.add(obj[1]);

                return line;
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        this.pack();
    }

    private class I18NItemModi extends I18NItem {

        public I18NItemModi(String key) {
            super(key);
        }

        public String toString() {
            return getKey();
        }
    }

    public static void main(String[] args) {
        new TestSortableAbstractTable();
    }
}
