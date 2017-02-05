package modelo;

import java.sql.*;

public class CargaMenus {

    public CargaMenus(){
        miConexion=new Conexion();
    }

    public String ejecutaConsultas(){
        Productos miProducto=null;

        accesoBBDD= miConexion.dameConexion();

        try{

            Statement secciones=accesoBBDD.createStatement();
            Statement paises=accesoBBDD.createStatement();

            rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
            rs2=paises.executeQuery("SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS");

            miProducto=new Productos();
//            miProducto.setSeccion(rs.getString(1));
//            miProducto.setpOrigen(rs2.getString(1));

//            rs.close();
//            rs2.close();
//            accesoBBDD.close();


        }catch (Exception e){

            e.printStackTrace();

        }


        return miProducto.getSeccion();
    }

//    public  ResultSet ejecutaConsultas(){
//        Connection accesoBBDD= miConexion.dameConexion();
//        try{
//            Statement secciones=accesoBBDD.createStatement();
//            return rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
//        }catch (Exception e){
//
//        }
//        return null;
//    }

    public Conexion miConexion;
    public Connection accesoBBDD;
    public ResultSet rs;
    public ResultSet rs2;
}
