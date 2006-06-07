/**
 *
 */
package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Tom
 */
public class FilteredJTableExample extends JFrame {

    FilterableJTable table;
    JTextField txtFilter
    ,
    txtA
    ,
    txtB;
    JButton btnAddRow;
    JComboBox cboSelectFilterColumn;
    final static String DEFAULT_FILTER = "^#.*";
    JPopupMenu popupMenu;

    public FilteredJTableExample() {
        super("FilteredJTableExample");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        popupMenu = createPopupMenu();

        table = new FilterableJTable(createFilterableTableModel());
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Point point = e.getPoint();
                    popupMenu.show(table, point.x, point.y);
                }
            }
        });

        txtFilter = new JTextField(20);

        txtFilter.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                table.setFilter(DEFAULT_FILTER
                        .replace("#", txtFilter.getText()));
                table.updateUI();
            }
        });

        cboSelectFilterColumn = new JComboBox(createHeaderNames());
        cboSelectFilterColumn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.setFilterColumn(cboSelectFilterColumn.getSelectedIndex());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel addRowPanel = new JPanel(new GridLayout(1, 5));
        txtA = new JTextField(5);
        txtB = new JTextField(5);
        btnAddRow = new JButton("add");
        btnAddRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String strA = txtA.getText();
                String strB = txtB.getText();
                if (!"".equals(strA) && !"".equals(strB)) {
                    table.addRow(new Object[]{strA, strB});
                    table.updateUI();
                    txtA.setText("");
                    txtB.setText("");
                }
            }
        });
        addRowPanel.add(new JLabel("A:"));
        addRowPanel.add(txtA);
        addRowPanel.add(new JLabel("B:"));
        addRowPanel.add(txtB);
        addRowPanel.add(btnAddRow);

        add(addRowPanel, BorderLayout.NORTH);
        add(cboSelectFilterColumn, BorderLayout.WEST);
        add(txtFilter, BorderLayout.EAST);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    JPopupMenu createPopupMenu() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("delete");
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(FilteredJTableExample.this,
                                                  "No row selected!", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("removeRow @idx: " + selectedRow);
                table.deleteRow(selectedRow);
                table.updateUI();
            }
        });
        menu.add(deleteItem);
        return menu;
    }

    private TableModel createFilterableTableModel() {
        return new DefaultTableModel(createRowData(), createHeaderNames());
    }

    private Object[][] createRowData() {
        return new Object[][]{{"aaa", "121"}, {"aab", "111"}, {"abc", "123"},
                              {"xabc", "4123"}};
    }

    private Object[] createHeaderNames() {
        return new Object[]{"A", "B"};
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new FilteredJTableExample();
    }

    static class FilterableJTable extends JTable {

        public FilterableJTable(TableModel model) {
            super(new FilterableTableModel(model));
        }

        public void deleteRow(int selectedRow) {
            ((FilterableTableModel) this.getModel()).deleteRow(selectedRow);
        }

        public void setFilterColumn(int filterColumn) {
            ((FilterableTableModel) this.getModel())
                    .setFilterColumn(filterColumn);
        }

        public int getFilterColumn() {
            return ((FilterableTableModel) this.getModel()).getFilterColumn();
        }

        public void setFilter(String filter) {
            ((FilterableTableModel) this.getModel()).setFilter(filter);
        }

        public String getFilter() {
            return ((FilterableTableModel) this.getModel()).getFilter();
        }

        public void addRow(Object[] rowColumns) {
            ((FilterableTableModel) this.getModel()).addRow(rowColumns);
        }
    }

    static class FilterableTableModel extends AbstractTableModel {

        TableModel delegateTableModel;
        int[] filterMapping;
        String filter;
        int filterColumn;
        int filterMatchCount;

        public FilterableTableModel(TableModel delegateTableModel) {
            this.delegateTableModel = delegateTableModel;
        }

        public void addRow(Object[] rowColumns) {
            try {
                Method addRowMethod = delegateTableModel.getClass()
                        .getDeclaredMethod("addRow",
                                           new Class[]{Object[].class});
                if (addRowMethod != null) {
                    addRowMethod.invoke(delegateTableModel,
                                        new Object[]{rowColumns});
                }
            }
            catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }

        }

        public int getRowCount() {
            if (isFilterEnabled()) {
                doFilter();
            }

            return this.filterMapping != null
                   ? this.filterMapping.length
                   : this.delegateTableModel.getRowCount();
        }

        public int getColumnCount() {
            return delegateTableModel.getColumnCount();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            int actualRowIndex = rowIndex;
            if (isFilterEnabled()) {
                actualRowIndex = this.filterMapping[rowIndex];
            }
            return delegateTableModel.getValueAt(actualRowIndex, columnIndex);
        }

        public int[] getFilterMapping() {
            return filterMapping;
        }

        public void setFilterMapping(int[] filterMapping) {
            this.filterMapping = filterMapping;
        }

        public TableModel getDelegateTableModel() {
            return delegateTableModel;
        }

        public void setDelegateTableModel(TableModel delegate) {
            this.delegateTableModel = delegate;
        }

        public void deleteRow(int selectedRow) {
            try {
                Method deleteRowMethod = delegateTableModel.getClass()
                        .getDeclaredMethod("removeRow", new Class[]{int.class});
                if (deleteRowMethod != null) {
                    deleteRowMethod.invoke(delegateTableModel,
                                           new Object[]{Integer.valueOf(selectedRow)});
                }
            }
            catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }

        private void doFilter() {
            filterMatchCount = 0;
            int rowCount = delegateTableModel.getRowCount();
            int[] tmpMapping = new int[rowCount];

            for (int i = 0; i < rowCount; i++) {
                if (delegateTableModel.getValueAt(i, filterColumn).toString()
                        .matches(this.filter)) {
                    tmpMapping[filterMatchCount++] = i;
                }
            }

            int[] currentFilterMapping = new int[filterMatchCount];
            System.arraycopy(tmpMapping, 0, currentFilterMapping, 0,
                             filterMatchCount);
            setFilterMapping(currentFilterMapping);
        }

        public int getFilterMatchCount() {
            return filterMatchCount;
        }

        public void setFilterMatchCount(int filterMatches) {
            this.filterMatchCount = filterMatches;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        boolean isFilterEnabled() {
            return getFilter() != null && !"".equals(getFilter());
        }

        public int getFilterColumn() {
            return filterColumn;
        }

        public void setFilterColumn(int filterColumn) {
            this.filterColumn = filterColumn;
        }
    }
}