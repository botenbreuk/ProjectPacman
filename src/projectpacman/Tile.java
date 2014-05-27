/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.util.HashMap;

/**
 *
 * @author Robin
 */
public class Tile
{
    private HashMap<Direction, Tile> neighbours = new HashMap<Direction, Tile>();
    private GameObject figure;
    private int xPos;
    private int yPos;
    
    public GameObject getGameObject() { return this.figure; }
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    
    public void setGameObject(GameObject gameObject) { this.figure = gameObject; }
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    
    public Tile(int xPos, int yPos)
    {
	this.xPos = xPos;
	this.yPos = yPos;
    }
    
    
}
