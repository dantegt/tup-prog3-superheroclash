package ar.frbb.utn.tup.characters;

import ar.frbb.utn.tup.Stats;

public class Hero extends Character {

    public Hero(String name, String raza, String genero, String image, String alignment, Stats stats) {
        super(name, raza, genero, image, alignment, stats);
    }

    @Override
    int atacar(Character character) {
//        calcular critico
//        calcular ataque = (combat + power) * critico
//        character.hpActual = hpActual - ataque
//        Log.it(character.name + " recibió " + ataque + " puntos de daño!")
//        void
        return 0;
    }

    @Override
    int defender(Character character, int damage) {

        return 0;
    }

    @Override
    void activarPoderEspecial() {

    }
}
