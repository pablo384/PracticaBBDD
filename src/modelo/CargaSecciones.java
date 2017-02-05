package modelo;

import java.sql.*;
import controlador.*;

public class CargaSecciones {

    public CargaSecciones(){
        miConexion=new Conexion();
    }

    public String ejecutaConsultas(){
        Productos miProducto=null;

        Connection acceoBBDD= miConexion.dameConexion();

        try{

            Statement secciones=acceoBBDD.createStatement();
            rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
            while (rs.next()){
                miProducto=new Productos();
                miProducto.setSeccion(rs.getString(1));
                return miProducto.getSeccion();
            }
            rs.close();
            secciones.close();

        }catch (Exception e){

        }


        return miProducto.getSeccion();
    }

    Conexion miConexion;
    public ResultSet rs;
}
