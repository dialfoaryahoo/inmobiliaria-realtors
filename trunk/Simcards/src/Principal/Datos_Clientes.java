/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import simcards.*;
import Models.Renderito;
import java.sql.ResultSet;
import java.util.Vector;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.core.Query;

/**
 *
 * @author Usuario
 */
public class Datos_Clientes extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Datos_Clientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(660,350);
        setTitle("Datos Basicos");
        setLocationRelativeTo(rootPane);
        buscar_operador();
       jButton_guardar.setVisible(false);
       jTextField_nombre.setVisible(false);
    }
    public void buscar_operador(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombrecompleto from clientes";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Cliente");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(cliente));

        }catch(Exception e){
            
        }
    }

        public int validar(Object[] datos) { 
            int validar = 1;
            for (int i = 0; i <= datos.length - 1; i++) { 
                if (datos[i].toString().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "Por Favor Diligencias todos los Datos"); 
                validar = 2;
                break;
                }
            } 
            return validar; 
        }     
        
        public void Clientesvacio(){

            jTextField_Ccedula.setText(""); 
            jTextField_Cdireccion.setText(""); 
            jTextField_Cciudad.setText(""); 
            jTextField_Cbarrio.setText(""); 
            jTextField_Ctelefono.setText(""); 
            jTextField_Ccelular.setText(""); 
            jTextField_Ccupomax.setText(""); 
            jRadioButton_Ccredito_si.setSelected(false);
            jRadioButton_Ccredito_no.setSelected(false);
            credito = 0;            
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton_eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_Ccedula = new javax.swing.JTextField();
        jTextField_Cbarrio = new javax.swing.JTextField();
        jTextField_Cciudad = new javax.swing.JTextField();
        jButton_crear = new javax.swing.JButton();
        jTextField_Cdireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_Ccelular = new javax.swing.JTextField();
        jTextField_Ctelefono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jRadioButton_Ccredito_no = new javax.swing.JRadioButton();
        jRadioButton_Ccredito_si = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField_Ccupomax = new javax.swing.JTextField();
        jButton_actualizar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField_nombre = new javax.swing.JTextField();
        jButton_guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Buscar");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 100, 30);

        jButton_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_eliminar);
        jButton_eliminar.setBounds(410, 190, 160, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Barrio");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 80, 90, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("CC / Nit");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 50, 110, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Direccion");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(320, 50, 90, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Ciudad");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 80, 110, 30);

        jTextField_Ccedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_Ccedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CcedulaActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_Ccedula);
        jTextField_Ccedula.setBounds(140, 50, 180, 30);

        jTextField_Cbarrio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cbarrio);
        jTextField_Cbarrio.setBounds(410, 80, 220, 30);

        jTextField_Cciudad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cciudad);
        jTextField_Cciudad.setBounds(140, 80, 180, 30);

        jButton_crear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_crear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_crear.setText("Crear");
        jButton_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_crearActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_crear);
        jButton_crear.setBounds(90, 190, 160, 40);

        jTextField_Cdireccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cdireccion);
        jTextField_Cdireccion.setBounds(410, 50, 220, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Celular");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(320, 110, 90, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Dar Credito?");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 140, 120, 30);

        jTextField_Ccelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ccelular);
        jTextField_Ccelular.setBounds(410, 110, 220, 30);

        jTextField_Ctelefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ctelefono);
        jTextField_Ctelefono.setBounds(140, 110, 180, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Telefono");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 110, 110, 30);

        buttonGroup2.add(jRadioButton_Ccredito_no);
        jRadioButton_Ccredito_no.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_Ccredito_no.setText("No");
        jRadioButton_Ccredito_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Ccredito_noActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_Ccredito_no);
        jRadioButton_Ccredito_no.setBounds(220, 140, 50, 30);

        buttonGroup2.add(jRadioButton_Ccredito_si);
        jRadioButton_Ccredito_si.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_Ccredito_si.setText("Si");
        jRadioButton_Ccredito_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Ccredito_siActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_Ccredito_si);
        jRadioButton_Ccredito_si.setBounds(160, 140, 50, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cupo Maximo");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(270, 140, 140, 30);

        jTextField_Ccupomax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ccupomax);
        jTextField_Ccupomax.setBounds(410, 140, 220, 30);

        jButton_actualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_actualizar);
        jButton_actualizar.setBounds(250, 190, 160, 40);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(140, 20, 490, 30);
        getContentPane().add(jTextField_nombre);
        jTextField_nombre.setBounds(140, 20, 490, 30);

        jButton_guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton_guardar.setText("Guardar");
        jButton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_guardar);
        jButton_guardar.setBounds(220, 230, 160, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        if(jComboBox1.getSelectedIndex()==0){
            Conexion.JOptionShowMessage("+1", null, "DEBE SELECCIONAR EL CLIENTE A ELIMINAR");
        }else{
            int a=JOptionPane.showConfirmDialog(rootPane, "ESTA SEGURO DE ELIMINAR ESTE CLIENTE?");
            if(a!=0){
                Conexion.JOptionShowMessage("+1", null, "OPERACION CANCELADA");
            }else{
                
            }
        }
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_crearActionPerformed
        jComboBox1.setVisible(false);
        jTextField_nombre.setVisible(true);
        jButton_guardar.setVisible(true);
        contador=1;
    }//GEN-LAST:event_jButton_crearActionPerformed

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        jComboBox1.setVisible(true);
        jTextField_nombre.setVisible(false);
        jButton_guardar.setVisible(true);
        contador=2;
    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jRadioButton_Ccredito_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Ccredito_siActionPerformed
        credito = 1;
        jTextField_Ccupomax.setText("");
        jTextField_Ccupomax.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_Ccredito_siActionPerformed

    private void jRadioButton_Ccredito_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Ccredito_noActionPerformed
        credito = 2;
        jTextField_Ccupomax.setText("0");
        jTextField_Ccupomax.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Ccredito_noActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        String consulta="select cedula, direccion, barrio,ciudad,fijo,celular,cupo_max from clientes where nombrecompleto='"+jComboBox1.getSelectedItem().toString()+"' ";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            jTextField_Ccedula.setText(n.getString(1));
            jTextField_Cdireccion.setText(n.getString(2));
            jTextField_Cbarrio.setText(n.getString(3));
            jTextField_Cciudad.setText(n.getString(4));
            jTextField_Ctelefono.setText(n.getString(5));
            jTextField_Ccelular.setText(n.getString(6));
            jTextField_Ccupomax.setText(n.getString(7));
        }
        }
        catch(Exception e){}
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField_CcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CcedulaActionPerformed
        int valid=0;
        String consulta="select cedula, direccion, barrio,ciudad,fijo,celular,cupo_max,nombre,primero_apellido,segundo_apellido from clientes where cedula='"+jTextField_Ccedula.getText()+"' ";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            jTextField_Ccedula.setText(n.getString(1));
            jTextField_Cdireccion.setText(n.getString(2));
            jTextField_Cbarrio.setText(n.getString(3));
            jTextField_Cciudad.setText(n.getString(4));
            jTextField_Ctelefono.setText(n.getString(5));
            jTextField_Ccelular.setText(n.getString(6));
            jTextField_Ccupomax.setText(n.getString(7));
            String nombre=n.getString(8)+" "+n.getString(9)+" "+n.getString(10);
            jComboBox1.setSelectedItem(nombre);
            valid++;
        }
        }
        catch(Exception e){}
        if(valid==0){
            Conexion.JOptionShowMessage("+1", null, "El cliente con cedula "+jTextField_Ccedula.getText()+" no se encuentra en la base de datos");
        }
    }//GEN-LAST:event_jTextField_CcedulaActionPerformed

    private void jButton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardarActionPerformed
     if(contador==1){
         Object [] datos = {jTextField_nombre.getText(),jTextField_Ccedula.getText(),jTextField_Cbarrio.getText(),jTextField_Ccelular.getText(),jTextField_Cciudad.getText(),jTextField_Ccupomax.getText(),jTextField_Cdireccion.getText(),jTextField_Ctelefono.getText()};
         if(Conexion.validar(datos)){
             String consulta="INSERT INTO clientes(\n" +
"             cedula, nombrecompleto,  \n" +
"            direccion, ciudad, barrio, fijo, celular, credito, cupo_max, \n" +
"             estado_cliente, fecha_reg)\n" +
"    VALUES ("+jTextField_Ccedula.getText()+",'"+jTextField_nombre.getText().toUpperCase()+"',  \n" +
"            '"+jTextField_Cdireccion.getText().toUpperCase()+"', '"+jTextField_Cciudad.getText().toUpperCase()+"', '"+jTextField_Cbarrio.getText().toUpperCase()+"','"+jTextField_Ctelefono.getText()+"', '"+jTextField_Ccelular.getText()+"', "+credito+", "+jTextField_Ccupomax.getText()+", \n" +
"             1, LOCALTIMESTAMP);";
             System.out.println(consulta);
             conn.insertar(consulta);
         }else{
             Conexion.JOptionShowMessage("+1", null, "DEBE LLENAR TODOS LOS CAMPOS");
         }
     }else if(contador==2){
     
     }
    }//GEN-LAST:event_jButton_guardarActionPerformed

    /**
     * @param args the command line arguments
     */
    Conexion conn = new Conexion();
    int credito = 0, contador=0;
    String buscar = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_crear;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_guardar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton_Ccredito_no;
    private javax.swing.JRadioButton jRadioButton_Ccredito_si;
    private javax.swing.JTextField jTextField_Cbarrio;
    private javax.swing.JTextField jTextField_Ccedula;
    private javax.swing.JTextField jTextField_Ccelular;
    private javax.swing.JTextField jTextField_Cciudad;
    private javax.swing.JTextField jTextField_Ccupomax;
    private javax.swing.JTextField jTextField_Cdireccion;
    private javax.swing.JTextField jTextField_Ctelefono;
    private javax.swing.JTextField jTextField_nombre;
    // End of variables declaration//GEN-END:variables
}
