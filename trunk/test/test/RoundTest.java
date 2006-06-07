package test;

import ecobill.core.util.FileUtils;

import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoManager;
import javax.swing.*;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Roman Georg Rädle
 * Date: 30.09.2005
 * Time: 15:21:11
 * To change this template use File | Settings | File Templates.
 */
public class RoundTest {

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final UndoManager undomanager = new UndoManager();

        final JTextArea textarea = new JTextArea(20, 40);
        textarea.setText("Hier zurück");
        f.getContentPane().add(new JScrollPane(textarea));
        textarea.getDocument().addUndoableEditListener(undomanager);

        undomanager.setLimit(1000);

        JButton undoB = new JButton("Undo");

        undoB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //undomanager.end();

                if (undomanager.canUndo())
                    undomanager.undo();

                textarea.requestFocus();
            }
        });

        f.getContentPane().add(undoB, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
    }
}
