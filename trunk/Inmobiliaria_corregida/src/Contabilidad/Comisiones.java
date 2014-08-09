/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contabilidad;

//import Models.Renderito;
import Models.acceso;
import inmobiliaria_fase01.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Usuario
 */
public class Comisiones extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Comisiones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,750);
//        jLabel10.requestFocus();
        setTitle("COMISIONES");
        setLocationRelativeTo(rootPane);
        jComboBox_uso.setVisible(false);
        llenartabla();
        buscar_propietario();
        buscar_clientes();

//        buscar_empresa();
        
//        jButton_reporte.setVisible(false);
//        jTextField_NOVEDAD.setEnabled(false);
//        jTextField_Cod_contrato.setEnabled(false);
//        jComboBox_MOTIVO.setEnabled(false);
        
    }
//    public void buscar_empresa(){
//      Vector plan= new Vector();
//      String consulta2="select nombre from operador";
//      plan.addElement("Seleccione un Operador");
//      ResultSet n= conn.consulta(consulta2);
//        try {
//            while(n.next()){
//                plan.addElement(n.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Crear_Simcard.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex);
//        }
//        jComboBox_empresa.setModel(new javax.swing.DefaultComboBoxModel(plan));
//
//    }
    public void buscar_clientes(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre from nombres ORDER BY NOMBRE";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un nombre");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jComboBox_uso.setModel(new javax.swing.DefaultComboBoxModel(cliente));
            AutoCompleteDecorator.decorate(jComboBox_uso);
        }catch(Exception e){
            
        }
    }
    public void buscar_propietario(){
        Vector propietario = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre_completo from propietarios";
         ResultSet resultado = conn.consulta(sql);
         propietario.addElement("Seleccione un propietario");
         while(resultado.next()){
             propietario.addElement(resultado.getString(1));
         }
        jComboBox_propietario.setModel(new javax.swing.DefaultComboBoxModel(propietario));
        AutoCompleteDecorator.decorate(jComboBox_propietario);

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
        
        modeloDeMiJTable.addColumn("CONTRATO");
        modeloDeMiJTable.addColumn("INMUEBLE");
        modeloDeMiJTable.addColumn("CEDULA");
        modeloDeMiJTable.addColumn("PROPIETARIO");
        modeloDeMiJTable.addColumn("FECHA");
        modeloDeMiJTable.addColumn("VALOR");

        jTable1.setModel(modeloDeMiJTable);
//            modeloDeMiJTable.addRow(new Object[]{"","","","","",""});
        
        int[] anchos = {15,150,20,100,30,25};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
//        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 2";
        String consulta="select arr.cod_contrato, ' Codigo: '||inm.codigo||' Direccion '||inm.direccion||' Barrio '||inm.barrio  as inmueble, pro.cedula, pro.nombres||' '||pro.primer_apellido||' '||pro.segundo_apellido as nombre, com.fecha::date, com.valor " +
                        "from comisiones as com INNER JOIN arrienda as arr  on com.cod_arrienda= arr.cod_arrienda INNER JOIN inmuebles as inm on arr.cod_inmueble = inm.codinmueble INNER JOIN propietarios as pro on inm.cod_propietario = pro.cod_propietario "
                        + "where com.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"'";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        int contador=0;
        try{
        while(n.next()){
            contador+= n.getInt(6);
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6)});
        }
        jTextField_total_arr.setText(""+integerFormat(contador));
        }
        catch(Exception e){
            System.out.println(e);
        }
        
      
    }

    public void limpiartabla(){
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
//    public void reporte(){
//            String ruta=System.getProperty("user.dir");
//            ruta=ruta.replace("\\", "/");
//            System.out.println(ruta);
//            String path=ruta+"/src/Reportes/Asignacion.jasper";
//            JasperReport jr = null;
//            String nombre=jComboBox_Inqulino.getSelectedItem().toString();
//            try {
//                JDialog viewer = new JDialog(new javax.swing.JFrame(),"Visualização do Relatório", true); 
//                viewer.setSize(1000,800); 
//                viewer.setLocationRelativeTo(null); 
//                Map parametro = new HashMap();
//                System.out.println(nombre);
//                parametro.put("NOMBRE_COMPLETO", nombre); 
//                parametro.put("Numero", codigo);  
//                //parametro.put("parameter1", jComboBox_usuarios.getSelectedItem());
//                jr = (JasperReport) JRLoader.loadObjectFromFile(path);
//                JasperPrint jp = JasperFillManager.fillReport(jr,parametro,conn.establecer_conexion());
//                JasperViewer jv = new JasperViewer(jp);
//                viewer.getContentPane().add(jv.getContentPane());
//                viewer.setVisible(true);
//                jv.setTitle("FORMATO DE ACEPTACION");
//
//
//            } catch (JRException ex) {
//                JOptionPane.showMessageDialog(rootPane, ex);
//            }   
//    }
    public void buscar_codigo(){
        try{
         conn.establecer_conexion();
         String sql="select codinmueble from inmuebles where codigo = '"+cod_inmueble_sel+"'";
         ResultSet resultado = conn.consulta(sql);
         while(resultado.next()){
             int codigoreal=Integer.parseInt(resultado.getString(1));
             codinmueble=codigoreal;
         }

        }catch(Exception e){
            System.out.println(""+e);
        }
    }
//    public int buscar_codigo_contrato(){
//        int validar = 0;
//        try{
//         conn.establecer_conexion();
//            String sql="select cod_contrato from arrienda where cod_contrato = '"+jTextField_Cod_contrato.getText().toUpperCase()+"'";
//         ResultSet resultado = conn.consulta(sql);
//         while(resultado.next()){
//             validar++;
//         }
//        }catch(Exception e){
//            System.out.println(""+e);
//        }
//        return validar;
//    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox_uso = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_propietario = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jTextField_valor = new javax.swing.JTextField();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField_total_arr = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 150, 970, 390);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("COMISIONES");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 10, 260, 20);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("PROPIETARIO");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(290, 40, 210, 30);

        jComboBox_uso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_uso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Uso", "COMERCIAL", "VIVIENDA", "NEGOCIO" }));
        jComboBox_uso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_usoItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_uso);
        jComboBox_uso.setBounds(500, 0, 230, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("VALOR MAYOR A");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(540, 40, 210, 30);

        jComboBox_propietario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_propietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Barrio", " " }));
        jComboBox_propietario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_propietarioMouseClicked(evt);
            }
        });
        jComboBox_propietario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_propietarioItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_propietario);
        jComboBox_propietario.setBounds(300, 80, 230, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(800, 70, 150, 40);

        jTextField_valor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_valorFocusLost(evt);
            }
        });
        getContentPane().add(jTextField_valor);
        jTextField_valor.setBounds(540, 80, 220, 30);
        getContentPane().add(dateChooserCombo1);
        dateChooserCombo1.setBounds(30, 80, 120, 30);
        getContentPane().add(dateChooserCombo2);
        dateChooserCombo2.setBounds(170, 80, 120, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Desde                         Hasta");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 60, 230, 20);

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 30, 970, 110);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "TOTAL COMISIONES", 3, 0, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setLayout(null);

        jTextField_total_arr.setEditable(false);
        jTextField_total_arr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_total_arr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_total_arrActionPerformed(evt);
            }
        });
        jTextField_total_arr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_total_arrKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_total_arr);
        jTextField_total_arr.setBounds(810, 40, 150, 26);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TOTAL COMISIONES");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(600, 40, 200, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 550, 970, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        int row=jTable1.getSelectedRow();
