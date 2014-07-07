/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import Enums.Direction;
import Enums.GameState;
import Enums.GhostState;
import Interfaces.GamePanel;
import Objects.Cherry;
import Objects.Dot;
import Objects.GameObject;
import Objects.Ghost;
import Objects.GhostWall;
import Objects.MovingObject;
import Objects.Pacman;
import Objects.SuperDot;
import Objects.Wall;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Stack;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

/**
 *
 * @author Robin
 */
public final class Playfield extends JPanel implements GamePanel
{
    private Timer timer = new Timer();
    private int levelNumber;
    private Tile[][] level;
    private int[][] template;
    private int score = 0;
    private String time;
    private GameState state;
    private int totalDots;
    private int dotsSpawnCherry;
    private boolean cherrySpawned = false;
    
    @Override
    public GameState getGameState() { return this.state; }
    
    @Override
    public void addScore(int score){ this.score += score; }
    
    @Override
    public void removeDot(){ this.totalDots -= 1; }
    
    private final String[] imgBlinky = new String[]{
        "/images/BlinkyUp.png",
        "/images/BlinkyDown.png",
        "/images/BlinkyRight.png",
        "/images/BlinkyLeft.png",
        "/images/BlinkyIdle.png",
        "/images/Scared1.png",
        "/images/ScaredInverse1.png"
    };
            
    private final String[] imgInky = new String[]{
        "/images/InkyUp.png",
        "/images/InkyDown.png",
        "/images/InkyRight.png",
        "/images/InkyLeft.png",
        "/images/InkyIdle.png",
        "/images/Scared2.png",
        "/images/ScaredInverse2.png"
    };
    
    private final String[] imgPinky = new String[]{
        "/images/PinkyUp.png",
        "/images/PinkyDown.png",
        "/images/PinkyRight.png",
        "/images/PinkyLeft.png",
        "/images/PinkyIdle.png",
        "/images/Scared3.png",
        "/images/ScaredInverse3.png"
        
    };
    
    private final String[] imgClyde = new String[]{
        "/images/ClydeUp.png",
        "/images/ClydeDown.png",
        "/images/ClydeRight.png",
        "/images/ClydeLeft.png",
        "/images/ClydeIdle.png",
        "/images/Scared4.png",
        "/images/ScaredInverse4.png"
    };
    
    private final String[] imgDerpy = new String[]{
        "/images/DerpyUp.png",
        "/images/DerpyDown.png",
        "/images/DerpyRight.png",
        "/images/DerpyLeft.png",
        "/images/DerpyRight.png",
        "/images/Scared1.png",
        "/images/ScaredInverse1.png"
    };
    
    public Playfield()
    {
        state = GameState.PLAY;
        this.levelNumber = 1;
	this.setVisible(true);
	initLevel();
	startTimer();
    }
    
