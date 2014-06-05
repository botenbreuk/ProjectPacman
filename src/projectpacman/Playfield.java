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
    private int totalScore = 0;
    private static int score = 0;
    private static String time;
    private static Timer timer;
    
    public int getScore() { return Playfield.score; };
    public String getTime() { return Playfield.time; } 
    
    public static void setTime(String time) { Playfield.time = time; }
    
    public static void addScore(int score){ Playfield.score += score; }
    public static void resetScore() { Playfield.score = 0; }
    
    public Playfield()
    {
	this.setVisible(true);
	initLevel();
	startTime();
    }
    
    private void setLayout()
    {
	int[][] level1 = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 6, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 1, 1, 4, 1, 4, 1, 1, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 1, 4, 4, 4, 4, 4, 8, 4, 4, 4, 4, 4, 1, 4, 4, 1},
	    {1, 1, 4, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 4, 1, 1},
	    {1, 4, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 1, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};
	
	int[][] level2 = new int[][]
	{
	    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 0, 0, 0, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 1, 1, 6, 1, 1, 4, 4, 4, 4, 4, 4, 1},
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
	    {1, 4, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 1, 1, 4, 1, 4, 1, 0, 0, 0, 1, 4, 1, 4, 1, 1, 4, 1},
	    {1, 4, 4, 4, 4, 4, 4, 1, 1, 6, 1, 1, 4, 4, 4, 4, 4, 4, 1},
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
	    {1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
	    {1, 4, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 4, 1},
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
	setLayout();
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
			tile.setGameObject(null);
			break;
		    case 1:
                        Wall wall = new Wall();
                        wall.setTile(tile);
			tile.setGameObject(wall);
			break;
		    case 4:
			Dot dot = new Dot();
			dot.setTile(tile);
			tile.setGameObject(dot);
			totalScore += 10;
			break;
		    case 6:
			GhostWall door = new GhostWall();
			door.setTile(tile);
			tile.setGameObject(door);
			break;
		    case 8:
			Pacman pacman = new Pacman();
                        pacman.setTile(tile);
			tile.setGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			break;
		    case 9:
			Ghost ghost = new Ghost();
                        ghost.setTile(tile);
			tile.setGameObject(ghost);
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
	Playfield.time = "00:00";
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
    public void paint(Graphics g) 
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
	repaint();
	g.setColor(Color.WHITE);
	g.setFont(new Font("default", Font.BOLD, 16));
	g.drawString("Score: " + Playfield.score, 10, 15);
	g.drawString("Time: " + getTime(), this.getWidth() - 100, 15);
	
	if(score == totalScore)
	{
	    level++;
	    initLevel();
	}
    }
}
