/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Objects.GameObject;
import Objects.Pacman;
import java.awt.Graphics;
import javax.swing.JFrame;
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
     * Test of getNeigbour method, of class Tile.
     */
    @Test
    public void testGetNeigbour() {
	System.out.println("Tests of getNeigbour()");
	
	//Test 1
	Direction direction = Direction.SOUTH;
	Tile instance = new Tile(1, 1);
	Tile expResult = new Tile(1, 2);
	instance.setNeighbour(direction, expResult);
	Tile result = instance.getNeigbour(direction);
	assertEquals(expResult, result);
	
	System.out.println("End of tests getNeighbour()\n----------------------\n");
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
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getXPos method, of class Tile.
     */
    @Test
    public void testGetXPos() {
	System.out.println("getXPos");
	Tile instance = null;
	int expResult = 0;
	int result = instance.getXPos();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getYPos method, of class Tile.
     */
    @Test
    public void testGetYPos() {
	System.out.println("Tests of getYPos()");
	
	//Test 1
	Tile instance = new Tile(0, 0);
	int expResult = 0;
	int result = instance.getYPos();
	assertEquals(expResult, result);
	
	//Test 2
	Tile instance2 = new Tile(0, 5);
	int expResult2 = 5;
	int result2 = instance2.getYPos();
	assertEquals(expResult2, result2);
	
	System.out.println("End of tests setNeighbour()\n----------------------\n");
    }

    /**
     * Test of getWidth method, of class Tile.
     */
    @Test
    public void testGetWidth() {
	System.out.println("Tests of getWidth()");
	
	//Test 1
	Tile instance = new Tile(0, 0);
	int expResult = 25;
	int result = instance.getWidth();
	assertEquals(expResult, result);
	
	System.out.println("End of tests getWidth()\n----------------------\n");
    }

    /**
     * Test of getHeight method, of class Tile.
     */
    @Test
    public void testGetHeight() {
	System.out.println("Tests of getHeight()");
	
	//Test 1
	Tile instance = new Tile(0, 2);
	int expResult = 25;
	int result = instance.getHeight();
	assertEquals(expResult, result);
	
	System.out.println("End of tests getHeight()\n----------------------\n");
    }

    /**
     * Test of setNeighbour method, of class Tile.
     */
    @Test
    public void testSetNeighbour() {
	System.out.println("Tests of setNeighbour()");
	
	//Test 1
	Direction dir = Direction.NORTH;
	Tile tile = new Tile(1, 1);
	Tile instance = new Tile(2, 1);
	Tile expResult = tile;
	instance.setNeighbour(dir, tile);
	Tile result = instance.getNeigbour(dir);
	assertEquals(expResult, result);
	
	System.out.println("End of tests setNeighbour()\n----------------------\n");
    }

    /**
     * Test of setGameObject method, of class Tile.
     */
    @Test
    public void testSetGameObject() {
	System.out.println("Tests of setGameObject()");
	
	//Test 1
	GameObject gameObject = new Pacman(new Playfield());
	Tile instance = new Tile(0, 0);
	instance.addGameObject(gameObject);
	GameObject result = instance.getGameObject();
	assertEquals(gameObject, result);
	
	System.out.println("End of tests setGameObject()\n----------------------\n");
    }

    /**
     * Test of draw method, of class Tile.
     */
    @Test
    public void testDraw() {
	System.out.println("Tests of draw()");
	
	//Test 1
	JFrame frame = new JFrame();
	Graphics g = frame.getGraphics();
	Tile instance = new Tile(0, 0);
	instance.draw(g);
	
	System.out.println("End of tests draw()\n----------------------\n");
    }
}
