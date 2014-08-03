/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo;

import inmobiliaria_fase01.Conexion;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Usuario
 */
public class informes_recibos extends javax.swing.JDialog {
private DefaultTableModel modeloDeMiJTable; 
    
    public informes_recibos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(850,600);
        this.setLocationRelativeTo(null);
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.NebulaSkin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSaturatedTheme");
        
        conn.establecer_conexion();
        

    
        //CREACION DE LA TABLA
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
        modeloDeMiJTable.addColumn("CODIGO FACTURA"); 
        modeloDeMiJTable.addColumn("TIPO MOVIMIENTO");
        modeloDeMiJTable.addColumn("USUARIO"); 
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("FECHA");
        
        jTable1.setModel(modeloDeMiJTable);
        
        
        int[] anchos = {30, 100, 150,80, 50};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {

            //Sacamos el modelo de columnas de nuestra tabla

            //luego obtenemos la columna en la posicion "i"

            //invocamos el metodo setPreferrefWidth para ajustar el ancho

            //y le damos el valor del entero que esta en el arreglo en la posicion "i"

            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }        
    modeloDeMiJTable.addRow(new Object[]{"","","","",""});        
    }
    

    
    
    static String integerFormat(int i) { 
        DecimalFormat df = new DecimalFormat("$#,###.##"); 
        String s = df.format(i); 
        return s; }
    //METODO QUE SITUA LA TABLA EN EL ULTIMO REGISTRO
////    public void scroll(){
////        int row =  jTable1.getRowCount () - 1;
////        Rectangle rect = jTable1.getCellRect(row, 0, true);
////        jTable1.scrollRectToVisible(rect);
////        jTable1.clearSelection();
////        jTable1.setRowSelectionInterval(row, row);
////        modeloDeMiJTable.fireTableDataChanged();
////    }
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

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel_egresos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_vlrtotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_diferencia = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(dateChooserCombo1);
        dateChooserCombo1.setBounds(20, 90, 180, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Informe Recibos");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 20, 160, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Hasta");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 70, 160, 20);

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
        jScrollPane1.setBounds(20, 130, 790, 290);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(670, 90, 140, 30);

        jLabel_egresos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_egresos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel_egresos);
        jLabel_egresos.setBounds(200, 470, 160, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("TOTAL EGRESOS");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 470, 180, 40);
        getContentPane().add(dateChooserCombo2);
        dateChooserCombo2.setBounds(220, 90, 180, 30);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo de Informe", "Ingreso", "Egreso", "Asiento" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(410, 90, 240, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Fecha Registro");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 50, 160, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Desde");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 70, 160, 20);

        jLabel_vlrtotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_vlrtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel_vlrtotal);
        jLabel_vlrtotal.setBounds(200, 430, 160, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL INGRESOS");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 430, 180, 40);

        jLabel_diferencia.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_diferencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabel_diferencia);
        jLabel_diferencia.setBounds(200, 510, 160, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("DIFERENCIA");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 510, 180, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vlrtotal=0;
        egresos=0;
        String consulta="";
        if(validador==0){
            if(jComboBox1.getSelectedIndex()==1){
                consulta="select DISTINCT fc.serial,fc.tipo_factura, fc.usuario, fc.total, fc.fecha::Date from facturas as fc  where fc.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' and fc.tipo_factura in ('"+buscar+"', '"+buscar2+"')" ;
            }
            if(jComboBox1.getSelectedIndex()==2){
                consulta="select DISTINCT fc.serial,fc.tipo_factura, fc.usuario, fc.total, fc.fecha::Date from facturas as fc  where fc.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' and fc.tipo_factura in ('"+buscar+"')" ;
            }            
        
        }else{
         consulta="select DISTINCT fc.serial,fc.tipo_factura, fc.usuario, fc.total, fc.fecha::Date from facturas as fc where fc.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"'";
           
        }
        limpiartabla();
        
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            if(n.getString(2).equals("FACTURA") ){
                vlrtotal+=Integer.parseInt(n.getString(4));
            }
            if(n.getString(2).equals("RECIBO_CAJA") ){
                vlrtotal+=Integer.parseInt(n.getString(4));
            }else if(n.getString(2).equals("EGRESO")){
                egresos+=Integer.parseInt(n.getString(4));
            }
            if(validador==0){
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5)});
            }else{
             modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(4),n.getString(5),n.getString(5)});
            }
        }
        }
        catch(Exception e){}
        if(validador==1){
        diferencia=vlrtotal-egresos;
        }
        jLabel_vlrtotal.setText("$"+vlrtotal);
        jLabel_egresos.setText("$"+egresos);
        jLabel_diferencia.setText("$"+diferencia);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
     if(jComboBox1.getSelectedItem().equals("Ingreso")){
         buscar="RECIBO_CAJA";
         buscar2 = "FACTURA";
         validador=0;
     }else if(jComboBox1.getSelectedItem().equals("Egreso")){
         buscar="EGRESO";
         validador=0;         
     }else if(jComboBox1.getSelectedItem().equals("Asiento")){
         validador=1;
     }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */

int vlrtotal=0;
int egresos=0;
int diferencia=0;
Conexion conn = new Conexion();
String buscar="", buscar2="";
int validador =0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_diferencia;
    private javax.swing.JLabel jLabel_egresos;
    private javax.swing.JLabel jLabel_vlrtotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
