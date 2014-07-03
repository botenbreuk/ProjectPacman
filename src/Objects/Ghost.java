/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import Enums.GameState;
import Enums.GhostState;
import Pathfinders.RandomPath;
import Interfaces.Pathfinder;
import Interfaces.GamePanel;
import Pathfinders.Breadthfirst;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import projectpacman.Tile;

/**
 *
 * @author Robin
 */
public class Ghost extends MovingObject{

    private Timer timer;
    private final int speed = 250;
    private final GhostState defaultState;
    private GhostState currentState;
    private Pathfinder pathfinder;
    private final String[] imgSet;
    
    public Ghost(GamePanel playfield, Tile startingTile, GhostState state, String[] imgSet)
    {
        super(playfield, startingTile);
        this.defaultState = state;
        currentState = defaultState;
        this.imgSet = imgSet;
        setPathfinder();
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
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() 
                {
                    Tile t = null;
                    if(pathfinder != null)
                    {
                        t = pathfinder.getExit(getTile());
                    }
                    if(t != null)
                    {
                        move(t);
                    }
                }

            } , 0, speed);
        }
    }
    
    public void stopTimer()
    {
        if(timer != null)
        {
            if(super.gamePanel.getGameState() != GameState.PAUSE)
            {
                pathfinder = null;
            }
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
            setPathfinder();
            startTimer();
        }
    }
            
    public GhostState getState(){ return currentState; }
    public void resetState(){ setState(defaultState); };
    public void setState(GhostState state){ 
        this.currentState = state; 
        setPathfinder();
    }
    
    private void setPathfinder(){
        switch(currentState){
        case DUMB:
            pathfinder = new RandomPath();
            break;
        case SMART:
            pathfinder = new Breadthfirst();
            break;
        case EATEN:
            pathfinder = null;
            break;
        case SCARED:
            pathfinder = new RandomPath(pathfinder.reverseDirection());
            break;
        }
    }
    
    @Override
    protected void move(Tile t)
    {
	//Tile tile = super.getTile().getNeigbour(d);
        //curDir = d;
        
        if(!(t.getGameObject() instanceof Pacman)){
            super.getTile().removeGameObject();
            t.addGameObject(this);
            super.setTile(t);
            super.gamePanel.redraw();
        }else{
            if(currentState != GhostState.SCARED && currentState != GhostState.EATEN){
                //System.out.println("Ik ben op pacman");
                gamePanel.restartPositions();
            }else{
                setState(GhostState.EATEN);
            }
        }
    }

    @Override
    public void resetPosition() {
        super.getTile().removeGameObject();
        startingTile.addGameObject(this);
        super.setTile(super.startingTile);
    }
    
    @Override
    public void draw(Graphics g) 
    {	
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
        
	//g.setColor(Color.RED);
        //g.fillOval(x, y, tile.getWidth(), tile.getHeight());
        try 
        {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(imgSet[4]));
                if(currentState != GhostState.SCARED){
                    if(pathfinder != null){
                    switch(pathfinder.getCurDir()) {
                    case NORTH:
                        img = ImageIO.read(getClass().getResourceAsStream(imgSet[0]));
                        break;
                    case SOUTH:
                        img = ImageIO.read(getClass().getResourceAsStream(imgSet[1]));
                        break;
                    case EAST:
                        img = ImageIO.read(getClass().getResourceAsStream(imgSet[2]));
                        break;
                    case WEST:
                        img = ImageIO.read(getClass().getResourceAsStream(imgSet[3]));
                        break;
                    }
                }
            }else{
                img = ImageIO.read(getClass().getResourceAsStream(imgSet[5]));
            }
            
            g.drawImage(img, x, y, null);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
