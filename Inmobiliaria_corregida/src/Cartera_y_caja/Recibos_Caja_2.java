/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

import Models.Metodos;
import Models.acceso;
import Models.dinero_caja;
import inmobiliaria_fase01.Conexion;
import java.awt.Dialog;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
//import java.util.ArrayList;
//import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
//import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author Usuario
 */
public class Recibos_Caja_2 extends javax.swing.JDialog {

   private DefaultTableModel modeloDeMiJTable; 
    
    public Recibos_Caja_2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(950,650);
        setLocationRelativeTo(rootPane);
        //metodos iniciados
        inactivos(falso);
        iniciarhora();
        
        jTextField_ncheque.setEnabled(false);        
        jPanel3.setVisible(false);
        
        
        //no editables
        jTextField_total.setEditable(false);
        jTextField_vueltos.setEditable(false);
        
        
        //Jtable
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
        
        modeloDeMiJTable.addColumn("Cod Concepto");
        modeloDeMiJTable.addColumn("Concepto");
        //modeloDeMiJTable.addColumn("No Cheque");
        modeloDeMiJTable.addColumn("Concepto");
        modeloDeMiJTable.addColumn("Valor");
        //modeloDeMiJTable.addColumn("NIT");
        jTable1.setModel(modeloDeMiJTable);
        //int[] anchos = {30, 70, 30,200,50,30};
        int[] anchos = {30, 100, 200,80};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {

            //Sacamos el modelo de columnas de nuestra tabla

            //luego obtenemos la columna en la posicion "i"

            //invocamos el metodo setPreferrefWidth para ajustar el ancho

            //y le damos el valor del entero que esta en el arreglo en la posicion "i"

            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            jButton_confirmar.setVisible(false);
            inicializar(false, false);
        }
        //buscar_CONCEPTOS();
        jButton_confirmar1.setVisible(false);
        
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
    
    private void inactivos(Boolean a){
        jLabel17.setVisible(a);
        jTextField_Ncheque2.setVisible(a);
        jRadioButton_cheque2.setVisible(a);
        jRadioButton_efectivo2.setVisible(a);
        jRadioButton_interno.setVisible(false);
        jRadioButton_externo.setVisible(false);
        
    }
    private void inicializar(Boolean a, Boolean b){
        jPanel2.setVisible(a);
        jPanel1.setVisible(b);

    }
    private void panelrecibo(Boolean a){
        jRadioButton_cheque2.setEnabled(a);
        jRadioButton_efectivo2.setEnabled(a);
        jRadioButton_externo.setEnabled(a);
        jRadioButton_interno.setEnabled(a);
        jTextField_concepto.setEnabled(a);
        jTextField_valor.setEnabled(a);
        jComboBox_codigo.setEnabled(a);
        //jTextField_Ncheque.setEnabled(false);
        jButton_confirmar.setEnabled(a);
        jCheck_saldo.setEnabled(a);
    }
    
    private void panelrecibo_vacio(){
        jRadioButton_cheque2.setText("");
        jRadioButton_efectivo2.setText("");
        jRadioButton_externo.setText("");
        jRadioButton_interno.setText("");
        jTextField_concepto.setText("");
        jTextField_valor.setText("");
        jComboBox_codigo.setSelectedIndex(0);
        //jTextField_Ncheque.setText(false);
        jCheck_saldo.setSelected(false);
    }    
    private void panelcliente(Boolean a,Boolean b,Boolean c,Boolean d,Boolean e){
        jComboBox_destino.setEnabled(a);
        jTextField_codigo.setEnabled(b);
        jTextField_nombre.setEnabled(c);
        jTextField_direccion.setEnabled(d);
        jTextField_saldo.setEnabled(e);
    }
    
        private void panelcliente_vacio(){
        jTextField_codigo.setText("");
        jTextField_nombre.setText("");
        jTextField_direccion.setText("");
        jTextField_saldo.setText("");
        jComboBox_destino.setSelectedIndex(0);
    }
    private void paneltotal (Boolean a){
        
       jTextField_Ncheque1.setEnabled(a);
       jTextField_total.setEnabled(a);
       jTextField_ingreso.setEnabled(a);
       jTextField_vueltos.setEnabled(a);
    }
    private void paneltotal_vacio(){
       jTextField_Ncheque1.setText("");
       jTextField_total.setText("");
       jTextField_ingreso.setText("");
       jTextField_vueltos.setText("");
    }    
    private void botones (Boolean a,Boolean b,Boolean c,Boolean d){
        jButton_nuevo.setSelected(a);
        jButton_bucar.setSelected(b);
        jButton_imprimir.setSelected(c);
        //jButton_informes.setSelected(d);
    }

