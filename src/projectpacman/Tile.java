/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Enums.Direction;
import Objects.GameObject;
import Objects.Ghost;
import Objects.Pacman;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
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
    private Stack<GameObject> gameObjects;
    
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    public Stack<GameObject> getGameObjects() { return this.gameObjects; }
    public GameObject getGameObject() { return !gameObjects.empty() ? this.gameObjects.peek() : null; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    public void addGameObject(GameObject gameObject) { this.gameObjects.push(gameObject); }
    public GameObject removeGameObject() { return !gameObjects.empty() ? this.gameObjects.pop() : null; }
    public void resetGameObjects() 
    {
        Iterator<GameObject> it = gameObjects.iterator();
	while(it.hasNext()) 
	{
	    GameObject object = it.next();
	    if(object instanceof Pacman)
	    {
		((Pacman) object).stopTimer();
	    }
	    if(object instanceof Ghost)
	    {
		((Ghost) object).stopTimer();
		((Ghost) object).resetState();
	    }
	    it.remove();
	}
	gameObjects = null;
    }
    
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
