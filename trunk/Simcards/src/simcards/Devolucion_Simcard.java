/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simcards;

import Models.Renderito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class Devolucion_Simcard extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMijTable1; 
    public Devolucion_Simcard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        jLabel10.requestFocus();
        setTitle("Creacion Plan");
        setLocationRelativeTo(rootPane);
        

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
   
    public void llenartabla2(){
        modeloDeMijTable1 = new DefaultTableModel() { 
        @Override 
        public Class getColumnClass(int c) { 
        return getValueAt(0, c).getClass(); 
        } 

        @Override 
        public boolean isCellEditable(int rowIndex, int columnIndex) { 
            if(columnIndex==7){
                return true;
            }
            return false; 
        }

        };
        
        modeloDeMijTable1.addColumn("CODIGO");
        modeloDeMijTable1.addColumn("LINEA");
        modeloDeMijTable1.addColumn("FECHA ASIG.");
        modeloDeMijTable1.addColumn("SALDO");
        modeloDeMijTable1.addColumn("ESTADO");
        modeloDeMijTable1.addColumn("USUARIO");
        modeloDeMijTable1.addColumn("CORTE");
        modeloDeMijTable1.addColumn("OK");
        jTable1.setModel(modeloDeMijTable1);
        int[] anchos = {5, 20, 40,20,20,20,10,10};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        
    }
    
    public void limpiartabla2(){
              DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
            int filas=jTable1.getRowCount();
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
        jTextField_cant = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

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

        jTextField_cant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jTextField_cant);
        jTextField_cant.setBounds(880, 240, 100, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COBRADOR ASOCIADO");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 330, 190, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTAL SIM CARD CLIENTES");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(650, 250, 230, 25);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(240, 330, 220, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PROXIMO PAGO");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(460, 290, 190, 30);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(240, 290, 220, 30);

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(670, 330, 180, 30);
        getContentPane().add(dateChooserCombo1);
        dateChooserCombo1.setBounds(650, 290, 200, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("MOTIVO DE LA ENTREGA");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(40, 290, 190, 30);

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
        jScrollPane1.setBounds(20, 400, 960, 150);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        modeloDeMijTable1.addRow(new Object[]{n.getString(11),n.getString(4),n.getString(5),n.getString(6),"VIGENTE",n.getString(8),n.getString(9),""});
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
                    modeloDeMijTable1.addRow(new Object[]{n.getString(11),n.getString(4),n.getString(5),n.getString(6),"VIGENTE",n.getString(8),n.getString(9)});
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        if(!jTable1.getValueAt(row, 7).equals("OK")){
            jTable1.setValueAt("OK", row, 7);
        }else{
            jTable1.setValueAt("", row, 7);
        }
        System.out.println(vlrsimcard);
    }//GEN-LAST:event_jTable1MouseClicked

    
    Conexion conn = new Conexion();
    int contdisponible=0;
    String cadbuscar="select * from simcard where estado=1 ";
    int valid=0;
    int vlrsimcard=0;
    String user="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox_cliente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_cant;
    private javax.swing.JTextField jTextField_cedula;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_fijo;
    private javax.swing.JTextField jTextField_saldo;
    // End of variables declaration//GEN-END:variables
}