    private void llenarcombocodigo(){
        conn.establecer_conexion();
        jComboBox_codigo.removeAllItems();
        jComboBox_codigo.addItem("SELECCIONE");
        String sql ="select nombres from conceptos2 where cod_referencia='INGRESO';";
        //String sql ="select DISTINCT fecha::date from factura_caja";
        ResultSet query = conn.consulta(sql);
        try {
            while(query.next()){
                jComboBox_codigo.addItem(query.getObject(1)); 
            }    
        } catch (Exception e) {
        }
        
    }    
        private void serial(){
        conn.establecer_conexion();
        String consultaserial = "select max(serial)+1 from facturas where tipo_factura = 'RECIBO_CAJA'";
        ResultSet query = conn.consulta(consultaserial);
            try {
                while(query.next()){
                    String label = "";
                    serial = (query.getInt(1));
                    label = Integer.toString(serial);
                    jLabelN_Recibo.setText(label);
                }
            } catch (Exception e) {
            }
        }
        private void detallepersonavacio(){
            jTextField_codigo.setText("");
            jTextField_nombre.setText("");
            jTextField_direccion.setText("");
            jTextField_saldo.setText("");
            jComboBox_destino.setSelectedIndex(0);
        }
        
        private void detatallerecivovacio(){
        jTextField_concepto.setText("");
        jTextField_valor.setText("");
        jComboBox_codigo.setSelectedIndex(0);
        jCheck_saldo.setSelected(false);
        }
        
        private void detatalletotalvacio(){
            jTextField_ncheque.setText("");
            jTextField_ingreso.setText("");
            jTextField_total.setText("");
            jTextField_vueltos.setText("");
            efecheque.clearSelection();
        
        }        
                
        private void iniciarhora (){
            
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = mes+1;
        String cadenafecha = ""+dia+"/"+mes+"/"+año+"";
            jLabel9.setText(cadenafecha);
        }
        
        private void reportes(String path){
            conn.establecer_conexion();
            contimp++;
            JasperReport jr = null;
            try {
                if(contimp!=0){
                    JDialog viewer = new JDialog(new javax.swing.JFrame(),"RECIBO DE CAJA", true); 
                    viewer.setSize(1000,800); 
                    viewer.setLocationRelativeTo(null);                    
                    Map parametro = new HashMap();
                    parametro.put("SERIAL", seriaimp);
                    parametro.put("TIPO_FACTURA", "RECIBO_CAJA");
                    jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                    JasperPrint jp = JasperFillManager.fillReport(jr,parametro,conn.establecer_conexion());
                    //JasperViewer jv = new JasperViewer(jp);
                    JasperViewer jv = new JasperViewer(jp);
                    //nuevo
                    viewer.getContentPane().add(jv.getContentPane());
                    viewer.setVisible(true);
                    jv.setTitle("RECIBO DE CAJA");
                    botones(false, false, false, false);
                    serial();
                }else{
                    Conexion.JOptionShowMessage("+1", null, "NO HA GENERADO NINGUN RECIBO");
                }
            } catch (JRException ex) {
            }        
        
        }
//        private void paneldinero(Boolean a){
//            
//            
//        }

        
///////////////////////////////////////////////////
///////////////7JTABLE
//////////////////////////////////////////////////

      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        extint = new javax.swing.ButtonGroup();
        efecheque = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton_nuevo = new javax.swing.JButton();
        jButton_bucar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton_imprimir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField_concepto = new javax.swing.JTextField();
        jTextField_valor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton_interno = new javax.swing.JRadioButton();
        jRadioButton_externo = new javax.swing.JRadioButton();
        jRadioButton_cheque2 = new javax.swing.JRadioButton();
        jRadioButton_efectivo2 = new javax.swing.JRadioButton();
        jComboBox_codigo = new javax.swing.JComboBox();
        jButton_confirmar = new javax.swing.JButton();
        jLabelN_Recibo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField_Ncheque2 = new javax.swing.JTextField();
        jCheck_saldo = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jTextField_codigo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox_destino = new javax.swing.JComboBox();
        jTextField_nombre = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField_direccion = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField_saldo = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_confirmar1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField_ncheque = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jRadioButton_cheque = new javax.swing.JRadioButton();
        jRadioButton_efectivo = new javax.swing.JRadioButton();
        jComboBox_codigo1 = new javax.swing.JComboBox();
        jButton_confirmar2 = new javax.swing.JButton();
        jLabelN_Recibo1 = new javax.swing.JLabel();
        jTextField_Ncheque1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_ingreso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_vueltos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recibo de Caja");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 260, 50);

