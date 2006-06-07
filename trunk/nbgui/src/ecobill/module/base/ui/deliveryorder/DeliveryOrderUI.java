/*
 * del.java
 *
 * Created on 5. Oktober 2005, 16:06
 */

package ecobill.module.base.ui.deliveryorder;

/**
 *
 * @author  Roman Georg R�dle
 */
public class DeliveryOrderUI extends javax.swing.JPanel {
    
    /** Creates new form del */
    public DeliveryOrderUI() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        createDeliveryOrderPanel = new javax.swing.JPanel();
        deliveryOrderTable = new ecobill.module.base.ui.deliveryorder.DeliveryOrderTable();
        addressPanel = new ecobill.module.base.ui.component.AddressPanel();
        formularDataPanel = new ecobill.module.base.ui.component.FormularDataPanel();
        prefixPanel = new ecobill.module.base.ui.component.TitleBorderedTextArea();
        suffixPanel = new ecobill.module.base.ui.component.TitleBorderedTextArea();

        setLayout(new java.awt.BorderLayout());

        org.jdesktop.layout.GroupLayout createDeliveryOrderPanelLayout = new org.jdesktop.layout.GroupLayout(createDeliveryOrderPanel);
        createDeliveryOrderPanel.setLayout(createDeliveryOrderPanelLayout);
        createDeliveryOrderPanelLayout.setHorizontalGroup(
            createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, createDeliveryOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, deliveryOrderTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, createDeliveryOrderPanelLayout.createSequentialGroup()
                        .add(createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, prefixPanel, 0, 300, Short.MAX_VALUE)
                            .add(addressPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(suffixPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .add(formularDataPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                .add(10, 10, 10))
        );
        createDeliveryOrderPanelLayout.setVerticalGroup(
            createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, createDeliveryOrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(addressPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(formularDataPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(createDeliveryOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(prefixPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(suffixPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(deliveryOrderTable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabbedPane.addTab("tab1", createDeliveryOrderPanel);

        add(tabbedPane, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ecobill.module.base.ui.component.AddressPanel addressPanel;
    private javax.swing.JPanel createDeliveryOrderPanel;
    private ecobill.module.base.ui.deliveryorder.DeliveryOrderTable deliveryOrderTable;
    private ecobill.module.base.ui.component.FormularDataPanel formularDataPanel;
    private ecobill.module.base.ui.component.TitleBorderedTextArea prefixPanel;
    private ecobill.module.base.ui.component.TitleBorderedTextArea suffixPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
    
}