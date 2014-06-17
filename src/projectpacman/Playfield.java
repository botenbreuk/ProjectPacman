/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Interfaces.GamePanel;
import Objects.Dot;
import Objects.GameObject;
import Objects.Ghost;
import Objects.GhostState;
import Objects.GhostWall;
import Objects.MovingObject;
import Objects.Pacman;
import Objects.SuperDot;
import Objects.Wall;
import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public final class Playfield extends JPanel implements GamePanel
{
    private int levelNumber;
    private Tile[][] level;
    private int[][] template;
    private int maxScore = 0;
    private int score = 0;
    private String time;
    
    public int getScore() { return this.score; };
    public String getTime() { return this.time; }
    
    @Override
    public void addScore(int score){ this.score += score; }
    //public void resetScore() { this.score = 0; }
    
    public Playfield()
    {
        this.levelNumber = 1;
	this.setVisible(true);
	initLevel();
	
    }
    
    private void setLevel()
    {
	int[][] level1 = new int[][]
	{
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 15, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 15, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 20, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 12, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {14, 14, 14, 14, 14, 14, 14, 11, 19, 19, 19, 11, 14, 14, 14, 14, 14, 14, 14},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 11, 14, 14, 14, 14, 14, 18, 14, 14, 14, 14, 14, 11, 14, 14, 11},
	    {11, 11, 14, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 14, 11, 11},
	    {11, 14, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 14, 11},
	    {11, 15, 11, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 11, 15, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}
	};
	
	int[][] level2 = new int[][]
	{
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 19, 19, 19, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 12, 11, 11, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 14, 14, 14, 10, 14, 14, 14, 11, 14, 11, 11, 11, 11},
	    {14, 14, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 14, 14},
	    {11, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 11, 14, 11, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 14, 11, 11, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 14, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 11, 14, 14, 11, 14, 14, 18, 14, 14, 11, 14, 14, 11, 14, 14, 11},
	    {11, 14, 14, 14, 11, 14, 14, 14, 11, 11, 11, 14, 14, 14, 11, 14, 14, 14, 11},
	    {11, 14, 11, 14, 14, 11, 11, 14, 14, 14, 14, 14, 11, 11, 14, 14, 11, 14, 11},
	    {11, 14, 11, 11, 14, 14, 11, 11, 11, 11, 11, 11, 11, 14, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}
	};
	
	int[][] level3 = new int[][]
	{
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
	    {11, 19, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 19, 11},
	    {11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11},
	    {11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 11, 11, 11, 14, 14, 14, 11, 11, 11, 11, 14, 11, 14, 11},
	    {11, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 11},
	    {11, 14, 11, 14, 11, 14, 14, 11, 14, 14, 14, 11, 14, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 14, 14, 11, 11, 14, 14, 14, 14, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 14, 14, 11, 11, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 11, 11, 11, 18, 11, 11, 11, 14, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 14, 14, 11, 14, 14, 14, 11, 14, 14, 11, 14, 11, 14, 11},
	    {11, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 11},
	    {11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11},
	    {11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11},
	    {11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11},
	    {11, 19, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 19, 11},
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}
	};
	
	if(levelNumber == 1)
	{
	    template = level1;
	}
	else if(levelNumber == 2)
	{
	    template = level2;
	}
	else if(levelNumber == 3)
	{
	    template = level3;
	}
    }
    
    public void initLevel()
    {
	setLevel();
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
		    case 12:
			GhostWall door = new GhostWall();
			door.setTile(tile);
			tile.addGameObject(door);
			break;
		    case 14:
			Dot dot = new Dot();
			dot.setTile(tile);
			tile.addGameObject(dot);
			maxScore += dot.getScoreValue();
			break;
		    case 15:
			SuperDot superDot = new SuperDot();
			superDot.setTile(tile);
			tile.addGameObject(superDot);
			maxScore += superDot.getScoreValue();
			break;
		    case 18:
			Pacman pacman = new Pacman(this, tile);
                        pacman.setTile(tile);
			tile.addGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			break;
		    case 19:
			Ghost dumbGhost = new Ghost(this, tile, GhostState.DUMB);
                        dumbGhost.setTile(tile);
			tile.addGameObject(dumbGhost);
			break;
                    case 20:
                        Ghost smartGhost = new Ghost(this, tile, GhostState.SMART);
                        smartGhost.setTile(tile);
			tile.addGameObject(smartGhost);
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
    
    @Override
    public void restart()
    {
        for(int i = 0; i < level.length; i++) 
        {
            for(int j = 0; j < level[0].length; j++) 
            {
                GameObject object = level[i][j].getGameObject();
                if(object instanceof MovingObject){
                    ((MovingObject)object).resetPosition();
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {	
	for (int i = 0; i < level.length; i++) 
	{
	    for (int j = 0; j < level[0].length; j++) 
	    {
		GameObject gameObject = level[i][j].getGameObject();
		Tile tile = level[i][j];
		
		if(tile != null) tile.draw(g);
		if(gameObject != null) gameObject.draw(g);
	    }
	}
	g.setColor(Color.WHITE);
	g.setFont(new Font("default", Font.BOLD, 16));
	g.drawString("Score: " + this.score, 10, 15);
//	g.drawString("Time: " + getTime(), this.getWidth() - 100, 15);
	
	if(score >= maxScore)
	{
	    levelNumber++;
	    initLevel();
	}
    }

    @Override
    public void paintComponent() {
        repaint();
    }
}
