package ar.frbb.utn.tup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    ChoiceHandler choiceHandler = new ChoiceHandler();
    UI ui = new UI();
    Visibility vm = new Visibility(ui);

    public static final String APP_NAME = "Super Hero Clash";

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        ui.crearUI(choiceHandler);
        vm.pantallaInicio();
    }

    public class ChoiceHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String opcion = e.getActionCommand();

            switch (opcion) {
                case "pelear": vm.iniciarPelea(); break;
            }
        }
    }

}