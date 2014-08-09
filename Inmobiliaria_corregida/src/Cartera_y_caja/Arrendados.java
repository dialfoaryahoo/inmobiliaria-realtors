/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

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

/**
 *
 * @author Usuario
 */
public class Arrendados extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Arrendados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,750);
//        jLabel10.requestFocus();
        setTitle("INMUEBLES EN ARRIENDO");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
//        buscar_clientes();
        buscar_barrio();
        buscar_municipio();
//        jPanel3.setVisible(false);

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
//    public void buscar_clientes(){
//        Vector cliente = new Vector();
//        try{
//         conn.establecer_conexion();
//         String sql="select nombre_completo from inquilinos ";
//         ResultSet resultado = conn.consulta(sql);
//         cliente.addElement("Seleccione un Inquilino");
//         while(resultado.next()){
//             cliente.addElement(resultado.getString(1));
//         }
//        jComboBox_Inqulino.setModel(new javax.swing.DefaultComboBoxModel(cliente));
//
//        }catch(Exception e){
//            
//        }
//    }
    public void buscar_barrio(){
        Vector barrio = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select DISTINCT barrio from inmuebles where estado = 1";
         ResultSet resultado = conn.consulta(sql);
         barrio.addElement("Seleccione un Bario");
         while(resultado.next()){
             barrio.addElement(resultado.getString(1));
         }
        jComboBox_barrio.setModel(new javax.swing.DefaultComboBoxModel(barrio));

        }catch(Exception e){
            
        }
    }    
    public void buscar_municipio(){
        Vector municipio = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select DISTINCT municipio from inmuebles where estado = 1";
         ResultSet resultado = conn.consulta(sql);
         municipio.addElement("Seleccione un Municipio");
         while(resultado.next()){
             municipio.addElement(resultado.getString(1));
         }
        jComboBox_municipio.setModel(new javax.swing.DefaultComboBoxModel(municipio));

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
        modeloDeMiJTable.addColumn("CONTRATO");
        modeloDeMiJTable.addColumn("NOMBRE INQ");
        modeloDeMiJTable.addColumn("TELEFONO INQ");
        modeloDeMiJTable.addColumn("CELULAR");
        modeloDeMiJTable.addColumn("USO");
        modeloDeMiJTable.addColumn("CLASE");
        modeloDeMiJTable.addColumn("DIRECCION");
        modeloDeMiJTable.addColumn("BARRIO");
        modeloDeMiJTable.addColumn("CANON");
        modeloDeMiJTable.addColumn("ADMINISTRACION");
        modeloDeMiJTable.addColumn("ESTADO ADMIN");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {15, 35, 10,30,60,10,30,50,40, 20, 20, 20};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
//        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 2";
        String consulta="select inm.codigo, arr.cod_contrato, inq.nombre_completo, inq.fijo, inq.celular, inm.uso ,inm.clase, inm.direccion, inm.barrio, inm.canon, inm.admon, inm.admon_estado  "
                + "FROM inmuebles as inm INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino "
                + "where inm.estado = 1 and inm.inmueble_disponible = 2 ";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        int contador=0;
        try{
        while(n.next()){
            String estado_admon = "";
            contador++;
            if(n.getInt(12)==1){
                estado_admon="INCLUIDA";
            }
            else if(n.getInt(12)==2){
                estado_admon="NO INCLUIDA";
            }                        
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),n.getString(10),n.getString(11),estado_admon});
        }
        jTextField_total_arr.setText(""+contador);
        }
        catch(Exception e){}
        
      
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
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_clase = new javax.swing.JComboBox();
        jTextField_codigo = new javax.swing.JTextField();
        jComboBox_municipio = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField_canon = new javax.swing.JTextField();
        jComboBox_uso = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox_barrio = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MUNICIPIO");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(550, 80, 230, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("INFORME DE ARRIENDO");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 10, 260, 20);

        jComboBox_clase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_clase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Clase", "CASA", "APARTAMENTO", "LOCAL" }));
        jComboBox_clase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_claseMouseClicked(evt);
            }
        });
        jComboBox_clase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_claseItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_clase);
        jComboBox_clase.setBounds(320, 100, 230, 30);

        jTextField_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_codigoFocusLost(evt);
            }
        });
        getContentPane().add(jTextField_codigo);
        jTextField_codigo.setBounds(90, 50, 220, 30);

        jComboBox_municipio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_municipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Municipio" }));
        jComboBox_municipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_municipioItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_municipio);
        jComboBox_municipio.setBounds(550, 100, 230, 30);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("CLASE");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(320, 80, 210, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("CODIGO");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(90, 30, 210, 20);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("CONTRATO");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(90, 80, 210, 20);

        jTextField_canon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_canonFocusLost(evt);
            }
        });
        jTextField_canon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_canonKeyTyped(evt);
            }
        });
        getContentPane().add(jTextField_canon);
        jTextField_canon.setBounds(90, 100, 220, 30);

        jComboBox_uso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_uso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Uso", "COMERCIAL", "VIVIENDA", "NEGOCIO" }));
        jComboBox_uso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_usoItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_uso);
        jComboBox_uso.setBounds(320, 50, 230, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USO");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(330, 30, 210, 20);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("BARRIO");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(550, 30, 210, 20);

        jComboBox_barrio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_barrio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Barrio", " " }));
        jComboBox_barrio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_barrioMouseClicked(evt);
            }
        });
        jComboBox_barrio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_barrioItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_barrio);
        jComboBox_barrio.setBounds(550, 50, 230, 30);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514254_Copy v2.png"))); // NOI18N
        jButton3.setText("Ver Todas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(810, 80, 150, 40);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(810, 40, 150, 40);

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 30, 970, 110);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "TOTAL ARRIENDO", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
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
        jTextField_total_arr.setBounds(860, 40, 60, 26);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("TOTAL EN ARRIENDO");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(660, 40, 200, 30);

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

    private void jComboBox_claseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_claseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_claseMouseClicked

    private void jComboBox_claseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_claseItemStateChanged
        if(jComboBox_clase.getSelectedIndex()==0){
            clase=" ";
        }
        else if(jComboBox_clase.getSelectedIndex()==1){
            clase=" and clase = 'CASA'";
        }
        else if(jComboBox_clase.getSelectedIndex()==2){
            clase=" and clase = 'APARTAMENTO'";
        }
        else if(jComboBox_clase.getSelectedIndex()==3){
            clase=" and clase = 'LOCAL'";
        }            
    }//GEN-LAST:event_jComboBox_claseItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String consulta="select inm.codigo, arr.cod_contrato, inq.nombre_completo, inq.fijo, inq.celular, inm.uso ,inm.clase, inm.direccion, inm.barrio, inm.canon, inm.admon, inm.admon_estado FROM inmuebles as inm INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino where inm.estado = 1 and inm.inmueble_disponible = 2  ";
        consulta=consulta+uso+clase+barrio+municipio+codigo_inmueble+canon;
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        limpiartabla();
        int contador = 0;
        try{
        while(n.next()){
            contador++;
            String estado_admon = "";
            if(n.getInt(12)==1){
                estado_admon="INCLUIDA";
            }
            else if(n.getInt(12)==2){
                estado_admon="NO INCLUIDA";
            }            
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),n.getString(10),n.getString(11),estado_admon});
        }
         jTextField_total_arr.setText(""+contador);
        }
        catch(Exception e){}
        

        if(jTable1.getRowCount()==0){
            
            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_barrioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_barrioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_barrioMouseClicked

    private void jComboBox_barrioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_barrioItemStateChanged
        if(jComboBox_barrio.getSelectedIndex()!=0){
            barrio=" and barrio = '"+jComboBox_barrio.getSelectedItem()+"'";
        }else{
          barrio=" ";
        }
       
    }//GEN-LAST:event_jComboBox_barrioItemStateChanged

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

    private void jComboBox_municipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_municipioItemStateChanged
        if(jComboBox_municipio.getSelectedIndex()!=0){
            municipio=" and municipio = '"+jComboBox_municipio.getSelectedItem()+"'";
        }else{
          municipio=" ";
        }
    }//GEN-LAST:event_jComboBox_municipioItemStateChanged

    private void jTextField_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_codigoFocusLost
        if(jTextField_codigo.getText().equals("")){
            codigo_inmueble=" ";
        }else{
            codigo_inmueble= " and codigo = '"+jTextField_codigo.getText().toUpperCase()+"'";
            
        
        }
    }//GEN-LAST:event_jTextField_codigoFocusLost

    private void jTextField_canonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_canonFocusLost
        if(jTextField_canon.getText().equals("")){
            canon=" ";
        }else{
            canon= " and arr.cod_contrato = '"+jTextField_canon.getText().toUpperCase()+"'";
            System.out.println(canon);
        
        }
    }//GEN-LAST:event_jTextField_canonFocusLost

    private void jTextField_canonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_canonKeyTyped
