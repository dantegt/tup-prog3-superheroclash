package ar.frbb.utn.tup.characters;

import ar.frbb.utn.tup.Stats;

public abstract class Character {
    private String name;
    private String raza;
    private String genero;
    private String image;
    private String alignment;
    private Stats stats;

    private int hpTotal;
    private int hpActual;

    public Character(String name, String raza, String genero, String image, String alignment, Stats stats) {
        this.name = name;
        this.raza = raza;
        this.genero = genero;
        this.image = image;
        this.alignment = alignment;
        this.stats = stats;
        setHpInicial();
    }

    private void setHpInicial() {
        hpTotal = stats.strength() + (int) (stats.durability() * 2.4);
        hpActual = hpTotal;
    }
    public abstract int atacar(Character character);
    public abstract void defender(Character character, int damage);
    public abstract void poderEspecial(Character character);

    public boolean critico (String atkAlign, String defAlign) {
        return  (atkAlign.equals("good") && defAlign.equals("bad")) ||
                (atkAlign.equals("bad") && defAlign.equals("neutral")) ||
                (atkAlign.equals("neutral") && defAlign.equals("good"));
    }

    public String name() {
        return name;
    }

    public Character setName(String name) {
        this.name = name;
        return this;
    }

    public String raza() {
        return raza;
    }

    public Character setRaza(String raza) {
        this.raza = raza;
        return this;
    }

    public String genero() {
        return genero;
    }

    public Character setGenero(String genero) {
        this.genero = genero;
        return this;
    }

    public String image() {
        return image;
    }

    public Character setImage(String image) {
        this.image = image;
        return this;
    }

    public String alignment() {
        return alignment;
    }

    public Character setAlignment(String alignment) {
        this.alignment = alignment;
        return this;
    }

    public Stats stats() {
        return stats;
    }

    public Character setStats(Stats stats) {
        this.stats = stats;
        return this;
    }

    public int hpTotal() {
        return hpTotal;
    }

    public Character setHpTotal(int hpTotal) {
        this.hpTotal = hpTotal;
        return this;
    }

    public int hpActual() {
        return hpActual;
    }

    public Character setHpActual(int hpActual) {
        this.hpActual = hpActual;
        return this;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", raza='" + raza + '\'' +
                ", genero='" + genero + '\'' +
                ", image='" + image + '\'' +
                ", alignment='" + alignment + '\'' +
                ", stats=" + stats +
                '}';
    }
}
