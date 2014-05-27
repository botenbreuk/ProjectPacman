/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class TileTest {
    
    public TileTest() {
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
     * Test of getGameObject method, of class Tile.
     */
    @Test
    public void testGetGameObject() {
	System.out.println("getGameObject");
	Tile instance = null;
	GameObject expResult = null;
	GameObject result = instance.getGameObject();
	assertEquals(expResult, result);
    }

    /**
     * Test of getNeigbour method, of class Tile.
     */
    @Test
    public void testGetNeigbour() {
	System.out.println("getNeigbour");
	Direction direction = Direction.SOUTH;
	Tile instance = new Tile(1, 1);
	Tile expResult = new Tile(1, 2);
	instance.setNeighbour(direction, expResult);
	Tile result = instance.getNeigbour(direction);
	assertEquals(expResult, result);
    }

    /**
     * Test of setGameObject method, of class Tile.
     */
    @Test
    public void testSetGameObject() {
	System.out.println("setGameObject");
	GameObject gameObject = null;
	Tile instance = null;
	instance.setGameObject(gameObject);
    }

    /**
     * Test of setNeighbour method, of class Tile.
     */
    @Test
    public void testSetNeighbour() {
	System.out.println("setNeighbour");
	Direction dir = Direction.NORTH;
	Tile tile = new Tile(1, 1);
	Tile instance = new Tile(2, 1);
	Tile expResult = tile;
	instance.setNeighbour(dir, tile);
	Tile result = instance.getNeigbour(dir);
	assertEquals(expResult, result);
    }
    
}
