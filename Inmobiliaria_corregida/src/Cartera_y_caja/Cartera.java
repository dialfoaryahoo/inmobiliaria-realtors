/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cartera_y_caja;

import Models.Render;
import Models.acceso;

import inmobiliaria_fase01.Conexion;
import static inmobiliaria_fase01.Conexion.JOptionShowMessage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Dialfoar
 */
public class Cartera extends javax.swing.JDialog {

    /**
     * Creates new form Cartera
     */
    private DefaultTableModel modeloDeMiJTable;     
    public Cartera(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
 
        setSize(950,720);
        setLocationRelativeTo(null);
        setTitle("Cartera");
        jButton_Cinforme.setVisible(false);
        jButton_Iinforme.setVisible(false);
        jButton_Pinforme.setVisible(false);
        jButton_ADM_informe.setVisible(false);
        
        inicio();
        //listener();
        conn.establecer_conexion();        
        
        
        
        
        
        
        //JTable
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
        modeloDeMiJTable.addColumn("CEDULA");
        modeloDeMiJTable.addColumn("PROPIETARIO");
        modeloDeMiJTable.addColumn("DIRECCION");
        modeloDeMiJTable.addColumn("BARRIO");        
        jTable_ADM.setModel(modeloDeMiJTable);
        int[] anchos = {20, 20, 50, 150, 50};
        for(int i = 0; i < jTable_ADM.getColumnCount(); i++) {
            jTable_ADM.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        llenartabla();
        jTable_ADM.setDefaultRenderer (Object.class, new Render());        
        
        
    }

    public void llenartabla(){
//    public void llenartabla(String campo, String buscar){        

        conn.establecer_conexion();
        String consulta="select inm.codigo, pro.cedula, (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) AS nombrecompleto "
                + ",inm.direccion, inm.barrio "
                + "from inmuebles as inm inner join propietarios as pro on inm.cod_propietario = pro.cod_propietario  "
                + "where inm.estado = 1 order by inm.codinmueble desc limit 6 ";
                //+ "where "+campo+" = '"+buscar+"'";
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        
            while(n.next()){
        modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5)});
        }
        }
        catch(Exception e){}
    }
    
    public void llenartablaparametros(String campo, String buscar){        

        conn.establecer_conexion();
        limpiarTabla(jTable_ADM);
        String consulta ="";
        
        consulta ="select inm.codigo , pro.cedula , (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) AS pronombrecompleto, inm.direccion, inm.barrio "
                + "from propietarios as pro inner join  inmuebles as inm on pro.cod_propietario = inm.cod_propietario INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino  "
                + "where "+campo+" = '"+buscar+"' and inm.estado = 1 and arr.estado = 1 order by inm.codinmueble desc " ; 
        if(ADM_jcombobuscar.equals("todo")){
//            consulta ="select inm.codigo , pro.cedula , (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) AS pronombrecompleto, inm.direccion, inm.barrio "
//                    + "from propietarios as pro inner join  inmuebles as inm on pro.cod_propietario = inm.cod_propietario INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino  "
//                    + "where inm.estado = 1 and arr.estado = 1 order by inm.codinmueble desc " ;             
            consulta ="select inm.codigo , pro.cedula , (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) AS pronombrecompleto, inm.direccion, inm.barrio "
                    + "from propietarios as pro inner join  inmuebles as inm on pro.cod_propietario = inm.cod_propietario  "
                    + "where inm.estado = 1 order by inm.codinmueble desc " ;                         
            
            jText_ADMbuscar.setText("");
        }        
        System.out.println(consulta);
        ResultSet n=conn.consulta(consulta);
        try{
        
            while(n.next()){
                contadorconsulta ++;
                modeloDeMiJTable.addRow(new Object[]{n.getString(1),n.getString(2),n.getString(3),n.getString(4),n.getString(5)});
        }
              if(contadorconsulta == 0){
                    Conexion.JOptionShowMessage("+1", null, "LA BUSQUEDA NO ARROJO RESULTADOS");
                    
                }            
              contadorconsulta = 0;
        }
        catch(Exception e){}
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
        
    public void inicio(){
        //Inquilinos
        Inquilinoenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        Inquilinoacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
        Inquilinovacio();
        //Codeudores
        Codeudorenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);                
        Codeudorvacio();
        //propietarios
        Propietarioenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        Propietarioacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
        Propietariovacio();
        //inmuebles
        INMinmueble_enabled(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        INMpropietarioedit(falso, falso, falso, falso, falso, falso, falso);
        INMinmueblevacio();
        INMpropietariovacio();
        //Adm inmuebles
        ADMBTN_iniciales(falso, falso, falso, falso);
        ADMinmueblesedit(falso, falso, falso);
      
    }
    
//    public void listener(){
//        jText_Icodigo.addKeyListener(this);
//        jText_Icedula.addKeyListener(this);
//        jText_Pcodigo.addKeyListener(this);
//        jText_INMcodigo.addKeyListener(this);
//        jText_INM_PROcedula.addKeyListener(this);
//    }
    
    public void Inquilinoenable(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j, Boolean k, Boolean l, Boolean m, Boolean n, Boolean o, Boolean p, Boolean q){
        jText_Icodigo.setEnabled(a);
        jText_Icedula.setEnabled(b);
        jText_Iexpedida.setEnabled(c);
        jText_Inombres.setEnabled(d);
        jText_Iapellido1.setEnabled(e);
        jText_Iapellido2.setEnabled(f);
        jText_Icelular.setEnabled(g);
        jText_Ifijo.setEnabled(h);
        jText_Idireccioncasa.setEnabled(i);
        jText_Idireccionoficina.setEnabled(j);
        jText_Ibanco.setEnabled(k);
        jText_Icuenta.setEnabled(l);
        jText_Icodpredial.setEnabled(m);
        jText_Icodcontable.setEnabled(o);
        jText_Iemail.setEnabled(n);
        jText_Isaldo.setEnabled(p);
        jButton_Iguardar.setVisible(q);
        
        jText_Icodcontable.setText("123");
        jText_Icodcontable.setVisible(false);
        
        
    }
    
    public void Inquilinoacciones(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j){
        jButton_Ibuscar.setVisible(a);
        jButton_Iagregar.setVisible(b);
        jButton_Imodificar.setVisible(c);
        jButton_Ieliminar.setVisible(d);
        jButton_Iinforme.setVisible(e);
        
        jButton_Ibuscar.setSelected(f);
        jButton_Iagregar.setSelected(g);
        jButton_Imodificar.setSelected(h);
        jButton_Ieliminar.setSelected(i);
        jButton_Iinforme.setSelected(j);
        if(acc.getNivel().equals("1")){
            jButton_Ieliminar.setVisible(false);
            
        }
    }
    
    public void Inquilinovacio(){
        jText_Icodigo.setText("");
        jText_Icedula.setText("");
        jText_Iexpedida.setText("");
        jText_Inombres.setText("");
        jText_Iapellido1.setText("");
        jText_Iapellido2.setText("");
        jText_Icelular.setText("");
        jText_Ifijo.setText("");
        jText_Idireccioncasa.setText("");
        jText_Idireccionoficina.setText("");
        jText_Ibanco.setText("");
        jText_Icuenta.setText("");
        jText_Icodpredial.setText("");
        jText_Icodcontable.setText("");
        jText_Iemail.setText("");
        jText_Isaldo.setText("");
        
        
        jText_Icodcontable.setText("123");
    }      

    public void InquilinoBuscar(){
        String cedula = "";
        returnquery = 0;
        Icod_Inquilino = 0;
        conn.establecer_conexion();
            if(jText_Icodigo.getText().length() >= 1 && jText_Icedula.getText().length()>=1){
                JOptionPane.showMessageDialog(rootPane, "Utiliza Solo Codigo o Cedula");
            }else{
                if(jText_Icedula.getText().equals("")){
                    cedula = "0";
                }
                else{
                    cedula= jText_Icedula.getText();
                }
                String consulta="select codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta, email, cod_predial, cod_contable, estado_inquilino, cod_inquilino, saldo from inquilinos where estado_inquilino = 1 and cedula = "+cedula+" or codigo = '"+jText_Icodigo.getText().toUpperCase()+"'";
                System.out.println(consulta);
                ResultSet query5 = conn.consulta(consulta);
            try{
                while(query5.next()){
                    jText_Icodigo.setText(query5.getString(1));
                    jText_Icedula.setText(query5.getString(2));
                    jText_Iexpedida.setText(query5.getString(3));
                    jText_Inombres.setText(query5.getString(4));
                    jText_Iapellido1.setText(query5.getString(5));
                    jText_Iapellido2.setText(query5.getString(6));
                    jText_Idireccioncasa.setText(query5.getString(7));
                    jText_Idireccionoficina.setText(query5.getString(8));
                    jText_Icelular.setText(query5.getString(9));
                    jText_Ifijo.setText(query5.getString(10));
                    jText_Ibanco.setText(query5.getString(11));
                    jText_Icuenta.setText(query5.getString(12));
                    jText_Iemail.setText(query5.getString(13));
                    jText_Icodpredial.setText(query5.getString(14));
                    jText_Icodcontable.setText(query5.getString(15));                        
                    Icod_Inquilino= Integer.parseInt(query5.getString(17));
                    jText_Isaldo.setText(query5.getString(18));
                    returnquery = 1;
                    
                }
                System.out.println(returnquery);
                
                if(returnquery ==0){
                    JOptionPane.showMessageDialog(rootPane, "No Existe el Inquilino");
                }
                else if(returnquery ==1){
                    Inquilinoacciones(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, falso);
                }
                
                
            }
            catch(Exception e){
                }
            }    
    }
    
    public void Propietarioenable(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j, Boolean k, Boolean l, Boolean m, Boolean n, Boolean o, Boolean p, Boolean q){
        jText_Pcodigo.setEnabled(a);
        jText_Pcedula.setEnabled(b);
        jText_Pexpedida.setEnabled(c);
        jText_Pnombres.setEnabled(d);
        jText_Papellido1.setEnabled(e);
        jText_Papellido2.setEnabled(f);
        jText_Pcelular.setEnabled(g);
        jText_Pfijo.setEnabled(h);
        jText_Pdireccioncasa.setEnabled(i);
        jText_Pdireccionoficina.setEnabled(j);
        jText_Pbanco.setEnabled(k);
        jText_Pcuenta.setEnabled(l);
        jText_Pcodpredial.setEnabled(m);
        jText_Pcodcontable.setEnabled(o);
        jText_Pemail.setEnabled(n);
        jText_Psaldo.setEnabled(p);
        jButton_Pguardar.setVisible(q);
        
        jText_Pcodcontable.setVisible(false);
        jText_Pcodcontable.setText("123");
        
    }
    
    
    public void Propietarioacciones(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j){
        jButton_Pbuscar.setVisible(a);
        jButton_Pagregar.setVisible(b);
        jButton_Pmodificar.setVisible(c);
        jButton_Peliminar.setVisible(d);
        jButton_Pinforme.setVisible(e);
        
        jButton_Pbuscar.setSelected(f);
        jButton_Pagregar.setSelected(g);
        jButton_Pmodificar.setSelected(h);
        jButton_Peliminar.setSelected(i);
        jButton_Pinforme.setSelected(j);
        
        if(acc.getNivel().equals("1")){
            jButton_Peliminar.setVisible(false);
            
        }        
    }
    
    public void Propietariovacio(){
        jText_Pcodigo.setText("");
        jText_Pcedula.setText("");
        jText_Pexpedida.setText("");
        jText_Pnombres.setText("");
        jText_Papellido1.setText("");
        jText_Papellido2.setText("");
        jText_Pcelular.setText("");
        jText_Pfijo.setText("");
        jText_Pdireccioncasa.setText("");
        jText_Pdireccionoficina.setText("");
        jText_Pbanco.setText("");
        jText_Pcuenta.setText("");
        jText_Pcodpredial.setText("");
        jText_Pcodcontable.setText("");
        jText_Pemail.setText("");
        jText_Psaldo.setText("");
        
        
        jText_Pcodcontable.setText("123");
    }      

    public void PropietariosBuscar(){
        String cedula = "";
        returnquery = 0;
        Pcod_propietario = 0;
        conn.establecer_conexion();
            if(jText_Pcodigo.getText().length() >= 1 && jText_Pcedula.getText().length()>=1){
                JOptionPane.showMessageDialog(rootPane, "Utiliza Solo Codigo o Cedula");
            }else{
                if(jText_Pcedula.getText().equals("")){
                    cedula = "0";
                }
                else{
                    cedula= jText_Pcedula.getText();
                }
                String consulta="select codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta, email, cod_predial, cod_contable, estado_propietario, cod_propietario, saldo from propietarios where estado_propietario = 1 and cedula = "+cedula+" or codigo = '"+jText_Pcodigo.getText().toUpperCase()+"'";
                System.out.println(consulta);
                ResultSet query5 = conn.consulta(consulta);
                System.out.println(returnquery);
            try{
                while(query5.next()){
                    jText_Pcodigo.setText(query5.getString(1));
                    jText_Pcedula.setText(query5.getString(2));
                    jText_Pexpedida.setText(query5.getString(3));
                    jText_Pnombres.setText(query5.getString(4));
                    jText_Papellido1.setText(query5.getString(5));
                    jText_Papellido2.setText(query5.getString(6));
                    jText_Pdireccioncasa.setText(query5.getString(7));
                    jText_Pdireccionoficina.setText(query5.getString(8));
                    jText_Pcelular.setText(query5.getString(9));
                    jText_Pfijo.setText(query5.getString(10));
                    jText_Pbanco.setText(query5.getString(11));
                    jText_Pcuenta.setText(query5.getString(12));
                    jText_Pemail.setText(query5.getString(13));
                    jText_Pcodpredial.setText(query5.getString(14));
                    jText_Pcodcontable.setText(query5.getString(15));                        
                    Pcod_propietario= Integer.parseInt(query5.getString(17));
                    jText_Psaldo.setText(query5.getString(18));
                    returnquery = 1;
                    
                }
                System.out.println(returnquery);
                
                if(returnquery ==0){
                    JOptionPane.showMessageDialog(rootPane, "No Existe el Propietario");
                }
                else if(returnquery ==1){
                    Propietarioacciones(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, falso);
                }
                
                
            }
            catch(Exception e){
            }
            }    
    }
    
    public void Codeudorenable(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j, Boolean k, Boolean l, Boolean m, Boolean n, Boolean o, Boolean p, Boolean q){
        jText_Ccodigo.setEnabled(a);
        jText_Ccedula.setEnabled(b);
        jText_Cexpedida.setEnabled(c);
        jText_Cnombres.setEnabled(d);
        jText_Capellido1.setEnabled(e);
        jText_Capellido2.setEnabled(f);
        jText_Ccelular.setEnabled(g);
        jText_Cfijo.setEnabled(h);
        jText_Cdireccioncasa.setEnabled(i);
        jText_Cdireccionoficina.setEnabled(j);
        jText_Cbanco.setEnabled(k);
        jText_Ccuenta.setEnabled(l);
        jText_Ccodpredial.setEnabled(m);
        jText_Ccodcontable.setEnabled(o);
        jText_Cemail.setEnabled(n);
        jText_Ccartera.setEnabled(p);
        jButton_Cguardar.setVisible(q);
        
        jText_Ccodcontable.setVisible(false);
        jText_Ccodcontable.setText("123");
        
    }
    
    public void Codeudoracciones(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j){
        jButton_Cbuscar.setVisible(a);
        jButton_Cagregar.setVisible(b);
        jButton_Cmodificar.setVisible(c);
        jButton_Celiminar.setVisible(d);
        jButton_Cinforme.setVisible(e);
        
        jButton_Cbuscar.setSelected(f);
        jButton_Cagregar.setSelected(g);
        jButton_Cmodificar.setSelected(h);
        jButton_Celiminar.setSelected(i);
        jButton_Cinforme.setSelected(j);
        
        if(acc.getNivel().equals("1")){
            jButton_Celiminar.setVisible(false);
            
        }        
    }
    
    public void Codeudorvacio(){
        jText_Ccodigo.setText("");
        jText_Ccedula.setText("");
        jText_Cexpedida.setText("");
        jText_Cnombres.setText("");
        jText_Capellido1.setText("");
        jText_Capellido2.setText("");
        jText_Ccelular.setText("");
        jText_Cfijo.setText("");
        jText_Cdireccioncasa.setText("");
        jText_Cdireccionoficina.setText("");
        jText_Cbanco.setText("");
        jText_Ccuenta.setText("");
        jText_Ccodpredial.setText("");
        jText_Ccodcontable.setText("");
        jText_Cemail.setText("");
        jText_Ccartera.setText("");
        
        jText_Ccodcontable.setText("123");
    } 

public void CodeudorBuscar(){
        String cedula = "";
        returnquery = 0;
        Ccod_codeudor = 0;
        conn.establecer_conexion();
            if(jText_Ccodigo.getText().length() >= 1 && jText_Ccedula.getText().length()>=1){
                JOptionPane.showMessageDialog(rootPane, "Utiliza Solo Codigo o Cedula");
            }else{
                if(jText_Ccedula.getText().equals("")){
                    cedula = "0";
                }
                else{
                    cedula= jText_Ccedula.getText();
                }
                String consulta="select codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta, email, cod_predial, cod_contable, estado_codeudor, cod_codeudor, cartera from codeudores where  estado_codeudor = 1 and cedula = "+cedula+" or codigo = '"+jText_Ccodigo.getText().toUpperCase()+"'";
                System.out.println(consulta);
                ResultSet query5 = conn.consulta(consulta);
            try{
                while(query5.next()){
                    jText_Ccodigo.setText(query5.getString(1));
                    jText_Ccedula.setText(query5.getString(2));
                    jText_Cexpedida.setText(query5.getString(3));
                    jText_Cnombres.setText(query5.getString(4));
                    jText_Capellido1.setText(query5.getString(5));
                    jText_Capellido2.setText(query5.getString(6));
                    jText_Cdireccioncasa.setText(query5.getString(7));
                    jText_Cdireccionoficina.setText(query5.getString(8));
                    jText_Ccelular.setText(query5.getString(9));
                    jText_Cfijo.setText(query5.getString(10));
                    jText_Cbanco.setText(query5.getString(11));
                    jText_Ccuenta.setText(query5.getString(12));
                    jText_Cemail.setText(query5.getString(13));
                    jText_Ccodpredial.setText(query5.getString(14));
                    jText_Ccodcontable.setText(query5.getString(15));                        
                    Ccod_codeudor= Integer.parseInt(query5.getString(17));
                    jText_Ccartera.setText(query5.getString(18));
                    returnquery = 1;
                    System.out.println(returnquery);                    
                    
                }

                
                if(returnquery ==0){
                    JOptionPane.showMessageDialog(rootPane, "No Existe el Codeudor");
                }
                else if(returnquery ==1){
                    Codeudoracciones(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, falso);
                }
                
                
            }
            catch(Exception e){
            }
            }    
    }
    public void INMinmueble_enabled(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g, Boolean h, Boolean i, Boolean j, Boolean k, Boolean l, Boolean m, Boolean n, Boolean o, Boolean p, Boolean q, Boolean r, Boolean s, Boolean t, Boolean u, Boolean v, Boolean y, Boolean z, Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5, Boolean a6, Boolean a7, Boolean a8, Boolean a9, Boolean b1, Boolean b2, Boolean b3){
        jText_INMcodigo.setEnabled(a);
        jText_INMdireccion.setEnabled(b);
        jText_INMedificio.setEnabled(c);
        jText_INMavaluo.setEnabled(d);
        jText_INMbarrio.setEnabled(e);
        jText_INMmunicipio.setEnabled(f);
        jText_INMcanon.setEnabled(g);
        jText_INMadmon.setEnabled(h);
        jText_INMcomicion.setEnabled(i);
        jText_INMdiacausacion.setEnabled(j);
        jText_INMdiaini.setEnabled(k);
        jText_INMdiafin.setEnabled(l);
        //jText_INMregimen.setEnabled(m);
        jText_INMiva.setEnabled(n);
        jComboBox_INMuso.setEnabled(o);
        jComboBox_INMmes.setEnabled(p);
        jComboBox_INMclase.setEnabled(q);
        jComboBox_INMregimen.setEnabled(m);
        jRadio_INMincluida.setEnabled(r);
        jRadio_INMnoincluida.setEnabled(s);
        jRadio_INMconsigna.setEnabled(t);
        jRadio_INMtransferencia.setEnabled(u);
        jRadio_INMbanco.setEnabled(v);
        dateChooserCombo_INMfechaini.setEnabled(y);
        jCheckBox_INMdisponible.setEnabled(z);
        //PROPIETARIOS
        jText_INM_PROcedula.setEnabled(a2);
        jText_INM_PROnombre.setEnabled(a3);
        jText_INM_PROcelular.setEnabled(a4);
        jText_INM_PROfijo.setEnabled(a5);
        jText_INM_PROemail.setEnabled(a6);
        jText_INM_PRObanco.setEnabled(a7);
        jText_INM_PROncuenta.setEnabled(a8);        

        //ARRENDATARIOS
        jText_INM_ARcedula.setEnabled(a2);
        jText_INM_ARnombre.setEnabled(a3);
        jText_INM_ARcelular.setEnabled(a4);
        jText_INM_ARfijo.setEnabled(a5);
        jText_INM_ARemail.setEnabled(a6);
        jText_INM_ARbanco.setEnabled(a7);
        jText_INM_ARncuenta.setEnabled(a8);                
        
        
        jButton_INMguardar.setVisible(a1);
        
        
    }
    
    public void INMinmueblevacio(){
    //Setear todo inmueble vacio 
        jText_INMcodigo.setText("");
        jText_INMdireccion.setText("");
        jText_INMedificio.setText("");
        jText_INMavaluo.setText("");
        jText_INMbarrio.setText("");
        jText_INMmunicipio.setText("");
        jText_INMcanon.setText("");
        jText_INMadmon.setText("");
        jText_INMcomicion.setText("");
        jText_INMdiacausacion.setText("");
        jText_INMdiaini.setText("");
        jText_INMdiafin.setText("");
        //jText_INMregimen.setText("");
        jText_INMiva.setText("");
        jComboBox_INMuso.setSelectedIndex(0);
        jComboBox_INMmes.setSelectedIndex(0);
        jComboBox_INMclase.setSelectedIndex(0);
        jComboBox_INMregimen.setSelectedIndex(0);
//        jRadio_INMincluida.setSelected(false);
//        jRadio_INMnoincluida.setSelected(false);                
//        jRadio_INMconsigna.setSelected(false);
//        jRadio_INMtransferencia.setSelected(false);
//        jRadio_INMbanco.setSelected(false);
        
        jCheckBox_INMdisponible.setSelected(false);
        
        //Vaciar Grupos de radio buttons
        
        Grupo_INMincluida.clearSelection();
        Grupo_INMRecoje.clearSelection();
        
        //vaciar variables
        INMcod_arrendatario = 0;
        INMcod_arrendatarioant = 0;

    }
    
    public void INMpropietariovacio(){
        jText_INM_PROcedula.setText("");
        jText_INM_PROnombre.setText("");
        jText_INM_PROcelular.setText("");
        jText_INM_PROfijo.setText("");
        jText_INM_PROemail.setText("");
        jText_INM_PRObanco.setText("");
        jText_INM_PROncuenta.setText("");
        //validar
        INMcod_propietario=0;    
    }
    
    public void INMinquilinovacio(){
        jText_INM_ARcedula.setText("");
        jText_INM_ARnombre.setText("");
        jText_INM_ARcelular.setText("");
        jText_INM_ARfijo.setText("");
        jText_INM_ARemail.setText("");
        jText_INM_ARbanco.setText("");
        jText_INM_ARncuenta.setText("");
        //validar
        INMcod_arrendatario=0;    
    }
    
    public void INMpropietarioedit(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f, Boolean g){
        jText_INM_PROcedula.setEditable(a);
        jText_INM_PROnombre.setEditable(b);
        jText_INM_PROcelular.setEditable(c);
        jText_INM_PROfijo.setEditable(d);
        jText_INM_PROemail.setEditable(e);
        jText_INM_PRObanco.setEditable(f);
        jText_INM_PROncuenta.setEditable(g);
    }
    
    public void INMinquilinoedit(Boolean a, Boolean b, Boolean c, Boolean d, Boolean e, Boolean f){
        jText_INM_ARcedula.setEditable(a);
        jText_INM_ARnombre.setEditable(a);
        jText_INM_ARcelular.setEditable(b);
        jText_INM_ARfijo.setEditable(c);
        jText_INM_ARemail.setEditable(d);
        jText_INM_ARbanco.setEditable(e);
        jText_INM_ARncuenta.setEditable(f);
    }    
    public void ADMinmueblesedit (Boolean a, Boolean b, Boolean c){
        jText_ADMbuscar.setEditable(a);
        jText_ADMbuscar.setEnabled(a);
        jComboBox1.setEnabled(a);
        jTable_ADM.setVisible(a);
        
        jText_ADM_codinm.setEditable(b);
        jText_ADM_cedpro.setEditable(b);
        jText_ADM_nompro.setEditable(b);
        jText_ADM_direccion.setEditable(b);
        jText_ADM_barrio.setEditable(b);
        jText_ADM_codpro.setEditable(b);
        jText_ADM_cedinq.setEditable(b);
        jText_ADM_codinq.setEditable(b);
        jText_ADM_nominq.setEditable(b);
        jText_ADM_municipio.setEditable(b);
        jText_ADM_diacausacion.setEditable(b);
        jText_ADM_regimen.setEditable(b);
        jText_ADM_inmdisponible.setEditable(b);
        
        jText_ADM_codinm.setEnabled(c);
        jText_ADM_cedpro.setEnabled(c);
        jText_ADM_nompro.setEnabled(c);
        jText_ADM_direccion.setEnabled(c);
        jText_ADM_barrio.setEnabled(c);
        jText_ADM_codpro.setEnabled(c);
        jText_ADM_cedinq.setEnabled(c);
        jText_ADM_codinq.setEnabled(c);
        jText_ADM_nominq.setEnabled(c);
        jText_ADM_municipio.setEnabled(c);
        jText_ADM_diacausacion.setEnabled(c);
        jText_ADM_regimen.setEnabled(c);
        jText_ADM_inmdisponible.setEnabled(c);
        
    }
    public void ADMinmueble_vacio(){
        jText_ADMbuscar.setText("");
        jText_ADM_codinm.setText("");
        jText_ADM_cedpro.setText("");
        jText_ADM_nompro.setText("");
        jText_ADM_direccion.setText("");
        jText_ADM_barrio.setText("");
        jText_ADM_codpro.setText("");
        jText_ADM_cedinq.setText("");
        jText_ADM_codinq.setText("");
        jText_ADM_nominq.setText("");
        jText_ADM_municipio.setText("");
        jText_ADM_diacausacion.setText("");
        jText_ADM_regimen.setText("");
        jText_ADM_inmdisponible.setText("");
        jComboBox1.setSelectedIndex(0);
    }

    public void ADMBTN_iniciales(Boolean a, Boolean b,Boolean c, Boolean d){
        jButton_ADM_buscar.setSelected(a);
        jButton_ADM_eliminar.setSelected(b);
        jButton_ADM_eliminar.setVisible(c);
        jButton_ADM_informe.setSelected(d);
        jButton_ADM_informe.setVisible(c);
        INMaccion = 0;
        
        if(acc.getNivel().equals("1")){
            jButton_ADM_eliminar.setVisible(false);
            
        }        
    }    
    public void INMBTN_iniciales(Boolean a, Boolean b){
        jButton_INMagregar.setSelected(a);
        jButton_INMmodificar.setSelected(b);
        INMaccion = 1;
        
    }
    
    
    public int INMvalidarcampos(){
        int validar = 0;
                if(jText_INMcodigo.getText().trim().equals("")||jText_INMdireccion.getText().trim().equals("")||jComboBox_INMuso.getSelectedIndex()==0
                    ||jComboBox_INMclase.getSelectedIndex()==0||jText_INMedificio.getText().trim().equals("")||jText_INMavaluo.getText().trim().equals("")
                    ||jText_INMbarrio.getText().trim().equals("")||jText_INMmunicipio.getText().trim().equals("")||jText_INMcanon.getText().trim().equals("")
                    ||jText_INMadmon.getText().trim().equals("")||jText_INMcomicion.getText().trim().equals("")||jText_INMdiacausacion.getText().trim().equals("")
                    ||jText_INMdiaini.getText().trim().equals("")||jText_INMdiafin.getText().trim().equals("")||jComboBox_INMmes.getSelectedIndex()==0
                    ||jComboBox_INMregimen.getSelectedIndex()==0||jText_INMiva.getText().trim().equals("")){

                    JOptionPane.showMessageDialog(rootPane, "Validar Todos los datos por favor");
                    validar=1;
                }

                //Validar Si la Administracion esta incluida
                if(!(jRadio_INMincluida.isSelected()||jRadio_INMnoincluida.isSelected())){
                    JOptionPane.showMessageDialog(rootPane, "La Administracion esta incluida?");
                    validar=1;
                }
                //Validar  Tipo de forma para recojer saldo 
                if(!(jRadio_INMconsigna.isSelected()||jRadio_INMtransferencia.isSelected()||jRadio_INMbanco.isSelected())){
                    JOptionPane.showMessageDialog(rootPane, "Seleccionar un Metodo de Pago?");
                    validar=1;
                }

                //validar propietario
                if(jText_INM_PROnombre.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(rootPane, "Por Favor agregar un Propietario");
                    validar=1;
                }
                

                
//                if(jText_INM_ARnombre.getText().trim().equals("")){
//                    int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Dejaras el Inmueble sin Inquilino?"+jText_Inombres.getText()+"?", "Advertencia Inquilino", JOptionPane.OK_CANCEL_OPTION);
//                    if(seletedvalue ==JOptionPane.YES_OPTION ){                    
//                        INMcod_arrendatario = 1;
//                        jText_INM_ARnombre.setText("Realtors");
//                    }
//                    validar=1;
//                }
                return  validar;
    }
    //PROPIETARIOS 
    
    public int Pvalidarcampos(){
        int pvalidar = 1;
               if(jText_Pcodigo.getText().trim().equals("")||jText_Pcedula.getText().trim().equals("")
                    ||jText_Pexpedida.getText().trim().equals("")||jText_Pnombres.getText().trim().equals("")
                    ||jText_Papellido1.getText().trim().equals("")||jText_Papellido2.getText().trim().equals("")
                    ||jText_Pdireccioncasa.getText().trim().equals("")||jText_Pdireccionoficina.getText().trim().equals("")
                    ||jText_Pcodpredial.getText().trim().equals("")||jText_Pcodcontable.getText().trim().equals("")){

                        JOptionPane.showMessageDialog(rootPane, "Validar Todos los datos por favor");
                        pvalidar=2;
                }
                
                if(Paccion == 2){
                conn.establecer_conexion();
                ResultSet query4 = conn.consulta("select codigo, cedula from propietarios where  cedula = "+jText_Pcedula.getText()+" or codigo = '"+jText_Pcodigo.getText()+"'");
                System.out.println(query4);
                    try{
                        while(query4.next()){
                            pvalidar = 2;
                            JOptionPane.showMessageDialog(rootPane, "Ya Existe el Codigo O la Cedula Registrada del Propietario");
                        }
                    }
                    catch (Exception ec){
                    }                
                }

                return  pvalidar;
    }

    
        public int validar(Object[] datos) { 
            int validar = 1;
            for (int i = 0; i <= datos.length - 1; i++) { 
                if (datos[i].toString().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "Por Favor Diligencias todos los Datos"); 
                validar = 2;
                break;
                }
            } 
            if(jTabbedPane5.getSelectedIndex()==0){
                if(Iaccion == 2){
                conn.establecer_conexion();
                ResultSet query4 = conn.consulta("select codigo, cedula, estado_inquilino from inquilinos where  cedula = "+jText_Icedula.getText()+" or codigo = '"+jText_Icodigo.getText()+"'");
                System.out.println(query4);
                    try{
                        while(query4.next()){
                            int estado = 0;
                            estado = Integer.parseInt(query4.getString(3));
                            if(estado == 2){
                                JOptionPane.showMessageDialog(rootPane, "El Inquilino Se encuentra desactivado, contactar el administrador para Activarlo");
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "El Codigo O la Cedula Registrada del Inquilino se encuentra asiganada");
                                
                            }
                            validar = 2;
                        }
                    }
                    catch (Exception ec){
                    }
                }             
            }
            if(jTabbedPane5.getSelectedIndex()==1){
                if(Caccion == 2){
                conn.establecer_conexion();
                String consulta = "select codigo, cedula, estado_codeudor from codeudores where  cedula = "+jText_Ccedula.getText()+" or codigo = '"+jText_Ccodigo.getText()+"'";
                System.out.println(consulta);
                ResultSet query4 = conn.consulta(consulta);
                System.out.println(query4);
                    try{
                        while(query4.next()){
                            int estado = 0;
                            estado = Integer.parseInt(query4.getString(3));
                            System.out.println(estado);
                            if(estado==2){
                                JOptionPane.showMessageDialog(rootPane, "El Codeudor Se encuentra desactivado, contactar el administrador para Activarlo");                                
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "Ya Existe el Codigo O la Cedula Registrada del Codeudor");
                            }
                            validar = 2;
                        }
                    }
                    catch (Exception ec){
                    }
                }            
            }
            if(jTabbedPane5.getSelectedIndex()==2){                
                if(Paccion == 2){
                conn.establecer_conexion();
                ResultSet query4 = conn.consulta("select codigo, cedula, estado_propietario from propietarios where  cedula = "+jText_Pcedula.getText()+" or codigo = '"+jText_Pcodigo.getText()+"'");
                System.out.println(query4);
                    try{
                        while(query4.next()){
                            int estado = 0;
                            estado = Integer.parseInt(query4.getString(3));
                            if(estado == 2){
                                JOptionPane.showMessageDialog(rootPane, "El Propietario Se encuentra desactivado, contactar el administrador para Activarlo");
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "El Codigo O la Cedula Registrada del Propietario se encuentra asiganada");
                                
                            }
                            validar = 2;
                        }
                    }
                    catch (Exception ec){
                    }
                }                
            }


            
            return validar; 
        } 

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo_INMRecoje = new javax.swing.ButtonGroup();
        Grupo_INMincluida = new javax.swing.ButtonGroup();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanelInquilinos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jText_Icodigo = new javax.swing.JTextField();
        jText_Icedula = new javax.swing.JTextField();
        jText_Iexpedida = new javax.swing.JTextField();
        jText_Inombres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_Ieliminar = new javax.swing.JButton();
        jButton_Iinforme = new javax.swing.JButton();
        jButton_Ibuscar = new javax.swing.JButton();
        jButton_Iagregar = new javax.swing.JButton();
        jButton_Imodificar = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jText_Iapellido1 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jText_Iapellido2 = new javax.swing.JTextField();
        jText_Ifijo = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jText_Icelular = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jText_Idireccioncasa = new javax.swing.JTextField();
        jText_Idireccionoficina = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jText_Icuenta = new javax.swing.JTextField();
        jText_Ibanco = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jText_Icodpredial = new javax.swing.JTextField();
        jText_Icodcontable = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jText_Iemail = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jText_Isaldo = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jButton_Iguardar = new javax.swing.JButton();
        jButton_Ilimpiar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanelCodeudores = new javax.swing.JPanel();
        jText_Ccodpredial = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jText_Ccodigo = new javax.swing.JTextField();
        jText_Ccedula = new javax.swing.JTextField();
        jText_Cexpedida = new javax.swing.JTextField();
        jText_Capellido2 = new javax.swing.JTextField();
        jText_Cnombres = new javax.swing.JTextField();
        jText_Ccodcontable = new javax.swing.JTextField();
        jText_Ccelular = new javax.swing.JTextField();
        jText_Cdireccioncasa = new javax.swing.JTextField();
        jText_Ccartera = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jButton_Celiminar = new javax.swing.JButton();
        jButton_Cinforme = new javax.swing.JButton();
        jButton_Cguardar = new javax.swing.JButton();
        jButton_Cagregar = new javax.swing.JButton();
        jButton_Cmodificar = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        jText_Cdireccionoficina = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jText_Cbanco = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jText_Ccuenta = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jText_Capellido1 = new javax.swing.JTextField();
        jText_Cfijo = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jButton_Cbuscar = new javax.swing.JButton();
        jButton_Climpiar = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jText_Cemail = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jPanelPropietarios = new javax.swing.JPanel();
        jText_Pcodpredial = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jText_Pcodigo = new javax.swing.JTextField();
        jText_Pcedula = new javax.swing.JTextField();
        jText_Pexpedida = new javax.swing.JTextField();
        jText_Papellido2 = new javax.swing.JTextField();
        jText_Pnombres = new javax.swing.JTextField();
        jText_Pcodcontable = new javax.swing.JTextField();
        jText_Pcelular = new javax.swing.JTextField();
        jText_Pdireccioncasa = new javax.swing.JTextField();
        jText_Psaldo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton_Peliminar = new javax.swing.JButton();
        jButton_Pinforme = new javax.swing.JButton();
        jButton_Pguardar = new javax.swing.JButton();
        jButton_Pagregar = new javax.swing.JButton();
        jButton_Pmodificar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jText_Pdireccionoficina = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jText_Pbanco = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jText_Pcuenta = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jText_Papellido1 = new javax.swing.JTextField();
        jText_Pfijo = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jButton_Pbuscar = new javax.swing.JButton();
        jButton_Plimpiar = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jText_Pemail = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jPanelAdmInm = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton_ADM_buscar = new javax.swing.JButton();
        jButton_ADM_eliminar = new javax.swing.JButton();
        jButton_ADM_informe = new javax.swing.JButton();
        jText_ADMbuscar = new javax.swing.JTextField();
        jButton_Plimpiar1 = new javax.swing.JButton();
        jLabel109 = new javax.swing.JLabel();
        jText_ADM_codinm = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jText_ADM_diacausacion = new javax.swing.JTextField();
        jText_ADM_direccion = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ADM = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel114 = new javax.swing.JLabel();
        jText_ADM_cedpro = new javax.swing.JTextField();
        jText_ADM_nominq = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jText_ADM_codpro = new javax.swing.JTextField();
        jText_ADM_cedinq = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jText_ADM_codinq = new javax.swing.JTextField();
        jLabel118 = new javax.swing.JLabel();
        jText_ADM_barrio = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jText_ADM_municipio = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jText_ADM_regimen = new javax.swing.JTextField();
        jLabel113 = new javax.swing.JLabel();
        jText_ADM_nompro = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jText_ADM_inmdisponible = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jPanelInmuebles = new javax.swing.JPanel();
        jButton_INMagregar = new javax.swing.JButton();
        jButton_INMmodificar = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jText_INMcodigo = new javax.swing.JTextField();
        jText_INMdireccion = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox_INMuso = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jComboBox_INMmes = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        dateChooserCombo_INMfechaini = new datechooser.beans.DateChooserCombo();
        jLabel35 = new javax.swing.JLabel();
        jText_INMedificio = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jText_INMbarrio = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jText_INMmunicipio = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jText_INMavaluo = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jText_INMcanon = new javax.swing.JTextField();
        jText_INMadmon = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jRadio_INMnoincluida = new javax.swing.JRadioButton();
        jRadio_INMbanco = new javax.swing.JRadioButton();
        jLabel41 = new javax.swing.JLabel();
        jCheckBox_INMdisponible = new javax.swing.JCheckBox();
        jLabel42 = new javax.swing.JLabel();
        jText_INMcomicion = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jText_INMdiacausacion = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jText_INMdiaini = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jText_INMdiafin = new javax.swing.JTextField();
        jComboBox_INMclase = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jText_INM_PROcedula = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jText_INM_PROnombre = new javax.swing.JTextField();
        jText_INM_PROemail = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jText_INM_PROcelular = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jText_INM_PROfijo = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jRadio_INMincluida = new javax.swing.JRadioButton();
        jRadio_INMconsigna = new javax.swing.JRadioButton();
        jRadio_INMtransferencia = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jText_INMiva = new javax.swing.JTextField();
        jButton_INMguardar = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jText_INM_PRObanco = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jText_INM_PROncuenta = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jText_INM_ARncuenta = new javax.swing.JTextField();
        jText_INM_ARfijo = new javax.swing.JTextField();
        jText_INM_ARnombre = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jText_INM_ARcelular = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jText_INM_ARbanco = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jText_INM_ARemail = new javax.swing.JTextField();
        jText_INM_ARcedula = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jComboBox_INMregimen = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane5MouseClicked(evt);
            }
        });

        jPanelInquilinos.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Codigo");
        jPanelInquilinos.add(jLabel2);
        jLabel2.setBounds(220, 30, 160, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cedula");
        jPanelInquilinos.add(jLabel3);
        jLabel3.setBounds(220, 70, 160, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Expedida");
        jPanelInquilinos.add(jLabel4);
        jLabel4.setBounds(220, 110, 160, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombres");
        jPanelInquilinos.add(jLabel5);
        jLabel5.setBounds(220, 150, 160, 40);

        jText_Icodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Icodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IcodigoActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jText_Icodigo);
        jText_Icodigo.setBounds(340, 30, 220, 40);

        jText_Icedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Icedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IcedulaActionPerformed(evt);
            }
        });
        jText_Icedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_IcedulaKeyTyped(evt);
            }
        });
        jPanelInquilinos.add(jText_Icedula);
        jText_Icedula.setBounds(340, 70, 410, 40);

        jText_Iexpedida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Iexpedida);
        jText_Iexpedida.setBounds(340, 110, 410, 40);

        jText_Inombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Inombres);
        jText_Inombres.setBounds(340, 150, 410, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Acción a realizar");
        jPanelInquilinos.add(jLabel6);
        jLabel6.setBounds(0, 20, 210, 25);

        jButton_Ieliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Ieliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_Ieliminar.setText("Eliminar");
        jButton_Ieliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IeliminarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Ieliminar);
        jButton_Ieliminar.setBounds(30, 270, 140, 60);

        jButton_Iinforme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Iinforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514254_Copy v2.png"))); // NOI18N
        jButton_Iinforme.setText("Informe");
        jPanelInquilinos.add(jButton_Iinforme);
        jButton_Iinforme.setBounds(30, 420, 140, 60);

        jButton_Ibuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Ibuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton_Ibuscar.setText("Buscar");
        jButton_Ibuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IbuscarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Ibuscar);
        jButton_Ibuscar.setBounds(30, 60, 140, 60);

        jButton_Iagregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Iagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_Iagregar.setText("Agregar");
        jButton_Iagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IagregarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Iagregar);
        jButton_Iagregar.setBounds(30, 130, 140, 60);

        jButton_Imodificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Imodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_Imodificar.setText("Modificar");
        jButton_Imodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImodificarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Imodificar);
        jButton_Imodificar.setBounds(30, 200, 140, 60);

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("Primer Apellido");
        jPanelInquilinos.add(jLabel76);
        jLabel76.setBounds(220, 190, 160, 40);

        jText_Iapellido1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Iapellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_Iapellido1ActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jText_Iapellido1);
        jText_Iapellido1.setBounds(340, 190, 160, 40);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setText("Seg. Apellido");
        jPanelInquilinos.add(jLabel77);
        jLabel77.setBounds(500, 190, 160, 40);

        jText_Iapellido2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Iapellido2);
        jText_Iapellido2.setBounds(590, 190, 160, 40);

        jText_Ifijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ifijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_IfijoKeyTyped(evt);
            }
        });
        jPanelInquilinos.add(jText_Ifijo);
        jText_Ifijo.setBounds(590, 230, 160, 40);

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setText("Tel. Casa");
        jPanelInquilinos.add(jLabel78);
        jLabel78.setBounds(510, 230, 160, 40);

        jText_Icelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Icelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IcelularActionPerformed(evt);
            }
        });
        jText_Icelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_IcelularKeyTyped(evt);
            }
        });
        jPanelInquilinos.add(jText_Icelular);
        jText_Icelular.setBounds(340, 230, 160, 40);

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Celular");
        jPanelInquilinos.add(jLabel79);
        jLabel79.setBounds(220, 230, 160, 40);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Direccion Casa");
        jPanelInquilinos.add(jLabel80);
        jLabel80.setBounds(220, 270, 160, 40);

        jText_Idireccioncasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Idireccioncasa);
        jText_Idireccioncasa.setBounds(340, 270, 410, 40);

        jText_Idireccionoficina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Idireccionoficina);
        jText_Idireccionoficina.setBounds(340, 310, 410, 40);

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Direccion Oficina");
        jPanelInquilinos.add(jLabel81);
        jLabel81.setBounds(220, 310, 160, 40);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setText("# Cuenta");
        jPanelInquilinos.add(jLabel82);
        jLabel82.setBounds(500, 350, 160, 40);

        jText_Icuenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Icuenta);
        jText_Icuenta.setBounds(600, 350, 150, 40);

        jText_Ibanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ibanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IbancoActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jText_Ibanco);
        jText_Ibanco.setBounds(340, 350, 150, 40);

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setText("Banco");
        jPanelInquilinos.add(jLabel83);
        jLabel83.setBounds(220, 350, 160, 40);

        jText_Icodpredial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Icodpredial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IcodpredialActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jText_Icodpredial);
        jText_Icodpredial.setBounds(340, 390, 410, 40);

        jText_Icodcontable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Icodcontable.setText("123");
        jText_Icodcontable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_IcodcontableActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jText_Icodcontable);
        jText_Icodcontable.setBounds(600, 390, 150, 40);

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Codeudor");
        jPanelInquilinos.add(jLabel84);
        jLabel84.setBounds(500, 390, 100, 40);

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setText("Cod. Predial");
        jPanelInquilinos.add(jLabel85);
        jLabel85.setBounds(220, 390, 160, 40);

        jText_Iemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Iemail);
        jText_Iemail.setBounds(340, 430, 410, 40);

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setText("E-mail");
        jPanelInquilinos.add(jLabel86);
        jLabel86.setBounds(220, 430, 160, 40);

        jText_Isaldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInquilinos.add(jText_Isaldo);
        jText_Isaldo.setBounds(340, 470, 410, 40);

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Saldo");
        jPanelInquilinos.add(jLabel87);
        jLabel87.setBounds(220, 470, 160, 40);

        jButton_Iguardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Iguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save-as-icon.png"))); // NOI18N
        jButton_Iguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IguardarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Iguardar);
        jButton_Iguardar.setBounds(420, 520, 140, 50);

        jButton_Ilimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Ilimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton_Ilimpiar.setText("Limpiar");
        jButton_Ilimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IlimpiarActionPerformed(evt);
            }
        });
        jPanelInquilinos.add(jButton_Ilimpiar);
        jButton_Ilimpiar.setBounds(560, 30, 190, 40);

        jLabel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelInquilinos.add(jLabel27);
        jLabel27.setBounds(210, 20, 570, 570);

        jTabbedPane5.addTab("INQUILINOS", jPanelInquilinos);

        jPanelCodeudores.setLayout(null);

        jText_Ccodpredial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Ccodpredial);
        jText_Ccodpredial.setBounds(340, 390, 410, 40);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Cod Contable");
        jPanelCodeudores.add(jLabel55);
        jLabel55.setBounds(500, 390, 100, 40);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Codigo");
        jPanelCodeudores.add(jLabel56);
        jLabel56.setBounds(220, 30, 160, 40);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Cedula");
        jPanelCodeudores.add(jLabel57);
        jLabel57.setBounds(220, 70, 160, 40);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Expedida");
        jPanelCodeudores.add(jLabel61);
        jLabel61.setBounds(220, 110, 160, 40);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Nombres");
        jPanelCodeudores.add(jLabel62);
        jLabel62.setBounds(220, 150, 160, 40);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("Celular");
        jPanelCodeudores.add(jLabel63);
        jLabel63.setBounds(220, 230, 160, 40);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Seg. Apellido");
        jPanelCodeudores.add(jLabel64);
        jLabel64.setBounds(500, 190, 160, 40);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Direccion Casa");
        jPanelCodeudores.add(jLabel65);
        jLabel65.setBounds(220, 270, 160, 40);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText("Cartera");
        jPanelCodeudores.add(jLabel66);
        jLabel66.setBounds(220, 470, 160, 40);

        jText_Ccodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ccodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_CcodigoActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jText_Ccodigo);
        jText_Ccodigo.setBounds(340, 30, 220, 40);

        jText_Ccedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ccedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_CcedulaActionPerformed(evt);
            }
        });
        jText_Ccedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_CcedulaKeyTyped(evt);
            }
        });
        jPanelCodeudores.add(jText_Ccedula);
        jText_Ccedula.setBounds(340, 70, 410, 40);

        jText_Cexpedida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cexpedida);
        jText_Cexpedida.setBounds(340, 110, 410, 40);

        jText_Capellido2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Capellido2);
        jText_Capellido2.setBounds(590, 190, 160, 40);

        jText_Cnombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cnombres);
        jText_Cnombres.setBounds(340, 150, 410, 40);

        jText_Ccodcontable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ccodcontable.setText("123");
        jPanelCodeudores.add(jText_Ccodcontable);
        jText_Ccodcontable.setBounds(600, 390, 150, 40);

        jText_Ccelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Ccelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_CcelularActionPerformed(evt);
            }
        });
        jText_Ccelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_CcelularKeyTyped(evt);
            }
        });
        jPanelCodeudores.add(jText_Ccelular);
        jText_Ccelular.setBounds(340, 230, 160, 40);

        jText_Cdireccioncasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cdireccioncasa);
        jText_Cdireccioncasa.setBounds(340, 270, 410, 40);

        jText_Ccartera.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Ccartera);
        jText_Ccartera.setBounds(340, 470, 410, 40);

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Acción a realizar");
        jPanelCodeudores.add(jLabel67);
        jLabel67.setBounds(0, 20, 210, 25);

        jButton_Celiminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Celiminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_Celiminar.setText("Eliminar");
        jButton_Celiminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CeliminarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Celiminar);
        jButton_Celiminar.setBounds(30, 270, 140, 60);

        jButton_Cinforme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Cinforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514254_Copy v2.png"))); // NOI18N
        jButton_Cinforme.setText("Informe");
        jPanelCodeudores.add(jButton_Cinforme);
        jButton_Cinforme.setBounds(30, 340, 140, 60);

        jButton_Cguardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Cguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save-as-icon.png"))); // NOI18N
        jButton_Cguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CguardarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Cguardar);
        jButton_Cguardar.setBounds(420, 510, 140, 50);

        jButton_Cagregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Cagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_Cagregar.setText("Agregar");
        jButton_Cagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CagregarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Cagregar);
        jButton_Cagregar.setBounds(30, 130, 140, 60);

        jButton_Cmodificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Cmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_Cmodificar.setText("Modificar");
        jButton_Cmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CmodificarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Cmodificar);
        jButton_Cmodificar.setBounds(30, 200, 140, 60);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setText("Direccion Oficina");
        jPanelCodeudores.add(jLabel68);
        jLabel68.setBounds(220, 310, 160, 40);

        jText_Cdireccionoficina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cdireccionoficina);
        jText_Cdireccionoficina.setBounds(340, 310, 410, 40);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setText("Banco");
        jPanelCodeudores.add(jLabel69);
        jLabel69.setBounds(220, 350, 160, 40);

        jText_Cbanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cbanco);
        jText_Cbanco.setBounds(340, 350, 150, 40);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("# Cuenta");
        jPanelCodeudores.add(jLabel70);
        jLabel70.setBounds(500, 350, 160, 40);

        jText_Ccuenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Ccuenta);
        jText_Ccuenta.setBounds(600, 350, 150, 40);

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setText("Cod. Predial");
        jPanelCodeudores.add(jLabel71);
        jLabel71.setBounds(220, 390, 160, 40);

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setText("Primer Apellido");
        jPanelCodeudores.add(jLabel72);
        jLabel72.setBounds(220, 190, 160, 40);

        jText_Capellido1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Capellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_Capellido1ActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jText_Capellido1);
        jText_Capellido1.setBounds(340, 190, 160, 40);

        jText_Cfijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Cfijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_CfijoKeyTyped(evt);
            }
        });
        jPanelCodeudores.add(jText_Cfijo);
        jText_Cfijo.setBounds(590, 230, 160, 40);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setText("Tel. Casa");
        jPanelCodeudores.add(jLabel73);
        jLabel73.setBounds(510, 230, 160, 40);

        jButton_Cbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Cbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton_Cbuscar.setText("Buscar");
        jButton_Cbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CbuscarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Cbuscar);
        jButton_Cbuscar.setBounds(30, 60, 140, 60);

        jButton_Climpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Climpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton_Climpiar.setText("Limpiar");
        jButton_Climpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClimpiarActionPerformed(evt);
            }
        });
        jPanelCodeudores.add(jButton_Climpiar);
        jButton_Climpiar.setBounds(560, 30, 190, 40);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setText("E-mail");
        jPanelCodeudores.add(jLabel74);
        jLabel74.setBounds(220, 430, 160, 40);

        jText_Cemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelCodeudores.add(jText_Cemail);
        jText_Cemail.setBounds(340, 430, 410, 40);

        jLabel75.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelCodeudores.add(jLabel75);
        jLabel75.setBounds(210, 0, 560, 590);

        jTabbedPane5.addTab("CODEUDORES", jPanelCodeudores);

        jPanelPropietarios.setLayout(null);

        jText_Pcodpredial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pcodpredial);
        jText_Pcodpredial.setBounds(340, 390, 410, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Cod Contable");
        jPanelPropietarios.add(jLabel11);
        jLabel11.setBounds(500, 390, 100, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Codigo");
        jPanelPropietarios.add(jLabel13);
        jLabel13.setBounds(220, 30, 160, 40);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Cedula");
        jPanelPropietarios.add(jLabel14);
        jLabel14.setBounds(220, 70, 160, 40);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Expedida");
        jPanelPropietarios.add(jLabel15);
        jLabel15.setBounds(220, 110, 160, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Nombres");
        jPanelPropietarios.add(jLabel16);
        jLabel16.setBounds(220, 150, 160, 40);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Celular");
        jPanelPropietarios.add(jLabel17);
        jLabel17.setBounds(220, 230, 160, 40);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Seg. Apellido");
        jPanelPropietarios.add(jLabel18);
        jLabel18.setBounds(500, 190, 160, 40);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Direccion Casa");
        jPanelPropietarios.add(jLabel19);
        jLabel19.setBounds(220, 270, 160, 40);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Saldo");
        jPanelPropietarios.add(jLabel20);
        jLabel20.setBounds(220, 470, 160, 40);

        jText_Pcodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Pcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_PcodigoActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jText_Pcodigo);
        jText_Pcodigo.setBounds(340, 30, 220, 40);

        jText_Pcedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Pcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_PcedulaActionPerformed(evt);
            }
        });
        jText_Pcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_PcedulaKeyTyped(evt);
            }
        });
        jPanelPropietarios.add(jText_Pcedula);
        jText_Pcedula.setBounds(340, 70, 410, 40);

        jText_Pexpedida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pexpedida);
        jText_Pexpedida.setBounds(340, 110, 410, 40);

        jText_Papellido2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Papellido2);
        jText_Papellido2.setBounds(590, 190, 160, 40);

        jText_Pnombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pnombres);
        jText_Pnombres.setBounds(340, 150, 410, 40);

        jText_Pcodcontable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Pcodcontable.setText("123");
        jPanelPropietarios.add(jText_Pcodcontable);
        jText_Pcodcontable.setBounds(600, 390, 150, 40);

        jText_Pcelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Pcelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_PcelularActionPerformed(evt);
            }
        });
        jText_Pcelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_PcelularKeyTyped(evt);
            }
        });
        jPanelPropietarios.add(jText_Pcelular);
        jText_Pcelular.setBounds(340, 230, 160, 40);

        jText_Pdireccioncasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pdireccioncasa);
        jText_Pdireccioncasa.setBounds(340, 270, 410, 40);

        jText_Psaldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Psaldo);
        jText_Psaldo.setBounds(340, 470, 410, 40);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Acción a realizar");
        jPanelPropietarios.add(jLabel21);
        jLabel21.setBounds(0, 20, 210, 25);

        jButton_Peliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Peliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_Peliminar.setText("Eliminar");
        jButton_Peliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PeliminarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Peliminar);
        jButton_Peliminar.setBounds(30, 270, 140, 60);

        jButton_Pinforme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Pinforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514254_Copy v2.png"))); // NOI18N
        jButton_Pinforme.setText("Informe");
        jPanelPropietarios.add(jButton_Pinforme);
        jButton_Pinforme.setBounds(30, 340, 140, 60);

        jButton_Pguardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Pguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save-as-icon.png"))); // NOI18N
        jButton_Pguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PguardarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Pguardar);
        jButton_Pguardar.setBounds(420, 510, 140, 50);

        jButton_Pagregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Pagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_Pagregar.setText("Agregar");
        jButton_Pagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PagregarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Pagregar);
        jButton_Pagregar.setBounds(30, 130, 140, 60);

        jButton_Pmodificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Pmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_Pmodificar.setText("Modificar");
        jButton_Pmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PmodificarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Pmodificar);
        jButton_Pmodificar.setBounds(30, 200, 140, 60);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Direccion Oficina");
        jPanelPropietarios.add(jLabel23);
        jLabel23.setBounds(220, 310, 160, 40);

        jText_Pdireccionoficina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pdireccionoficina);
        jText_Pdireccionoficina.setBounds(340, 310, 410, 40);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Banco");
        jPanelPropietarios.add(jLabel24);
        jLabel24.setBounds(220, 350, 160, 40);

        jText_Pbanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pbanco);
        jText_Pbanco.setBounds(340, 350, 150, 40);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("# Cuenta");
        jPanelPropietarios.add(jLabel25);
        jLabel25.setBounds(500, 350, 160, 40);

        jText_Pcuenta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanelPropietarios.add(jText_Pcuenta);
        jText_Pcuenta.setBounds(600, 350, 150, 40);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Cod. Predial");
        jPanelPropietarios.add(jLabel26);
        jLabel26.setBounds(220, 390, 160, 40);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Primer Apellido");
        jPanelPropietarios.add(jLabel52);
        jLabel52.setBounds(220, 190, 160, 40);

        jText_Papellido1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Papellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_Papellido1ActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jText_Papellido1);
        jText_Papellido1.setBounds(340, 190, 160, 40);

        jText_Pfijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_Pfijo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_PfijoKeyTyped(evt);
            }
        });
        jPanelPropietarios.add(jText_Pfijo);
        jText_Pfijo.setBounds(590, 230, 160, 40);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Tel. Casa");
        jPanelPropietarios.add(jLabel53);
        jLabel53.setBounds(510, 230, 160, 40);

        jButton_Pbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Pbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton_Pbuscar.setText("Buscar");
        jButton_Pbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PbuscarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Pbuscar);
        jButton_Pbuscar.setBounds(30, 60, 140, 60);

        jButton_Plimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Plimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton_Plimpiar.setText("Limpiar");
        jButton_Plimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PlimpiarActionPerformed(evt);
            }
        });
        jPanelPropietarios.add(jButton_Plimpiar);
        jButton_Plimpiar.setBounds(560, 30, 190, 40);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("E-mail");
        jPanelPropietarios.add(jLabel54);
        jLabel54.setBounds(220, 430, 160, 40);

        jText_Pemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelPropietarios.add(jText_Pemail);
        jText_Pemail.setBounds(340, 430, 410, 40);

        jLabel47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPropietarios.add(jLabel47);
        jLabel47.setBounds(210, 0, 560, 590);

        jTabbedPane5.addTab("PROPIETARIOS", jPanelPropietarios);

        jPanelAdmInm.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Acción a realizar");
        jPanelAdmInm.add(jLabel22);
        jLabel22.setBounds(0, 20, 210, 25);

        jButton_ADM_buscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ADM_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514242_Search.png"))); // NOI18N
        jButton_ADM_buscar.setText("Buscar");
        jButton_ADM_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ADM_buscarActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jButton_ADM_buscar);
        jButton_ADM_buscar.setBounds(30, 60, 140, 60);

        jButton_ADM_eliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ADM_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton_ADM_eliminar.setText("Eliminar");
        jButton_ADM_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ADM_eliminarActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jButton_ADM_eliminar);
        jButton_ADM_eliminar.setBounds(30, 130, 140, 60);

        jButton_ADM_informe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_ADM_informe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514254_Copy v2.png"))); // NOI18N
        jButton_ADM_informe.setText("Informe");
        jPanelAdmInm.add(jButton_ADM_informe);
        jButton_ADM_informe.setBounds(30, 200, 140, 60);

        jText_ADMbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADMbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADMbuscarActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADMbuscar);
        jText_ADMbuscar.setBounds(400, 40, 290, 40);

        jButton_Plimpiar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Plimpiar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396849974_trash yellow.png"))); // NOI18N
        jButton_Plimpiar1.setText("Limpiar");
        jButton_Plimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Plimpiar1ActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jButton_Plimpiar1);
        jButton_Plimpiar1.setBounds(690, 40, 150, 40);

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel109.setText("Cod Inmueble");
        jPanelAdmInm.add(jLabel109);
        jLabel109.setBounds(230, 210, 160, 40);

        jText_ADM_codinm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_codinm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_codinmActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_codinm);
        jText_ADM_codinm.setBounds(370, 210, 450, 40);

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel110.setText("Ced Inquilino");
        jPanelAdmInm.add(jLabel110);
        jLabel110.setBounds(230, 330, 160, 40);

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel111.setText("Dia Causacion");
        jPanelAdmInm.add(jLabel111);
        jLabel111.setBounds(230, 490, 160, 40);

        jText_ADM_diacausacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelAdmInm.add(jText_ADM_diacausacion);
        jText_ADM_diacausacion.setBounds(370, 490, 170, 40);

        jText_ADM_direccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelAdmInm.add(jText_ADM_direccion);
        jText_ADM_direccion.setBounds(370, 410, 450, 40);

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel112.setText("Direccion Inmueble");
        jPanelAdmInm.add(jLabel112);
        jLabel112.setBounds(230, 410, 160, 40);

        jTable_ADM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_ADM.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_ADM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ADMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_ADM);

        jPanelAdmInm.add(jScrollPane1);
        jScrollPane1.setBounds(230, 80, 610, 130);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buscar Por", "Cod inmueble", "Codigo Propietario", "Cedula Propietario", "Codigo Inquilino", "Cedula Inquilino", "Barrio", "Todos" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jComboBox1);
        jComboBox1.setBounds(230, 40, 170, 40);

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel114.setText("Ced Propietario");
        jPanelAdmInm.add(jLabel114);
        jLabel114.setBounds(230, 250, 160, 40);

        jText_ADM_cedpro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_cedpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_cedproActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_cedpro);
        jText_ADM_cedpro.setBounds(370, 250, 170, 40);

        jText_ADM_nominq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelAdmInm.add(jText_ADM_nominq);
        jText_ADM_nominq.setBounds(370, 370, 450, 40);

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel108.setText("Nombre Inquilino");
        jPanelAdmInm.add(jLabel108);
        jLabel108.setBounds(230, 370, 160, 40);

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel116.setText("Cod Propietario");
        jPanelAdmInm.add(jLabel116);
        jLabel116.setBounds(540, 250, 120, 40);

        jText_ADM_codpro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_codpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_codproActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_codpro);
        jText_ADM_codpro.setBounds(650, 250, 170, 40);

        jText_ADM_cedinq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_cedinq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_cedinqActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_cedinq);
        jText_ADM_cedinq.setBounds(370, 330, 170, 40);

        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel117.setText("Cod Inquilino");
        jPanelAdmInm.add(jLabel117);
        jLabel117.setBounds(540, 330, 120, 40);

        jText_ADM_codinq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_codinq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_codinqActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_codinq);
        jText_ADM_codinq.setBounds(650, 330, 170, 40);

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel118.setText("Municipio");
        jPanelAdmInm.add(jLabel118);
        jLabel118.setBounds(540, 450, 160, 40);

        jText_ADM_barrio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_barrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_barrioActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_barrio);
        jText_ADM_barrio.setBounds(370, 450, 170, 40);

        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel119.setText("Municipio");
        jPanelAdmInm.add(jLabel119);
        jLabel119.setBounds(540, 420, 110, 40);

        jText_ADM_municipio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_municipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_municipioActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_municipio);
        jText_ADM_municipio.setBounds(650, 450, 170, 40);

        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel120.setText("Regimen");
        jPanelAdmInm.add(jLabel120);
        jLabel120.setBounds(540, 490, 110, 40);

        jText_ADM_regimen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_regimen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_regimenActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_regimen);
        jText_ADM_regimen.setBounds(650, 490, 170, 40);

        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel113.setText("Nombre Propietario");
        jPanelAdmInm.add(jLabel113);
        jLabel113.setBounds(230, 290, 160, 40);

        jText_ADM_nompro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_ADM_nompro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ADM_nomproActionPerformed(evt);
            }
        });
        jPanelAdmInm.add(jText_ADM_nompro);
        jText_ADM_nompro.setBounds(370, 290, 450, 40);

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel121.setText("Barrio");
        jPanelAdmInm.add(jLabel121);
        jLabel121.setBounds(230, 450, 160, 40);

        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel115.setText("Inm Disponible");
        jPanelAdmInm.add(jLabel115);
        jLabel115.setBounds(230, 530, 160, 40);

        jText_ADM_inmdisponible.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelAdmInm.add(jText_ADM_inmdisponible);
        jText_ADM_inmdisponible.setBounds(370, 530, 450, 40);

        jLabel103.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelAdmInm.add(jLabel103);
        jLabel103.setBounds(200, 0, 670, 630);

        jTabbedPane5.addTab("ADMINISTRAR INMUEBLE", jPanelAdmInm);

        jPanelInmuebles.setLayout(null);

        jButton_INMagregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_INMagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514185_Add.png"))); // NOI18N
        jButton_INMagregar.setText("Agregar");
        jButton_INMagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_INMagregarActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jButton_INMagregar);
        jButton_INMagregar.setBounds(30, 60, 140, 60);

        jButton_INMmodificar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_INMmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton_INMmodificar.setText("Modificar");
        jButton_INMmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_INMmodificarActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jButton_INMmodificar);
        jButton_INMmodificar.setBounds(30, 130, 140, 60);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Acción a realizar");
        jPanelInmuebles.add(jLabel28);
        jLabel28.setBounds(0, 20, 210, 25);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Uso");
        jPanelInmuebles.add(jLabel29);
        jLabel29.setBounds(210, 90, 60, 30);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("DATOS DEL PROPIETARIO");
        jPanelInmuebles.add(jLabel30);
        jLabel30.setBounds(20, 330, 200, 30);

        jText_INMcodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INMcodigoActionPerformed(evt);
            }
        });
        jText_INMcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jText_INMcodigoKeyPressed(evt);
            }
        });
        jPanelInmuebles.add(jText_INMcodigo);
        jText_INMcodigo.setBounds(270, 50, 170, 30);

        jText_INMdireccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INMdireccionActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jText_INMdireccion);
        jText_INMdireccion.setBounds(530, 50, 350, 30);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Direccion");
        jPanelInmuebles.add(jLabel31);
        jLabel31.setBounds(460, 50, 80, 30);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Codigo");
        jPanelInmuebles.add(jLabel32);
        jLabel32.setBounds(210, 50, 60, 30);

        jComboBox_INMuso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_INMuso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "COMERCIAL", "VIVIENDA", "NEGOCIO" }));
        jComboBox_INMuso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_INMusoActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jComboBox_INMuso);
        jComboBox_INMuso.setBounds(270, 90, 110, 30);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Fecha Inicio");
        jPanelInmuebles.add(jLabel33);
        jLabel33.setBounds(610, 90, 90, 30);

        jComboBox_INMmes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_INMmes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE MES", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jPanelInmuebles.add(jComboBox_INMmes);
        jComboBox_INMmes.setBounds(690, 250, 190, 30);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Clase");
        jPanelInmuebles.add(jLabel34);
        jLabel34.setBounds(390, 90, 60, 30);

        dateChooserCombo_INMfechaini.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        jPanelInmuebles.add(dateChooserCombo_INMfechaini);
        dateChooserCombo_INMfechaini.setBounds(710, 90, 170, 30);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Edificio");
        jPanelInmuebles.add(jLabel35);
        jLabel35.setBounds(210, 130, 60, 30);

        jText_INMedificio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INMedificio);
        jText_INMedificio.setBounds(270, 130, 360, 30);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setText("Barrio");
        jPanelInmuebles.add(jLabel36);
        jLabel36.setBounds(210, 170, 60, 30);

        jText_INMbarrio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INMbarrio);
        jText_INMbarrio.setBounds(270, 170, 290, 30);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("Municipio");
        jPanelInmuebles.add(jLabel37);
        jLabel37.setBounds(570, 170, 90, 30);

        jText_INMmunicipio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INMmunicipio);
        jText_INMmunicipio.setBounds(640, 170, 240, 30);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Avaluo");
        jPanelInmuebles.add(jLabel38);
        jLabel38.setBounds(650, 130, 60, 30);

        jText_INMavaluo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMavaluo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMavaluoKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMavaluo);
        jText_INMavaluo.setBounds(710, 130, 170, 30);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Canon");
        jPanelInmuebles.add(jLabel39);
        jLabel39.setBounds(30, 210, 70, 30);

        jText_INMcanon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMcanon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INMcanonActionPerformed(evt);
            }
        });
        jText_INMcanon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMcanonKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMcanon);
        jText_INMcanon.setBounds(100, 210, 170, 30);

        jText_INMadmon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMadmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INMadmonActionPerformed(evt);
            }
        });
        jText_INMadmon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMadmonKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMadmon);
        jText_INMadmon.setBounds(340, 210, 140, 30);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Admon");
        jPanelInmuebles.add(jLabel40);
        jLabel40.setBounds(280, 210, 60, 30);

        Grupo_INMincluida.add(jRadio_INMnoincluida);
        jRadio_INMnoincluida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadio_INMnoincluida.setText("No Incluida");
        jRadio_INMnoincluida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_INMnoincluidaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jRadio_INMnoincluida);
        jRadio_INMnoincluida.setBounds(600, 210, 110, 30);

        Grupo_INMRecoje.add(jRadio_INMbanco);
        jRadio_INMbanco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadio_INMbanco.setText("Banco");
        jRadio_INMbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_INMbancoActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jRadio_INMbanco);
        jRadio_INMbanco.setBounds(350, 290, 80, 30);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("DATOS DEL INMUEBLE");
        jPanelInmuebles.add(jLabel41);
        jLabel41.setBounds(210, 10, 200, 40);

        jCheckBox_INMdisponible.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox_INMdisponible.setText("Inmueble Disponible");
        jCheckBox_INMdisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_INMdisponibleActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jCheckBox_INMdisponible);
        jCheckBox_INMdisponible.setBounds(720, 210, 170, 30);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Recoge en");
        jPanelInmuebles.add(jLabel42);
        jLabel42.setBounds(30, 290, 100, 30);

        jText_INMcomicion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMcomicion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMcomicionKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMcomicion);
        jText_INMcomicion.setBounds(100, 250, 170, 30);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Dia Causacion");
        jPanelInmuebles.add(jLabel43);
        jLabel43.setBounds(280, 250, 100, 30);

        jText_INMdiacausacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMdiacausacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INMdiacausacionActionPerformed(evt);
            }
        });
        jText_INMdiacausacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMdiacausacionKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMdiacausacion);
        jText_INMdiacausacion.setBounds(380, 250, 40, 30);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Dia Inicial");
        jPanelInmuebles.add(jLabel44);
        jLabel44.setBounds(430, 250, 80, 30);

        jText_INMdiaini.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMdiaini.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMdiainiKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMdiaini);
        jText_INMdiaini.setBounds(510, 250, 40, 30);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Dia Final");
        jPanelInmuebles.add(jLabel45);
        jLabel45.setBounds(560, 250, 70, 30);

        jText_INMdiafin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INMdiafin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INMdiafinKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INMdiafin);
        jText_INMdiafin.setBounds(630, 250, 40, 30);

        jComboBox_INMclase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_INMclase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "CASA", "APARTAMENTO", "LOCAL" }));
        jPanelInmuebles.add(jComboBox_INMclase);
        jComboBox_INMclase.setBounds(440, 90, 160, 30);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Codigo / Cedula");
        jPanelInmuebles.add(jLabel46);
        jLabel46.setBounds(30, 360, 130, 30);

        jText_INM_PROcedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INM_PROcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INM_PROcedulaActionPerformed(evt);
            }
        });
        jText_INM_PROcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jText_INM_PROcedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jText_INM_PROcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INM_PROcedulaKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INM_PROcedula);
        jText_INM_PROcedula.setBounds(150, 360, 180, 30);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Nombre");
        jPanelInmuebles.add(jLabel48);
        jLabel48.setBounds(340, 360, 160, 30);

        jText_INM_PROnombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_PROnombre);
        jText_INM_PROnombre.setBounds(410, 360, 470, 30);

        jText_INM_PROemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_PROemail);
        jText_INM_PROemail.setBounds(100, 390, 230, 28);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("E-mail");
        jPanelInmuebles.add(jLabel49);
        jLabel49.setBounds(30, 390, 160, 30);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Celular");
        jPanelInmuebles.add(jLabel50);
        jLabel50.setBounds(340, 390, 160, 30);

        jText_INM_PROcelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_PROcelular);
        jText_INM_PROcelular.setBounds(410, 390, 210, 30);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Tel. Casa");
        jPanelInmuebles.add(jLabel51);
        jLabel51.setBounds(630, 390, 160, 30);

        jText_INM_PROfijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_PROfijo);
        jText_INM_PROfijo.setBounds(710, 390, 170, 30);

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Comision");
        jPanelInmuebles.add(jLabel58);
        jLabel58.setBounds(30, 250, 70, 30);

        Grupo_INMincluida.add(jRadio_INMincluida);
        jRadio_INMincluida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadio_INMincluida.setText("Incluida");
        jRadio_INMincluida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_INMincluidaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jRadio_INMincluida);
        jRadio_INMincluida.setBounds(490, 210, 100, 30);

        Grupo_INMRecoje.add(jRadio_INMconsigna);
        jRadio_INMconsigna.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadio_INMconsigna.setText("Consigna");
        jRadio_INMconsigna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_INMconsignaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jRadio_INMconsigna);
        jRadio_INMconsigna.setBounds(120, 290, 100, 30);

        Grupo_INMRecoje.add(jRadio_INMtransferencia);
        jRadio_INMtransferencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadio_INMtransferencia.setText("Transferencia");
        jRadio_INMtransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_INMtransferenciaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jRadio_INMtransferencia);
        jRadio_INMtransferencia.setBounds(220, 290, 130, 30);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Regimen");
        jPanelInmuebles.add(jLabel59);
        jLabel59.setBounds(470, 290, 60, 30);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("% Iva");
        jPanelInmuebles.add(jLabel60);
        jLabel60.setBounds(690, 290, 70, 30);

        jText_INMiva.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INMiva);
        jText_INMiva.setBounds(760, 290, 40, 30);

        jButton_INMguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_INMguardarActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jButton_INMguardar);
        jButton_INMguardar.setBounds(400, 610, 100, 30);

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("Banco");
        jPanelInmuebles.add(jLabel88);
        jLabel88.setBounds(30, 420, 160, 30);

        jText_INM_PRObanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_PRObanco);
        jText_INM_PRObanco.setBounds(100, 420, 230, 28);

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("# Cuenta");
        jPanelInmuebles.add(jLabel89);
        jLabel89.setBounds(340, 420, 160, 30);

        jText_INM_PROncuenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INM_PROncuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INM_PROncuentaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jText_INM_PROncuenta);
        jText_INM_PROncuenta.setBounds(410, 420, 470, 28);

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("DATOS DEL PROPIETARIO");
        jPanelInmuebles.add(jLabel90);
        jLabel90.setBounds(20, 330, 200, 30);

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("Codigo / Cedula");
        jPanelInmuebles.add(jLabel91);
        jLabel91.setBounds(30, 360, 130, 30);

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setText("Nombres");
        jPanelInmuebles.add(jLabel92);
        jLabel92.setBounds(340, 360, 160, 30);

        jLabel98.setForeground(new java.awt.Color(102, 102, 102));
        jLabel98.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanelInmuebles.add(jLabel98);
        jLabel98.setBounds(10, 330, 880, 130);

        jText_INM_ARncuenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INM_ARncuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INM_ARncuentaActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jText_INM_ARncuenta);
        jText_INM_ARncuenta.setBounds(410, 560, 470, 28);

        jText_INM_ARfijo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_ARfijo);
        jText_INM_ARfijo.setBounds(720, 530, 160, 30);

        jText_INM_ARnombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_ARnombre);
        jText_INM_ARnombre.setBounds(410, 500, 470, 30);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel93.setText("Tel. Casa");
        jPanelInmuebles.add(jLabel93);
        jLabel93.setBounds(650, 530, 160, 30);

        jText_INM_ARcelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_ARcelular);
        jText_INM_ARcelular.setBounds(410, 530, 210, 30);

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel94.setText("# Cuenta");
        jPanelInmuebles.add(jLabel94);
        jLabel94.setBounds(340, 560, 160, 30);

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel95.setText("Celular");
        jPanelInmuebles.add(jLabel95);
        jLabel95.setBounds(340, 530, 160, 30);

        jText_INM_ARbanco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_ARbanco);
        jText_INM_ARbanco.setBounds(100, 560, 230, 28);

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setText("Nombre");
        jPanelInmuebles.add(jLabel96);
        jLabel96.setBounds(340, 500, 160, 30);

        jText_INM_ARemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanelInmuebles.add(jText_INM_ARemail);
        jText_INM_ARemail.setBounds(100, 530, 230, 28);

        jText_INM_ARcedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jText_INM_ARcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_INM_ARcedulaActionPerformed(evt);
            }
        });
        jText_INM_ARcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jText_INM_ARcedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jText_INM_ARcedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jText_INM_ARcedulaKeyTyped(evt);
            }
        });
        jPanelInmuebles.add(jText_INM_ARcedula);
        jText_INM_ARcedula.setBounds(150, 500, 180, 30);

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel97.setText("Banco");
        jPanelInmuebles.add(jLabel97);
        jLabel97.setBounds(30, 560, 160, 30);

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel100.setText("E-mail");
        jPanelInmuebles.add(jLabel100);
        jLabel100.setBounds(30, 530, 160, 30);

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel101.setText("Codigo / Cedula");
        jPanelInmuebles.add(jLabel101);
        jLabel101.setBounds(30, 500, 130, 30);

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel102.setText("DATOS DEL INQUILINO");
        jPanelInmuebles.add(jLabel102);
        jLabel102.setBounds(20, 470, 210, 30);

        jLabel99.setForeground(new java.awt.Color(102, 102, 102));
        jLabel99.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanelInmuebles.add(jLabel99);
        jLabel99.setBounds(10, 470, 880, 130);

        jComboBox_INMregimen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_INMregimen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Comun", "Simplificado" }));
        jComboBox_INMregimen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_INMregimenActionPerformed(evt);
            }
        });
        jPanelInmuebles.add(jComboBox_INMregimen);
        jComboBox_INMregimen.setBounds(550, 290, 120, 30);

        jTabbedPane5.addTab("AGREGAR/MODIFICAR INMUEBLES", jPanelInmuebles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jText_IcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IcodigoActionPerformed
        InquilinoBuscar();
    }//GEN-LAST:event_jText_IcodigoActionPerformed

    private void jText_IcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IcedulaActionPerformed
        InquilinoBuscar();
    }//GEN-LAST:event_jText_IcedulaActionPerformed

    private void jButton_IeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IeliminarActionPerformed
        Inquilinoacciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, verdadero, falso);
        int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Estas SEGURO que deseas eliminar el Inquilino "+jText_Inombres.getText()+"?", "Eliminar Inquilino", JOptionPane.OK_CANCEL_OPTION);
        if(seletedvalue ==JOptionPane.YES_OPTION ){
            String update = "Update inquilinos set estado_inquilino = 2 where cod_inquilino = "+Icod_Inquilino;
            if(conn.Dactualizar(update, "Inquilino Eliminado Con Exito")==1){
                JOptionPane.showMessageDialog(rootPane, "El Inquilino se ha Eliminado con Exito");
                Inquilinoacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                Inquilinovacio();
            }
        }
    }//GEN-LAST:event_jButton_IeliminarActionPerformed

    private void jButton_IbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IbuscarActionPerformed
        Inquilinoenable(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        jText_Icodigo.requestFocus();
    }//GEN-LAST:event_jButton_IbuscarActionPerformed

    private void jButton_IagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IagregarActionPerformed
        jButton_Iagregar.setSelected(true);
        jButton_Iguardar.setText("Guardar");
        Iaccion = 2;
        Inquilinoenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, verdadero);
        Inquilinoacciones(verdadero, verdadero, falso, falso, falso, falso, verdadero, falso, falso, falso);
        Inquilinovacio();
        jText_Icodigo.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_IagregarActionPerformed

    private void jButton_ImodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImodificarActionPerformed
        Inquilinoacciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, verdadero, falso, falso);
        Inquilinoenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
        jButton_Iguardar.setText("Actualizar");
        Iaccion = 3;
    }//GEN-LAST:event_jButton_ImodificarActionPerformed

    private void jText_Iapellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_Iapellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_Iapellido1ActionPerformed

    private void jText_IcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_IcelularActionPerformed

    private void jText_IbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IbancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_IbancoActionPerformed

    private void jButton_IguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IguardarActionPerformed
        conn.establecer_conexion();
        switch(Iaccion){
            case 1:

            break;
            case 2:
            Object[] datos = {jText_Icodigo.getText(), jText_Icedula.getText(), jText_Iexpedida.getText(), jText_Inombres.getText(), jText_Iapellido1.getText(), jText_Iapellido2.getText(), jText_Idireccioncasa.getText(), jText_Idireccionoficina.getText(), jText_Icodpredial.getText(), jText_Icodcontable.getText()};
            java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
            String nombre_completo = jText_Inombres.getText().toUpperCase()+" "+jText_Iapellido1.getText().toUpperCase()+" "+jText_Iapellido2.getText().toUpperCase();
            
            
            String insert= "insert into inquilinos(codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta , email, cod_predial, cod_contable, saldo, estado_inquilino, fecha_reg, nombre_completo) "
            + "VALUES ('"+jText_Icodigo.getText().toUpperCase()+"', "+jText_Icedula.getText().toUpperCase()+", '"+jText_Iexpedida.getText().toUpperCase()+"', '"+jText_Inombres.getText().toUpperCase()+"', '"+jText_Iapellido1.getText().toUpperCase()+"', '"+jText_Iapellido2.getText().toUpperCase()+"', '"+jText_Idireccioncasa.getText().toUpperCase()+"', '"+jText_Idireccionoficina.getText().toUpperCase()+"', "+jText_Icelular.getText().toUpperCase()+", "+jText_Ifijo.getText().toUpperCase()+", '"+jText_Ibanco.getText().toUpperCase()+"' , '"+jText_Icuenta.getText().toUpperCase()+"', '"+jText_Cemail.getText().toUpperCase()+"', '"+jText_Icodpredial.getText().toUpperCase()+"', '"+jText_Icodcontable.getText().toUpperCase()+"', 0, 1, '"+sqlTimestamp+"', '"+nombre_completo+"')";
            System.out.println(insert);

            if(validar(datos)==1){
                if(conn.Dinsertar(insert)==1){
                    Inquilinoenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Inquilinoacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                    Inquilinovacio();
                }
            }
            break;
            case 3:
            Object[] datosupdate = {jText_Icodigo.getText(), jText_Icedula.getText(), jText_Iexpedida.getText(), jText_Inombres.getText(), jText_Iapellido1.getText(), jText_Iapellido2.getText(), jText_Idireccioncasa.getText(), jText_Idireccionoficina.getText(), jText_Icodpredial.getText(), jText_Icodcontable.getText()};
            nombre_completo = jText_Inombres.getText().toUpperCase()+" "+jText_Iapellido1.getText().toUpperCase()+" "+jText_Iapellido2.getText().toUpperCase();
            String update = "UPDATE inquilinos SET codigo = '"+jText_Icodigo.getText().toUpperCase()+"', cedula="+jText_Icedula.getText()+", expedida='"+jText_Iexpedida.getText().toUpperCase()+"', nombres='"+jText_Inombres.getText().toUpperCase()+"', primer_apellido='"+jText_Iapellido1.getText().toUpperCase()+"', segundo_apellido='"+jText_Iapellido2.getText().toUpperCase()+"', direccion_casa='"+jText_Idireccioncasa.getText().toUpperCase()+"', direccion_oficina='"+jText_Idireccionoficina.getText().toUpperCase()+"', celular="+jText_Icelular.getText().toUpperCase()+", fijo="+jText_Ifijo.getText().toUpperCase()+", banco='"+jText_Ibanco.getText().toUpperCase()+"', ncuenta='"+jText_Icuenta.getText().toUpperCase()+"', email='"+jText_Iemail.getText().toUpperCase()+"',cod_predial='"+jText_Icodpredial.getText().toUpperCase()+"',  cod_contable='"+jText_Icodcontable.getText().toUpperCase()+"', saldo="+jText_Isaldo.getText()+", nombre_completo = '"+nombre_completo+"' where cod_inquilino = "+Icod_Inquilino;
            System.out.println(update);
            if(validar(datosupdate)==1){
                if(conn.Dactualizar(update, "Inquilino Actualizado Con Exito")==1){
                    JOptionPane.showMessageDialog(rootPane, "El Inquilino se Actualizo Correctamente");
                    Codeudorenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                    Codeudorvacio();
                }
            }
            break;
        }
    }//GEN-LAST:event_jButton_IguardarActionPerformed

    private void jButton_IlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IlimpiarActionPerformed
        Inquilinovacio();
    }//GEN-LAST:event_jButton_IlimpiarActionPerformed

    private void jText_CcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_CcodigoActionPerformed
        CodeudorBuscar();
    }//GEN-LAST:event_jText_CcodigoActionPerformed

    private void jText_CcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_CcedulaActionPerformed
        CodeudorBuscar();
    }//GEN-LAST:event_jText_CcedulaActionPerformed

    private void jText_CcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_CcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_CcelularActionPerformed

    private void jButton_CeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CeliminarActionPerformed
        Codeudoracciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, verdadero, falso);
        int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Estas SEGURO que deseas eliminar el Codeudor "+jText_Cnombres.getText()+"?", "Eliminar Codeudor", JOptionPane.OK_CANCEL_OPTION);
        if(seletedvalue ==JOptionPane.YES_OPTION ){
            String update = "Update codeudores set estado_codeudor = 2 where cod_codeudor = "+Ccod_codeudor;
            if(conn.Dactualizar(update, "Codeudor Eliminado Con Exito")==1){
                Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                Codeudorvacio();
            }
        }
    }//GEN-LAST:event_jButton_CeliminarActionPerformed

    private void jButton_CguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CguardarActionPerformed
        conn.establecer_conexion();
        String nombre_completo = jText_Cnombres.getText().toUpperCase()+" "+jText_Capellido1.getText().toUpperCase()+" "+jText_Capellido2.getText().toUpperCase();
        switch(Caccion){
            case 1:
            break;
            case 2:
            Object[] datos = {jText_Ccodigo.getText(), jText_Ccedula.getText(), jText_Cexpedida.getText(), jText_Cnombres.getText(), jText_Capellido1.getText(), jText_Capellido2.getText(), jText_Cdireccioncasa.getText(), jText_Cdireccionoficina.getText(), jText_Ccodpredial.getText(), jText_Ccodcontable.getText()};
            java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
            
            String insert= "insert into codeudores(codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta , email, cod_predial, cod_contable, cartera, estado_codeudor, fecha_reg, nombre_completo) "
            + "VALUES ('"+jText_Ccodigo.getText().toUpperCase()+"', "+jText_Ccedula.getText().toUpperCase()+", '"+jText_Cexpedida.getText().toUpperCase()+"', '"+jText_Cnombres.getText().toUpperCase()+"', '"+jText_Capellido1.getText().toUpperCase()+"', '"+jText_Capellido2.getText().toUpperCase()+"', '"+jText_Cdireccioncasa.getText().toUpperCase()+"', '"+jText_Cdireccionoficina.getText().toUpperCase()+"', "+jText_Ccelular.getText().toUpperCase()+", "+jText_Cfijo.getText().toUpperCase()+", '"+jText_Cbanco.getText().toUpperCase()+"' , '"+jText_Ccuenta.getText().toUpperCase()+"', '"+jText_Ccartera.getText().toUpperCase()+"', '"+jText_Ccodpredial.getText().toUpperCase()+"', '"+jText_Ccodcontable.getText().toUpperCase()+"', 0, 1, '"+sqlTimestamp+"', '"+nombre_completo+"')";
            System.out.println(insert);

            if(validar(datos)==1){
                if(conn.Dinsertar(insert)==1){
                    Codeudorenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Codeudorvacio();
                    Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                }
            }
            break;
            case 3:
                
            Object[] datosupdate = {jText_Ccodigo.getText(), jText_Ccedula.getText(), jText_Cexpedida.getText(), jText_Cnombres.getText(), jText_Capellido1.getText(), jText_Capellido2.getText(), jText_Cdireccioncasa.getText(), jText_Cdireccionoficina.getText(), jText_Ccodpredial.getText(), jText_Ccodcontable.getText()};
            String update = "UPDATE codeudores SET codigo = '"+jText_Ccodigo.getText().toUpperCase()+"', cedula="+jText_Ccedula.getText()+", expedida='"+jText_Cexpedida.getText().toUpperCase()+"', nombres='"+jText_Cnombres.getText().toUpperCase()+"', primer_apellido='"+jText_Capellido1.getText().toUpperCase()+"', segundo_apellido='"+jText_Capellido2.getText().toUpperCase()+"', direccion_casa='"+jText_Cdireccioncasa.getText().toUpperCase()+"', direccion_oficina='"+jText_Cdireccionoficina.getText().toUpperCase()+"', celular="+jText_Ccelular.getText().toUpperCase()+", fijo="+jText_Cfijo.getText().toUpperCase()+", banco='"+jText_Cbanco.getText().toUpperCase()+"', ncuenta='"+jText_Ccuenta.getText().toUpperCase()+"', email='"+jText_Cemail.getText().toUpperCase()+"',cod_predial='"+jText_Ccodpredial.getText().toUpperCase()+"',  cod_contable='"+jText_Ccodcontable.getText().toUpperCase()+"', cartera="+jText_Ccartera.getText()+", nombre_completo = '"+nombre_completo+"' where cod_codeudor = "+Ccod_codeudor;
            System.out.println(update);
            if(validar(datosupdate)==1){
                if(conn.Dactualizar(update, "Codeudor Actualizado Con Exito")==1){
                    Codeudorenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                    Codeudorvacio();
                }
            }
            break;
            default:
            JOptionPane.showMessageDialog(rootPane, "Por Favor Seleccione nuevamente una Accion a Realizar");
            break;
        }
    }//GEN-LAST:event_jButton_CguardarActionPerformed

    private void jButton_CagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CagregarActionPerformed
        jButton_Cagregar.setSelected(true);
        jButton_Cguardar.setText("Guardar");
        Caccion = 2;
        Codeudorenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, verdadero);
        Codeudoracciones(verdadero, verdadero, falso, falso, falso, falso, verdadero, falso, falso, falso);
        Codeudorvacio();
        jText_Ccodigo.requestFocus();
    }//GEN-LAST:event_jButton_CagregarActionPerformed

    private void jButton_CmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CmodificarActionPerformed
        Codeudoracciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, verdadero, falso, falso);
        Codeudorenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
        jButton_Cguardar.setText("Actualizar");
        Caccion = 3;
    }//GEN-LAST:event_jButton_CmodificarActionPerformed

    private void jText_Capellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_Capellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_Capellido1ActionPerformed

    private void jButton_CbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CbuscarActionPerformed
        Codeudorenable(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        Codeudoracciones(verdadero, verdadero, falso, falso, falso, verdadero, falso, falso, falso, falso);
        jText_Ccodigo.requestFocus();
        Paccion = 1;
    }//GEN-LAST:event_jButton_CbuscarActionPerformed

    private void jButton_ClimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClimpiarActionPerformed
        Codeudorvacio();
    }//GEN-LAST:event_jButton_ClimpiarActionPerformed

    private void jText_PcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_PcodigoActionPerformed
        if(Paccion==1){
            PropietariosBuscar();
        }
    }//GEN-LAST:event_jText_PcodigoActionPerformed

    private void jText_PcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_PcedulaActionPerformed
        if(Paccion==1){
            PropietariosBuscar();
        }
    }//GEN-LAST:event_jText_PcedulaActionPerformed

    private void jText_PcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_PcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_PcelularActionPerformed

    private void jButton_PeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PeliminarActionPerformed
        Propietarioacciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, falso, verdadero, falso);
        int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Estas SEGURO que deseas eliminar el propietario "+jText_Pnombres.getText()+"?", "Eliminar Propietario", JOptionPane.OK_CANCEL_OPTION);
        if(seletedvalue ==JOptionPane.YES_OPTION ){
            System.out.println("Existos!");
            String update = "Update propietarios set estado_propietario = 2 where cod_propietario = "+Pcod_propietario;
            if(conn.Dactualizar(update, "Propietario Eliminado Con Exito")==1){
                Propietarioacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                Propietariovacio();
            }

        }
    }//GEN-LAST:event_jButton_PeliminarActionPerformed

    private void jButton_PguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PguardarActionPerformed
        conn.establecer_conexion();
        String nombre_completo = jText_Pnombres.getText().toUpperCase()+" "+jText_Papellido1.getText().toUpperCase()+" "+jText_Papellido2.getText().toUpperCase();            
        switch(Paccion){
            case 1:

            break;
            case 2:
            Object[] datos = {jText_Pcodigo.getText(), jText_Pcedula.getText(), jText_Pexpedida.getText(), jText_Pnombres.getText(), jText_Papellido1.getText(), jText_Papellido2.getText(), jText_Pdireccioncasa.getText(), jText_Pdireccionoficina.getText(), jText_Pcodpredial.getText(), jText_Pcodcontable.getText()};
            java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
            String insert= "insert into propietarios(codigo, cedula, expedida, nombres, primer_apellido, segundo_apellido, direccion_casa, direccion_oficina, celular, fijo, banco, ncuenta , email, cod_predial, cod_contable, saldo, estado_propietario, fecha_reg, nombre_completo) "
            + "VALUES ('"+jText_Pcodigo.getText().toUpperCase()+"', "+jText_Pcedula.getText().toUpperCase()+", '"+jText_Pexpedida.getText().toUpperCase()+"', '"+jText_Pnombres.getText().toUpperCase()+"', '"+jText_Papellido1.getText().toUpperCase()+"', '"+jText_Papellido2.getText().toUpperCase()+"', '"+jText_Pdireccioncasa.getText().toUpperCase()+"', '"+jText_Pdireccionoficina.getText().toUpperCase()+"', "+jText_Pcelular.getText().toUpperCase()+", "+jText_Pfijo.getText().toUpperCase()+", '"+jText_Pbanco.getText().toUpperCase()+"' , '"+jText_Pcuenta.getText().toUpperCase()+"', '"+jText_Psaldo.getText().toUpperCase()+"', '"+jText_Pcodpredial.getText().toUpperCase()+"', '"+jText_Pcodcontable.getText().toUpperCase()+"', 0, 1, '"+sqlTimestamp+"', '"+nombre_completo+"')";
            System.out.println(insert);

            if(validar(datos)==1){
                if(conn.Dinsertar(insert)==1){
                    Propietarioenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Propietariovacio();
                    jButton_Pagregar.setSelected(false);
                }
            }
            break;
            case 3:
            String update = "UPDATE propietarios SET codigo = '"+jText_Pcodigo.getText().toUpperCase()+"', cedula="+jText_Pcedula.getText()+", expedida='"+jText_Pexpedida.getText().toUpperCase()+"', nombres='"+jText_Pnombres.getText().toUpperCase()+"', primer_apellido='"+jText_Papellido1.getText().toUpperCase()+"', segundo_apellido='"+jText_Papellido2.getText().toUpperCase()+"', direccion_casa='"+jText_Pdireccioncasa.getText().toUpperCase()+"', direccion_oficina='"+jText_Pdireccionoficina.getText().toUpperCase()+"', celular="+jText_Pcelular.getText().toUpperCase()+", fijo="+jText_Pfijo.getText().toUpperCase()+", banco='"+jText_Pbanco.getText().toUpperCase()+"', ncuenta='"+jText_Pcuenta.getText().toUpperCase()+"', email='"+jText_Pemail.getText().toUpperCase()+"',cod_predial='"+jText_Pcodpredial.getText().toUpperCase()+"',  cod_contable='"+jText_Pcodcontable.getText().toUpperCase()+"', saldo="+jText_Psaldo.getText()+", nombre_completo='"+nombre_completo+"' where cod_propietario = "+Pcod_propietario;
            System.out.println(update);
            if(Pvalidarcampos()==1){
                if(conn.Dactualizar(update, "Propietario Actualizado Con Exito")==1){
                    Propietarioenable(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                    Propietariovacio();
                    Propietarioacciones(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso);
                }
            }
            break;
        }
    }//GEN-LAST:event_jButton_PguardarActionPerformed

    private void jButton_PagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PagregarActionPerformed
        jButton_Pagregar.setSelected(true);
        jButton_Pguardar.setText("Guardar");
        Propietarioenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, falso, verdadero);
        Propietariovacio();
        Propietarioacciones(verdadero, verdadero, falso, falso, falso, falso, verdadero, falso, falso, falso);
        Paccion=2;
    }//GEN-LAST:event_jButton_PagregarActionPerformed

    private void jButton_PmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PmodificarActionPerformed
        Propietarioacciones(verdadero, verdadero, verdadero, verdadero, verdadero, falso, falso, verdadero, falso, falso);
        Propietarioenable(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
        jButton_Pguardar.setText("Actualizar");
        Paccion = 3;
    }//GEN-LAST:event_jButton_PmodificarActionPerformed

    private void jText_Papellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_Papellido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_Papellido1ActionPerformed

    private void jButton_PbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PbuscarActionPerformed
        Propietarioenable(verdadero, verdadero, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        Propietarioacciones(verdadero, verdadero, falso, falso, falso, verdadero, falso, falso, falso, falso);
        Paccion = 1;
        jText_Pcodigo.requestFocus();

    }//GEN-LAST:event_jButton_PbuscarActionPerformed

    private void jButton_PlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PlimpiarActionPerformed
        Propietariovacio();
    }//GEN-LAST:event_jButton_PlimpiarActionPerformed

    private void jButton_ADM_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ADM_buscarActionPerformed
        ADMinmueblesedit(verdadero, falso, falso);
        ADMBTN_iniciales(verdadero, falso, falso, falso);
        ADM_accion = 1;
    }//GEN-LAST:event_jButton_ADM_buscarActionPerformed

    private void jButton_INMagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_INMagregarActionPerformed
        INMinmueblevacio();
        INMpropietariovacio();
        INMinquilinovacio();
        INMinmueble_enabled(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
        INMpropietarioedit(verdadero, falso, falso, falso, falso, falso, falso);
        INMinquilinoedit(falso, falso, falso, falso, falso, falso);
        INMBTN_iniciales(verdadero, falso);
        
        jButton_INMguardar.setText("Crear");
        INMaccion = 1;  
        
        
    }//GEN-LAST:event_jButton_INMagregarActionPerformed

    private void jButton_INMmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_INMmodificarActionPerformed
        INMinmueblevacio();
        INMpropietariovacio();
        INMinquilinovacio();
        INMinmueble_enabled(verdadero, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
        INMBTN_iniciales(falso, verdadero);

        jButton_INMguardar.setText("Actualizar");
        INMaccion = 2;        
    }//GEN-LAST:event_jButton_INMmodificarActionPerformed

    private void jText_INMcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INMcodigoActionPerformed
        conn.establecer_conexion();
        if(INMaccion==2){
            String consulta = "";
            consulta = "select direccion, uso, clase, fecha_inicio, edificio, avaluo, barrio, municipio, canon, admon, admon_estado, inmueble_disponible, comision, dia_causacion, dia_inicial, dia_final, mes, metodo_pago, regimen, iva, pro.cedula, (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) as nombrecompletopropietario, pro.email, pro.celular, pro.fijo, pro.banco, pro.ncuenta, pro.cod_propietario, inm.codinmueble from propietarios as pro INNER JOIN inmuebles as inm on inm.cod_propietario = pro.cod_propietario where inm.codigo = '"+jText_INMcodigo.getText().toUpperCase()+"'";
//            String consulta = "select direccion, uso, clase, fecha_inicio, edificio, avaluo, barrio, municipio, canon, admon, admon_estado, inmueble_disponible, comision, dia_causacion, dia_inicial, dia_final, mes, metodo_pago, regimen, iva, pro.cedula, (pro.nombres || ' ' || pro.primer_apellido || ' ' || pro.segundo_apellido) as nombrecompletopropietario, pro.email, pro.celular, pro.fijo, "
//                    + "pro.banco, pro.ncuenta, pro.cod_propietario, inq.cedula, (inq.nombres || ' ' || inq.primer_apellido || ' ' || inq.segundo_apellido) as nombrecompletoinquilino, inq.email, inq.celular, inq.fijo, inq.banco, inq.ncuenta, inq.cod_inquilino, "
//                    + "inm.codinmueble "
//                    + "from propietarios as pro INNER JOIN inmuebles as inm on inm.cod_propietario = pro.cod_propietario INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino  "
//                + "where inm.codigo = '"+jText_INMcodigo.getText().trim().toUpperCase()+"' and arr.estado = 1";
            System.out.println(consulta);
            ResultSet query3 = conn.consulta(consulta);
            String fechacombo = "";
            Calendar fecha = new GregorianCalendar();
            int contador = 0;
            try{

                while(query3.next()){
                    contador ++ ;
                    int radioadmin = 0;
                    String radioformapago = "";
                    radioadmin = Integer.parseInt(query3.getString(11));
                    radioformapago = (query3.getString(18));
                    INMmetodo_pago = radioformapago;
                    admon_estado = radioadmin;
                    switch (radioadmin){
                        case 1:
                        jRadio_INMincluida.setSelected(true);
                        break;
                        case 2:
                        jRadio_INMnoincluida. setSelected(true);
                        break;
                        default:
                        JOptionPane.showMessageDialog(rootPane, "Guarde el codigo del inmueble y Contacte Soporte Tecnico ERROR ADMINISTRACION INCLUIDA");
                        break;
                    }
                    switch (radioformapago){
                        case "CONSIGNACION":
                            jRadio_INMconsigna.setSelected(true);
                        break;
                        case "TRANSFERENCIA":
                            jRadio_INMtransferencia. setSelected(true);
                        break;
                        case "BANCO":
                            jRadio_INMbanco. setSelected(true);
                        break;
                        default:
                            JOptionPane.showMessageDialog(rootPane, "Guarde el codigo del inmueble y Contacte Soporte Tecnico ERROR FORMA DE PAGO");
                        break;
                    }

                    jText_INMdireccion.setText(query3.getString(1));
                    jComboBox_INMuso.setSelectedItem(query3.getString(2));
                    jComboBox_INMclase.setSelectedItem(query3.getString(3));
                    jComboBox_INMregimen.setSelectedItem(query3.getString(19));
                    //Setear Fecha 
                    fecha.setTime(query3.getDate(4));
                    dateChooserCombo_INMfechaini.setEnabled(true);
                    dateChooserCombo_INMfechaini.setSelectedDate(fecha);
                    jText_INMedificio.setText(query3.getString(5));
                    jText_INMavaluo.setText(query3.getString(6));
                    jText_INMbarrio.setText(query3.getString(7));
                    jText_INMmunicipio.setText(query3.getString(8));
                    jText_INMcanon.setText(query3.getString(9));
                    jText_INMadmon.setText(query3.getString(10));
                    //radiobuton de estado de admon
                    jCheckBox_INMdisponible.setSelected(query3.getBoolean(12));
                    jText_INMcomicion.setText(query3.getString(13));
                    jText_INMdiacausacion.setText(query3.getString(14));
                    jText_INMdiaini.setText(query3.getString(15));
                    jText_INMdiafin.setText(query3.getString(16));
                    jComboBox_INMmes.setSelectedItem(query3.getString(17));
                    //INMmetodo_pago = (query3.getNString(18));
                    //radio button de foma de pago
                    //jText_INMregimen.setText(query3.getString(19));
                    jText_INMiva.setText(query3.getString(20));
                    //propietarios
                    jText_INM_PROcedula.setText(query3.getString(21));
                    jText_INM_PROnombre.setText(query3.getString(22));
                    jText_INM_PROemail.setText(query3.getString(23));
                    jText_INM_PROcelular.setText(query3.getString(24));
                    jText_INM_PROfijo.setText(query3.getString(25));
                    jText_INM_PRObanco.setText(query3.getString(26));
                    jText_INM_PROncuenta.setText(query3.getString(27));
                    INMcod_propietario = (query3.getInt(28));
                    //System.out.print("codigo "+INMcod_propietario);

//                                            //Inquilinos
//                    jText_INM_ARcedula.setText(query3.getString(29));
//                    jText_INM_ARnombre.setText(query3.getString(30));
//                    jText_INM_ARemail.setText(query3.getString(31));
//                    jText_INM_ARcelular.setText(query3.getString(32));
//                    jText_INM_ARfijo.setText(query3.getString(33));
//                    jText_INM_ARbanco.setText(query3.getString(34));
//                    jText_INM_ARncuenta.setText(query3.getString(35));
//                    INMcod_arrendatario = (query3.getInt(36));
//                    //System.out.print("codigo "+INMcod_propietario);                    
                    
                    //Codigo inmueble
                    INMcod_inmueble = query3.getInt(29);
                    
                    //activando los botones editables
                    INMinmueble_enabled(verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero, verdadero);
                    INMpropietarioedit(verdadero, falso, falso, falso, falso, falso, falso);
                    INMinquilinoedit(falso, falso, falso, falso, falso, falso);
                    
                    //vacio la variable anterior por si algo.
                    INMcod_arrendatarioant=0;
                }
                
                //verificar inquilinos arrendados
                String consultaarrienda = "select inq.cedula, (inq.nombres || ' ' || inq.primer_apellido || ' ' || inq.segundo_apellido) as nombrecompletoinquilino, inq.email, inq.celular, inq.fijo, inq.banco, inq.ncuenta, inq.cod_inquilino \n" +
                                            "from arrienda as arr INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino  where arr.cod_inmueble = "+INMcod_inmueble+" and arr.estado = 1";
                System.out.println(consultaarrienda);
                ResultSet rt = conn.consulta(consultaarrienda);
                try {
                    while (rt.next()) {                        
                    jText_INM_ARcedula.setText(rt.getString(1));
                    jText_INM_ARnombre.setText(rt.getString(2));
                    jText_INM_ARemail.setText(rt.getString(3));
                    jText_INM_ARcelular.setText(rt.getString(4));
                    jText_INM_ARfijo.setText(rt.getString(5));
                    jText_INM_ARbanco.setText(rt.getString(6));
                    jText_INM_ARncuenta.setText(rt.getString(7));
                    INMcod_arrendatario = (rt.getInt(8));
                    //System.out.print("codigo "+INMcod_propietario);
                    }
                } catch (Exception e) {
                }
                
                
                if(contador == 0){
                    JOptionPane.showMessageDialog(rootPane, "El Codigo "+jText_INMcodigo.getText()+" No Existe");
                    INMinmueblevacio();
                    INMpropietariovacio();
                    INMinquilinovacio();
                }
            }
            catch(Exception ee){
                
            }
        }
    }//GEN-LAST:event_jText_INMcodigoActionPerformed

    private void jText_INMcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMcodigoKeyPressed
        conn.establecer_conexion();
    }//GEN-LAST:event_jText_INMcodigoKeyPressed

    private void jComboBox_INMusoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_INMusoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_INMusoActionPerformed

    private void jRadio_INMnoincluidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_INMnoincluidaActionPerformed
        admon_estado = 2;
    }//GEN-LAST:event_jRadio_INMnoincluidaActionPerformed

    private void jRadio_INMbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_INMbancoActionPerformed
        INMmetodo_pago="BANCO";
                
    }//GEN-LAST:event_jRadio_INMbancoActionPerformed

    private void jCheckBox_INMdisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_INMdisponibleActionPerformed
        
    }//GEN-LAST:event_jCheckBox_INMdisponibleActionPerformed

    private void jText_INM_PROcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INM_PROcedulaActionPerformed
        conn.establecer_conexion();
        ResultSet query = conn.consulta("select cod_propietario, (nombres || ' ' || primer_apellido || ' ' || segundo_apellido) as nombrecompleto, celular, fijo, email, banco, ncuenta from propietarios where cedula  = "+jText_INM_PROcedula.getText().toUpperCase()+" or cod_propietario = "+jText_INM_PROcedula.getText().toUpperCase() );
        INMpropietarioedit(verdadero, falso, falso, falso, falso, falso, falso);
        INMcod_propietarioant =  INMcod_propietario;
        try{
            while(query.next()){
                INMcod_propietario = (query.getInt(1));
                jText_INM_PROnombre.setText(query.getString(2));
                jText_INM_PROcelular.setText(query.getString(3));
                jText_INM_PROfijo.setText(query.getString(4));
                jText_INM_PROemail.setText(query.getString(5));
                jText_INM_PRObanco.setText(query.getString(6));
                jText_INM_PROncuenta.setText(query.getString(7));

            }

        }
        catch(Exception e){
        }
    }//GEN-LAST:event_jText_INM_PROcedulaActionPerformed

    private void jText_INM_PROcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_PROcedulaKeyPressed

    }//GEN-LAST:event_jText_INM_PROcedulaKeyPressed

    private void jText_INM_PROcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_PROcedulaKeyReleased

    }//GEN-LAST:event_jText_INM_PROcedulaKeyReleased

    private void jText_INM_PROcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_PROcedulaKeyTyped

    }//GEN-LAST:event_jText_INM_PROcedulaKeyTyped

    private void jRadio_INMincluidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_INMincluidaActionPerformed
        admon_estado = 1;
    }//GEN-LAST:event_jRadio_INMincluidaActionPerformed

    private void jRadio_INMconsignaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_INMconsignaActionPerformed
        INMmetodo_pago="CONSIGNACION";
    }//GEN-LAST:event_jRadio_INMconsignaActionPerformed

    private void jRadio_INMtransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_INMtransferenciaActionPerformed
        INMmetodo_pago="TRANSFERENCIA";
    }//GEN-LAST:event_jRadio_INMtransferenciaActionPerformed

    private void jButton_INMguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_INMguardarActionPerformed
        conn.establecer_conexion();
        String prueba, codpropietario="";
        int validar = 0, validarpro=0;
            if(jCheckBox_INMdisponible.isSelected()==true){
                inmdisponible = 1;
            }
            else {
                inmdisponible = 2;
            }
        switch(INMaccion){
            case 1:
                
            //validar codigoa inmueble
                String consulta2="select codigo from inmuebles where codigo = '"+jText_INMcodigo.getText().toUpperCase()+"'";
                int valid_codigo_inmueble = 0;
                ResultSet rs = conn.consulta(consulta2);
                try {
                    while (rs.next()) {                        
                        valid_codigo_inmueble++;
                    }
                    if(valid_codigo_inmueble!=0){

                    }
                } catch (Exception e) {
                }
            //
            if(valid_codigo_inmueble>0){
                conn.JOptionShowMessage("+1", "", "El Codigo "+jText_INMcodigo.getText().toUpperCase()+" Ya existe");
                jText_INMcodigo.setText("");                                        
            }
            else if(INMvalidarcampos()==0){
                    String insert= "INSERT INTO inmuebles(cod_propietario, codigo, direccion, uso, clase, fecha_inicio, edificio, avaluo, barrio, municipio, canon, admon, admon_estado, inmueble_disponible, comision, dia_causacion, dia_inicial, dia_final, mes, metodo_pago, regimen, iva, estado, fecha_reg) "
                    + "VALUES ("+INMcod_propietario+", '"+jText_INMcodigo.getText().toUpperCase()+"', '"+jText_INMdireccion.getText().toUpperCase()+"', '"+jComboBox_INMuso.getSelectedItem()+"', '"+jComboBox_INMclase.getSelectedItem()+"', '"+dateChooserCombo_INMfechaini.getText()+"', '"+jText_INMedificio.getText().toUpperCase()+"', "+jText_INMavaluo.getText()+", '"+jText_INMbarrio.getText().toUpperCase()+"', '"+jText_INMmunicipio.getText().toUpperCase()+"' ,"+jText_INMcanon.getText()+" ,"+jText_INMadmon.getText()+", "+admon_estado+", "+inmdisponible+", "+jText_INMcomicion.getText()+", "+jText_INMdiacausacion.getText()+", "+jText_INMdiaini.getText()+", "+jText_INMdiafin.getText()+", '"+jComboBox_INMmes.getSelectedItem()+"', '"+INMmetodo_pago+"', '"+jComboBox_INMregimen.getSelectedItem()+"', "+jText_INMiva.getText()+", 1, now())";

                    System.out.println(insert);
                    jButton_INMagregar.setSelected(false);
                    try {
                        if(conn.Dinsertar(insert)==1){
                                String query = "select max(codinmueble) as codinmueble from inmuebles";
                                ResultSet consulta = conn.consulta(query);
                                try {
                                    while (consulta.next()) {
                                        INMcod_inmueble = Integer.parseInt(consulta.getString(1));
                                    }
                                } catch (Exception e) {

                                }
    //                            String insert2 = "insert into arrienda(cod_inmueble, cod_inquilino, fecha, estado) "
    //                                    + "values ("+INMcod_inmueble+", "+INMcod_arrendatario+", now(), 1)";                  
    //                            System.out.print(insert2);
    //                            conn.Dinsertar2(insert2);
                                INMinmueblevacio();
                                INMinmueble_enabled(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);
                                INMpropietariovacio();
                                INMpropietarioedit(falso, falso, falso, falso, falso, falso, falso);
                                INMinquilinovacio();
                                INMinquilinoedit(falso, falso, falso, falso, falso, falso);
                        }
                    } catch (Exception e) {
                    }                
                }            


            break;
            case 2:
            if(INMvalidarcampos()==0){
                String update= "update inmuebles set cod_propietario = "+INMcod_propietario+", direccion = '"+jText_INMdireccion.getText().toUpperCase()+"', uso='"+jComboBox_INMuso.getSelectedItem()+"', clase='"+jComboBox_INMclase.getSelectedItem()+"', fecha_inicio='"+dateChooserCombo_INMfechaini.getText()+"', edificio='"+jText_INMedificio.getText().toUpperCase()+"', avaluo="+jText_INMavaluo.getText().toUpperCase()+", barrio='"+jText_INMbarrio.getText()+"', municipio='"+jText_INMmunicipio.getText()+"', canon="+jText_INMcanon.getText()+", admon="+jText_INMadmon.getText()+", admon_estado="+admon_estado+", inmueble_disponible="+inmdisponible+", comision="+jText_INMcomicion.getText()+", dia_causacion="+jText_INMdiacausacion.getText()+", dia_inicial="+jText_INMdiaini.getText()+", dia_final="+jText_INMdiafin.getText()+", mes='"+jComboBox_INMmes.getSelectedItem()+"', metodo_pago='"+INMmetodo_pago+"', regimen='"+jComboBox_INMregimen.getSelectedItem()+"', iva="+jText_INMiva.getText()+"  "
                + "where codigo = '"+jText_INMcodigo.getText().toUpperCase()+"' ";
                System.out.println(update);
                
                String update2 = "update arrienda set estado = 2 where cod_inmueble = "+INMcod_inmueble;
                System.out.println(update2);
                
                String insert = "insert into arrienda(cod_inmueble, cod_inquilino, fecha, estado) "
                        + "values ("+INMcod_inmueble+", "+INMcod_arrendatario+", now(), 1)";                  
                System.out.println(insert);                
                
                jButton_INMmodificar.setSelected(false); //deselecciono boton modificar
                try {
                    if(conn.Dactualizar(update,"Inmueble Actualizado Con Exito")==1){
                            if(INMcod_arrendatarioant!=0){
                                conn.Dactualizar2(update2);
                                conn.Dinsertar2(insert);                            
                            }
                            INMinmueblevacio();
                            INMinmueble_enabled(falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso, falso);

                            INMpropietariovacio();
                            INMpropietarioedit(falso, falso, falso, falso, falso, falso, falso);

                            INMinquilinovacio();
                            INMinquilinoedit(falso, falso, falso, falso, falso, falso);                    

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Contacte con soporte Tecnico: Error Actualizar Inmuebles");
                                }                
            }
            break;
            default:
            JOptionPane.showMessageDialog(rootPane, "Hay un Error por Favor Contactar con el Soporte");
            break;
        }
    }//GEN-LAST:event_jButton_INMguardarActionPerformed

    private void jText_INM_PROncuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INM_PROncuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INM_PROncuentaActionPerformed

    private void jTabbedPane5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane5MouseClicked
        int posicion = 0;

        posicion = jTabbedPane5.getSelectedIndex();
        if (posicion==2) {
            System.out.println("pasopor aqui");
        }
        if(posicion==3){
            //autoComplete.setAutoComplete(jComboBox1, "select (nombres || ' ' || primer_apellido || ' ' || segundo_apellido) AS nombrecompleto, cod_propietario from propietarios");
            //new ajTextField.autocompleterText(jText_ADMbuscar, "nombres, primer_apellido", "propietarios");
            //jComboBox1.setSelectedItem("");
        }

    }//GEN-LAST:event_jTabbedPane5MouseClicked

    private void jText_INM_ARncuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INM_ARncuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INM_ARncuentaActionPerformed

    private void jText_INM_ARcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INM_ARcedulaActionPerformed
