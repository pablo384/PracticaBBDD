package modelo;

import java.sql.*;

public class EjecutaConsultas {

    public EjecutaConsultas(){
        miConexion=new Conexion();
    }

    public ResultSet filtraBBDD(String seccion, String pais){

        rs=null;
        Connection conecta=miConexion.dameConexion();
        try {
            if (!seccion.equals("Todos") && pais.equals("Todos")) {
                enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
                enviaConsultaSeccion.setString(1,seccion);
                rs=enviaConsultaSeccion.executeQuery();

            } else if (seccion.equals("Todos") && !pais.equals("Todos")) {
                enviaConsultaPais = conecta.prepareStatement(consultaPais);
                enviaConsultaPais.setString(1, pais);
                rs=enviaConsultaPais.executeQuery();

            } else if (seccion.equals("Todos") && pais.equals("Todos")){
                enviaConsultaTodo = conecta.prepareStatement(consultaTodo);
                rs=enviaConsultaTodo.executeQuery();
            }else {

                enviaConsultaAmbos = conecta.prepareStatement(consultaAmbos);
                enviaConsultaAmbos.setString(1, seccion);
                enviaConsultaAmbos.setString(2, pais);
                rs=enviaConsultaAmbos.executeQuery();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return rs;
    }

    private Conexion miConexion;
    private ResultSet rs;


    private PreparedStatement enviaConsultaSeccion;
    private PreparedStatement enviaConsultaPais;
    private PreparedStatement enviaConsultaAmbos;
    private PreparedStatement enviaConsultaTodo;

    private final String consultaSeccion="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=?";
    private final String consultaPais="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN=?";
    private final String consultaAmbos="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=? AND PAÍSDEORIGEN=?";
    private final String consultaTodo="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS";

}
