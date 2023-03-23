package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void testPlayer(){
        // Could be set to GameCharacter instead, for clarification purposes same test is in npc and player test classes.
        // Other way around compared to npcTest
        GameCharacter p = new Player("foo", 100);
        GameCharacter n = new Npc("foo", 100);
        assertEquals(p,p);
        assertEquals( 100-p.attack(n),n.getHitPoints());
        assertEquals(100, p.getHitPoints());
    }
}
