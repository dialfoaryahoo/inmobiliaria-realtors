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
public class Arrienda extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    private DefaultTableModel modeloDeMiJTable2; 
    public Arrienda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(1000,700);
        jLabel10.requestFocus();
        setTitle("Arrienda");
        setLocationRelativeTo(rootPane);
        
        llenartabla();
        buscar_clientes();
        buscar_barrio();
        buscar_municipio();
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
    public void buscar_barrio(){
        Vector barrio = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select DISTINCT barrio from inmuebles";
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
         String sql="select DISTINCT municipio from inmuebles";
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
        modeloDeMiJTable.addColumn("DIRECCION");
        modeloDeMiJTable.addColumn("USO");
        modeloDeMiJTable.addColumn("CLASE");
        modeloDeMiJTable.addColumn("EDIFICIO");
        modeloDeMiJTable.addColumn("BARRIO");
        modeloDeMiJTable.addColumn("MUNICIPIO");
        modeloDeMiJTable.addColumn("CANON");
        modeloDeMiJTable.addColumn("ADMINISTRACION");
        modeloDeMiJTable.addColumn("ESTADO ADMIN");
        modeloDeMiJTable.addColumn("ASIGNAR");
        jTable1.setModel(modeloDeMiJTable);
        int[] anchos = {15, 30, 10,30,30,10,30,50,40,30,5};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        conn.establecer_conexion();
        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 1";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        while(n.next()){
            String estado_admon = "";
            if(n.getInt(10)==1){
                estado_admon="INCLUIDA";
            }
            else if(n.getInt(10)==2){
                estado_admon="NO INCLUIDA";
            }                        
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),estado_admon,""});
        }
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
    public int buscar_codigo_contrato(){
        int validar = 0;
        try{
         conn.establecer_conexion();
            String sql="select cod_contrato from arrienda where cod_contrato = '"+jTextField_Cod_contrato.getText().toUpperCase()+"'";
         ResultSet resultado = conn.consulta(sql);
         while(resultado.next()){
             validar++;
         }
        }catch(Exception e){
            System.out.println(""+e);
        }
        return validar;
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
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_clase = new javax.swing.JComboBox();
        jTextField_codigo = new javax.swing.JTextField();
        jComboBox_municipio = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField_canon = new javax.swing.JTextField();
        jComboBox_uso = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox_barrio = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dateChooserCombo_fin = new datechooser.beans.DateChooserCombo();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dateChooserCombo_ini = new datechooser.beans.DateChooserCombo();
        jTextField_Cod_contrato = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_observacion = new javax.swing.JTextField();
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
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 270, 970, 250);

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MUNICIPIO");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(550, 200, 230, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("INMUEBLES EXISTENTES");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 130, 220, 20);

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
        jComboBox_clase.setBounds(320, 220, 230, 30);

        jTextField_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_codigoFocusLost(evt);
            }
        });
        getContentPane().add(jTextField_codigo);
        jTextField_codigo.setBounds(90, 170, 220, 30);

        jComboBox_municipio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_municipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Municipio" }));
        jComboBox_municipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_municipioItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_municipio);
        jComboBox_municipio.setBounds(550, 220, 230, 30);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("CLASE");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(320, 200, 210, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Codigo");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(90, 150, 210, 20);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(820, 180, 120, 50);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Canon");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(90, 200, 210, 20);

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
        jTextField_canon.setBounds(90, 220, 220, 30);

        jComboBox_uso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_uso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Uso", "COMERCIAL", "VIVIENDA", "NEGOCIO" }));
        jComboBox_uso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_usoItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_uso);
        jComboBox_uso.setBounds(320, 170, 230, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USO");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(330, 150, 210, 20);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("BARRIO");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(550, 150, 210, 20);

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
        jComboBox_barrio.setBounds(550, 170, 230, 30);

        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 150, 970, 110);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel3.setLayout(null);

        dateChooserCombo_fin.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        jPanel3.add(dateChooserCombo_fin);
        dateChooserCombo_fin.setBounds(590, 70, 155, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("CODIGO CONTRATO");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(160, 10, 200, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("INICIO");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(250, 70, 100, 30);

        jButton1.setBackground(new java.awt.Color(0, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(460, 110, 140, 30);

        dateChooserCombo_ini.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        jPanel3.add(dateChooserCombo_ini);
        dateChooserCombo_ini.setBounds(360, 70, 160, 30);

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
        jTextField_Cod_contrato.setBounds(360, 10, 380, 28);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("FIN");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(520, 70, 60, 30);

        jTextField_observacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField_observacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_observacionActionPerformed(evt);
            }
        });
        jTextField_observacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_observacionKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_observacion);
        jTextField_observacion.setBounds(360, 40, 380, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("OBSERVACION");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(160, 40, 200, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 530, 970, 150);
        jPanel3.getAccessibleContext().setAccessibleName("Asignar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row=jTable1.getSelectedRow();
        if(validar==1){
            if(jTable1.getValueAt(row2, 10).equals("X")){
                jTable1.setValueAt("", row2, 10);
            }        
        }
        if(jTable1.getValueAt(row, 10).equals("")){
            jTable1.setValueAt("X", row, 10);
            cod_inmueble_sel=jTable1.getValueAt(row, 0).toString()  ;
            validar=1;
            row2=row;
        }   
        

        System.out.println(row2);
        System.out.println(cod_inmueble_sel);
        
        jPanel3.setVisible(true);
        
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
        String consulta="select codigo, direccion, uso, clase, edificio, barrio, municipio, canon , admon, admon_estado FROM inmuebles where inmueble_disponible = 1 ";
        consulta=consulta+uso+clase+barrio+municipio+codigo_inmueble+canon;
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
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
            modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5),n.getString(6),n.getString(7),n.getString(8),n.getString(9),estado_admon,""});

        }
        }
        catch(Exception e){}
        

        if(jTable1.getRowCount()==0){
            
            conn.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
        }
      
    }//GEN-LAST:event_jButton2ActionPerformed

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
        if(!jTextField_Cod_contrato.getText().toUpperCase().equals("")&&!jTextField_observacion.getText().equals("")){
            if(buscar_codigo_contrato()==0){
                if(jComboBox_Inqulino.getSelectedIndex()!=0){
                int crow=modeloDeMiJTable.getRowCount();
                String mensaje="";
                for(int i=0;i<crow;i++){
                    if(jTable1.getValueAt(i,10).equals("X")){
                        buscar_codigo();
                        mensaje+="El Inmueble Asignado Es: "+jTable1.getValueAt(i, 0);
                        mensaje+="\n Con Valor de Canon: "+jTable1.getValueAt(i, 7);
                        mensaje+="\n Al Inquilino: "+jComboBox_Inqulino.getSelectedItem();
                    }
                }
                mensaje+="\n\nDESEA REALIZAR LA ASIGNACION?";
                int a=JOptionPane.showConfirmDialog(rootPane, mensaje);
                if(a==0){
                for(int i=0;i<crow;i++){
                    if(jTable1.getValueAt(i,10).equals("X")){
                        String insert="insert into arrienda (cod_inmueble, cod_inquilino, cod_contrato, fecha, fecha_ini, fecha_fin, estado, usuario, causacion, observacion) "
                                + "values ("+codinmueble+", "+cod_inquilino+", '"+jTextField_Cod_contrato.getText().toUpperCase()+"', now(), '"+dateChooserCombo_ini.getText()+"', '"+dateChooserCombo_fin.getText()+"', 1, '"+acc.getUsuario()+"', 1, '"+jTextField_observacion.getText().toUpperCase()+"')";
                        String update="update inmuebles set inmueble_disponible = 2 where codinmueble = "+codinmueble;
                        conn.Dinsertar2(insert);
                        conn.Dactualizar2(update);
                        valid++;

                        }else{}
                    }
                //fin del for
                    if(valid!=0){
                            conn.JOptionShowMessage("+1", null, "INMUEBLE "+cod_inmueble_sel+", ASIGNADO CORRECTAMENTE");
                            this.dispose();
                   }else{
                            conn.JOptionShowMessage("+1",null, "DEBE ESCOGER UN INMUEBLE");

                    }
                }
                }else{
                    conn.JOptionShowMessage("+1", null, "DEBE ESCOGER UN INQULINO PARA ASIGNAR");
                }
            }else{
               conn.JOptionShowMessage("+1", "", "El CODIGO DE CONTRATO YA EXISTE");
                }
        }else{
            conn.JOptionShowMessage("+1", "", "LLENAR LOS DATOS DE CODIGO DE CONTRATO Y OBSERVACION");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_Cod_contratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Cod_contratoKeyTyped
    }//GEN-LAST:event_jTextField_Cod_contratoKeyTyped

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
            canon= " and canon = "+jTextField_canon.getText();
            System.out.println(canon);
        
        }
    }//GEN-LAST:event_jTextField_canonFocusLost

    private void jTextField_canonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_canonKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField_canonKeyTyped

    private void jTextField_Cod_contratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Cod_contratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Cod_contratoActionPerformed

    private void jTextField_observacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_observacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_observacionActionPerformed

    private void jTextField_observacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_observacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_observacionKeyTyped

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
    private datechooser.beans.DateChooserCombo dateChooserCombo_fin;
    private datechooser.beans.DateChooserCombo dateChooserCombo_ini;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox_Inqulino;
    private javax.swing.JComboBox jComboBox_barrio;
    private javax.swing.JComboBox jComboBox_clase;
    private javax.swing.JComboBox jComboBox_municipio;
    private javax.swing.JComboBox jComboBox_uso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cod_contrato;
    private javax.swing.JTextField jTextField_canon;
    private javax.swing.JTextField jTextField_cedula;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_fijo;
    private javax.swing.JTextField jTextField_observacion;
    private javax.swing.JTextField jTextField_saldo;
    // End of variables declaration//GEN-END:variables
}
