/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
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
    private final int width = 25;
    private final int height = 25;
    
    public Tile getNeigbour(Direction direction) { return this.neighbours.get(direction); }
    public GameObject getGameObject() { return this.figure; }
    public int getXPos() { return this.xPos; }
    public int getYPos() { return this.yPos; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    
    public void setNeighbour(Direction dir, Tile tile) { this.neighbours.put(dir, tile); }
    public void setGameObject(GameObject gameObject) { this.figure = gameObject; }
    
    public Tile(int xPos, int yPos)
    {
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
