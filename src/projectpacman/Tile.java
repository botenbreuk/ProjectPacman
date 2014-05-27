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
    private final HashMap<Direction, Tile> neighbours;
    private GameObject figure;
    private final int xPos;
    private final int yPos;
    
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    public GameObject getGameObject() { return this.figure; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    public void setGameObject(GameObject gameObject) { this.figure = gameObject; }
    
    
    public Tile(int xPos, int yPos)
    {
	this.neighbours = new HashMap<>();
	this.xPos = xPos;
	this.yPos = yPos;
    }
}
