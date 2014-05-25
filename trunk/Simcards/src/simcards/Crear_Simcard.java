/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simcards;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Crear_Simcard extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Crear_Simcard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        setTitle("Creacion Plan");
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
        
        modeloDeMiJTable.addColumn("NOMBRE");
        modeloDeMiJTable.addColumn("OPERADOR");
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("MINUTOS");
        modeloDeMiJTable.addColumn("COSTO MIN");
        modeloDeMiJTable.addColumn("VENTA");
        modeloDeMiJTable.addColumn("$ MIN CLI");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {200, 70, 40,40,40,40,40};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        jButton_ELIMINAR.setEnabled(false);
        llenartabla();
        buscar_operador();
        jTextField_cant.setEditable(false);
    }
    public void buscar_operador(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre from operador";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Operador");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        jPanel1.add(jComboBox1);
        }catch(Exception e){
            
        }
    }
    public void llenartabla(){

        conn.establecer_conexion();
        String consulta="select * from plan where estado=1";
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            ResultSet m=conn.consulta("select nombre from operador where id="+n.getString(3));
            while(m.next()){
            modeloDeMiJTable.addRow(new Object[]{n.getString(2),m.getString(1),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8)});
            }
        }
        }
        catch(Exception e){}
        jTextField_cant.setText(String.valueOf(jTable1.getRowCount()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_costoplan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_costominutos = new javax.swing.JTextField();
        jTextField_utilidadmin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_tipo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_utilidadplan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_vlrplancliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_vlrmincliente = new javax.swing.JTextField();
        jTextField_minadicional = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton_ELIMINAR = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField_cant = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 380, 960, 150);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Datos del Plan", 3, 0, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("N° Linea");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 100, 240, 30);

        jTextField_costoplan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_costoplan);
        jTextField_costoplan.setBounds(120, 100, 210, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Operador");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 40, 110, 30);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(120, 40, 210, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Empresa");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 130, 240, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Plan");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 70, 110, 30);

        jTextField_costominutos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_costominutos);
        jTextField_costominutos.setBounds(430, 80, 130, 30);

        jTextField_utilidadmin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_utilidadmin);
        jTextField_utilidadmin.setBounds(430, 110, 130, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tipo");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(600, 80, 200, 30);

        jTextField_tipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_tipo);
        jTextField_tipo.setBounds(790, 80, 140, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Utilidad/Plan");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(600, 110, 230, 30);

        jTextField_utilidadplan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_utilidadplan);
        jTextField_utilidadplan.setBounds(790, 110, 140, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Vlr Plan Cliente");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(600, 140, 240, 30);

        jTextField_vlrplancliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_vlrplancliente);
        jTextField_vlrplancliente.setBounds(790, 140, 140, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Vlr Min Cliente");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(600, 170, 240, 30);

        jTextField_vlrmincliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_vlrmincliente);
        jTextField_vlrmincliente.setBounds(790, 170, 140, 30);

        jTextField_minadicional.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jTextField_minadicional);
        jTextField_minadicional.setBounds(430, 140, 130, 30);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton3.setText("LIMPIAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(20, 170, 160, 40);

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un plan" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(120, 70, 210, 30);

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un plan" }));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(120, 130, 210, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 40, 970, 230);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TOTAL PLANES");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(690, 530, 200, 50);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514344_Refresh.png"))); // NOI18N
        jButton1.setText("EDITAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(820, 280, 160, 40);

        jButton_ELIMINAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_ELIMINAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_ELIMINAR.setText("ELIMINAR");
        jButton_ELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ELIMINARActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_ELIMINAR);
        jButton_ELIMINAR.setBounds(20, 550, 160, 40);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PLANES EXISTENTES");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 350, 960, 30);

        jTextField_cant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_cant);
        jTextField_cant.setBounds(870, 540, 100, 30);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton4.setText("GUARDAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(660, 280, 160, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ELIMINARActionPerformed

    }//GEN-LAST:event_jButton_ELIMINARActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
 
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
 
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
      Vector plan= new Vector();
      String operador=jComboBox1.getSelectedItem().toString();
      String consulta="select nombre from plan where operador=(select id from operador where nombre='"+operador+"');";
      plan.addElement("Seleccione un plan");
      ResultSet n= conn.consulta(consulta);
        try {
            while(n.next()){
                plan.addElement(n.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crear_Simcard.class.getName()).log(Level.SEVERE, null, ex);
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(plan));
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    Conexion conn = new Conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_ELIMINAR;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_cant;
    private javax.swing.JTextField jTextField_costominutos;
    private javax.swing.JTextField jTextField_costoplan;
    private javax.swing.JTextField jTextField_minadicional;
    private javax.swing.JTextField jTextField_tipo;
    private javax.swing.JTextField jTextField_utilidadmin;
    private javax.swing.JTextField jTextField_utilidadplan;
    private javax.swing.JTextField jTextField_vlrmincliente;
    private javax.swing.JTextField jTextField_vlrplancliente;
    // End of variables declaration//GEN-END:variables
}
