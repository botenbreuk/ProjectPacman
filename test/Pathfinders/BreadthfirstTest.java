/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pathfinders;

import Enums.Direction;
import Enums.GhostState;
import Interfaces.Pathfinder;
import Objects.Ghost;
import Objects.Pacman;
import Objects.Wall;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projectpacman.Playfield;
import projectpacman.Tile;

/**
 *
 * @author Robin
 */
public class BreadthfirstTest {
    
    Pathfinder pathfinder;
    Playfield PFdecoy = new Playfield(); //ongebruikt in de tests, 
                                         //word alleen gebruikt om pacman en spook aan te kunnen maken
    Tile[][] level;
    Ghost ghost;
    Pacman pacman;
    int[][] template1 = new int[][]{
        {11, 11, 11, 11}, //10 = niets, 11 = muur, 19 = pacman, 20 = spook
        {11, 20, 19, 11},
        {11, 11, 11, 11}
    };
    
    int[][] template2 = new int[][]{
        {11, 11, 11},
        {11, 20, 11},
        {11, 11, 11}
    };
    
    int[][] template3 = new int[][]{
        {11, 11, 11},
        {11, 10, 11},
        {11, 10, 19},
        {11, 20, 11},
        {11, 10, 11},
        {11, 11, 11},
    };
    
    
    
    public BreadthfirstTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pathfinder = new Breadthfirst();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getExit method, of class Breadthfirst.
     */
    @Test
    public void testGetExit() {
        System.out.println("getExit");
        Tile expResult;
        Tile result;
        
        initLevel(template1);
        expResult = getTile(2, 1);
        result = pathfinder.getExit(ghost.getTile());
        assertEquals(expResult, result);
        
        initLevel(template2);
        expResult = null;
        result = pathfinder.getExit(ghost.getTile());
        assertEquals(expResult, result);
        
        initLevel(template3);
        expResult = getTile(1, 2);
        result = pathfinder.getExit(ghost.getTile());
        assertEquals(expResult, result);
    }

    /**
     * Test of reverseDirection method, of class Breadthfirst.
     */
    /*@Test
    public void testReverseDirection() {
        System.out.println("reverseDirection");
        Breadthfirst instance = new Breadthfirst();
        Direction expResult = null;
        Direction result = instance.reverseDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of getCurDir method, of class Breadthfirst.
     */
    @Test
    public void testGetCurDir() {
        System.out.println("getCurDir");
        Direction expResult = Direction.NONE;
        Direction result = pathfinder.getCurDir();
        assertEquals(expResult, result);
    }
    
    public Tile getTile(int x, int y){
        return level[y][x];
    }
    
    public void initLevel(int[][] template)
    {
	level = new Tile[template.length][template[0].length];
	for (int i = 0; i < template.length; i++) 
	{
	    for (int j = 0; j < template[0].length; j++) 
	    {	
		Tile tile = new Tile(j, i);
		switch(template[i][j])
		{
		    default:
		    case 10:
			tile.addGameObject(null);
			break;
		    case 11:
                        Wall wall = new Wall();
                        wall.setTile(tile);
			tile.addGameObject(wall);
			break;
		    case 19:
			pacman = new Pacman(PFdecoy, tile);
                        pacman.setTile(tile);
			tile.addGameObject(pacman);
			break;
		    case 20:
			ghost = new Ghost(PFdecoy, tile, GhostState.SMART, null);
                        ghost.setTile(tile);
			tile.addGameObject(ghost);
			break;
		}
		level[i][j] = tile;
	    }
	}
        setNeighbours();
    }
    
    private void setNeighbours()
    {
        for (int i = 0; i < level.length; i++) 
        {
            for (int j = 0; j < level[0].length; j++) 
            {
                if(i > 0)
                {
                    level[i][j].setNeighbour(Direction.NORTH, level[i - 1][j]);
                }
                if(i < level.length - 1)
                {
                    level[i][j].setNeighbour(Direction.SOUTH, level[i + 1][j]);
                }
                if(j > 0)
                {
                    level[i][j].setNeighbour(Direction.WEST, level[i][j - 1]);
                }
                if(j < level[0].length - 1)
                {
                    level[i][j].setNeighbour(Direction.EAST, level[i][j + 1]);
                }
                
		// if statements om door de randen van de level te gaan
                if(i == 0 && level[i][j].getGameObject() instanceof Wall == false)
                {
                    level[i][j].setNeighbour(Direction.NORTH, level[level.length - 1][j]);
                }
                if(i == level.length - 1 && level[i][j].getGameObject() instanceof Wall == false)
                {
                    level[i][j].setNeighbour(Direction.SOUTH, level[0][j]);
                }
                if(j == 0 && level[i][j].getGameObject() instanceof Wall == false)
                {
                    level[i][j].setNeighbour(Direction.WEST, level[i][level[0].length - 1]);
                }
                if(j == level[0].length - 1 && level[i][j].getGameObject() instanceof Wall == false)
                {
                    level[i][j].setNeighbour(Direction.EAST, level[i][0]);
                }
            }
        }
    }
}
