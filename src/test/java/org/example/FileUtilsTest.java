package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsTest {

    @Test
    public void testPlayerSaveAndLoad(){
        GameCharacter p = new Player("foo", 100);
        FileUtils.saveObject(p,"test.save");
        GameCharacter x = (GameCharacter) FileUtils.loadObject("test.save");
        assertEquals("foo",x.getName());
    }
    @Test
    public void testInventory(){
        GameCharacter p =  new Player("foo", 100);
        Weapon w = new Weapon("bar", 50);
        p.inventory = new ArrayList<>();
        p.inventory.add(w);
        FileUtils.saveObject(p,"test.save");
        GameCharacter x = (GameCharacter) FileUtils.loadObject("test.save");
        assertEquals(w.getName(), x.inventory.get(0).getName());
    }
}