        jButton_nuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_nuevo.setText("Nuevo");
        jButton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_nuevo);
        jButton_nuevo.setBounds(450, 20, 150, 50);

        jButton_bucar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_bucar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton_bucar.setText("Buscar");
        jButton_bucar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bucarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_bucar);
        jButton_bucar.setBounds(600, 20, 150, 50);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel9.setText("Fecha: 27/04/2014");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 80, 200, 40);

        jButton_imprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1398654205_printer.png"))); // NOI18N
        jButton_imprimir.setText("Imprimir");
        jButton_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_imprimir);
        jButton_imprimir.setBounds(750, 20, 150, 50);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Detalle Recibo", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 20))); // NOI18N
        jPanel1.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Concepto");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(10, 150, 130, 30);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Recibo N°");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(10, 20, 80, 40);

        jTextField_concepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_concepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_conceptoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_concepto);
        jTextField_concepto.setBounds(90, 90, 360, 30);

        jTextField_valor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_valor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_valorKeyTyped(evt);
            }
        });
        jPanel1.add(jTextField_valor);
        jTextField_valor.setBounds(90, 120, 210, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Valor");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(10, 120, 60, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("N° Cheque");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(10, 180, 80, 30);

        extint.add(jRadioButton_interno);
        jRadioButton_interno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_interno.setForeground(new java.awt.Color(0, 0, 204));
        jRadioButton_interno.setText("Interno");
        jPanel1.add(jRadioButton_interno);
        jRadioButton_interno.setBounds(110, 60, 90, 30);

        extint.add(jRadioButton_externo);
        jRadioButton_externo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_externo.setForeground(new java.awt.Color(0, 0, 204));
        jRadioButton_externo.setText("Externo");
        jPanel1.add(jRadioButton_externo);
        jRadioButton_externo.setBounds(10, 60, 100, 29);

        efecheque.add(jRadioButton_cheque2);
        jRadioButton_cheque2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_cheque2.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton_cheque2.setText("Cheque");
        jRadioButton_cheque2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_cheque2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton_cheque2);
        jRadioButton_cheque2.setBounds(350, 60, 90, 29);

        efecheque.add(jRadioButton_efectivo2);
        jRadioButton_efectivo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_efectivo2.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton_efectivo2.setText("Efectivo");
        jRadioButton_efectivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_efectivo2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton_efectivo2);
        jRadioButton_efectivo2.setBounds(240, 60, 100, 29);

        jComboBox_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jComboBox_codigo);
        jComboBox_codigo.setBounds(90, 150, 210, 30);

        jButton_confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        jButton_confirmar.setText("Añadir Registro");
        jButton_confirmar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_confirmar);
        jButton_confirmar.setBounds(310, 180, 150, 30);

        jLabelN_Recibo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelN_Recibo.setForeground(new java.awt.Color(153, 0, 0));
        jLabelN_Recibo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabelN_Recibo);
        jLabelN_Recibo.setBounds(90, 20, 140, 40);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Descripcion");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(10, 90, 80, 30);

        jTextField_Ncheque2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jTextField_Ncheque2);
        jTextField_Ncheque2.setBounds(90, 180, 210, 30);

        jCheck_saldo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jCheck_saldo.setText("Usar Saldo?");
        jCheck_saldo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheck_saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheck_saldoActionPerformed(evt);
            }
        });
        jPanel1.add(jCheck_saldo);
        jCheck_saldo.setBounds(320, 120, 100, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(410, 110, 470, 230);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Detalle Persona", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 20))); // NOI18N
        jPanel2.setLayout(null);

        jTextField_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_codigoActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField_codigo);
        jTextField_codigo.setBounds(90, 90, 260, 30);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Destino");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(10, 60, 50, 30);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Cedula/Cod");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(10, 90, 80, 30);

        jComboBox_destino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox_destino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "INQUILINO", "PROPIETARIO", "Otros Usuarios" }));
        jComboBox_destino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_destinoItemStateChanged(evt);
            }
        });
        jComboBox_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_destinoActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox_destino);
        jComboBox_destino.setBounds(90, 60, 260, 30);

        jTextField_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(jTextField_nombre);
        jTextField_nombre.setBounds(90, 120, 260, 30);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Nombre");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(10, 120, 60, 30);

        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(jTextField_direccion);
        jTextField_direccion.setBounds(90, 150, 260, 30);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Dirección");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(10, 150, 60, 30);

        jTextField_saldo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(jTextField_saldo);
        jTextField_saldo.setBounds(90, 180, 260, 30);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Saldo");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(10, 180, 60, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 110, 370, 230);

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
        jScrollPane1.setBounds(10, 350, 880, 120);

        jButton_confirmar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        jButton_confirmar1.setText("GUARDAR RECIBO");
        jButton_confirmar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_confirmar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmar1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_confirmar1);
        jButton_confirmar1.setBounds(700, 510, 190, 40);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Detalle Total", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 20))); // NOI18N
        jPanel3.setLayout(null);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Codigo");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(10, 150, 130, 30);

        jTextField_ncheque.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_ncheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nchequeActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField_ncheque);
        jTextField_ncheque.setBounds(250, 30, 170, 30);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Valor");
        jPanel3.add(jLabel26);
        jLabel26.setBounds(10, 120, 60, 30);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("N° Cheque");
        jPanel3.add(jLabel27);
        jLabel27.setBounds(10, 180, 80, 30);

        efecheque.add(jRadioButton_cheque);
        jRadioButton_cheque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_cheque.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton_cheque.setText("Cheque");
        jRadioButton_cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_chequeActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton_cheque);
        jRadioButton_cheque.setBounds(100, 30, 80, 29);

        efecheque.add(jRadioButton_efectivo);
        jRadioButton_efectivo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_efectivo.setForeground(new java.awt.Color(0, 153, 0));
        jRadioButton_efectivo.setText("Efectivo");
        jRadioButton_efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_efectivoActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton_efectivo);
        jRadioButton_efectivo.setBounds(10, 30, 90, 29);

        jPanel3.add(jComboBox_codigo1);
        jComboBox_codigo1.setBounds(90, 150, 210, 30);

        jButton_confirmar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        jButton_confirmar2.setText("Añadir Registro");
        jButton_confirmar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_confirmar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmar2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton_confirmar2);
        jButton_confirmar2.setBounds(310, 180, 150, 30);

        jLabelN_Recibo1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelN_Recibo1.setForeground(new java.awt.Color(153, 0, 0));
        jLabelN_Recibo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabelN_Recibo1);
        jLabelN_Recibo1.setBounds(100, 20, 80, 40);
        jPanel3.add(jTextField_Ncheque1);
        jTextField_Ncheque1.setBounds(90, 180, 210, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("N° Cheque");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(180, 30, 110, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Total $");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(440, 30, 90, 30);

        jTextField_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_totalActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField_total);
        jTextField_total.setBounds(490, 30, 170, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("$ Ingreso");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(190, 60, 90, 30);

        jTextField_ingreso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ingresoActionPerformed(evt);
            }
        });
        jTextField_ingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ingresoKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_ingreso);
        jTextField_ingreso.setBounds(250, 60, 170, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("$ Vueltos");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(430, 60, 90, 30);

        jTextField_vueltos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel3.add(jTextField_vueltos);
        jTextField_vueltos.setBounds(490, 60, 170, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(20, 480, 680, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nuevoActionPerformed

    
    //inactivo
    botones(true, false, false, false);
    inicializar(true, true);
    panelrecibo(false);
    panelcliente(true, true, false, false,false);
    serial();
    llenarcombocodigo();

    //vacio     
    
    panelrecibo_vacio();
    panelcliente_vacio();
    paneltotal_vacio();    
    
    
    
    }//GEN-LAST:event_jButton_nuevoActionPerformed

    private void jButton_bucarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bucarActionPerformed
        String recibo= JOptionPane.showInputDialog("<html><font='Arial' size=+1>Ingrese el numero del recibo a buscar</font></html>");
        String consulta ="select serial from facturas where serial = "+recibo+" and tipo_factura = 'RECIBO_CAJA'";
        System.out.print(consulta);
        conn.establecer_conexion();
        ResultSet rs=conn.consulta(consulta);
        int contador = 0;
        try {
            while (rs.next()) {                    
                seriaimp = (rs.getInt(1));
                System.out.println(seriaimp);

                int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Imprimir Recibo?" , "Imprimir Recibo", JOptionPane.OK_CANCEL_OPTION);                
                if(seletedvalue ==JOptionPane.YES_OPTION ){                
                    jButton_imprimirActionPerformed(evt);
                    contador++;
                }                
            }
        } catch (Exception e) {
        }
            if(contador==0){
                conn.JOptionShowMessage("+1", "", "No existe ese recibo");
            }
        botones(false, true, false, false);    // TODO add your handling code here:
    }//GEN-LAST:event_jButton_bucarActionPerformed

    private void jButton_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirActionPerformed
    botones(false, false, true, false);    // TODO add your handling code here:
    String consulta = "select tipo_usuario from facturas where tipo_factura = 'RECIBO_CAJA' ORDER BY cod_factura desc limit 1";
    ResultSet n = conn.consulta(consulta);
        try {
            while (n.next()) {                
                tipo_factura=n.getString(1);
            }
        } catch (Exception e) {
        }
            String ruta=System.getProperty("user.dir");
            ruta=ruta.replace("\\", "/");
            System.out.println(ruta);
            String path="";
            if(tipo_factura.equals("INQUILINO")){
                path=ruta+"/src/Reportes/recibo_caja01.jasper";
            }
            else if(tipo_factura.equals("PROPIETARIO")){
                path=ruta+"/src/Reportes/recibo_caja02.jasper";
            }
            else if(tipo_factura.equals("Otros Usuarios")){
                path=ruta+"/src/Reportes/recibo_caja03.jasper";            
            }
            System.out.println(path);
            reportes(path);
    }//GEN-LAST:event_jButton_imprimirActionPerformed

    private void jButton_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmarActionPerformed
        if (jTextField_concepto.getText().equals("")) {
            Conexion.JOptionShowMessage("+1", "", "El pago es por concepto de?");
        }
        else if (jTextField_valor.getText().equals("")) {
            Conexion.JOptionShowMessage("+1", "", "Valor a Pagar?");
        }
        else if(jComboBox_codigo.getSelectedIndex()==0){
            Conexion.JOptionShowMessage("+1", "", "Seleccione un Concepto");
        }
        else{
        conn.establecer_conexion();
        String Consulta = "select cod_concepto from conceptos2 where cod_referencia='INGRESO' and nombres = '"+jComboBox_codigo.getSelectedItem()+"';";
        ResultSet query = conn.consulta(Consulta);
        int codigo = 0;
            try {
                while (query.next()) {                    
                    codigo = (query.getInt(1));
                }
            } catch (Exception e) {
            }
        
       modeloDeMiJTable.addRow(new Object[]{codigo, jComboBox_codigo.getSelectedItem().toString(),jTextField_concepto.getText(),jTextField_valor.getText()});
       jButton_confirmar1.setVisible(true);
       jPanel3.setVisible(true);
       
        //////////////////
        //inicio  sumatoria//
        //////////////////  
        int totalRow= jTable1.getRowCount();
        totalRow-=1; 
        int sumatoria = 0;
        int sumatoria1 = 0;
        for(int i=0;i<=(totalRow);i++){
            sumatoria= Integer.parseInt(String.valueOf(jTable1.getValueAt(i,3)));
            sumatoria1+=sumatoria;
        }
        jTextField_total.setText(String.valueOf(sumatoria1));
        valor_total = sumatoria1;
        
        //Guardo el saldo, en una variable por si lo nesecito.
        //saldo_total= Integer.parseInt(jTextField_saldo.getText());
        
        if(jComboBox_destino.getSelectedIndex()==3){
            jCheck_saldo.setVisible(false);
        }

        jTextField_codigo.setEnabled(false);
        jComboBox_destino.setEnabled(false);
   
       
       detatallerecivovacio();
       
       }
    }//GEN-LAST:event_jButton_confirmarActionPerformed

    private void jComboBox_destinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_destinoItemStateChanged
        destino=jComboBox_destino.getSelectedIndex();
    }//GEN-LAST:event_jComboBox_destinoItemStateChanged

    private void jTextField_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_codigoActionPerformed
        if(destino==0){
            Conexion.JOptionShowMessage("+1", "", "Seleccione a quien desea hacer el recibo de Caja");
        }
        if(destino==1){
            conn.establecer_conexion();
            String datos = jTextField_codigo.getText().toUpperCase();
            String consulta="";

            if(datos.trim().matches(".*\\D+.*")){
                System.out.println("NO ES NUMERO O CODIGO");
                consulta="select nombres, direccion_casa,saldo, cod_inquilino from inquilinos where codigo='"+jTextField_codigo.getText().toUpperCase()+"'";
            }
            else{
                System.out.println("SI ES NUMERO =D O CEDULA");
                consulta="select nombres, direccion_casa,saldo, cod_inquilino from inquilinos where cedula='"+jTextField_codigo.getText().toUpperCase()+"'";        
            }
            System.out.println(consulta);
            ResultSet n=conn.consulta(consulta);
            int contador = 0;
            try{
                while(n.next()){
                    jTextField_nombre.setText(n.getString(1));
                    jTextField_direccion.setText(n.getString(2));
                    jTextField_saldo.setText(n.getString(3));
                    cod_usuario =  (n.getInt(4));
                    
                    panelrecibo(true);
                    jButton_confirmar.setVisible(true);
                    contador++;
                }
                if(contador==0){
                    conn.JOptionShowMessage("+1", "", "No existe el inquilino");
                    detallepersonavacio();
                    contador = 0;
                }
            }
            catch(Exception e){
            }
        }   
        if(destino==2){
            conn.establecer_conexion();
            String datos = jTextField_codigo.getText().toUpperCase();
            String consulta="";

            if(datos.trim().matches(".*\\D+.*")){
                consulta="select nombres, direccion_casa,saldo, cod_propietario from propietarios where codigo='"+jTextField_codigo.getText().toUpperCase()+"'";
            }
            else{
                consulta="select nombres, direccion_casa,saldo, cod_propietario from propietarios where cedula='"+jTextField_codigo.getText().toUpperCase()+"'";        
            }
            System.out.println(consulta);
            ResultSet n=conn.consulta(consulta);
            int contador = 0;
            
            try{
                while(n.next()){
                    jTextField_nombre.setText(n.getString(1));
                    jTextField_direccion.setText(n.getString(2));
                    jTextField_saldo.setText(n.getString(3));
                    cod_usuario =  (n.getInt(4));
                    
                    panelrecibo(true);
                    jButton_confirmar.setVisible(true);
                    contador++;
                }
                if(contador==0){
                    conn.JOptionShowMessage("+1", "", "No existe el Propietario");
                    detallepersonavacio();
                    contador=0;
                }            
            }
            catch(Exception e){
            }
        }
        if(destino==3){
            conn.establecer_conexion();
            
            String datos = jTextField_codigo.getText().toUpperCase();
            String consulta="select nombre_completo, direccion from otros_usuarios";
            if(datos.trim().matches(".*\\D+.*")){
                consulta="select nombre_completo, direccion, cod_usuario from otros_usuarios where codigo='"+jTextField_codigo.getText().toUpperCase()+"'";
            }
            else{
                consulta="select nombre_completo, direccion, cod_usuario from otros_usuarios where cedula='"+jTextField_codigo.getText().toUpperCase()+"'";        
            }
            System.out.println(consulta);
            ResultSet n=conn.consulta(consulta);
            int contador =0;
            try{
                while(n.next()){
                    jTextField_nombre.setText(n.getString(1));
                    jTextField_direccion.setText(n.getString(2));
                    jTextField_saldo.setText("0");
                    cod_usuario =  (n.getInt(3));
                    panelrecibo(true);
                    jButton_confirmar.setVisible(true);
                    contador++;
                }
                if(contador==0){
                    int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "El usuario no existe, deseas registrarlo?" , "Crear Otro Usuario", JOptionPane.OK_CANCEL_OPTION);
                    if(seletedvalue ==JOptionPane.YES_OPTION ){
                        Conexion.dialog();
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                Crear_Otros_Usuarios dialog = new Crear_Otros_Usuarios(new javax.swing.JFrame(), true);
                                dialog.setVisible(true);
                            }
                        });        
                        
                    }                    
                    contador=0;
                }                    
            }
            catch(Exception e){
            }
        }
        if(!jTextField_saldo.equals("")){
            saldo_total=Integer.parseInt(jTextField_saldo.getText());
        }
        
    }//GEN-LAST:event_jTextField_codigoActionPerformed

    private void jCheck_saldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheck_saldoActionPerformed
            int saldo = 0, total = 0;
            
            saldo = Integer.parseInt(jTextField_saldo.getText());

       if(jCheck_saldo.isSelected()==true){        
            saldo = Integer.parseInt(jTextField_saldo.getText());
            jTextField_valor.setText(String.valueOf(saldo));
            jTextField_saldo.setText("0");
       }else{
           jTextField_saldo.setText(String.valueOf(saldo_total));
           jTextField_valor.setText("0");

       }
           

