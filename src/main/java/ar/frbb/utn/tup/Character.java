package ar.frbb.utn.tup;

public abstract class Character {
    private String raza; //(humanos/orcos/elfos)
    private String name;
    private String nickname;
    private String birthdate;
    private int age; //entre 0 a 300
    private int health = 100;
    private String image;

    private int speed;// 1 a 10
    private int dexterity; //1 a 5
    private int strength;//1 a 10
    private int level; //1 a 10
    private int armor; //1 a 10

    public Character(String name, String raza, String nickname, String birthdate, int age, int health, String image) {
        this.raza = raza;
        this.name = name;
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.age = age;
        this.health = health;
        this.image = image;
    }
    public Character(String name, String raza, String image) {
        this.name = name;
        this.raza = raza;
        this.image = image;
    }

    public Character() {

    }

    abstract int attack(Character character);

    abstract int takeDamage(int damage);

    public String getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public String getRaza() {
        return raza;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public int getLevel() {
        return level;
    }

    public int getArmor() {
        return armor;
    }
}
