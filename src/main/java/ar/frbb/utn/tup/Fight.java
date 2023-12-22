package ar.frbb.utn.tup;

import ar.frbb.utn.tup.characters.Character;

import java.util.Random;

public class Fight {
    private static int turno;
    private boolean ataca1;
    private Random random;

    public Fight(){
        turno = 1;
        Log.reset();
        random = new Random();
    }

    public Character pelear(Character hero1, Character hero2) throws InterruptedException {

        ataca1 = random.nextBoolean();
        System.out.println("---- TURNO " + turno + " ---");
        if(ataca1) {
            int golpe = hero1.atacar(hero2);
            Log.it(hero1.name() + " atacó a " + hero2.name() + "! Le hizo " + golpe + " de daño!");
            System.out.println(hero1.name() + " atacó a " + hero2.name() + "! Le hizo " + golpe + " de daño!");
        } else {
            int golpe = hero2.atacar(hero1);
            Log.it(hero2.name() + " atacó a " + hero1.name() + "! Le hizo " + golpe + " de daño!");
            System.out.println(hero2.name() + " atacó a " + hero1.name() + "! Le hizo " + golpe + " de daño!");
        }
        System.out.println("Hero1: " + hero1.hpActual());
        System.out.println("Hero2: " + hero2.hpActual());
        System.out.println("-------");
        turno = turno + 1;
        if (hero1.hpActual() <= 0) return hero2;
        if (hero2.hpActual() <= 0) return hero1;
        return null;
    }

    public static int turno() {
        return turno;
    }

    public static void setTurno(int turno) {
        Fight.turno = turno;
    }

    public boolean ataca1() {
        return ataca1;
    }

    public Fight setAtaca1(boolean ataca1) {
        this.ataca1 = ataca1;
        return this;
    }
}
