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
    private long lastPressProcessed = 0;
    
    public Pacman()
    {
	
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
	
	if(System.currentTimeMillis() - lastPressProcessed > 150) {
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
            lastPressProcessed = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { 
	
	lastPressProcessed = 0;
    }
    
    private void move(Direction d)
    {
	Tile tile = super.getTile().getNeigbour(d);
	GameObject object = tile.getGameObject();
	System.out.println(object);
	if(object instanceof Wall == false)
	{
	    if(object instanceof Dot) 
	    {
		Dot dot = (Dot) object;
		tile.removeObject();
	    }
	    tile.addGameObject(this);
	    super.setTile(tile);
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
