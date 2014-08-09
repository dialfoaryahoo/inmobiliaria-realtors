/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo;

//import Models.Renderito;
import Cartera_y_caja.*;
import Models.Numero_a_Letra;
import Models.acceso;
import inmobiliaria_fase01.Conexion;
import java.awt.Dialog;
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
public class Informe_Propietarios extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Informe_Propietarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,750);
//        jLabel10.requestFocus();
        setTitle("INFORME PROPIETARIOS");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
        buscar_propietario();
        jPanel3.setVisible(true);
    }
    
    public void buscar_propietario(){
        Vector propietario = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre_completo from propietarios order by nombre_completo";
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
        
        modeloDeMiJTable.addColumn("CEDULA");
        modeloDeMiJTable.addColumn("CODIGO");
        modeloDeMiJTable.addColumn("NOMBRE");
        modeloDeMiJTable.addColumn("TELEFONO");
        modeloDeMiJTable.addColumn("CELULAR");
        modeloDeMiJTable.addColumn("SALDO");
        modeloDeMiJTable.addColumn("ESTADO");
        modeloDeMiJTable.addColumn("SELECCIONE");
        
        jTable1.setModel(modeloDeMiJTable);
//            modeloDeMiJTable.addRow(new Object[]{"","","","","",""});
        
        int[] anchos = {15, 15,270,70,30,25,20,20};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
//        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 2";
        String consulta="select pro.cedula, pro.codigo, pro.nombre_completo, pro.fijo, pro.celular, pro.saldo, pro.estado_propietario from propietarios as pro ";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        int contador = 0;
        int debe = 0;
        try{
            while(n.next()){
                contador++;
                String estado="";
                    if(n.getInt(7)==1){
                        estado="ACTIVO";
                    }
                    if(n.getInt(7)==2){
                        estado="ELIMINADO";
                    } 
                debe+=n.getInt(6); 
                modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),estado,""});
                }
            jTextField_total_arr.setText(""+contador);
            jTextField_total_saldo.setText(""+debe);
        }
        catch(Exception e){
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextField_total_arr = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_total_saldo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox_propietario = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextField_saldo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField_codigo = new javax.swing.JTextField();

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
        jScrollPane1.setBounds(10, 140, 970, 400);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "PROPIETARIOS", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
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
        jTextField_total_arr.setBounds(830, 40, 120, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TOTAL PROPIETARIOS");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(620, 40, 220, 30);

        jTextField_total_saldo.setEditable(false);
        jTextField_total_saldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_total_saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_total_saldoActionPerformed(evt);
            }
        });
        jTextField_total_saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_total_saldoKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_total_saldo);
        jTextField_total_saldo.setBounds(410, 40, 220, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("TOTAL SALDO");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(210, 40, 220, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 550, 970, 100);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "BUSCAR PROPIETARIOS", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setLayout(null);

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
        jPanel1.add(jComboBox_propietario);
        jComboBox_propietario.setBounds(520, 40, 230, 40);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(800, 40, 140, 40);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("PROPIETARIO");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(510, 10, 250, 30);

        jTextField_saldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_saldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_saldoFocusLost(evt);
            }
        });
        jTextField_saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_saldoKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_saldo);
        jTextField_saldo.setBounds(290, 40, 180, 40);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DEUDA MAYOR A:");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(280, 10, 200, 30);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("CEDULA/CODIGO");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(50, 10, 210, 30);

        jTextField_codigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_codigoFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_codigo);
        jTextField_codigo.setBounds(70, 40, 180, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 10, 950, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        if(validar==1){
            if(jTable1.getValueAt(row2, 7).equals("X")){
                jTable1.setValueAt("", row2, 7);
            }        
        }
        if(jTable1.getValueAt(row, 7).equals("")){
            jTable1.setValueAt("X", row, 7);
            serialimp=jTable1.getValueAt(row, 0).toString()  ;
            validar=1;
            row2=row;
        }   
        System.out.println(row2);
        System.out.println(serialimp);
        
        jPanel3.setVisible(true);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consulta="select pro.cedula, pro.codigo, pro.nombre_completo, pro.fijo, pro.celular, pro.saldo, pro.estado_propietario from propietarios as pro ";
        int valid_and = 0, valid_where=0;
        if(!jTextField_saldo.getText().equals("")||!jTextField_codigo.getText().equals("")||jComboBox_propietario.getSelectedIndex()!=0){
            consulta+= " where ";
        }
        if(!jTextField_codigo.getText().equals("")){
            consulta+=codigo_pro;
            valid_and++;
        }
        if(!jTextField_saldo.getText().equals("")){
            if(valid_and!=0){
                consulta+=" and "+saldo;
            }else{
            consulta+=saldo;
            }
         }
        if(jComboBox_propietario.getSelectedIndex()!=0){
            if(valid_and!=0){
                consulta+=" and "+nombre_propietario;
            }else{
                consulta+=nombre_propietario;
            }
        }
        consulta+= " order by nombre_completo"; 
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        limpiartabla();
        int contador = 0;
        int debe = 0;
        try{
        while(n.next()){
            String estado="";
                if(n.getInt(7)==1){
                    estado="ACTIVO";
                }
                if(n.getInt(7)==2){
                    estado="ELIMINADO";
                }                    
            debe+=n.getInt(6);                 
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),estado,""});
            contador++;
            
        }
         jTextField_total_arr.setText(""+contador);
         jTextField_total_saldo.setText(""+debe);
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
            nombre_propietario=" nombre_completo = '"+jComboBox_propietario.getSelectedItem()+"'";
            System.out.println("entro");
        }else{
            nombre_propietario = " ";
            System.out.println("NO entro");
        }
       
    }//GEN-LAST:event_jComboBox_propietarioItemStateChanged

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTextField_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_codigoFocusLost
            if(jTextField_codigo.getText().trim().matches(".*\\D+.*")){
                System.out.println("NO ES NUMERO O CODIGO");
                codigo_pro=" codigo='"+jTextField_codigo.getText().toUpperCase()+"'";
            }
            else{
                System.out.println("SI ES NUMERO =D O CEDULA");
                codigo_pro=" cedula="+jTextField_codigo.getText().toUpperCase()+" ";        
            }
            if(jTextField_codigo.getText().equals("")){
                codigo_pro=" ";
            }
    }//GEN-LAST:event_jTextField_codigoFocusLost

    private void jTextField_saldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_saldoFocusLost
        if(!jTextField_saldo.getText().equals("")){
            saldo = " saldo >= "+jTextField_saldo.getText()+" ";
        }
        else{
            saldo = " ";
        }
    }//GEN-LAST:event_jTextField_saldoFocusLost

    private void jTextField_saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_saldoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField_saldoKeyTyped

    private void jTextField_total_arrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_total_arrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_arrActionPerformed

    private void jTextField_total_arrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_total_arrKeyTyped

    }//GEN-LAST:event_jTextField_total_arrKeyTyped

    private void jTextField_total_saldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_total_saldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_saldoActionPerformed

    private void jTextField_total_saldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_total_saldoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_saldoKeyTyped

    int novedad=0;
    String tipo_factura= "";
    Conexion conn = new Conexion();
    acceso acc = new acceso();
    int contdisponible=0;
    int valid=0;
    int vlrsimcard=0;
    String user="";
    int codigo=0;
    int codinmueble=0;
    String Bnovedad="NO HAY NOVEDAD";
    String uso = "", clase ="", propietario = "", municipio= "", codigo_inmueble ="", canon ="", valor = "";
    //String cod_inmueble_sel = "", inquilino="", nombre_inquilino="", nombre_propietario= "" ;
    int row2 = 0;
    int validar = 0, cod_inquilino=0, cod_propietario=0;
    int contimp = 0;
    //reporte propietarios
    String serialimp= "", codigo_pro = "", saldo="", nombre_propietario="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox_propietario;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_saldo;
    private javax.swing.JTextField jTextField_total_arr;
    private javax.swing.JTextField jTextField_total_saldo;
    // End of variables declaration//GEN-END:variables
}