//        conn.establecer_conexion();
//        String select = "select cod_inquilino, (nombres || ' ' || primer_apellido || ' ' || segundo_apellido) as nombrecompleto, celular, fijo, email, banco, ncuenta from inquilinos where cedula  = "+jText_INM_ARcedula.getText().toUpperCase()+" or cod_inquilino = "+jText_INM_ARcedula.getText().toUpperCase() ; 
//        System.out.print(select);
//        ResultSet query = conn.consulta(select);
//        
//        INMinquilinoedit(falso, falso, falso, falso, falso, falso);
//        INMcod_arrendatarioant =  INMcod_arrendatario;
//        try{
//            while(query.next()){
//                INMcod_arrendatario = Integer.parseInt(query.getString(1));                
//                jText_INM_ARnombre.setText(query.getString(2));
//                jText_INM_ARcelular.setText(query.getString(3));
//                jText_INM_ARfijo.setText(query.getString(4));
//                jText_INM_ARemail.setText(query.getString(5));
//                jText_INM_ARbanco.setText(query.getString(6));
//                jText_INM_ARncuenta.setText(query.getString(7));
//                
//            }
//        }
//        catch(Exception e){
//        }
    }//GEN-LAST:event_jText_INM_ARcedulaActionPerformed

    private void jText_INM_ARcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_ARcedulaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INM_ARcedulaKeyPressed

    private void jText_INM_ARcedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_ARcedulaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INM_ARcedulaKeyReleased

    private void jText_INM_ARcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INM_ARcedulaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INM_ARcedulaKeyTyped

    private void jText_ADMbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADMbuscarActionPerformed
            if (ADM_jcombobuscar.equals("")) {
                mensaje = "Escoje una opcion para Buscar";
                JOptionShowMessage("+1", "", mensaje);
            }else{
                String campo = ADM_jcombobuscar;
                String buscar = jText_ADMbuscar.getText().toUpperCase();
                llenartablaparametros(campo, buscar);
            }

    }//GEN-LAST:event_jText_ADMbuscarActionPerformed

    private void jButton_Plimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Plimpiar1ActionPerformed
        limpiarTabla(jTable_ADM);
    }//GEN-LAST:event_jButton_Plimpiar1ActionPerformed

    private void jComboBox_INMregimenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_INMregimenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_INMregimenActionPerformed

    private void jText_INMadmonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMadmonKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMadmonKeyTyped

    private void jText_INMcanonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMcanonKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMcanonKeyTyped

    private void jText_INMdiacausacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMdiacausacionKeyTyped
        char car = evt.getKeyChar();
        if(jText_INMdiacausacion.getText().length()>=2) evt.consume();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMdiacausacionKeyTyped

    private void jText_INMdiainiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMdiainiKeyTyped
        char car = evt.getKeyChar();
        if(jText_INMdiaini.getText().length()>=2) evt.consume();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMdiainiKeyTyped

    private void jText_INMdiafinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMdiafinKeyTyped
        char car = evt.getKeyChar();
        if(jText_INMdiafin.getText().length()>=2) evt.consume();
        if((car<'0' || car>'9')) evt.consume();        
    }//GEN-LAST:event_jText_INMdiafinKeyTyped

    private void jText_INMcomicionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMcomicionKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMcomicionKeyTyped

    private void jText_INMavaluoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_INMavaluoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_INMavaluoKeyTyped

    private void jText_ADM_codinmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_codinmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_codinmActionPerformed

    private void jText_INMdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INMdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INMdireccionActionPerformed

    private void jTable_ADMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ADMMouseClicked
        int row=jTable_ADM.getSelectedRow();
        
        jText_ADM_codinm.setText(jTable_ADM.getValueAt(row, 0).toString());
        jText_ADM_cedpro.setText(jTable_ADM.getValueAt(row, 1).toString());
        jText_ADM_nompro.setText(jTable_ADM.getValueAt(row, 2).toString());        
        jText_ADM_direccion.setText(jTable_ADM.getValueAt(row, 3).toString());
        jText_ADM_barrio.setText(jTable_ADM.getValueAt(row, 4).toString());
        
        if(!jText_ADM_codinm.getText().equals("")){
//            String consulta = "select pro.codigo as procodigo, inq.codigo as inqcodigo, inq.cedula as inqcedula,(inq.nombres|| ' ' || inq.primer_apellido || ' ' || inq.segundo_apellido) AS inqnombrecompleto, inm.municipio, inm.dia_causacion, inm.regimen, inm.inmueble_disponible from propietarios as pro inner join  inmuebles as inm on pro.cod_propietario = inm.cod_propietario INNER JOIN arrienda as arr on inm.codinmueble = arr.cod_inmueble INNER JOIN inquilinos as inq on arr.cod_inquilino = inq.cod_inquilino  where inm.codigo= '"+jText_ADM_codinm.getText()+"' and arr.estado=1 and inm.estado = 1;";
            String consulta = "select pro.codigo, inm.municipio, inm.dia_causacion, inm.canon, inmueble_disponible, codinmueble from inmuebles as inm INNER JOIN propietarios as pro on inm.cod_propietario = pro.cod_propietario where inm.codigo= '"+jText_ADM_codinm.getText()+"'" ;
            System.out.println(consulta);
            ResultSet query = conn.consulta(consulta);
            try {
                int vinmdisponible = 0;
                int cod_inmueble = 0;
                String inmdisponible= "";
                while (query.next()) {
                    jText_ADM_codpro.setText(query.getString(1));        
                    jText_ADM_municipio.setText(query.getString("municipio"));
                    jText_ADM_diacausacion.setText(query.getString("dia_causacion"));
                    jText_ADM_regimen.setText(query.getString("canon"));
                    vinmdisponible= (query.getInt("inmueble_disponible"));  
                    cod_inmueble=query.getInt("codinmueble");        
                    switch (vinmdisponible){
                        case 1:
                            jText_ADM_inmdisponible.setText("Disponible");                            
                            break;
                        case 2:
                            jText_ADM_inmdisponible.setText("No Disponible");
                            break;
                        default:
                            jText_ADM_inmdisponible.setText("Error, contacte con soporte tecnico.");
                            break;                            
                    }
                    if(vinmdisponible==2){
                        String consulta2= "select inq.cedula, inq.codigo, inq.nombre_completo from inmuebles as inm INNER JOIN arrienda as arr on  inm.codinmueble = arr.cod_inmueble \n" +
                        "INNER JOIN inquilinos as inq  on arr.cod_inquilino = inq.cod_inquilino  where inm.codinmueble  = "+inmdisponible;
                        ResultSet n= conn.consulta(consulta2);
                        while (n.next()) {                            
                            jText_ADM_cedinq.setText(query.getString(1));
                            jText_ADM_codinq.setText(query.getString(2));
                            jText_ADM_nominq.setText(query.getString(3));	
                        }
                        
                    }
                    
                    
                    
                    //activando el boton eliminar
                    ADMBTN_iniciales(falso, falso, verdadero, falso);
                    ADMinmueblesedit(verdadero, falso, verdadero);
                }                
            } catch (Exception e) {
            }

            
        }
        

        
    }//GEN-LAST:event_jTable_ADMMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jText_ADM_cedproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_cedproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_cedproActionPerformed

    private void jText_ADM_codproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_codproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_codproActionPerformed

    private void jText_ADM_cedinqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_cedinqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_cedinqActionPerformed

    private void jText_ADM_codinqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_codinqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_codinqActionPerformed

    private void jText_ADM_barrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_barrioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_barrioActionPerformed

    private void jText_ADM_municipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_municipioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_municipioActionPerformed

    private void jText_ADM_regimenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_regimenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_regimenActionPerformed

    private void jText_ADM_nomproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ADM_nomproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ADM_nomproActionPerformed

    private void jButton_ADM_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ADM_eliminarActionPerformed
        
        if(!jText_ADM_codinm.getText().equals("")){
            jButton_ADM_eliminar.setSelected(true);
            String select = "select inm.codinmueble, inm.codigo, arr.estado  "
                    + "from inmuebles as inm inner join arrienda as arr on inm.codinmueble = arr.cod_inmueble "
                    + "where inm.codigo= '"+jText_ADM_codinm.getText()+"' and arr.estado = 1 and arr.cod_inquilino = 1";
            System.out.println(select);
            ResultSet query = conn.consulta(select);
            int validar = 0;
            try {
                while(query.next()){
                    validar++;
                    
                }
            } catch (Exception e) {
            }
            if(validar==0){
                JOptionShowMessage("+1", "", "Inmuebles en Arriendo, No pueden ser Eliminados.");
            }else{            
                int seletedvalue = JOptionPane.showConfirmDialog(rootPane, "Estas Seguro de Eliminar el Inmueble?", "Eliminar Inmueble", JOptionPane.OK_CANCEL_OPTION);
                if(seletedvalue ==JOptionPane.YES_OPTION ){                                
                    if(validar==1){
                        String update = "update inmuebles set estado = 2 where codigo = '"+jText_ADM_codinm.getText()+"'";
                        if(conn.Dactualizar(update, "Inmueble Eliminado con Exito")==1){
                            jButton_ADM_eliminar.setSelected(false);
                            ADMinmueble_vacio();
                            limpiarTabla(jTable_ADM);
                        }
                    }
                }
            }
        }
        else{
                JOptionShowMessage("+1", "", "Debes Seleccionar un inmueble para eliminar");
            }
            
    }//GEN-LAST:event_jButton_ADM_eliminarActionPerformed

    private void jText_INMcanonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INMcanonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INMcanonActionPerformed

    private void jText_INMadmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INMadmonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INMadmonActionPerformed

    private void jText_INMdiacausacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_INMdiacausacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_INMdiacausacionActionPerformed

    private void jText_PcelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_PcelularKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_PcelularKeyTyped

    private void jText_PfijoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_PfijoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_PfijoKeyTyped

    private void jText_PcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_PcedulaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_PcedulaKeyTyped

    private void jText_CcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_CcedulaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_CcedulaKeyTyped

    private void jText_CcelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_CcelularKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_CcelularKeyTyped

    private void jText_IfijoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_IfijoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_IfijoKeyTyped

    private void jText_IcelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_IcelularKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_IcelularKeyTyped

    private void jText_IcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_IcedulaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_IcedulaKeyTyped

    private void jText_CfijoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_CfijoKeyTyped
                char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jText_CfijoKeyTyped

    private void jText_IcodpredialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IcodpredialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_IcodpredialActionPerformed

    private void jText_IcodcontableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_IcodcontableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_IcodcontableActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cartera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cartera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cartera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cartera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cartera dialog = new Cartera(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

//Variables Diego
    int admon_estado =0, INMaccion=0, INMcod_propietario=0, inmdisponible=0, INMvalidconsulta=0, Paccion=0, returnquery=0, Pcod_propietario=0, Caccion=0, Ccod_codeudor, Iaccion=0, Icod_Inquilino = 0, INMcod_arrendatario = 0, INMcod_inmueble = 0, INMcod_arrendatarioant = 0, INMcod_propietarioant;
    int contadorconsulta = 0, ADM_accion=0;    
    String ADM_jcombobuscar = "";
    String INMmetodo_pago = "", mensaje = "" ;
    Boolean verdadero=true;
    Boolean falso=false;
    Conexion conn = new Conexion();
    acceso acc = new acceso();
    //Jtable
//    
//    static ResultSet rs;
//    static Statement st;
//    static Conexion con;
//    DefaultTableModel temp;    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo_INMRecoje;
    private javax.swing.ButtonGroup Grupo_INMincluida;
    private datechooser.beans.DateChooserCombo dateChooserCombo_INMfechaini;
    private javax.swing.JButton jButton_ADM_buscar;
    private javax.swing.JButton jButton_ADM_eliminar;
    private javax.swing.JButton jButton_ADM_informe;
    private javax.swing.JButton jButton_Cagregar;
    private javax.swing.JButton jButton_Cbuscar;
    private javax.swing.JButton jButton_Celiminar;
    private javax.swing.JButton jButton_Cguardar;
    private javax.swing.JButton jButton_Cinforme;
    private javax.swing.JButton jButton_Climpiar;
    private javax.swing.JButton jButton_Cmodificar;
    private javax.swing.JButton jButton_INMagregar;
    private javax.swing.JButton jButton_INMguardar;
    private javax.swing.JButton jButton_INMmodificar;
    private javax.swing.JButton jButton_Iagregar;
    private javax.swing.JButton jButton_Ibuscar;
    private javax.swing.JButton jButton_Ieliminar;
    private javax.swing.JButton jButton_Iguardar;
    private javax.swing.JButton jButton_Iinforme;
    private javax.swing.JButton jButton_Ilimpiar;
    private javax.swing.JButton jButton_Imodificar;
    private javax.swing.JButton jButton_Pagregar;
    private javax.swing.JButton jButton_Pbuscar;
    private javax.swing.JButton jButton_Peliminar;
    private javax.swing.JButton jButton_Pguardar;
    private javax.swing.JButton jButton_Pinforme;
    private javax.swing.JButton jButton_Plimpiar;
    private javax.swing.JButton jButton_Plimpiar1;
    private javax.swing.JButton jButton_Pmodificar;
    private javax.swing.JCheckBox jCheckBox_INMdisponible;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox_INMclase;
    private javax.swing.JComboBox jComboBox_INMmes;
    private javax.swing.JComboBox jComboBox_INMregimen;
    private javax.swing.JComboBox jComboBox_INMuso;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanelAdmInm;
    private javax.swing.JPanel jPanelCodeudores;
    private javax.swing.JPanel jPanelInmuebles;
    private javax.swing.JPanel jPanelInquilinos;
    private javax.swing.JPanel jPanelPropietarios;
    private javax.swing.JRadioButton jRadio_INMbanco;
    private javax.swing.JRadioButton jRadio_INMconsigna;
    private javax.swing.JRadioButton jRadio_INMincluida;
    private javax.swing.JRadioButton jRadio_INMnoincluida;
    private javax.swing.JRadioButton jRadio_INMtransferencia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable_ADM;
    private javax.swing.JTextField jText_ADM_barrio;
    private javax.swing.JTextField jText_ADM_cedinq;
    private javax.swing.JTextField jText_ADM_cedpro;
    private javax.swing.JTextField jText_ADM_codinm;
    private javax.swing.JTextField jText_ADM_codinq;
    private javax.swing.JTextField jText_ADM_codpro;
    private javax.swing.JTextField jText_ADM_diacausacion;
    private javax.swing.JTextField jText_ADM_direccion;
    private javax.swing.JTextField jText_ADM_inmdisponible;
    private javax.swing.JTextField jText_ADM_municipio;
    private javax.swing.JTextField jText_ADM_nominq;
    private javax.swing.JTextField jText_ADM_nompro;
    private javax.swing.JTextField jText_ADM_regimen;
    private javax.swing.JTextField jText_ADMbuscar;
    private javax.swing.JTextField jText_Capellido1;
    private javax.swing.JTextField jText_Capellido2;
    private javax.swing.JTextField jText_Cbanco;
    private javax.swing.JTextField jText_Ccartera;
    private javax.swing.JTextField jText_Ccedula;
    private javax.swing.JTextField jText_Ccelular;
    private javax.swing.JTextField jText_Ccodcontable;
    private javax.swing.JTextField jText_Ccodigo;
    private javax.swing.JTextField jText_Ccodpredial;
    private javax.swing.JTextField jText_Ccuenta;
    private javax.swing.JTextField jText_Cdireccioncasa;
    private javax.swing.JTextField jText_Cdireccionoficina;
    private javax.swing.JTextField jText_Cemail;
    private javax.swing.JTextField jText_Cexpedida;
    private javax.swing.JTextField jText_Cfijo;
    private javax.swing.JTextField jText_Cnombres;
    private javax.swing.JTextField jText_INM_ARbanco;
    private javax.swing.JTextField jText_INM_ARcedula;
    private javax.swing.JTextField jText_INM_ARcelular;
    private javax.swing.JTextField jText_INM_ARemail;
    private javax.swing.JTextField jText_INM_ARfijo;
    private javax.swing.JTextField jText_INM_ARncuenta;
    private javax.swing.JTextField jText_INM_ARnombre;
    private javax.swing.JTextField jText_INM_PRObanco;
    private javax.swing.JTextField jText_INM_PROcedula;
    private javax.swing.JTextField jText_INM_PROcelular;
    private javax.swing.JTextField jText_INM_PROemail;
    private javax.swing.JTextField jText_INM_PROfijo;
    private javax.swing.JTextField jText_INM_PROncuenta;
    private javax.swing.JTextField jText_INM_PROnombre;
    private javax.swing.JTextField jText_INMadmon;
    private javax.swing.JTextField jText_INMavaluo;
    private javax.swing.JTextField jText_INMbarrio;
    private javax.swing.JTextField jText_INMcanon;
    private javax.swing.JTextField jText_INMcodigo;
    private javax.swing.JTextField jText_INMcomicion;
    private javax.swing.JTextField jText_INMdiacausacion;
    private javax.swing.JTextField jText_INMdiafin;
    private javax.swing.JTextField jText_INMdiaini;
    private javax.swing.JTextField jText_INMdireccion;
    private javax.swing.JTextField jText_INMedificio;
    private javax.swing.JTextField jText_INMiva;
    private javax.swing.JTextField jText_INMmunicipio;
    private javax.swing.JTextField jText_Iapellido1;
    private javax.swing.JTextField jText_Iapellido2;
    private javax.swing.JTextField jText_Ibanco;
    private javax.swing.JTextField jText_Icedula;
    private javax.swing.JTextField jText_Icelular;
    private javax.swing.JTextField jText_Icodcontable;
    private javax.swing.JTextField jText_Icodigo;
    private javax.swing.JTextField jText_Icodpredial;
    private javax.swing.JTextField jText_Icuenta;
    private javax.swing.JTextField jText_Idireccioncasa;
    private javax.swing.JTextField jText_Idireccionoficina;
    private javax.swing.JTextField jText_Iemail;
    private javax.swing.JTextField jText_Iexpedida;
    private javax.swing.JTextField jText_Ifijo;
    private javax.swing.JTextField jText_Inombres;
    private javax.swing.JTextField jText_Isaldo;
    private javax.swing.JTextField jText_Papellido1;
    private javax.swing.JTextField jText_Papellido2;
    private javax.swing.JTextField jText_Pbanco;
    private javax.swing.JTextField jText_Pcedula;
    private javax.swing.JTextField jText_Pcelular;
    private javax.swing.JTextField jText_Pcodcontable;
    private javax.swing.JTextField jText_Pcodigo;
    private javax.swing.JTextField jText_Pcodpredial;
    private javax.swing.JTextField jText_Pcuenta;
    private javax.swing.JTextField jText_Pdireccioncasa;
    private javax.swing.JTextField jText_Pdireccionoficina;
    private javax.swing.JTextField jText_Pemail;
    private javax.swing.JTextField jText_Pexpedida;
    private javax.swing.JTextField jText_Pfijo;
    private javax.swing.JTextField jText_Pnombres;
    private javax.swing.JTextField jText_Psaldo;
    // End of variables declaration//GEN-END:variables
}
