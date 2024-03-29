/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import System.Conexion;
import System.Utilidades;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
public class Historial_Mora extends javax.swing.JDialog {

    /**
     * Creates new form Clientes
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Historial_Mora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(710,550);
        setLocationRelativeTo(null);
        setTitle("Historial Puntos");
        construirtabla();
    }
    public void construirtabla(){
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
        jTable1.setModel(modeloDeMiJTable);
        modeloDeMiJTable.setColumnIdentifiers(new String[]{"COD ESTUDIANTE","NOMBRE ESTUDIANTE","PERIODO PENDIENTE","VALOR"});
        JTableHeader th; 
        th = jTable1.getTableHeader(); 
        th.setPreferredSize(new Dimension(100, 50));
        Font fuente = new Font("Tahoma", Font.BOLD, 11); 
        th.setFont(fuente); 
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        int[] anchos = {300,1000,500,300};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        
    }

    public void buscar(){
            String consulta2="select movimiento,cantidad,puntos,fecha,aud_puntos,puntos from cliente, auditoria where ced_cliente=cedula order by fecha desc";
            Conexion conn = new Conexion();
            Conexion.establecer_conexion();
            ResultSet m = conn.consulta(consulta2);
            try{
                while (m.next()){
                        modeloDeMiJTable.addRow(new Object[]{m.getString(1),m.getString(2),m.getString(5),m.getString(4)});
                }

           }catch (Exception e){}  
    }
    public void limpiartabla(){
              DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
            int filas=jTable1.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
              }
    }
    public void reporte(){
            String ruta=System.getProperty("user.dir");
            ruta=ruta.replace("\\", "/");
            System.out.println(ruta);
            String path=ruta+"/src/Reportes/ResumenPuntos.jasper";
            JasperReport jr = null;
            try {
                JDialog viewer = new JDialog(new javax.swing.JFrame(),"Imprimir", true); 
                viewer.setSize(1000,800); 
                viewer.setLocationRelativeTo(null); 
                Map parametro = new HashMap();
                
                parametro.put("NCedula", Integer.parseInt(jTextField_Cedula.getText())); 
                parametro.put("Nombre", jTextField_Nombre.getText());  
                parametro.put("Cedula", jTextField_Cedula.getText());
                parametro.put("Puntos", puntos.getText());
                
                //parametro.put("parameter1", jComboBox_usuarios.getSelectedItem());
                jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jp = JasperFillManager.fillReport(jr,parametro,Conexion.conexion);
                JasperViewer jv = new JasperViewer(jp);
                viewer.getContentPane().add(jv.getContentPane());
                viewer.setVisible(true);
                jv.setTitle("Detalle Puntos");


            } catch (JRException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
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

        puntos = new javax.swing.JLabel();
        jTextField_Cedula = new javax.swing.JTextField();
        jTextField_Nombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton_agregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        puntos.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        puntos.setForeground(new java.awt.Color(255, 0, 0));
        puntos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(puntos);
        puntos.setBounds(560, 430, 130, 40);

        jTextField_Cedula.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CedulaActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_Cedula);
        jTextField_Cedula.setBounds(160, 70, 230, 40);

        jTextField_Nombre.setEditable(false);
        jTextField_Nombre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getContentPane().add(jTextField_Nombre);
        jTextField_Nombre.setBounds(160, 110, 380, 40);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setText("Codigo Estudiante");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 70, 160, 40);

        jButton_agregar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton_agregar.setText("Imprimir");
        jButton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_agregarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_agregar);
        jButton_agregar.setBounds(10, 430, 160, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Puntos_img.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(340, 0, 350, 120);

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
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
        jScrollPane1.setBounds(10, 170, 680, 250);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setText("Nombre Estudiante");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 110, 180, 40);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setText("Total dinero en mora");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(350, 430, 200, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CedulaActionPerformed
        limpiartabla();
        buscar();
    }//GEN-LAST:event_jTextField_CedulaActionPerformed

    private void jButton_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_agregarActionPerformed
        if(!jTextField_Nombre.getText().equals("")){
        reporte();
        }else{
            Utilidades.JOptionShowMessage("+1", "ERROR: Debe buscar primero un cliente para poder imprimir el reporte");
        }
    }//GEN-LAST:event_jButton_agregarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_agregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Cedula;
    private javax.swing.JTextField jTextField_Nombre;
    private javax.swing.JLabel puntos;
    // End of variables declaration//GEN-END:variables
}
