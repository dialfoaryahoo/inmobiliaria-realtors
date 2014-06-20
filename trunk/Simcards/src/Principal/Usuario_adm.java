/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import simcards.*;
import Models.Renderito;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Usuario_adm extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Usuario_adm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(660,500);
        setTitle("CREAR OPERADOR");
        usuarios_admEnabled(falso, falso, falso, falso, falso, falso, verdadero, verdadero, verdadero);
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
        
        modeloDeMiJTable.addColumn("Nombre");
        modeloDeMiJTable.addColumn("Usuario");
        modeloDeMiJTable.addColumn("Categoria");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {20, 200, 70};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        

        jTable1.setDefaultRenderer (Object.class, new Renderito());
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
        
    private boolean isPasswordCorrect(char[] j1,char[] j2) {
        boolean valor = true;
        int puntero = 0;
        if (j1.length != j2.length){
            valor = false;
        }
        else{
            while((valor)&&(puntero < j1.length)){
                if (j1[puntero] != j2[puntero]){
                 valor = false;
                }
                puntero++;
            }
        }
    return valor;
    }        
    public void usuarios_admEnabled(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i){
    jTextField_nombre.setEnabled(a);
    jTextField_usuario.setEnabled(b);
    jPasswordField1.setEnabled(c);
    jPasswordField2.setEnabled(d);
    jComboBoxnivel.setEnabled(e);
    jButton_guardar.setVisible(f);
    jButton_crear.setVisible(g);
    jButton_modificar.setVisible(h);
    jButton_eliminar.setVisible(i);
    jComboBoxnivel.setSelectedIndex(0);
    
    }
    public void usuarios_vacio(){
        jTextField_nombre.setText("");
    jTextField_usuario.setText("");
    jPasswordField1.setText("");
    jPasswordField2.setText("");
    jButton_guardar.setVisible(falso);
    jComboBoxnivel.setSelectedIndex(0);
    accion = 0;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField_usuario = new javax.swing.JTextField();
        jButton_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jComboBoxnivel = new javax.swing.JComboBox();
        jButton_guardar = new javax.swing.JButton();
        jButton_modificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton_crear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Contraseña");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(210, 130, 110, 30);

        jTextField_usuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_usuario);
        jTextField_usuario.setBounds(310, 50, 310, 30);

        jButton_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_eliminar);
        jButton_eliminar.setBounds(30, 90, 160, 40);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 102));
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
        jScrollPane1.setBounds(20, 250, 610, 160);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(210, 10, 190, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Usuario");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(210, 50, 190, 30);

        jTextField_nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_nombre);
        jTextField_nombre.setBounds(310, 10, 310, 30);

        jComboBoxnivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxnivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Administrador", "SuperUsuario", "Usuario" }));
        jComboBoxnivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxnivelActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxnivel);
        jComboBoxnivel.setBounds(310, 90, 310, 30);

        jButton_guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton_guardar.setText("Guardar");
        jButton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_guardar);
        jButton_guardar.setBounds(460, 200, 160, 30);

        jButton_modificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_modificar.setText("Modificar");
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_modificar);
        jButton_modificar.setBounds(30, 50, 160, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Usuarios Existentes");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 210, 610, 40);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(410, 130, 210, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Nivel");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(210, 90, 190, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Confirmar Contraseña");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(210, 160, 210, 30);
        getContentPane().add(jPasswordField2);
        jPasswordField2.setBounds(410, 160, 210, 30);

        jButton_crear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_crear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_crear.setText("Crear");
        jButton_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_crearActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_crear);
        jButton_crear.setBounds(30, 10, 160, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed

    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardarActionPerformed
        conn.establecer_conexion();
        java.util.Date utilDate = new java.util.Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);            
        String insert = "INSERT INTO usuarios(usuario, nombre, nivel, contrasena, estado_usuario, fecha_reg) values "
            + "('"+jTextField_usuario.getText().toUpperCase()+"', '"+jTextField_nombre.getText().toUpperCase()+"', '"+jComboBoxnivel.getSelectedIndex()+"', MD5('"+jPasswordField2.getPassword()+"'), 1, '"+sqlTimestamp+"')";
        
        
        switch(accion){
            case 1:
                Object [] datos = {jTextField_nombre.getText(), jTextField_usuario.getText(), jPasswordField1.getPassword(), jPasswordField2.getPassword()};
                if (validar(datos)==1) {
                    if (jComboBoxnivel.getSelectedIndex()!=0) {
                        if(isPasswordCorrect(jPasswordField1.getPassword(),jPasswordField2.getPassword())){
                            if(conn.Dinsertar(insert)==1){
                                usuarios_vacio();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(rootPane, "Las Contraseñas No Coinciden");
                            jPasswordField1.setText("");
                            jPasswordField2.setText("");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Selecione un nivel");
                    }

                }
                
                break;
            case 2:
                break;
            case 3:
                break;                
            
        }
    }//GEN-LAST:event_jButton_guardarActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void jButton_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_crearActionPerformed
        usuarios_admEnabled(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
        accion = 1;
    }//GEN-LAST:event_jButton_crearActionPerformed

    private void jComboBoxnivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxnivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxnivelActionPerformed

    /**
     * @param args the command line arguments
     */
    int accion = 0;
    Boolean verdadero=true;
    Boolean falso=false;    
    Conexion conn = new Conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_crear;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_guardar;
    private javax.swing.JButton jButton_modificar;
    private javax.swing.JComboBox jComboBoxnivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_usuario;
    // End of variables declaration//GEN-END:variables
}
