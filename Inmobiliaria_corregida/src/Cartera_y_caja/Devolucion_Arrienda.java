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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Usuario
 */
public class Devolucion_Arrienda extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Devolucion_Arrienda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        jLabel10.requestFocus();
        setTitle("Arrienda");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
        buscar_clientes();
//        buscar_barrio();
//        buscar_municipio();
        jPanel3.setVisible(false);


        
    }
    public void buscar_clientes(){
        Vector cliente = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre_completo from inquilinos";
         ResultSet resultado = conn.consulta(sql);
         cliente.addElement("Seleccione un Inquilino");
         while(resultado.next()){
             cliente.addElement(resultado.getString(1));
         }
        jComboBox_Inqulino.setModel(new javax.swing.DefaultComboBoxModel(cliente));
        AutoCompleteDecorator.decorate(jComboBox_Inqulino);

        }catch(Exception e){
            
        }
    }
//    public void buscar_barrio(){
//        Vector barrio = new Vector();
//        try{
//         conn.establecer_conexion();
//         String sql="select DISTINCT barrio from inmuebles";
//         ResultSet resultado = conn.consulta(sql);
//         barrio.addElement("Seleccione un Bario");
//         while(resultado.next()){
//             barrio.addElement(resultado.getString(1));
//         }
//        jComboBox_barrio.setModel(new javax.swing.DefaultComboBoxModel(barrio));
//
//        }catch(Exception e){
//            
//        }
//    }    
//    public void buscar_municipio(){
//        Vector municipio = new Vector();
//        try{
//         conn.establecer_conexion();
//         String sql="select DISTINCT municipio from inmuebles";
//         ResultSet resultado = conn.consulta(sql);
//         municipio.addElement("Seleccione un Municipio");
//         while(resultado.next()){
//             municipio.addElement(resultado.getString(1));
//         }
//        jComboBox_municipio.setModel(new javax.swing.DefaultComboBoxModel(municipio));
//
//        }catch(Exception e){
//            
//        }
//    }
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
        modeloDeMiJTable.addColumn("DIRECCION");
        modeloDeMiJTable.addColumn("USO");
        modeloDeMiJTable.addColumn("CLASE");
        modeloDeMiJTable.addColumn("EDIFICIO");
        modeloDeMiJTable.addColumn("BARRIO");
        modeloDeMiJTable.addColumn("MUNICIPIO");
        modeloDeMiJTable.addColumn("CANON");
        modeloDeMiJTable.addColumn("ADMINISTRACION");
        modeloDeMiJTable.addColumn("ESTADO ADMIN");
        modeloDeMiJTable.addColumn("DEVOLUCION");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {15, 30, 10,30,30,10,30,50,40,30,5};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
            modeloDeMiJTable.addRow(new Object[]{"","","","","","","","","","",""});
