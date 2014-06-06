/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Objects.GameObject;
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
    private final int width;
    private final int height;
    private final int xPos;
    private final int yPos;
    private final HashMap<Direction, Tile> neighbours;
    private final Stack<GameObject> gameObjects;
    
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    public GameObject getGameObject() { return !gameObjects.empty() ? this.gameObjects.peek() : null; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    public void addGameObject(GameObject gameObject) { this.gameObjects.push(gameObject); }
    public GameObject removeGameObject() { return this.gameObjects.pop(); }
    
    public Tile(int xPos, int yPos)
    {
        this.width = 25;
        this.height = 25;
	this.xPos = xPos;
	this.yPos = yPos;
	this.gameObjects = new Stack<>();
	this.neighbours = new HashMap<>();
    }
    
    public void draw(Graphics g)
    {
	int x = width * xPos;
	int y = height * yPos;
	g.setColor(Color.BLACK);
	g.fillRect(x, y, width, height);
    }
}
