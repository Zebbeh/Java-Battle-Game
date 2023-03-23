package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCharacterTest {

    @Test
   public void testCharacterName(){
        GameCharacter g = new Player("foo", 100);
        assertEquals("foo", g.getName());


    }
    @Test
    public void testCharacterHp(){
        GameCharacter g = new Player("foo", 100);
        g.takeDamage(30);
        assertEquals(70, g.getHitPoints());
    }

}
