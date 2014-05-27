/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Robin
 */
public class Pacman extends GameObject implements KeyListener
{
    public Pacman()
    {
	
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
	switch (e.getKeyCode()) {
	    case KeyEvent.VK_UP:
		move(Direction.NORTH);
		break;
	    case KeyEvent.VK_RIGHT:
		move(Direction.EAST);
		break;
	    case KeyEvent.VK_DOWN:
		move(Direction.SOUTH);
		break;
	    case KeyEvent.VK_LEFT:
		move(Direction.WEST);
		break;
	}
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    
    private void move(Direction d)
    {
	
	    if(d == Direction.NORTH)
            {
		Tile tileNorth = super.getTile().getNeigbour(d);
                if(tileNorth.getGameObject() instanceof Wall == false)
                {
                    super.getTile().setGameObject(null);
                    tileNorth.setGameObject(this);
                    super.setTile(tileNorth);
                }
            }
	    if(d == Direction.EAST)
            {
		Tile tileEast = super.getTile().getNeigbour(d);
                if(tileEast.getGameObject() instanceof Wall == false)
                {
                    super.getTile().setGameObject(null);
                    tileEast.setGameObject(this);
                    super.setTile(tileEast);
                }
            }
	    if(d == Direction.SOUTH)
            {
		Tile tileSouth = super.getTile().getNeigbour(d);
                if(tileSouth.getGameObject() instanceof Wall == false)
                {
                    super.getTile().setGameObject(null);
                    tileSouth.setGameObject(this);
                    super.setTile(tileSouth);
                }
            }
	    if(d == Direction.WEST)
            {
		Tile tileWest = super.getTile().getNeigbour(d);
                if(tileWest.getGameObject() instanceof Wall == false)
                {
                    super.getTile().setGameObject(null);
                    tileWest.setGameObject(this);
                    super.setTile(tileWest);
                }
            }
    }

    @Override
    public void draw(Graphics g) {
	Tile tile = super.getTile();
	int x = tile.getWidth() * tile.getXPos();
	int y = tile.getHeight() * tile.getYPos();
	
	g.setColor(Color.YELLOW);
	g.fillOval(x, y, tile.getWidth(), tile.getHeight());
    }
}