//        char car = evt.getKeyChar();
//        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField_canonKeyTyped

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String consulta="select inm.codigo, arr.cod_contrato, inq.nombre_completo, inq.fijo, inq.celular, inm.uso ,inm.clase, inm.direccion, inm.barrio, inm.canon, inm.admon, inm.admon_estado FROM inmuebles as inm INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino where inm.estado = 1 and inm.inmueble_disponible = 2  ";
        ResultSet n=conn.consulta(consulta);
        limpiartabla();
        int contador = 0;
        try{
        while(n.next()){
            contador++;
            String estado_admon = "";
            if(n.getInt(12)==1){
                estado_admon="INCLUIDA";
            }
            else if(n.getInt(12)==2){
                estado_admon="NO INCLUIDA";
            }            
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),n.getString(10),n.getString(11),estado_admon});
        }
            jTextField_total_arr.setText(""+contador);
        }
        catch(Exception e){}
        

        if(jTable1.getRowCount()==0){
            
            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField_total_arrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_total_arrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_total_arrActionPerformed

    private void jTextField_total_arrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_total_arrKeyTyped

    }//GEN-LAST:event_jTextField_total_arrKeyTyped

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
    String uso = "", clase ="", barrio = "", municipio= "", codigo_inmueble ="", canon ="";
    String cod_inmueble_sel = "";
    int row2 = 0;
    int validar = 0, cod_inquilino=0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox_barrio;
    private javax.swing.JComboBox jComboBox_clase;
    private javax.swing.JComboBox jComboBox_municipio;
    private javax.swing.JComboBox jComboBox_uso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_canon;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_total_arr;
    // End of variables declaration//GEN-END:variables
}
