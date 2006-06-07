package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class JTableCheckBox extends JPanel {
  private boolean DEBUG = false;

  public JTableCheckBox() {
    super(new BorderLayout());

    JTable table = new JTable(new MyTableModel()) {

    };

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(700, 300));

    add(scrollPane);
  }

  class MyTableModel extends AbstractTableModel {
    private String[] columnNames = { "Name", "Tag", "Von",
        "Bis", "Projekt", "Aufgabe", "Task-Nr.", "Beschreibung",
        "Stunden", "Summe", "freigeben" };

    private Object[][] data = {

    	{ "","","","","","", new Integer(0),"","","", new Boolean(false) },
    	{ "","","","","","", new Integer(0),"","","", new Boolean(false) },
    	{ "","","","","","", new Integer(0),"","","", new Boolean(false) },
    	{ "","","","","","", new Integer(0),"","","", new Boolean(false) },
    	{ "","","","","","", new Integer(0),"","","", new Boolean(false) },};

    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {

      if (col < 0) {
        return false;
      } else {
        return true;
      }
    }

    public void setValueAt(Object value, int row, int col) {
      if (DEBUG) {
        System.out.println("Setting value at " + row + "," + col
            + " to " + value + " (an instance of "
            + value.getClass() + ")");
      }

      data[row][col] = value;
      fireTableCellUpdated(row, col);

      if (DEBUG) {
        System.out.println("New value of data:");
        printDebugData();
      }
    }

    private void printDebugData() {
      int numRows = getRowCount();
      int numCols = getColumnCount();

      for (int i = 0; i < numRows; i++) {
        System.out.print("    row " + i + ":");
        for (int j = 0; j < numCols; j++) {
          System.out.print("  " + data[i][j]);
        }
        System.out.println();
      }
      System.out.println("--------------------------");
    }
}
  private static void createAndShowGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);

    JFrame frame = new JFrame("TableDemo");
    frame.setLocation(200,300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JComponent newContentPane = new JTableCheckBox();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}