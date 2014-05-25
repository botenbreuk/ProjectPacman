/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectpacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Robin
 */
public class Playfield extends JPanel
{
    private Tile[][] tiles = new Tile[10][10];
    
    public Playfield()
    {
	this.setVisible(true);
	createVakjes();
    }
    
    private void createVakjes()
    {	
	
	for (int i = 0; i < tiles.length; i++) 
	{
	    for (int j = 0; j < tiles[1].length; j++) 
	    {	
		if((i == 0 || j == 0 || i == (tiles[i].length - 1) || j == (tiles.length - 1)))
		{
		    Tile vak = new Tile(i, j);
		    vak.setGameObject(new Wall());
		    tiles[i][j] = vak;
		}
		else
		{
		    tiles[i][j] = new Tile(i, j);
		}
	    }
	}
	
	tiles[2][2].setGameObject(new Wall());
	tiles[3][2].setGameObject(new Wall());
	tiles[4][2].setGameObject(new Wall());
	tiles[6][2].setGameObject(new Wall());
	tiles[7][2].setGameObject(new Wall());
	
	tiles[1][4].setGameObject(new Wall());
	tiles[3][4].setGameObject(new Wall());
	tiles[4][4].setGameObject(new Wall());
	tiles[6][4].setGameObject(new Wall());
	tiles[8][4].setGameObject(new Wall());
	
	GameObject pacman = new Pacman();
	this.addKeyListener((KeyListener) pacman);
	tiles[5][5].setGameObject(pacman);
	
	tiles[2][6].setGameObject(new Wall());
	tiles[3][6].setGameObject(new Wall());
	tiles[4][6].setGameObject(new Wall());
	tiles[6][6].setGameObject(new Wall());
	tiles[7][6].setGameObject(new Wall());
	
	tiles[1][6].setGameObject(new Wall());
	tiles[3][8].setGameObject(new Wall());
	tiles[6][8].setGameObject(new Wall());
	tiles[6][7].setGameObject(new Wall());
    }

    @Override
    public void paint(Graphics g) 
    {	
	
	for (int i = 0; i < tiles.length; i++) 
	{
	    for (int j = 0; j < tiles[i].length; j++) 
	    {		
		int x = 25 * i;
		int y = 25 * j;
		
		if(tiles[i][j].getGameObject() == null)
		{
		    g.setColor(Color.BLACK);
		    g.fillRect(x, y, 25, 25);
		}
		else if(tiles[i][j].getGameObject() instanceof Wall)
		{
		    g.setColor(Color.BLUE);
		    g.fillRect(x, y, 25, 25);
		}
		else if(tiles[i][j].getGameObject() instanceof Pacman)
		{
		    g.setColor(Color.YELLOW);
		    g.fillRect(x, y, 25, 25);
		}
	    }
	}
    }
}
