/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Padres;
import Clases_DAO.PadresDB;
import System.Utilidades;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Add_Padre extends javax.swing.JDialog {

    /**
     * Creates new form HojaEstudiante
     */
    public Add_Padre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(870, 760);
        setLocationRelativeTo(null);
        activar_boton(false, false, false, false, false, false);
        
    }
    
    public void limpiar_campospadres(){
        jTextField_cedulapadre.setText("");
        jTextField_nombrepadre.setText("");
        jTextField_direccioncasapadre.setText("");
        jTextField_barriopadre.setText("");
        jTextField_telefonopadre.setText("");
        jTextField_celularpadre.setText("");
        jTextField_emailpadre.setText("");
        jTextField_titulopadre.setText("");
        jTextField_direccionoficinapadre.setText("");
        jTextField_empresapadre.setText("");
        jTextField_cargopadre.setText("");
        jComboBox_niveleducacionpadre.setSelectedIndex(0);
    }
    public void get_campospadres(){
        Padres pf = new Padres();
        jTextField_cedulapadre.setText(pf.getCedula());
        jTextField_nombrepadre.setText(pf.getNombre_completo());
        jTextField_direccioncasapadre.setText(pf.getDireccion_residencia());
        jTextField_barriopadre.setText(pf.getBarrio());
        jTextField_telefonopadre.setText(pf.getTelefono());
        jTextField_celularpadre.setText(pf.getCelular());
        jTextField_emailpadre.setText(pf.getEmail());
        jTextField_titulopadre.setText(pf.getTitulo());
        jTextField_direccionoficinapadre.setText(pf.getDireccion_oficina());
        jTextField_empresapadre.setText(pf.getEmpresa_labora());
        jTextField_cargopadre.setText(pf.getCargo());;
    }
    public void set_padres (){
        Padres pf = new Padres();
        pf.setCedula(jTextField_cedulapadre.getText().toUpperCase());
        pf.setNombre_completo(jTextField_nombrepadre.getText().toUpperCase());
        pf.setDireccion_residencia(jTextField_direccioncasapadre.getText().toUpperCase());
        pf.setBarrio(jTextField_barriopadre.getText().toUpperCase());
        pf.setTelefono(jTextField_telefonopadre.getText().toUpperCase());
        pf.setCelular(jTextField_celularpadre.getText().toUpperCase());
        pf.setEmail(jTextField_emailpadre.getText().toUpperCase());
        pf.setTipo_estudio(jComboBox_niveleducacionpadre.getSelectedItem().toString());
        pf.setTitulo(jTextField_titulopadre.getText().toUpperCase());
        pf.setDireccion_oficina(jTextField_direccionoficinapadre.getText().toUpperCase());
        pf.setEmpresa_labora(jTextField_empresapadre.getText().toUpperCase());
        pf.setCargo(jTextField_cargopadre.getText().toUpperCase());
        pf.setEstado(1);
      }
    public void enabled_padres (){
        jTextField_cedulapadre.setEditable(true);
        jTextField_nombrepadre.setEditable(true);
        jTextField_direccioncasapadre.setEditable(true);
        jTextField_barriopadre.setEditable(true);
        jTextField_telefonopadre.setEditable(true);
        jTextField_celularpadre.setEditable(true);
        jTextField_emailpadre.setEditable(true);
        jTextField_titulopadre.setEditable(true);
        jTextField_direccionoficinapadre.setEditable(true);
        jTextField_empresapadre.setEditable(true);
        jTextField_cargopadre.setEditable(true);
      }
    public void disable_padres (){
        jTextField_nombrepadre.setEditable(false);
        jTextField_direccioncasapadre.setEditable(false);
        jTextField_barriopadre.setEditable(false);
        jTextField_telefonopadre.setEditable(false);
        jTextField_celularpadre.setEditable(false);
        jTextField_emailpadre.setEditable(false);
        jTextField_titulopadre.setEditable(false);
        jTextField_direccionoficinapadre.setEditable(false);
        jTextField_empresapadre.setEditable(false);
        jTextField_cargopadre.setEditable(false);
      }    
    public void activar_boton(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f){
        jButton_add.setSelected(a);
        jButton_del.setSelected(b);
        jButton_imprimir.setSelected(c);
        jButton_limpiar.setSelected(d);
        jButton_modificar.setSelected(e);
        jButton_save.setVisible(f);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField_nombrepadre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_telefonopadre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_celularpadre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_direccioncasapadre = new javax.swing.JTextField();
        jTextField_cedulapadre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_direccionoficinapadre = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton_imprimir = new javax.swing.JButton();
        jButton_limpiar = new javax.swing.JButton();
        jButton_modificar = new javax.swing.JButton();
        jButton_del = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField_barriopadre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_emailpadre = new javax.swing.JTextField();
        jComboBox_niveleducacionpadre = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_titulopadre = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_empresapadre = new javax.swing.JTextField();
        jTextField_cargopadre = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton_save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Nombre Completo");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 130, 180, 40);

        jTextField_nombrepadre.setEditable(false);
        jTextField_nombrepadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_nombrepadre);
        jTextField_nombrepadre.setBounds(230, 130, 470, 40);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setText("Teléfono");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 180, 180, 40);

        jTextField_telefonopadre.setEditable(false);
        jTextField_telefonopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_telefonopadre);
        jTextField_telefonopadre.setBounds(230, 180, 180, 40);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Celular");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(430, 180, 90, 40);

        jTextField_celularpadre.setEditable(false);
        jTextField_celularpadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_celularpadre);
        jTextField_celularpadre.setBounds(520, 180, 180, 40);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setText("Dirección Casa");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 230, 180, 40);

        jTextField_direccioncasapadre.setEditable(false);
        jTextField_direccioncasapadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_direccioncasapadre);
        jTextField_direccioncasapadre.setBounds(230, 230, 470, 40);

        jTextField_cedulapadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_cedulapadre.setText("Buscar");
        jTextField_cedulapadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cedulapadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_cedulapadre);
        jTextField_cedulapadre.setBounds(230, 80, 470, 40);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setText("Dirección Oficina");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(40, 480, 180, 40);

        jTextField_direccionoficinapadre.setEditable(false);
        jTextField_direccionoficinapadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_direccionoficinapadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_direccionoficinapadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_direccionoficinapadre);
        jTextField_direccionoficinapadre.setBounds(230, 480, 470, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "ACCION", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 24))); // NOI18N
        jPanel1.setLayout(null);

        jButton_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_PRINT.png"))); // NOI18N
        jButton_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_imprimir);
        jButton_imprimir.setBounds(10, 460, 110, 100);

        jButton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clean_estudiante.png"))); // NOI18N
        jButton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_limpiarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_limpiar);
        jButton_limpiar.setBounds(10, 30, 110, 100);

        jButton_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Change_estudiante.png"))); // NOI18N
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_modificar);
        jButton_modificar.setBounds(10, 240, 110, 100);

        jButton_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/padre_del.fw.png"))); // NOI18N
        jButton_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_del);
        jButton_del.setBounds(10, 340, 110, 100);

        jButton_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/padre_add.fw.png"))); // NOI18N
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_add);
        jButton_add.setBounds(10, 130, 110, 100);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(720, 10, 130, 680);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setText("Barrio");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(40, 280, 180, 40);

        jTextField_barriopadre.setEditable(false);
        jTextField_barriopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_barriopadre);
        jTextField_barriopadre.setBounds(230, 280, 470, 40);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setText("Nivel Educación");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(40, 380, 180, 40);

        jTextField_emailpadre.setEditable(false);
        jTextField_emailpadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_emailpadre);
        jTextField_emailpadre.setBounds(230, 330, 470, 40);

        jComboBox_niveleducacionpadre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox_niveleducacionpadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONA NIVEL DE ESTUDIOS", "PRIMARIA", "SECUNDARIA", "UNIVERSITARIOS", "NO TIENE", "OTRO", " " }));
        getContentPane().add(jComboBox_niveleducacionpadre);
        jComboBox_niveleducacionpadre.setBounds(230, 380, 290, 40);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setText("E-mail");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(40, 330, 180, 40);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setText("Título");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(40, 430, 180, 40);

        jTextField_titulopadre.setEditable(false);
        jTextField_titulopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_titulopadre);
        jTextField_titulopadre.setBounds(230, 430, 470, 40);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel14.setText("Empresa Donde Labora");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(40, 530, 190, 40);

        jTextField_empresapadre.setEditable(false);
        jTextField_empresapadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_empresapadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_empresapadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_empresapadre);
        jTextField_empresapadre.setBounds(230, 530, 470, 40);

        jTextField_cargopadre.setEditable(false);
        jTextField_cargopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_cargopadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cargopadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_cargopadre);
        jTextField_cargopadre.setBounds(230, 580, 470, 40);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel15.setText("Cargo");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(40, 580, 190, 40);

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel16.setText("Cedula");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(40, 80, 180, 40);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 40)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AGREGAR PADRE DE FAMILIA");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 10, 670, 80);

        jButton_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1426879311_floppy-64.png"))); // NOI18N
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_save);
        jButton_save.setBounds(410, 630, 70, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_cedulapadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cedulapadreActionPerformed
        Padres pf = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
        if(PadresDB.load_ONE(jTextField_cedulapadre.getText(), 1)==true){
            get_campospadres();
            enabled_padres();
        }else{
            Utilidades.JOptionShowMessage("+1", "La Busqueda No Arrojo Resultados");
        }
    }//GEN-LAST:event_jTextField_cedulapadreActionPerformed

    private void jTextField_direccionoficinapadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_direccionoficinapadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_direccionoficinapadreActionPerformed

    private void jTextField_empresapadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_empresapadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_empresapadreActionPerformed

    private void jTextField_cargopadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cargopadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cargopadreActionPerformed

    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveActionPerformed
        Object[] datos = {jTextField_cedulapadre.getText(), jTextField_nombrepadre.getText(), jTextField_direccioncasapadre.getText()
                              ,jTextField_barriopadre.getText(), jTextField_telefonopadre.getText(), jTextField_celularpadre.getText(), jTextField_emailpadre.getText()
                              , jTextField_titulopadre.getText(), jTextField_direccionoficinapadre.getText(), jTextField_empresapadre.getText(), jTextField_cargopadre.getText()};     
        if(jButton_add.isSelected()==true){
            Padres pf = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
            if(jComboBox_niveleducacionpadre.getSelectedIndex()==0){
                Utilidades.JOptionShowMessage("+1", "DEBE SELECCIONAR UN NIVEL DE EDUCACION");
            }else{
                if(Utilidades.validar(datos)==true){
                    set_padres();
                    if(PadresDB.insertar()==1){
                        limpiar_campospadres();
                         Utilidades.JOptionShowMessage("+1", "FAMILIAR REGISTRADO CON EXITO");
                         activar_boton(false, false, false, false, false, false);
                         Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
                    }    
                 }  
            }        
        }
        
        else if(jButton_modificar.isSelected()==true){
            Padres pf = new Padres();
            if(Utilidades.validar(datos)==true){
                set_padres();
                if(PadresDB.actualizar(pf.getId_padre_familia())==1){
                    Utilidades.JOptionShowMessage("+1", "Registro Actualizado Con Exito");
                }
            }
            limpiar_campospadres();
            Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
            activar_boton(false, false, false, false, false, false);
        }




    }//GEN-LAST:event_jButton_saveActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        limpiar_campospadres();
        activar_boton(true, false, false, false, false, true);
        enabled_padres();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delActionPerformed
        if(jTextField_nombrepadre.getText().equals("")){
            Utilidades.JOptionShowMessage("+1", "Debes buscar un padre para eliminar");
        }else{
            int valor = Utilidades.JOptionConfirmDialog("+1", "Esta Seguro?");
            if(valor==0){
                Padres pf = new Padres();
                set_padres();
                pf.setEstado(2);
                if(PadresDB.actualizar(pf.getId_padre_familia())==1){
                    Utilidades.JOptionShowMessage("+1", "Padre de Familia Eliminado con Exito");
                    Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
                    limpiar_campospadres();
                    disable_padres();
                }
            }else{
                limpiar_campospadres();
                Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
            }
        }

        //        else if

        //        Padres pf = new Padres();
        //        pf.setEstado(2);
        //        if(PadresDB.actualizar(pf.getId_padre_familia())==1){
            //            Utilidades.JOptionShowMessage("+1", "Registro Eliminado Con Exito");
            //        }
        //        limpiar_campospadres();
        //        Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
    }//GEN-LAST:event_jButton_delActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        limpiar_campospadres();
        Padres pf2 = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
        activar_boton(false, false, false, false, true, true);
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void jButton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_limpiarActionPerformed
        Padres pf = new Padres(0, "", "", "", "", "", "", "", "", "", "", "", "", 0, null);
        limpiar_campospadres();
        int variable = 0;
        for (int i=0; i>500; i++){

        }
    }//GEN-LAST:event_jButton_limpiarActionPerformed

    private void jButton_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirActionPerformed
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            JOptionPane.showMessageDialog(rootPane, ""+localHost.getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Add_Padre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_imprimirActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_del;
    private javax.swing.JButton jButton_imprimir;
    private javax.swing.JButton jButton_limpiar;
    private javax.swing.JButton jButton_modificar;
    private javax.swing.JButton jButton_save;
    private javax.swing.JComboBox jComboBox_niveleducacionpadre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_barriopadre;
    private javax.swing.JTextField jTextField_cargopadre;
    private javax.swing.JTextField jTextField_cedulapadre;
    private javax.swing.JTextField jTextField_celularpadre;
    private javax.swing.JTextField jTextField_direccioncasapadre;
    private javax.swing.JTextField jTextField_direccionoficinapadre;
    private javax.swing.JTextField jTextField_emailpadre;
    private javax.swing.JTextField jTextField_empresapadre;
    private javax.swing.JTextField jTextField_nombrepadre;
    private javax.swing.JTextField jTextField_telefonopadre;
    private javax.swing.JTextField jTextField_titulopadre;
    // End of variables declaration//GEN-END:variables

    void setMnemonic(int VK_X) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
