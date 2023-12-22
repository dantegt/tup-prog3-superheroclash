package ar.frbb.utn.tup.characters;

import ar.frbb.utn.tup.Log;
import ar.frbb.utn.tup.Stats;

import java.util.Random;

public class Entity extends Character {
    Stats stats;
    Random random;
    public Entity(String name, String raza, String genero, String image, String alignment, Stats stats) {
        super(name, raza, genero, image, alignment, stats);
        this.stats = stats;
        random = new Random();
    }

    @Override
    public int atacar(Character character) {
        float variacion = random.nextFloat();
        int poder = (stats.combat() + (int) (stats.power() * 1.4)) / 5;
        poder = poder + (int) (poder * variacion);

        String message = this.name() + " atacó a " + character.name() + " ♦ " + poder + " de daño!";

        boolean alignment = critico(alignment(), character.alignment());
        boolean critico = random.nextBoolean();
        if(alignment && critico) {
            poder = (int) (poder * 1.2);
            message = "CRITICO! " + message;
        }

        boolean usaPoderEspecial = random.nextBoolean() && random.nextBoolean() && random.nextBoolean();
        if (usaPoderEspecial) poderEspecial(character);

        Log.it(message);
        System.out.println(message);

        character.defender(this, poder);
        return poder;
    }

    @Override
    public void defender(Character character, int damage) {
        int defensa = (int)((stats.strength() + stats.durability()) / 6);
        if(damage > defensa) damage = damage - defensa;
        int vida = hpActual() - damage;
        if(vida < 0 ) vida = 0;
        setHpActual(vida);
    }

    @Override
    public void poderEspecial(Character character) {
        String message = "▒ PODER ESPECIAL! ▒ " + this.name() + " usa CURACION DIVINA y se cura un 20% de su HP!!";
        Log.it(message);
        int curacion = (int)(this.hpTotal() * 0.2);
        this.setHpActual(this.hpActual() + curacion);
    }
}
