package ar.frbb.utn.tup.characters;

import ar.frbb.utn.tup.Stats;

public class Entity extends Character {

    private String nombrePoderEspecial = "Curación Divina";
    Stats stats;
    public Entity(String name, String raza, String genero, String image, String alignment, Stats stats) {
        super(name, raza, genero, image, alignment, stats);
        this.stats = stats;
    }

    @Override
    public int atacar(Character character) {
        boolean critico = critico(alignment(), character.alignment());
        int poder = (stats.combat() + (int) (stats.power() * 1.4)) / 4;
        if(critico) poder = (int) (poder * 1.2);
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
    public void activarPoderEspecial() {

    }
}