//        if(validar==1){
//            if(jTable1.getValueAt(row2, 10).equals("X")){
//                jTable1.setValueAt("", row2, 10);
//            }        
//        }
//        if(jTable1.getValueAt(row, 10).equals("")){
//            jTable1.setValueAt("X", row, 10);
//            cod_inmueble_sel=jTable1.getValueAt(row, 0).toString()  ;
//            validar=1;
//            row2=row;
//        }   
//        
//
//        System.out.println(row2);
//        System.out.println(cod_inmueble_sel);
//        
//        jPanel3.setVisible(true);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consulta="select arr.cod_contrato, ' Codigo: '||inm.codigo||' Direccion '||inm.direccion||' Barrio '||inm.barrio  as inmueble, pro.cedula, pro.nombres||' '||pro.primer_apellido||' '||pro.segundo_apellido as nombre, com.fecha::date, com.valor " +
                        "from comisiones as com INNER JOIN arrienda as arr  on com.cod_arrienda= arr.cod_arrienda INNER JOIN inmuebles as inm on arr.cod_inmueble = inm.codinmueble INNER JOIN propietarios as pro on inm.cod_propietario = pro.cod_propietario "
                        + "where com.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' ";
        consulta=consulta+propietario+valor;
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        limpiartabla();
        int contador = 0;
        try{
        while(n.next()){
            contador=contador+n.getInt(6);
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6)});
        }
         jTextField_total_arr.setText(""+integerFormat(contador));
        }
        catch(Exception e){}
        

        if(jTable1.getRowCount()==0){
            
            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_propietarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_propietarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_propietarioMouseClicked

    private void jComboBox_propietarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_propietarioItemStateChanged
        if(jComboBox_propietario.getSelectedIndex()!=0){
            propietario=" and nombre_completo = '"+jComboBox_propietario.getSelectedItem()+"'";
        }else{
          propietario=" ";
        }
       
    }//GEN-LAST:event_jComboBox_propietarioItemStateChanged

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTextField_total_arrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_total_arrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_arrActionPerformed

    private void jTextField_total_arrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_total_arrKeyTyped

    }//GEN-LAST:event_jTextField_total_arrKeyTyped

    private void jTextField_valorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_valorFocusLost
        if(jTextField_valor.getText().equals("")){
            valor=" ";
        }else{
            valor= " and com.valor >= "+jTextField_valor.getText()+"";

        }
    }//GEN-LAST:event_jTextField_valorFocusLost

    private void jComboBox_usoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_usoItemStateChanged
        if(jComboBox_uso.getSelectedIndex()==0){
            uso=" ";
        }
        else if(jComboBox_uso.getSelectedIndex()==1){
            uso=" and uso = 'COMERCIAL'";
        }
        else if(jComboBox_uso.getSelectedIndex()==2){
            uso=" and uso = 'VIVIENDA'";
        }
        else if(jComboBox_uso.getSelectedIndex()==3){
            uso=" and uso = 'NEGOCIO'";
        }
    }//GEN-LAST:event_jComboBox_usoItemStateChanged

    int novedad=0;
    Conexion conn = new Conexion();
    acceso acc = new acceso();
    int contdisponible=0;
    String cadbuscar="select * from simcard where estado=1 ";
    int valid=0;
    int vlrsimcard=0;
    String user="";
    int codigo=0;
    int codinmueble=0;
    String Bnovedad="NO HAY NOVEDAD";
    String uso = "", clase ="", propietario = "", municipio= "", codigo_inmueble ="", canon ="", valor = "";
    String cod_inmueble_sel = "";
    int row2 = 0;
    int validar = 0, cod_inquilino=0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox_propietario;
    private javax.swing.JComboBox jComboBox_uso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_total_arr;
    private javax.swing.JTextField jTextField_valor;
    // End of variables declaration//GEN-END:variables
}
