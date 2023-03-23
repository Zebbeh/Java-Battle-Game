package org.example;

import java.util.Random;

public class Npc extends GameCharacter {
   private static Random random;

   private static Weapon[] weapons;

    public Npc(String name, int hitPoints) {
        super(name, hitPoints);
        dexterity = 0.5;
        setEquippedWeapon(randomWep());
    }
   public static GameCharacter spawnNpc(){
        random = new Random();
        String[] npcName = {"Ghoul","Bat","Demon","Gnoll","Centipede","Kobold","Goblin","Worg","Dragon"};
        return new Npc(npcName[random.nextInt(npcName.length)],Utils.randomHp());
    }
    private static Weapon randomWep(){
        random = new Random();
        weapons = new Weapon[5];
        weapons[0] = new Weapon("Club",Utils.randomNum());
        weapons[1] = new Weapon("Fist",Utils.randomNum());
        weapons[2] = new Weapon("Claw",Utils.randomNum());
        weapons[3] = new Weapon("Spear", Utils.randomNum());
        weapons[4] = new Weapon("Kick", Utils.randomNum());
        return weapons[random.nextInt(weapons.length)];
    }
}
