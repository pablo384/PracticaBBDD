package controlador;


import java.awt.event.*;
import modelo.CargaMenus;
import vista.MarcoAplicacion2;

public class ControladorCargaMenus extends WindowAdapter{

    public ControladorCargaMenus(MarcoAplicacion2 elmarco){
        this.elmarco=elmarco;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        obj.ejecutaConsultas();
        try {

            while (obj.rs.next()){
                elmarco.secciones.addItem(obj.rs.getString(1));
            }
            while (obj.rs2.next()){
                elmarco.paises.addItem(obj.rs2.getString(1));
            }
            obj.rs.close();
            obj.rs2.close();

        }catch (Exception e2){
            e2.printStackTrace();
        }
    }

    CargaMenus obj=new CargaMenus();
    private MarcoAplicacion2 elmarco;
}
