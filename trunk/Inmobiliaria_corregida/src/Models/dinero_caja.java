/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import javax.swing.JOptionPane;
import inmobiliaria_fase01.Conexion;
import java.sql.ResultSet;


public class dinero_caja {
    private String accion;
    private String tipo;
    private int codigo_recibo;
    private int cantidad;


    
    public void registrar_dinero(String accion, String tipo, int codigo_recibo, int cantidad){
        Conexion conn = new Conexion();
        conn.establecer_conexion();
        String insert = "insert into dinero_caja (accion, tipo, codigo_recibo, cantidad, estado, fecha_reg) values ('"+accion+"', '"+tipo+"', "+codigo_recibo+", "+cantidad+", 1, now())";
        System.out.println(insert);
        conn.Dinsertar2(insert);
    }
    public int dinero_en_caja(){
        Conexion conn = new Conexion();
        conn.establecer_conexion();
        int dinero = 0;
        String select = "SELECT (select sum(cantidad) from dinero_caja where tipo = 'RECIBO_CAJA') - (select sum(cantidad) from dinero_caja where tipo = 'EGRESO') AS dinero_en_caja FROM dinero_caja GROUP BY tipo LIMIT 1;";
        ResultSet rs = conn.consulta(select);
        try {
            while (rs.next()) {
                dinero = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        
        return dinero;
    }
    
}
