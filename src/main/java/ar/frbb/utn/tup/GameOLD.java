package ar.frbb.utn.tup;

import java.util.Scanner;

public class GameOLD {
    public static void start() {
        Scanner input = new Scanner(System.in);
        Log.it("Ejecutando: Medieval Fight");
        Log.it("Creando equipos");
        Log.it("👺 Alto ahí maleante! Quién osa desafiar a los campeones?");
        Log.ask("👺 Diga su nombre! >> ");
        String user = input.nextLine();
        Team userTeam = new Team(user);
        Log.it("👺 Y realmente crees estar a la altura? JAJAJA.. Ya lo veremos!");
        Log.it("👺 Presenta a tu \"equipo\", desafiante!");

        userTeam.getCharacter(1);

        //        JFrame window = new JFrame();
        //        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //        window.setResizable(false);
        //        window.setTitle("SuperHero Clash!");
        //
        //        GamePanel gamePanel = new GamePanel();
        //        window.add(gamePanel);
        //
        //        window.pack();
        //
        //        window.setLocationRelativeTo(null);
        //        window.setVisible(true);



        // Juego consola
        //        Game.start();

        /*Log.it("Presentando equipo A: Usuario");
        Log.it("Presentando equipo B: Enemigo");
        Log.it("Sorteo de Peleas: Orden");
        Log.it("Sorteo de Primer Golpe");
        Log.it("Golpe A => X: Daño / Esquiva - CHECKEO SALUD");
        Log.it("Golpe X => A: Daño / Esquiva - CHECKEO SALUD");
        Log.it("Golpe A => X: Daño / Esquiva - CHECKEO SALUD");
        Log.it("X muere: Entra Y ... Pelea A contra Y");
        Log.it("Paso");
        Log.it("Paso");
        Log.it("Paso");
        Log.it("Paso");
        Log.it("Paso");
        Log.it("Paso");*/

    }
}
