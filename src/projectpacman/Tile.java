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
    private HashMap<Direction, Tile> neighbours;
    private GameObject figure;
    private int xPos;
    private int yPos;
    
    public GameObject getGameObject() { return this.figure; }
    
    public void setGameObject(GameObject gameObject) { this.figure = gameObject; }
    
    public Tile(int xPos, int yPos)
    {
	this.xPos = xPos;
	this.yPos = yPos;
    }
    
    public Tile getNeigbour(Direction direction)
    {
	return null;
    }
}