//              
//       if(jCheck_saldo.isSelected()==true){
//            saldo = Integer.parseInt(jTextField_saldo.getText());
//            total = Integer.parseInt(jTextField_total.getText());  
//            uso_saldo = 1;
//            
//            if(saldo > total){
//                int  saldoasignar = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Cuanto Saldo deseas Utilizar"));
//                if(saldoasignar < saldo ){
//                    total = total -saldoasignar;
//                    saldo = saldo - saldoasignar;
//                }
//                else{
//                    conn.JOptionShowMessage("+1", "", "No Tienes esa Cantidad en Saldo");
//                    jCheck_saldo.setSelected(false);
//                }
//            }else{
//                total = total - saldo;
//            }
//            jTextField_total.setText(String.valueOf(total));
//            jTextField_saldo.setText(String.valueOf(saldo));
//            
//            
//            
//       }else{
//           jTextField_total.setText(String.valueOf(valor_total));
//           jTextField_saldo.setText(String.valueOf(saldo_total));
//           uso_saldo = 0;
//       }

       
       
       

       
    }//GEN-LAST:event_jCheck_saldoActionPerformed

    private void jButton_confirmar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmar1ActionPerformed
        if(jTextField_ingreso.getText().equals("")){
            conn.JOptionShowMessage("+1", "", "Ingrese una cantidad de dinero");
        }
        
        int ingresovalid = Integer.parseInt(jTextField_ingreso.getText());
        int totalvalid = Integer.parseInt(jTextField_total.getText());
        
        if(!(jRadioButton_cheque.isSelected()||jRadioButton_efectivo.isSelected())){
            Conexion.JOptionShowMessage("+1", "", "Seleccione un Metodo de pago");
        }
        else if(ingresovalid < totalvalid ){
            Conexion.JOptionShowMessage("+1", "", "El dinero Ingresado no es suficiente para el total");
        }        
        
        else{
            conn.establecer_conexion();
            String insertar="INSERT INTO facturas(tipo_factura, serial,tipo_usuario, cod_usuario, tipo_pago, ncheque, interno_externo, total, estado, usuario, fecha) " +
                "VALUES ('RECIBO_CAJA', "+serial+", '"+jComboBox_destino.getSelectedItem()+"', "+cod_usuario+", '"+forma_pago+"', '"+jTextField_ncheque.getText().toUpperCase()+"', 'INTERNO', "+valor_total+", 1,  '"+acc.getUsuario()+"', now())";
            seriaimp = serial;
            System.out.print(insertar);
            conn.Dinsertar2(insertar);
            //conseguir el numero de la factura.
            String consulta = "select max(cod_factura) from facturas";
            ResultSet rs = conn.consulta(consulta);
            int cod_factura=0;
            try {
                while (rs.next()) {
                    cod_factura = (rs.getInt(1));
                }
            } catch (Exception e) {
            }
            if(cod_factura!=0){
                for(int i=0;i<modeloDeMiJTable.getRowCount();i++){
                 conn.establecer_conexion();
                String insertar2="INSERT INTO detalle_factura_caja (cod_factura, cod_concepto, concepto, valor, fecha) "
                        + "values ("+cod_factura+", "+modeloDeMiJTable.getValueAt(i, 0)+", '"+modeloDeMiJTable.getValueAt(i, 2).toString().toUpperCase()+"', "+modeloDeMiJTable.getValueAt(i, 3)+", now())";
                System.out.println(insertar2);
                conn.Dinsertar(insertar2);
                contimp++;
                tipo_factura=jComboBox_destino.getSelectedItem().toString();
                }
                //imprimir
                int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Desea Imprimir el Recibo" , "Imprimir Recibo", JOptionPane.OK_CANCEL_OPTION);
                if(seletedvalue ==JOptionPane.YES_OPTION ){                
                    jButton_imprimirActionPerformed(evt);
                }
           }
            else{
                conn.JOptionShowMessage("+1", "", "Error al Guardar la Factura");
            }            
        //verificar si uso, el saldo para descontarlo.
        if(jComboBox_destino.getSelectedIndex()==1){
            String update = "update ";
        }

                
            //reportes
                 
                
                
            //limpiar todo
                
//        detallepersonavacio();
//        detatallerecivovacio();
//        detatalletotalvacio();
//        panelcliente(falso, falso, falso, falso, falso);
//        panelrecibo(falso);
//        paneltotal(falso);
//        limpiarTabla(jTable1);
        this.dispose();
        
        
        
        

        }
    }//GEN-LAST:event_jButton_confirmar1ActionPerformed

    private void jTextField_conceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_conceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_conceptoActionPerformed

    private void jRadioButton_efectivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_efectivo2ActionPerformed

    }//GEN-LAST:event_jRadioButton_efectivo2ActionPerformed

    private void jRadioButton_cheque2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_cheque2ActionPerformed

    }//GEN-LAST:event_jRadioButton_cheque2ActionPerformed

    private void jRadioButton_chequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_chequeActionPerformed
        forma_pago="CHEQUE";
        jTextField_ncheque.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_chequeActionPerformed

    private void jRadioButton_efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_efectivoActionPerformed
        forma_pago="EFECTIVO";
        jTextField_ncheque.setEnabled(false);
        jTextField_ncheque.setText("");
    }//GEN-LAST:event_jRadioButton_efectivoActionPerformed

    private void jButton_confirmar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_confirmar2ActionPerformed

    private void jTextField_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_totalActionPerformed
        
    }//GEN-LAST:event_jTextField_totalActionPerformed

    private void jTextField_ingresoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ingresoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField_ingresoKeyTyped

    private void jTextField_ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ingresoActionPerformed
        
        if(jTextField_total.getText().equals("")){
            
        }else{
            int total = Integer.parseInt(jTextField_total.getText());
            int ingreso = Integer.parseInt(jTextField_ingreso.getText());
            int vueltos = 0;
            vueltos = ingreso - total;
            
            jTextField_vueltos.setText(String.valueOf(vueltos));
        }
    }//GEN-LAST:event_jTextField_ingresoActionPerformed

    private void jTextField_nchequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nchequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nchequeActionPerformed

    private void jTextField_valorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_valorKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField_valorKeyTyped

    private void jComboBox_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_destinoActionPerformed

    /**
     * @param args the command line arguments
     */
    
    int[] anchos = {40, 200, 50};
    int destino=0, serial=0, cod_usuario=0, seriaimp = 0, valor_total=0, saldo_total =0, uso_saldo=0;
    Boolean falso= false, verdadero= true;
    String forma_pago="", tipo_factura="";
    Conexion conn = new Conexion();
    dinero_caja dinero = new dinero_caja();
    acceso acc = new acceso();
    Metodos met = new Metodos();
    int contimp=0;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup efecheque;
    private javax.swing.ButtonGroup extint;
    private javax.swing.JButton jButton_bucar;
    private javax.swing.JButton jButton_confirmar;
    private javax.swing.JButton jButton_confirmar1;
    private javax.swing.JButton jButton_confirmar2;
    private javax.swing.JButton jButton_imprimir;
    private javax.swing.JButton jButton_nuevo;
    private javax.swing.JCheckBox jCheck_saldo;
    private javax.swing.JComboBox jComboBox_codigo;
    private javax.swing.JComboBox jComboBox_codigo1;
    private javax.swing.JComboBox jComboBox_destino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelN_Recibo;
    private javax.swing.JLabel jLabelN_Recibo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton_cheque;
    private javax.swing.JRadioButton jRadioButton_cheque2;
    private javax.swing.JRadioButton jRadioButton_efectivo;
    private javax.swing.JRadioButton jRadioButton_efectivo2;
    private javax.swing.JRadioButton jRadioButton_externo;
    private javax.swing.JRadioButton jRadioButton_interno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Ncheque1;
    private javax.swing.JTextField jTextField_Ncheque2;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_concepto;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_ingreso;
    private javax.swing.JTextField jTextField_ncheque;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_saldo;
    private javax.swing.JTextField jTextField_total;
    private javax.swing.JTextField jTextField_valor;
    private javax.swing.JTextField jTextField_vueltos;
    // End of variables declaration//GEN-END:variables
}
