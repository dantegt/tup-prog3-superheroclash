package ar.frbb.utn.tup;

public class Hero extends Character {

    public Hero(String name, String raza, String nickname, String birthdate, int age, int health, String image) {
        super(name, raza, nickname, birthdate, age, health, image);
    }

    public Hero(String name, String raza, String image) {
        super(name, raza, image);
    }


    public Hero() {
        super();
    }

    public int attack(Character character) {

        return 0;
    }

    public int takeDamage(int damage) {
        return damage;
    }
}
