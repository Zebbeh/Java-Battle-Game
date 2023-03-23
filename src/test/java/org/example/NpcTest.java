package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NpcTest {
    @Test
    public void testNpc(){
        // Could be set to GameCharacter instead, for clarification purposes same test is in npc and player test classes.
        // Other way around compared to playerTest
        GameCharacter p = new Player("foo", 100);
        GameCharacter n = new Npc("foo", 100);
        assertEquals(n,n);
        assertEquals( 100-n.attack(p),p.getHitPoints());
        assertEquals(100, n.getHitPoints());
    }
}
