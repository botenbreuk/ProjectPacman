/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public final class Playfield extends JPanel
{
    private Tile[][] tiles;
    private int[][] layout;
    private int level = 1;
    private int maxScore = 0;
    private int score = 0;
    private String time;
    private Timer timer;
    
    public int getScore() { return this.score; };
    public String getTime() { return this.time; } 
    
    public void setTime(String time) { this.time = time; }
    
    public void addScore(int score){ this.score += score; }
    public void resetScore() { this.score = 0; }
    
    public Playfield()
    {
	this.setVisible(true);
	initLevel();
	startTime();
    }
    
    private void setLevel()
    {
	int[][] level1 = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 5, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 5, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 8, 9, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 2, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1},
	    {1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 5, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 5, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	
	int[][] level2 = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 0, 0, 0, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 1, 1, 2, 1, 1, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 4, 1, 4, 4, 4, 0, 4, 4, 4, 1, 4, 1, 1, 1, 1},
	    {4, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 4, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 1, 1, 4, 1, 1, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 1, 4, 4, 1, 4, 4, 8, 4, 4, 1, 4, 4, 1, 4, 4, 1},
	    {1, 4, 4, 4, 1, 4, 4, 4, 1, 1, 1, 4, 4, 4, 1, 4, 4, 4, 1},
	    {1, 4, 1, 4, 4, 1, 1, 4, 4, 4, 4, 4, 1, 1, 4, 4, 1, 4, 1},
	    {1, 4, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	
	int[][] level3 = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 4, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 1, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 4, 1, 4, 4, 4, 1, 4, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 4, 1, 1, 4, 4, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 1, 1, 4, 4, 4, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 1, 1, 1, 8, 1, 1, 1, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 4, 1, 4, 4, 4, 1, 4, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 4, 1},
	    {1, 4, 4, 1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	
	if(level == 1)
	{
	    layout = level1;
	}
	else if(level == 2)
	{
	    layout = level2;
	}
	else if(level == 3)
	{
	    layout = level3;
	}
    }
    
    public void initLevel()
    {
	setLevel();
	tiles = new Tile[layout.length][layout[0].length];
	for (int i = 0; i < layout.length; i++) 
	{
	    for (int j = 0; j < layout[0].length; j++) 
	    {	
		Tile tile = new Tile(j, i);
		switch(layout[i][j])
		{
		    default:
		    case 0:
			tile.addGameObject(null);
			break;
		    case 1:
                        Wall wall = new Wall();
                        wall.setTile(tile);
			tile.addGameObject(wall);
			break;
		    case 2:
			GhostWall door = new GhostWall();
			door.setTile(tile);
			tile.addGameObject(door);
			break;
		    case 4:
			Dot dot = new Dot();
			dot.setTile(tile);
			tile.addGameObject(dot);
			maxScore += 10;
			break;
		    case 5:
			SuperDot superDot = new SuperDot();
			superDot.setTile(tile);
			tile.addGameObject(superDot);
			break;
		    case 8:
			Pacman pacman = new Pacman();
                        pacman.setTile(tile);
			tile.addGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			break;
		    case 9:
			Ghost ghost = new Ghost();
                        ghost.setTile(tile);
			tile.addGameObject(ghost);
			break;
		}
		tiles[i][j] = tile;
	    }
	}
        setNeighbours();
    }
    
    private void setNeighbours()
    {
        for (int i = 0; i < tiles.length; i++) 
        {
            for (int j = 0; j < tiles[0].length; j++) 
            {
                if(i > 0)
                {
                    tiles[i][j].setNeighbour(Direction.NORTH, tiles[i - 1][j]);
                }
                if(i < tiles.length - 1)
                {
                    tiles[i][j].setNeighbour(Direction.SOUTH, tiles[i + 1][j]);
                }
                if(j > 0)
                {
                    tiles[i][j].setNeighbour(Direction.WEST, tiles[i][j - 1]);
                }
                if(j < tiles[0].length - 1)
                {
                    tiles[i][j].setNeighbour(Direction.EAST, tiles[i][j + 1]);
                }
                
		// if statements om door de randen van de level te gaan
                if(i == 0 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.NORTH, tiles[tiles.length - 1][j]);
                }
                if(i == tiles.length - 1 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.SOUTH, tiles[0][j]);
                }
                if(j == 0 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.WEST, tiles[i][tiles[0].length - 1]);
                }
                if(j == tiles[0].length - 1 && tiles[i][j].getGameObject() instanceof Wall == false)
                {
                    tiles[i][j].setNeighbour(Direction.EAST, tiles[i][0]);
                }
            }
        }
    }
    
    private void startTime()
    {
	this.time = "00:00";
	if(timer != null)
	{
	    timer.cancel();
	    timer = null;
	    startTime();
	}
	else
	{
	    timer = new Timer();
	    timer.schedule(new TimerTask() 
	    {
		private int minutes = 0;
		private int seconds = 0;
		@Override
		public void run() {

		    if(seconds < 59)
		    {
			seconds += 1;
		    }
		    else
		    {
			seconds = 1;
			minutes += 1;
		    }
		    setTime(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
		}
	    }, 0, 1000);
	}
    }

    @Override
    protected void paintComponent(Graphics g) 
    {	
	for (int i = 0; i < tiles.length; i++) 
	{
	    for (int j = 0; j < tiles[0].length; j++) 
	    {
		GameObject gmob = tiles[i][j].getGameObject();
		Tile tile = tiles[i][j];
		
		if(tile != null) tile.draw(g);
		if(gmob != null) gmob.draw(g);
	    }
	}
//	repaint();
	g.setColor(Color.WHITE);
	g.setFont(new Font("default", Font.BOLD, 16));
	g.drawString("Score: " + this.score, 10, 15);
	g.drawString("Time: " + getTime(), this.getWidth() - 100, 15);
	
	if(score >= maxScore)
	{
	    level++;
	    initLevel();
	}
    }
    
    
    
//    @Override
//    public void paint(Graphics g) 
//    {
//	
//    }
}
