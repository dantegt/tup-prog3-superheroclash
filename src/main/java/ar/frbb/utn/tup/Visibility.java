package ar.frbb.utn.tup;

import java.awt.*;

public class Visibility {
    UI ui;
    public Visibility (UI userInterface) {
        ui = userInterface;
    }

    public void pantallaInicio() {
        ocultarTodo();
        ui.pantallaInicio.setVisible(true);
    }

    public void seleccionarPersonajes() {
        ocultarTodo();
        ui.panelPrincipal.setVisible(true);
    }

    public void iniciarPelea() {
        ocultarTodo();
        ui.crearPantallaGanador();
//        ui.panelGanador.setVisible(true);
    }

    public void jugarDeNuevo() {
        pantallaInicio();
    }

    public void ocultarTodo() {
        ui.pantallaInicio.setVisible(false);
        ui.panelPrincipal.setVisible(false);
//        ui.panelGanador.setVisible(false);
    }
}
