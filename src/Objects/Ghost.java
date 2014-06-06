/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import projectpacman.Direction;
import projectpacman.GamePanel;
import projectpacman.Tile;

/**
 *
 * @author Robin
 */
public class Ghost extends MovingObject
{
    public Ghost(GamePanel playfield)
    {
        super.gamePanel = playfield;
	Timer timer = new Timer();
	timer.schedule(new TimerTask() {

            private final Random random = new Random();
            private Direction curDir = Direction.SOUTH;
	    @Override
	    public void run() {
		
		int randomDirection = random.nextInt(4);
		
                if(randomDirection == 0 && curDir != Direction.NORTH)
                {
		    curDir = Direction.NORTH;
                    move(Direction.NORTH);
                }
                if(randomDirection == 1 && curDir != Direction.EAST)
                {
		    curDir = Direction.EAST;
                    move(Direction.EAST);
                }
                if(randomDirection == 2 && curDir != Direction.SOUTH)
                {
		    curDir = Direction.SOUTH;
                    move(Direction.SOUTH);
                }
                if(randomDirection == 3 && curDir != Direction.WEST)
                {
		    curDir = Direction.WEST;
                    move(Direction.WEST);
                }
		System.out.println(randomDirection);
	    }
	    
	} , 0, 1000);
    }
    
    @Override
    protected void move(Direction d)
    {
	Tile tile = super.getTile().getNeigbour(d);
        if(tile != null)
        {
            if(tile.getGameObject() instanceof Wall == false ||
               tile.getGameObject() instanceof GhostWall == true ||
               tile.getGameObject() instanceof Ghost == true)
            {
		super.getTile().removeGameObject();
                tile.addGameObject(this);
                super.setTile(tile);
                super.gamePanel.paintComponent();
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
