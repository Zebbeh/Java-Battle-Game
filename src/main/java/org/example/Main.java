package org.example;


import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner strScan = new Scanner(System.in);
        GameCharacter player;
        String again, playerChoise, yesOrNo, loadGame;
        File f = new File("./player.save");
        // Game starts
        System.out.println("Let the battle begin");
        // Check if the file exists, if not let the player start a new game!
        // Player can choose to load the saved game or start a new game
        if (f.exists()) {
            load:
            while (true) {
                System.out.println("Would you like to load the last game [l] or start a new game [n]");
                loadGame = strScan.nextLine().toLowerCase();
                switch (loadGame) {
                    case "l":
                        player = (GameCharacter) FileUtils.loadObject("player.save");
                        System.out.printf("Welcome back %s\n", player.getName());
                        break load;
                    case "n":
                        System.out.print("Welcome noble warrior! Enter your name: ");
                        String playerName = strScan.nextLine();
                        player = new Player(playerName, 100);
                        break load;
                    default:
                        System.out.println("Wrong input");
                }
            }
        } else {
            // If the save file is not found start a new game!
            System.out.print("Welcome noble warrior! Enter your name: ");
            String playerName = strScan.nextLine();
            player = new Player(playerName, 100);
        }
        GameCharacter enemy = Npc.spawnNpc();
        System.out.printf("A scary-looking %s run towards you.\n", enemy.getName());
        loop:
        while (true) {
            System.out.println("Would you like to Attack [enter], Use potion [e], Open inventory [i] or fly like a fool and save [q]");
            playerChoise = strScan.nextLine().toLowerCase();
            // Check if player is alive
            if (player.getHitPoints() > 0) {
                switch (playerChoise) {
                    case "i":
                        System.out.println("--- INVENTORY ---");
                        for (int i = 0; i < player.inventory.size(); i++) {
                            System.out.printf("%d: %s (%d)\n", i + 1, player.inventory.get(i).getName(), player.inventory.get(i).getDamage());
                        }
                        while (true){
                            System.out.printf("Choose the weapon you would like to use 1-%d", player.inventory.size());
                            try {
                                int weaponOfChoice = Integer.parseInt(strScan.nextLine());
                                player.setEquippedWeapon(player.inventory.get(weaponOfChoice - 1));
                                continue loop;
                            } catch (IndexOutOfBoundsException e){
                                System.out.println("Please use a correct number");
                            } catch (NumberFormatException e){
                                System.out.printf("Please use a number: 1-%d\n", player.inventory.size());
                            }
                        }
                    case "":
                        // Attack
                        System.out.printf("%s hits %s for %d HP. %s has %d HP left.\n", player.getName(), enemy.getName(), player.attack(enemy), enemy.getName(), enemy.getHitPoints());
                        break;
                    // Heal
                    case "e":
                        System.out.printf("%s heals for %d HP. %s has %d HP left, \n", player.getName(), player.heal(), player.getName(), player.getHitPoints());
                        break;
                    // Quit
                    case "q":
                        break loop;
                    default:
                        System.out.println("Wrong input");
                        continue;
                }
                // Player takes damage
                if (enemy.getHitPoints() > 0) {
                    System.out.printf("%s hits %s for %d HP. %s has %d HP left.\n", enemy.getName(), player.getName(), enemy.attack(player), player.getName(), player.getHitPoints());
                }
                // Check if player is dead
                if (player.getHitPoints() <= 0) {
                    System.out.printf("%s is dead\n%s wins\n", player.getName(), enemy.getName());
                    break;
                }
            }
            // Check if enemy is dead
            if (enemy.getHitPoints() <= 0) {
                System.out.printf("%s is dead\n%s wins\n", enemy.getName(), player.getName());
                yesNo:
                while (true) {
                    System.out.printf("%s has dropped a %s (%d), would you like to pick it up?\nYes [y] or No [n] or quit and save [q]\n", enemy.getName(), enemy.getEquippedWeapon().getName(), enemy.getEquippedWeapon().getDamage());
                    yesOrNo = strScan.nextLine();
                    switch (yesOrNo) {
                        case "y":
                            player.inventory.add(enemy.getEquippedWeapon());
                            System.out.printf("%s added to your inventory\n", enemy.getEquippedWeapon().getName());
                            break yesNo;
                        case "n":
                            System.out.println("No weapons added!");
                            break yesNo;
                        case "q":
                            break loop;
                        default:
                            System.out.println("Wrong input!");
                    }
                }
                while (true) {
                    // Ask if player wants to play again after defeating the enemy
                    System.out.printf("%s has %d HP left. Do you want to smash more nasty stuff? [enter] to continue and [q] to quit and save", player.getName(), player.getHitPoints());
                    again = strScan.nextLine().toLowerCase();
                    // Check if player wants to continue
                    switch (again) {
                        case "":
                            enemy = Npc.spawnNpc();
                            System.out.printf("A %s appears from the shadows!\n", enemy.getName());
                            continue loop;
                        case "q":
                            break loop;
                        default:
                            System.out.println("Wrong input");
                    }
                }
            }
        }
        if (player.getHitPoints() > 0){
            String saveGame;
            saveGame:
            while (true) {
                System.out.println("Do you want to save the game?\nYes [y] or No [n]");
                saveGame = strScan.nextLine().toLowerCase();
                switch (saveGame) {
                    case "y":
                        FileUtils.saveObject(player, "player.save");
                        System.out.println("Game saved");
                        break saveGame;
                    case "n":
                        break saveGame;
                    default:
                        System.out.println("Wrong input");
                }
        }
        }
        System.out.println("GAME OVER!");
    }
}