    private void startTimer()
    {
	if(timer != null)
	{
	    stopTimer();
	    startTimer();
	}
	else
	{
	    timer = new java.util.Timer();
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
			seconds = 0;
			minutes += 1;
		    }
		    time = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
		    redraw();
		}
	    }, 0, 1000);
	}
    }
    
    public void stopTimer()
    {
        if(timer != null)
        {
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }
    
    public void pauzeTimer(boolean pauzed)
    {
        if(pauzed)
        {
            stopTimer();
        }
        else
        {
            startTimer();
        }
    }
    
    private void setLevel()
    {
	int[][] level1 = new int[][]
	{
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 15, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 15, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 20, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 12, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {14, 14, 14, 14, 14, 14, 14, 11, 21, 22, 23, 11, 14, 14, 14, 14, 14, 14, 14},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 11, 14, 14, 14, 14, 14, 19, 14, 14, 14, 14, 14, 11, 14, 14, 11},
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
	    {11, 15, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 15, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 21, 22, 23, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 12, 11, 11, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 14, 14, 14, 20, 14, 14, 14, 11, 14, 11, 11, 11, 11},
	    {14, 14, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 11, 14, 14, 14, 14, 14},
	    {11, 11, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 14, 11, 11, 14, 11, 11, 14, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 14, 14, 14, 14, 11, 11, 14, 11, 11, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 14, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {10, 10, 10, 11, 14, 11, 14, 14, 14, 15, 14, 14, 14, 11, 14, 11, 10, 10, 10},
	    {11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11, 11, 14, 11, 14, 11, 11, 11, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 14, 11, 11, 14, 11, 11, 11, 14, 11, 14, 11, 11, 11, 14, 11, 11, 14, 11},
	    {11, 14, 14, 11, 14, 14, 11, 14, 14, 19, 14, 14, 11, 14, 14, 11, 14, 14, 11},
	    {11, 14, 14, 14, 11, 14, 14, 14, 11, 11, 11, 14, 14, 14, 11, 14, 14, 14, 11},
	    {11, 14, 11, 14, 14, 11, 11, 14, 14, 14, 14, 14, 11, 11, 14, 14, 11, 14, 11},
	    {11, 15, 11, 11, 14, 14, 11, 11, 11, 11, 11, 11, 11, 14, 14, 11, 11, 15, 11},
	    {11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11},
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}
	};
	
	int[][] level3 = new int[][]
	{
	    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
	    {11, 24, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 24, 11},
	    {11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11},
	    {11, 14, 11, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 11, 14, 11},
	    {11, 14, 11, 14, 11, 11, 11, 11, 14, 15, 14, 11, 11, 11, 11, 14, 11, 14, 11},
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
	    {11, 14, 11, 14, 14, 14, 14, 14, 14, 19, 14, 14, 14, 14, 14, 14, 11, 14, 11},
	    {11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11, 11, 11, 14, 11},
	    {11, 24, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 24, 11},
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
        cherrySpawned = false;
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
			totalDots += 1;
			break;
		    case 15:
			SuperDot superDot = new SuperDot();
			superDot.setTile(tile);
			tile.addGameObject(superDot);
			totalDots += 1;
			break;
		    case 19:
			Pacman pacman = new Pacman(this, tile);
                        pacman.setTile(tile);
			tile.addGameObject(pacman);
			this.addKeyListener((KeyListener) pacman);
			break;
		    case 20:
			Ghost blinky = new Ghost(this, tile, GhostState.SMART, imgBlinky);
                        blinky.setTile(tile);
			tile.addGameObject(blinky);
			break;
                    case 21:
                        Ghost inky = new Ghost(this, tile, GhostState.DUMB, imgInky);
                        inky.setTile(tile);
			tile.addGameObject(inky);
			break;
                    case 22:
                        Ghost pinky = new Ghost(this, tile, GhostState.SMART, imgPinky);
                        pinky.setTile(tile);
			tile.addGameObject(pinky);
			break;
                    case 23:
                        Ghost clyde = new Ghost(this, tile, GhostState.DUMB, imgClyde);
                        clyde.setTile(tile);
			tile.addGameObject(clyde);
			break;
                    case 24:
                        Ghost derpy = new Ghost(this, tile, GhostState.DUMB, imgDerpy);
                        derpy.setTile(tile);
			tile.addGameObject(derpy);
			break;
		}
		level[i][j] = tile;
	    }
	}
        dotsSpawnCherry = totalDots / 2;
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
    
    public void start()
    {
        for (int i = 0; i < level.length; i++) 
        {
            for (int j = 0; j < level[0].length; j++) 
            {
                Stack<GameObject> it = level[i][j].getGameObjects();
                for (int k = 0; k < it.size(); k++) 
                {
                    GameObject object = it.get(k);
                    if(object != null && object instanceof Ghost)
                    {
                        ((Ghost) object).pauzeTimer(false);
                    }
                    if(object != null && object instanceof Pacman)
                    {
                        ((Pacman) object).pauzeTimer(false);
                    }
                }
            }
        }
        pauzeTimer(false);
    }
    
    public void pauze()
    {
        for (int i = 0; i < level.length; i++) 
        {
            for (int j = 0; j < level[0].length; j++) 
            {
                Iterator<GameObject> it = level[i][j].getGameObjects().iterator();
                while(it.hasNext()) 
                {
                    GameObject object = it.next();
                    if(object != null && object instanceof Ghost)
                    {
                        ((Ghost) object).pauzeTimer(true);
                    }
                    if(object != null && object instanceof Pacman)
                    {
                        ((Pacman) object).pauzeTimer(true);
                    }
                }
            }
        }
        pauzeTimer(true);
        repaint();
    }
    
    public void restart()
    {
	levelNumber = 1;
	score = 0;
        totalDots = 0;
        dotsSpawnCherry = 0;
        cherrySpawned = false;
        setGameState(GameState.PLAY);
        Pacman.resetLives();
	emptyField(false);
	initLevel();
	startTimer();
        repaint();
        
    }
    
    private void emptyField(boolean gameover)
    {
	for (int i = 0; i < level.length; i++) 
        {
            for (int j = 0; j < level[0].length; j++) 
	    {
                if(level[i][j].getGameObjects() != null && !level[i][j].getGameObjects().empty())
                {
                    level[i][j].resetGameObjects();
                }
	    }
	}
        if(!gameover)
        {
            level = null;
        }
    }
    
    @Override
    public void restartPositions()
    {
        for(int i = 0; i < level.length; i++) 
        {
            for(int j = 0; j < level[0].length; j++) 
            {
                GameObject object = level[i][j].getGameObject();
                if(object instanceof MovingObject){
                    ((MovingObject)object).resetPosition();
                }
                if(object instanceof Ghost){
                    ((Ghost)object).resetState();
		    ((MovingObject)object).resetPosition();
                }
            }
        }
        if(state != GameState.GAMEOVER)
        {
            setGameState(GameState.PLAY);
        }
    }
    
    @Override
    public void setGameState(GameState gameState) 
    {
        state = gameState;
        switch(state){
            case SUPERPACMAN:
                scareGhosts();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setGameState(GameState.PLAY);
                        unScareGhosts();
                        this.cancel();
                    }
                }, 8000);

                break;
            case GAMEOVER:
                emptyField(true);
                stopTimer();
                redraw();
                break;
        }
    }
    
    private void scareGhosts(){
        for(int i = 0; i < level.length; i++) 
        {
            for(int j = 0; j < level[0].length; j++) 
            {
                GameObject object = level[i][j].getGameObject();
                if(object instanceof Ghost){
                    if(((Ghost)object).getState() != GhostState.SCARED && 
                       ((Ghost)object).getState() != GhostState.EATEN)
                        ((Ghost)object).setState(GhostState.SCARED);
                }
            }
        }
    }
    
    private void unScareGhosts(){
        for(int i = 0; i < level.length; i++) 
        {
            for(int j = 0; j < level[0].length; j++) 
            {
                GameObject object = level[i][j].getGameObject();
                if(object instanceof Ghost){
                    if(((Ghost)object).getState() == GhostState.SCARED)
                        ((Ghost)object).resetState();
                }
                if(object instanceof Ghost){
                    ((Ghost)object).resetState();
                }
            }
        }
        setGameState(GameState.PLAY);
    }
    
    private void spawnFruit()
    {
        for (int i = 0; i < template.length; i++)
        {
            for (int j = 0; j < template[0].length; j++)
            {
                if(template[i][j] == 19)
                {
                    Cherry cherry = new Cherry();
                    cherry.setTile(level[i][j]);
                    level[i][j].addGameObject(cherry);
                    cherrySpawned = true;
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        if(state != GameState.GAMEOVER)
        {
            for (int i = 0; i < level.length; i++) 
            {
                for (int j = 0; j < level[0].length; j++) 
                {
                    GameObject gameObject = null;
                    if(level[i][j].getGameObjects() != null && !level[i][j].getGameObjects().empty())
                    {
                        gameObject = level[i][j].getGameObject();
                    }
                    
                    Tile tile = level[i][j];

                    if(tile != null) tile.draw(g);
                    if(gameObject != null) gameObject.draw(g);
                }
            }
        }
	
	g.setColor(Color.WHITE);
	g.setFont(new Font("default", Font.BOLD, 16));
	g.drawString("Score: " + String.format("%06d", this.score), 10, 15);
	g.drawString("Lives: " + Pacman.getLives(), 150, 15);
	g.drawString("Time: " + time, this.getWidth() - 100, 15);
        
	if(state == GameState.PAUSE)
        {
            g.setColor(Color.RED);
            g.fillRoundRect((this.getWidth() - 200) / 2, (this.getHeight() - 60) / 2, 200, 60, 25, 25);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
            g.drawString("Paused", (this.getWidth() - 170) / 2, (this.getHeight() + 35) / 2);
        }
        
        if(state == GameState.GAMEOVER)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 500, 700);
            g.setColor(Color.RED);
            g.fillRoundRect((this.getWidth() - 320) / 2, (this.getHeight() - 60) / 2, 320, 60, 25, 25);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
            g.drawString("GAMEOVER", (this.getWidth() - 290) / 2, (this.getHeight() + 35) / 2);
            g.setColor(Color.WHITE);
            g.setFont(new Font("default", Font.BOLD, 16));
            g.drawString("Score: " + String.format("%06d", this.score), (this.getWidth() - 280) / 2, (this.getHeight() + 70) / 2);
            g.drawString("Time: " + time, (this.getWidth() + 100) / 2, (this.getHeight() + 70) / 2);
        }
        
	if(totalDots == 0)
	{
	    levelNumber++;
            if(levelNumber <= 3)
            {   
                emptyField(false);
                initLevel();
            }
	}
        System.out.println(totalDots);
        if(!cherrySpawned && totalDots <= dotsSpawnCherry)
        {
            spawnFruit();
        }
    }

    @Override
    public void redraw() {
        repaint();
    }
}
