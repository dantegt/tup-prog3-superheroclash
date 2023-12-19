package ar.frbb.utn.tup;

public class Stats {
    private int power;
    private int combat;
    private int durability; 
    private int strength;
    private int speed;
    private int intelligence;

    public int power() {
        return power;
    }

    public Stats setPower(int power) {
        this.power = power;
        return this;
    }

    public int combat() {
        return combat;
    }

    public Stats setCombat(int combat) {
        this.combat = combat;
        return this;
    }

    public int durability() {
        return durability;
    }

    public Stats setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public int strength() {
        return strength;
    }

    public Stats setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public int speed() {
        return speed;
    }

    public Stats setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int intelligence() {
        return intelligence;
    }

    public Stats setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "power=" + power +
                ", combat=" + combat +
                ", durability=" + durability +
                ", strength=" + strength +
                ", speed=" + speed +
                ", intelligence=" + intelligence +
                '}';
    }
}
