/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simcards;

import Models.Renderito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class Asignar_Simcard extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Asignar_Simcard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        jLabel10.requestFocus();
        setTitle("Creacion Plan");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
        buscar_clientes();
        llenartabla2();
        jTextField_cant.setEditable(false);
    }
    
    public void buscar_clientes(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombrecompleto from clientes";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Cliente");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jComboBox_cliente.setModel(new javax.swing.DefaultComboBoxModel(cliente));

        }catch(Exception e){
            
        }
    }
    public void llenartabla(){
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
        
        modeloDeMiJTable.addColumn("CODIGO");
        modeloDeMiJTable.addColumn("SERIAL");
        modeloDeMiJTable.addColumn("OPERADOR");
        modeloDeMiJTable.addColumn("# LINEA");
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("CORTE");
        modeloDeMiJTable.addColumn("ESTADO");
        modeloDeMiJTable.addColumn("PLAN");
        modeloDeMiJTable.addColumn("EMPRESA");
        modeloDeMiJTable.addColumn("REF PAGO");
        modeloDeMiJTable.addColumn("SEL");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {5, 20, 10,30,30,10,30,50,40,20,30,5};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
        String consulta="select * from simcard where estado=1";
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            if(n.getString(9).equals("DISPONIBLE")){
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(8),n.getString(2),n.getString(4),n.getString(11),n.getString(7),n.getString(9),n.getString(3),n.getString(5),n.getString(13),""});
            

            }
        }
        }
        catch(Exception e){}
        jTextField_cant.setText(String.valueOf(jTable1.getRowCount()));

    }
    public void llenartabla2(){
        modeloDeMiJTable2 = new DefaultTableModel() { 
        @Override 
        public Class getColumnClass(int c) { 
        return getValueAt(0, c).getClass(); 
        } 

        @Override 
        public boolean isCellEditable(int rowIndex, int columnIndex) { 
            if(columnIndex==4){
                return true;
            }
            return false; 
        }

        };
        
        modeloDeMiJTable2.addColumn("CODIGO");
        modeloDeMiJTable2.addColumn("LINEA");
        modeloDeMiJTable2.addColumn("FECHA ASIG.");
        modeloDeMiJTable2.addColumn("SALDO");
        modeloDeMiJTable2.addColumn("ESTADO");
        modeloDeMiJTable2.addColumn("USUARIO");
        modeloDeMiJTable2.addColumn("CORTE");
        jTable2.setModel(modeloDeMiJTable2);
        int[] anchos = {5, 20, 40,20,20,20,10};
        for(int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    public void limpiartabla(){
              DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
            int filas=jTable1.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
              }
    }
    public void limpiartabla2(){
              DefaultTableModel modelo=(DefaultTableModel) jTable2.getModel();
            int filas=jTable2.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
              }
    }
    static String integerFormat(int i) { 
        DecimalFormat df = new DecimalFormat("$#,###.##"); 
        String s = df.format(i); 
        return s; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_saldo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_cedula = new javax.swing.JTextField();
        jComboBox_cliente = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_direccion = new javax.swing.JTextField();
        jTextField_fijo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_cant = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_operador1 = new javax.swing.JComboBox();
        jTextField_numerobuscar = new javax.swing.JTextField();
        jComboBox_empresa1 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jScrollPane1.setBounds(20, 340, 960, 180);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Cliente", 3, 0, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText(" Saldo");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(620, 40, 100, 30);

        jTextField_saldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_saldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_saldoFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_saldo);
        jTextField_saldo.setBounds(720, 40, 210, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Cliente");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 40, 100, 30);

        jTextField_cedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_cedulaFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_cedula);
        jTextField_cedula.setBounds(120, 70, 200, 30);

        jComboBox_cliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_cliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un plan" }));
        jComboBox_cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_clienteItemStateChanged(evt);
            }
        });
        jComboBox_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_clienteFocusLost(evt);
            }
        });
        jPanel1.add(jComboBox_cliente);
        jComboBox_cliente.setBounds(120, 40, 500, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cedula");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 70, 100, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText(" Direccion");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(320, 70, 100, 30);

        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_direccionFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_direccion);
        jTextField_direccion.setBounds(420, 70, 200, 30);

        jTextField_fijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_fijo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_fijoFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_fijo);
        jTextField_fijo.setBounds(720, 70, 210, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText(" Telefono");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(620, 70, 100, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 970, 120);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Empresa");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(650, 280, 210, 20);

        jTextField_cant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_cant);
        jTextField_cant.setBounds(880, 240, 100, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("SIMCARDS EXISTENTES");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 260, 220, 20);

        jComboBox_operador1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_operador1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_operador1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_operador1MouseClicked(evt);
            }
        });
        jComboBox_operador1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_operador1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_operador1);
        jComboBox_operador1.setBounds(150, 300, 230, 30);
        getContentPane().add(jTextField_numerobuscar);
        jTextField_numerobuscar.setBounds(400, 300, 220, 30);

        jComboBox_empresa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_empresa1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Empresa" }));
        getContentPane().add(jComboBox_empresa1);
        jComboBox_empresa1.setBounds(630, 300, 230, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("  Buscar:");
        jLabel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(30, 280, 950, 60);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Operador");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(150, 280, 210, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Numero");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(400, 280, 210, 20);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(870, 300, 100, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COBRADOR ASOCIADO");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 570, 190, 30);

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 130, 960, 110);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTAL SIM CARD CLIENTES");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(650, 240, 230, 25);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(220, 570, 220, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PROXIMO PAGO");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(440, 530, 190, 30);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(220, 530, 220, 30);

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(650, 570, 180, 30);
        getContentPane().add(dateChooserCombo1);
        dateChooserCombo1.setBounds(630, 530, 200, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("MOTIVO DE LA ENTREGA");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(20, 530, 190, 30);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(180, 640, 73, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        if(!jTable1.getValueAt(row, 10).equals("X")){
            jTable1.setValueAt("X", row, 10);
            vlrsimcard+=Integer.parseInt(jTable1.getValueAt(row, 4).toString());
        }else{
            jTable1.setValueAt("", row, 10);
            vlrsimcard-=Integer.parseInt(jTable1.getValueAt(row, 4).toString());
        }
        System.out.println(vlrsimcard);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox_operador1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_operador1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_operador1MouseClicked

    private void jComboBox_operador1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_operador1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_operador1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      String consulta="";
        if(jComboBox_operador1.getSelectedIndex()==0){
          if(jTextField_numerobuscar.getText().equals("")){
              if(jComboBox_empresa1.getSelectedIndex()==0){
                  Conexion.JOptionShowMessage("+1", null, "DEBE INGRESAR ALGUN PARAMETRO DE BUSQUEDA");
              }else{
                  limpiartabla();
                  consulta="select * from simcard where empresa='"+jComboBox_empresa1.getSelectedItem()+"'";
              }
          }else{
              if(jComboBox_empresa1.getSelectedIndex()==0){
                  limpiartabla();
                  consulta="select * from simcard where nlinea ='"+jTextField_numerobuscar.getText()+"'";
              }else{
                  limpiartabla();
                  consulta="select * from simcard where empresa='"+jComboBox_empresa1.getSelectedItem()+"' and nlinea ='"+jTextField_numerobuscar.getText()+"'";
              }
          }
      }else{
           if(jTextField_numerobuscar.getText().equals("")){
              if(jComboBox_empresa1.getSelectedIndex()==0){
                 limpiartabla();
                 consulta="select * from simcard where operador='"+jComboBox_operador1.getSelectedItem()+"'";
              }else{
                  limpiartabla();
                  consulta="select * from simcard where empresa='"+jComboBox_empresa1.getSelectedItem()+"' and operador='"+jComboBox_operador1.getSelectedItem()+"'";
              }
          }else{
              if(jComboBox_empresa1.getSelectedIndex()==0){
                  limpiartabla();
                  consulta="select * from simcard where nlinea ='"+jTextField_numerobuscar.getText()+"' and operador='"+jComboBox_operador1.getSelectedItem()+"'";
              }else{
                  limpiartabla();
                  consulta="select * from simcard where empresa='"+jComboBox_empresa1.getSelectedItem()+"' and nlinea ='"+jTextField_numerobuscar.getText()+"' and operador='"+jComboBox_operador1.getSelectedItem()+"'";
              }
          } 
        }
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(8),n.getString(2),n.getString(4),n.getString(11),n.getString(7),n.getString(9),n.getString(3),n.getString(5),n.getString(14),n.getString(13)});
            if(n.getString(9).equals("Disponible")){

            }
        }
        }
        catch(Exception e){}
        jTextField_cant.setText(String.valueOf(jTable1.getRowCount()));

        if(jTable1.getRowCount()==0){
            Conexion.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_clienteFocusLost
    }//GEN-LAST:event_jComboBox_clienteFocusLost

    private void jComboBox_clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_clienteItemStateChanged
        if(valid==0){
        int saldo=0;
        String cadena=jComboBox_cliente.getSelectedItem().toString();
        String consulta="select clientes.cedula,clientes.direccion,clientes.fijo, simcard.nlinea, asignacion.fecha, simcard.saldo, asignacion.estado, asignacion.usuario, simcard.corte, clientes.cod_cliente,asignacion.id "
                + "from simcard,clientes,asignacion where clientes.cod_cliente=asignacion.cod_cliente and asignacion.cod_simcard=simcard.cod_simcards and clientes.nombrecompleto='"+cadena+"'";
        System.out.println(consulta);
        ResultSet n = conn.consulta(consulta);
        limpiartabla2();
        try{
        while(n.next()){
            
        jTextField_cedula.setText(n.getString(1));
        jTextField_direccion.setText(n.getString(2));
        jTextField_fijo.setText(n.getString(3));
        
        if(n.getString(7).equals("1")){
            saldo+=Integer.parseInt(n.getString(6));
        modeloDeMiJTable2.addRow(new Object[]{n.getString(11),n.getString(4),n.getString(5),n.getString(6),"VIGENTE",n.getString(8),n.getString(9)});
        contdisponible++;
        } 
        }
        valid++;
        String s = integerFormat(saldo);
        jTextField_saldo.setText(s);
        jTextField_cant.setText(""+contdisponible);
        }
        catch(Exception e){}
        }else{
            valid=0;
        }
        user=jComboBox_cliente.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox_clienteItemStateChanged

    private void jTextField_cedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_cedulaFocusLost
        cadbuscar+=" and cedula='"+jTextField_cedula.getText()+"' ";
        jTextField_cedula.setEnabled(false);
    }//GEN-LAST:event_jTextField_cedulaFocusLost

    private void jTextField_saldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_saldoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_saldoFocusLost

    private void jTextField_direccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_direccionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_direccionFocusLost

    private void jTextField_fijoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_fijoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fijoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, Ingreso.usuario);
        int crow=modeloDeMiJTable.getRowCount();
        for(int i=0;i<crow;i++){

            if(jTable1.getValueAt(i,10).equals("X")){
                String consulta="insert into asignacion (cod_simcard, cod_cliente, fecha, usuario, estado) values ("+jTable1.getValueAt(i, 0)+",(select cod_cliente from clientes where nombrecompleto='"+user+"'),localtimestamp,'"+ Ingreso.usuario+"',1)";
                String update="update simcard set estadointerno='ASIGNADA' where cod_simcards="+jTable1.getValueAt(i, 0);
                System.out.println(consulta);
                int vquery=conn.insertar_venta(consulta);
                int vquery2=conn.insertar_venta(update);
                if(vquery*vquery2!=0){
                    Conexion.JOptionShowMessage("+1", null, "SIMCARDS "+jTable1.getValueAt(i, 3)+" ASIGNADA CORRECTAMENTE");
                    int a= JOptionPane.showConfirmDialog(rootPane, "DESEA IMPRIMIR EL FORMATO DE ACEPTACION?");
                    if(a==0){
            
                    }
                }
                int saldo=0;
                    String cadena=jComboBox_cliente.getSelectedItem().toString();
                    String consulta2="select clientes.cedula,clientes.direccion,clientes.fijo, simcard.nlinea, asignacion.fecha, simcard.saldo, asignacion.estado, asignacion.usuario, simcard.corte, clientes.cod_cliente,asignacion.id "
                            + "from simcard,clientes,asignacion where clientes.cod_cliente=asignacion.cod_cliente and asignacion.cod_simcard=simcard.cod_simcards and clientes.nombrecompleto='"+cadena+"'";
                    System.out.println(consulta2);
                    ResultSet n = conn.consulta(consulta2);
                    limpiartabla2();
                    try{
                    while(n.next()){

                    jTextField_cedula.setText(n.getString(1));
                    jTextField_direccion.setText(n.getString(2));
                    jTextField_fijo.setText(n.getString(3));

                    if(n.getString(7).equals("1")){
                        saldo+=Integer.parseInt(n.getString(6));
                    modeloDeMiJTable2.addRow(new Object[]{n.getString(11),n.getString(4),n.getString(5),n.getString(6),"VIGENTE",n.getString(8),n.getString(9)});
                    contdisponible++;
                    } 
                    }
                    valid++;
                    String s = integerFormat(saldo);
                    jTextField_saldo.setText(s);
                    jTextField_cant.setText(""+contdisponible);
                    }
                    catch(Exception e){}
                    }else{
                        valid=0;
                    }
            }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                String path="D:/Proyecto/Simcards/src/Reportes/Nuevo.jasper";
            JasperReport jr = null;
            String nombre=jComboBox_cliente.getSelectedItem().toString();
            try {
                Map parametro = new HashMap();
                System.out.println(nombre);
                 java.util.Date utilDate = new java.util.Date(); 
                parametro.put("NOMBRE", nombre); 
                parametro.put("FECHA", utilDate);  
                //parametro.put("parameter1", jComboBox_usuarios.getSelectedItem());
                jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jp = JasperFillManager.fillReport(jr,parametro,conn.establecer_conexion());
                JasperViewer jv = new JasperViewer(jp);
                jv.setVisible(true);
                jv.setTitle("FORMATO DE ACEPTACION");


            } catch (JRException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }    
    }//GEN-LAST:event_jButton3ActionPerformed

    
    Conexion conn = new Conexion();
    int contdisponible=0;
    String cadbuscar="select * from simcard where estado=1 ";
    int valid=0;
    int vlrsimcard=0;
    String user="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox_cliente;
    private javax.swing.JComboBox jComboBox_empresa1;
    private javax.swing.JComboBox jComboBox_operador1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_cant;
    private javax.swing.JTextField jTextField_cedula;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_fijo;
    private javax.swing.JTextField jTextField_numerobuscar;
    private javax.swing.JTextField jTextField_saldo;
    // End of variables declaration//GEN-END:variables
}
