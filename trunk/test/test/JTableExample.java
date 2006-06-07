package test;

/*
 * Created on 07.02.2005@10:19:11
 *
 * TODO Licence info
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
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
 *
 * TODO Explain me
 */
public class JTableExample extends JFrame {

    private JTable table;

    private FooTableModel model;

    private final ImageIcon UP_ICON = new ImageIcon(MotifGraphicsUtils.class
            .getResource("icons/ScrollUpArrow.gif"));

    private ImageIcon DOWN_ICON = new ImageIcon(MotifGraphicsUtils.class
            .getResource("icons/ScrollDownArrow.gif"));

    public JTableExample() {
        super("JTableExample");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Object[][] rowData = { { "a", "c" }, { "b", "b" }, { "f", "r" },{ "q", "a" },{ "w", "z" },{ "c", "a" } };
        Object[] columnHeaders = { "Header1", "Header2" };
        model = new FooTableModel(rowData, columnHeaders);

        table = new JTable(model);

        final GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 0.0F);
        path.lineTo(7.5F, 7.5F);
        path.lineTo(15.0F, 0.0F);
        path.closePath();

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
                        } else {
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

        Container c = getContentPane();
        c.add(new JScrollPane(table), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new JTableExample();
    }

    class FooTableModel extends DefaultTableModel {

        private boolean[] sortColumnDesc;

        public FooTableModel(Object[][] rowData, Object[] headers) {
            super(rowData, headers);
            sortColumnDesc = new boolean[headers.length];
        }

        public void sortByColumn(final int clm) {
            Collections.sort(this.dataVector, new Comparator() {
                public int compare(Object o1, Object o2) {
                    Vector v1 = (Vector) o1;
                    Vector v2 = (Vector) o2;

                    int size1 = v1.size();
                    if (clm >= size1)
                        throw new IllegalArgumentException("max column idx: "
                                + size1);

                    Comparable c1 = (Comparable) v1.get(clm);
                    Comparable c2 = (Comparable) v2.get(clm);

                    int cmp = c1.compareTo(c2);

                    if (sortColumnDesc[clm]) {
                        cmp *= -1;
                    }

                    return cmp;
                }
            });
            model.sortColumnDesc[clm] ^= true;
        }
    }
}