package ar.frbb.utn.tup;

import ar.frbb.utn.tup.characters.Character;

import java.awt.*;

public class Visibility {
    UI ui;
    Character ganador;

    public Visibility (UI userInterface) {
        ui = userInterface;
    }

    public void pantallaInicio() {
        ui.pantallaInicio.setVisible(true);
        ui.loading.setVisible(false);
    }

    public void seleccionarPersonajes() {
        ui.loading.setVisible(true);
        ui.pantallaInicio.setVisible(false);
        ui.seleccionarPersonajes();
        ui.crearPantallaPrincipal(ui.choiceHandler);
        ui.panelPrincipal.setVisible(true);
        ui.proximoTurno.setVisible(false);
    }

    public void iniciarPelea() {
        ui.botonPelear.setVisible(false);
        ui.proximoTurno.setVisible(true);
        ganador = null;
        nuevoTurno();
    }

    public void nuevoTurno() {
        ganador = ui.nuevoTurno();
        ui.reBuildHeros();
        ui.botonPelear.setVisible(false);

        if(ganador != null) {
            mostrarGanador(ganador);
        }
    }

    public void mostrarGanador(Character ganador) {
//        ocultarTodo();
        ui.panelPrincipal.setVisible(false);
        ui.crearPantallaGanador(ganador);
        ui.panelGanador.setVisible(true);
    }

    public void jugarDeNuevo() {
        ui.panelGanador.setVisible(false);
        pantallaInicio();
        ui.resetGame();
    }

    public void ocultarTodo() {
        ui.pantallaInicio.setVisible(false);
//        ui.panelPrincipal.setVisible(false);
//        ui.panelGanador.setVisible(false);
    }
}
