package pablo384.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AplicacionConsulta {

    public static void main(String[] args) {
	// write your code here
        JFrame mimarco=new Marco_Aplicacion();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mimarco.setVisible(true);

    }

}

class Marco_Aplicacion extends JFrame{

    public Marco_Aplicacion(){

        setTitle ("Consulta BBDD");

        setBounds(500,300,400,400);

        setLayout(new BorderLayout());

        JPanel menus=new JPanel();

        menus.setLayout(new FlowLayout());

        secciones=new JComboBox();

        secciones.setEditable(false);

        secciones.addItem("Todos");

        paises=new JComboBox();

        paises.setEditable(false);

        paises.addItem("Todos");

        resultado= new JTextArea(4,50);

        resultado.setEditable(false);

        add(resultado);

        menus.add(secciones);

        menus.add(paises);

        add(menus, BorderLayout.NORTH);

        add(resultado, BorderLayout.CENTER);

        JButton botonConsulta=new JButton("Consulta");

        botonConsulta.addActionListener(e -> ejecutaConsulta());

        add(botonConsulta, BorderLayout.SOUTH);

        //---------------CONEXION CON BBDD----------------------
        try{
            //1. CREAR CONEXION CON BSD
            miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cursosql","root","");
            //2. CREAR OBJETO STATEMENT
            Statement sentencia=miConexion.createStatement();

            //3. EJECUTAR SQL
            String consulta="SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS";
            ResultSet rs=sentencia.executeQuery(consulta);

            while (rs.next()){
                secciones.addItem(rs.getString(1));
            }
            rs.close();

            //--carga jcomboBox PAISORIGEN
            consulta="SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS";
            rs=sentencia.executeQuery(consulta);

            while (rs.next()){
                paises.addItem(rs.getString(1));
            }
            rs.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    private void ejecutaConsulta(){

        ResultSet rs = null;

        try{
            resultado.setText("");

            String seccion= (String) secciones.getSelectedItem();
            String pais= (String) paises.getSelectedItem();

            if (!seccion.equals("Todos") && pais.equals("Todos")){
                enviaConsultaSeccion=miConexion.prepareStatement(consultaSeccion);

                enviaConsultaSeccion.setString(1,seccion);

                rs = enviaConsultaSeccion.executeQuery();
            }else if (seccion.equals("Todos") && !pais.equals("Todos")){
                enviaConsultaPais=miConexion.prepareStatement(consultaPais);

                enviaConsultaPais.setString(1,pais);

                rs = enviaConsultaPais.executeQuery();
            }else if (!seccion.equals("Todos") && !pais.equals("Todos")){

                enviaConsultaAll=miConexion.prepareStatement(consultaAll);
                enviaConsultaAll.setString(1,seccion);
                enviaConsultaAll.setString(2,pais);
                rs = enviaConsultaAll.executeQuery();

            }


            while (rs.next()){
                resultado.append(rs.getString(1));
                resultado.append(", ");

                resultado.append(rs.getString(2));
                resultado.append(", ");

                resultado.append(rs.getString(3));
                resultado.append(", ");

                resultado.append(rs.getString(4));
                resultado.append("\n");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private Connection miConexion;

    private PreparedStatement enviaConsultaSeccion;
    private PreparedStatement enviaConsultaPais;
    private PreparedStatement enviaConsultaAll;

    private final String consultaSeccion="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=?";
    private final String consultaPais="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN=?";
    private final String consultaAll="SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=? AND PAÍSDEORIGEN=?";

    private JComboBox secciones;

    private JComboBox paises;

    private JTextArea resultado;



}