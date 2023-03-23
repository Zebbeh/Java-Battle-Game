package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponTest {
    @Test
    public void testWeaponName(){
        Weapon weapon = new Weapon("foo", 10);
        assertEquals("foo", weapon.getName());
    }
}
