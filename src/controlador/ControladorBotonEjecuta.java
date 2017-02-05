package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import vista.*;

public class ControladorBotonEjecuta implements ActionListener{

    public ControladorBotonEjecuta(MarcoAplicacion2 elmarco){
        this.elmarco=elmarco;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String seleccionSeccion=(String) elmarco.secciones.getSelectedItem();
        String seleccionPais=(String) elmarco.paises.getSelectedItem();

        elmarco.resultado.append(obj.filtraBBDD(seleccionSeccion, seleccionPais));
        elmarco.resultado.append("\n");
    }

    private MarcoAplicacion2 elmarco;
    EjecutaConsultas obj=new EjecutaConsultas();
}
