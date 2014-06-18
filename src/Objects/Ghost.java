/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import Pathfinders.RandomPath;
import Interfaces.Pathfinder;
import Interfaces.GamePanel;
import Pathfinders.Breadthfirst;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
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
    private GhostState state;
    private Pathfinder pathfinder;
    
    public Ghost(GamePanel playfield, Tile startingTile, GhostState state)
    {
        super(playfield, startingTile);
        this.state = state;
        initPathfinder();
	Timer timer = new Timer();
	timer.schedule(new TimerTask() {
            private final Random random = new Random();
	    @Override
	    public void run() 
	    {
                Tile t = pathfinder.getExit(getTile());
                if(t != null)
		{
                    move(t);
                }
		else
		{
                    //Omdat een spook niet opeens om mag draaien, 
                    //word de direction gereset bij een doodlopend punt.
                    //curDir = Direction.NONE;
                }
	    }
	    
	} , 0, 250);
    }
    
    private void initPathfinder(){
        switch(state){
            case DUMB:
                pathfinder = new RandomPath();
                break;
            case SMART:
                pathfinder = new Breadthfirst();
                break;
        }
    }
    
    @Override
    protected void move(Tile t)
    {
	//Tile tile = super.getTile().getNeigbour(d);
        //curDir = d;
        
        if(t.getGameObject() instanceof Pacman){
            //System.out.println("Ik ben op pacman");
            gamePanel.restart();
        }else{
            super.getTile().removeGameObject();
            t.addGameObject(this);
            super.setTile(t);
            super.gamePanel.paintComponent();
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
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeIdle.png"));
            switch(pathfinder.getCurDir()) {
            case NORTH:
                img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeUp.png"));
                break;
            case SOUTH:
                img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeDown.png"));
                break;
            case WEST:
                img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeLeft.png"));
                break;
            case EAST:
                img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeRight.png"));
                break;
            default:
                img = ImageIO.read(getClass().getResourceAsStream("/images/ClydeIdle.png"));
                break;
            }

            g.drawImage(img, x, y, null);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
