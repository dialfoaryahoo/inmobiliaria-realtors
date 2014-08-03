/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartera_y_caja;

import inmobiliaria_fase01.Conexion;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hpsf.Constants;

/**
 *
 * @author Usuario
 */
public class Causacion extends javax.swing.JDialog {

   private DefaultTableModel modeloDeMiJTable; 
    
    public Causacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(380,430);
        setLocationRelativeTo(rootPane);
        inicializar(false, false);

    }
  public void inicializar(boolean a, boolean b){
      jTextField_nombre.setEnabled(false);
      jTextField_inmueble.setEnabled(false);
      jRadioButton_enero.setEnabled(a);
      jRadioButton_febrero.setEnabled(a);
      jRadioButton_marzo.setEnabled(a);
      jRadioButton_abril.setEnabled(a);
      jRadioButton_mayo.setEnabled(a);
      jRadioButton_junio.setEnabled(a);
      jRadioButton_julio.setEnabled(a);
      jRadioButton_agosto.setEnabled(a);
      jRadioButton_septiembre.setEnabled(a);
      jRadioButton_octubre.setEnabled(a);
      jRadioButton_noviembre.setEnabled(a);
      jRadioButton_diciembre.setEnabled(a);
      jComboBox_codigo.setEnabled(b);
      meses.clearSelection();
  }
    public void mes_causar(){
        if(jRadioButton_enero.isSelected()){
            mes_causar="ENERO";
        }
        else if(jRadioButton_febrero.isSelected()){
            mes_causar="FEBRERO";
        }
        else if(jRadioButton_marzo.isSelected()){
            mes_causar="MARZO";
        }
        else if(jRadioButton_abril.isSelected()){
            mes_causar="ABRIL";
        }
        else if(jRadioButton_mayo.isSelected()){
            mes_causar="MAYO";
        }
        else if(jRadioButton_junio.isSelected()){
            mes_causar="JUNIO";
        }
        else if(jRadioButton_julio.isSelected()){
            mes_causar="JULIO";
        }
        else if(jRadioButton_agosto.isSelected()){
            mes_causar="AGOSTO";
        }        
        else if(jRadioButton_septiembre.isSelected()){
            mes_causar="SEPTIEMBRE";
        }        
        else if(jRadioButton_octubre.isSelected()){
            mes_causar="OCTUBRE";
        }        
        else if(jRadioButton_noviembre.isSelected()){
            mes_causar="NOVIEMBRE";
        }                
        else if(jRadioButton_diciembre.isSelected()){
            mes_causar="DICIEMBRE";
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

        meses = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton_marzo = new javax.swing.JRadioButton();
        jRadioButton_enero = new javax.swing.JRadioButton();
        jRadioButton_febrero = new javax.swing.JRadioButton();
        jRadioButton_junio = new javax.swing.JRadioButton();
        jRadioButton_abril = new javax.swing.JRadioButton();
        jRadioButton_mayo = new javax.swing.JRadioButton();
        jRadioButton_septiembre = new javax.swing.JRadioButton();
        jRadioButton_julio = new javax.swing.JRadioButton();
        jRadioButton_agosto = new javax.swing.JRadioButton();
        jRadioButton_diciembre = new javax.swing.JRadioButton();
        jRadioButton_octubre = new javax.swing.JRadioButton();
        jRadioButton_noviembre = new javax.swing.JRadioButton();
        jRadioButton_liquidaciontotal = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_codigo = new javax.swing.JComboBox();
        jTextField_inmueble = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jRadioButton_liquidacionindividual = new javax.swing.JRadioButton();
        jTextField_nota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirm.png"))); // NOI18N
        jButton2.setText("Generar Causacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(110, 360, 170, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Causación");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 10, 200, 50);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Liquidacion Total", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setLayout(null);

        meses.add(jRadioButton_marzo);
        jRadioButton_marzo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_marzo.setText("Marzo");
        jRadioButton_marzo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_marzo.setBorderPainted(true);
        jPanel1.add(jRadioButton_marzo);
        jRadioButton_marzo.setBounds(20, 130, 110, 30);

        meses.add(jRadioButton_enero);
        jRadioButton_enero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_enero.setText("Enero");
        jRadioButton_enero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_enero.setBorderPainted(true);
        jPanel1.add(jRadioButton_enero);
        jRadioButton_enero.setBounds(20, 50, 110, 30);

        meses.add(jRadioButton_febrero);
        jRadioButton_febrero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_febrero.setText("Febrero");
        jRadioButton_febrero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_febrero.setBorderPainted(true);
        jPanel1.add(jRadioButton_febrero);
        jRadioButton_febrero.setBounds(20, 90, 110, 30);

        meses.add(jRadioButton_junio);
        jRadioButton_junio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_junio.setText("Junio");
        jRadioButton_junio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_junio.setBorderPainted(true);
        jPanel1.add(jRadioButton_junio);
        jRadioButton_junio.setBounds(130, 90, 100, 30);

        meses.add(jRadioButton_abril);
        jRadioButton_abril.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_abril.setText("Abril");
        jRadioButton_abril.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_abril.setBorderPainted(true);
        jPanel1.add(jRadioButton_abril);
        jRadioButton_abril.setBounds(20, 170, 110, 30);

        meses.add(jRadioButton_mayo);
        jRadioButton_mayo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_mayo.setText("Mayo");
        jRadioButton_mayo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_mayo.setBorderPainted(true);
        jPanel1.add(jRadioButton_mayo);
        jRadioButton_mayo.setBounds(130, 50, 100, 30);

        meses.add(jRadioButton_septiembre);
        jRadioButton_septiembre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_septiembre.setText("Septiembre");
        jRadioButton_septiembre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_septiembre.setBorderPainted(true);
        jPanel1.add(jRadioButton_septiembre);
        jRadioButton_septiembre.setBounds(230, 50, 110, 30);

        meses.add(jRadioButton_julio);
        jRadioButton_julio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_julio.setText("Julio");
        jRadioButton_julio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_julio.setBorderPainted(true);
        jPanel1.add(jRadioButton_julio);
        jRadioButton_julio.setBounds(130, 130, 100, 30);

        meses.add(jRadioButton_agosto);
        jRadioButton_agosto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_agosto.setText("Agostó");
        jRadioButton_agosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_agosto.setBorderPainted(true);
        jPanel1.add(jRadioButton_agosto);
        jRadioButton_agosto.setBounds(130, 170, 100, 30);

        meses.add(jRadioButton_diciembre);
        jRadioButton_diciembre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_diciembre.setText("Diciembre");
        jRadioButton_diciembre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_diciembre.setBorderPainted(true);
        jPanel1.add(jRadioButton_diciembre);
        jRadioButton_diciembre.setBounds(230, 170, 110, 30);

        meses.add(jRadioButton_octubre);
        jRadioButton_octubre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_octubre.setText("Octubre");
        jRadioButton_octubre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jRadioButton_octubre.setBorderPainted(true);
        jPanel1.add(jRadioButton_octubre);
        jRadioButton_octubre.setBounds(230, 90, 110, 30);

        meses.add(jRadioButton_noviembre);
        jRadioButton_noviembre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_noviembre.setText("Noviembre");
        jRadioButton_noviembre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jRadioButton_noviembre.setBorderPainted(true);
        jPanel1.add(jRadioButton_noviembre);
        jRadioButton_noviembre.setBounds(230, 130, 110, 30);

        buttonGroup1.add(jRadioButton_liquidaciontotal);
        jRadioButton_liquidaciontotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_liquidaciontotalMouseClicked(evt);
            }
        });
        jPanel1.add(jRadioButton_liquidaciontotal);
        jRadioButton_liquidaciontotal.setBounds(80, 0, 20, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 60, 360, 230);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Liquidacion Individual", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel2.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(30, 90, 80, 50);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Codigo Inquilino");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 60, 130, 30);

        jComboBox_codigo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Codigo" }));
        jPanel2.add(jComboBox_codigo);
        jComboBox_codigo.setBounds(160, 60, 140, 30);
        jPanel2.add(jTextField_inmueble);
        jTextField_inmueble.setBounds(110, 130, 190, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Inmueble");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 120, 80, 50);
        jPanel2.add(jTextField_nombre);
        jTextField_nombre.setBounds(110, 100, 190, 30);

        buttonGroup1.add(jRadioButton_liquidacionindividual);
        jRadioButton_liquidacionindividual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_liquidacionindividualMouseClicked(evt);
            }
        });
        jRadioButton_liquidacionindividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_liquidacionindividualActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton_liquidacionindividual);
        jRadioButton_liquidacionindividual.setBounds(60, 0, 20, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(380, 60, 350, 230);
        getContentPane().add(jTextField_nota);
        jTextField_nota.setBounds(60, 310, 310, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nota");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 300, 30, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mes_causar();
        int validar=0;
        conn.establecer_conexion();
        String consulta1 = "select date_part('day', arr.fecha_ini), arr.cod_inmueble, inm.canon, arr.causacion, arr.cod_arrienda, "
                + "arr.cod_inquilino, inm.cod_propietario, inm.comision , inm.admon_estado, inm.admon "
                + "from arrienda as arr  INNER JOIN inmuebles  as inm on  arr.cod_inmueble = inm.codinmueble where arr.estado = 1";
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
                if(n.getInt(4)==1){
                    dias_causados=(30-dia_tomo)+5;
                    valor_a_pagar = (canon/30)*dias_causados;
                    
                    System.out.println("Dias Causados: "+dias_causados);
                    
                    //Verifica si hay administracion incluida
                    if(n.getInt(9)==1){
                        admon_i = (admon/30)*dias_causados;
                        admon_p = admon - (admon/30)*dias_causados;
                        String insert3 = "insert into administracion (cod_arrienda, fecha, valor, cancelada) values("+cod_arrienda+", now(), "+admon+", 1)";
                        conn.insertar(insert3);
                        System.out.println(insert3);
                    }
                    
                    comision_inmueble = (comision_inmueble/30)*dias_causados;
                    valor_pro = valor_a_pagar - admon_i - admon_p - comision_inmueble;
                    
                    String update1 = "update arrienda set causacion = 2 where  cod_arrienda = "+cod_arrienda;
                    String update2 = "update inquilinos set saldo= saldo+"+valor_a_pagar+" where  cod_inquilino = "+cod_inquilino; //preguntar sobre los inquilinos
                    String update3 = "update propietarios set saldo= saldo+"+valor_pro+" where  cod_propietario = "+cod_propietario;
                    String insert1 = "insert into causacion (cod_arrienda, mes, fecha, valor, nota) values("+cod_arrienda+", '"+mes_causar+"', now(), "+valor_a_pagar+", '"+jTextField_nota.getText().toUpperCase()+"')";
                    String insert2 = "insert into comisiones (cod_arrienda, fecha, valor) values ("+cod_arrienda+", now(), "+comision_inmueble+")";
                    
                    
                    conn.Dactualizar2(update1);
                    conn.Dactualizar2(update2);
                    conn.Dactualizar2(update3);
                    conn.Dinsertar2(insert1);
                    conn.Dinsertar2(insert2);
                    validar++;
                    
                    System.out.println(update1);
                    System.out.println(update2);
                    System.out.println(update3);
                    System.out.println(insert1);
                    System.out.println(insert2);
                    
                    
                    
                }
                //cuando se ha causado >= 1 
                else if(n.getInt(4)==2){
                    //Verificar si hay administracion incluida
                    if(n.getInt(9)==1){
                        admon_i = admon;
                        String insert3 = "insert into administracion (cod_arrienda, fecha, valor, cancelada) values("+cod_arrienda+", now(), "+admon+", 1)";
                        conn.Dinsertar2(insert3);
                        System.out.println(insert3);
                    }                    
                    valor_pro = canon - comision_inmueble - admon_i ;
                    
                    String update2 = "update inquilinos set saldo= saldo+"+canon+" where  cod_inquilino = "+cod_inquilino;
                    String update3 = "update propietarios set saldo= saldo+"+valor_pro+" where  cod_propietario = "+cod_propietario;  
                    String insert1 = "insert into causacion (cod_arrienda, mes, fecha, valor, nota) values("+cod_arrienda+", '"+mes_causar+"', now(), "+canon+", '"+jTextField_nota.getText().toUpperCase()+"')";   
                    String insert2 = "insert into comisiones (cod_arrienda, fecha, valor) values ("+cod_arrienda+", now(), "+comision_inmueble+")";                    
                    
                    conn.Dactualizar2(update2);
                    conn.Dactualizar2(update3);
                    conn.Dinsertar2(insert1);
                    conn.Dinsertar2(insert2);
                    
                    System.out.println(update2);
                    System.out.println(update3);
                    System.out.println(insert1);
                    System.out.println(insert2);
                }

            }
        } catch (Exception e) {}
        if(validar!=0){
            conn.JOptionShowMessage("+1", "", "CAUSACION EXITOSA!");
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton_liquidaciontotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_liquidaciontotalMouseClicked
       inicializar(true, false);
    }//GEN-LAST:event_jRadioButton_liquidaciontotalMouseClicked

    private void jRadioButton_liquidacionindividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_liquidacionindividualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_liquidacionindividualActionPerformed

    private void jRadioButton_liquidacionindividualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_liquidacionindividualMouseClicked
        inicializar(false, true);
    }//GEN-LAST:event_jRadioButton_liquidacionindividualMouseClicked

    /**
     * @param args the command line arguments
     */
    
    int[] anchos = {40, 200, 50};
    String mes_causar="";
    Conexion conn= new Conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox_codigo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_abril;
    private javax.swing.JRadioButton jRadioButton_agosto;
    private javax.swing.JRadioButton jRadioButton_diciembre;
    private javax.swing.JRadioButton jRadioButton_enero;
    private javax.swing.JRadioButton jRadioButton_febrero;
    private javax.swing.JRadioButton jRadioButton_julio;
    private javax.swing.JRadioButton jRadioButton_junio;
    private javax.swing.JRadioButton jRadioButton_liquidacionindividual;
    private javax.swing.JRadioButton jRadioButton_liquidaciontotal;
    private javax.swing.JRadioButton jRadioButton_marzo;
    private javax.swing.JRadioButton jRadioButton_mayo;
    private javax.swing.JRadioButton jRadioButton_noviembre;
    private javax.swing.JRadioButton jRadioButton_octubre;
    private javax.swing.JRadioButton jRadioButton_septiembre;
    private javax.swing.JTextField jTextField_inmueble;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_nota;
    private javax.swing.ButtonGroup meses;
    // End of variables declaration//GEN-END:variables
}
