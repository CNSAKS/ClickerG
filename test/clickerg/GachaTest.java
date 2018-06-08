/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Dragon
 */
public class GachaTest {
    
    
    public GachaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of getGold method, of class Gacha.
     */
    @Test
    public void testGetGold() {
        System.out.println("getGold");
        GachaToTest instance = new GachaToTest();
        int expResult = instance.gold;
        int result = instance.getGold();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of setGold method, of class Gacha.
     */
    @Test
    public void testSetGold() {
        System.out.println("setGold");
        int goldAmmount = 0;
        GachaToTest instance = new GachaToTest();
        instance.setGold(goldAmmount);
        assertEquals(goldAmmount,instance.getGold());
    }

    /**
     * Test of obtainRandomHero method, of class Gacha.
     */
    @Test
    public void testObtainRandomHero() throws Exception {
        System.out.println("obtainRandomHero");
        MouseEvent event = null;
        GachaToTest instance = new GachaToTest();
        instance.obtainRandomHero(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkGold method, of class Gacha.
     */
    @Test
    public void testCheckGold() {
        System.out.println("checkGold");
        GachaToTest instance = new GachaToTest();
        boolean expResult = false;
        boolean result = instance.checkGold();
        assertEquals(expResult, result);
    }

    /**
     * Test of randomHeroPicker method, of class Gacha.
     */
    @Test
    public void testRandomHeroPicker() throws Exception {
        System.out.println("randomHeroPicker");
        GachaToTest instance = new GachaToTest();
        int x = instance.contratadosToSave.size();
        instance.randomHeroPicker();
        assertEquals(x , instance.contratadosToSave.size());
    }


    /**
     * Test of actualizeGold method, of class Gacha.
     */
    @Test
    public void testActualizeGold() {
        System.out.println("actualizeGold");
        GachaToTest instance = new GachaToTest();
        int gold = instance.getGold();
        instance.actualizeGold();
        assertEquals(gold-500, instance.getGold());
    }

    /**
     * Test of cloneHero method, of class Gacha.
     */
    @Test
    public void testCloneHero() throws Exception {
        System.out.println("cloneHero");
        AccountHeroe heroe = new AccountHeroe("false" , "-1", "Vargas", "1", "2", "2", "0");
        GachaToTest instance = new GachaToTest();
        int x = instance.contratadosToSave.size();
        instance.cloneHero(heroe);
        assertEquals(x +1, instance.contratadosToSave.size());
    }



    
}
