/*
 * Input.java
 *
 * Created on 10. Dezember 2005, 15:16
 */

package ecobill.module.base.ui.textblock;

/**
 *
 * @author  Roman Georg R�dle
 */
public class Input extends javax.swing.JPanel {
    
    /** Creates new form Input */
    public Input() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        nameL = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        textL = new javax.swing.JLabel();
        textSP = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Eingabe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0)));
        nameL.setText("Name");

        name.setMinimumSize(new java.awt.Dimension(11, 20));

        textL.setText("Text");

        text.setColumns(20);
        text.setRows(5);
        textSP.setViewportView(text);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, name, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .add(nameL)
                    .add(textL)
                    .add(textSP, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                .add(nameL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(textL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(textSP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField name;
    private javax.swing.JLabel nameL;
    private javax.swing.JTextArea text;
    private javax.swing.JLabel textL;
    private javax.swing.JScrollPane textSP;
    // End of variables declaration//GEN-END:variables
    
}
