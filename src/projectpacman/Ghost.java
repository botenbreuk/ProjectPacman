/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Robin
 */
public class Ghost extends GameObject
{
    public Ghost()
    {
	Timer timer = new Timer();
	timer.schedule(new TimerTask() {

            private final Random random = new Random();
            private final Direction curDir = Direction.WEST;
	    @Override
	    public void run() {
		
		int randomDirection = random.nextInt(4);
		
                if(randomDirection == 0 && curDir != Direction.NORTH)
                {
                    move(Direction.NORTH);
                }
                if(randomDirection == 1 && curDir != Direction.EAST)
                {
                    move(Direction.EAST);
                }
                if(randomDirection == 2 && curDir != Direction.SOUTH)
                {
                    move(Direction.SOUTH);
                }
                if(randomDirection == 3 && curDir != Direction.WEST)
                {
                    move(Direction.WEST);
                }
	    }
	    
	} , 0, 1000);
    }
    
    public void move(Direction d)
    {
	Tile tile = super.getTile().getNeigbour(d);
        if(tile != null)
        {
            if(tile.getGameObject() instanceof Wall == false ||
               tile.getGameObject() instanceof GhostWall == true ||
               tile.getGameObject() instanceof Ghost == true)
            {
                tile.setGameObject(this);
                super.setTile(tile);
            }
        }
    }
    
    @Override
    public void draw(Graphics g) 
    {	
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(Color.RED);
	g.fillOval(x, y, tile.getWidth(), tile.getHeight());
    }
    
}
