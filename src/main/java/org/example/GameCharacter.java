package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class GameCharacter implements Serializable {
    private String name;
    public int hitPoints;
    private int dmg;
    private Weapon equippedWeapon,sword;
    public double dexterity;
    public ArrayList<Weapon> inventory =  new ArrayList<>();


    public GameCharacter(String name, int hitPoints){
        sword = new Weapon("Sword", Utils.randomNum());
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = sword;
        inventory.add(sword);
    }
    public String getName() {
        return name;
    }

    public int getDmg() {
        return dmg;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void takeDamage(int damage) {

        this.hitPoints = this.hitPoints - damage;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {

        this.equippedWeapon = equippedWeapon;
        this.dmg = equippedWeapon.getDamage();
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int attack(GameCharacter defender){
        // Random damage based on weapon damage and attackers dexterity
        Random random = new Random();
        int maxDmg = equippedWeapon.getDamage();
        int minDmg = (int) (equippedWeapon.getDamage() * dexterity);
        int randomDmg = (random.nextInt( (maxDmg + 1 - minDmg)) + minDmg);
        defender.takeDamage(randomDmg);
        setDmg(randomDmg);
        // Set HP to 0 if HP is below 0
        if (defender.getHitPoints() < 0){
            defender.setHitPoints(0);
        }
        return randomDmg;
    }
    public int heal() {
        int heal = Utils.randomNum();
        if (getHitPoints() < 100) {
            int playerHp = getHitPoints() + heal;
            setHitPoints(playerHp);
        }
        if (getHitPoints() >= 100) {
            setHitPoints(100);
        }
        return heal;
    }
}
