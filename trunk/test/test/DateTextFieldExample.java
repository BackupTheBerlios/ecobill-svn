package test;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

/**
 * @author Tom
 *
 */
public class DateTextFieldExample extends JFrame {

    JFormattedTextField jtf = null;
    public DateTextFieldExample() {
        super("MaskFormatterExample");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            jtf = new JFormattedTextField(new MaskFormatter("##.##.####") {
                {
                    setPlaceholderCharacter('#');
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

        add(jtf);

        pack();
        setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DateTextFieldExample();
    }
}