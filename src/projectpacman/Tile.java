/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Robin
 */
public class Tile
{
    private final HashMap<Direction, Tile> neighbours;
    private final Stack<GameObject> gmObjects;
    private final int xPos;
    private final int yPos;
    private final int width = 25;
    private final int height = 25;
    
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    public GameObject getGameObject() { return !gmObjects.empty() ? this.gmObjects.peek() : null; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    public void addGameObject(GameObject gameObject) { this.gmObjects.push(gameObject); }
    public GameObject removeObject() { return this.gmObjects.pop(); }
    
    public Tile(int xPos, int yPos)
    {
	this.gmObjects = new Stack<>();
	this.neighbours = new HashMap<>();
	this.xPos = xPos;
	this.yPos = yPos;
    }
    
    public void draw(Graphics g)
    {
	int x = width * xPos;
	int y = height * yPos;
	g.setColor(Color.BLACK);
	g.fillRect(x, y, width, height);
    }
}
