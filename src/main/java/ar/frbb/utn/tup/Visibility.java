package ar.frbb.utn.tup;

public class Visibility {
    UI ui;
    public Visibility (UI userInterface) {
        ui = userInterface;
    }

    public void pantallaInicio() {
        ocultarTodo();
        ui.pantallaInicio.setVisible(true);
    }

    public void iniciarPelea() {
        ocultarTodo();
        ui.panelPrincipal.setVisible(true);
    }

    public void ocultarTodo() {
        ui.pantallaInicio.setVisible(false);
        ui.panelPrincipal.setVisible(false);
    }
}
