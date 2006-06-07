import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TableStore extends JFrame {

    JTable table;

    public TableStore() {
        super("JTableStoreExample");
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(
                            new FileOutputStream("artmod.ebd"));
                    oos.writeObject(table.getColumnModel());
                    oos.flush();
                    oos.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        DefaultTableModel model = new DefaultTableModel(1, 4);

        try {
            table = new JTable();

            TableColumnModel colModel = loadTableColumnModel();

            model.setDataVector(new String[][]{{"w", "x", "y", "z"}, {"w", "x", "y", "z"}, {"w", "x", "y", "z"}, {"w", "x", "y", "z"}}, new String[]{"w", "x", "y", "z"});

            table.setModel(model);
            table.setColumnModel(colModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            table = new JTable(model);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private TableColumnModel loadTableColumnModel() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("artmod.ebd"));
        try {
            return (TableColumnModel) ois.readObject();
        }
        finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    public static void main(String[] args) {
        new TableStore();
    }
}