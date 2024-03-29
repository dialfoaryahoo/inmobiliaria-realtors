/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Asientos extends javax.swing.JDialog {

   private DefaultTableModel modeloDeMiJTable; 
    
    public Asientos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(790,500);
        setLocationRelativeTo(rootPane);
        modeloDeMiJTable = new DefaultTableModel() { 
        @Override 
        public Class getColumnClass(int c) { 
        return getValueAt(0, c).getClass(); 
        } 

        @Override 
        public boolean isCellEditable(int rowIndex, int columnIndex) { 
        return false; 
        }

        };
        
        modeloDeMiJTable.addColumn("Documento");
        modeloDeMiJTable.addColumn("Fecha");
        modeloDeMiJTable.addColumn("Cod Inmueble");
        modeloDeMiJTable.addColumn("Nombre");
        modeloDeMiJTable.addColumn("Valor");
        modeloDeMiJTable.addColumn("Detalle");
        modeloDeMiJTable.addColumn("Debe");
        modeloDeMiJTable.addColumn("Haber");
        modeloDeMiJTable.addColumn("Cuenta");
        modeloDeMiJTable.addColumn("Control");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {40, 30, 40,100,30,100,30,30,50,30};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {

            //Sacamos el modelo de columnas de nuestra tabla

            //luego obtenemos la columna en la posicion "i"

            //invocamos el metodo setPreferrefWidth para ajustar el ancho

            //y le damos el valor del entero que esta en el arreglo en la posicion "i"

            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

            }

    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton_confirmar = new javax.swing.JButton();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asientos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 200, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 100, 740, 310);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Hasta");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(460, 60, 90, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo", "Recibo de Caja", "Facturas" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(130, 60, 140, 30);

        jButton_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        getContentPane().add(jButton_confirmar);
        jButton_confirmar.setBounds(660, 50, 80, 50);

        dateChooserCombo2.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        getContentPane().add(dateChooserCombo2);
        dateChooserCombo2.setBounds(510, 60, 130, 30);

        dateChooserCombo3.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        getContentPane().add(dateChooserCombo3);
        dateChooserCombo3.setBounds(330, 60, 130, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tipo de Asiento");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 60, 110, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Desde");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(280, 60, 120, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    int[] anchos = {40, 200, 50};
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JButton jButton_confirmar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
