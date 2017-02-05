package modelo;

import java.sql.*;

public class EjecutaConsultas {

    public String filtraBBDD(String seccion, String pais){
        pruebas="";

        if (!seccion.equals("Todos") && pais.equals("Todos")){
            pruebas="Has escogido Seccion";
        }else if (seccion.equals("Todos") && !pais.equals("Todos")){
            pruebas="Has escogido Pais";
        }else {
            pruebas="Has escogido Ambos";
        }

        return pruebas;
    }

    private String pruebas;
}
