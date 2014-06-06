/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Objects.GameObject;
import Objects.GhostWall;
import Objects.Pacman;
import Objects.Dot;
import Objects.SuperDot;
import Objects.Wall;
import Objects.Ghost;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
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
    private Timer timer;
    
    public int getScore() { return this.score; };
    public String getTime() { return this.time; } 
    
    public void setTime(String time) { this.time = time; }
    
    @Override
    public void addScore(int score){ this.score += score; }
    //public void resetScore() { this.score = 0; }
    
    public Playfield()
    {
        this.levelNumber = 1;
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
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 9, 4, 4, 4, 1, 4, 1, 0, 0, 0},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 2, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4},
	    {1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1, 1, 4, 1, 4, 1, 1, 1, 1},
	    {0, 0, 0, 1, 4, 1, 4, 4, 4, 8, 4, 4, 4, 1, 4, 1, 0, 0, 0},
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
			Pacman pacman = new Pacman(this);
                        pacman.setTile(tile);
			tile.addGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			break;
		    case 9:
			Ghost ghost = new Ghost(this);
                        ghost.setTile(tile);
			tile.addGameObject(ghost);
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
	g.drawString("Time: " + getTime(), this.getWidth() - 100, 15);
	
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
