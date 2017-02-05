package modelo;

import java.sql.*;

public class CargaSecciones {

    public CargaSecciones(){
        miConexion=new Conexion();
    }

    /*public String ejecutaConsultas(){
        Productos miProducto=null;

        Connection acceoBBDD= miConexion.dameConexion();

        try{

            Statement secciones=acceoBBDD.createStatement();
            rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
//            while (rs.next()){
//                rs.previous();
                miProducto=new Productos();
                miProducto.setSeccion(rs.getString(1));
//                return miProducto.getSeccion();
//            }
            rs.close();
            secciones.close();

        }catch (Exception e){

        }


        return miProducto.getSeccion();
    }*/

    public  ResultSet ejecutaConsultas(){
        Connection accesoBBDD= miConexion.dameConexion();
        try{
            Statement secciones=accesoBBDD.createStatement();
            return rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
        }catch (Exception e){

        }
        return null;
    }

    Conexion miConexion;
    public ResultSet rs;
}
