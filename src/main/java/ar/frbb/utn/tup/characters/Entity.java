package ar.frbb.utn.tup.characters;

import ar.frbb.utn.tup.Stats;

public class Entity extends Character {

    private String nombrePoderEspecial = "Curación Divina";

    public Entity(String name, String raza, String genero, String image, String alignment, Stats stats) {
        super(name, raza, genero, image, alignment, stats);
    }

    @Override
    int atacar(Character character) {
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