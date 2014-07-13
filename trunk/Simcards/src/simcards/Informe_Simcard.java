/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simcards;

import Models.Renderito;
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
public class Informe_Simcard extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Informe_Simcard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        jLabel1.requestFocus();
        setTitle("Creacion Plan");
        setLocationRelativeTo(rootPane);
        modeloDeMiJTable = new DefaultTableModel() { 
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
        
        modeloDeMiJTable.addColumn("CODIGO");
        modeloDeMiJTable.addColumn("SERIAL");
        modeloDeMiJTable.addColumn("OPERADOR");
        modeloDeMiJTable.addColumn("# LINEA");
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("CORTE");
        modeloDeMiJTable.addColumn("ESTADO");
        modeloDeMiJTable.addColumn("PLAN");
        modeloDeMiJTable.addColumn("EMPRESA");
        modeloDeMiJTable.addColumn("SALDO");
        modeloDeMiJTable.addColumn("REF PAGO");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {5, 20, 10,30,30,10,30,50,40,20,30};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        jButton_ELIMINAR.setEnabled(false);
        llenartabla();
        buscar_operador();
        buscar_clientes();
        jTextField_cant.setEditable(false);
    }
    public void buscar_clientes(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre,primero_apellido,segundo_apellido from clientes";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Cliente");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1)+" "+resultado.getString(2)+" "+resultado.getString(3));
         }
        jComboBox_cliente.setModel(new javax.swing.DefaultComboBoxModel(cliente));

        }catch(Exception e){
            
        }
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
        jComboBox_operador.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        jComboBox_operador1.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        jPanel1.add(jComboBox_operador);
        }catch(Exception e){
            
        }
        jTable1.setDefaultRenderer (Object.class, new Renderito());
    }
    public void llenartabla(){

        conn.establecer_conexion();
        String consulta="select * from simcard where estado=1";
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(8),n.getString(2),n.getString(4),n.getString(11),n.getString(7),n.getString(9),n.getString(3),n.getString(5),n.getString(14),n.getString(13)});
            if(n.getString(9).equals("Disponible")){
                contdisponible++;
            }
        }
        }
        catch(Exception e){}
        jTextField_cant.setText(String.valueOf(jTable1.getRowCount()));
        jTextField_cant1.setText(""+contdisponible);
        Vector plan= new Vector();
      String operador=jComboBox_operador.getSelectedItem().toString();
      String consulta2="select nombre from empresa";
      plan.addElement("Seleccione una Empresa");
      ResultSet n2= conn.consulta(consulta2);
        try {
            while(n2.next()){
                plan.addElement(n2.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Informe_Simcard.class.getName()).log(Level.SEVERE, null, ex);
        }
        jComboBox_empresa.setModel(new javax.swing.DefaultComboBoxModel(plan));
        jComboBox_empresa1.setModel(new javax.swing.DefaultComboBoxModel(plan));
    }
    public void limpiartabla(){
              DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
            int filas=jTable1.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_nlinea = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_operador = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_cedula = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox_cliente = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_empresa = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jComboBox_estadp = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton_ELIMINAR = new javax.swing.JButton();
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
        jTextField_cant1 = new javax.swing.JTextField();

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
        jScrollPane1.setBounds(20, 360, 960, 180);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Informe de SimCard", 3, 0, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("N° Linea");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 160, 100, 30);

        jTextField_nlinea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_nlinea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_nlineaFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_nlinea);
        jTextField_nlinea.setBounds(120, 160, 210, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Operador");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 100, 100, 30);

        jComboBox_operador.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_operador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_operador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_operadorMouseClicked(evt);
            }
        });
        jComboBox_operador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_operadorItemStateChanged(evt);
            }
        });
        jComboBox_operador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_operadorFocusLost(evt);
            }
        });
        jPanel1.add(jComboBox_operador);
        jComboBox_operador.setBounds(120, 100, 210, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Empresa");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 130, 100, 30);

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
        jTextField_cedula.setBounds(120, 70, 500, 30);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton3.setText("LIMPIAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(640, 40, 270, 40);

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

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Corte", "1", "2", "3", "4", "5", "7", "12", "15", "20", "25", "27", "30" }));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(410, 130, 210, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cedula");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 70, 100, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Corte");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(340, 130, 80, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Estado");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(340, 100, 80, 30);

        jComboBox_empresa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_empresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Empresa" }));
        jComboBox_empresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_empresaFocusLost(evt);
            }
        });
        jPanel1.add(jComboBox_empresa);
        jComboBox_empresa.setBounds(120, 130, 210, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton1.setText("EDITAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(640, 80, 270, 40);

        jComboBox_estadp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_estadp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Estado", "Asignada", "Disponible" }));
        jComboBox_estadp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox_estadpFocusLost(evt);
            }
        });
        jPanel1.add(jComboBox_estadp);
        jComboBox_estadp.setBounds(410, 100, 210, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 30, 970, 210);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TOTAL SIM CARD");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(700, 570, 200, 50);

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Empresa");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(640, 260, 210, 20);

        jTextField_cant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_cant);
        jTextField_cant.setBounds(880, 580, 100, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("SIMCARDS EXISTENTES");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 240, 220, 20);

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
        jComboBox_operador1.setBounds(140, 280, 230, 30);
        getContentPane().add(jTextField_numerobuscar);
        jTextField_numerobuscar.setBounds(390, 280, 220, 30);

        jComboBox_empresa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_empresa1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Empresa" }));
        getContentPane().add(jComboBox_empresa1);
        jComboBox_empresa1.setBounds(620, 280, 230, 30);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("  Buscar:");
        jLabel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(20, 260, 950, 60);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Operador");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(140, 260, 210, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Numero");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(390, 260, 210, 20);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(860, 280, 100, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL SIM CARD DISPONIBLES");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(580, 540, 320, 50);

        jTextField_cant1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cant1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_cant1);
        jTextField_cant1.setBounds(880, 550, 100, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ELIMINARActionPerformed
          int row=jTable1.getSelectedRow();
    int validar= JOptionPane.showConfirmDialog(rootPane, "<html><font size=+1 face='Arial'>DESE ELIMINAR ESTE SIMCARD?</font></html>", "CONFIRMACION", 0);
    if(validar==0){
        String update="update plan set estado=2 where nombre='"+jTable1.getValueAt(row,0)+"'";
        conn.actualizar(update);
        Conexion.JOptionShowMessage("+1", null, "SIMCARD ELIMINADO CON EXITO");
        modeloDeMiJTable.removeRow(row);
    }else{
        Conexion.JOptionShowMessage("+1", null, "OPERACION CANCELADA");
    }
    }//GEN-LAST:event_jButton_ELIMINARActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
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
                contdisponible++;
            }
        }
        }
        catch(Exception e){}
        jTextField_cant.setText(String.valueOf(jTable1.getRowCount()));
        jTextField_cant1.setText(""+contdisponible);
        if(jTable1.getRowCount()==0){
            Conexion.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_estadpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_estadpFocusLost
        cadbuscar+=" and estado='"+jComboBox_estadp.getSelectedItem()+"' ";
    }//GEN-LAST:event_jComboBox_estadpFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println(cadbuscar);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_clienteFocusLost
        cadbuscar+="and cliente='"+jComboBox_cliente.getSelectedItem()+"' ";
        jComboBox_cliente.setEnabled(false);
    }//GEN-LAST:event_jComboBox_clienteFocusLost

    private void jComboBox_clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_clienteItemStateChanged

    }//GEN-LAST:event_jComboBox_clienteItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField_cedulaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_cedulaFocusLost
        cadbuscar+=" and cedula='"+jTextField_cedula.getText()+"' ";
        jTextField_cedula.setEnabled(false);
    }//GEN-LAST:event_jTextField_cedulaFocusLost

    private void jComboBox_operadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_operadorFocusLost
        cadbuscar+=" and operador='"+jComboBox_operador.getSelectedItem()+"' ";
    }//GEN-LAST:event_jComboBox_operadorFocusLost

    private void jComboBox_operadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_operadorItemStateChanged
 
    }//GEN-LAST:event_jComboBox_operadorItemStateChanged

    private void jComboBox_operadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_operadorMouseClicked

    }//GEN-LAST:event_jComboBox_operadorMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox_empresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox_empresaFocusLost
       cadbuscar+=" and empresa='"+jComboBox_empresa.getSelectedItem()+"' ";
    }//GEN-LAST:event_jComboBox_empresaFocusLost

    private void jTextField_nlineaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_nlineaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nlineaFocusLost

    /**
     * @param args the command line arguments
     */
    Conexion conn = new Conexion();
    int contdisponible=0;
    String cadbuscar="select * from simcard where estado=1 ";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_ELIMINAR;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox_cliente;
    private javax.swing.JComboBox jComboBox_empresa;
    private javax.swing.JComboBox jComboBox_empresa1;
    private javax.swing.JComboBox jComboBox_estadp;
    private javax.swing.JComboBox jComboBox_operador;
    private javax.swing.JComboBox jComboBox_operador1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_cant;
    private javax.swing.JTextField jTextField_cant1;
    private javax.swing.JTextField jTextField_cedula;
    private javax.swing.JTextField jTextField_nlinea;
    private javax.swing.JTextField jTextField_numerobuscar;
    // End of variables declaration//GEN-END:variables
}
