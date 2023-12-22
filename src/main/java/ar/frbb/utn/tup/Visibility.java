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
        ui.finDelCombate.setVisible(false);
    }

    public void iniciarPelea() {
        ui.botonPelear.setVisible(false);
        ui.finDelCombate.setVisible(false);
        ui.proximoTurno.setVisible(true);
        ganador = null;
        nuevoTurno();
    }

    public void nuevoTurno() {
        ganador = ui.nuevoTurno();
        ui.reBuildHeros();
        ui.botonPelear.setVisible(false);
        ui.finDelCombate.setVisible(false);

        if(ganador != null) {
//            mostrarGanador(ganador);
            finDelCombate();
        }
    }

    public void finDelCombate() {
        ui.reBuildHeros();
        ui.botonPelear.setVisible(false);
        ui.proximoTurno.setVisible(false);
        ui.finDelCombate.setVisible(true);
    }

    public void mostrarGanador() {
        ui.panelPrincipal.setVisible(false);
        ui.crearPantallaGanador(ganador);
        ui.exportarLog.setVisible(true);
        ui.logExportado.setVisible(false);
        ui.panelGanador.setVisible(true);
    }

    public void logDeBatallaExportado() {
        ui.exportarLog();
        ui.exportarLog.setVisible(false);
        ui.logExportado.setVisible(true);
    }

    public void jugarDeNuevo() {
        ui.panelGanador.setVisible(false);
        pantallaInicio();
        ui.resetGame();
    }
}
