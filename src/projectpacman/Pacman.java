/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

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
	switch (d) {
	    case NORTH:
		super.getTile().getNeigbour(d);
		break;
	    case EAST:
		System.out.println("Right pressed");
		break;
	    case SOUTH:
		System.out.println("Down pressed");
		break;
	    case WEST:
		System.out.println("Left pressed");
		break;
	}
    }
}
