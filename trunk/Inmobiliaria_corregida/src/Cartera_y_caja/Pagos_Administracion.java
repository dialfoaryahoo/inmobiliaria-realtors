/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

import Models.acceso;
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
public class Pagos_Administracion extends javax.swing.JDialog {

   private DefaultTableModel modeloDeMiJTable; 
    
    public Pagos_Administracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(750,500);
        setLocationRelativeTo(rootPane);
        setTitle("PAGOS DE ADMINISTRACION");
        conn.establecer_conexion();
        jPanel2.setVisible(false);
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
        modeloDeMiJTable.addColumn("CONTRATO");
        modeloDeMiJTable.addColumn("VALOR_ADM");
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("FECHA");
        modeloDeMiJTable.addColumn("ESTADO");
        modeloDeMiJTable.addColumn("SELECCIONAR");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {20, 20, 20, 20, 20, 20};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            //Sacamos el modelo de columnas de nuestra tabla
            //luego obtenemos la columna en la posicion "i"
            //invocamos el metodo setPreferrefWidth para ajustar el ancho
            //y le damos el valor del entero que esta en el arreglo en la posicion "i"
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        llenartabla();
      }
      public void llenartabla(){
        conn.establecer_conexion();
        String consulta="select arr.cod_contrato, inm.admon, adm.valor, adm.fecha::date, adm.cancelada, inm.codinmueble, adm.cod_administracion "
                        +"from administracion as adm inner join arrienda as arr on adm.cod_arrienda = arr.cod_arrienda " +
                        "inner join inmuebles as inm on arr.cod_inmueble = inm.codinmueble order by adm.fecha desc";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
            while(n.next()){
                String cancelada = "";
                if(n.getInt(5)==1){
                    cancelada="No Cancelada";
                }
                if(n.getInt(5)==2){
                    cancelada="Cancelada";
                }
                modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3), n.getString(4),cancelada,""});
                cod_inmueble=n.getInt(6);
                cod_administracion=n.getInt(7);
                System.out.println(cod_inmueble);
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
       
       
//    public void buscar_operador(){
//        Vector cliente = new Vector();
//        try{
//         conn.establecer_conexion();
//         String sql="select usuario from usuarios";
//         ResultSet resultado = conn.consulta(sql);
//         cliente.addElement("Seleccione un Usuario");
//         while(resultado.next()){
//             cliente.addElement(resultado.getString(1));
//         }
//        jCombo_usuarios.setModel(new javax.swing.DefaultComboBoxModel(cliente));

//        //jPanel1.add(jComboBox_operador);
//        }catch(Exception e){
//            
//        }
        //jTable1.setDefaultRenderer (Object.class, new Renderito());
//    }       
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
        jButton4 = new javax.swing.JButton();
        jTextField_cod_contrato = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel10 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel24 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton_confirmar = new javax.swing.JButton();
        jTextField_nota = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

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
        jScrollPane1.setBounds(20, 110, 690, 200);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "ADMINISTRACION", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setLayout(null);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(560, 30, 120, 41);

        jTextField_cod_contrato.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_cod_contrato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_cod_contratoFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_cod_contrato);
        jTextField_cod_contrato.setBounds(270, 40, 140, 30);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("ESTADO");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(410, 20, 130, 20);
        jPanel1.add(dateChooserCombo2);
        dateChooserCombo2.setBounds(150, 40, 120, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Desde                         Hasta");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(30, 20, 230, 20);
        jPanel1.add(dateChooserCombo1);
        dateChooserCombo1.setBounds(30, 40, 120, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("CODIGO CONTRATO");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(270, 20, 140, 20);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "No Canceladas", "Canceladas" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(410, 40, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 20, 690, 80);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "CANCELAR", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(null);

        jButton_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton_confirmar.setText("Cancelar");
        jButton_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmarActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_confirmar);
        jButton_confirmar.setBounds(250, 60, 170, 50);
        jPanel2.add(jTextField_nota);
        jTextField_nota.setBounds(130, 20, 480, 30);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("OBSERVACION");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(0, 20, 130, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 320, 690, 120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jPanel2.setVisible(true);
        int row=jTable1.getSelectedRow();
        if(validar==1){
            if(jTable1.getValueAt(row2, 5).equals("X")){
                jTable1.setValueAt("", row2, 5);
                
            }
        }
        if(jTable1.getValueAt(row, 5).equals("")){
            jTable1.setValueAt("X", row, 5);
            validar=1;
            row2=row;
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField_cod_contratoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_cod_contratoFocusLost
        if(!cod_contrato.equals("")){
            cod_contrato=" and arr.cod_contrato= '"+jTextField_cod_contrato.getText().toUpperCase()+"'";
        }else{
            cod_contrato=" ";
        }
    }//GEN-LAST:event_jTextField_cod_contratoFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        conn.establecer_conexion();
        String order = "order by adm.fecha desc";
        String consulta="select arr.cod_contrato, inm.admon, adm.valor, adm.fecha::date, adm.cancelada, inm.codinmueble, adm.cod_administracion  "
                        +"from administracion as adm inner join arrienda as arr on adm.cod_arrienda = arr.cod_arrienda " +
                        "inner join inmuebles as inm on arr.cod_inmueble = inm.codinmueble "
                + "where adm.fecha between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' ";
        consulta+=cod_contrato+estado_can+order;
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
            limpiarTabla(jTable1);
            while(n.next()){
                String cancelada = "";
                if(n.getInt(5)==1){
                    cancelada="No Cancelada";
                }
                if(n.getInt(5)==2){
                    cancelada="Cancelada";
                }
                modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3), n.getString(4),cancelada,""});
                cod_inmueble=n.getInt(6);
                cod_administracion=n.getInt(7);
            }
        }
        catch(Exception e){}
        //+ "where adm.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' ";
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmarActionPerformed
        String codigo_contrato = "";
        int crow=modeloDeMiJTable.getRowCount();
        for(int i=0;i<crow;i++){
            if(jTable1.getValueAt(i,3).equals("X")){
                codigo_contrato = jTable1.getValueAt(i, 0).toString();
            }
        }
        //Mostrar la deuda como  ya cancelada.
        String update = "update administracion set cancelada = 2 where cod_administracion = "+cod_administracion;
        System.out.println(update);
//        conn.Dactualizar(update, "Cancelacion Exitosa");
        
        jPanel2.setVisible(false);
        limpiarTabla(jTable1);
        llenartabla();
        
       conn.JOptionShowMessage("+1", "", "ESTA FUNCION ESTA EN CONTRUCCION POR RAZONES CONTABLES.");


    }//GEN-LAST:event_jButton_confirmarActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if(jComboBox1.getSelectedIndex()==0){
            estado_can=" ";
        }
        else if(jComboBox1.getSelectedIndex()==1){
            estado_can= " and adm.cancelada = 1";
        }else if(jComboBox1.getSelectedIndex()==2){
            estado_can= " and adm.cancelada = 2";
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    
    
    int row2=-1, validar = 0;
    Conexion conn = new Conexion();
    acceso acc = new acceso();
    int accion = 0, codigo =0, cod_inmueble=0, cod_administracion;
    String cod_contrato= "", estado_can = "";
    Boolean falso = false, verdadero = true;    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton_confirmar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_cod_contrato;
    private javax.swing.JTextField jTextField_nota;
    // End of variables declaration//GEN-END:variables
}
