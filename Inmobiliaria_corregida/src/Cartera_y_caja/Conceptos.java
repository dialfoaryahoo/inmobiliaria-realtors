/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

import inmobiliaria_fase01.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Conceptos extends javax.swing.JDialog {

   private DefaultTableModel modeloDeMiJTable; 
    
    public Conceptos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(750,500);
        setLocationRelativeTo(rootPane);
        setTitle("Conceptos");
        conn.establecer_conexion();
        buscar_operador();
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
        
        modeloDeMiJTable.addColumn("Cod");
        modeloDeMiJTable.addColumn("Concepto");
        modeloDeMiJTable.addColumn("Cod Contable");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {40, 200, 50};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {

            //Sacamos el modelo de columnas de nuestra tabla

            //luego obtenemos la columna en la posicion "i"

            //invocamos el metodo setPreferrefWidth para ajustar el ancho

            //y le damos el valor del entero que esta en el arreglo en la posicion "i"

            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            
        }
            jButton_confirmar.setVisible(false);
            inicializar(false, false);
            llenartabla();
            
    }
   private void inicializar(Boolean a, Boolean b){
       jTextField_cod.setEnabled(a);
       jTextField_nombre.setEnabled(b);
       jCombo_contable.setEnabled(b);
   }
   
   private void botones_iniciales(Boolean a, boolean b){
       jButton2.setSelected(a);
       jButton3.setSelected(b);
   
   }
       public void llenartabla(){

        conn.establecer_conexion();
        String consulta="select * from conceptos";
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            System.out.println("a");
        modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3)});
        }
        }
        catch(Exception e){}
    }
       
    public void limpiarTabla(JTable tabla){
            try {
                DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
                int filas=tabla.getRowCount();
                for (int i = 0;filas>i; i++) {
                    modelo.removeRow(0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }
        }       
       
       public void combo(){
       //limpio el combobox
        jCombo_contable.removeAllItems();
           try {
               conn.establecer_conexion();
                String consulta = "select detalle_cuenta from cuentas_contables";
                ResultSet query = conn.consulta(consulta);
                while(query.next()){
                jCombo_contable.addItem(query.getObject("detalle_cuenta"));
                }
                
               
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null,"Error sql no se pueden leer datos");
           }
       }

       
    public void buscar_operador(){
        Vector cliente = new Vector();
        System.out.println("paso por aqui");
        try{
         conn.establecer_conexion();
         String sql="select nombre from operador";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Operador");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jCombo_contable.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        jCombo_contable.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        //jPanel1.add(jComboBox_operador);
        }catch(Exception e){
            
        }
        //jTable1.setDefaultRenderer (Object.class, new Renderito());
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
        jTextField_buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jTextField_cod = new javax.swing.JTextField();
        jCombo_contable = new javax.swing.JComboBox();
        jButton_confirmar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Conceptos");
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 100, 410, 310);

        jTextField_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_buscarActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_buscar);
        jTextField_buscar.setBounds(90, 60, 340, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cod Contable");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(440, 190, 100, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Buscar");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 60, 50, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Codigo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(440, 110, 50, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(440, 150, 60, 30);
        getContentPane().add(jTextField_nombre);
        jTextField_nombre.setBounds(510, 150, 170, 30);
        getContentPane().add(jTextField_cod);
        jTextField_cod.setBounds(510, 110, 170, 30);

        jCombo_contable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Codigo" }));
        jCombo_contable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCombo_contableItemStateChanged(evt);
            }
        });
        jCombo_contable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombo_contableActionPerformed(evt);
            }
        });
        getContentPane().add(jCombo_contable);
        jCombo_contable.setBounds(540, 190, 140, 30);

        jButton_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        jButton_confirmar.setText("sin texto");
        jButton_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_confirmar);
        jButton_confirmar.setBounds(490, 240, 170, 50);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(450, 60, 120, 40);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(580, 60, 120, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       inicializar(false, true);
        botones_iniciales(true, false);
       jButton_confirmar.setVisible(true);
       jButton_confirmar.setText("GUARDAR");
       
       String select = "select max(cod_concepto)+1 from conceptos";
       ResultSet query = conn.consulta(select);
        try {
            while (query.next()) {                
                jTextField_cod.setText(query.getString(1));
            }
        } catch (Exception e) {
        }
               
       
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       inicializar(true, false);
       botones_iniciales(false, true);
       jButton_confirmar.setVisible(true);
       jButton_confirmar.setText("ELIMINAR");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_buscarActionPerformed
    String ele = jTextField_buscar.getText().toUpperCase();
 
    for (int i = 0; i < jTable1.getRowCount(); i++) {
        
            if (jTable1.getValueAt(i, 1).equals(ele)) {                                           
           //if (jTable1.getValueAt(i, 1).equals(ele)) {                                           
                  jTable1.changeSelection(i, 1, false, false);
                  break;
           }
    }
        
        

//        modeloDeMiJTable.setRowCount(0);
//        String consulta = "";
//        if(jTextField_buscar.getText().equals("")){
//            consulta = "select * from conceptos";
//        }
//        else if(!jTextField_buscar.getText().equals("")){
//                consulta="select * from conceptos where nombres like '%"+jTextField_buscar.getText().toUpperCase()+"%'";    
//        }   
//        System.out.println(consulta);
//        ResultSet n=conn.consulta(consulta);
//        int contador = 0;
//        try{
//            while(n.next()){
//                modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3)});
//                contador++;
//            }
//        }catch(Exception e){
//            
//        }   
//        if(contador==0){
//            conn.JOptionShowMessage("+1", "", "No existe el concepto");
//        }
    }//GEN-LAST:event_jTextField_buscarActionPerformed

    private void jButton_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmarActionPerformed
        int validar =1;
        if(jButton_confirmar.getText().equals("GUARDAR")){
            String insrt="insert into conceptos (nombRes,cod_referencia) values ('"+jTextField_nombre.getText().toUpperCase()+"','"+jCombo_contable.getSelectedItem()+"')";
            conn.insertar(insrt);
            validar=1;
        }else if(jButton_confirmar.getText().equals("ELIMINAR")){
            int opcion= JOptionPane.showConfirmDialog(rootPane,"SEGURO DESEA ELIMINAR ESTE CODIGO?","ADVERTENCIA",JOptionPane.OK_CANCEL_OPTION);
            if(opcion== JOptionPane.YES_OPTION){
                String delete="delete from conceptos where cod_concepto="+jTextField_cod.getText();
                conn.actualizar(delete, "CONCEPTO ELIMINADO CON EXITO");
                validar=1;
            }
        }
        else if(jButton_confirmar.getText().equals("ACTUALIZAR")){
            String update = "update conceptos set nombres='"+jTextField_nombre.getText().toUpperCase()+"', cod_referencia ='"+jCombo_contable.getSelectedItem()+"' where cod_concepto = 1";
            System.out.println(update);
            conn.Dactualizar(update, "CONCEPTO "+jTextField_nombre.getText()+"se actualizo Correctamente");
            validar=1;            
            inicializar(false, false);
        }
        if (validar==1){
            limpiarTabla(jTable1);
            llenartabla();
            botones_iniciales(false, false);
        }
        
    }//GEN-LAST:event_jButton_confirmarActionPerformed

    private void jCombo_contableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombo_contableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombo_contableActionPerformed

    private void jCombo_contableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCombo_contableItemStateChanged

    }//GEN-LAST:event_jCombo_contableItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Deseas Editar el concepto?", "Editar Concepto", JOptionPane.OK_CANCEL_OPTION);
        if(seletedvalue ==JOptionPane.YES_OPTION ){                    
            inicializar(false, true);
            jButton_confirmar.setVisible(true);
            jButton_confirmar.setText("ACTUALIZAR");
            jTextField_cod.setText(jTable1.getValueAt(row, 0).toString());
            jTextField_nombre.setText(jTable1.getValueAt(row, 1).toString());
            
            //ojo falta cuadrar el combo 
            //////
            //jTextField_nombre.setText(jTable1.getValueAt(row, 2).toString());            
        }  
        
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    
    int[] anchos = {40, 200, 50};
    Conexion conn = new Conexion();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_confirmar;
    private javax.swing.JComboBox jCombo_contable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_buscar;
    private javax.swing.JTextField jTextField_cod;
    private javax.swing.JTextField jTextField_nombre;
    // End of variables declaration//GEN-END:variables
}
