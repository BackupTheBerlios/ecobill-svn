package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.sun.java.swing.plaf.motif.MotifGraphicsUtils;

/**
 * @author Administrator
 *         <p/>
 *         TODO Explain me
 */
public class SortableJTableExample extends JFrame {

    private JTable table;

    private SortableTableModel model;

    private final ImageIcon UP_ICON = new ImageIcon(MotifGraphicsUtils.class
            .getResource("icons/ScrollUpArrow.gif"));

    private ImageIcon DOWN_ICON = new ImageIcon(MotifGraphicsUtils.class
            .getResource("icons/ScrollDownArrow.gif"));

    public SortableJTableExample() {
        super("SortableJTableExample");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Object[][] rowData = {{Integer.valueOf(13), "a", "c"},
                              {Integer.valueOf(2), "b", "b"},
                              {Integer.valueOf(3), "f", "r"},
                              {Integer.valueOf(12), "q12", "a12"},
                              {Integer.valueOf(5), "w", "z"},
                              {Integer.valueOf(6), "c", "a"},
                              {Integer.valueOf(11), "a11", "q11"},
                              {Integer.valueOf(8), "a8", "q8"},
                              {Integer.valueOf(9), "a9", "q9"},
                              {Integer.valueOf(10), "a10", "q10"},
                              {Integer.valueOf(7), "a7", "q7"},
                              {Integer.valueOf(4), "a4", "q4"},
                              {Integer.valueOf(1), "a1", "q1"}};

        final Object[] columnHeaders = {"ID", "Header1", "Header2"};

        model = new SortableTableModel(rowData, columnHeaders);
        table = new JTable(model);

        table.getTableHeader().setDefaultRenderer(
                new DefaultTableCellRenderer() {

                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {

                        JTableHeader header = table.getTableHeader();
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());

                        setText(value == null ? "" : value.toString());
                        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                        setHorizontalAlignment(SwingConstants.CENTER);
                        setHorizontalTextPosition(SwingConstants.LEFT);

                        if (model.sortColumnDesc[column]) {
                            setIcon(UP_ICON);
                        }
                        else {
                            setIcon(DOWN_ICON);
                        }

                        return this;
                    }
                });

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                model.sortByColumn(table.columnAtPoint(evt.getPoint()));
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component component = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (column == model.currentSortColumn) {
                    component
                            .setFont(component.getFont().deriveFont(Font.BOLD));
                }

                return component;
            }
        });

        Container c = getContentPane();
        c.add(new JScrollPane(table), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new SortableJTableExample();
    }

    class SortableTableModel extends DefaultTableModel {

        private boolean[] sortColumnDesc;

        private int currentSortColumn = 0;

        private Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                Vector v1 = (Vector) o1;
                Vector v2 = (Vector) o2;

                int columnCnt = getColumnCount();
                if (currentSortColumn >= columnCnt) {
                    throw new IllegalArgumentException("max column idx: "
                                                       + columnCnt);
                }

                Comparable c1 = (Comparable) v1.get(currentSortColumn);
                Comparable c2 = (Comparable) v2.get(currentSortColumn);

                int cmp = c1.compareTo(c2);

                if (sortColumnDesc[currentSortColumn]) {
                    cmp *= -1;
                }

                return cmp;
            }
        };

        public SortableTableModel(Object[][] rowData, Object[] headers) {
            super(rowData, headers);
            sortColumnDesc = new boolean[headers.length];
        }

        public void sortByColumn(final int clm) {
            currentSortColumn = clm;

            Collections.sort(SortableJTableExample.this.model.dataVector,
                             comparator);
            model.sortColumnDesc[clm] ^= true;
        }
    }
}