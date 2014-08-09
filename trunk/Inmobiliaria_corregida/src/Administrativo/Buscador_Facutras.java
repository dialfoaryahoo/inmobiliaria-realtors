/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativo;

//import Models.Renderito;
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
public class Buscador_Facutras extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Buscador_Facutras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,750);
//        jLabel10.requestFocus();
        setTitle("COMISIONES");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
//        buscar_propietario();
//        buscar_inquilino();
        jPanel3.setVisible(false);
        jComboBox_usuario.setEnabled(false);


        
    }
    public void buscar_propietario(){
        Vector propietario = new Vector();
        try{
            jComboBox_usuario.setEnabled(true);
            conn.establecer_conexion();
            String sql="select nombre_completo from propietarios order by nombre_completo";
            ResultSet resultado = conn.consulta(sql);
            propietario.addElement("Seleccione un propietario");
            while(resultado.next()){
                propietario.addElement(resultado.getString(1));
            }
            jComboBox_usuario.setModel(new javax.swing.DefaultComboBoxModel(propietario));
            AutoCompleteDecorator.decorate(jComboBox_usuario);
            tipo_usuario="INQUILINO";
        }catch(Exception e){}
    }
    
    public void buscar_inquilino(){
        Vector propietario = new Vector();
        try{
            jComboBox_usuario.setEnabled(true);
             conn.establecer_conexion();
             String sql="select nombre_completo from inquilinos order by nombre_completo";
             ResultSet resultado = conn.consulta(sql);
             propietario.addElement("Seleccione un Inquilino");
             while(resultado.next()){
                 propietario.addElement(resultado.getString(1));
             }
            jComboBox_usuario.setModel(new javax.swing.DefaultComboBoxModel(propietario));
            AutoCompleteDecorator.decorate(jComboBox_usuario);
            //tipo de usuario
            tipo_usuario="INQUILINO";
            }catch(Exception e){}
    }    

    public void buscar_Otro_Usuario(){
        Vector propietario = new Vector();
        try{
            jComboBox_usuario.setEnabled(true);
             conn.establecer_conexion();
             String sql="select nombre_completo from otros_usuarios order by nombre_completo";
             ResultSet resultado = conn.consulta(sql);
             propietario.addElement("Seleccione un Usuario");
             while(resultado.next()){
                 propietario.addElement(resultado.getString(1));
             }
            jComboBox_usuario.setModel(new javax.swing.DefaultComboBoxModel(propietario));
            AutoCompleteDecorator.decorate(jComboBox_usuario);
            tipo_usuario="Otros Usuarios";
            }catch(Exception e){}
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
        
        modeloDeMiJTable.addColumn("COD FACTURA");
        modeloDeMiJTable.addColumn("TIPO FACTURA");
        modeloDeMiJTable.addColumn("TIPO USUARIO");
        modeloDeMiJTable.addColumn("NOMBRE");
        modeloDeMiJTable.addColumn("VALOR");
        modeloDeMiJTable.addColumn("FECHA");
        modeloDeMiJTable.addColumn("USUARIO");
        modeloDeMiJTable.addColumn("SELECCIONE");
        
        jTable1.setModel(modeloDeMiJTable);
//            modeloDeMiJTable.addRow(new Object[]{"","","","","",""});
        
        int[] anchos = {15, 15,100,170,30,25,20,20};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
//        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 2";
        String consulta="select fac.serial, fac.tipo_factura, fac.tipo_usuario, fac.total, fac.fecha::date, fac.usuario, fac.cod_usuario, fac.usuario from facturas as fac ";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            String nombre ="";
            if(n.getString(3).equals("INQUILINO")){
                String Consulta2 ="select nombre_completo from inquilinos where cod_inquilino="+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {                    
                    nombre=n1.getString(1);
                    System.out.println("entro 1");
                }
            }
            else if(n.getString(3).equals("PROPIETARIO")){
                String Consulta2 ="select nombre_completo from propietarios where cod_propietario="+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {                    
                    nombre=n1.getString(1);
                    System.out.println("entro 2");
                }                
            }            
            else if(n.getString(3).equals("Otros Usuarios")){
                String Consulta2 ="select nombre_completo from otros_usuarios where cod_usuario = "+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {                    
                    nombre=n1.getString(1);
                    System.out.println("entro 3");
                }                
            }                        
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),nombre,n.getString(4),n.getString(5),n.getString(8),""});
        }
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

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextField_total_arr = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton_imprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox_usuario = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox_tipo_factura = new javax.swing.JComboBox();
        jComboBox_tipo_usuario = new javax.swing.JComboBox();

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
        jScrollPane1.setBounds(10, 130, 970, 410);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "IMPRMIR FACTURA", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
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
        jTextField_total_arr.setBounds(450, 40, 60, 26);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("IMPRIMIR FACTURA");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(610, 40, 200, 30);

        jButton_imprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1398654205_printer.png"))); // NOI18N
        jButton_imprimir.setText("Imprimir");
        jButton_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirActionPerformed(evt);
            }
        });
        jPanel3.add(jButton_imprimir);
        jButton_imprimir.setBounds(810, 30, 150, 50);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 550, 970, 100);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "BUSCAR FACTURAS", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("USUARIO");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(590, 20, 210, 30);

        jComboBox_usuario.setEditable(true);
        jComboBox_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_usuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        jComboBox_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_usuarioMouseClicked(evt);
            }
        });
        jComboBox_usuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_usuarioItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox_usuario);
        jComboBox_usuario.setBounds(570, 60, 250, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("TIPO USUARIO");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(420, 20, 140, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(833, 50, 120, 40);
        jPanel1.add(dateChooserCombo2);
        dateChooserCombo2.setBounds(130, 60, 120, 30);
        jPanel1.add(dateChooserCombo1);
        dateChooserCombo1.setBounds(10, 60, 120, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Desde                         Hasta");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 30, 230, 20);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("TIPO FACTURA");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(250, 20, 150, 30);

        jComboBox_tipo_factura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_tipo_factura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo Factura", "RECIBO CAJA", "FACTURA", "EGRESO" }));
        jComboBox_tipo_factura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipo_facturaItemStateChanged(evt);
            }
        });
        jComboBox_tipo_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_tipo_facturaActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_tipo_factura);
        jComboBox_tipo_factura.setBounds(260, 60, 150, 30);

        jComboBox_tipo_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_tipo_usuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "INQUILINO", "PROPIETARIO", "Otro_Usuario" }));
        jComboBox_tipo_usuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipo_usuarioItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox_tipo_usuario);
        jComboBox_tipo_usuario.setBounds(420, 60, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 10, 960, 110);

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
        String consulta="select fac.serial, fac.tipo_factura, fac.tipo_usuario, fac.total, fac.fecha::date, fac.usuario, fac.cod_usuario, fac.usuario from facturas as fac "
                        + "where fac.fecha::Date between '"+dateChooserCombo1.getText()+"' and '"+dateChooserCombo2.getText()+"' ";
        consulta=consulta+tipo_factura+inquilino+propietario;
        consulta+= "order by fac.fecha"; 
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        limpiartabla();
        int contador = 0;
        try{
        while(n.next()){
            String nombre ="";
            if(n.getString(3).equals("INQUILINO")){
                String Consulta2 ="select nombre_completo from inquilinos where cod_inquilino="+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {                    
                    nombre=n1.getString(1);
                    System.out.println("entro 1");
                }
            }
            else if(n.getString(3).equals("PROPIETARIO")){
                String Consulta2 ="select nombre_completo from propietarios where cod_propietario ="+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {                    
                    nombre=n1.getString(1);
                    System.out.println("entro 2");
                }                
            }            
            else if(n.getString(3).equals("Otros Usuarios")){
                String Consulta2 ="select nombre_completo from otros_usuarios where cod_usuario = "+n.getString(7);
                System.out.println(Consulta2);
                ResultSet n1 =conn.consulta(Consulta2);
                while (n1.next()) {
                    nombre=n1.getString(1);
                    System.out.println("entro 3");
                }                
            }                        
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),nombre,n.getString(4),n.getString(5),n.getString(6),""});
        }
         jTextField_total_arr.setText(""+contador);
        }
        catch(Exception e){}
        

        if(jTable1.getRowCount()==0){
            
            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_usuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_usuarioMouseClicked

    private void jComboBox_usuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_usuarioItemStateChanged
        if(jComboBox_usuario.getSelectedIndex()!=0){
            if(tipo_usuario.equals("INQUILINO")){
                tipo_usuario2 = " and tipo_usuario = 'INQUILINO' ";
                String consulta = "select cod_inquilino from inquilinos where nombre_completo = '"+jComboBox_usuario.getSelectedItem()+"'";
                ResultSet n = conn.consulta(consulta);
                    try {
                        int  cod_usuario = 0;
                        while (n.next()) {                    
                            cod_usuario = n.getInt(1);
                        }
                    tipo_usuario2 += " and fac.cod_usuario="+cod_usuario+" ";
                    } catch (Exception e) {
                    }
            }
            else if(tipo_usuario.equals("PROPIETARIO")){
                tipo_usuario2 = " and tipo_usuario = 'PROPIETARIO' ";
                String consulta = "select cod_propietario from PROPIETARIO where nombre_completo = '"+jComboBox_usuario.getSelectedItem()+"'";
                ResultSet n = conn.consulta(consulta);
                    try {
                        int  cod_usuario = 0;
                        while (n.next()) {                    
                            cod_usuario = n.getInt(1);
                        }
                    tipo_usuario2 += " and fac.cod_usuario="+cod_usuario+" ";
                    } catch (Exception e) {
                    }
            }
            else if(tipo_usuario.equals("Otro Usuario")){
                tipo_usuario2 = " and tipo_usuario = 'Otro Usuario' ";
                String consulta = "select cod_usuario from Otros_Usuarios where nombre_completo = '"+jComboBox_usuario.getSelectedItem()+"'";
                ResultSet n = conn.consulta(consulta);
                    try {
                        int  cod_usuario = 0;
                        while (n.next()) {                    
                            cod_usuario = n.getInt(1);
                        }
                    tipo_usuario2 += " and fac.cod_usuario="+cod_usuario+" ";
                    } catch (Exception e) {
                    }                
            }            
        }else{
            tipo_usuario2 = "";
        }
       
    }//GEN-LAST:event_jComboBox_usuarioItemStateChanged

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTextField_total_arrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_total_arrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_arrActionPerformed

    private void jTextField_total_arrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_total_arrKeyTyped

    }//GEN-LAST:event_jTextField_total_arrKeyTyped

    private void jComboBox_tipo_facturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tipo_facturaItemStateChanged
        if(jComboBox_tipo_factura.getSelectedIndex()==0){
            tipo_factura=" ";
        }        
        else if(jComboBox_tipo_factura.getSelectedIndex()==1){
            tipo_factura=" and fac.tipo_factura = 'RECIBO_CAJA'";
        }
        else if(jComboBox_tipo_factura.getSelectedIndex()==2){
            tipo_factura=" and fac.tipo_factura = 'FACTURA'";
        }
        else if(jComboBox_tipo_factura.getSelectedIndex()==3){
            tipo_factura=" and fac.tipo_factura = 'EGRESO'";
        }        
        
        
    }//GEN-LAST:event_jComboBox_tipo_facturaItemStateChanged

    private void jButton_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirActionPerformed
    conn.establecer_conexion();
    Numero_a_Letra NumLetra = new Numero_a_Letra();
    int serial = 0;
    serial = Integer.parseInt(serialimp);    
    String total_letras="";
    String total_letras2="";

    //TEMPORAL PARA SACAR LE VALOR EN LETRAS

        String consulta2="select total from facturas where tipo_factura = 'EGRESO' and serial ="+serial;
        ResultSet rs2 = conn.consulta(consulta2);
            try {
                while (rs2.next()) {
                    total_letras= (rs2.getString(1));
                    System.out.println(total_letras);
                    total_letras2=NumLetra.Convertir(total_letras, true);
                }
                System.out.println(total_letras2);
        } catch (Exception e) {
        }
            //seriaimp=30;
            contimp++;
            String path="D:/Proyecto/Inmobiliaria_corregida/src/Reportes/egreso.jasper";
            JasperReport jr = null;
            try {
                if(contimp!=0){
                    Dialog viewer = new JDialog(new javax.swing.JFrame(),"Visualização do Relatório", true); 
                    viewer.setSize(1000,800);
                    viewer.setLocationRelativeTo(null);                    
                    Map parametro = new HashMap();
                    parametro.put("serial", serial);
                    parametro.put("tipo_factura", "EGRESO");
                    parametro.put("total_letras", total_letras2);            
                    jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                    JasperPrint jp = JasperFillManager.fillReport(jr,parametro,conn.establecer_conexion());
                    JasperViewer jv = new JasperViewer(jp);
                    
//                    viewer.getContentPane().add(jv.getContentPane());
//                    jv.isActive();                    
//                    jv.toFront();                    
                    jv.setVisible(true);                    
                    //viewer.setVisible(true);

                    jv.setTitle("EGRESO");
                }else{
                    Conexion.JOptionShowMessage("+1", null, "NO HA GENERADO NINGUN RECIBO");
                }
            } catch (JRException ex) {
            }            
    }//GEN-LAST:event_jButton_imprimirActionPerformed

    private void jComboBox_tipo_usuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tipo_usuarioItemStateChanged
        if(jComboBox_tipo_usuario.getSelectedIndex()==0){
            jComboBox_usuario.setSelectedIndex(0);
            jComboBox_usuario.setEnabled(false);

        }else if(jComboBox_tipo_usuario.getSelectedIndex()==1){
            buscar_inquilino();
        }else if(jComboBox_tipo_usuario.getSelectedIndex()==2){
            buscar_propietario();
        }else if(jComboBox_tipo_usuario.getSelectedIndex()==3){
            buscar_Otro_Usuario();
        }
        
    }//GEN-LAST:event_jComboBox_tipo_usuarioItemStateChanged

    private void jComboBox_tipo_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_tipo_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_tipo_facturaActionPerformed

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
    String cod_inmueble_sel = "", inquilino="", nombre_inquilino="", nombre_propietario= "" ;
    int row2 = 0;
    int validar = 0, cod_inquilino=0, cod_propietario=0;
    int contimp = 0;
    String serialimp= "", tipo_usuario="", tipo_usuario2="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_imprimir;
    private javax.swing.JComboBox jComboBox_tipo_factura;
    private javax.swing.JComboBox jComboBox_tipo_usuario;
    private javax.swing.JComboBox jComboBox_usuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_total_arr;
    // End of variables declaration//GEN-END:variables
}
