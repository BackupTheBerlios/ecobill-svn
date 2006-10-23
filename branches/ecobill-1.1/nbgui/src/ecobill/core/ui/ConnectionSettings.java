/*
 * ConnectionSettings.java
 *
 * Created on 2. Februar 2006, 16:06
 */

package ecobill.core.ui;

/**
 *
 * @author  Romsl
 */
public class ConnectionSettings extends javax.swing.JFrame {
    
    /** Creates new form ConnectionSettings */
    public ConnectionSettings() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        driverClassL = new javax.swing.JLabel();
        driverClassTF = new javax.swing.JTextField();
        urlL = new javax.swing.JLabel();
        urlTF = new javax.swing.JTextField();
        usernameL = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passwordL = new javax.swing.JLabel();
        passwordPF = new javax.swing.JPasswordField();
        applyB = new javax.swing.JButton();
        exitB = new javax.swing.JButton();
        okB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        driverClassL.setText("Driver Class");

        urlL.setText("URL");

        usernameL.setText("Username");

        passwordL.setText("Password");

        applyB.setText("\u00dcbernehmen");

        exitB.setText("Abbrechen");

        okB.setText("OK");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(driverClassL)
                            .add(urlL)
                            .add(usernameL)
                            .add(passwordL))
                        .add(43, 43, 43)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(passwordPF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .add(urlTF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .add(driverClassTF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .add(usernameTF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(okB)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(exitB)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(applyB)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {applyB, exitB, okB}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(driverClassL)
                    .add(driverClassTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(urlL)
                    .add(urlTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(usernameL)
                    .add(usernameTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordL)
                    .add(passwordPF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(30, 30, 30)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(applyB)
                    .add(exitB)
                    .add(okB))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionSettings().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyB;
    private javax.swing.JLabel driverClassL;
    private javax.swing.JTextField driverClassTF;
    private javax.swing.JButton exitB;
    private javax.swing.JButton okB;
    private javax.swing.JLabel passwordL;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JLabel urlL;
    private javax.swing.JTextField urlTF;
    private javax.swing.JLabel usernameL;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
    
}