//        conn.establecer_conexion();
//        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles as inm inner join arrienda as arr on inm.codinmueble = arr.cod_inmueble " +
//                        "where arr.estado = 1";
//        System.out.println(consulta);
//        ResultSet n=conn.consulta(consulta);
//        try{
//        while(n.next()){
//            String estado_admon = "";
//            if(n.getInt(10)==1){
//                estado_admon="INCLUIDA";
//            }
//            else if(n.getInt(10)==2){
//                estado_admon="NO INCLUIDA";
//            }                        
//            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),estado_admon,""});
//        }
//        }
//        catch(Exception e){}
        
      
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
    public void reporte(){
            String ruta=System.getProperty("user.dir");
            ruta=ruta.replace("\\", "/");
            System.out.println(ruta);
            String path=ruta+"/src/Reportes/Asignacion.jasper";
            JasperReport jr = null;
            String nombre=jComboBox_Inqulino.getSelectedItem().toString();
            try {
                JDialog viewer = new JDialog(new javax.swing.JFrame(),"Visualização do Relatório", true); 
                viewer.setSize(1000,800); 
                viewer.setLocationRelativeTo(null); 
                Map parametro = new HashMap();
                System.out.println(nombre);
                parametro.put("NOMBRE_COMPLETO", nombre); 
                parametro.put("Numero", codigo);  
                //parametro.put("parameter1", jComboBox_usuarios.getSelectedItem());
                jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jp = JasperFillManager.fillReport(jr,parametro,conn.establecer_conexion());
                JasperViewer jv = new JasperViewer(jp);
                viewer.getContentPane().add(jv.getContentPane());
                viewer.setVisible(true);
                jv.setTitle("FORMATO DE ACEPTACION");


            } catch (JRException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }   
    }

    public int buscar_codigo_arrienda(){
        int cod_arrienda = 0;
        int row = jTable1.getRowCount();
        try{
         conn.establecer_conexion();
            String sql="select cod_arrienda from arrienda where cod_contrato = '"+jTable1.getValueAt(row, 0)+"'";
         ResultSet resultado = conn.consulta(sql);
         while(resultado.next()){
             cod_arrienda=resultado.getInt(1);
         }
        }catch(Exception e){
            System.out.println(""+e);
        }
        return cod_arrienda;
    }    
    
    public void causacion (int causar){
        //mes_causar(); como es solo una causacion, no pondre mes.
        int validar=0;
        conn.establecer_conexion();
        String consulta1 = "select date_part('day', arr.fecha_ini), arr.cod_inmueble, inm.canon, arr.causacion, arr.cod_arrienda, "
                + "arr.cod_inquilino, inm.cod_propietario, inm.comision , inm.admon_estado, inm.admon "
                + "from arrienda as arr  INNER JOIN inmuebles  as inm on  arr.cod_inmueble = inm.codinmueble "
                + "where arr.estado = 1 and inm.codinmueble = "+codinmueble;
        System.out.println(consulta1);
        ResultSet n=conn.consulta(consulta1);
        try {
            while (n.next()) {                
                //Variables
                int canon= 0, valor_a_pagar=0, dias_causados=0, dia_tomo =0, cod_inmueble=0, cod_arrienda=0, cod_inquilino=0, cod_propietario=0, comision_inmueble=0, valor_pro=0, admon_estado=0, admon=0, admon_i=0, admon_p=0;
                dia_tomo= n.getInt(1);
                cod_inmueble=n.getInt(2);
                canon= n.getInt(3);
                cod_arrienda=n.getInt(5);
                cod_inquilino=n.getInt(6);
                cod_propietario=n.getInt(7);
                comision_inmueble=n.getInt(8);
                admon=n.getInt(10);
                

                //este if, cuando se cause por primera vez.
                    dias_causados=(30-dia_tomo)+5;
                    valor_a_pagar = (canon/30)*dias_causados;
                    System.out.println("Dias Causados: "+dias_causados);
                    jTextField_Cod_contrato1.setText(""+dias_causados);
                    //Verifica si hay administracion incluida
                    if(n.getInt(9)==1){
                        admon_i = (admon/30)*dias_causados;
                        admon_p = admon - (admon/30)*dias_causados;
                        String insert3 = "insert into administracion (cod_arrienda, fecha, valor, cancelada) values("+cod_arrienda+", now(), "+admon+", 1)";
                        if(causar==2){
                            conn.insertar(insert3);                            
                        }
                        System.out.println(insert3);
                    }
                    comision_inmueble = (comision_inmueble/30)*dias_causados;
                    valor_pro = valor_a_pagar - admon_i - admon_p - comision_inmueble;
                    jTextField_Valor_A_Pagar.setText(""+valor_a_pagar);
                    
                    String update1 = "update arrienda set causacion = 2 where  cod_arrienda = "+cod_arrienda;
                    String update2 = "update inquilinos set saldo= saldo+"+valor_a_pagar+" where  cod_inquilino = "+cod_inquilino; //preguntar sobre los inquilinos
                    String update3 = "update propietarios set saldo= saldo+"+valor_pro+" where  cod_propietario = "+cod_propietario;
//                    String insert1 = "insert into causacion (cod_arrienda, mes, fecha, valor, nota) values("+cod_arrienda+", '"+mes_causar+"', now(), "+valor_a_pagar+", '"+jTextField_nota.getText().toUpperCase()+"')";
                    String insert2 = "insert into comisiones (cod_arrienda, fecha, valor) values ("+cod_arrienda+", now(), "+comision_inmueble+")";
                    
                    if(causar==2){
                        conn.Dactualizar2(update1);
                        conn.Dactualizar2(update2);
                        conn.Dactualizar2(update3);
//                        conn.Dinsertar2(insert1);
                        conn.Dinsertar2(insert2);
                        validar++;
                    }

                    
                    System.out.println(update1);
                    System.out.println(update2);
                    System.out.println(update3);
//                    System.out.println(insert1);
                    System.out.println(insert2);
                    
                    
                    
                
//                //cuando se ha causado > 1 
//                if(n.getInt(4)==2){
//                    //Verificar si hay administracion incluida
//                    if(n.getInt(9)==1){
//                        admon_i = admon;
//                        String insert3 = "insert into administracion (cod_arrienda, fecha, valor, cancelada) values("+cod_arrienda+", now(), "+admon+", 1)";
//                        
//                        conn.Dinsertar2(insert3);
//                        System.out.println(insert3);
//                    }                    
//                    valor_pro = canon - comision_inmueble - admon_i ;
                    
//                    String update2 = "update inquilinos set saldo= saldo+"+canon+" where  cod_inquilino = "+cod_inquilino;
//                    String update3 = "update propietarios set saldo= saldo+"+valor_pro+" where  cod_propietario = "+cod_propietario;  
//                    String insert1 = "insert into causacion (cod_arrienda, mes, fecha, valor, nota) values("+cod_arrienda+", '"+mes_causar+"', now(), "+canon+", '"+jTextField_nota.getText().toUpperCase()+"')";   
//                    String insert2 = "insert into comisiones (cod_arrienda, fecha, valor) values ("+cod_arrienda+", now(), "+comision_inmueble+")";                    
                    
//                    conn.Dactualizar2(update2);
//                    conn.Dactualizar2(update3);
////                    conn.Dinsertar2(insert1);
//                    conn.Dinsertar2(insert2);
//                    validar++;
//                    System.out.println(update2);
//                    System.out.println(update3);
////                    System.out.println(insert1);
//                    System.out.println(insert2);
//                }

            }
        } catch (Exception e) {}
        if(validar!=0){
            conn.JOptionShowMessage("+1", "", "CAUSACION EXITOSA!");
            this.dispose();
        }
    }    
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
        jComboBox_Inqulino = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_direccion = new javax.swing.JTextField();
        jTextField_fijo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField_Cod_contrato = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Cod_contrato1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField_Valor_A_Pagar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

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
        jScrollPane1.setBounds(10, 140, 970, 370);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Cliente", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText(" Saldo");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(620, 40, 100, 30);

        jTextField_saldo.setEditable(false);
        jTextField_saldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_saldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_saldoFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_saldo);
        jTextField_saldo.setBounds(720, 40, 210, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Inquilino");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 40, 100, 30);

        jTextField_cedula.setEditable(false);
        jTextField_cedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_cedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_cedulaFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_cedula);
        jTextField_cedula.setBounds(120, 70, 200, 30);

        jComboBox_Inqulino.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_Inqulino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un plan" }));
        jComboBox_Inqulino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_InqulinoItemStateChanged(evt);
            }
        });
        jPanel1.add(jComboBox_Inqulino);
        jComboBox_Inqulino.setBounds(120, 40, 500, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cedula");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 70, 100, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText(" Direccion");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(320, 70, 100, 30);

        jTextField_direccion.setEditable(false);
        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_direccionFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_direccion);
        jTextField_direccion.setBounds(420, 70, 200, 30);

        jTextField_fijo.setEditable(false);
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

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel3.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("MOTIVO DEVOLUCION");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(30, 10, 250, 40);

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton1.setText("Realizar Devolucion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(280, 90, 470, 50);

        jTextField_Cod_contrato.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField_Cod_contrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Cod_contratoActionPerformed(evt);
            }
        });
        jTextField_Cod_contrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Cod_contratoKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_Cod_contrato);
        jTextField_Cod_contrato.setBounds(280, 10, 470, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Valor a Pagar");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(410, 10, 250, 40);

        jTextField_Cod_contrato1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField_Cod_contrato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Cod_contrato1ActionPerformed(evt);
            }
        });
        jTextField_Cod_contrato1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Cod_contrato1KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_Cod_contrato1);
        jTextField_Cod_contrato1.setBounds(590, 50, 160, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Fecha Entrega");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(450, 50, 140, 40);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Valor a Pagar");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(30, 50, 250, 40);

        jTextField_Valor_A_Pagar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField_Valor_A_Pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Valor_A_PagarActionPerformed(evt);
            }
        });
        jTextField_Valor_A_Pagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Valor_A_PagarKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_Valor_A_Pagar);
        jTextField_Valor_A_Pagar.setBounds(280, 50, 170, 40);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 530, 970, 150);
        jPanel3.getAccessibleContext().setAccessibleName("Asignar");

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 130, 980, 130);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        if(!jTable1.getValueAt(row, 0).equals("")){
            if(validar==1){

                if(jTable1.getValueAt(row2, 10).equals("X")){
                    jTable1.setValueAt("", row2, 10);
                    
                }        
            }
            if(jTable1.getValueAt(row, 10).equals("")){
                jTable1.setValueAt("X", row, 10);
                causacion(1);
                validar=1;
                row2=row;
            }
            jPanel3.setVisible(true);
        }
        System.out.println(row2);
        System.out.println(cod_inmueble_sel);
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox_InqulinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_InqulinoItemStateChanged
        String consulta = "select cedula, saldo, direccion_casa, fijo, cod_inquilino from inquilinos where nombre_completo='"+jComboBox_Inqulino.getSelectedItem()+"'";
        ResultSet m= conn.consulta(consulta);
        try {
            while(m.next()){
                jTextField_cedula.setText(m.getString(1));
                jTextField_saldo.setText(m.getString(2));
                jTextField_direccion.setText(m.getString(3));
                jTextField_fijo.setText(m.getString(4));
                cod_inquilino = m.getInt(5);
            }
        } catch (Exception e) {
        }
        conn.establecer_conexion();
        String consulta2="select arr.cod_contrato, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado, inm.codinmueble FROM inmuebles as inm inner join arrienda as arr on inm.codinmueble = arr.cod_inmueble " +
                        "where arr.estado = 1 and cod_inquilino = "+cod_inquilino;
        System.out.println(consulta2);
        ResultSet n=conn.consulta(consulta2);
        limpiartabla();
        try{
        while(n.next()){
            String estado_admon = "";
            if(n.getInt(10)==1){
                estado_admon="INCLUIDA";
            }
            else if(n.getInt(10)==2){
                estado_admon="NO INCLUIDA";
            }
            codinmueble=n.getInt(11);
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),estado_admon,""});
        }
        if(jTable1.getRowCount()==0){
//            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }        
        }
        catch(Exception e){}
    }//GEN-LAST:event_jComboBox_InqulinoItemStateChanged

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
        if(!jTextField_Cod_contrato.getText().toUpperCase().equals("")){
                if(jComboBox_Inqulino.getSelectedIndex()!=0){
                int crow=modeloDeMiJTable.getRowCount();
                String mensaje="";
                for(int i=0;i<crow;i++){
                    if(jTable1.getValueAt(i,10).equals("X")){
                        mensaje+="El Inmueble Asignado Es: "+jTable1.getValueAt(i, 0);
                        mensaje+="\n Con Valor de Canon: "+jTable1.getValueAt(i, 7);
                        mensaje+="\n Al Inquilino: "+jComboBox_Inqulino.getSelectedItem();
                    }
                }
                mensaje+="\n\nDESEA REALIZAR LA Devolucion?";
                int a=JOptionPane.showConfirmDialog(rootPane, mensaje);
                if(a==0){
                for(int i=0;i<crow;i++){
                    if(jTable1.getValueAt(i,10).equals("X")){
                        String update1 = "update arrienda set fecha_fin = now(), causacion = 1,  usuario = '"+acc.getUsuario()+"' , motivo_devolucion  = '"+jTextField_Cod_contrato.getText().toUpperCase()+"', estado = 2 where cod_arrienda = "+buscar_codigo_arrienda();
                        String update2="update inmuebles set inmueble_disponible = 1 where codinmueble = "+codinmueble;
//                        conn.Dinsertar2(insert);
                        conn.Dactualizar2(update1);
                        conn.Dactualizar2(update2);
                        valid++;

                        }
                    else{
                    }
                }
                //fin del for
                    if(valid!=0){
                            conn.JOptionShowMessage("+1", null, "INMUEBLE "+cod_inmueble_sel+", DEVOLUCION EXITOSA");
                            this.dispose();
                   }else{
                            conn.JOptionShowMessage("+1",null, "DEBE ESCOGER UN INMUEBLE");

                    }
                }
                }else{
                    conn.JOptionShowMessage("+1", null, "DEBE ESCOGER UN INQULINO PARA ASIGNAR");
                }
 
        }else{
            conn.JOptionShowMessage("+1", "", "LLENAR LOS DATOS DE CODIGO DE CONTRATO Y OBSERVACION");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_Cod_contratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Cod_contratoKeyTyped
    }//GEN-LAST:event_jTextField_Cod_contratoKeyTyped

    private void jTextField_Cod_contratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Cod_contratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Cod_contratoActionPerformed

    private void jTextField_Cod_contrato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Cod_contrato1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Cod_contrato1ActionPerformed

    private void jTextField_Cod_contrato1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Cod_contrato1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Cod_contrato1KeyTyped

    private void jTextField_Valor_A_PagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Valor_A_PagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Valor_A_PagarActionPerformed

    private void jTextField_Valor_A_PagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Valor_A_PagarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Valor_A_PagarKeyTyped

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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox_Inqulino;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cod_contrato;
    private javax.swing.JTextField jTextField_Cod_contrato1;
    private javax.swing.JTextField jTextField_Valor_A_Pagar;
    private javax.swing.JTextField jTextField_cedula;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_fijo;
    private javax.swing.JTextField jTextField_saldo;
    // End of variables declaration//GEN-END:variables
}
